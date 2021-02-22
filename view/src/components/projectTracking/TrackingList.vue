<template>
  <div id="app" class="pt-5">
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link
      href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/@mdi/font@5.8.55/css/materialdesignicons.min.css"
    />
    <section>
      <div class="hero-body">
        <div class="container has-text-centered">
          <h1 class="title ">
            프로젝트 근황
          </h1>
          <h2 class="subtitle centered">
            당신의 꿈을 찾으세요!
          </h2>
          <nav
            class="breadcrumb has-dot-separator is-centered"
            aria-label="breadcrumbs"
          >
            <ul>
              <li><a href="#">홈페이지</a></li>
              <li class="is-active">
                <a href="#" aria-current="page">프로젝트 근황</a>
              </li>
            </ul>
          </nav>
        </div>
      </div>
    </section>

    <section>
      <div class="container">
        <div class="section">
          <div class="columns">
            <b-field grouped group-multiline class="mb-6">
              <div>
                <b-button focused inverted outlined @click="categoryAll"
                  >전체</b-button
                >
                <b-button inverted outlined @click="webApp">웹앱</b-button>
                <b-button inverted outlined @click="mobileApp"
                  >모바일앱</b-button
                >
                <b-button inverted outlined @click="dataSci"
                  >데이터사이언스</b-button
                >
                <b-button inverted outlined @click="game">게임개발</b-button>
              </div>
            </b-field>
            <b-field grouped group-multiline>
              <b-input
                class="ml-3"
                placeholder="프로젝트 타이틀 검색..."
                type="text"
                v-model="title"
                @keyup.native.enter="searchTitle"
              ></b-input>
              <b-button @click="searchTitle">
                검색
              </b-button>
            </b-field>
            <div class="column has-text-centered"></div>
          </div>
          <div id="app" class="row columns is-multiline">
            <div
              v-for="(value, key) in trackings"
              v-bind:key="key"
              class="column is-3"
            >
              <div class="card medium">
                <router-link
                  :to="{
                    name: 'ProjectTrackingDetail',
                    params: { track: value }
                  }"
                >
                  <div class="card-image">
                    <figure class="image is-16by11">
                      <img :src="imgURL + value.trackImage" />
                    </figure>
                  </div>
                  <div class="card-content">
                    <div class="media">
                      <div class="media-left">
                        <figure class="image is-48x48">
                          <img
                            class="is-rounded"
                            :src="imgURL + value.project.userId.userImage"
                          />
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
                        <p class="subtitle is-6">
                          {{ value.project.pjCategory }}
                        </p>
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
    </section>
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
      title: ""
    };
  },
  computed: {
    ...mapState(["imgURL"])
  },
  methods: {
    trackList: function() {
      this.axios
        .get("/track/gettrackinglist")
        .then(response => {
          this.trackings = response.data;
          this.tracks = this.trackings;
        })
        .catch(error => {
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
          this.trackings = this.tracks;
        }
      } else {
        // 아무 입력 없이 검색버튼 클릭할 경우 -> 모든 리스트
        this.trackings = this.tracks;
      }
      this.title = "";
    }
  },

  mounted() {
    this.trackList();
  }
};
</script>
