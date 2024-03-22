<template>
    <div class="website">
        <div class="contaner">
            <el-tabs v-model="activeName" type="card" @tab-click="handleClick" class="tabs">
                <el-tab-pane label="网站信息管理" name="website">
                    <el-form :label-position="labelPosition" label-width="80px" :model="websiteInfo">
                        <el-form-item label="网站名称">
                            <el-input v-model="websiteInfo.websiteName"></el-input>
                        </el-form-item>
                        <el-form-item label="头像">
                            <el-input v-model="websiteInfo.websiteCover"></el-input>
                        </el-form-item>
                        <el-form-item label="地址">
                            <el-input v-model="websiteInfo.websiteUrl"></el-input>
                        </el-form-item>
                        <el-form-item label="描述">
                            <el-input v-model="websiteInfo.websiteDetail"></el-input>
                        </el-form-item>
                        <el-form-item label="备注">
                            <el-input v-model="websiteInfo.websiteRemark"></el-input>
                        </el-form-item>
                    </el-form>
                    <el-button type="primary" round @click="clearWebsite">重置</el-button>
                    <el-button type="success" round @click="saveWebsite">提交</el-button>
                </el-tab-pane>
                <el-tab-pane label="公告管理" name="info">
                    <el-form :label-position="labelPosition" label-width="80px" :model="noticeInfo">
                        <el-form-item label="公告标题">
                            <el-input v-model="noticeInfo.noticeTitle"></el-input>
                        </el-form-item>
                        <el-form-item label="公告内容">
                            <el-input v-model="noticeInfo.noticeContent"></el-input>
                        </el-form-item>
                    </el-form>
                    <el-button type="primary" round @click="clearNotice">重置</el-button>
                    <el-button type="success" round @click="saveNotice">提交</el-button>
                </el-tab-pane>
                <el-tab-pane label="友链申请管理" name="friend">
                    <el-form :label-position="labelPosition" label-width="80px" :model="friendInfo">
                        <el-form-item label="申请地址">
                            <el-input v-model="friendInfo.friendUrl"></el-input>
                        </el-form-item>
                        <el-form-item label="内容">
                            <el-input v-model="friendInfo.friendContent"></el-input>
                        </el-form-item>
                        <el-form-item label="备注">
                            <el-input v-model="friendInfo.friendDetail"></el-input>
                        </el-form-item>
                    </el-form>
                    <el-button type="primary" round @click="clearFriendInfo">重置</el-button>
                    <el-button type="success" round @click="saveFiendInfo">提交</el-button>
                </el-tab-pane>
            </el-tabs>
        </div>
    </div>

</template>
<script>
import {addWebsite, listWebsite, updateWebsite,} from '@/api/blog/website'
import {addNotice, listNotice, updateNotice} from '@/api/blog/notice'
import {addInfo, listInfo, updateInfo} from '@/api/blog/info'

export default {
    data() {
        return {
            labelPosition: 'left',
            activeName: 'website',
            //网站信息
            websiteInfo: {
                websiteName: '',
                websiteCover: '',
                websiteUrl: '',
                websiteDetail: '',
                websiteRemark: '',
            },
            //公告信息
            noticeInfo: {
                noticeTitle: '',
                noticeContent: '',
            },
            //友链申请信息
            friendInfo: {
                friendUrl: '',
                friendContent: '',
                friendDetail: ''
            }
        };
    },
    created() {
        //初始化数据
        this.init()
    },
    methods: {
        clearWebsite() {
            this.websiteInfo = {
                id:this.websiteInfo.id || "",
                websiteName: '',
                websiteCover: '',
                websiteUrl: '',
                websiteDetail: '',
                websiteRemark: '',
            }
        },
        clearNotice() {
            this.noticeInfo = {
                id:this.noticeInfo.id || "",
                noticeTitle: '',
                noticeContent: '',
            }
        },
        clearFriendInfo() {
            this.friendInfo = {
                id:this.friendInfo.id || "",
                friendUrl: '',
                friendContent: '',
                friendDetail: ''
            }
        },
        saveFiendInfo() {
            //如果友链信息id是为空 则添加 负责则修改
            if (!this.noticeInfo.id) {
                addInfo(this.friendInfo).then(res => {
                    this.$message({
                        message: res.msg,
                        type: 'success'
                    });
                }).catch(error => {
                    this.$message({
                        message: error,
                        type: 'error'
                    });
                })
            } else {
                updateInfo(this.friendInfo).then(res => {
                    this.$message({
                        message: res.msg,
                        type: 'success'
                    });
                }).catch(error => {
                    this.$message({
                        message: error,
                        type: 'error'
                    });
                })
            }
        },
        saveNotice() {
            //如果公告信息id是为空 则添加 负责则修改
            if (!this.noticeInfo.id) {
                addNotice(this.noticeInfo).then(res => {
                    this.$message({
                        message: res.msg,
                        type: 'success'
                    });
                }).catch(error => {
                    this.$message({
                        message: error,
                        type: 'error'
                    });
                })
            } else {
                updateNotice(this.noticeInfo).then(res => {
                    this.$message({
                        message: res.msg,
                        type: 'success'
                    });
                }).catch(error => {
                    this.$message({
                        message: error,
                        type: 'error'
                    });
                })
            }
        },
        saveWebsite() {
            //如果网站信息id是为空 则添加 负责则修改
            if (!this.websiteInfo.id) {
                addWebsite(this.websiteInfo).then(res => {
                    this.$message({
                        message: res.msg,
                        type: 'success'
                    });
                }).catch(error => {
                    this.$message({
                        message: error,
                        type: 'error'
                    });
                })
            } else {
                updateWebsite(this.websiteInfo).then(res => {
                    this.$message({
                        message: res.msg,
                        type: 'success'
                    });
                }).catch(error => {
                    this.$message({
                        message: error,
                        type: 'error'
                    });
                })
            }
        },
        handleClick(tab, event) {
            //每次切换则程序加载数据
            this.init()
            console.log(tab, event);
        },
        init() {
            //获取网站信息
            listWebsite().then(res => {
                this.websiteInfo = res.rows[0]
            })
            //获取公告信息
            listNotice().then(res => {
                this.noticeInfo = res.rows[0]
            })
            //获取友链信息
            listInfo().then(res => {
                this.friendInfo = res.rows[0]
            })
        },
    }
};
</script>
<style lang="scss" scoped>
.website {
    width: 100%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;

    .contaner {
        width: 100%;
        margin: 50px;

        .tabs {
            display: flex;
            flex-wrap: wrap;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }
    }
}
</style>
