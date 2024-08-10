<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="服务器IP" prop="ip">
        <el-input v-model="queryParams.ip" placeholder="请输入服务器IP" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="服务器端口" prop="port">
        <el-input v-model="queryParams.port" placeholder="请输入服务器端口" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="社区ID" prop="communityId">
        <el-input v-model="queryParams.communityId" placeholder="请输入社区ID" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="模式ID" prop="modeId">
        <el-input v-model="queryParams.modeId" placeholder="请输入模式ID" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['game:server:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['game:server:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['game:server:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['game:server:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="serverList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="服务器IP" align="center" prop="ip" />
      <el-table-column label="服务器端口" align="center" prop="port" />
      <el-table-column label="社区ID" align="center" prop="communityId" />
      <el-table-column label="模式ID" align="center" prop="modeId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['game:server:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['game:server:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改游戏服务器对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="服务器IP" prop="ip">
          <el-input v-model="form.ip" placeholder="请输入服务器IP" />
        </el-form-item>
        <el-form-item label="服务器端口" prop="port">
          <el-input v-model="form.port" placeholder="请输入服务器端口" />
        </el-form-item>
        <el-form-item label="社区ID" prop="communityId">
          <el-input v-model="form.communityId" placeholder="请输入社区ID" />
        </el-form-item>
        <el-form-item label="模式ID" prop="modeId">
          <el-input v-model="form.modeId" placeholder="请输入模式ID" />
        </el-form-item>
        <el-form-item label="是否删除" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入是否删除(0:否,1:是)" />
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
import { getServer, listServer, addServer, updateServer, delServer } from "@/api/game/server.js";

export default {
  name: "Server",
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
      // 游戏服务器表格数据
      serverList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        ip: null,
        port: null,
        communityId: null,
        modeId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        ip: [
          { required: true, message: "服务器IP不能为空", trigger: "blur" }
        ],
        port: [
          { required: true, message: "服务器端口不能为空", trigger: "blur" }
        ],
        communityId: [
          { required: true, message: "社区ID不能为空", trigger: "blur" }
        ],
        modeId: [
          { required: true, message: "模式ID不能为空", trigger: "blur" }
        ],
        createBy: [
          { required: true, message: "创建用户ID不能为空", trigger: "blur" }
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
    /** 查询游戏服务器列表 */
    getList() {
      this.loading = true;
      listServer(this.queryParams).then(response => {
        this.serverList = response.rows;
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
        ip: null,
        port: null,
        communityId: null,
        modeId: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
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
      this.title = "添加游戏服务器";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getServer(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改游戏服务器";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateServer(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addServer(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除游戏服务器编号为"' + ids + '"的数据项？').then(function () {
        return delServer(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('game/server/export', {
        ...this.queryParams
      }, `server_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
