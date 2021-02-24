<template>
  <div class="TrackingDetail">
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link
      href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/@mdi/font@5.8.55/css/materialdesignicons.min.css"
    />
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
    />
    <link
      rel="stylesheet"
      href="//cdn.jsdelivr.net/gh/xpressengine/xeicon@2.3.1/xeicon.min.css"
    />
    <div class="container is-max-desktop pt-5">
      <section class="mt-6 mb-5">
        <article class="media">
          <figure class="media-left">
            <p class="image is-64x64">
              <img
                class="is-rounded"
                :src="imgURL + trackInfo.project.userId.userImage"
              />
            </p>
          </figure>
          <div class="media-content">
            <div class="content">
              <router-link
                :to="{
                  name: 'yourpage',
                  params: { pickedid: trackInfo.project.userId.userId }
                }"
              >
                <p>
                  <strong>{{ trackInfo.project.userId.name }}</strong>
                  <small>@{{ trackInfo.project.userId.userId }}</small>
                  <br />
                  {{ trackInfo.project.userId.position }}
                </p>
              </router-link>
              <div v-if="likeFlag == 0">
                <b-button
                  class="mt-1"
                  type="is-link is-light"
                  v-on:click="addTrackingLike(trackInfo.trackSeq)"
                  icon-left="heart-outline"
                >
                  {{ trackInfo.trackLike }}
                </b-button>
              </div>
              <div v-else>
                <b-button
                  type="is-link is-light"
                  v-on:click="addTrackingLike(trackInfo.trackSeq)"
                  icon-left="cards-heart"
                >
                  {{ trackInfo.trackLike }}
                </b-button>
              </div>
            </div>
            <nav class="level is-mobile">
              <div class="level-left">
                <a class="level-item">
                  <span class="icon is-small"
                    ><i class="fas fa-reply"></i
                  ></span>
                </a>
                <a class="level-item">
                  <span class="icon is-small"
                    ><i class="fas fa-retweet"></i
                  ></span>
                </a>
                <a class="level-item">
                  <span class="icon is-small"
                    ><i class="fas fa-heart"></i
                  ></span>
                </a>
              </div>
            </nav>
          </div>
        </article>
      </section>
      <section>
        <b-tabs type="is-boxed">
          <b-tab-item label="프로젝트">
            <b-taglist>
              <b-tag type="is-primary">{{
                trackInfo.project.pjCategory
              }}</b-tag>
              <b-tag type="is-link">{{ trackInfo.project.pjTools }}</b-tag>
              <b-tag type="is-link is-light">{{
                trackInfo.project.pjLang
              }}</b-tag>
            </b-taglist>
            <h2 class="title is-4 mt-5">
              <i class="xi-lightbulb"></i> {{ trackInfo.project.pjTitle }}
            </h2>
            <h2 class="subtitle">
              {{ trackInfo.project.pjDescription }}
            </h2>
            <br />
            <h2 class="subtitle">
              [ 프로젝트 기간 ] <br />
              {{ trackInfo.project.pjDuration }} <br /><br />
              [ 카테고리 ] <br />
              {{ trackInfo.project.pjCategory }} <br /><br />
              [ 사용 툴 ] <br />
              {{ trackInfo.project.pjTools }} <br /><br />
              [ 사용 언어 ] <br />
              {{ trackInfo.project.pjLang }} <br /><br />
              [ 사용 DBMS ] <br />
              {{ trackInfo.project.pjDbms }} <br /><br />
              [ 개발 단계 ] <br />
              {{ trackInfo.stage }} <br /><br />
              [ 이슈 사항 ] <br />
              {{ trackInfo.issue }} <br /><br />
              [ 프로젝트 결과 ] <br />
              {{ trackInfo.output }} <br /><br />
            </h2>
            <br />
            <img class="is-rounded" :src="imgURL + trackInfo.trackImage" />

            <br />
            <b-message
              title="NOTIFICATION"
              class="mt-6"
              type="is-success"
              has-icon
              aria-close-label="Close message"
              v-if="userdatas.userId == trackInfo.project.userId.userId"
            >
              당신의 프로젝트 입니다.

              <br />
              <router-link
                class="button"
                :to="{
                  name: 'ProjectTrackingUpdate',
                  params: { trackSeq: this.trackInfo }
                }"
                >수정</router-link
              >
            </b-message>
            <b-message v-else></b-message>
          </b-tab-item>
          <b-tab-item label="댓글">
            <comment />
          </b-tab-item>
        </b-tabs>
      </section>
    </div>
  </div>
