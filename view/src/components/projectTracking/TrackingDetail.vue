<template>
    <div class= "TrackingDetail">
        <h2>Tracking Detail</h2>
        <center>
             <table border="1">
                <tr>
                     <td>Title</td> 
                     <td>{{trackInfo.project.pjTitle}}</td>
                </tr>
                <tr>
                     <td>Duration</td> 
                     <td>{{trackInfo.project.pjDuration}}</td>
                </tr>
                <tr>
                     <td>Tools</td> 
                     <td>{{trackInfo.project.pjTools}}</td>
                </tr>
                <tr>
                     <td>Category</td> 
                     <td>{{trackInfo.project.pjCategory}}</td>
                </tr>
                <tr>
                     <td>Language</td> 
                     <td>{{trackInfo.project.pjLang}}</td>
                </tr>
                <tr>
                     <td>DBMS</td> 
                     <td>{{trackInfo.project.pjDbms}}</td>
                </tr>
                <!-- <tr>
                     <td>applications</td> 
                     <td>{{value.project.pjTitle}}</td>
                </tr> -->
                <tr>
                     <td>Description</td> 
                     <td>{{trackInfo.project.pjDescription}}</td>
                </tr>
                <tr>
                     <td>Stage</td> 
                     <td>{{trackInfo.stage}}</td>
                </tr>
                <tr>
                     <td>Issue</td> 
                     <td>{{trackInfo.issue}}</td>
                </tr>
                <tr>
                     <td>Output</td> 
                     <td>{{trackInfo.output}}</td>
                </tr>
                <tr>
                     <td>Image</td> 
                     <td>{{trackInfo.trackImage}}</td>
                </tr>
            </table><br>
            <button @click="deleteTracking">삭제</button>
            <router-link tag ="button" :to="{name: 'ProjectTrackingUpdate', params: {trackSeq: trackInfo}}">수정</router-link>
        </center>
    </div>
</template>
<script>
import ProjectTracking from '../../views/ProjectTracking.vue'
export default {
    name: "TrackingDetail",
    data() {
        return{trackInfo: []}
    },
    methods: {
          tracking: function() {
               console.log("이곳은 detail처음")
            this.axios.get('/getprojecttracking', this.trackInfo.trackSeq).then(response => {
                this.trackInfo = response.data
                console.log("--------detail---------")
                console.log(response)
                console.log(this.trackInfo)
                console.log("--------detail---------")
            }).catch(error => {
                console.log("에러" + error);
            })
          },
          deleteTracking: function() {
           console.log("1")
           this.axios.delete('/deleteprojecttracking',  {
                params: {
                    trackSeq: this.trackInfo.trackSeq
                }
           }).then(response => {
                console.log("2")
                console.log(response)
                // 페이지 이동
                this.$router.push(ProjectTracking)
            }).catch((ex) => {
                console.warn("ERROR!!!!! : ",ex)
            });
        },
    },
    mounted () {
          this.trackInfo = this.$route.params.track
          this.tracking();
    }
}
</script>