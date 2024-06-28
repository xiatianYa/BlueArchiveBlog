<template>
  <div class="photo">
    <div class="left animate__animated animate__fadeInUp">
      <div class="photo-title">
        我的相册
      </div>
      <div class="photo-menu">
        <span class="pointer" @click="handelPhotoAdd">添加相册</span>
        <span class="pointer" @click="handelPhoneDelete">删除相册</span>
      </div>
      <div class="photo-list">
        <div class="item pointer" v-for="photo in photoList">
          <input type="checkbox" v-model="deletePhotoList" name="article" :value="photo.id">
          <span style="flex: 1;">
            <n-ellipsis :line-clamp="1" style="max-width: 55px;">
              {{ photo.photoName }}
            </n-ellipsis>
          </span>
          <span>{{ photo.status === 0 ? '审核中' : photo.status === 1 ? '审核通过' : '审核未通过' }}</span>
          <span class="pointer" @click="handelPhotoUpdate(photo.id)">修改相册</span>
        </div>
      </div>
    </div>
    <div class="right animate__animated animate__fadeInRight">
      <div class="photo-list">
        <PhotoDetail :photo="photo" v-for="photo in photoList" :key="photo.id"></PhotoDetail>
      </div>
      <reuse-Pagination :page="queryParam.pageNum" :count="queryParam.count" :pageSize="queryParam.pageSize"
        :pageSizes="queryParam.pageSizes" @size-change="onSizeChange"
        @current-change="onCurrentChange"></reuse-Pagination>
    </div>
    <!-- 添加修改框 -->
    <n-modal v-model:show="photoShow" transform-origin="center">
      <resuse-form ref="formRef" class="formClass" :formData="photo" :formOption="formOption"
        :formItemOption="selectOption" :rules="rules" labelPosition="right" labelWidth="140">
        <template #ImgUpload>
          <ImgUpload v-model="photo.photoUrl"></ImgUpload>
        </template>
        <template #Footer>
          <n-space>
            <n-button secondary round @click="photoShow = false">
              取消
            </n-button>
            <n-button type="info" secondary round @click="photoSubmit">
              提交
            </n-button>
          </n-space>
        </template>
      </resuse-form>
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
import { onMounted, ref, computed, reactive } from 'vue'
import { addPhoto, delPhoto, getPhoto, listPhotoByUser, updatePhoto } from '@/api/photo'
import { listSort } from '@/api/sort/photoSort'
import { useMessage, NModal, NCard, NButton, NSpace, NInput, NForm, NFormItem, NSelect, NEllipsis, type FormInst } from 'naive-ui'
import ImgUpload from '@/components/imgUpload/index.vue'
import reusePagination from '@/components/reusePagination/index.vue'
import PhotoDetail from '@/components/PhotoDetail.vue'
import resuseForm from '@/components/reuseForm/index.vue'
//表单
const formRef = ref<any | null>(null)
// 定义Photo的类型  
interface PhotoType {
  id?: number | null;
  sortId: number | null;
  photoName: string;
  photoUrl: string;
}
//下拉框配置项
const selectOption: any = reactive({
  sortOptions: [] //相册分类列表
})
//表单配置项
const formOption = reactive([
  {
    type: "input", props: "photoName", label: "相片名称", placeholder: "请输入相片名称"
  },
  {
    type: "select", selectProps: "sortOptions", props: "sortId", label: "相册分类", placeholder: "请选择分类名称"
  },
  {
    type: "slot", slotName: "ImgUpload", props: "photoUrl", label: "相册图片"
  },
  {
    type: "slot", slotName: "Footer"
  }
]);
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
//分页对象
const queryParam = ref({
  pageNum: 1,
  pageSize: 8,
  count: 0,
  pageSizes: [8, 16, 24, 32]
})
//删除相册的名称列表
const mappedPhotoNamesByIds = computed(() => {
  return deletePhotoList.value.map((id: number) => {
    const photo: any = photoList.value.find((photo: any) => photo.id === id);
    return photo.photoName;
  })
})
onMounted(() => {
  init();
})
//初始化数据
function init() {
  let { pageNum, pageSize } = queryParam.value
  const query = { pageNum, pageSize }
  listPhotoByUser(query).then((res: any) => {
    photoList.value = res.rows
    //计算总页数
    queryParam.value.count = Math.ceil(res.total / pageSize)
  })
  listSort().then((res: any) => {
    selectOption.sortOptions = res.rows.map((item: any) => {
      return {
        value: item.id,
        label: item.sortName
      }
    });
  })
}
// 切换个数
const onSizeChange = (size: number) => {
  queryParam.value.pageSize = size;
  init();
};
// 切换页数
const onCurrentChange = (page: number) => {
  queryParam.value.pageNum = page;
  init();
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
  formRef.value?.ruleFormRef().validate((errors: any) => {
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
    border-radius: 10px;
    width: 15%;
    height: 100%;
    margin: 20px;
    border: 1px solid #c8d9eb;

    .photo-title {
      display: flex;
      justify-content: center;
      font-size: 14px;
      margin: 10px;
    }

    .photo-menu {
      display: flex;
      justify-content: space-between;
      font-size: 12px;
      padding: 10px;
      border-top: 1px solid #c8d9eb;

      span:hover {
        color: #00e0ff;
      }
    }

    .photo-list {
      display: flex;
      flex-wrap: wrap;
      flex-direction: column;

      .item {
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

    .photo-list {
      display: flex;
      justify-content: space-between;
      flex-wrap: wrap;
      width: 100%;
      height: 90%;
      min-height: 400px;
      margin-top: 20px;
    }
  }
}
</style>
