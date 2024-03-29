<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户名称" prop="userId">
        <el-input v-model="queryParams.userId" placeholder="请输入用户名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="文章名称" prop="articleName">
        <el-input v-model="queryParams.articleName" placeholder="请输入文章名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="文章描述" prop="articleDescribe">
        <el-input v-model="queryParams.articleDescribe" placeholder="请输入文章描述" clearable
          @keyup.enter.native="handleQuery" />
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
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['blog:article:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['blog:article:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['blog:article:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['blog:article:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="articleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="用户名称" align="center" prop="userName" />
      <el-table-column label="文章名称" align="center" prop="articleName" />
      <el-table-column label="文章描述" align="center" prop="articleDescribe" />
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
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['blog:article:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['blog:article:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改文章对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="文章名称" prop="articleName">
          <el-input v-model="form.articleName" placeholder="请输入文章名称" />
        </el-form-item>
        <el-form-item label="文章描述" prop="articleName">
          <el-input v-model="form.articleDescribe" placeholder="请输入文章描述" />
        </el-form-item>
        <el-form-item label="分类名称" prop="sortId">
          <el-select v-model="form.sortId" placeholder="请选择分类名称">
            <el-option v-for="dict in sortDict" :key="dict.value" :label="dict.label"
              :value="parseInt(dict.value)"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="标签关联" prop="checkedTags">
          <el-checkbox-group v-model="checkedTags" :min="1" :max="4">
            <el-checkbox v-for="tag in tags" :label="tag.tagId" :key="tag.tagName">{{ tag.tagName }}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="文章编辑" prop="sortId" v-show="form.id">
          <el-button type="primary" icon="el-icon-edit" circle @click="editArticle(form.id)" />
        </el-form-item>
        <el-form-item label="文章视频" prop="videoUrl">
          <file-upload v-model="form.videoUrl" />
        </el-form-item>
        <el-form-item label="文章图片" prop="cover">
          <image-upload v-model="form.cover" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
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
import {addArticle, delArticle, getArticle, listArticle, updateArticle} from "@/api/blog/article";
import {listSort} from '@/api/sort/sort'

export default {
  name: "Article",
  dicts: ['sys_shenhe'],
  data() {
    return {
      //被选择的标签
      checkedTags: [],
      //是否被全选
      checkAll: false,
      //全部标签
      tags: [],
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
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        articleName: null,
        articleDescribe: null,
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
        articleName: [
          { required: true, message: "文章名称不能为空", trigger: "blur" }
        ],
        articleDescribe: [
          { required: true, message: "文章描述不能为空", trigger: "blur" }
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
    listSort().then(res => {
      for (const item of res.rows) {
        this.sortDict.push({ value: item.id, label: item.sortName, tagList: item.tagList })
      }
    })
    this.getList();
  },
  methods: {
    //前往编辑文字
    editArticle(sortId) {
      this.$router.push({ path: "/article/edit", query: { sortId: sortId } })
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
        remark: null,
      };
      this.checkedTags = [];
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
        for (const tag of this.form.tagList) {
          this.checkedTags.push(tag.tagId)
        }
        this.open = true;
        this.title = "修改文章";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        //获取被选中的标签
        const tagList = [];
        for (const tagId of this.checkedTags) {
          tagList.push({ tagId: tagId })
        }
        this.form.tagList = tagList;
        if (valid) {
          if (this.form.id != null) {
            updateArticle(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            if (this.checkedTags.length == 0) {
              this.$message({
                message: '请至少选中一个标签!',
                type: 'warning'
              });
              return;
            }
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
  },
  watch: {
    'form.sortId'(newVal, oldVal) {
      this.tags = [];
      const sort = this.sortDict.find(item => newVal === item.value)
      if (sort) {
        for (const tag of sort.tagList) {
          this.tags.push({ tagId: tag.id, tagName: tag.tagName });
        }
      }
    },
  },
};
</script>
