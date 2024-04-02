<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="二创作者" prop="ecAuthor">
        <el-input v-model="queryParams.ecAuthor" placeholder="请输入二创作者" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="二创标题" prop="ecName">
        <el-input v-model="queryParams.ecName" placeholder="请输入二创标题" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="二创路径" prop="ecUrl">
        <el-input v-model="queryParams.ecUrl" placeholder="请输入二创路径" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="二创发布时间" prop="ecPublish">
        <el-date-picker clearable v-model="queryParams.ecPublish" type="date" value-format="yyyy-MM-dd"
          placeholder="请选择二创发布时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="审核状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择审核状态" clearable>
          <el-option v-for="dict in dict.type.sys_shenhe" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['blog:erchuang:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['blog:erchuang:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['blog:erchuang:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['blog:erchuang:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="erchuangList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="二创作者" align="center" prop="ecAuthor" />
      <el-table-column label="二创标题" align="center" prop="ecName" />
      <el-table-column label="二创图片" align="center" prop="ecAvater" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.ecAvater" :width="50" :height="50" />
        </template>
      </el-table-column>
      <el-table-column label="二创路径" align="center" prop="ecUrl" />
      <el-table-column label="二创发布时间" align="center" prop="ecPublish" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.ecPublish, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="二创简介" align="center" prop="ecSynopsis" />
      <el-table-column label="审核状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_shenhe" :value="scope.row.status" />
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['blog:erchuang:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['blog:erchuang:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改二创信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="二创作者" prop="ecAuthor">
          <el-input v-model="form.ecAuthor" placeholder="请输入二创作者" />
        </el-form-item>
        <el-form-item label="二创标题" prop="ecName">
          <el-input v-model="form.ecName" placeholder="请输入二创标题" />
        </el-form-item>
        <el-form-item label="二创图片" prop="ecAvater">
          <image-upload v-model="form.ecAvater" />
        </el-form-item>
        <el-form-item label="二创路径" prop="ecUrl">
          <el-input v-model="form.ecUrl" placeholder="请输入二创路径" />
        </el-form-item>
        <el-form-item label="二创发布时间" prop="ecPublish">
          <el-date-picker clearable v-model="form.ecPublish" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择二创发布时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="二创简介" prop="ecSynopsis">
          <el-input v-model="form.ecSynopsis" type="textarea" placeholder="请输入内容" />
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
import {addErchuang, delErchuang, getErchuang, listErchuang, updateErchuang} from "@/api/blog/erchuang";

export default {
  name: "Erchuang",
  dicts: ['sys_shenhe'],
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
      // 二创信息表格数据
      erchuangList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        ecAuthor: null,
        ecName: null,
        ecAvater: null,
        ecUrl: null,
        ecPublish: null,
        ecSynopsis: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        ecAuthor: [
          { required: true, message: "二创作者不能为空", trigger: "blur" }
        ],
        ecName: [
          { required: true, message: "二创标题不能为空", trigger: "blur" }
        ],
        ecAvater: [
          { required: true, message: "二创图片不能为空", trigger: "blur" }
        ],
        ecUrl: [
          { required: true, message: "二创路径不能为空", trigger: "blur" }
        ],
        ecPublish: [
          { required: true, message: "二创发布时间不能为空", trigger: "blur" }
        ],
        ecSynopsis: [
          { required: true, message: "二创简介不能为空", trigger: "blur" }
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
  },
  methods: {
    /** 查询二创信息列表 */
    getList() {
      this.loading = true;
      listErchuang(this.queryParams).then(response => {
        this.erchuangList = response.rows;
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
        ecAuthor: null,
        ecName: null,
        ecAvater: null,
        ecUrl: null,
        ecPublish: null,
        ecSynopsis: null,
        status: null,
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
      this.title = "添加二创信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getErchuang(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改二创信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateErchuang(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addErchuang(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除二创信息编号为"' + ids + '"的数据项？').then(function () {
        return delErchuang(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('blog/erchuang/export', {
        ...this.queryParams
      }, `erchuang_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
