<template>
  <div>
    <div id="header" class="header">
        <comment-list></comment-list>
    </div>
    <comment-input></comment-input>
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