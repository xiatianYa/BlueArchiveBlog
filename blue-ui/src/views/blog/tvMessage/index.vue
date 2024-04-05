<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="番剧ID" prop="pixivId">
        <el-input v-model="queryParams.pixivId" placeholder="请输入番剧ID" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="集ID" prop="episodeId">
        <el-input v-model="queryParams.episodeId" placeholder="请输入集ID" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="是否描边" prop="border">
        <el-input v-model="queryParams.border" placeholder="请输入是否描边 0表示描边,1不描边" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="背景色" prop="color">
        <el-input v-model="queryParams.color" placeholder="请输入背景色" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="弹幕内容" prop="text">
        <el-input v-model="queryParams.text" placeholder="请输入弹幕内容" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="发送时间" prop="time">
        <el-input v-model="queryParams.time" placeholder="请输入弹幕发送时间" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="弹幕模式" prop="mode">
        <el-input v-model="queryParams.mode" placeholder="请输入弹幕模式: 0表示滚动, 1静止" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['blog:message:remove']">删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="messageList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="番剧ID" align="center" prop="pixivId" />
      <el-table-column label="集ID" align="center" prop="episodeId" />
      <el-table-column label="是否描边" align="center" prop="border" />
      <el-table-column label="背景色" align="center" prop="color" />
      <el-table-column label="弹幕内容" align="center" prop="text" />
      <el-table-column label="弹幕发送时间" align="center" prop="time" />
      <el-table-column label="弹幕模式" align="center" prop="mode" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['blog:message:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['blog:message:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改番剧弹幕对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="番剧ID" prop="pixivId">
          <el-input v-model="form.pixivId" placeholder="请输入番剧ID" />
        </el-form-item>
        <el-form-item label="集ID" prop="episodeId">
          <el-input v-model="form.episodeId" placeholder="请输入集ID" />
        </el-form-item>
        <el-form-item label="是否描边 0表示描边,1不描边" prop="border">
          <el-input v-model="form.border" placeholder="请输入是否描边 0表示描边,1不描边" />
        </el-form-item>
        <el-form-item label="背景色" prop="color">
          <el-input v-model="form.color" placeholder="请输入背景色" />
        </el-form-item>
        <el-form-item label="弹幕内容" prop="text">
          <el-input v-model="form.text" placeholder="请输入弹幕内容" />
        </el-form-item>
        <el-form-item label="弹幕发送时间" prop="time">
          <el-input v-model="form.time" placeholder="请输入弹幕发送时间" />
        </el-form-item>
        <el-form-item label="弹幕模式: 0表示滚动, 1静止" prop="mode">
          <el-input v-model="form.mode" placeholder="请输入弹幕模式: 0表示滚动, 1静止" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {addMessage, delMessage, getMessage, listMessage, updateMessage} from "@/api/blog/pixivMessage";

export default {
  name: "Message",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 番剧弹幕表格数据
      messageList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        pixivId: null,
        episodeId: null,
        border: null,
        color: null,
        text: null,
        time: null,
        mode: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        pixivId: [
          { required: true, message: "番剧ID不能为空", trigger: "blur" }
        ],
        episodeId: [
          { required: true, message: "集ID不能为空", trigger: "blur" }
        ],
        border: [
          { required: true, message: "是否描边 0表示描边,1不描边不能为空", trigger: "blur" }
        ],
        color: [
          { required: true, message: "背景色不能为空", trigger: "blur" }
        ],
        text: [
          { required: true, message: "弹幕内容不能为空", trigger: "blur" }
        ],
        time: [
          { required: true, message: "弹幕发送时间不能为空", trigger: "blur" }
        ],
        mode: [
          { required: true, message: "弹幕模式: 0表示滚动, 1静止不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询番剧弹幕列表 */
    getList() {
      this.loading = true;
      listMessage(this.queryParams).then(response => {
        this.messageList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        pixivId: null,
        episodeId: null,
        border: null,
        color: null,
        text: null,
        time: null,
        mode: null,
        createTime: null,
        updateTime: null,
        createBy: null,
        updateBy: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加番剧弹幕";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getMessage(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改番剧弹幕";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateMessage(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMessage(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除番剧弹幕编号为"' + ids + '"的数据项？').then(function () {
        return delMessage(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('blog/message/export', {
        ...this.queryParams
      }, `message_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
