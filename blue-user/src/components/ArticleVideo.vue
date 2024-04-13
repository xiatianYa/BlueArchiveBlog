<template>
    <div ref="artRef"></div>
</template>

<script setup>
import Artplayer from 'artplayer';
import {nextTick, onBeforeUnmount, onMounted, ref} from 'vue';

const emit = defineEmits(['get-instance']);

const props = defineProps({
    option: {
        type: Object,
        required: true,
    }
});
const artRef = ref(null);
var art;
onMounted(() => {
    nextTick(() => {
        art = new Artplayer({
            ...props.option,
            container: artRef.value,
        });
        emit('get-instance', art);
    });
});

onBeforeUnmount(() => {
    if (art) {
        art.destroy(false);
    }
});
</script>