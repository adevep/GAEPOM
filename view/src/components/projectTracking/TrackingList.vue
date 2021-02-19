<template>
  <div class="TrackingList">
    <router-link tag="a" to="/projectTrackingInsert">이동</router-link>
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
              <div class="card-image">
                <figure class="image is-16by9">
                  <img src="https://bulma.io/images/placeholders/128x128.png" />
                </figure>
              </div>
              <div class="card-content">
                <div class="media">
                  <div class="media-left">
                    <figure class="image is-48x48">
                      <img
                        class="is-rounded"
                        src="https://bulma.io/images/placeholders/128x128.png"
                      />
                      <!-- <img :src="card.avatar" alt="Image"> -->
                    </figure>
                  </div>
                  <div class="media-content">
                    <p class="title is-4 no-padding">
                      <router-link
                        tag="td"
                        :to="{
                          name: 'ProjectTrackingDetail',
                          params: { track: value },
                        }"
                        >{{ value.project.pjTitle }}</router-link
                      >
                    </p>
                    <p>
                      <span class="title is-6">
                        <a :href="'http://twitter.com/${card.user.handle}'">
                          {{ value.project.userId.userId }}
                        </a>
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
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- <table border="1">
       <tr>
              <th>제목</th>
              <th>카테고리</th>
              <th>기간</th>
              <th>이미지</th>
             
          </tr>
          <tr v-for="(value, key) in trackings" v-bind:key="key">
            
              <router-link tag="td" :to="{name: 'ProjectTrackingDetail', params: {track: value}}">{{value.project.pjTitle}}</router-link>
              <td>{{value.project.pjCategory}}</td>
              <td>{{value.project.pjDuration}}</td>
              <td>{{value.trackImage}}</td>
          </tr>
           
    </table> -->
  </div>
</template>

<script>
// @ is an alias to /src
export default {
  name: "TrackingList",
  data() {
    return { trackings: [] };
  },
  methods: {
    trackList: function() {
      this.axios
        .get("/gettrackinglistaxios")
        .then((response) => {
          this.trackings = response.data;
          console.log("==========list==========");
          console.log(response);
          console.log(this.trackings);
          console.log("==========list==========");
        })
        .catch((error) => {
          console.log("에러" + error);
        });
    },
  },
  mounted() {
    this.trackList();
  },
};
</script>
