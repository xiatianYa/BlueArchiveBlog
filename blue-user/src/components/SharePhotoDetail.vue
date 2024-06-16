<template>
  <div class="photo">
    <div class="left animate__animated animate__fadeInUp">
      <div class="photo_title">
        我的相册
      </div>
      <div class="photo_menu">
        <span class="pointer" @click="handelPhotoAdd">添加相册</span>
        <span class="pointer" @click="handelPhoneDelete">删除相册</span>
      </div>
      <div class="photo_list">
        <div class="item pointer" v-for="photo in photoList">
          <input type="checkbox" v-model="deletePhotoList" name="article" :value="photo.id">
          <span style="flex: 1;">
            <n-ellipsis :line-clamp="1">
              {{ photo.photoName }}
            </n-ellipsis>
          </span>
          <span>{{ photo.status === 0 ? '审核中' : photo.status === 1 ? '审核通过' : '审核为通过' }}</span>
          <span class="pointer" @click="handelPhotoUpdate(photo.id)">修改相册</span>
        </div>
      </div>
    </div>
    <div class="right animate__animated animate__fadeInRight">
      <div class="photo_list">
        <div class="photo box_shadow" v-for="photo in photoList" :key="photo.id">
          <div class="photo_img">
            <img :src="photo.photoUrl" class="box_shadow">
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
        </div>
      </div>
    </div>
    <!-- 添加修改框 -->
    <n-modal v-model:show="photoShow" transform-origin="center">
      <n-card style="width: 600px" :title="title" :bordered="false" size="huge" role="dialog" aria-modal="true">
        <n-form ref="formRef" :model="photo" :rules="rules" label-placement="left" label-width="auto"
          require-mark-placement="right-hanging" size="medium" :style="{
            maxWidth: '640px'
          }">
          <n-form-item label="相册名称" path="photoName">
            <n-input v-model:value="photo.photoName" placeholder="请输入相册名称" />
          </n-form-item>
          <n-form-item label="相册分类" path="sortId">
            <n-select v-model:value="photo.sortId" :options="photoOptions" placeholder="请选择相册分类" clearable />
          </n-form-item>
          <n-form-item label="相册图片" path="photoUrl">
            <ImgUpload v-model="photo.photoUrl"></ImgUpload>
          </n-form-item>
        </n-form>
        <template #footer>
          <n-space>
            <n-button secondary round @click="photoShow = false">
              取消
            </n-button>
            <n-button type="info" secondary round @click="photoSubmit">
              提交
            </n-button>
          </n-space>
        </template>
      </n-card>
    </n-modal>
    <!-- 删除框 -->
    <n-modal v-model:show="photoDeleteShow" transform-origin="center">
      <n-card style="width: 600px" :title="title" :bordered="false" size="huge" role="dialog" aria-modal="true">
        <template #footer>
          <n-space style="padding-bottom:20px">
            是否删除相册名称为:{{ mappedPhotoNamesByIds }}的数据
          </n-space>
          <n-space>
            <n-button secondary round @click="photoDeleteShow = false">
              取消
            </n-button>
            <n-button type="info" secondary round @click="deleteMusicSubmit">
              提交
            </n-button>
          </n-space>
        </template>
      </n-card>
    </n-modal>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref, computed } from 'vue'
import { addPhoto, delPhoto, getPhoto, listPhotoByUser, updatePhoto } from '@/api/photo'
import { listSort } from '@/api/sort/photoSort'
import { useMessage, NModal, NCard, NButton, NSpace, NInput, NForm, NFormItem, NSelect, NEllipsis, type FormInst } from 'naive-ui'
import ImgUpload from '@/components/ImgUpload/index.vue'
//表单
const formRef = ref<FormInst>()
// 定义Photo的类型  
interface PhotoType {
  id?: number | null;
  sortId: number | null;
  photoName: string;
  photoUrl: string;
}
const rules = ref({
  sortId: {
    required: true,
    trigger: ['blur', '相册分类'],
    message: '请选择分类名称',
    validator(rule: any, value: number) {
      return !value ? false : true;
    }
  },
  photoName: {
    required: true,
    trigger: ['blur', '相册名称'],
    message: '请输入相册名称'
  },
  photoUrl: {
    required: true,
    trigger: ['blur', '相册图片'],
    message: '请上传相册图片'
  },
})
//标题
const title = ref("")
//提示框
const message = useMessage()
//相册列表
const photoList = ref()
//相册选中列表
const deletePhotoList = ref([])
//删除框显示
const photoDeleteShow = ref(false)
//添加修改框显示
const photoShow = ref(false)
//相册信息
const photo = ref<PhotoType>({
  sortId: null,
  photoName: "",
  photoUrl: ""
})
//删除相册的名称列表
const mappedPhotoNamesByIds = computed(() => {
  return deletePhotoList.value.map((id: number) => {
    const photo: any = photoList.value.find((photo: any) => photo.id === id);
    return photo.photoName;
  })
})
//相册分类
const photoOptions = ref()
onMounted(() => {
  init();
})
//初始化数据
function init() {
  listPhotoByUser().then(res => {
    photoList.value = res.data
  })
  listSort().then((res:any) => {
    photoOptions.value = res.rows.map((item: any) => {
      return {
        value: item.id,
        label: item.sortName
      }
    });
  })
}
//打开添加修改相册
function handelPhotoAdd() {
  title.value = "新增相册"
  photo.value = {
    sortId: null,
    photoName: "",
    photoUrl: ""
  }
  photoShow.value = true;
}
//打开修改相册框
function handelPhotoUpdate(photoId: any) {
  title.value = "修改相册"
  getPhoto(photoId).then(res => {
    photo.value = res.data
  })
  photoShow.value = true;
}
//打开删除相册
function handelPhoneDelete() {
  title.value = "删除相册"
  if (deletePhotoList.value.length) {
    photoDeleteShow.value = true;
  } else {
    message.warning("请勾选需要删除的相册")
  }
}
//删除相册
function deleteMusicSubmit() {
  delPhoto(deletePhotoList.value).then(() => {
    photoDeleteShow.value = false;
    message.success("删除成功")
    init();
  }).catch((error) => {
    message.error(error)
  })
}
//提交相册
function photoSubmit() {
  formRef.value?.validate((errors) => {
    if (!errors) {
      if (photo.value.id != null) {
        updatePhoto(photo.value).then(() => {
          message.success("修改成功")
          photoShow.value = false;
          init();
        }).catch(() => {
          message.error("修改失败")
        })
      } else {
        addPhoto(photo.value).then(() => {
          message.success("添加成功")
          photoShow.value = false;
          init();
        }).catch(() => {
          message.error("新增失败")
        })
      }
    } else {
      message.warning('请检查参数')
    }
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
        box-sizing: border-box;
        display: flex;
        justify-content: start;
        align-items: center;
        width: 100%;
        height: 30px;
        padding: 5px;
        font-size: 12px;
        border-top: 1px solid #c8d9eb;

        span {
          display: flex;
          align-items: center;
          padding: 5px;
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
      width: 100%;
      height: 100%;
      min-height: 400px;
      margin-top: 20px;

      .photo {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        box-sizing: border-box;
        width: 20%;
        margin: 0 2%;
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
      }
    }
  }
}
</style>
