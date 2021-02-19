<template id="commentTestList">
  <div class="comm">
    <article
      class="media"
      v-for="value in commentlist"
      v-bind:key="value.cmtSeq"
    >
      <figure class="media-left">
        <p class="image is-64x64">
          <img src="https://bulma.io/images/placeholders/128x128.png" />
        </p>
      </figure>
      <div class="media-content">
        <div class="content">
          <p>
            <strong>{{ value.userId }}</strong>
            <br />
            {{ value.cmt }}
            <br />
            <small
              ><a>Like</a> · <a>Reply</a> {{ value.cmtDate }}
              <span class="close" v-on:click.stop="deletecomment(value.cmtSeq)"
                >delete</span
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

  methods: {
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

    addcomment: function(cmt) {

      if (cmt !== "") {
        // 입력 값 list에 추가
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
          .post("/insertcomment", this.commentlist[this.commentlist.length - 1])
          .then((response) => {
            console.warn(response.data);
          })
          .catch((ex) => {
            console.warn("ERROR!!!!! : ", ex);
          });
      }
      this.showComment();
    },

    deletecomment: function(id) {
      var index = this.commentlist.findIndex(function(item) {
        return item.cmtSeq === id;
      });

      this.axios
        .delete("/deletecomment", {
          params: {
            cmtSeq: id,
          },
        })
        .then((response) => {
          console.log(response);
          this.commentlist.splice(index, 1); //(start, 삭제하고자 하는 개수)
        })
        .catch((ex) => {
          console.warn("ERROR!!!!! : ", ex);
        });
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
