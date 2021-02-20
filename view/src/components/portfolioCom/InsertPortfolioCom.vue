<template>
  <div class="container is-max-desktop">
    <div class="notification is-accent" align="center">
      <h1>
        <strong>포트폴리오 등록</strong>
      </h1>
      <br />
      <b-field label="프로젝트 제목" type="" message="" align="left">
        <b-input
          v-model="pfSubtitle"
          maxlength="30"
          size="is-medium"
          rounded
        ></b-input>
      </b-field>

      <b-field label="프로젝트 기간" type="" message="" align="left">
        <b-input
          v-model="pfDuration"
          maxlength="30"
          size="is-medium"
          rounded
        ></b-input>
      </b-field>

      <b-field label="프로젝트 참여도" type="" message="" align="left">
        <b-input
          v-model="participation"
          maxlength="30"
          size="is-medium"
          rounded
        ></b-input>
      </b-field>

      <b-field label="프로젝트 외부링크" type="" message="" align="left">
        <b-input
          v-model="pfLink"
          maxlength="30"
          size="is-medium"
          rounded
        ></b-input>
      </b-field>

      <b-field label="프로젝트 설명" type="" message="" align="left">
        <b-input
          v-model="pfDescription"
          size="is-medium"
          maxlength="200" type="textarea"
          rounded
        ></b-input>
      </b-field>

      <b-field label="프로젝트 카테고리" align="left">
        <b-select v-model="pfCategory" expanded>
          <option value="모바일앱">모바일앱 </option>
          <option value="웹앱">웹앱 </option>
          <option value="데이터사이언스">데이터사이언스 </option>
          <option value="게임개발">게임개발 </option>
        </b-select>
      </b-field>

      <b-field label="프로젝트 포지션" align="left">
        <b-select v-model="pfPosition" expanded>
          <option value="개발자">개발자</option>
          <option value="기획자">기획자</option>
          <option value="디자이너">디자이너</option>
        </b-select>
      </b-field>
<br>
      <b-field label="프로젝트 사용툴" align="left" v-if="pfPosition === '개발자'">
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

      <b-field label="프로젝트 사용툴" align="left" v-if="pfPosition === '기획자'">
        <b-checkbox v-model="pfTools" native-value="Gloo maps">
          Gloo maps
        </b-checkbox>
        <b-checkbox v-model="pfTools" native-value="Plectica">
          Plectica
        </b-checkbox>
        <b-checkbox v-model="pfTools" native-value="Trello">
          Trello
        </b-checkbox>
        <b-checkbox v-model="pfTools" native-value="Axure">
          Axure
        </b-checkbox>
        <b-checkbox v-model="pfTools" native-value="Oven">
          Oven
        </b-checkbox>
        <b-checkbox v-model="pfTools" native-value="Power Mockup">
          Power Mockup
        </b-checkbox>
      </b-field>
      <b-field label="프로젝트 사용툴" align="left" v-if="pfPosition === '디자이너'">
        <b-checkbox v-model="pfTools" native-value="Sketch">
          Sketch
        </b-checkbox>
        <b-checkbox v-model="pfTools" native-value="Figma">
          Figma
        </b-checkbox>
        <b-checkbox v-model="pfTools" native-value="Photoshop">
          Photoshop
        </b-checkbox>
        <b-checkbox v-model="pfTools" native-value="Adobe XD">
          Adobe XD
        </b-checkbox>
        <b-checkbox v-model="pfTools" native-value="Framer X">
          Framer X
        </b-checkbox>
        <b-checkbox v-model="pfTools" native-value="Illustrator">
          Illustrator
        </b-checkbox>
      </b-field>
      <br>
      <b-field
        label="프로젝트 사용언어"
        align="left"
        v-if="pfPosition === '개발자'"
      >
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
      <br>
      <b-field
        label="프로젝트 DBMS"
        align="left"
        v-if="pfPosition === '개발자'"
      >
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
        v-on:click="insertPortfolio()"
        position="is-centered"
        size="is-large"
        >포트폴리오 등록</b-button
      >
    </div>
  </div>
</template>
<script>
import router from "../../router";
import axios from "axios";

export default {
  data() {
    return {
      pfSubtitle: "",
      pfDuration: "",
      pfDescription: "",
      participation: "",
      pfLink: "",
      pfCategory: "",
      pfPosition: "",
      pfLang: [],
      pfTools: [],
      pfDbms: [],
    };
  },
  methods: {
    insertPortfolio() {
      let formData = new FormData();

      formData.append(
        "userid",
        JSON.parse(sessionStorage.getItem("user")).userId
      );
      formData.append("pfSubtitle", this.pfSubtitle);
      formData.append("pfDuration", this.pfDuration);
      formData.append("pfDescription", this.pfDescription);
      formData.append("participation", this.participation);
      formData.append("pfLink", this.pfLink);
      formData.append("pfCategory", this.pfCategory);
      formData.append("pfPosition", this.pfPosition);
      formData.append("pftoolslist", this.pfTools);

      if (this.pfPosition === "개발자") {
        formData.append("pflanglist", this.pfLang);
        formData.append("pfdbmslist", this.pfDbms);
      }

      axios.post("http://localhost:80/insertportfolio", formData);
      router.push({ name: "mypage" });
    },
  },
};
</script>
