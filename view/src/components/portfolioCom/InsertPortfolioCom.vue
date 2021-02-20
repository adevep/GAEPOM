<template>
  <ValidationObserver
    ref="observer"
    v-slot="{ handleSubmit }"
    autocomplete="off"
  >
    <div class="container is-max-desktop">
      <div class="notification is-accent" align="center">
        <h1>
          <strong>포트폴리오 등록</strong>
        </h1>
        <br />
        <BInputWithValidation
          rules="required|max:30"
          label="프로젝트 제목"
          v-model="pfSubtitle"
          size="is-medium"
          align="left"
          rounded
        />

        <b-field label="프로젝트 기간" align="left">
          <b-datepicker
            placeholder="클릭해 기간을 선택하세요."
            v-model="pfDuration"
            range
            required
            mobile-native: true
          >
          </b-datepicker>
        </b-field>

        <!-- <BInputWithValidation
          rules="required"
          label="프로젝트 기간"
          v-model="pfDuration"
          size="is-medium"
          align="left"
          rounded
        /> -->

        <BInputWithValidation
          rules="required|numeric|max:2"
          label="프로젝트 참여도"
          placeholder="예시) 50 => 50%(2자리까지)"
          v-model="participation"
          size="is-medium"
          align="left"
          rounded
        />

        <BInputWithValidation
          rules="required"
          label="프로젝트 외부링크"
          placeholder="예시) https://github.com/adevep/GAEPOM"
          v-model="pfLink"
          size="is-medium"
          align="left"
          rounded
        />

        <BInputWithValidation
          rules="required|max:1024"
          label="프로젝트 설명"
          placeholder="해당하는 프로젝트를 자유롭게 소개해주세요!"
          type="textarea"
          v-model="pfDescription"
          size="is-medium"
          align="left"
          rounded
        />

        <BSeletWithValidation
          rules="required"
          label="프로젝트 카테고리"
          v-model="pfCategory"
          align="left"
        >
          <option value="모바일앱">모바일앱</option>
          <option value="웹앱">웹앱</option>
          <option value="데이터사이언스">데이터사이언스</option>
          <option value="게임개발">게임개발</option>
        </BSeletWithValidation>

        <BSeletWithValidation
          rules="required"
          label="프로젝트 희망직무"
          v-model="pfPosition"
          size="is-medium"
          align="left"
          rounded
        >
          <option value="개발자">개발자</option>
          <option value="기획자">기획자</option>
          <option value="디자이너">디자이너</option>
        </BSeletWithValidation>
        <br />
        <BCheckboxesWithValidation
          rules="required"
          v-if="pfPosition === '개발자'"
        >
          <b-field label="프로젝트 사용툴" align="left">
            <b-checkbox v-model="pfTools" native-value="Eclipse"
              >Eclipse</b-checkbox
            >
            <b-checkbox v-model="pfTools" native-value="IntelliJ"
              >IntelliJ</b-checkbox
            >
            <b-checkbox v-model="pfTools" native-value="VS Code"
              >VS Code</b-checkbox
            >
            <b-checkbox v-model="pfTools" native-value="Jupyter Notebook"
              >Jupyter Notebook</b-checkbox
            >
          </b-field>
        </BCheckboxesWithValidation>
        <BCheckboxesWithValidation
          rules="required"
          v-if="pfPosition === '기획자'"
        >
          <b-field label="프로젝트 사용툴" align="left">
            <b-checkbox v-model="pfTools" native-value="Gloo maps"
              >Gloo maps</b-checkbox
            >
            <b-checkbox v-model="pfTools" native-value="Plectica"
              >Plectica</b-checkbox
            >
            <b-checkbox v-model="pfTools" native-value="Trello"
              >Trello</b-checkbox
            >
            <b-checkbox v-model="pfTools" native-value="Axure"
              >Axure</b-checkbox
            >
            <b-checkbox v-model="pfTools" native-value="Oven">Oven</b-checkbox>
            <b-checkbox v-model="pfTools" native-value="Power Mockup"
              >Power Mockup</b-checkbox
            >
          </b-field>
        </BCheckboxesWithValidation>
        <BCheckboxesWithValidation
          rules="required"
          v-if="pfPosition === '디자이너'"
        >
          <b-field label="프로젝트 사용툴" align="left">
            <b-checkbox v-model="pfTools" native-value="Sketch"
              >Sketch</b-checkbox
            >
            <b-checkbox v-model="pfTools" native-value="Figma"
              >Figma</b-checkbox
            >
            <b-checkbox v-model="pfTools" native-value="Photoshop"
              >Photoshop</b-checkbox
            >
            <b-checkbox v-model="pfTools" native-value="Adobe XD"
              >Adobe XD</b-checkbox
            >
            <b-checkbox v-model="pfTools" native-value="Framer X"
              >Framer X</b-checkbox
            >
            <b-checkbox v-model="pfTools" native-value="Illustrator"
              >Illustrator</b-checkbox
            >
          </b-field>
        </BCheckboxesWithValidation>
        <br />
        <BCheckboxesWithValidation
          rules="required"
          v-if="pfPosition === '개발자'"
        >
          <b-field label="프로젝트 사용언어" align="left">
            <b-checkbox v-model="pfLang" native-value="Java">Java</b-checkbox>
            <b-checkbox v-model="pfLang" native-value="Python"
              >Python</b-checkbox
            >
            <b-checkbox v-model="pfLang" native-value="JavaScript"
              >JavaScript</b-checkbox
            >
            <b-checkbox v-model="pfLang" native-value="C++">C++</b-checkbox>
            <b-checkbox v-model="pfLang" native-value="Vue">Vue</b-checkbox>
            <b-checkbox v-model="pfLang" native-value="React">React</b-checkbox>
          </b-field>
        </BCheckboxesWithValidation>
        <br />
        <BCheckboxesWithValidation
          rules="required"
          v-if="pfPosition === '개발자'"
        >
          <b-field label="프로젝트 DBMS" align="left">
            <b-checkbox v-model="pfDbms" native-value="ORACLE"
              >ORACLE</b-checkbox
            >
            <b-checkbox v-model="pfDbms" native-value="MySQL">MySQL</b-checkbox>
            <b-checkbox v-model="pfDbms" native-value="MariaDB"
              >MariaDB</b-checkbox
            >
            <b-checkbox v-model="pfDbms" native-value="MongoDB"
              >MongoDB</b-checkbox
            >
            <b-checkbox v-model="pfDbms" native-value="PostgreSQL"
              >PostgreSQL</b-checkbox
            >
          </b-field>
        </BCheckboxesWithValidation>
        <br />
        <div class="buttons">
          <button
            class="button is-success"
            type="is-primary"
            outlined
            size="is-large"
            v-on:click="handleSubmit(insertPortfolio)"
          >
            <span>포트폴리오 등록</span>
          </button>
          <button class="button" @click="resetForm">
            <span>재입력</span>
          </button>
        </div>
      </div>
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
import BSeletWithValidation from "../veeInputs/BSeletWithValidation";
import BInputWithValidation from "../veeInputs/BInputWithValidation";
import BCheckboxesWithValidation from "../veeInputs/BCheckboxesWithValidation";

import router from "../../router";
import axios from "axios";

export default {
  components: {
    ValidationObserver,
    BSeletWithValidation,
    BInputWithValidation,
    BCheckboxesWithValidation
  },
  data() {
    return {
      pfSubtitle: "",
      // pfDuration: "",
      pfDuration: [],
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
      // formData.append("pfDuration", this.pfDuration);
      formData.append("pfDuration", this.pfDuration.join("-"));
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
    resetForm() {
      this.pfSubtitle = "";
      // this.pfDuration = "";
      this.pfDuration = [];
      this.pfDescription = "";
      this.participation = "";
      this.pfLink = "";
      this.pfCategory = "";
      this.pfPosition = "";
      this.pfLang = [];
      this.pfTools = [];
      this.pfDbms = [];
      requestAnimationFrame(() => {
        this.$refs.observer.reset();
      });
    },
  },
};
</script>
