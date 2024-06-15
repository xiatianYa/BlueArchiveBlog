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
          <n-ellipsis style="flex: 1;" :line-clamp="1">
            {{ erchuang.ecName }}
          </n-ellipsis>
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
    <n-modal v-model:show="erchuangShow" transform-origin="center">
      <n-card style="width: 600px" title="添加文章" :bordered="false" size="huge" role="dialog" aria-modal="true">
        <n-form ref="formRef" :model="erchuang" :rules="rules" label-placement="left" label-width="auto"
          require-mark-placement="right-hanging" size="medium" :style="{
            maxWidth: '640px'
          }">
          <n-form-item label="二创标题" path="ecName">
            <n-input v-model:value="erchuang.ecName" placeholder="请输入二创标题" />
          </n-form-item>
          <n-form-item label="二创作者" path="ecAuthor">
            <n-input v-model:value="erchuang.ecAuthor" placeholder="请输入二创作者" />
          </n-form-item>
          <n-form-item label="二创路径" path="ecUrl">
            <n-input v-model:value="erchuang.ecUrl" placeholder="请输入二创路径" />
          </n-form-item>
          <n-form-item label="二创简介" path="ecSynopsis">
            <n-input v-model:value="erchuang.ecSynopsis" placeholder="请输入二创简介" />
          </n-form-item>
          <n-form-item label="二创封面" path="ecAvater">
            <ImgUpload @onSuccess="uploadImageOnSuccess"></ImgUpload>
          </n-form-item>
        </n-form>
        <template #footer>
          <n-space>
            <n-button secondary round @click="erchuangShow = false">
              取消
            </n-button>
            <n-button type="info" secondary round @click="addErchuangSubmit">
              提交
            </n-button>
          </n-space>
        </template>
      </n-card>
    </n-modal>
    <!-- 删除框 -->
    <n-modal v-model:show="erchuangDeleteShow" transform-origin="center">
      <n-card style="width: 600px" title="删除文章" :bordered="false" size="huge" role="dialog" aria-modal="true">
        <template #footer>
          <n-space style="padding-bottom:20px">
            删除数据ID为:{{ deleteErchuangList }}的数据
          </n-space>
          <n-space>
            <n-button secondary round @click="erchuangDeleteShow = false">
              取消
            </n-button>
            <n-button type="info" secondary round @click="deleteErchuangSubmit">
              提交
            </n-button>
          </n-space>
        </template>
      </n-card>
    </n-modal>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { addErchuang, delErchuang, getErchuang, listErchuangByUser, updateErchuang } from '@/api/erchuang'
import { useMessage, NModal, NCard, NButton, NSpace, NInput, NForm, NFormItem, NEllipsis } from 'naive-ui'
import ErchuangDetail from "@/components/ErchuangDetail.vue"
import ImgUpload from '@/components/ImgUpload/index.vue'

const rules = ref({
  ecName: {
    required: true,
    trigger: ['blur', '二创名称'],
    message: '请输入二创名称'
  },
  ecAuthor: {
    required: true,
    trigger: ['blur', '二创作者'],
    message: '请输入二创作者'
  },
  ecAvater: {
    required: true,
    trigger: ['blur', '二创封面'],
    message: '请输入二创封面'
  },
  ecUrl: {
    required: true,
    trigger: ['blur', '二创路径'],
    message: '请输入二创路径'
  },
  ecSynopsis: {
    required: true,
    trigger: ['blur', '二创简介'],
    message: '请输入二创简介'
  }
})
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
//添加二创
function addErchuangSubmit() {
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
//删除二创
function deleteErchuangSubmit() {
  delErchuang(deleteErchuangList.value).then(() => {
    message.success("删除成功")
    init();
  }).catch(() => {
    message.error("删除失败")
  })
}
//图片上传成功
function uploadImageOnSuccess(data: any) {

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
          padding: 5px;
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
}
</style>
