<template>
  <div class="container is-max-desktop">
    <div class="notification is-accent">
      <h1><strong>프로젝트 모집글 만들기</strong></h1>
      <section class="mt-6 mb-5">
        <b-field label="아이디" align="left">
          <b-input v-model="userId" maxlength="30"></b-input>
        </b-field>
        <b-field label="프로젝트주제" align="left">
          <b-input v-model="pjTitle" maxlength="30"></b-input>
        </b-field>
        <b-field label="프로젝트를 간단히 설명해 주세요." align="left">
          <b-input v-model="pjDescription" maxlength="30"></b-input>
        </b-field>
        <b-field label="프로젝트 기간 (예상)" align="left">
          <b-input v-model="pjDuration" maxlength="30"></b-input>
        </b-field>
        <b-field label="지역" align="left">
          <b-input v-model="location" maxlength="30"></b-input>
        </b-field>
        <b-field label="사용 툴" align="left">
          <b-input v-model="pjTools" maxlength="30"></b-input>
        </b-field>
        <b-field label="프로젝트 분류" align="left">
          <b-input v-model="pjCategory" maxlength="30"></b-input>
        </b-field>
        <b-field label="사용 언어" align="left">
          <b-input v-model="pjLang" maxlength="30"></b-input>
        </b-field>
        <b-field label="DBMS" align="left">
          <b-input v-model="pjDbms" maxlength="30"></b-input>
        </b-field>
        <b-field label="모집 인원" align="left">
          <b-input
            v-model="needNum"
            placeholder="최소 1명이상"
            type="number"
            min="1"
            max="100"
          >
          </b-input>
        </b-field>
        <b-field label="모집 직무" align="left">
          <b-input v-model="needPosi" maxlength="30"></b-input>
        </b-field>
        <b-field label="우대사항" align="left">
          <b-input v-model="preference" maxlength="30"></b-input>
        </b-field>
        <b-field label="모집 기간" align="left">
          <b-input v-model="recDuratio" maxlength="30"></b-input>
        </b-field>
      </section>
      <b-button
        type="is-primary"
        outlined
        @click="updateProjectRec"
        position="is-centered"
        size="is-large"
        >모집글 수정하기</b-button
      >
    </div>
    <div class="container is-max-desktop pt-5"></div>
  </div>
</template>
<script>
import http from "../http-common";

export default {
  name: "UpdateProject",
  props: ["pjNum"],
  data() {
    const all = [];
    const allPjs = [];
    return {
      loginUser: JSON.parse(sessionStorage.getItem("user")).userId,
      all,
      allPjs,
      userId: "",
      pjTitle: "",
      pjDescription: "",
      pjDuration: "",
      pjTools: "",
      pjLang: "",
      pjDbms: "",
      needNum: "",
      needPosi: "",
      location: "",
      preference: "",
      recStatus: 0,
      recDuration: "",
      recSeq: ""
      //recSeq: ""
    };
  },
  methods: {
    getProject() {
      http
        .get("/recruit/gettotalpj/" + this.pjNum + "?userId=" + this.loginUser)
        .then(response => {
          this.all = response.data;
          var array = [];
          this.all.forEach(function(element) {
            var allPj;
            allPj = [element[0], element[1]].reduce(function(r, o) {
              Object.keys(o).forEach(function(k) {
                r[k] = o[k];
              });
              return r;
            }, {});
            array.push(allPj);
          });
          this.allPjs = array;
          console.log(this.allPjs);

          this.userId = this.loginUser;
          this.pjTitle = this.allPjs.pjTitle;
          this.pjDescription = this.allPjs.pjDescription;
          this.pjDuration = this.allPjs.pjDuration;
          this.pjTools = this.allPjs.pjTools;
          this.pjLang = this.allPjs.pjLang;
          this.pjDbms = this.allPjs.pjDbms;
          this.needNum = this.allPjs.needNum;
          this.needPosi = this.allPjs.needPosi;
          this.location = this.allPjs.location;
          this.preference = this.allPjs.preference;
          this.recStatus = this.allPjs.recSeq.recStatus;
          this.recDuration = this.allPjs.recDuration;
          this.recSeq = this.allPjs.recSeq;
        })
        .catch(e => {
          console.log(e);
          this.errors.push(e);
        });
    },
    updateProjectRec: function() {
      http
        .put(
          "/recruit/updaterec/" + this.recSeq + "?userId=" + this.loginUser,
          {
            needNum: this.needNum,
            needPosi: this.needPosi,
            location: this.location,
            preference: this.preference,
            recDuration: this.recDuration
          }
        )
        .then(response => {
          console.log("==========add==========");
          console.warn(response);
          console.warn(response.data);
          this.recSeq = response.data.recSeq;
          console.log("==========add==========");
          console.log(this.recSeq);
          // 페이지 이동
          //this.$router.push(Project)
          const data = {
            userId: { userId: this.userId },
            pjTitle: this.pjTitle,
            pjDescription: this.pjDescription,
            pjDuration: this.pjDuration,
            pjTools: this.pjTools,
            pjLang: this.pjLang,
            pjDbms: this.pjDbms,
            recSeq: { recSeq: this.recSeq }
          };
          http
            .put(
              "/recruit/updatepj/" + this.recSeq + "?userId=" + this.loginUser,
              data
            )
            .then(response => {
              console.log("==========add==========");
              console.warn(response);
              console.warn(response.data);
              console.log("==========add==========");
              // 페이지 이동
              //this.$router.push(Project)
            })
            .catch(ex => {
              console.warn("ERROR!!!!! : ", ex);
            });
        })
        .catch(ex => {
          console.warn("ERROR!!!!! : ", ex);
        });
    }
    // showProject: function() {
    //   this.axios.get('/getproject', {
    //     params: {
    //       projectId: 2
    //     }
    //   }).then(response => {
    //     this.project = response.data
    //   }).catch(error => {
    //       console.log("에러" + error);
    //   })
    // }
  },
  mounted() {
    this.getProject();
  }
};
</script>
