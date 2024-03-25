<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="音乐名称" prop="musicName">
        <el-input
          v-model="queryParams.musicName"
          placeholder="请输入音乐名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="音乐分类名称" prop="sortId">
        <el-select v-model="queryParams.sortId" placeholder="请选择音乐分类名称" clearable>
          <el-option
            v-for="dict in musicDict"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['blog:music:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['blog:music:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['blog:music:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['blog:music:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="musicList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="图片路径" align="center" prop="imgUrl" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.imgUrl" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="音乐的路径" align="center" prop="musicUrl" show-overflow-tooltip="true" />
      <el-table-column label="音乐名称" align="center" prop="musicName" show-overflow-tooltip="true" />
      <el-table-column label="音乐分类名称" align="center" prop="sortName" show-overflow-tooltip="true"/>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['blog:music:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['blog:music:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改音乐对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="图片路径" prop="imgUrl">
          <image-upload v-model="form.imgUrl"/>
        </el-form-item>
        <el-form-item label="音乐的路径" prop="musicUrl">
          <file-upload v-model="form.musicUrl"/>
        </el-form-item>
        <el-form-item label="音乐名称" prop="musicName">
          <el-input v-model="form.musicName" placeholder="请输入音乐名称" />
        </el-form-item>
        <el-form-item label="音乐分类ID" prop="sortId">
          <el-select v-model="form.sortId" placeholder="请选择音乐分类ID">
            <el-option
              v-for="dict in musicDict"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
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
import {addMusic, delMusic, getMusic, listMusic, updateMusic} from "@/api/blog/music";
import {listSort} from "@/api/sort/music";

export default {
  name: "Music",
  data() {
    return {
      //音乐分类字典
      musicDict:[],
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
      // 音乐表格数据
      musicList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        imgUrl: null,
        musicUrl: null,
        musicName: null,
        sortId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        musicUrl: [
          { required: true, message: "音乐的路径不能为空", trigger: "blur" }
        ],
        musicName: [
          { required: true, message: "音乐名称不能为空", trigger: "blur" }
        ],
        sortId: [
          { required: true, message: "音乐分类ID不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    listSort().then(res=>{
      for (const item of res.rows) {
        this.musicDict.push({ value: item.id, label: item.sortName })
      }
      console.log(this.musicDict);
    })
    this.getList();
  },
  methods: {
    /** 查询音乐列表 */
    getList() {
      this.loading = true;
      listMusic(this.queryParams).then(response => {
        this.musicList = response.rows;
        //循环查找出分类
        for (let index = 0; index < this.musicList.length; index++) {
          let item = this.musicDict.find(n => n.value === this.musicList[index].sortId)
          if (!item) {
            item.label = "未知分类";
          }
          this.musicList[index].sortName = item.label
        }
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
        imgUrl: null,
        musicUrl: null,
        musicName: null,
        sortId: null,
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
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加音乐";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getMusic(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改音乐";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateMusic(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMusic(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除音乐编号为"' + ids + '"的数据项？').then(function() {
        return delMusic(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('blog/music/export', {
        ...this.queryParams
      }, `music_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
