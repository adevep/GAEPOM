<template>
  <ValidationObserver
    ref="observer"
    v-slot="{ handleSubmit }"
    autocomplete="off"
  >
    <div class="container is-max-desktop">
      <div class="notification is-accent">
        <h1><strong>프로젝트 모집글 만들기</strong></h1>
        <section class="mt-6 mb-5">
          <b-field label="프로젝트주제" align="left">
            <BInputWithValidation
              v-model="pjTitle"
              maxlength="20"
              rules="required"
            />
          </b-field>
          <b-field label="프로젝트를 간단히 설명해 주세요." align="left">
            <b-input v-model="pjDescription" maxlength="20" required></b-input>
          </b-field>
          <b-field label="프로젝트 예상 기간" align="left">
            <b-datepicker
              placeholder="클릭해 기간을 선택하세요."
              v-model="pjDuration"
              range
              required
              mobile-native:
              true
            >
            </b-datepicker>
          </b-field>
          <b-field label="지역" align="left">
            <b-select v-model="location" expanded>
              <option value="서울">서울</option>
              <option value="경기">경기</option>
              <option value="인천">인천</option>
              <option value="대전">대전</option>
              <option value="광주">광주</option>
              <option value="부산">부산</option>
              <option value="제주">제주</option>
              <option value="울산">울산</option>
              <option value="대구">대구</option>
              <option value="세종">세종</option>
              <option value="충북">충북</option>
              <option value="충남">충남</option>
              <option value="전북">전북</option>
              <option value="전남">전남</option>
              <option value="경북">경북</option>
              <option value="경남">경남</option>
            </b-select>
          </b-field>
          <b-field label="프로젝트 사용 툴" align="left">
            <b-checkbox v-model="pjTools" native-value="Eclipse">
              Eclipse
            </b-checkbox>
            <b-checkbox v-model="pjTools" native-value="IntelliJ">
              IntelliJ
            </b-checkbox>
            <b-checkbox v-model="pjTools" native-value="VS Code">
              VS Code
            </b-checkbox>
            <b-checkbox v-model="pjTools" native-value="Jupyter Notebook">
              Jupyter Notebook
            </b-checkbox>
          </b-field>
          <b-field label="프로젝트 분류" align="left">
            <b-select v-model="pjCategory" expanded>
              <option value="모바일앱">모바일앱 </option>
              <option value="웹앱">웹앱</option>
              <option value="데이터사이언스">데이터사이언스</option>
              <option value="게임개발">게임개발</option>
            </b-select>
          </b-field>
          <b-field label="프로젝트 사용언어" align="left">
            <b-checkbox v-model="pjLang" native-value="Java">
              Java
            </b-checkbox>
            <b-checkbox v-model="pjLang" native-value="Python">
              Python
            </b-checkbox>
            <b-checkbox v-model="pjLang" native-value="C++">
              C++
            </b-checkbox>
            <b-checkbox v-model="pjLang" native-value="JavaScript">
              JavaScript
            </b-checkbox>
            <b-checkbox v-model="pjLang" native-value="Vue">
              Vue
            </b-checkbox>
            <b-checkbox v-model="pjLang" native-value="React">
              React
            </b-checkbox>
          </b-field>
          <b-field label="프로젝트 DBMS" align="left">
            <b-checkbox v-model="pjDbms" native-value="ORACLE">
              ORACLE
            </b-checkbox>
            <b-checkbox v-model="pjDbms" native-value="MySQL">
              MySQL
            </b-checkbox>
            <b-checkbox v-model="pjDbms" native-value="MariaDB">
              MariaDB
            </b-checkbox>
            <b-checkbox v-model="pjDbms" native-value="MongoDB">
              MongoDB
            </b-checkbox>
            <b-checkbox v-model="pjDbms" native-value="PostgreSQL">
              PostgreSQL
            </b-checkbox>
          </b-field>
          <b-field label="모집 인원" align="left">
            <b-input
              v-model="needNum"
              placeholder="최소 1명이상"
              type="number"
              min="1"
              max="100"
              required
            >
            </b-input>
          </b-field>
          <b-field label="모집 직무" align="left">
            <b-checkbox v-model="needPosi" native-value="개발자">
              개발자
            </b-checkbox>
            <b-checkbox v-model="needPosi" native-value="기획자">
              기획자
            </b-checkbox>
            <b-checkbox v-model="needPosi" native-value="디자이너">
              디자이너
            </b-checkbox>
            <b-checkbox v-model="needPosi" native-value="퍼블리셔">
              퍼블리셔
            </b-checkbox>
          </b-field>
          <b-field label="우대사항" align="left">
            <b-input v-model="preference" maxlength="30" required></b-input>
          </b-field>
          <b-field label="모집 기간" align="left">
            <b-datepicker
              placeholder="클릭해 기간을 선택하세요."
              v-model="recDuration"
              range
              mobile-native:
              true
            >
            </b-datepicker>
          </b-field>
        </section>
        <b-button
          type="is-primary"
          outlined
          @click="handleSubmit(addProjectRec)"
          position="is-centered"
          size="is-large"
          >모집글 생성완료</b-button
        >
      </div>
      <div class="container is-max-desktop pt-5"></div>
    </div>
  </ValidationObserver>
</template>
<script>
/**
 * *  vee-validation 사용
 * ? BSeletWithValidation | Buefy의 <b-select>와 동일 + 유효성 검증 로직 구현
 * ? BInputWithValidation | Buefy의 <b-input>과 동일 + 유효성 검증 로직 구현
 * ? BCheckboxesWithValidation | Buefy의 <b-checkbox>와 동일 + 유효성 검증 로직 구현
 */
import { ValidationObserver } from "vee-validate";
import BInputWithValidation from "./inputs/BInputWithValidation";

import http from "../http-common";
export default {
  name: "AddProject",
  components: {
    ValidationObserver,
    BInputWithValidation
  },
  data() {
    return {
      loginUser: JSON.parse(sessionStorage.getItem("user")).userId,
      userId: "",
      pjTitle: "",
      pjDescription: "",
      pjDuration: [],
      pjTools: [],
      pjLang: [],
      pjDbms: [],
      needNum: "",
      needPosi: [],
      location: "",
      preference: "",
      recStatus: 0,
      recDuration: [],
      recSeq: "",
      pjCategory: ""
    };
  },
  methods: {
    addProjectRec: function() {
      this.needPosi = this.needPosi.join();
      this.recDuration = this.recDuration.join("-");
      http
        .post("/recruit/createrec?userId=" + this.loginUser, {
          needNum: this.needNum,
          needPosi: this.needPosi,
          location: this.location,
          preference: this.preference,
          recDuration: this.recDuration
        })
        .then(response => {
          console.log("==========add==========");
          console.warn(response);
          console.warn(response.data);
          this.recSeq = response.data.recSeq;
          console.log("==========add==========");
          console.log(this.recSeq);
          // 페이지 이동
          //this.$router.push(Project)
          this.pjTools = this.pjTools.join();
          this.pjLang = this.pjLang.join();
          this.pjDbms = this.pjDbms.join();
          this.pjDuration = this.pjDuration.join("-");
          const data = {
            userId: { userId: this.loginUser },
            pjTitle: this.pjTitle,
            pjDescription: this.pjDescription,
            pjDuration: this.pjDuration,
            pjTools: this.pjTools,
            pjLang: this.pjLang,
            pjDbms: this.pjDbms,
            pjCategory: this.pjCategory,
            recSeq: { recSeq: this.recSeq }
          };
          http
            .post("/recruit/createpj?userId=" + this.loginUser, data)
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
    //this.showProject();
  }
};
</script>
