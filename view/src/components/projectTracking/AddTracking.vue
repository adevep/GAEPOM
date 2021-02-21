<template>
  <div class="container is-max-desktop">
    <div class="notification is-accent">
      <h1><strong>프로젝트 트레킹글 만들기</strong></h1>
      <section class="mt-6 mb-5">
        <b-field label="프로젝트주제" align="left">
          <b-input v-model="project.pjTitle" maxlength="20" required></b-input>
        </b-field>
        <b-field label="프로젝트를 간단히 설명해 주세요." align="left">
          <b-input v-model="project.pjDescription" maxlength="20" required></b-input>
        </b-field>
        <b-field label="프로젝트 예상 기간" align="left">
          <b-datepicker
            placeholder="클릭해 기간을 선택하세요."
            v-model="pjDu"
            range
            required
            mobile-native: true
          >
          </b-datepicker>
        </b-field>
        
        <b-field label="프로젝트 사용 툴" align="left">
          <b-checkbox v-model="project.pjTools" native-value="Eclipse">
            Eclipse
          </b-checkbox>
          <b-checkbox v-model="project.pjTools" native-value="IntelliJ">
            IntelliJ
          </b-checkbox>
          <b-checkbox v-model="project.pjTools" native-value="VS Code">
            VS Code
          </b-checkbox>
          <b-checkbox v-model="project.pjTools" native-value="Jupyter Notebook">
            Jupyter Notebook
          </b-checkbox>
        </b-field>
        <b-field label="프로젝트 분류" align="left">
          <b-select v-model="project.pjCategory" expanded>
            <option value="모바일앱">모바일앱 </option>
            <option value="웹앱">웹앱</option>
            <option value="데이터사이언스">데이터사이언스</option>
            <option value="게임개발">게임개발</option>
          </b-select>
        </b-field>
        <b-field label="프로젝트 사용언어" align="left">
          <b-checkbox v-model="project.pjLang" native-value="Java">
            Java
          </b-checkbox>
          <b-checkbox v-model="project.pjLang" native-value="Python">
            Python
          </b-checkbox>
          <b-checkbox v-model="project.pjLang" native-value="C++">
            C++
          </b-checkbox>
          <b-checkbox v-model="project.pjLang" native-value="JavaScript">
            JavaScript
          </b-checkbox>
          <b-checkbox v-model="project.pjLang" native-value="Vue">
            Vue
          </b-checkbox>
          <b-checkbox v-model="project.pjLang" native-value="React">
            React
          </b-checkbox>
        </b-field>
        <b-field label="프로젝트 DBMS" align="left">
          <b-checkbox v-model="project.pjDbms" native-value="ORACLE">
            ORACLE
          </b-checkbox>
          <b-checkbox v-model="project.pjDbms" native-value="MySQL">
            MySQL
          </b-checkbox>
          <b-checkbox v-model="project.pjDbms" native-value="MariaDB">
            MariaDB
          </b-checkbox>
          <b-checkbox v-model="project.pjDbms" native-value="MongoDB">
            MongoDB
          </b-checkbox>
          <b-checkbox v-model="project.pjDbms" native-value="PostgreSQL">
            PostgreSQL
          </b-checkbox>
        </b-field>
        <b-field label="개발 단계를 입력해주세요." align="left">
          <b-input v-model="stage" maxlength="20" required></b-input>
        </b-field>
        <b-field label="개발시 이슈를 입력해주세요." align="left">
          <b-input v-model="issue" maxlength="20" required></b-input>
        </b-field>
        <b-field label="프로젝트 결과를 입력해주세요." align="left">
          <b-input v-model="output" maxlength="20" required></b-input>
        </b-field>
        <b-field label="이미지를 첨부해주세요" align="left">
          <input
            type="file"
            id="file"
            ref="file"
            v-on:change="handleFileUpload()"
          />
        </b-field>
        <b-field label="git주소 등 링크를 입력해주세요." align="left">
          <b-input v-model="trackLink" maxlength="20" required></b-input>
        </b-field>
      </section>
      <b-button
        type="is-primary"
        outlined
        @click="addTracking"
        position="is-centered"
        size="is-large"
        >프로젝트 트레킹 생성</b-button
      >
    </div>
    <div class="container is-max-desktop pt-5"></div>
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
            file: '',
            trackLink:'',
            pjDu: [],
            // pjLang: [this.project.pjLang]
        }
    },
    methods : {
        addTracking : function() {
          this.project.pjDuration = this.pjDu.join('-')

          console.log(this.file)

          let formData = new FormData();
          formData.append("stage", this.stage);
          formData.append("issue", this.issue);
          formData.append("output", this.output);
          formData.append("file", this.file);
          formData.append("trackLink", this.trackLink);
         
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
  //stage:this.stage, issue:this.issue, output:this.output, file:this.file, trackLink:this.trackLink, project: this.project,
          // this.axios.post('/insertprojecttracking', { stage:this.stage, issue:this.issue, output:this.output, file:this.file, trackLink:this.trackLink, project: this.project, headers: {
          //   "Content-Type": "multipart/form-data"
          // }
          this.axios.post('/insertprojecttracking', formData, {project:this.project}, {
          headers: {
            "Content-Type": "multipart/form-data"
          }
          }).then(response => {
                console.log("==========add==========")
                console.warn(response)
                console.log("==========add==========")
                // 페이지 이동
                this.$router.push(ProjectTracking)

            }).catch((ex) => {
                console.warn("ERROR!!!!! : ",ex)
            });
        },
       
        showProject: function() {
          this.axios.get('/project/getproject', {
            params: {
              projectId: this.$route.params.pjseq
            }
          }).then(response => {
            this.project = response.data
            this.pjDu[0] = new Date(this.project.pjDuration.split('-')[0])
            this.pjDu[1] = new Date(this.project.pjDuration.split('-')[1])
            console.log(this.project)
          }).catch(error => {
              console.log("에러" + error);
          })
        },
        
        handleFileUpload() {
          console.log("upload--")
          console.log(this.file)
          this.file = this.$refs.file.files[0];
          console.log(this.file)
        },
        
    },
    mounted () {
      this.showProject();
    }
}
</script>