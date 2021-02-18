<template>
  <div class="UpdateTracking">
      <h2>UpdateTracking</h2>
        <center>
             <table border="1">
                 <tr>
                     <td>Title</td>
                     <td><input type="text" v-model="trackInfo.project.pjTitle" placeholder=""/></td>
                 </tr>
                 <tr>
                     <td>Duration</td>
                     <td><input type="text" v-model="trackInfo.project.pjDuration" placeholder=""/></td>
                 </tr>
                 <tr>
                     <td>Tools</td>
                     <td><input type="text" v-model="trackInfo.project.pjTools" placeholder=""/></td>
                 </tr>
                 <tr>
                     <td>Category</td>
                     <td><input type="text" v-model="trackInfo.project.pjCategory" placeholder=""/></td>
                 </tr>
                 <tr>
                     <td>Language</td>
                     <td><input type="text" v-model="trackInfo.project.pjLang" placeholder=""/></td>
                 </tr>
                 <tr>
                     <td>DBMS</td>
                     <td><input type="text" v-model="trackInfo.project.pjDbms" placeholder=""/></td>
                 </tr>
                 <tr>
                     <td>Description</td>
                     <td><input type="text" v-model="trackInfo.project.pjDescription" placeholder=""/></td>
                 </tr>
                 <tr>
                     <td>Stage</td>
                     <td><input type="text" v-model="trackInfo.stage" placeholder=""/></td>
                 </tr>
                 <tr>
                     <td>Issue</td>
                     <td><input type="text" v-model="trackInfo.issue" placeholder=""/></td>
                 </tr>
                 <tr>
                     <td>Output</td>
                     <td><input type="text" v-model="trackInfo.output" placeholder=""/></td>
                 </tr>
                 <tr>
                     <td>Image</td>
                     <td><input type="text" v-model="trackInfo.trackImage" placeholder=""/></td>
                 </tr>
            </table>
            <br>
                 <button @click="updateTracking">수정</button>
             <!-- <router-link tag ="button" :to="{name: 'ProjectTrackingDetail', params: {trackSeq: trackInfo.trackSeq}}">수정<router-link> -->
        </center>
  </div>
</template>
<script>
// import ProjectTrackingDetail from '../../views/ProjectTrackingDetail.vue';
export default {
    name: "TrackingUpdate",
    data() {
        return{
            trackInfo: []
        }
    },
    methods: {
      updateTracking : function() {
          this.axios.put('/updateprojecttracking', this.trackInfo
            ).then(response => {
                console.log("==========update==========")
                console.warn(response.data)
                console.log("==========update==========")
                // 페이지 이동
                this.$router.push({ name: 'ProjectTrackingDetail', params: {track: this.trackInfo}})
            }).catch((ex) => {
                console.warn("ERROR!!!!! : ",ex)
            });
        },
    },
    mounted() {
        this.trackInfo = this.$route.params.trackSeq
    }
}
 </script>