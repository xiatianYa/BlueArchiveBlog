<template>
    <n-card :style="'width:' + cardSize + 'px'" :bordered="false" role="dialog" aria-modal="true">
        <n-form ref="formRef" :model="formData" :rules="rules" :label-placement="labelPlacement"
            :label-width="labelWidth" require-mark-placement="right-hanging" :size="formSize">
            <template v-for="item of formOption" :key="item.props">
                <!-- 插槽 -->
                <n-form-item v-if="item.type === 'slot'" :label="item.label" :path="item.props">
                    <slot :name="item.slotName"></slot>
                </n-form-item>
                <!-- 输入框 -->
                <n-form-item v-if="item.type === 'input'" :label="item.label" :path="item.props">
                    <n-input v-model:value="formData[item.props]" :placeholder="item.placeholder"
                        :disabled="typeof item.isDisabled === 'function' ? item.isDisabled(formData) : item.isDisabled"
                        :clearable="item.clearable" :type="item.inputType" :maxlength="item.maxlength"
                        :minlength="item.minlength" />
                </n-form-item>
                <!-- 下拉框 -->
                <n-form-item v-if="item.type === 'select'" :label="item.label" :path="item.props">
                    <n-select v-model:value="formData[item.props]" :placeholder="item.placeholder"
                        :filterable="item.filterable" :options="formItemOption[item.selectProps]"
                        :disabled="typeof item.isDisabled === 'function' ? item.isDisabled(formData) : item.isDisabled"
                        clearable />
                </n-form-item>
                <!-- 日期选择器 -->
                <n-form-item v-if="item.type === 'date'" :label="item.label" :path="item.props">
                    <n-date-picker v-model:formatted-value="formData[item.props]" :value-format="item.formatData"
                        :type="item.dataType" :placeholder="item.placeholder || '请选择日期'"
                        :is-date-disabled="item.isDataDisabled(formData[item.props])" clearable />
                </n-form-item>
                <!-- 单选框 -->
                <n-form-item v-if="item.type === 'redio'" :label="item.label" :path="item.props">
                    <n-radio-group v-model:value="formData[item.props]">
                        <n-space>
                            <n-radio v-for="option in item.options" :key="option.value" :value="option.value">
                                {{ option.label }}
                            </n-radio>
                        </n-space>
                    </n-radio-group>
                </n-form-item>
                <!-- 多选框 -->
                <n-form-item v-if="item.type === 'mredio'" :label="item.label" :path="item.props">
                    <n-tree-select multiple cascade checkable check-strategy="parent"
                        :options="formItemOption[item.selectProps]" :placeholder="item.placeholder"
                        :default-value="formData[item.props]" @update:value="item.handleUpdate" />
                </n-form-item>
            </template>
        </n-form>
    </n-card>
</template>
<script lang="ts" setup>
import { NCard, NInput, NForm, NFormItem, NSelect, NDatePicker, NRadioGroup, NSpace, NRadio, NTreeSelect } from 'naive-ui'
import { ref, defineExpose, getCurrentInstance } from 'vue';
import { type CustomType } from '@/types';

interface Props {
    // 表单ref
    formRef?: string;
    // 表单数据
    formData: CustomType;
    // 表单配置
    formOption: Array<any>;
    // 下拉框options
    formItemOption?: CustomType;
    // 单选框数据
    radios?: CustomType,
    // 验证规则
    rules?: CustomType;
    // 标签的宽度
    labelWidth?: labelWidthType;
    // 尺寸
    formSize?: formSizeType;
    // 标签显示的位置
    labelPlacement?: labelPlacementType;
    // 元素宽度
    cardSize?: CustomType;
}

type formSizeType = 'small' | 'medium' | 'large';
type labelPlacementType = "left" | "top";
type labelWidthType = number | string | 'auto';
// 表单对象
let formRef = ref<any | null>(null)
// props参数
let props = withDefaults(defineProps<Props>(), {
    formRef: "formRef",
    formData: () => { return {} },
    rules: () => { return {} },
    formOption: () => [],
    formItemOption: () => { return {} },
    labelWidth: "120px",
    formSize: "medium",
    labelPosition: "top",
    cardSize: 500,
})

// 获取vue实例
const { proxy }: any = getCurrentInstance()
// 表单实例
const ruleFormRef = () => proxy.$refs[props.formRef];
// 暴露表单对象
defineExpose({ ruleFormRef })
</script>
<style lang="scss" scoped></style>