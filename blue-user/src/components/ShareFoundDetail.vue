<template>
  <div class="found">
    <div class="left animate__animated animate__fadeInUp">
      <div class="found_title">
        我的文章
      </div>
      <div class="found_menu">
        <span class="pointer" @click="openErchuang">添加二创</span>
        <span class="pointer" @click="openErchuangDelete">删除二创</span>
      </div>
      <div class="found_list">
        <div class="item pointer" v-for="erchuang in ErchuangList">
          <input type="checkbox" v-model="deleteErchuangList" name="article" :value="erchuang.id">
          <span>{{ erchuang.ecName }}</span>
          <span>{{ erchuang.status === 0 ? '审核中' : erchuang.status === 1 ? '审核通过' : '审核为通过' }}</span>
          <span class="pointer" @click="openUpdateErchuang(erchuang.id)">修改二创</span>
        </div>
      </div>
    </div>
    <div class="right">
      <div class="found_list">
        <ErchuangDetail :erchuang="erchuang" v-for="erchuang in ErchuangList"></ErchuangDetail>
      </div>
    </div>
    <!-- 添加修改框 -->
    <div class="found_dialog" v-if="erchuangShow">
      <div class="dialog">
        <div class="found_title">
          <span v-show="!erchuang.id">添加二创</span>
          <span v-show="erchuang.id">修改二创</span>
          <svg class="icon pointer" aria-hidden="true" @click="closeErchuang">
            <use xlink:href="#icon-guanbi"></use>
          </svg>
        </div>
        <div class="found_name">
          <span>二创标题</span>
          <input type="text" v-model="erchuang.ecName" placeholder="请输入二创标题">
        </div>
        <div class="found_name">
          <span>二创作者</span>
          <input type="text" v-model="erchuang.ecAuthor" placeholder="请输入二创作者">
        </div>
        <div class="found_name">
          <span>二创路径</span>
          <input type="text" v-model="erchuang.ecUrl" placeholder="请输入二创路径">
        </div>
        <div class="found_name">
          <span>二创简介</span>
          <input type="text" v-model="erchuang.ecSynopsis" placeholder="请输入二创简介">
        </div>
        <div class="found_avatar">
          <span>上传封面</span>
          <input type="file" @change="handleImageUpload" accept="image/*">
        </div>
        <div class="button_box">
          <button @click="addErchuangSubmit" v-show="!erchuang.id">添加</button>
          <button @click="updateErchuangSubmit" v-show="erchuang.id">修改</button>
        </div>
      </div>
    </div>
    <!-- 删除框 -->
    <div class="found_delete_dialog" v-show="erchuangDeleteShow">
      <div class="dialog">
        <div class="found_title">
          <span>删除二创</span>
          <svg class="icon pointer" aria-hidden="true" @click="closeErchuangDelete">
            <use xlink:href="#icon-guanbi"></use>
          </svg>
        </div>
        <div class="found_body">
          删除文章ID为: {{ deleteErchuangList }} 的数据
        </div>
        <div class="button_box">
          <button @click="deleteErchuangSubmit">确认删除</button>
          <button @click="closeErchuangDelete">取消删除</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { addErchuang, delErchuang, getErchuang, listErchuangByUser, updateErchuang } from '@/api/erchuang'
import ErchuangDetail from "@/components/ErchuangDetail.vue"
import { uploadImages } from "@/api/file";
import { useMessage } from 'naive-ui'
//提示框
const message = useMessage()
//二创列表
const ErchuangList = ref()
//选中列表
const deleteErchuangList = ref([])
//删除框是否显示
const erchuangDeleteShow = ref(false)
//二创对象
const erchuang = ref({})
//二创添加删除是否显示
const erchuangShow = ref(false)
onMounted(() => {
  init();
})
//初始化数据
function init() {
  listErchuangByUser().then(res => {
    ErchuangList.value = res.data;
  })
}
//修改二创
function updateErchuangSubmit() {
  if (!erchuang.value.ecName) {
    message.warning("请添加二创标题")
    return;
  }
  if (!erchuang.value.ecAuthor) {
    message.warning("请添加二创作者")
    return;
  }
  if (!erchuang.value.ecAvater) {
    message.warning("请添加二创封面")
    return;
  }
  if (!erchuang.value.ecUrl) {
    message.warning("请选择二创路径")
    return;
  }
  if (!erchuang.value.ecSynopsis) {
    message.warning("请选择二创简介")
    return;
  }
  updateErchuang(erchuang.value).then(() => {
    message.warning("修改成功")
    init();
  }).catch(() => {
    message.error("修改失败")
  })
  closeErchuang();
}
//添加二创
function addErchuangSubmit() {
  if (!erchuang.value.ecName) {
    message.warning("请添加二创标题")
    return;
  }
  if (!erchuang.value.ecAuthor) {
    message.warning("请添加二创作者")
    return;
  }
  if (!erchuang.value.ecAvater) {
    message.warning("请添加二创封面")
    return;
  }
  if (!erchuang.value.ecUrl) {
    message.warning("请选择二创路径")
    return;
  }
  if (!erchuang.value.ecSynopsis) {
    message.warning("请选择二创简介")
    return;
  }
  addErchuang(erchuang.value).then(() => {
    message.success("添加成功")
    init();
  }).catch(() => {
    message.error("添加失败")
  })
  closeErchuang();
}
//开启修改框
function openUpdateErchuang(erchuangId) {
  getErchuang(erchuangId).then(res => {
    erchuang.value = res.data
  })
  erchuangShow.value = true;
}
//开启添加修改框
function openErchuang() {
  erchuangShow.value = true;
}
//关闭修改添加修改框
function closeErchuang() {
  erchuang.value = {}
  erchuangShow.value = false;
}
//开启删除框
function openErchuangDelete() {
  erchuangDeleteShow.value = true;
}
//关闭删除框
function closeErchuangDelete() {
  deleteErchuangList.value = [];
  erchuangDeleteShow.value = false;
}
//删除二创
function deleteErchuangSubmit() {
  delErchuang(deleteErchuangList.value).then(() => {
    message.success("删除成功")
    init();
  }).catch(() => {
    message.error("删除失败")
  })
  closeErchuangDelete();
}
//上传图片
function handleImageUpload(event) {
  const formData = new FormData();
  formData.append("file", event.target.files[0])
  uploadImages(formData).then(res => {
    erchuang.value.ecAvater = res.data.url;
    message.success("上传图片成功")
  }).catch(error => {
    message.error("上传图片失败")
  })
}
</script>

