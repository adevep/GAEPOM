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
                     <b-field
                            label="Current UserImage"
                            align="left"
                            message="<주의> 이미지 재등록 없을시 기본 이미지로 변경"
                        >
                            <img :src="imgURL + trackInfo.trackImage" alt="" width="500" />
                        </b-field>
                        <br />
                        <b-field align="left">
                            <input
                            type="file"
                            id="file"
                            ref="file"
                            placeholder="Add tracking picture"
                            v-on:change="handleFileUpload()"
                            />
                        </b-field>
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
import { mapState } from "vuex";
export default {
    
    name: "TrackingUpdate",
    data() {
        return{
            trackInfo: [],
            project: [],
        }
    },
    methods: {
      updateTracking : function() {
          let formData = new FormData();
          formData.append("trackSeq", this.trackInfo.trackSeq);
          formData.append("stage", this.trackInfo.stage);
          formData.append("issue", this.trackInfo.issue);
          formData.append("output", this.trackInfo.output);
          formData.append("file", this.trackInfo.trackImage);
          formData.append("trackLink", this.trackInfo.trackLink);
         
          formData.append("pjSeq", this.project.pjSeq);
          formData.append("pjTitle", this.project.pjTitle);
          formData.append("pjDescription", this.project.pjDescription);
          formData.append("pjDuration", this.project.pjDuration);
          formData.append("pjTools", this.project.pjTools);
          formData.append("pjLang", this.project.pjLang);
          formData.append("pjDbms", this.project.pjDbms);
          formData.append("pjCategory", this.project.pjCategory);

          formData.append("userId", this.project.userId.userId);
          formData.append("password", this.project.userId.password);
          formData.append("name", this.project.userId.name);
          formData.append("age", this.project.userId.age);
          formData.append("email", this.project.userId.email);
          formData.append("phoneNum", this.project.userId.phonenum);
          formData.append("address", this.project.userId.address);
          formData.append("position", this.project.userId.position);
          formData.append("stacklist", this.project.userId.stacklist);

          console.log(formData)
          this.axios.put('/updateprojecttracking', formData , {
          headers: {
            "Content-Type": "multipart/form-data",
          },
            }).then(response => {
                console.log("==========update==========")
                console.warn(response.data)
                console.log("==========update==========")
                // 페이지 이동
                this.trackInfo = response.data
                this.$router.push({ name: 'ProjectTrackingDetail', params: {track: this.trackInfo}})
            }).catch((ex) => {
                console.warn("ERROR!!!!! : ",ex)
            });
        },
         handleFileUpload() {
          this.trackInfo.trackImage = this.$refs.file.files[0];
        },
    },
    computed: {
    ...mapState(["imgURL"]),
  },
    mounted() {
        this.trackInfo = this.$route.params.trackSeq
        this.project = this.trackInfo.project
        console.log(this.trackInfo)
    }
}
 </script>
