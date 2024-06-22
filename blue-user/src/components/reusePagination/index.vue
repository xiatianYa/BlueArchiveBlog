<template>
    <n-pagination class="pagination" :page="pageNum + 1" :page-size="pageSize" :page-sizes="pageSizes"
        :page-count="count" size="medium" show-quick-jumper show-size-picker :on-update:page="handleCurrentChange"
        :on-update:page-size="handleSizeChange" />
</template>
<script lang="ts" setup>
import { NPagination } from 'naive-ui'
interface Props {
    // 当前页
    pageNum: number;
    // 总页数
    count?: number;
    //展示数量
    pageSize?: number;
    // 展示数量列表
    pageSizes?: Array<number>;
}
const props = withDefaults(defineProps<Props>(), {
    pageNum: 0,
    count: 0,
    pageSize: 10,
    pageSizes: () => [10, 20, 30],
})
const emits = defineEmits<{
    (e: 'sizeChange', size: number): void
    (e: 'currentChange', pageSize: number): void
}>()
//当前页改变的回调函数
const handleSizeChange = (val: number) => {
    emits("sizeChange", val)
}
const handleCurrentChange = (val: number) => {
    emits("currentChange", val)
}
</script>
<style lang="scss" scoped>
.pagination {
    width: 100%;
    padding-top: 20px;
    display: flex;
    justify-content: center
}
</style>