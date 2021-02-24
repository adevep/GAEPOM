<template>
  <div id="app" class="container is-max-desktop pt-5">
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link
      href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
      rel="stylesheet"
    />
    <section>
      <div class="hero-body">
        <div class="container has-text-centered">
          <h1 class="title ">
            프로젝트 트레킹 
          </h1>
          <h2 class="subtitle centered">
            프로젝트를 진행 해 보세요.
          </h2>
          <nav
            class="breadcrumb has-dot-separator is-centered"
            aria-label="breadcrumbs"
          > 
            <ul>
              <li><a href="/">홈페이지</a></li>
              <li><a href="/mypage">마이페이지</a></li>
              <li class="is-active">
                <a href="#" aria-current="page">프로젝트 주최</a>
              </li>
            </ul>
          </nav>
        </div>
      </div>
    </section>
    <div class="notification is-accent">
      <h1><strong>프로젝트 트레킹글 수정</strong></h1>
      <section class="mt-6 mb-5">
        <b-field label="프로젝트주제" align="left">
          <b-input v-model="trackInfo.project.pjTitle" maxlength="20" required></b-input>
        </b-field>
        <b-field label="프로젝트를 간단히 설명해 주세요." align="left">
          <b-input
            v-model="trackInfo.project.pjDescription"
            maxlength="20"
            required
          ></b-input>
        </b-field>
        <b-field label="프로젝트 예상 기간" align="left">
          <b-datepicker
            placeholder="클릭해 기간을 선택하세요."
            v-model="arrDuration"
            range
            required
            mobile-native:
            true
          >
          </b-datepicker>
        </b-field>

        <b-field label="프로젝트 사용 툴" align="left">
          <b-checkbox v-model="arrTools" native-value="Eclipse">
            Eclipse
          </b-checkbox>
          <b-checkbox v-model="arrTools" native-value="IntelliJ">
            IntelliJ
          </b-checkbox>
          <b-checkbox v-model="arrTools" native-value="VS Code">
            VS Code
          </b-checkbox>
          <b-checkbox v-model="arrTools" native-value="Jupyter Notebook">
            Jupyter Notebook
          </b-checkbox>
        </b-field>
        <b-field>
          <b-checkbox v-model="arrTools" native-value="Gloo maps"
            >Gloo maps</b-checkbox
          >
          <b-checkbox v-model="arrTools" native-value="Plectica"
            >Plectica</b-checkbox
          >
          <b-checkbox v-model="arrTools" native-value="Trello"
            >Trello</b-checkbox
          >
          <b-checkbox v-model="arrTools" native-value="Axure">Axure</b-checkbox>
        </b-field>
        <b-field>
          <b-checkbox v-model="arrTools" native-value="Oven">Oven</b-checkbox>
          <b-checkbox v-model="arrTools" native-value="Power Mockup"
            >Power Mockup</b-checkbox
          >
          <b-checkbox v-model="arrTools" native-value="Sketch"
            >Sketch</b-checkbox
          >
          <b-checkbox v-model="arrTools" native-value="Figma">Figma</b-checkbox>
        </b-field>
        <b-field>
          <b-checkbox v-model="arrTools" native-value="Photoshop"
            >Photoshop</b-checkbox
          >
          <b-checkbox v-model="arrTools" native-value="Adobe XD"
            >Adobe XD</b-checkbox
          >
          <b-checkbox v-model="arrTools" native-value="Framer X"
            >Framer X</b-checkbox
          >
          <b-checkbox v-model="arrTools" native-value="Illustrator"
            >Illustrator</b-checkbox
          >
        </b-field>
        <b-field label="프로젝트 분류" align="left">
          <b-select v-model="trackInfo.project.pjCategory" expanded>
            <option value="모바일앱">모바일앱 </option>
            <option value="웹앱">웹앱</option>
            <option value="데이터사이언스">데이터사이언스</option>
            <option value="게임개발">게임개발</option>
          </b-select>
        </b-field>
        <b-field label="프로젝트 사용언어" align="left">
          <b-checkbox v-model="arrLang" native-value="Java">
            Java
          </b-checkbox>
          <b-checkbox v-model="arrLang" native-value="Python">
            Python
          </b-checkbox>
          <b-checkbox v-model="arrLang" native-value="C++">
            C++
          </b-checkbox>
          <b-checkbox v-model="arrLang" native-value="JavaScript">
            JavaScript
          </b-checkbox>
          <b-checkbox v-model="arrLang" native-value="Vue">
            Vue
          </b-checkbox>
          <b-checkbox v-model="arrLang" native-value="React">
            React
          </b-checkbox>
        </b-field>
        <b-field label="프로젝트 DBMS" align="left">
          <b-checkbox v-model="arrDbms" native-value="ORACLE">
            ORACLE
          </b-checkbox>
          <b-checkbox v-model="arrDbms" native-value="MySQL">
            MySQL
          </b-checkbox>
          <b-checkbox v-model="arrDbms" native-value="MariaDB">
            MariaDB
          </b-checkbox>
          <b-checkbox v-model="arrDbms" native-value="MongoDB">
            MongoDB
          </b-checkbox>
          <b-checkbox v-model="arrDbms" native-value="PostgreSQL">
            PostgreSQL
          </b-checkbox>
        </b-field>
        <b-field label="개발 단계를 입력해주세요." align="left">
          <b-input v-model="trackInfo.stage" maxlength="20" required></b-input>
        </b-field>
        <b-field label="개발시 이슈를 입력해주세요." align="left">
          <b-input v-model="trackInfo.issue" maxlength="20" required></b-input>
        </b-field>
        <b-field label="프로젝트 결과를 입력해주세요." align="left">
          <b-input v-model="trackInfo.output" maxlength="20" required></b-input>
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
          <b-input v-model="trackInfo.trackLink" maxlength="20" required></b-input>
        </b-field>
      </section>
      <b-button
        type="is-primary"
        outlined
        @click="updateTracking"
        position="is-centered"
        size="is-large"
        >프로젝트 트레킹 수정</b-button
      >
    </div>
    <div class="container is-max-desktop pt-5"></div>
  </div>