</template>
<script>
import Comment from "./comment/Comment.vue";
import { mapState } from "vuex";
export default {
  name: "TrackingDetail",
  data() {
    return {
      trackInfo: [],
      likeFlag: 0,
      likeArray: [],
      userdatas: JSON.parse(sessionStorage.getItem("user")),
      likeSeq: null
    };
  },
  components: {
    Comment
  },
  computed: {
    ...mapState(["imgURL"])
  },
  created() {
    this.tracking();
  },
  methods: {
    tracking: function() {
      this.axios
        .get("/track/getprojecttracking", {
          params: {
            trackSeq: this.trackInfo.trackSeq
          }
        })
        .then(response => {
          this.trackInfo = response.data;
          console.log(this.trackInfo);
        })
        .catch(error => {
          console.log("에러" + error);
        });
    },
    showlike: function() {
      if (this.likeSeq != "") {
        this.likeArray = this.likeSeq.split(",");
        for (let i = 0; i < this.likeArray.length; i++) {
          if (this.likeArray[i] == this.trackInfo.trackSeq.toString()) {
            this.likeFlag = 1;
          }
        }
      }
    },
    addTrackingLike: function(id) {
      //좋아요 수 증가, 해당유저가 좋아요한 트래킹글 추가
      if (this.likeArray != "") {
        if (this.likeFlag == 0) {
          this.likeFlag += 1;
          this.likeArray.splice(
            this.likeArray.length - 1,
            1,
            this.trackInfo.trackSeq + ","
          );
          this.trackInfo.trackLike += 1;
        } else {
          this.likeFlag = 0;
          var num = 0;
          for (let i = 0; i < this.likeArray.length; i++) {
            if (this.likeArray[i] == id) {
              num = i;
            }
          }
          this.likeArray.splice(num, 1); //(start, 삭제하고자 하는 개수)
          this.trackInfo.trackLike -= 1;
        }
      } else {
        this.likeFlag += 1;
        this.likeArray = this.trackInfo.trackSeq + ",hi,";
        this.trackInfo.trackLike += 1;
      }
      //서버통신부
      this.axios
        .put(
          "http://localhost:80/user/updatetliked?userid=" +
            this.userdatas.userId +
            "&tliked=" +
            this.likeArray.toString()
        )
        .then(response => {
          console.warn(response.data);
          this.getUser();
        })
        .catch(ex => {
          console.warn("ERROR!!!!! : ", ex);
        });
      this.updateTrackingLike();
    },
    getUser: function() {
      this.axios
        .get("http://localhost:80/user/get?userid=" + this.userdatas.userId)
        .then(response => {
          this.likeSeq = response.data.tliked;
          this.showlike(this.likeSeq);
        })
        .catch(ex => {
          console.warn("ERROR!!!!! : ", ex);
        });
    },
    updateTrackingLike: function() {
      this.axios
        .put(
          "/track/updatetrackinglike?trackSeq=" +
            this.trackInfo.trackSeq +
            "&trackLike=" +
            this.trackInfo.trackLike
        )
        .then(response => {
          console.warn(response.data);
        })
        .catch(ex => {
          console.warn("ERROR!!!!! : ", ex);
        });
    }
  },
  mounted() {
    this.trackInfo = this.$route.params.track;
    this.getUser();
  }
};
</script>
