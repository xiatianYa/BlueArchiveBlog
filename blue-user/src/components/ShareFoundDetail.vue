<template>
  <div class="found">
    <div class="left animate__animated animate__fadeInUp">
      <div class="found_title">
        我的二创
      </div>
      <div class="found_menu">
        <span class="pointer" @click="handleErchuangAdd">添加二创</span>
        <span class="pointer" @click="handleErchuangDelete">删除二创</span>
      </div>
      <div class="found_list">
        <div class="item pointer" v-for="erchuang in ErchuangList">
          <input type="checkbox" v-model="deleteErchuangList" name="article" :value="erchuang.id">
          <span style="flex: 1;">
            <n-ellipsis :line-clamp="1">
              {{ erchuang.ecName }}
            </n-ellipsis>
          </span>
          <span>{{ erchuang.status === 0 ? '审核中' : erchuang.status === 1 ? '审核通过' : '审核为通过' }}</span>
          <span class="pointer" @click="handleErchuangUpdate(erchuang.id)">修改二创</span>
        </div>
      </div>
    </div>
    <div class="right animate__animated animate__fadeInRight">
      <div class="found_list">
        <ErchuangDetail :erchuang="erchuang" v-for="erchuang in ErchuangList"></ErchuangDetail>
        <reuse-Pagination></reuse-Pagination>
      </div>
    </div>
    <!-- 添加修改框 -->
    <n-modal v-model:show="erchuangShow" transform-origin="center">
      <n-card style="width: 600px" :title="title" :bordered="false" size="huge" role="dialog" aria-modal="true">
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
            <n-input v-model:value="erchuang.ecSynopsis" type="textarea" placeholder="请输入二创简介" />
          </n-form-item>
          <n-form-item label="二创封面" path="ecAvater">
            <ImgUpload v-model="erchuang.ecAvater"></ImgUpload>
          </n-form-item>
        </n-form>
        <template #footer>
          <n-space>
            <n-button secondary round @click="erchuangShow = false">
              取消
            </n-button>
            <n-button type="info" secondary round @click="erchuangSubmit">
              提交
            </n-button>
          </n-space>
        </template>
      </n-card>
    </n-modal>
    <!-- 删除框 -->
    <n-modal v-model:show="erchuangDeleteShow" transform-origin="center">
      <n-card style="width: 600px" :title="title" :bordered="false" size="huge" role="dialog" aria-modal="true">
        <template #footer>
          <n-space style="padding-bottom:20px">
            是否删除二创名称为:{{ mappedErchuangNamesByIds }}的数据
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
import { onMounted, ref, computed } from 'vue'
import { addErchuang, delErchuang, getErchuang, listErchuangByUser, updateErchuang } from '@/api/erchuang'
import { useMessage, NModal, NCard, NButton, NSpace, NInput, NForm, NFormItem, NEllipsis, type FormInst } from 'naive-ui'
import ErchuangDetail from "@/components/ErchuangDetail.vue"
import ImgUpload from '@/components/imgUpload/index.vue'
import reusePagination from '@/components/reusePagination/index.vue'

// 定义Erchuang的类型  
interface ErchuangType {
  id: number | null;
  ecName: string;
  ecAuthor: string;
  ecUrl: string;
  ecSynopsis: string;
  ecAvater: string;
}
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
    message: '请上传二创封面'
  },
  ecUrl: {
    required: true,
    trigger: ['blur', '二创路径'],
    message: '请输入二创'
  },
  ecSynopsis: {
    required: true,
    trigger: ['blur', '二创简介'],
    message: '请输入二创简介'
  }
})
//标题
const title = ref("")
//表单
const formRef = ref<FormInst>()
//提示框
const message = useMessage()
//二创列表
const ErchuangList = ref()
//选中列表
const deleteErchuangList = ref([])
//删除框是否显示
const erchuangDeleteShow = ref(false)
//二创添加修改是否显示
const erchuangShow = ref(false)
//二创对象
const erchuang = ref<ErchuangType>({
  id: null,
  ecName: "",
  ecAuthor: "",
  ecUrl: "",
  ecSynopsis: "",
  ecAvater: "",
})
onMounted(() => {
  init();
})
//删除二创的名称列表
const mappedErchuangNamesByIds = computed(() => {
  return deleteErchuangList.value.map((id: number) => {
    const erchuang: any = ErchuangList.value.find((erchuang: any) => erchuang.id === id);
    return erchuang.ecName;
  })
})
//初始化数据
function init() {
  listErchuangByUser().then((res: any) => {
    ErchuangList.value = res.rows;
  })
}
//添加二创
function erchuangSubmit() {
  formRef.value?.validate((errors) => {
    if (!errors) {
      if (erchuang.value.id != null) {
        updateErchuang(erchuang.value).then(() => {
          message.success("修改成功")
          erchuangShow.value = false;
          init();
        }).catch(() => {
          message.error("修改失败")
        })
      } else {
        addErchuang(erchuang.value).then(() => {
          message.success("添加成功")
          erchuangShow.value = false;
          init();
        }).catch(() => {
          message.error("添加失败")
        })
      }
    } else {
      message.warning('请检查参数')
    }
  })
}
//开启修改框
function handleErchuangUpdate(erchuangId: any) {
  title.value = "修改二创"
  getErchuang(erchuangId).then(res => {
    erchuang.value = res.data
    erchuangShow.value = true;
  })
}
//添加框显示
function handleErchuangAdd() {
  title.value = "添加二创"
  erchuang.value = {
    id: null,
    ecName: "",
    ecAuthor: "",
    ecUrl: "",
    ecSynopsis: "",
    ecAvater: "",
  }
  erchuangShow.value = true;
}
//删除框显示
function handleErchuangDelete() {
  title.value = "删除二创"
  if (deleteErchuangList.value.length) {
    erchuangDeleteShow.value = true;
  } else {
    message.warning("请勾选需要删除的二创")
  }
}
//删除二创
function deleteErchuangSubmit() {
  delErchuang(deleteErchuangList.value).then(() => {
    message.success("删除成功")
    erchuangDeleteShow.value = false;
    init();
  }).catch(() => {
    message.error("删除失败")
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

    .found_list {
      display: flex;
      flex-direction: row;
      flex-wrap: wrap;
      justify-content: space-between;
    }
  }
}
</style>
