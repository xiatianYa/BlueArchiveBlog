<template>
  <div class="photo">
    <div class="left animate__animated animate__fadeInUp">
      <div class="photo_title">
        我的相册
      </div>
      <div class="photo_menu">
        <span class="pointer" @click="openPhoto">添加相册</span>
        <span class="pointer" @click="openPhoneDelete">删除相册</span>
      </div>
      <div class="photo_list">
        <div class="item pointer" v-for="photo in photoList">
          <input type="checkbox" v-model="deletePhotoList" name="article" :value="photo.id">
          <span>{{ photo.photoName }}</span>
          <span>{{ photo.status === 0 ? '审核中' : photo.status === 1 ? '审核通过' : '审核为通过' }}</span>
          <span class="pointer" @click="openPhotoUpdate(photo.id)">修改相册</span>
        </div>
      </div>
    </div>
    <div class="right">
      <div class="photo_list">
        <div class="photo box_shadow pointer animate__animated animate__zoomIn" v-for="photo in photoList"
          :key="photo.id">
          <div class="photo_img">
            <img v-lazy="photo.photoUrl" class="box_shadow">
          </div>
          <div class="photo_msg">
            <span>
              {{ photo.photoName }}
            </span>
          </div>
          <div class="photo_time">
            <svg class="icon pointer" aria-hidden="true">
              <use xlink:href="#icon-shalou"></use>
            </svg>
            <span>
              {{ photo.createTime }}
            </span>
          </div>
          <div class="photo_name">
            <span>
              发布者: {{ photo.userName }}
            </span>
          </div>
        </div>
      </div>
    </div>
    <!-- 添加修改框 -->
    <div class="photo_dialog" v-if="photoShow">
      <div class="dialog">
        <div class="photo_title">
          <span v-show="!photo.id">添加相册</span>
          <span v-show="photo.id">修改相册</span>
          <svg class="icon pointer" aria-hidden="true" @click="closePhoto">
            <use xlink:href="#icon-guanbi"></use>
          </svg>
        </div>
        <div class="photo_name">
          <span>相册名称</span>
          <input type="text" v-model="photo.photoName" placeholder="请输入相册名称">
        </div>
        <div class="photo_sort">
          <span>相册分类</span>
          <select id="sort" name="sort" v-model="photo.sortId">
            <option :value="sort.id" v-for="sort in SortList">{{ sort.sortName }}</option>
          </select>
        </div>
        <div class="photo_avatar">
          <span>上传封面</span>
          <input type="file" @change="handleImageUpload" accept="image/*">
        </div>
        <div class="button_box">
          <button v-show="!photo.id" @click="addPhotoSubmit">添加</button>
          <button v-show="photo.id" @click="updatePhotoSubmit">修改</button>
        </div>
      </div>
    </div>
    <!-- 删除框 -->
    <div class="photo_delete_dialog" v-show="photoDeleteShow">
      <div class="dialog">
        <div class="photo_title">
          <span>删除相册</span>
          <svg class="icon pointer" aria-hidden="true" @click="closePhotoDelete">
            <use xlink:href="#icon-guanbi"></use>
          </svg>
        </div>
        <div class="photo_body">
          删除文章ID为: {{ deletePhotoList }} 的数据
        </div>
        <div class="button_box">
          <button @click="deletePhotoSubmit">确认删除</button>
          <button @click="closePhotoDelete">取消删除</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {onMounted, ref} from 'vue'
