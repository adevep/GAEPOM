<template>
  <ValidationObserver
    ref="observer"
    v-slot="{ handleSubmit }"
    autocomplete="off"
  >
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
              프로젝트
            </h1>
            <h2 class="subtitle centered">
              새로운 프로젝트를 주최해 보세요.
            </h2>
            <nav
              class="breadcrumb has-dot-separator is-centered"
              aria-label="breadcrumbs"
            >
              <ul>
                <li><a href="#">홈페이지</a></li>
                <li><a href="#">마이페이지</a></li>
                <li class="is-active">
                  <a href="#" aria-current="page">프로젝트 주최</a>
                </li>
              </ul>
            </nav>
          </div>
        </div>
      </section>
      <div class="notification is-accent">
        <section>
          <b-field label="프로젝트주제" align="left">
            <BInputWithValidation
              v-model="pjTitle"
              maxlength="20"
              rules="required"
            />
          </b-field>
          <b-field label="프로젝트를 간단히 설명해 주세요." align="left">
            <BInputWithValidation
              v-model="pjDescription"
              maxlength="20"
              rules="required"
            />
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
            <BSeletWithValidation v-model="location" expanded rules="required">
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
            </BSeletWithValidation>
          </b-field>
          <BCheckboxesWithValidation rules="required">
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
            <b-field>
              <b-checkbox v-model="pjTools" native-value="Gloo maps"
                >Gloo maps</b-checkbox
              >
              <b-checkbox v-model="pjTools" native-value="Plectica"
                >Plectica</b-checkbox
              >
              <b-checkbox v-model="pjTools" native-value="Trello"
                >Trello</b-checkbox
              >
              <b-checkbox v-model="pjTools" native-value="Axure"
                >Axure</b-checkbox
              >
            </b-field>
            <b-field>
              <b-checkbox v-model="pjTools" native-value="Oven"
                >Oven</b-checkbox
              >
              <b-checkbox v-model="pjTools" native-value="Power Mockup"
                >Power Mockup</b-checkbox
              >
              <b-checkbox v-model="pjTools" native-value="Sketch"
                >Sketch</b-checkbox
              >
              <b-checkbox v-model="pjTools" native-value="Figma"
                >Figma</b-checkbox
              >
            </b-field>
            <b-field>
              <b-checkbox v-model="pjTools" native-value="Photoshop"
                >Photoshop</b-checkbox
              >
              <b-checkbox v-model="pjTools" native-value="Adobe XD"
                >Adobe XD</b-checkbox
              >
              <b-checkbox v-model="pjTools" native-value="Framer X"
                >Framer X</b-checkbox
              >
              <b-checkbox v-model="pjTools" native-value="Illustrator"
                >Illustrator</b-checkbox
              >
            </b-field>
          </BCheckboxesWithValidation>
          <BCheckboxesWithValidation rules="required">
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
          </BCheckboxesWithValidation>
          <BCheckboxesWithValidation rules="required">
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
          </BCheckboxesWithValidation>

          <b-field label="모집 인원" align="left">
            <BInputWithValidation
              v-model="needNum"
              placeholder="최소 1명이상"
              type="number"
              min="1"
              max="100"
              rules="required"
            />
          </b-field>
          <BCheckboxesWithValidation rules="required">
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
          </BCheckboxesWithValidation>

          <b-field label="우대사항" align="left">
            <BInputWithValidation
              v-model="preference"
              maxlength="30"
              rules="required"
            />
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
          size="is-medium"
          class="mt-6"
          >주최하기</b-button
        >
      </div>
      <div class="container is-max-desktop pt-5"></div>
    </div>
  </ValidationObserver>
</template>
<script>
import { ValidationObserver } from "vee-validate";
import BInputWithValidation from "../veeInputs//BInputWithValidation";
import BCheckboxesWithValidation from "../veeInputs/BCheckboxesWithValidation";
import BSeletWithValidation from "../veeInputs/BSeletWithValidation";
import http from "../../http-common";
import router from "../../router";
export default {
  name: "AddProject",
  components: {
    ValidationObserver,
    BSeletWithValidation,
    BInputWithValidation,
    BCheckboxesWithValidation
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
      //DATEPARSING
      let myDate = new Date(Date.parse(this.recDuration[0]));
      let myDate2 = new Date(Date.parse(this.recDuration[1]));
      let date1 =
        myDate.getFullYear() +
        "-" +
        ("0" + (myDate.getMonth() + 1)).slice(-2) +
        "-" +
        ("0" + myDate.getDate()).slice(-2);
      let date2 =
        myDate2.getFullYear() +
        "-" +
        ("0" + (myDate2.getMonth() + 1)).slice(-2) +
        "-" +
        ("0" + myDate2.getDate()).slice(-2);
      let date3 = [];
      date3.push(date1);
      date3.push(date2);
      this.needPosi = this.needPosi.join();
      this.recDuration = date3.join("-");
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
          this.pjTools = this.pjTools.join();
          this.pjLang = this.pjLang.join();
          this.pjDbms = this.pjDbms.join();

          //DATE PARSING
          let myDate = new Date(Date.parse(this.pjDuration[0]));
          let myDate2 = new Date(Date.parse(this.pjDuration[1]));
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

          this.pjDuration = date3.join("-");

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
              console.warn(response.data);
              this.success();
              router.push({ name: "mypage" });
            })
            .catch(ex => {
              this.danger();
              console.warn("ERROR!!!!! : ", ex);
            });
        })
        .catch(ex => {
          console.warn("ERROR!!!!! : ", ex);
        });
    },
    success() {
      this.$buefy.notification.open({
        message: "모집글 작성이 완료되었습니다.",
        type: "is-success",
        position: "is-bottom-right"
      });
    },
    danger() {
      this.$buefy.notification.open({
        message: `모집글을 정확히 작성해주세요.`,
        type: "is-danger",
        position: "is-bottom-right"
      });
    }
  },
  mounted() {}
};
</script>
