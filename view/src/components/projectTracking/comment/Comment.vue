<style scoped>
* {
  box-sizing: border-box;
}

.header {
  width: 75%;
  background-color: rgb(155, 155, 155);
  padding: 30px 30px;
  color: rgb(0, 0, 0);
  text-align: center;
}

.header:after {
  content: "";
  display: table;
  clear: both;
}
</style>

<template>
  <div>
    <div id="header" class="header">
      <comment-input></comment-input>
    </div>

    <commentList></commentList>
  </div>
</template>

<script>
import CommentInput from "./CommentInput.vue";
import CommentList from "./CommentList.vue";

export default {
  name: "Comment",
  components: { CommentInput, CommentList },
};
</script>