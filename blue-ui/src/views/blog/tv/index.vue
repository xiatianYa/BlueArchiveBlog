<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="番剧名称" prop="pixivName">
        <el-input v-model="queryParams.pixivName" placeholder="请输入番剧名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="番剧类型" prop="typeName">
        <el-select v-model="queryParams.pixivType" placeholder="请选择番剧类型" clearable>
          <el-option v-for="dict in pixiv_type" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
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
          <el-option v-for="dict in dict.type.sys_shenhe" :key="dict.value" :label="dict.label" :value="dict.value" />
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
      <el-table-column label="番剧类型" align="center" prop="typeName" />
      <el-table-column label="声优" align="center" prop="pixivVoiceActor" show-overflow-tooltip />
      <el-table-column label="番剧简介" align="center" prop="pixivSynopsis" show-overflow-tooltip />
      <el-table-column label="播放数" align="center" prop="pixivPlay" />
      <el-table-column label="审核状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_shenhe" :value="scope.row.status" />
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
        <el-form-item label="番剧集" v-show="form.id">
          <el-button type="primary" @click="uploadEpisode(form.id)">上传番剧集<i
              class="el-icon-upload el-icon--right"></i></el-button>
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
    <!-- 添加番剧集对话框 -->
    <el-dialog title="番剧集" :visible.sync="dialogEpisodeVisible">
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleEpisodeAdd"
            v-hasPermi="['blog:tv:add']">新增</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
      <el-table :data="EpisodeData" style="width: 100%">
        <el-table-column prop="pixivChapters" label="番剧集" width="180">
        </el-table-column>
        <el-table-column prop="pixivUrl" label="番剧地址" width="180" show-overflow-tooltip>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="danger" icon="el-icon-delete" circle @click="deleteEpisode(scope.row.id)"></el-button>
          </template>
        </el-table-column>
      </el-table>
      <pagination v-show="EpisodeTotal > 0" :total="EpisodeTotal" :page.sync="EpisodequeryParams.pageNum" :limit.sync="EpisodequeryParams.pageSize"
        @pagination="listEpisodeByTvId" />
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogEpisodeVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveEpisode">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 添加番剧集单条记录对话框 -->
    <el-dialog title="添加番剧集" :visible.sync="addEpisodeVisible" width="500px" append-to-body>
      <el-form ref="episodeForm" :model="addEpisodeForm" :rules="episodeRules" label-width="80px">
        <el-form-item label="番剧集" prop="pixivChapters">
          <el-input-number v-model="addEpisodeForm.pixivChapters" :min="1" label="番剧第几集"></el-input-number>
        </el-form-item>
        <el-form-item label="番剧集路径" prop="pixivUrl">
          <file-upload v-model="addEpisodeForm.pixivUrl" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addEpisode">确 定</el-button>
          <el-button @click="closeAddEpisode">取 消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import {addTv, delTv, getTv, listTv, updateTv} from "@/api/blog/tv";
import {listType} from "@/api/sort/pixivSort";
import {addEpisode, delEpisode, listEpisode} from '@/api/blog/episode';

export default {
  name: "Tv",
  dicts: ['system_is_end', 'sys_shenhe'],
  data() {
    return {
      //番剧集校验
      episodeRules: {
        pixivChapters: [
          { required: true, message: "番剧集不能为空", trigger: "blur" }
        ],
        pixivUrl: [
          { required: true, message: "番剧视频不能为空", trigger: "blur" }
        ],
      },
      //添加番剧集
      addEpisodeForm: {},
      //添加番剧集拟态框
      addEpisodeVisible: false,
      //番剧集列表
      EpisodeData: [],
      //番剧集拟态框
      dialogEpisodeVisible: false,
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
      // 总条数
      EpisodeTotal: 0,
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
      // 番剧集查询参数
      EpisodequeryParams: {
        pageNum: 1,
        pageSize: 10,
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
    resetAddEpisode() {
      this.addEpisodeForm = {
        pixivId: "",
        pixivChapters: "",
        pixivUrl: "",
      }
    },
    /** 删除单条番剧集 */
    deleteEpisode(id) {
      this.$modal.confirm('是否确认删除番剧集信息编号为"' + id + '"的数据项？').then(function () {
        return delEpisode(id);
      }).then(() => {
        this.$modal.msgSuccess("删除成功");
        //重新查询番剧集
        this.listEpisodeByTvId();
      }).catch(() => {
        this.$modal.msgError("删除失败");
      });
    },
    /** 添加单条番剧集 */
    addEpisode() {
      this.$refs["episodeForm"].validate(valid => {
        if (valid) {
          this.addEpisodeForm.pixivId = this.form.id;
          //保存记录
          addEpisode(this.addEpisodeForm).then(response => {
            this.$modal.msgSuccess("添加成功");
            this.listEpisodeByTvId();
          }).catch(error => {
            this.$modal.msgError("添加失败");
          })
          this.resetAddEpisode();
          this.addEpisodeVisible = false;
        }
      });

    },
    /** 关闭单挑番剧集 */
    closeAddEpisode() {
      this.resetAddEpisode()
      this.addEpisodeVisible = false;
    },
    /** 番剧集保存 */
    saveEpisode() {
      this.dialogEpisodeVisible = false;
    },
    /** 番剧集添加 */
    handleEpisodeAdd() {
      this.addEpisodeVisible = true;
    },
    /** 上传番剧集 */
    uploadEpisode() {
      //打开拟态框
      this.dialogEpisodeVisible = true;
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
    /** 查询当前番剧的番剧集列表 */
    listEpisodeByTvId() {
      //清空列表
      this.EpisodequeryParams.pixivId = this.form.id;
      listEpisode(this.EpisodequeryParams).then(res => {
        this.EpisodeTotal=res.total;
        this.EpisodeData = []
        for (const episode of res.rows) {
          this.EpisodeData.push(episode);
        }
      })
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
      this.EpisodeData = [];
      this.open = true;
      this.title = "添加番剧信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.EpisodeData = [];
      const id = row.id || this.ids
      getTv(id).then(response => {
        this.form = response.data;
        //查询当前番剧拥有的番剧集
        this.listEpisodeByTvId()
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
