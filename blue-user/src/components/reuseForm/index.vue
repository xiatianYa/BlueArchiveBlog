<template>
    <n-card style="width: 600px" :bordered="false" role="dialog" aria-modal="true">
        <n-form :ref="formRef" :model="formData" :rules="rules" :label-placement="labelPlacement"
            :label-width="labelWidth" require-mark-placement="right-hanging" :size="formSize">
            <template v-for="item of formOption" :key="item.props">

                <!-- 插槽 -->
                <n-form-item v-if="item.type === 'slot'" :label="item.label" :path="item.props">
                    <slot :name="item.slotName"></slot>
                </n-form-item>
                <!-- 输入框 -->
                <n-form-item v-if="item.type === 'input'" :label="item.label" :path="item.props">
                    <n-input v-model="formData[item.props]" :placeholder="item.placeholder"
                        :disabled="typeof item.isDisabled === 'function' ? item.isDisabled(formData) : item.isDisabled"
                        :clearable="item.clearable" :type="item.inputType" :maxlength="item.maxlength"
                        :minlength="item.minlength" @input="(val: any) => onInput(val, item.props)" />
                </n-form-item>
                <!-- 下拉框 -->
                <n-form-item v-if="item.type === 'select'" :label="item.label" :path="item.props">
                    <n-select v-model:value="formData[item.props]" :placeholder="item.placeholder"
                        :filterable="item.filterable" :options="item.options"
                        :disabled="typeof item.isDisabled === 'function' ? item.isDisabled(formData) : item.isDisabled"
                        clearable @change="(val: any) => onSelectChange(item.props, val)" />
                </n-form-item>
                <!-- 日期选择器 -->
                <n-form-item v-if="item.type === 'date'" :label="item.label" :path="item.props">
                    <n-date-picker v-model:formatted-value="formData[item.props]" :value-format="item.formatData"
                        :type="item.dataType" :placeholder="item.placeholder || '请选择日期'"
                        :is-date-disabled="item.isDataDisabled(formData[item.props])" clearable />
                </n-form-item>
                <!-- 单选框 -->
                <n-form-item v-if="item.type === 'redio'" :label="item.label" :path="item.props">
                    <n-radio-group v-model:value="formData[item.props]" @change="onRadioChange">
                        <n-space>
                            <n-radio v-for="option in item.options" :key="option.value" :value="option.value">
                                {{ option.label }}
                            </n-radio>
                        </n-space>
                    </n-radio-group>
                </n-form-item>

            </template>
        </n-form>
    </n-card>
</template>
<script lang="ts" setup>
import { NCard, NInput, NForm, NFormItem, NSelect, NDatePicker, NRadioGroup, NSpace, NRadio } from 'naive-ui'
import { getCurrentInstance } from 'vue';
import { type CustomType } from '@/types';

type formSizeType = 'small' | 'medium' | 'large';
type labelPlacementType = "left" | "top";
type labelWidthType = number | string | 'auto';
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
}

const props = withDefaults(defineProps<Props>(), {
    formRef: "formRef",
    formData: () => { return {} },
    rules: () => { return {} },
    formOption: () => [],
    formItemOption: () => { return {} },
    labelWidth: "120px",
    formSize: "medium",
    labelPosition: "top",
})
let emits = defineEmits<{
    (e: 'onSelectChange', data: { propsStr: string, val: any }): void,
    (e: 'onRadioChange', value: any): void,
    (e: 'onInput', data: { value: string, props: string }): void
}>()

// 输入框输入
const onInput = (value: string, props: string) => {
    emits("onInput", { value, props })
}


// 下拉切换
const onSelectChange = (propsStr: string, val: any) => {
    emits("onSelectChange", { propsStr, val })
}
// 单选框切换
const onRadioChange = (value: any) => {
    emits("onRadioChange", value)
}

// 获取vue实例
const { proxy }: any = getCurrentInstance()
// 表单实例
const ruleFormRef = () => proxy.$refs[props.formRef];

defineExpose({
    ruleFormRef
})
</script>
<style lang="scss" scoped></style>