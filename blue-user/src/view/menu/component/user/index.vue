<template>
    <div class="user">
        <div class="user-left animate__animated animate__fadeInDown">
            <div class="user-title">
                <span>个人信息</span>
            </div>
            <div class="user-avatar">
                <img :src="user.avatar">
            </div>
            <div class="user-info">
                <div class="user-name">
                    <div class="left">
                        <svg class="icon pointer" aria-hidden="true">
                            <use xlink:href="#icon-user"></use>
                        </svg>
                        <span>
                            姓名
                        </span>
                    </div>
                    <div class="right">
                        <span>{{ user.name }}</span>
                    </div>
                </div>
                <div class="user-phone">
                    <div class="left">
                        <svg class="icon pointer" aria-hidden="true">
                            <use xlink:href="#icon-mobile"></use>
                        </svg>
                        <span>
                            手机号码
                        </span>
                    </div>
                    <div class="right">
                        <span>{{ user.phone }}</span>
                    </div>
                </div>
                <div class="user-email">
                    <div class="left">
                        <svg class="icon pointer" aria-hidden="true">
                            <use xlink:href="#icon-mail"></use>
                        </svg>
                        <span>
                            邮箱
                        </span>
                    </div>
                    <div class="right">
                        <span>{{ user.email }}</span>
                    </div>
                </div>
                <div class="user-create">
                    <div class="left">
                        <svg class="icon pointer" aria-hidden="true">
                            <use xlink:href="#icon-calendar-check"></use>
                        </svg>
                        <span>
                            创建日期
                        </span>
                    </div>
                    <div class="right">
                        <span>{{ user.createTime }}</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="user-right animate__animated animate__fadeInRight">
            <div class="user-title">基本信息</div>
            <div class="userInfo">
                <div class="user-name">
                    <span>姓名</span>
                    <input type="text" v-model="user.name">
                </div>
                <div class="user-phone">
                    <span>手机</span>
                    <input type="text" v-model="user.phone">
                </div>
                <div class="user-email">
                    <span>邮箱</span>
                    <input type="text" v-model="user.email">
                </div>
                <div class="button-box">
                    <button @click="saveUserInfo()">保存</button>
                </div>
            </div>
            <div class="userPwd">
                <div class="user-pwd">
                    <span>旧密码</span>
                    <input type="password" v-model="userPwd.oldPwd">
                </div>
                <div class="user-pwd">
                    <span>新密码</span>
                    <input type="password" v-model="userPwd.newPwd">
                </div>
                <div class="user-pwd">
                    <span>确认密码</span>
                    <input type="password" v-model="userPwd.entryPwd">
                </div>
                <div class="button-box">
                    <button @click="submitPwd()">确认修改</button>
                </div>
            </div>
        </div>
        <div class="active-graph animate__animated animate__fadeInUp">
            <calendar-heatmap :round="3" :max="11" tooltip-unit="commit" :end-date="Date()" :values="userHeatMapData"
                :range-color="['#ebedf0',
                    'rgba(255,202,43,0.4)',
                    'rgba(255,202,43,0.6)',
                    'rgba(255,202,43,0.8)',
                    'rgba(255,202,43,1.0)',]" />
        </div>
    </div>
</template>

<script setup lang="ts">
interface userPwdType {
    oldPwd: string;
    newPwd: string;
    entryPwd: string;
}
interface userType {
    id: null | number,
    name: string;
    phone: string | number;
    email: string;
    avatar: string;
    createTime: string;
}
import { onMounted, ref } from 'vue'
import { updateUserProfile, updateUserPwd } from '@/api/user'
import { selectUserHeatMapData } from '@/api/charts'
import { useMessage } from 'naive-ui'
let { userStore } = useStore()
import useStore from "@/store"
//提示框
const message = useMessage()

const user = ref<userType>({
    id: null,
    name: "",
    phone: "",
    email: "",
    avatar: "",
    createTime: ""
})

const userPwd = ref<userPwdType>({
    oldPwd: "",
    newPwd: "",
    entryPwd: ""
})
const userHeatMapData = ref([])
onMounted(() => {
    setTimeout(() => {
        let { id, nickName, phone, email, avatar, createTime } = userStore
        user.value = {
            id,
            name: nickName,
            phone,
            email,
            avatar,
            createTime
        }
    }, 500)
    //获取用户热力图信息
    selectUserHeatMapData().then(res => {
        userHeatMapData.value = res.data
    })
})
//保存用户信息
function saveUserInfo() {
    updateUserProfile(user.value).then(() => {
        message.success("修改用户信息成功")
    }).catch((error) => {
        message.error("修改用户信息失败")
    })
}
//修改账号密码
function submitPwd() {
    if (!userPwd.value.oldPwd) {
        message.warning("请输入旧密码")
        return;
    }
    if (!userPwd.value.newPwd) {
        message.warning("请输入新密码")
        return;
    }
    if (!userPwd.value.entryPwd) {
        message.warning("请输入确认密码")
        return;
    }
    if (userPwd.value.newPwd !== userPwd.value.entryPwd) {
        message.warning("两次密码不一致")
        return;
    }
    updateUserPwd(userPwd.value.oldPwd, userPwd.value.newPwd).then(() => {
        message.success("修改密码成功")
        userPwd.value = {
            oldPwd: "",
            newPwd: "",
            entryPwd: ""
        }
    }).catch((error) => {
        message.error(error)
    })
}
</script>

