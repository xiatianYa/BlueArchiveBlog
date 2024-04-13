<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户名称" prop="userId">
        <el-input v-model="queryParams.userId" placeholder="请输入用户名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="文章名称" prop="userId">
        <el-input v-model="queryParams.articleName" placeholder="请输入文章名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="分类名称" prop="sortId">
        <el-select v-model="queryParams.sortId" placeholder="请选择分类名词" clearable>
          <el-option v-for="dict in sortDict" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="审核状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择审核状态" clearable>
          <el-option v-for="dict in dict.type.sys_shenhe" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="文章内容" prop="content">
        <el-input v-model="queryParams.content" placeholder="请输入文章内容" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['blog:article:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="articleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" width="40" />
      <el-table-column label="用户名称" align="center" prop="userName" />
      <el-table-column label="文章名称" align="center" prop="articleName" />
      <el-table-column label="分类名称" align="center" prop="sortName" />
      <el-table-column label="审核状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_shenhe" :value="scope.row.status" />
        </template>
      </el-table-column>
      <el-table-column label="文章视频" align="center" prop="videoUrl" />
      <el-table-column label="文章图片" align="center" prop="cover" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.cover" :width="50" :height="50" />
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" circle @click="goEdit(scope.row.id)"></el-button>
          <el-button type="success" icon="el-icon-check" circle @click="success(scope.row)"></el-button>
          <el-button type="danger" icon="el-icon-delete" circle @click="danger(scope.row)"></el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

  </div>
</template>

<script>
import {addArticle, delArticle, getArticle, listArticle, updateArticle} from "@/api/blog/article";
import {listSort} from '@/api/sort/sort'

export default {
  name: "Article",
  dicts: ['sys_shenhe'],
  data() {
    return {
      //分类列表
      sortDict: [],
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
      // 文章表格数据
      articleList: [],
      // 弹出层标题
      title: "",
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        Name: null,
        userId: null,
        sortId: null,
        status: null,
        videoUrl: null,
        content: null,
        cover: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "用户名称不能为空", trigger: "blur" }
        ],
        sortId: [
          { required: true, message: "分类名称不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "审核状态不能为空", trigger: "change" }
        ],
        content: [
          { required: true, message: "文章内容不能为空", trigger: "blur" }
        ],
        cover: [
          { required: true, message: "文章图片不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    //获取分类列表
    //默认查询未审核
    if (!this.queryParams.status) {
      this.queryParams.status = "0"
    }
    listSort().then(res => {
      for (const item of res.rows) {
        this.sortDict.push({ value: item.id.toString(), label: item.sortName })
      }
    })
    this.getList();
  },
  methods: {
    //前往编辑页面
    goEdit(sortId){
      this.$router.push({path:"/article/edit",query:{sortId:sortId}})
    },
    //通过
    success(article) {
      article.status = "1";
      updateArticle(article).then(res => {
        this.$modal.msgSuccess("修改成功");
        this.getList()
      })
    },
    //驳回
    danger(article) {
      article.status = "2";
      updateArticle(article).then(res => {
        this.$modal.msgSuccess("修改成功");
        this.getList()
      })
    },
    /** 查询文章列表 */
    getList() {
      this.loading = true;
      listArticle(this.queryParams).then(response => {
        this.articleList = response.rows;
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
        userId: null,
        sortId: null,
        status: null,
        videoUrl: null,
        content: null,
        cover: null,
        createTime: null,
        updateTime: null,
        createBy: null,
        updateBy: null,
        remark: null
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
      this.title = "添加文章";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getArticle(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改文章";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateArticle(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addArticle(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除文章编号为"' + ids + '"的数据项？').then(function () {
        return delArticle(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('blog/article/export', {
        ...this.queryParams
      }, `article_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