<style lang="scss" scoped>
.found {
  display: flex;
  width: 100%;
  margin-bottom: 40px;

  .left {
    box-sizing: border-box;
    border-radius: 10px;
    width: 15%;
    height: 100%;
    margin: 20px;
    border: 1px solid #c8d9eb;

    .found_title {
      display: flex;
      justify-content: center;
      font-size: 14px;
      margin: 10px;
    }

    .found_menu {
      display: flex;
      justify-content: space-between;
      font-size: 12px;
      padding: 10px;
      border-top: 1px solid #c8d9eb;

      span:hover {
        color: #00e0ff;
      }
    }

    .found_list {
      display: flex;
      flex-direction: column;

      .item {
        display: flex;
        justify-content: center;
        width: 100%;
        height: 30px;
        padding: 10px 0;
        font-size: 12px;
        border-top: 1px solid #c8d9eb;

        input {
          float: left;
          margin: 0 10px;
        }

        span:first-of-type {
          max-width: 50%;
          min-width: 50%;
          display: -webkit-box;
          -webkit-line-clamp: 2;
          -webkit-box-orient: vertical;
          text-overflow: ellipsis;
          overflow: hidden;
        }

        span {
          display: flex;
          align-items: center;
          justify-content: center;
          flex-grow: 1;
        }

        span:last-child {
          display: flex;
          justify-content: end;
          align-items: right;
          margin-right: 5px;
        }

        span:hover {
          color: #00e0ff;
        }
      }
    }
  }

  .right {
    width: 70%;
    min-height: 100vh;
    margin: 0 20px;

    .found_list {
      display: flex;
      flex-direction: row;
      flex-wrap: wrap;
      justify-content: space-between;
    }
  }

  .found_dialog {
    position: fixed;
    display: flex;
    justify-content: center;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    padding-top: 10%;
    background-color: rgba(rgb(0, 0, 0), 0.3);
    z-index: 999;

    .dialog {
      position: fixed;
      width: 420px;
      height: 350px;
      border-radius: 10px;
      background-color: #FFFFFF;

      .found_title {
        box-sizing: border-box;
        position: relative;
        display: flex;
        justify-content: left;
        width: 100%;
        margin-top: 10px;
        padding-left: 10px;

        .icon {
          position: absolute;
          top: 0;
          right: 20px;
          /* 初始状态 */
          transition: transform 0.5s ease-in-out;
          /* 定义过渡效果，持续时间为0.5秒，使用ease-in-out缓动函数 */
          transform: rotate(0deg);
          /* 初始旋转角度为0度 */
        }

        .icon:hover {
          /* 鼠标移入状态 */
          transform: rotate(240deg);
          /* 旋转角度为360度，即一圈 */
        }
      }

      .found_name {
        padding-left: 10px;
        margin-top: 20px;
        display: flex;
        justify-content: left;

        span {
          display: flex;
          justify-content: center;
          align-items: center;
          font-size: 12px;
        }

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

      .found_avatar {
        padding-left: 10px;
        margin-top: 20px;
        display: flex;
        justify-content: left;

        span {
          display: flex;
          justify-content: center;
          align-items: center;
          font-size: 12px;
        }

        input {
          margin-left: 8px;
        }
      }

      .button_box {
        margin: 10px 10px;

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

    }

  }

  .found_delete_dialog {
    position: fixed;
    display: flex;
    justify-content: center;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    padding-top: 10%;
    background-color: rgba(rgb(0, 0, 0), 0.3);

    .dialog {
      position: fixed;
      width: 420px;
      border-radius: 10px;
      background-color: #FFFFFF;

      .found_title {
        box-sizing: border-box;
        position: relative;
        display: flex;
        justify-content: left;
        width: 100%;
        margin-top: 10px;
        padding-left: 10px;

        .icon {
          position: absolute;
          top: 0;
          right: 20px;
          /* 初始状态 */
          transition: transform 0.5s ease-in-out;
          /* 定义过渡效果，持续时间为0.5秒，使用ease-in-out缓动函数 */
          transform: rotate(0deg);
          /* 初始旋转角度为0度 */
        }

        .icon:hover {
          /* 鼠标移入状态 */
          transform: rotate(240deg);
          /* 旋转角度为360度，即一圈 */
        }
      }

      .found_body {
        margin: 10px;
        font-size: 14px;
      }

      .button_box {
        display: flex;
        margin: 10px;

        button {
          padding: 3px;
          margin-right: 10px;
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
  }
}
</style>
