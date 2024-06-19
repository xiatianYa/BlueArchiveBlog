<template>
    <n-pagination class="pagination" v-model:page="props.page" :page-sizes="props.pageSizes" :page-count="100" size="medium"
        show-quick-jumper show-size-picker @on-update:page="props.pageUpdate"
        @on-update:page-size="props.pageSizeUpdate" />
</template>
<script lang="ts" setup>
import { ref } from 'vue';
import { NPagination } from 'naive-ui'
interface Props {
    // 当前页
    page: number;
    // 总页数
    count?: number;
    // 每页展示多少条
    pageSizes?: Array<number>;
    // 当前页发生改变时的回调函数
    pageUpdate: any;
    // 当前分页大小发生改变时的回调函数
    pageSizeUpdate: any;
}
const props = withDefaults(defineProps<Props>(), {
    page: 1,
    count: 100,
    pageSizes: () => [10, 20, 30, 40],
    pageUpdate: () => { },
    pageSizeUpdate: () => { }
})
</script>
<style lang="scss" scoped>
.pagination{
    width: 100%;
    padding-top: 20px;
    display: flex;
    justify-content: center
}
</style>