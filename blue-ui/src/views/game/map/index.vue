<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="地图名称" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入地图名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="译名" prop="label">
        <el-input v-model="queryParams.label" placeholder="请输入译名" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="模式名称" prop="modeId">
        <el-select v-model="queryParams.modeId" placeholder="请选择模式名称" clearable>
          <el-option v-for="dict in dict.type.sys_game_mode" :key="dict.value" :label="dict.label"
            :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="地图难度" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择地图难度" clearable>
          <el-option v-for="dict in dict.type.sys_game_type" :key="dict.value" :label="dict.label"
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
          v-hasPermi="['game:map:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['game:map:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['game:map:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['game:map:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="mapList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="地图名称" align="center" prop="name" />
      <el-table-column label="译名" align="center" prop="label" />
      <el-table-column label="图片路径" align="center" prop="mapUrl" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.mapUrl" :width="50" :height="50" />
        </template>
      </el-table-column>
      <el-table-column label="模式名称" align="center" prop="modeId">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_game_mode" :value="scope.row.modeId" />
        </template>
      </el-table-column>
      <el-table-column label="地图难度" align="center" prop="type">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_game_type" :value="scope.row.type" />
        </template>
      </el-table-column>
      <el-table-column label="地图标签" align="center" prop="tag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_game_tag" :value="scope.row.tag ? scope.row.tag.split(',') : []" />
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['game:map:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['game:map:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改游戏地图对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="地图名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入地图名称" />
        </el-form-item>
        <el-form-item label="译名" prop="label">
          <el-input v-model="form.label" placeholder="请输入译名" />
        </el-form-item>
        <el-form-item label="图片路径" prop="mapUrl">
          <image-upload v-model="form.mapUrl" />
        </el-form-item>
        <el-form-item label="模式名称" prop="modeId">
          <el-select v-model="form.modeId" placeholder="请选择模式名称">
            <el-option v-for="dict in dict.type.sys_game_mode" :key="dict.value" :label="dict.label"
              :value="parseInt(dict.value)"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="地图难度" prop="type">
          <el-select v-model="form.type" placeholder="请选择地图难度">
            <el-option v-for="dict in dict.type.sys_game_type" :key="dict.value" :label="dict.label"
              :value="parseInt(dict.value)"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="地图标签" prop="tag">
          <el-checkbox-group v-model="form.tag">
            <el-checkbox v-for="dict in dict.type.sys_game_tag" :key="dict.value" :label="dict.value">
              {{ dict.label }}
            </el-checkbox>
          </el-checkbox-group>
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
import { getMap, listMap, addMap, updateMap, delMap } from "@/api/game/map.js";

export default {
  name: "Map",
  dicts: ['sys_game_tag', 'sys_game_mode', 'sys_game_type'],
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
      // 游戏地图表格数据
      mapList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        label: null,
        modeId: null,
        type: null,
        tag: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "地图名称不能为空", trigger: "blur" }
        ],
        label: [
          { required: true, message: "译名不能为空", trigger: "blur" }
        ],
        modeId: [
          { required: true, message: "模式名称不能为空", trigger: "change" }
        ],
        type: [
          { required: true, message: "地图难度不能为空", trigger: "change" }
        ],
        tag: [
          { required: true, message: "地图标签不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询游戏地图列表 */
    getList() {
      this.loading = true;
      listMap(this.queryParams).then(response => {
        this.mapList = response.rows;
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
        name: null,
        label: null,
        mapUrl: null,
        modeId: null,
        type: null,
        tag: [],
        delFlag: null
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
      this.title = "添加游戏地图";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getMap(id).then(response => {
        this.form = response.data;
        this.form.tag = this.form.tag.split(",");
        this.open = true;
        this.title = "修改游戏地图";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.tag = this.form.tag.join(",");
          if (this.form.id != null) {
            updateMap(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMap(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除游戏地图编号为"' + ids + '"的数据项？').then(function () {
        return delMap(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('game/map/export', {
        ...this.queryParams
      }, `map_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
