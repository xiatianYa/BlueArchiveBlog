<template>
  <div class="edit" v-loading="loading">
    <v-md-editor v-model="article.content" class="md_edit" :disabled-menus="[]" @save="saveArticle"
      @upload-image="handleUploadImage"></v-md-editor>
  </div>
</template>

<script>
import {getArticle, updateArticle} from "@/api/blog/article";
import {uploadImages} from "@/api/blog/file";

export default {
  data() {
    return {
      //文章对象
      article: {
        //id
        id: null,
        //内容
        content: "",
        //视频
        videoUrl: null,
        //封面
        cover: null,
      }
    };
  },
  created() {
    this.loading = true;
    //获取文字ID 通过文字ID查询文章内容
    const sortId = this.$route.query.sortId;
    if (!sortId) {
      this.$message.warning('文章ID获取失败,请通过正常途径编辑!');
    } else {
      this.getArticleById(sortId)
    }
  },
  methods: {
    //图片上传
    handleUploadImage(event, insertImage, file) {
      const formData = new FormData();
      formData.append('file', file[0]); // 假设 file 是你要上传的文件对象
      uploadImages(formData).then(res => {
        insertImage({
          url: res.data.url,
          desc: res.data.name,
          width: '100%',
          height: 'auto',
        })
        this.$message({
          message: '上传图片成功',
          type: 'success'
        });
      }).catch(error => {
        this.$message({
          message: '上传图片失败!' + error,
          type: 'error'
        });
      })
    },
    //保存文章信息
    saveArticle() {
      //拷贝一个文章对象
      const articleTemp = {
        //id
        id: this.article.id,
        //内容
        content: this.article.content,
        //视频
        videoUrl: this.article.videoUrl,
        //封面
        cover: this.article.cover,
      }
      //对文章内容进行Base64编码
      const buffer = Buffer.from(articleTemp.content, 'utf-8');
      //添加base64:头部 方便后端解析
      articleTemp.content = "base64:" + buffer.toString('base64');
      updateArticle(articleTemp).then(res => {
        this.$message({
          message: '修改文章信息成功',
          type: 'success'
        });
      }).catch(error => {
        this.$message({
          message: '修改文章信息失败' + error,
          type: 'error'
        });
      })
    },
    //根据ID获取文章信息
    getArticleById(sortId) {
      this.loading = true;

      getArticle(sortId).then(res => {
        this.article = res.data
        this.$message({
          message: '获取文章信息成功',
          type: 'success'
        });
        this.loading = false;
      }).catch(error => {
        this.$message({
          message: '获取文章信息失败!' + error,
          type: 'error'
        });
      })
    }
  }
};
</script>
<style lang="scss" scoped>
.edit {
  width: 100%;
  height: 100%;

  .md_edit {
    width: 100%;
    height: 100%;
    min-height: 100vh;
  }
}
</style>
