<template>
  <div class="AddTracking">
    <input type="text" v-model="project.pjTitle" placeholder="프로젝트 제목을 입력합니다."/><br>
    <input type="text" v-model="project.pjDescription" placeholder="프로젝트 설명을 입력합니다."/><br>
    <input type="text" v-model="project.pjDuration" placeholder="yyyy-mm-dd~yyyy-mm-dd"/><br>
    <input type="text" v-model="project.pjTools" placeholder="사용하는 툴을 입력합니다."/><br>
    <input type="text" v-model="project.pjLang" placeholder="사용하는 언어를 입력합니다."/><br>
    <input type="text" v-model="project.pjDbms" placeholder="사용하는 DB를 입력합니다."/><br>
    <input type="text" v-model="stage" placeholder="개발 단계를 입력합니다."/><br>
    <input type="text" v-model="issue" placeholder="개발시 이슈를 입력합니다."/><br>
    <input type="text" v-model="output" placeholder="프로젝트 결과를 입력합니다."/><br>
    <input type="text" v-model="trackImage" placeholder="이미지를 첨부합니다."/><br>
    <input type="text" v-model="trackLink" placeholder="git주소 등 링크를 입력합니다."/><br>
    <button @click="addTracking">프로젝트 트레킹 생성</button>
  </div>
</template>
<script>
import ProjectTracking from '../../views/ProjectTracking.vue';

export default {
  name: "AddTracking",
  data() {
        return {
            project: [],
            stage: '',
            issue: '',
            output: '',
            trackImage: '',
            trackLink:''
        }
    },
    methods : {
        addTracking : function() {
          this.axios.post('/insertprojecttracking', 
              { stage:this.stage, issue:this.issue, output:this.output, trackImage:this.trackImage, trackLink:this.trackLink, project: this.project }
            ).then(response => {
                console.log("==========add==========")
                console.warn(response)
                console.warn(response.data)
                console.log("==========add==========")
                this.success();
                // 페이지 이동
                this.$router.push(ProjectTracking)

            }).catch((ex) => {
                this.danger();
                console.warn("ERROR!!!!! : ",ex)
            });
        },
        showProject: function() {
          this.axios.get('/project/getproject', {
            params: {
              projectId: 2
            }
          }).then(response => {
            this.project = response.data
          }).catch(error => {
              console.log("에러" + error);
          })
        },
         success() {
                this.$buefy.notification.open({
                    message: '트래킹 작성이 완료되었습니다.',
                    type: 'is-success',
                    position: 'is-bottom-right',
                })
            },
           danger() {
                this.$buefy.notification.open({
                    message: `트래킹 내용을 정확히 입력해주세요.`,
                    type: 'is-danger',
                    position: 'is-bottom-right',
                })
            },
    },
    mounted () {
      this.showProject();
    }
}
</script>