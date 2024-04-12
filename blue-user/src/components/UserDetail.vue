<template>
    <div class="user">
        <div class="user_left">
            <div class="user_title">
                <span>个人信息</span>
            </div>
            <div class="user_avatar">
                <img :src="user.avatar">
            </div>
            <div class="user_info">
                <div class="user_name">
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
                <div class="user_phone">
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
                <div class="user_email">
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
                <div class="user_create">
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
        <div class="user_right">
            <div class="user_title">基本信息</div>
            <div class="left">
                <div class="user_name">
                    <span>姓名</span>
                    <input type="text" :value="user.name">
                </div>
                <div class="user_phone">
                    <span>手机</span>
                    <input type="text" :value="user.phone">
                </div>
                <div class="user_email">
                    <span>邮箱</span>
                    <input type="text" :value="user.email">
                </div>
                <div class="button_box">
                    <button>保存</button>
                </div>
            </div>
            <div class="right">

            </div>
        </div>
        <div class="active_graph">
            <calendar-heatmap :round="3" :max="6"
                :values="[{ date: '2024-4-09', count: 3 }, { date: '2024-4-10', count: 5 }]" :end-date="Date()"
                :range-color="['#EBEDF0', '#9AE8A7', '#3DBC5F', '#30A14E', '#216E39']" :dark-mode />
        </div>
    </div>
</template>

<script setup>
const props = defineProps(['user'])
</script>

<style lang="scss" scoped>
.user {
    display: flex;
    flex-wrap: wrap;
    width: 100%;
    height: 100%;
    min-height: 100vh;

    .user_left {
        box-sizing: border-box;
        width: 20%;
        margin: 20px;
        border: 1px solid #c8d9eb;

        .user_title {
            box-sizing: border-box;
            width: 100%;
            height: 10%;
            padding: 6px;
            font-size: 14px;
            display: flex;
            justify-content: center;
            align-items: center;
            border-bottom: 1px solid #c8d9eb;
        }

        .user_avatar {
            box-sizing: border-box;
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

        .user_info {
            width: 100%;
            padding-top: 10px;
            padding-bottom: 30px;
            display: flex;
            flex-wrap: wrap;
            justify-content: center;

            .user_name {
                box-sizing: border-box;
                width: 80%;
                display: flex;
                padding: 10px;
                justify-content: space-between;
                border-top: 1px solid #c8d9eb;

                span {
                    font-size: 12px;
                }
            }

            .user_phone {
                box-sizing: border-box;
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

            .user_email {
                box-sizing: border-box;
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

            .user_create {
                box-sizing: border-box;
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

    .user_right {
        box-sizing: border-box;
        flex-grow: 1;
        margin: 20px;
        border: 1px solid #c8d9eb;

        .user_title {
            box-sizing: border-box;
            width: 100%;
            height: 10%;
            padding: 6px;
            font-size: 14px;
            display: flex;
            justify-content: center;
            align-items: center;
            border-bottom: 1px solid #c8d9eb;
        }

        .left {
            display: flex;
            flex-direction: column;
            width: 30%;
            padding: 20px;

            .button_box {
                margin: 10px 0;

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

            .user_name {
                margin: 10px 0;
                font-size: 14px;

                input {
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

            .user_phone {
                margin: 10px 0;
                font-size: 14px;

                input {
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

            .user_email {
                margin: 10px 0;
                font-size: 14px;

                input {
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
    }

    .active_graph {
        width: 100%;
        height: 50%;
        font-size: 8px;
        padding: 30px 100px 30px 100px;
        margin: 20px;
        box-sizing: border-box;
        border: 1px solid #c8d9eb;
        overflow: hidden;
    }
}
</style>