<style lang="scss" scoped>
.user {
    display: flex;
    flex-wrap: wrap;
    width: 100%;
    height: 100%;
    min-height: 100vh;

    .user-left {
        width: 20%;
        margin: 20px;
        border: 1px solid #c8d9eb;

        .user-title {
            width: 100%;
            height: 10%;
            padding: 6px;
            font-size: 14px;
            display: flex;
            justify-content: center;
            align-items: center;
            border-bottom: 1px solid #c8d9eb;
        }

        .user-avatar {
            width: 100%;
            height: 30%;
            margin: 10px;
            display: flex;
            justify-content: center;
            align-items: center;

            img {
                width: 100px;
                height: 100px;
                border-radius: 50%;
                object-fit: cover;
            }
        }

        .user-info {
            width: 100%;
            padding-top: 10px;
            padding-bottom: 30px;
            display: flex;
            flex-wrap: wrap;
            justify-content: center;

            .user-name {
                width: 80%;
                display: flex;
                padding: 10px;
                justify-content: space-between;
                border-top: 1px solid #c8d9eb;

                span {
                    font-size: 12px;
                }
            }

            .user-phone {
                width: 80%;
                display: flex;
                padding: 10px;
                justify-content: space-between;
                border-top: 1px solid #c8d9eb;

                span {
                    font-size: 12px;
                }

                .right {
                    span {
                        font-size: 12px;
                    }
                }
            }

            .user-email {
                width: 80%;
                display: flex;
                padding: 10px;
                justify-content: space-between;
                border-top: 1px solid #c8d9eb;

                span {
                    font-size: 12px;
                }

                .right {
                    span {
                        font-size: 12px;
                    }
                }
            }

            .user-create {
                width: 80%;
                display: flex;
                padding: 10px;
                justify-content: space-between;
                border-top: 1px solid #c8d9eb;
                border-bottom: 1px solid #c8d9eb;

                span {
                    font-size: 12px;
                }

                .right {
                    span {
                        font-size: 12px;
                    }
                }
            }
        }
    }

    .user-right {
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        flex-grow: 1;
        margin: 20px;
        border: 1px solid #c8d9eb;

        .user-title {
            width: 100%;
            height: 10%;
            padding: 6px;
            font-size: 14px;
            display: flex;
            justify-content: center;
            align-items: center;
            border-bottom: 1px solid #c8d9eb;
        }

        .userInfo {
            display: flex;
            flex-direction: column;
            width: 25%;
            height: 90%;
            padding: 20px;
            border-right: 1px solid #c8d9eb;

            .button-box {
                margin: 0;

                button {
                    width: 50px;
                    padding: 3px;
                    border-radius: 5px;
                    border: none;
                    font-size: 14px;
                    cursor: pointer;
                    background-color: #74f9ff;
                }

                button:hover {
                    background-color: #00e0ff;
                }
            }

            .user-name {
                margin: 10px 0;
                font-size: 14px;

                input {
                    width: 70%;
                    font-size: 12px;
                    margin-left: 8px;
                    padding: 3px 5px;
                    border-radius: 3px;
                    border: 1px solid #c8d9eb;
                    transition: all 0.5s ease;
                    /* 添加过渡效果，使边框颜色变化更平滑 */
                }

                input:hover {
                    border-color: #a7b4c3;
                }

                input:focus {
                    border-color: #a1eafb;
                    /* 鼠标悬停或获得焦点时的边框颜色 */
                }
            }

            .user-phone {
                margin: 10px 0;
                font-size: 14px;

                input {
                    width: 70%;
                    font-size: 12px;
                    margin-left: 8px;
                    padding: 3px 5px;
                    border-radius: 3px;
                    border: 1px solid #c8d9eb;
                    transition: all 0.5s ease;
                    /* 添加过渡效果，使边框颜色变化更平滑 */
                }

                input:hover {
                    border-color: #a7b4c3;
                }

                input:focus {
                    border-color: #a1eafb;
                    /* 鼠标悬停或获得焦点时的边框颜色 */
                }
            }

            .user-email {
                margin: 10px 0;
                font-size: 14px;

                input {
                    width: 70%;
                    font-size: 12px;
                    margin-left: 8px;
                    padding: 3px 5px;
                    border-radius: 3px;
                    border: 1px solid #c8d9eb;
                    transition: all 0.5s ease;
                    /* 添加过渡效果，使边框颜色变化更平滑 */
                }

                input:hover {
                    border-color: #a7b4c3;
                }

                input:focus {
                    border-color: #a1eafb;
                    /* 鼠标悬停或获得焦点时的边框颜色 */
                }
            }
        }

        .userPwd {
            display: flex;
            flex-direction: column;
            width: 25%;
            height: 90%;
            padding: 20px;
            border-right: 1px solid #c8d9eb;

            .user-pwd {
                margin: 10px 0;
                font-size: 14px;

                input {
                    width: 50%;
                    float: right;
                    font-size: 12px;
                    margin-left: 8px;
                    padding: 3px 5px;
                    border-radius: 3px;
                    border: 1px solid #c8d9eb;
                    transition: all 0.5s ease;
                    /* 添加过渡效果，使边框颜色变化更平滑 */
                }

                input:hover {
                    border-color: #a7b4c3;
                }

                input:focus {
                    border-color: #a1eafb;
                    /* 鼠标悬停或获得焦点时的边框颜色 */
                }
            }

            button {
                width: 70px;
                padding: 3px;
                border-radius: 5px;
                border: none;
                font-size: 14px;
                cursor: pointer;
                background-color: #74f9ff;
            }

            button:hover {
                background-color: #00e0ff;
            }
        }
    }

    .active-graph {
        width: 100%;
        height: 50%;
        font-size: 8px;
        padding: 30px 100px 30px 100px;
        margin: 20px;
        border: 1px solid #c8d9eb;
        overflow: hidden;
    }
}
</style>
