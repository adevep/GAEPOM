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
          <h1 class="title">포트폴리오 수정</h1>
          <h2 class="subtitle centered">정보는 소중해요.</h2>
          <nav
            class="breadcrumb has-dot-separator is-centered"
            aria-label="breadcrumbs"
          >
            <ul>
              <li><a href="#">홈페이지</a></li>
              <li class="is-active">
                <a href="#" aria-current="page">포트폴리오 수정</a>
              </li>
            </ul>
          </nav>
        </div>
      </div>
    </section>
    <section>
      <ValidationObserver
        ref="observer"
        v-slot="{ handleSubmit }"
        autocomplete="off"
      >
        <div class="container is-max-desktop">
          <div class="notification is-accent" align="center">
            <br />
            <BInputWithValidation
              rules="required|max:30"
              label="프로젝트 제목"
              v-model="pfSubtitle"
              size="is-medium"
              align="left"
            />
            
            <b-field label="프로젝트 기간" align="left">
              <b-datepicker
                placeholder="클릭해 기간을 선택하세요."
                v-model="pfDuration"
                icon="calendar-today"
                range
                required
                :mobile-native="true"
              >
              </b-datepicker>
            </b-field>

            <BInputWithValidation
              rules="required|numeric|max:2"
              label="프로젝트 참여도"
              placeholder="예시) 50 => 50%(2자리까지)"
              v-model="participation"
              size="is-medium"
              align="left"
            />

            <BInputWithValidation
              rules="required"
              label="프로젝트 외부링크"
              placeholder="예시) https://github.com/adevep/GAEPOM"
              v-model="pfLink"
              size="is-medium"
              align="left"
            />

            <BInputWithValidation
              rules="required|max:200"
              label="프로젝트 설명"
              placeholder="해당하는 프로젝트를 자유롭게 소개해주세요!"
              type="textarea"
              v-model="pfDescription"
              size="is-medium"
              align="left"
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
              align="left"
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
                <b-checkbox v-model="pfTools" native-value="Oven"
                  >Oven</b-checkbox
                >
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
                <b-checkbox v-model="pfLang" native-value="Java"
                  >Java</b-checkbox
                >
                <b-checkbox v-model="pfLang" native-value="Python"
                  >Python</b-checkbox
                >
                <b-checkbox v-model="pfLang" native-value="JavaScript"
                  >JavaScript</b-checkbox
                >
                <b-checkbox v-model="pfLang" native-value="C++">C++</b-checkbox>
                <b-checkbox v-model="pfLang" native-value="Vue">Vue</b-checkbox>
                <b-checkbox v-model="pfLang" native-value="React"
                  >React</b-checkbox
                >
              </b-field>
            </BCheckboxesWithValidation>
            <br />
            <div class="buttons">
              <button
                class="button is-primary is-light"
                size="is-large"
                v-on:click="handleSubmit(submitPortfolio)"
              >
                <span>포트폴리오 수정</span>
              </button>
              <button class="button is-warning is-light" @click="resetForm">
                <span>재입력</span>
              </button>
            </div>
          </div>
        </div>
      </ValidationObserver>
    </section>
  </div>
</template>

<script>
import { ValidationObserver } from "vee-validate";
import BSeletWithValidation from "../veeInputs/BSeletWithValidation";
import BInputWithValidation from "../veeInputs/BInputWithValidation";
import BCheckboxesWithValidation from "../veeInputs/BCheckboxesWithValidation";

import axios from "axios";
import router from "../../router";

export default {
  components: {
    ValidationObserver,
    BSeletWithValidation,
    BInputWithValidation,
    BCheckboxesWithValidation,
  },
  data() {
    return {
      pfSeq: "",
      pfSubtitle: "",
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
    portfolioUpdateInfoCall() {
      axios
        .get(
          "http://localhost:80/portfolio/get?pfSeq=" + this.$route.params.pfSeq
        )
        .then(response => {
          this.pfSeq = response.data.pfSeq;
          this.pfSubtitle = response.data.pfSubtitle;
          this.pfDuration = response.data.pfDuration;
          this.pfDescription = response.data.pfDescription;
          this.participation = response.data.participation;
          this.pfLink = response.data.pfLink;
          this.pfCategory = response.data.pfCategory;
          this.pfTools = response.data.pfTools.split(",");
          this.pfPosition = response.data.pfPosition;

          if (this.pfPosition === "개발자") {
            this.pfLang = response.data.pfLang.split(",");
            this.pfDbms = response.data.pfDbms.split(",");
          }
        })
        .catch(e => {
          console.log(e);
        });
    },
    submitPortfolio() {
      let myDate = new Date(Date.parse(this.pfDuration[0]));
      let myDate2 = new Date(Date.parse(this.pfDuration[1]));
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
      this.pfDuration = date3.join("-");

      let formData = new FormData();

      formData.append("pfSeq", this.pfSeq);
      formData.append("pfSubtitle", this.pfSubtitle);
      formData.append("pfDuration", this.pfDuration);
      formData.append("pfDescription", this.pfDescription);
      formData.append("participation", this.participation);
      formData.append("pfLink", this.pfLink);
      formData.append("pfCategory", this.pfCategory);
      formData.append("pftoolslist", this.pfTools);

      if (this.pfPosition === "개발자") {
        formData.append("pflanglist", this.pfLang);
        formData.append("pfdbmslist", this.pfDbms);
      }
      axios
        .put(
          "http://localhost:80/portfolio/update", formData, {
        })
        .then(() => {
          this.success();
          router.push({ name: "mypage" });
        })
        .catch(() => {
          this.danger();
        });
    },
    resetForm() {
      this.pfSubtitle = "";
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
    success() {
      this.$buefy.notification.open({
        message: "포트폴리오 수정이 완료되었습니다.",
        type: "is-success",
        position: "is-bottom-right",
      });
    },
    danger() {
      this.$buefy.notification.open({
        message: `포트폴리오 수정 내용를 정확히 작성해주세요.`,
        type: "is-danger",
        position: "is-bottom-right",
      });
    },
  },
  mounted() {
    this.portfolioUpdateInfoCall();
  },
};
</script>
