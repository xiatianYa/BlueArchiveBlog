<template>
    <div class="layout">
        <Header></Header>
        <RouterView />
        <Footer></Footer>
        <Setup></Setup>
    </div>
</template>

<script setup lang="ts">
import Header from "./component/NavView.vue"
import Footer from "./component/FooterView.vue"
import Setup from "./component/SetUpView.vue"
</script>

<style scoped lang="scss"></style>