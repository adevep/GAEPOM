<template>
  <div class="TrackingList">
    <!-- 웹앱,모바일앱,데이터사이언스,게임개발 -->
    <router-link tag="a" to="/projectTrackingInsert"
      >Tracking Insert</router-link
    >
    <b-field custom-class="is-medium" horizontal>
      <div class="notification is-primary">
        <b-field class="buttons">
          <b-button type="is-primary" inverted outlined @click="categoryAll"
            >전체</b-button
          >
          <b-button type="is-primary" inverted outlined @click="webApp"
            >웹앱</b-button
          >
          <b-button type="is-primary" inverted outlined @click="mobileApp"
            >모바일앱</b-button
          >
          <b-button type="is-primary" inverted outlined @click="dataSci"
            >데이터사이언스</b-button
          >
          <b-button type="is-primary" inverted outlined @click="game"
            >게임개발</b-button
          >
          <b-input
            placeholder="프로젝트 타이틀 검색..."
            type="text"
            v-model="title"
            @keyup.native.enter="searchTitle"
          ></b-input>
          <b-button type="is-primary" @click="searchTitle"> 검색 </b-button>
        </b-field>
      </div>
    </b-field>
    <div class="container">
      <div class="section">
        <div class="columns"></div>
        <div id="app" class="row columns is-multiline">
          <div
            v-for="(value, key) in trackings"
            v-bind:key="key"
            class="column is-4"
          >
         
            <div class="card large">
               <router-link
                        :to="{
                          name: 'ProjectTrackingDetail',
                          params: { track: value },
                        }"
                        >
              <div class="card-image">
                <figure class="image is-16by9">
                  <img class="is-rounded" :src="imgURL + value.trackImage" />
                </figure>
              </div>
              <div class="card-content">
                <div class="media">
                  <div class="media-left">
                    <figure class="image is-48x48">
                      
                      <img class="is-rounded" :src="imgURL + value.project.userId.userImage" />
                      <!-- <img :src="card.avatar" alt="Image"> -->
                    </figure>
                  </div>
                  <div class="media-content">
                    <p class="title is-4 no-padding">
                      {{ value.project.pjTitle }}
                    </p>
                    <p>
                      <span class="title is-6">
                        {{ value.project.userId.userId }}
                      </span>
                    </p>
                    <p class="subtitle is-6">{{ value.project.pjCategory }}</p>
                  </div>
                </div>
                <div class="content">
                  {{ value.project.pjDescription }}
                  <div class="background-icon">
                    <span class="icon-twitter"></span>
                  </div>
                </div>
              </div>
          </router-link>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "TrackingList",
  data() {
    const tracks = [];
    return {
      tracks,
      trackings: [],
      cardData: [JSON.parse(sessionStorage.getItem("user"))],
      title: "",
    };
  },
  computed: {
    ...mapState(["imgURL"])
  },
  methods: {
    trackList: function() {
      this.axios
        .get("/gettrackinglistaxios")
        .then((response) => {
          this.trackings = response.data;
          this.tracks = this.trackings;
          console.log("==========list==========");
          console.log(response);
          console.log("==========list==========");
        })
        .catch((error) => {
          console.log("에러" + error);
        });
    },

    categoryAll: function() {
      this.trackings = this.tracks;
    },

    webApp: function() {
      this.trackings = [];
      for (let i = 0; i < this.tracks.length; i++) {
        if (this.tracks[i].project.pjCategory == "웹앱") {
          this.trackings.push(this.tracks[i]);
        }
      }
      console.log("---webappbtn");
      console.log(this.trackings);
    },

    mobileApp: function() {
      this.trackings = [];
      for (let i = 0; i < this.tracks.length; i++) {
        if (this.tracks[i].project.pjCategory == "모바일앱") {
          this.trackings.push(this.tracks[i]);
        }
      }
    },

    dataSci: function() {
      this.trackings = [];
      for (let i = 0; i < this.tracks.length; i++) {
        if (this.tracks[i].project.pjCategory == "데이터사이언스") {
          this.trackings.push(this.tracks[i]);
        }
      }
    },

    game: function() {
      this.trackings = [];
      for (let i = 0; i < this.tracks.length; i++) {
        if (this.tracks[i].project.pjCategory == "게임개발") {
          this.trackings.push(this.tracks[i]);
        }
      }
    },

    searchTitle: function() {
      this.trackings = [];
      var trackTitle = this.title;

      if (this.title != "") {
        // 입력값이 있을 경우
        this.trackings = this.tracks.filter(function(item) {
          return item.project.pjTitle.includes(trackTitle);
        });
        if (this.trackings.length == 0) {
          // 입력값에 해당하는 데이터가 없을 경우
          alert("해당하는 데이터가 없습니다.");
          this.trackings = this.tracks;
        }
      } else {
        // 아무 입력 없이 검색버튼 클릭할 경우 -> 모든 리스트
        this.trackings = this.tracks;
      }
      this.title = "";
    },
  },

  mounted() {
    this.trackList();
  },
};
</script>
