<template id="commentTestInput">
  <div class="commInput">
    <br><br>
    <article class="media">
      <!-- <figure class="media-left">
        <p class="image is-64x64">
          <img src="https://bulma.io/images/placeholders/128x128.png" />
        </p>
      </figure> -->
      <div class="media-content">
        <div class="field">
          <p class="control">
            <textarea
              class="textarea"
              id="task"
              v-model="cmt" 
              v-on:keyup.enter="addcomment"
              placeholder="Add a comment..."
            ></textarea>
          </p>
        </div>
        <div class="field">
          <p class="control">
            <b-button class="addbutton" v-on:click="addcomment">작성</b-button>
          </p>
        </div>
      </div>
    </article>
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
            console.log("eventbus 전송 후")
            this.cmt="";
        }
    }
};
</script>
