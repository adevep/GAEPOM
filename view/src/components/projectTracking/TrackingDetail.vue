<template>
  <div class="TrackingDetail">
    <div class="container is-max-desktop pt-5">
      <section class="mt-6 mb-5">
        <article class="media">
          <figure class="media-left">
            <p class="image is-64x64">
              <img
                class="is-rounded"
                src="https://bulma.io/images/placeholders/128x128.png"
              />
            </p>
          </figure>
          <div class="media-content">
            <div class="content" >
              <p>
                <strong>{{  trackInfo.project.userId.name }}</strong>
                <small>@{{  trackInfo.project.userId.userId }}</small>
                <br />
                {{  trackInfo.project.userId.words }}
                {{  trackInfo.project.userId.position }}
              </p> 
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
              <b-tag type="is-primary">{{  trackInfo.project.pjCategory }}</b-tag>
              <b-tag type="is-primary is-light">{{  trackInfo.project.recSeq.location }}</b-tag>
              <b-tag type="is-link">{{  trackInfo.project.pjTools }}</b-tag>
              <b-tag type="is-link is-light">{{ trackInfo.project.pjLang }}</b-tag>
              <b-tag type="is-link is-light">{{ trackInfo.project.pjDuration }}</b-tag>
            </b-taglist>
            <center>
              <table border="1">
                <tr>
                  <td>Title</td>
                  <td>{{ trackInfo.project.pjTitle }}</td>
                </tr>
                <tr>
                  <td>Duration</td>
                  <td>{{ trackInfo.project.pjDuration }}</td>
                </tr>
                <tr>
                  <td>Tools</td>
                  <td>{{ trackInfo.project.pjTools }}</td>
                </tr>
                <tr>
                  <td>Category</td>
                  <td>{{ trackInfo.project.pjCategory }}</td>
                </tr>
                <tr>
                  <td>Language</td>
                  <td>{{ trackInfo.project.pjLang }}</td>
                </tr>
                <tr>
                  <td>DBMS</td>
                  <td>{{ trackInfo.project.pjDbms }}</td>
                </tr>
                <!-- <tr>
                     <td>applications</td> 
                     <td>{{value.project.pjTitle}}</td>
                </tr> -->
                <tr>
                  <td>Description</td>
                  <td>{{ trackInfo.project.pjDescription }}</td>
                </tr>
                <tr>
                  <td>Stage</td>
                  <td>{{ trackInfo.stage }}</td>
                </tr>
                <tr>
                  <td>Issue</td>
                  <td>{{ trackInfo.issue }}</td>
                </tr>
                <tr>
                  <td>Output</td>
                  <td>{{ trackInfo.output }}</td>
                </tr>
                <tr>
                  <td>Image</td>
                  <td>{{ trackInfo.trackImage }}</td>
                </tr>
              </table>

              <br />
              <li>tracklike : {{ trackInfo.trackLike }}</li>
              <li>likeFlag : {{ likeFlag }}</li>
              <br />
              <button v-on:click="addTrackingLike(trackInfo.trackSeq)">
                좋아요 선택하기</button
              ><br />
              <button @click="deleteTracking">삭제</button>
              <router-link
                tag="button"
                :to="{
                  name: 'ProjectTrackingUpdate',
                  params: { trackSeq: trackInfo },
                }"
                >수정</router-link
              >
            </center>
            <!-- <h4 class="title is-4">우대사항:</h4>
            <h5 class="subtitle is-5">{{ pj.preference }}</h5> -->
          </b-tab-item>
          <b-tab-item label="댓글">
            <comment/>
          </b-tab-item>
        </b-tabs>
      </section>
    </div>
  </div>
</template>
<script>
import ProjectTracking from "../../views/ProjectTracking.vue";
import Comment from "./comment/Comment.vue";

export default {
  name: "TrackingDetail",
  data() {
    return {
      trackInfo: [],
      likeFlag: 0,
      likeArray: [],
      userdatas: JSON.parse(sessionStorage.getItem("user")),
      // uId: this.userdatas.userId,
      likeSeq: '' 
    };
  },
  components: {
    Comment,
  },
  created() {
    this.tracking();
  },
  methods: {
    tracking: function() {
      this.axios
        .get("/getprojecttracking", {
          params: {
            trackSeq: this.$route.params.track.trackSeq,
          },
        })
        .then((response) => {
          this.trackInfo = response.data;
        })
        .catch((error) => {
          console.log("에러" + error);
        });
    },
    deleteTracking: function() {
      this.axios
        .delete("/deleteprojecttracking", {
          params: {
            trackSeq: this.trackInfo.trackSeq,
          },
        })
        .then((response) => {
          console.log(response);
          // 페이지 이동
          this.$router.push(ProjectTracking);
        })
        .catch((ex) => {
          console.warn("ERROR!!!!! : ", ex);
        });
    },
    showlike: function() {
      console.log("---showlike()-----")
     console.log(this.likeSeq)
      if (this.likeSeq != "") {  //4,
        console.log("show - likeSeq는 null이 아님")
        this.likeArray = this.likeSeq.split(',');

        // if(this.likeArray != null){
        for (let i = 0; i < this.likeArray.length; i++) {
          if (this.likeArray[i] == this.trackInfo.trackSeq.toString()) {
            //좋아요 표시되있는것 구분 0 or 1
            this.likeFlag += 1;
          } 
        }
        // }else{

        //   this.likeFlag += 1;
        // }
      }
    },
    addTrackingLike: function(id) {
      console.log(this.likeArray)
      //좋아요 수 증가, 해당유저가 좋아요한 트래킹글 추가
    if(this.likeArray != ""){
      if (this.likeFlag == 0) {
        this.likeFlag += 1;

        this.likeArray.splice(this.likeArray.length-1, 1, this.trackInfo.trackSeq + ", ")
        this.trackInfo.trackLike += 1;

      } else {
        this.likeFlag = 0;
        var index = this.likeArray.findIndex(function(item) {
          return item.trackSeq === id;
        });

        this.likeArray.splice(index, 1); //(start, 삭제하고자 하는 개수)
        this.trackInfo.trackLike -= 1;
      }
    }else{
      console.log("ddd")
       this.likeFlag += 1;
       this.likeArray = this.trackInfo.trackSeq + ",hi,"
       this.trackInfo.trackLike += 1;
    }
      //서버통신부
      //
      this.axios
        .put(
          "/updateusertliked?userid=" +
            this.userdatas.userId +
            "&tliked=" +
            this.likeArray.toString()
        )
        .then((response) => {
          console.warn(response.data);
          this.getUser();
        })
        .catch((ex) => {
          console.warn("ERROR!!!!! : ", ex);
        });

      this.updateTrackingLike();
     
    },
    getUser: function(){
      
      console.log("userID에옹잉")
      console.log(this.userdatas.userId)
      this.axios.get("/getuser?userid=" + this.userdatas.userId)
      .then((response) => {
        console.warn(response.data);
        this.likeSeq = response.data.tliked
        console.log(this.likeSeq)
      })
      .catch((ex) => {
        console.warn("ERROR!!!!! : ", ex);
      });
    },
    updateTrackingLike: function() {
      this.axios
        .put(
          "/updatetrackinglike?trackSeq=" +
            this.trackInfo.trackSeq +
            "&trackLike=" +
            this.trackInfo.trackLike
        )
        .then((response) => {
          console.warn(response.data);
        })
        .catch((ex) => {
          console.warn("ERROR!!!!! : ", ex);
        });
    },
  },
  mounted() {
    this.trackInfo = this.$route.params.track;
    this.getUser();
    this.showlike();
    
  },
};
</script>