</template>

<script>

import { mapState } from "vuex";
export default {
  name: "TrackingUpdate",
  data() {
    return {
      trackInfo: [],
      project: [],
      arrDuration: [],
      arrLang: [],
      arrTools: [],
      arrDbms: [],
    };
  },
  methods: {
    updateTracking: function() {
      this.project.pjTools = this.arrTools.join();
      this.project.pjLang = this.arrLang.join();
      this.project.pjDbms = this.arrDbms.join();
 
      let myDate = new Date(Date.parse(this.arrDuration[0]));
      let myDate2 = new Date(Date.parse(this.arrDuration[1]));
      let date1 =
        myDate.getFullYear() +
        "." +
        ("0" + (myDate.getMonth() + 1)).slice(-2) +
        "." +
        ("0" + myDate.getDate()).slice(-2);
      let date2 =
        myDate2.getFullYear() +
        "." +
        ("0" + (myDate2.getMonth() + 1)).slice(-2) +
        "." +
        ("0" + myDate2.getDate()).slice(-2);

      let date3 = [];

      date3.push(date1);
      date3.push(date2);

      this.arrDuration = date3.join("-");
      this.project.pjDuration = this.arrDuration;

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
      formData.append("phoneNum", this.project.userId.phoneNum);
      formData.append("address", this.project.userId.address);
      formData.append("position", this.project.userId.position);
      formData.append("userImage", this.project.userId.userImage)
      formData.append("stack", this.project.userId.stack);
      this.axios
        .put("/track/updateprojecttracking", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((response) => {
          this.trackInfo = response.data;
          this.$router.push({
            name: "ProjectTrackingDetail",
            params: { track: this.trackInfo },
          });
        })
        .catch((ex) => {
          console.warn("ERROR!!!!! : ", ex);
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
    this.trackInfo = this.$route.params.trackSeq;
    this.project = this.trackInfo.project;
    
     this.arrLang = this.project.pjLang.split(",");
          this.arrTools = this.project.pjTools.split(",");
          this.arrDbms = this.project.pjDbms.split(",");
          this.arrDuration[0] = new Date(this.project.pjDuration.split("-")[0]);
          this.arrDuration[1] = new Date(this.project.pjDuration.split("-")[1]);
  },
};
</script>
