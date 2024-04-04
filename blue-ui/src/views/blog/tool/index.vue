<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="工具分类" prop="sortId">
        <el-select v-model="queryParams.sortId" placeholder="请选择分类" clearable>
          <el-option v-for="dict in toolDict" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="工具名称" prop="toolName">
        <el-input v-model="queryParams.toolName" placeholder="请输入工具名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['blog:tool:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['blog:tool:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['blog:tool:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['blog:tool:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="toolList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="工具分类" align="center" prop="sortId">
        <template slot-scope="scope">
          {{ scope.row.sortName }}
        </template>
      </el-table-column>
      <el-table-column label="工具名称" align="center" prop="toolName" />
      <el-table-column label="工具简介" align="center" prop="toolDescribe" />
      <el-table-column label="工具网址" align="center" prop="toolUrl" />
      <el-table-column label="工具图片" align="center" prop="toolAvater" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.toolAvater" :width="50" :height="50" />
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['blog:tool:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['blog:tool:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改编程工具对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="工具分类" prop="sortId">
          <el-select v-model="form.sortId" placeholder="请选择分类名称">
            <el-option v-for="dict in toolDict" :key="dict.value" :label="dict.label"
              :value="parseInt(dict.value)"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="工具名称" prop="toolName">
          <el-input v-model="form.toolName" placeholder="请输入工具名称" />
        </el-form-item>
        <el-form-item label="工具简介" prop="toolDescribe">
          <el-input v-model="form.toolDescribe" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="工具网址" prop="toolUrl">
          <el-input v-model="form.toolUrl" placeholder="请输入工具网址" />
        </el-form-item>
        <el-form-item label="工具图片" prop="toolAvater">
          <image-upload v-model="form.toolAvater" />
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
import {addTool, delTool, getTool, listTool, updateTool} from "@/api/blog/tool";
import {listSort} from '@/api/sort/toolSort'

export default {
  name: "Tool",
  data() {
    return {
      //工具分类Dict
      toolDict: [],
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
      // 编程工具表格数据
      toolList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sortId: null,
        toolName: null,
        toolDescribe: null,
        toolAvater: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        sortId: [
          { required: true, message: "工具分类不能为空", trigger: "blur" }
        ],
        toolName: [
          { required: true, message: "工具名称不能为空", trigger: "blur" }
        ],
        toolDescribe: [
          { required: true, message: "工具简介不能为空", trigger: "blur" }
        ],
        toolAvater: [
          { required: true, message: "工具图片不能为空", trigger: "blur" }
        ],
        toolUrl: [
          { required: true, message: "工具网址不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        createBy: [
          { required: true, message: "创建人不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    //获取编程工具分类列表
    listSort().then(res => {
      for (const item of res.rows) {
        this.toolDict.push({ value: item.id, label: item.sortName })
      }
    })
  },
  methods: {
    /** 查询编程工具列表 */
    getList() {
      this.loading = true;
      listTool(this.queryParams).then(response => {
        this.toolList = response.rows;
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
        sortId: null,
        toolName: null,
        toolDescribe: null,
        toolAvater: null,
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
      this.title = "添加编程工具";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getTool(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改编程工具";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateTool(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTool(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除编程工具编号为"' + ids + '"的数据项？').then(function () {
        return delTool(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('blog/tool/export', {
        ...this.queryParams
      }, `tool_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
