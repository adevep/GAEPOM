<template id="commentTestList">
  <div class="comm">
    <article
      class="media"
      v-for="value in commentlist"
      v-bind:key="value.cmtSeq"
    >
      <div class="media-content">
        <div class="content">
          <p>
            <strong>{{ value.userId }}</strong>
            <br />
            {{ value.cmt }}
            <br />
            <small
              > {{ value.cmtDate }}
              <span class="close" v-on:click.stop="deletecomment(value.cmtSeq, value.userId)"
                ><p v-if="value.userId == userInfo.userId">delete</p></span
              ></small
            >
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
  name: "CommentList",
  data: function() {
    return {
      commentlist: [],
      userInfo: ''
    };
  },

  created: function() {
    eventBus.$on("add-comment", this.addcomment);
  },

  computed: {
    ...mapState(["imgURL"]),
  },

  methods: {
    showComment: function() {
      this.axios
        .get("/comment/getcommentlist", {
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

    addcomment: function(cmt) {

      if (cmt !== "") {
        // 입력 값 list 추가
        this.commentlist.push({
          cmt: cmt,
          userId: this.userInfo.userId,
          cmtLike: 0,
          parentId: "",
          depth: 0,
          trackSeq: this.$route.params.track,
        });

        // server 통신부
        this.axios
          .post("/comment/insertcomment", this.commentlist[this.commentlist.length - 1])
          .then((response) => {
            console.warn(response.data);
            this.showComment();
          })
          .catch((ex) => {
            console.warn("ERROR!!!!! : ", ex);
          });
      }
      
    },

    deletecomment: function(id, user) {
      console.log(typeof(user))  //string
      console.log(typeof(this.userInfo.userId))  //string

      // 본인 댓글만 지울 수 있게 하는 검증
      if(this.userInfo.userId == user){
      var index = this.commentlist.findIndex(function(item) {
        return item.cmtSeq === id;
      });

      this.axios
        .delete("/comment/deletecomment", {
          params: {
            cmtSeq: id,
          },
        })
        .then((response) => {
          console.log(response);
          this.commentlist.splice(index, 1); 
        })
        .catch((ex) => {
          console.warn("ERROR!!!!! : ", ex);
        });
      }else{
        alert("댓글 삭제 불가")
      }
    },
  },

  mounted() {
    this.showComment();
    this.userInfo = JSON.parse(sessionStorage.getItem("user"));
  },
  beforeDestroy() {
    eventBus.$off("add-comment");
  },
};
</script>
