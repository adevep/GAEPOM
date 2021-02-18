<template id="commentInput">
    <div class="commentInput">
        <hr>
        <h2>Comment</h2>
        <center>
            <input class="input" type="text" id="task" placeholder="댓글을 입력하세요" 
               v-model="cmt" v-on:keyup.enter="addcomment">
            <span class="addbutton" v-on:click="addcomment">작성</span>
        </center>
    </div>
</template>
<script>
import eventBus from '../../../EventBus'
export default {
    name: "CommentInput",
    data () {
        return {
            cmt:'',
        }
    },
    methods: {
        addcomment: function () {
            
            // commentList로 이동
            eventBus.$emit('add-comment', this.cmt); 
            this.cmt="";
        }
    }
}
</script>
<style scoped>
* {
    box-sizing: border-box;
}
.comment {
    width: 75%;
}
.input {
  border: none;
  width: 75%;
  height: 35px;
  padding: 10px;
  float: left;
  font-size: 16px;
}
.addbutton {
  padding: 10px;
  width: 25%;
  height: 35px;
  background: #D9D9D9;
  color: #555;
  float: left;
  text-align: center;
  font-size: 13px;
  cursor: pointer;
}
.addbutton:hover {
  background-color: #bbb;
}
</style>