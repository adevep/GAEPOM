<template>
  <div class="TrackingList">
    <router-link tag="a" to="/projectTrackingInsert">이동</router-link>
    <h4>{{ msg }}</h4>
    <table border="1">
       <tr>
              <th>제목</th>
              <th>카테고리</th>
              <th>기간</th>
              <th>이미지</th>
             
          </tr>
          <tr v-for="(value, key) in trackings" v-bind:key="key">
            <!-- "{ name: 'user', params: { userId: 123 }}"-->
              <!-- <router-link tag="td" to="/projectTrackingDetail">{{value.project.pjTitle}}</router-link> -->
              <router-link tag="td" :to="{name: 'ProjectTrackingDetail', params: {track: value}}">{{value.project.pjTitle}}</router-link>
              <td>{{value.project.pjCategory}}</td>
              <td>{{value.project.pjDuration}}</td>
              <td>{{value.trackImage}}</td>
          </tr>
           
    </table>
  </div>
</template>

<script>

// @ is an alias to /src
export default {
  name: "TrackingList",
  props: {
    msg: String
  },
  data() {
    return {trackings: []}
  },
  methods: {
    trackList: function() {
      this.axios.get('/gettrackinglistaxios').then(response => {
          this.trackings = response.data
          console.log("==========list==========")
          console.log(response)
          console.log(this.trackings)
          console.log("==========list==========")
      }).catch(error => {
          console.log("에러" + error);
      })
    },
    
    
  },
  mounted() {
      this.trackList();
  }
 
  
};
</script>
