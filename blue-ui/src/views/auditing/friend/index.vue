<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="申请手机号" prop="friendPhone">
        <el-input v-model="queryParams.friendPhone" placeholder="请输入申请手机号" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="网站名称" prop="friendName">
        <el-input v-model="queryParams.friendName" placeholder="请输入网站名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="介绍" prop="friendIntroduce">
        <el-input v-model="queryParams.friendIntroduce" placeholder="请输入介绍" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="申请状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择申请状态" clearable>
          <el-option v-for="dict in dict.type.sys_apply_status" :key="dict.value" :label="dict.label"
            :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="发布时间" prop="pleaseTime">
        <el-date-picker clearable v-model="queryParams.pleaseTime" type="date" value-format="yyyy-MM-dd"
          placeholder="请选择发布时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="friendList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="申请手机号" align="center" prop="friendPhone" />
      <el-table-column label="网站名称" align="center" prop="friendName" />
      <el-table-column label="网站图片" align="center" prop="friendUrl" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.friendUrl" :width="50" :height="50" />
        </template>
      </el-table-column>
      <el-table-column label="介绍" align="center" prop="friendIntroduce" />
      <el-table-column label="申请状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_apply_status" :value="scope.row.status" />
        </template>
      </el-table-column>
      <el-table-column label="发布时间" align="center" prop="pleaseTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.pleaseTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <div style="display: flex;">
            <el-button type="success" plain @click="success(scope.row)">通过</el-button>
            <el-button type="danger" plain @click="danger(scope.row)">驳回</el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

  </div>
</template>

<script>
import { addFriend, delFriend, getFriend, listFriend, updateFriend } from "@/api/blog/friend";

export default {
  name: "Friend",
  dicts: ['sys_apply_status'],
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
      // 友情链接表格数据
      friendList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        friendPhone: null,
        friendName: null,
        friendUrl: null,
        friendIntroduce: null,
        status: null,
        pleaseTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        friendPhone: [
          { required: true, message: "申请手机号不能为空", trigger: "blur" }
        ],
        friendName: [
          { required: true, message: "网站名称不能为空", trigger: "blur" }
        ],
        friendUrl: [
          { required: true, message: "网站图片不能为空", trigger: "blur" }
        ],
        friendIntroduce: [
          { required: true, message: "介绍不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "申请状态不能为空", trigger: "change" }
        ],
        pleaseTime: [
          { required: true, message: "发布时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    //通过
    success(friendInfo) {
      friendInfo.status = 1
      updateFriend(friendInfo).then(res => {
        this.$modal.msgSuccess("修改成功");
      }).catch(error => {
        this.$modal.msgSuccess("修改失败");
      })
      this.getList()
    },
    //驳回
    danger(friendInfo) {
      friendInfo.status = 2
      updateFriend(friendInfo).then(res => {
        this.$modal.msgSuccess("修改成功");
      }).catch(error => {
        this.$modal.msgSuccess("修改失败");
      })
      this.getList()
    },
    /** 查询友情链接列表 */
    getList() {
      this.loading = true;
      //默认查询未审核
      if (!this.queryParams.status) {
        this.queryParams.status = "0"
      }
      listFriend(this.queryParams).then(response => {
        this.friendList = response.rows;
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
        friendPhone: null,
        friendName: null,
        friendUrl: null,
        friendIntroduce: null,
        status: null,
        pleaseTime: null,
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
      this.title = "添加友情链接";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getFriend(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改友情链接";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateFriend(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFriend(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除友情链接编号为"' + ids + '"的数据项？').then(function () {
        return delFriend(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/friend/export', {
        ...this.queryParams
      }, `friend_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
