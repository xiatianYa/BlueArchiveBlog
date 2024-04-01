<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="番剧名称" prop="pixivName">
        <el-input v-model="queryParams.pixivName" placeholder="请输入番剧名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="番剧类型" prop="typeName">
        <el-select v-model="queryParams.status" placeholder="请选择番剧类型" clearable>
          <el-option v-for="dict in pixiv_type" :key="dict.value" :label="dict.label"
            :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="发布时间" prop="pixivPublish">
        <el-date-picker clearable v-model="queryParams.pixivPublish" type="date" value-format="yyyy-MM-dd"
          placeholder="请选择番剧发布时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="评分" prop="pixivScore">
        <el-input v-model="queryParams.pixivScore" placeholder="请输入评分" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="声优" prop="pixivVoiceActor">
        <el-input v-model="queryParams.pixivVoiceActor" placeholder="请输入声优" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="播放数" prop="pixivPlay">
        <el-input v-model="queryParams.pixivPlay" placeholder="请输入播放数" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="审核状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择审核状态" clearable>
          <el-option v-for="dict in dict.type.sys_apply_status" :key="dict.value" :label="dict.label"
            :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="是否完结" prop="isEnd">
        <el-select v-model="queryParams.isEnd" placeholder="请选择是否完结" clearable>
          <el-option v-for="dict in dict.type.system_is_end" :key="dict.value" :label="dict.label"
            :value="dict.value" />
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
          v-hasPermi="['blog:tv:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['blog:tv:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['blog:tv:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['blog:tv:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="tvList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="番剧名称" align="center" prop="pixivName" />
      <el-table-column label="番剧图片" align="center" prop="pixivAvater" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.pixivAvater" :width="50" :height="50" />
        </template>
      </el-table-column>
      <el-table-column label="番剧发布时间" align="center" prop="pixivPublish" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.pixivPublish, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="评分" align="center" prop="pixivScore" />
      <el-table-column label="番剧类型" align="center" prop="typeName"/>
      <el-table-column label="声优" align="center" prop="pixivVoiceActor" show-overflow-tooltip />
      <el-table-column label="番剧简介" align="center" prop="pixivSynopsis" show-overflow-tooltip />
      <el-table-column label="播放数" align="center" prop="pixivPlay" />
      <el-table-column label="审核状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_apply_status" :value="scope.row.status" />
        </template>
      </el-table-column>
      <el-table-column label="是否完结" align="center" prop="isEnd">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.system_is_end" :value="scope.row.isEnd" />
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['blog:tv:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['blog:tv:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改番剧信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="番剧名称" prop="pixivName">
          <el-input v-model="form.pixivName" placeholder="请输入番剧名称" />
        </el-form-item>
        <el-form-item label="番剧图片" prop="pixivAvater">
          <image-upload v-model="form.pixivAvater" />
        </el-form-item>
        <el-form-item label="番剧集">
          <el-button type="primary" @click="uploadEpisode(form)">上传番剧集<i class="el-icon-upload el-icon--right"></i></el-button>
        </el-form-item>
        <el-form-item label="发布时间" prop="pixivPublish">
          <el-date-picker clearable v-model="form.pixivPublish" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择番剧发布时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="评分" prop="pixivScore">
          <el-input v-model="form.pixivScore" placeholder="请输入评分 如:9.7" />
        </el-form-item>
        <el-form-item label="声优" prop="pixivVoiceActor">
          <el-input v-model="form.pixivVoiceActor" placeholder="请输入声优 如:角色1:名字 角色2:名字" />
        </el-form-item>
        <el-form-item label="番剧简介" prop="pixivSynopsis">
          <el-input v-model="form.pixivSynopsis" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="番剧类型" prop="pixiv_type">
          <el-select v-model="form.pixivType" placeholder="请选择番剧类型">
            <el-option v-for="dict in pixiv_type" :key="dict.value" :label="dict.label"
              :value="parseInt(dict.value)"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="审核状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio v-for="dict in dict.type.sys_apply_status" :key="dict.value"
              :label="parseInt(dict.value)">{{ dict.label }}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否完结" prop="isEnd">
          <el-select v-model="form.isEnd" placeholder="请选择是否完结">
            <el-option v-for="dict in dict.type.system_is_end" :key="dict.value" :label="dict.label"
              :value="parseInt(dict.value)"></el-option>
          </el-select>
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
import {addTv, delTv, getTv, listTv, updateTv} from "@/api/blog/tv";
import {listType} from "@/api/sort/type";

export default {
  name: "Tv",
  dicts: ['system_is_end', 'sys_apply_status'],
  data() {
    return {
      //番剧类型
      pixiv_type: [],
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
      // 番剧信息表格数据
      tvList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        pixivName: null,
        pixivAvater: null,
        pixivPublish: null,
        pixivScore: null,
        pixivType: null,
        pixivVoiceActor: null,
        pixivSynopsis: null,
        pixivPlay: null,
        status: null,
        isEnd: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        pixivName: [
          { required: true, message: "番剧名称不能为空", trigger: "blur" }
        ],
        pixivAvater: [
          { required: true, message: "番剧图片不能为空", trigger: "blur" }
        ],
        pixivPublish: [
          { required: true, message: "番剧发布时间不能为空", trigger: "blur" }
        ],
        pixivScore: [
          { required: true, message: "评分不能为空", trigger: "blur" }
        ],
        pixivType: [
          { required: true, message: "番剧类型不能为空", trigger: "change" }
        ],
        pixivVoiceActor: [
          { required: true, message: "声优不能为空", trigger: "blur" }
        ],
        pixivSynopsis: [
          { required: true, message: "番剧简介不能为空", trigger: "blur" }
        ],
        pixivPlay: [
          { required: true, message: "播放数不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "审核状态不能为空", trigger: "change" }
        ],
        isEnd: [
          { required: true, message: "是否完结不能为空", trigger: "change" }
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
    listType().then(res => {
      for (const tvType of res.rows) {
        this.pixiv_type.push({ value: tvType.id, label: tvType.typeName })
      }
    })
  },
  methods: {
    /** 上传番剧集 */
    uploadEpisode(pixivId){
      console.log(pixivId);
    },
    /** 查询番剧信息列表 */
    getList() {
      this.loading = true;
      listTv(this.queryParams).then(response => {
        this.tvList = response.rows;
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
        pixivName: null,
        pixivAvater: null,
        pixivPublish: null,
        pixivScore: null,
        pixivType: null,
        pixivVoiceActor: null,
        pixivSynopsis: null,
        pixivPlay: null,
        status: null,
        isEnd: null,
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
      this.title = "添加番剧信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getTv(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改番剧信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateTv(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTv(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除番剧信息编号为"' + ids + '"的数据项？').then(function () {
        return delTv(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('blog/tv/export', {
        ...this.queryParams
      }, `tv_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
