<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
            label-width="68px">
            <el-form-item label="用户id" prop="userId">
                <el-input v-model="queryParams.userId" placeholder="请输入用户id" clearable
                    @keyup.enter.native="handleQuery" />
            </el-form-item>
            <el-form-item label="分类名称" prop="sortId">
                <el-select v-model="queryParams.sortId" placeholder="请选择分类名称" clearable>
                    <el-option v-for="dict in photoDict" :key="dict.value" :label="dict.label" :value="dict.value" />
                </el-select>
            </el-form-item>
            <el-form-item label="审核状态" prop="status">
                <el-select v-model="queryParams.status" placeholder="请选择审核状态" clearable>
                    <el-option v-for="dict in dict.type.sys_shenhe" :key="dict.value" :label="dict.label"
                        :value="dict.value" />
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            </el-form-item>
        </el-form>

        <el-table v-loading="loading" :data="photoList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="id" align="center" prop="id" />
            <el-table-column label="用户名称" align="center" prop="userName" />
            <el-table-column label="分类" align="center" prop="sortId">
                <template slot-scope="scope">
                    {{ scope.row.sortName }}
                </template>
            </el-table-column>
            <el-table-column label="相册图片地址" align="center" prop="photoUrl" width="100">
                <template slot-scope="scope">
                    <image-preview :src="scope.row.photoUrl" :width="50" :height="50" />
                </template>
            </el-table-column>
            <el-table-column label="图片名称" align="center" prop="photoName" />
            <el-table-column label="审核状态" align="center" prop="status">
                <template slot-scope="scope">
                    <dict-tag :options="dict.type.sys_shenhe" :value="scope.row.status" />
                </template>
            </el-table-column>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <div style="display: flex;">
                        <el-button type="success" round @click="success(scope.row)">通过</el-button>
                        <el-button type="danger" round @click="danger(scope.row)">驳回</el-button>
                    </div>
                </template>
            </el-table-column>
        </el-table>

        <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize" @pagination="getList" />

        <!-- 添加或修改相册对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                <el-form-item label="分类名称" prop="sortId">
                    <el-select v-model="form.sortId" placeholder="请选择分类名称">
                        <el-option v-for="dict in photoDict" :key="dict.value" :label="dict.label"
                            :value="parseInt(dict.value)"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="相册图片地址" prop="photoUrl">
                    <image-upload v-model="form.photoUrl" />
                </el-form-item>
                <el-form-item label="图片名称" prop="photoName">
                    <el-input v-model="form.photoName" placeholder="请输入图片名称" />
                </el-form-item>
                <el-form-item label="审核状态" prop="status">
                    <el-select v-model="form.status" placeholder="请选择审核状态">
                        <el-option v-for="dict in dict.type.sys_shenhe" :key="dict.value" :label="dict.label"
                            :value="parseInt(dict.value)"></el-option>
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
import {addPhoto, delPhoto, getPhoto, listPhoto, updatePhoto} from "@/api/blog/photo";
import {listSort} from '@/api/sort/photoSort'
import store from '@/store'

export default {
    name: "Photo",
    dicts: ['sys_shenhe'],
    data() {
        return {
            //相册分类Dict
            photoDict: [],
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
            // 相册表格数据
            photoList: [],
            // 弹出层标题
            title: "",
            // 是否显示弹出层
            open: false,
            // 查询参数
            queryParams: {
                pageNum: 1,
                pageSize: 10,
                userId: null,
                sortId: null,
                photoUrl: null,
                photoName: null,
                status: null,
            },
            // 表单参数
            form: {},
            // 表单校验
            rules: {
                userId: [
                    { required: true, message: "用户id不能为空", trigger: "blur" }
                ],
                sortId: [
                    { required: true, message: "分类id不能为空", trigger: "change" }
                ],
                photoUrl: [
                    { required: true, message: "相册图片地址不能为空", trigger: "blur" }
                ],
                photoName: [
                    { required: true, message: "图片名称不能为空", trigger: "blur" }
                ],
                status: [
                    { required: true, message: "审核状态不能为空", trigger: "change" }
                ],
            }
        };
    },
    created() {
        //获取相册分类列表
        listSort().then(res => {
            for (const item of res.rows) {
                this.photoDict.push({ value: item.id, label: item.sortName })
            }
        })
        this.getList();
    },
    methods: {
        /** 通过审核 */
        success(photo) {
            photo.status = "2";
            updatePhoto(photo).then(res => {
                this.$modal.msgSuccess("修改成功");
                this.getList();
            }).catch(error => {
                this.$modal.msgSuccess("修改失败");
            })
        },
        /** 不通过审核 */
        danger(photo) {
            photo.status = "3";
            updatePhoto(photo).then(res => {
                this.$modal.msgSuccess("修改成功");
                this.getList();
            }).catch(error => {
                this.$modal.msgSuccess("修改失败");
            })
        },
        /** 查询相册列表 */
        getList() {
            this.loading = true;
            //默认查询未审核
            if (!this.queryParams.status) {
                this.queryParams.status = "1"
            }
            listPhoto(this.queryParams).then(response => {
                this.photoList = response.rows;
                //循环查找出分类
                for (let index = 0; index < this.photoList.length; index++) {
                    let item = this.photoDict.find(n => n.value === this.photoList[index].sortId)
                    if (!item) {
                        item.label = "未知分类";
                    }
                    this.photoList[index].sortName = item.label
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
                userId: null,
                sortId: null,
                photoUrl: null,
                photoName: null,
                status: null,
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
            this.title = "添加相册";
        },
        /** 修改按钮操作 */
        handleUpdate(row) {
            this.reset();
            const id = row.id || this.ids
            getPhoto(id).then(response => {
                this.form = response.data;
                this.open = true;
                this.title = "修改相册";
            });
        },
        /** 提交按钮 */
        submitForm() {
            this.$refs["form"].validate(valid => {
                if (valid) {
                    if (this.form.id != null) {
                        updatePhoto(this.form).then(response => {
                            this.$modal.msgSuccess("修改成功");
                            this.open = false;
                            this.getList();
                        });
                    } else {
                        this.form.userId = store.getters.userId
                        this.createBy = store.getters.userId
                        addPhoto(this.form).then(response => {
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
            this.$modal.confirm('是否确认删除相册编号为"' + ids + '"的数据项？').then(function () {
                return delPhoto(ids);
            }).then(() => {
                this.getList();
                this.$modal.msgSuccess("删除成功");
            }).catch(() => { });
        },
        /** 导出按钮操作 */
        handleExport() {
            this.download('blog/photo/export', {
                ...this.queryParams
            }, `photo_${new Date().getTime()}.xlsx`)
        }
    }
};
</script>
