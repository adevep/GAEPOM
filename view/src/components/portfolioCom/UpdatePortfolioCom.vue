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
          <option
            value="모바일앱"
            >모바일앱
          </option>
          <option
            value="웹앱"
            >웹앱
          </option>
          <option
            value="데이터사이언스"
            >데이터사이언스
          </option>
          <option
            value="게임개발"
            >게임개발
          </option>
        </b-select>
      </b-field>
      <br />
    <b-field label="프로젝트 사용언어" align="left">
        <b-checkbox v-model="pfLang" native-value="Java">
          Java
        </b-checkbox>
        <b-checkbox v-model="pfLang" native-value="Python">
          Python
        </b-checkbox>
        <b-checkbox v-model="pfLang" native-value="C++">
          C++
        </b-checkbox>
        <b-checkbox v-model="pfLang" native-value="JavaScript">
          JavaScript
        </b-checkbox>
        <b-checkbox v-model="pfLang" native-value="Vue">
          Vue
        </b-checkbox>
        <b-checkbox v-model="pfLang" native-value="React">
          React
        </b-checkbox>
      </b-field>
      <br />
     <b-field label="프로젝트 사용툴" align="left">
        <b-checkbox v-model="pfTools" native-value="Eclipse">
          Eclipse
        </b-checkbox>
        <b-checkbox v-model="pfTools" native-value="IntelliJ">
          IntelliJ
        </b-checkbox>
        <b-checkbox v-model="pfTools" native-value="VS Code">
          VS Code
        </b-checkbox>
        <b-checkbox v-model="pfTools" native-value="Jupyter Notebook">
          Jupyter Notebook
        </b-checkbox>
      </b-field>
      <br />
      <b-field label="프로젝트 DBMS" align="left">
        <b-checkbox v-model="pfDbms" native-value="ORACLE">
          ORACLE
        </b-checkbox>
        <b-checkbox v-model="pfDbms" native-value="MySQL">
          MySQL
        </b-checkbox>
        <b-checkbox v-model="pfDbms" native-value="MariaDB">
          MariaDB
        </b-checkbox>
        <b-checkbox v-model="pfDbms" native-value="MongoDB">
          MongoDB
        </b-checkbox>
        <b-checkbox v-model="pfDbms" native-value="PostgreSQL">
          PostgreSQL
        </b-checkbox>
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
      pfLink: "",
      pfCategory: "",
      pfLang: [],
      pfTools: [],
      pfDbms: []
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
           this.pfLink = response.data.pfLink;
          this.pfCategory = response.data.pfCategory;
          this.pfLang = response.data.pfLang.split(',');
          this.pfTools = response.data.pfTools.split(',');
          this.pfDbms = response.data.pfDbms.split(',');
        })
        .catch(e => {
          console.log(e);
        });
    },
    updatePortfolio() {
      let formData = new FormData();

      formData.append("pfSubtitle", this.pfSubtitle);
      formData.append("pfDuration", this.pfDuration);
      formData.append("pfDescription", this.pfDescription);
      formData.append("participation", this.participation);
      formData.append("pfLink", this.pfLink);
      formData.append("pfCategory", this.pfCategory);
      formData.append("pflanglist", this.pfLang);
      formData.append("pftoolslist", this.pfTools);
      formData.append("pfdbmslist", this.pfDbms);

      axios
        .put(
          "http://localhost:80/updateportfolio/" + this.$route.params.pfSeq,
          formData
        )
        .then(() => {
          router.push({ name: "mypage" });
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
