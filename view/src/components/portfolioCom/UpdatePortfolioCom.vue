<template>
  <div class="container is-max-desktop" style="width: 550px; height: 50px;">
    <div class="notification is-accent" align="center">
      <h1>
        <strong>포트폴리오 수정</strong>
      </h1>
      <br />
      <b-field label="프로젝트 제목" type="" message="" align="left">
        <b-input
          v-model="pfSubtitle"
          maxlength="30"
          size="is-small"
          rounded
        ></b-input>
      </b-field>
      <br />
      <b-field label="프로젝트 기간" type="" message="" align="left">
        <b-input
          v-model="pfDuration"
          maxlength="30"
          size="is-small"
          rounded
        ></b-input>
      </b-field>
      <br />
      <b-field label="프로젝트 설명" type="" message="" align="left">
        <b-input
          v-model="pfDescription"
          maxlength="30"
          size="is-small"
          rounded
        ></b-input>
      </b-field>
      <br />
      <b-field label="프로젝트 참여도" type="" message="" align="left">
        <b-input
          v-model="participation"
          maxlength="30"
          size="is-small"
          rounded
        ></b-input>
      </b-field>
      <br />
      <b-field label="프로젝트 사용언어" align="left">
        <b-select v-model="pfLang" expanded>
          <option value="Java">Java</option>
          <option value="Python">Python</option>
          <option value="GoLang">Go</option>
          <option value="node.js">Node.js</option>
          <option value="Vue.js">Vue.js</option>
          <option value="JavaScript">JavaScript</option>
          <option value="Tensorflow">Tensorflow</option>
        </b-select>
      </b-field>
      <br />
      <b-field label="프로젝트 사용툴" align="left">
        <b-select v-model="pfTools" expanded>
          <option value="Eclipse">Eclipse</option>
          <option value="IntelliJ">IntelliJ</option>
          <option value="VSCode">Visual Studio Code</option>
        </b-select>
      </b-field>
      <br />
      <b-field label="프로젝트 DBMS" align="left">
        <b-select v-model="pfDbms" expanded>
          <option value="Oracle">OracleDB</option>
          <option value="MySQL">MySQL</option>
          <option value="MariaDB">MariaDB</option>
          <option value="MicrosoftSQL"> SQL Server</option>
          <option value="PostgreSQL">PostgreSQL</option>
        </b-select>
      </b-field>
      <br />
      <b-field label="프로젝트 외부링크" type="" message="" align="left">
        <b-input
          v-model="pfLink"
          maxlength="30"
          size="is-small"
          rounded
        ></b-input>
      </b-field>
      <br />
      <b-field label="프로젝트 카테고리" align="left">
        <b-select v-model="pfCategory" expanded>
          <option value="카테고리1">카테고리1</option>
          <option value="카테고리2">카테고리2</option>
          <option value="카테고리3">카테고리3</option>
        </b-select>
      </b-field>
      <br />
      <br />
      <b-button
        type="is-primary"
        outlined
        v-on:click="updatePortfolio()"
        position="is-centered"
        size="is-medium"
        >수정</b-button
      >
    </div>
  </div>
</template>
<script>
import axios from "axios";
import router from "../../router";

export default {
  data() {
    return {
      pfSubtitle: "",
      pfDuration: "",
      pfDescription: "",
      participation: "",
      pfLang: "",
      pfTools: "",
      pfDbms: "",
      pfLink: "",
      pfCategory: ""
    };
  },
  methods: {
    portfolioInfoCall() {
      axios
        .get("/portfolio/" + this.$route.params.pfSeq)
        .then(response => {
          this.pfSubtitle = response.data.pfSubtitle;
          this.pfDuration = response.data.pfDuration;
          this.pfDescription = response.data.pfDescription;
          this.participation = response.data.participation;
          this.pfLang = response.data.pfLang;
          this.pfTools = response.data.pfTools;
          this.pfDbms = response.data.pfDbms;
          this.pfLink = response.data.pfLink;
          this.pfCategory = response.data.pfCategory;
        })
        .catch(e => {
          console.log(e);
          this.errors.push(e);
        });
    },
    updatePortfolio() {
      let formData = new FormData();

      formData.append(
        "userid",
        JSON.parse(sessionStorage.getItem("user")).userId
      );
      formData.append("pfSubtitle", this.pfSubtitle);
      formData.append("pfDuration", this.pfDuration);
      formData.append("pfDescription", this.pfDescription);
      formData.append("participation", this.participation);
      formData.append("pfLang", this.pfLang);
      formData.append("pfTools", this.pfTools);
      formData.append("pfDbms", this.pfDbms);
      formData.append("pfLink", this.pfLink);
      formData.append("pfCategory", this.pfCategory);

      axios
        .put(
          "http://localhost:80/updateportfolio/" + this.$route.params.pfSeq,
          formData
        )
        .then(response => {
          sessionStorage.setItem("portfolio", JSON.stringify(response.data));
          router.push({ name: "portfoliolist" });
        })
        .catch(function() {
          console.log("FAILURE!!");
        });
    }
  },
  mounted() {
    this.portfolioInfoCall();
  }
};
</script>
