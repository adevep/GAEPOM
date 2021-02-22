<template id="commentTestInput">
  <div class="commInput">
    <br /><br />
    <article class="media">
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
import eventBus from "../../../EventBus";
import { mapState } from "vuex";

export default {
  name: "CommentInput",
  data() {
    return {
      cmt: "",
      userInfo: "",
    };
  },

  computed: {
    ...mapState(["imgURL"]),
  },
  methods: {
    addcomment: function() {
      eventBus.$emit("add-comment", this.cmt);
      console.log("eventbus 전송 후");
      this.cmt = "";
    },
    showComment: function() {
      this.axios
        .get("/getcommentlist", {
          params: {
            trackSeq: this.$route.params.track.trackSeq,
          },
        })
        .then((response) => {
          this.commentlist = response.data;
          this.commentlist.sort((a, b) => a.cmtSeq - b.cmtSeq);
        })
        .catch((error) => {
          console.log("에러" + error);
        });
    },
  },
  mounted () {
    this.userInfo = JSON.parse(sessionStorage.getItem("user"))
  }
};
</script>