import {addPhoto, delPhoto, getPhoto, listPhotoByUser, updatePhoto} from '@/api/photo'
import {listSort} from '@/api/sort/photoSort'
import {uploadImages} from "@/api/file";
import promptMsg from "@/components/PromptBoxView"
//相册列表
const photoList = ref()
//相册选中列表
const deletePhotoList = ref([])
//删除框显示
const photoDeleteShow = ref(false)
//添加修改框显示
const photoShow = ref(false)
//相册信息
const photo = ref({})
//相册分类
const SortList = ref({})
onMounted(() => {
  init();
})
//初始化数据
function init() {
  listPhotoByUser().then(res => {
    photoList.value = res.data
  })
  listSort().then(res => {
    SortList.value = res.rows;
  })
}
//打开修改相册框
function openPhotoUpdate(photoId) {
  getPhoto(photoId).then(res => {
    photo.value = res.data
  })
  photoShow.value = true;
}
//修改相册
function updatePhotoSubmit() {
  if (!photo.value.photoName) {
    promptMsg({ type: "warn", msg: "请添加相册名称" })
    return;
  }
  if (!photo.value.sortId) {
    promptMsg({ type: "warn", msg: "请添加相册分类" })
    return;
  }
  if (!photo.value.photoUrl) {
    promptMsg({ type: "warn", msg: "请添加相册图片" })
    return;
  }
  updatePhoto(photo.value).then(() => {
    promptMsg({ type: "success", msg: "修改成功" })
    init();
  }).catch(() => {
    promptMsg({ type: "error", msg: "修改失败" })
  })
  closePhoto();
}
//添加相册
function addPhotoSubmit() {
  if (!photo.value.photoName) {
    promptMsg({ type: "warn", msg: "请添加相册名称" })
    return;
  }
  if (!photo.value.sortId) {
    promptMsg({ type: "warn", msg: "请添加相册分类" })
    return;
  }
  if (!photo.value.photoUrl) {
    promptMsg({ type: "warn", msg: "请添加相册图片" })
    return;
  }
  addPhoto(photo.value).then(() => {
    promptMsg({ type: "success", msg: "添加成功" })
    init();
  }).catch(() => {
    promptMsg({ type: "error", msg: "添加失败" })
  })
  closePhoto();
}
//删除相册显示
function openPhoneDelete() {
  photoDeleteShow.value = true;
}
//删除相册关闭
function closePhotoDelete() {
  deletePhotoList.value = [];
  photoDeleteShow.value = false;
}
//删除确认
function deletePhotoSubmit() {
  delPhoto(deletePhotoList.value).then(() => {
    promptMsg({ type: "success", msg: "删除成功" })
    init();
  }).catch(() => {
    promptMsg({ type: "error", msg: "删除失败" })
  })
  closePhotoDelete();
}
//打开修改添加框
function openPhoto() {
  photoShow.value = true;
}
//关闭修改添加框
function closePhoto() {
  photo.value = {}
  photoShow.value = false;
}
//上传图片
function handleImageUpload(event) {
  const formData = new FormData();
  formData.append("file", event.target.files[0])
  uploadImages(formData).then(res => {
    photo.value.photoUrl = res.data.url;
    promptMsg({ type: "success", msg: "上传图片成功!" })
  }).catch(error => {
    promptMsg({ type: "error", msg: "上传图片失败!" + error })
  })
}
</script>

<style lang="scss" scoped>
.photo {
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

    .photo_title {
      display: flex;
      justify-content: center;
      font-size: 14px;
      margin: 10px;
    }

    .photo_menu {
      display: flex;
      justify-content: space-between;
      font-size: 12px;
      padding: 10px;
      border-top: 1px solid #c8d9eb;

      span:hover {
        color: #00e0ff;
      }
    }

    .photo_list {
      display: flex;
      flex-direction: column;

      .item {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100%;
        height: 30px;
        padding: 10px 0;
        font-size: 12px;
        border-top: 1px solid #c8d9eb;

        input {
          float: left;
          margin: 0 10px;
        }

        span {
          display: flex;
          align-items: center;
          justify-content: center;
          flex-grow: 1;
        }

        span:first-of-type {
          display: -webkit-box;
          -webkit-line-clamp: 2;
          -webkit-box-orient: vertical;
          text-overflow: ellipsis;
          overflow: hidden;
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

    .photo_list {
      display: flex;
      flex-wrap: wrap;
      justify-content: space-between;
      width: 90%;
      height: 100%;
      min-height: 400px;
      margin-top: 20px;

      .photo {
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
        align-items: center;
        box-sizing: border-box;
        width: 20%;
        max-height: 300px;
        border-radius: 10px;
        padding-bottom: 0;

        .photo_img {
          display: flex;
          justify-content: center;
          width: 80%;
          min-height: 60%;
          max-height: 60%;
          overflow: hidden;
          border-radius: 10px;
          margin: 10px;

          img {
            width: 100%;
            object-fit: cover;
            transition: all .3s ease-in 0s;
            border-radius: 10px;
          }

          img:hover {
            object-fit: cover;
            transform: scale(1.1);
            transition: all 0.3s ease-in;
          }
        }

        .photo_msg {
          display: flex;
          width: 80%;
          margin: 5px 0;

          span {
            font-size: 12px;
          }
        }



        .photo_time {
          width: 80%;

          .icon {
            font-size: 14px;
          }

          span {
            font-size: 10px;
          }
        }

        .photo_name {
          display: flex;
          align-items: center;
          width: 80%;
          height: 10%;
          margin: 5px 0;
          font-size: 10px;
        }
      }
    }
  }

  .photo_dialog {
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

      .photo_title {
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

      .photo_sort {
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

        select {
          margin-left: 8px;
          font-size: 12px;
        }
      }

      .photo_name {
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

      .photo_avatar {
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

  .photo_delete_dialog {
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

      .photo_title {
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

      .photo_body {
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
