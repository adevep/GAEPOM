<template>
  <div class="container is-max-desktop">
    <div class="notification is-accent">
      <h1><strong>프로젝트 모집글 수정하기</strong></h1>
      <section class="mt-6 mb-5">
        <b-field label="프로젝트주제" align="left">
          <b-input v-model="pjTitle" maxlength="30"></b-input>
        </b-field>
        <b-field label="프로젝트를 간단히 설명해 주세요." align="left">
          <b-input v-model="pjDescription" maxlength="30"></b-input>
        </b-field>
        <b-field label="프로젝트 기간 (예상)" align="left">
          <div>변경하지 않는 경우 기존 기간을 유지합니다.</div>

          <b-datepicker
            placeholder="클릭해 기간을 변경하세요."
            v-model="pjDuration"
            range
            mobile-native:
            true
          >
          </b-datepicker>
        </b-field>
        <b-field label="지역" align="left">
          <!-- <b-input v-model="location" maxlength="30"></b-input> -->
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
          <b-checkbox v-model="pjTools" native-value="Axure">Axure</b-checkbox>
        </b-field>
        <b-field>
          <b-checkbox v-model="pjTools" native-value="Oven">Oven</b-checkbox>
          <b-checkbox v-model="pjTools" native-value="Power Mockup"
            >Power Mockup</b-checkbox
          >
          <b-checkbox v-model="pjTools" native-value="Sketch"
            >Sketch</b-checkbox
          >
          <b-checkbox v-model="pjTools" native-value="Figma">Figma</b-checkbox>
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
        <b-field label="프로젝트 분류" align="left">
          <!-- <b-input v-model="pjCategory" maxlength="30"></b-input> -->
          <b-select v-model="pjCategory" expanded>
            <option value="모바일앱">모바일앱</option>
            <option value="웹앱">웹앱</option>
            <option value="데이터사이언스">데이터사이언스</option>
            <option value="게임개발">게임개발</option>
          </b-select>
        </b-field>
        <b-field label="프로젝트 사용 언어" align="left">
          <!-- <b-input v-model="pjLang" maxlength="30"></b-input> -->
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
          <!-- <b-input v-model="pjDbms" maxlength="30"></b-input> -->
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
          >
          </b-input>
        </b-field>
        <b-field label="모집 직무" align="left">
          <!-- <b-input v-model="needPosi" maxlength="30"></b-input> -->
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
          <b-input v-model="preference" maxlength="30"></b-input>
        </b-field>
        <b-field label="모집 기간" align="left">
          <div>변경하지 않는 경우 기존 기간을 유지합니다.</div>

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
import router from "../router";

export default {
  name: "UpdateProject",
  data() {
    const all = [];
    const allPjs = [];
    const allPjs2 = [];
    return {
      loginUser: JSON.parse(sessionStorage.getItem("user")).userId,
      all,
      allPjs,
      allPjs2,
      userId: "",
      pjTitle: "",
      pjDescription: "",
      pjCategory: "",
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
      pjNum2: this.$route.params.pjNum
      //recSeq: ""
    };
  },
  methods: {
    getProject() {
      http
        .get("/recruit/gettotalpj/" + this.pjNum2 + "?userId=" + this.loginUser)
        .then(response => {
          this.all = response.data;
          console.log(response.data);
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
          console.log("데이터 확인");
          console.log(this.allPjs);
          console.log("데이터뽑기");
          var allPjs3 = "";
          this.allPjs.forEach(function(entry) {
            allPjs3 = entry;
          });
          console.log(allPjs3);

          // this.allPjs.forEach(function(eachObj) {
          //   for (var key in eachObj) {
          //     // eslint-disable-next-line no-prototype-builtins
          //     if (eachObj.hasOwnProperty(key)) {
          //       console.log(key, eachObj[key]);
          //     }
          //   }
          // });

          console.log("데이터뽑기2");

          this.userId = this.loginUser;
          this.pjTitle = allPjs3.pjTitle;
          this.pjDescription = allPjs3.pjDescription;
          this.pjDuration = allPjs3.pjDuration.split("-");
          this.pjTools = allPjs3.pjTools.split(",");
          this.pjLang = allPjs3.pjLang.split(",");
          this.pjDbms = allPjs3.pjDbms.split(",");
          this.needNum = allPjs3.needNum;
          this.needPosi = allPjs3.needPosi.split(",");
          this.location = allPjs3.location;
          this.preference = allPjs3.preference;
          this.recDuration = allPjs3.recDuration.split("-");
          this.pjCategory = allPjs3.pjCategory;
          this.recSeq = allPjs3.recSeq.recSeq;
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
            needPosi: this.needPosi.join(),
            location: this.location,
            preference: this.preference,
            recDuration: this.recDuration.join("-")
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
            userId: { userId: this.loginUser },
            pjTitle: this.pjTitle,
            pjDescription: this.pjDescription,
            pjDuration: this.pjDuration.join("-"),
            pjCategory: this.pjCategory,
            pjTools: this.pjTools.join(),
            pjLang: this.pjLang.join(),
            pjDbms: this.pjDbms.join()
            //recSeq: { recSeq: this.recSeq }
          };
          http
            .put(
              "/recruit/updatepj/" + this.pjNum2 + "?userId=" + this.loginUser,
              data
            )
            .then(response => {
              console.log("==========add pj==========");
              console.warn(response);
              console.warn(response.data);
              console.log("==========add==========");
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
                    message: '모집글 수정이 완료되었습니다.',
                    type: 'is-success',
                    position: 'is-bottom-right',
                })
            },
     danger() {
                this.$buefy.notification.open({
                    message: `모집글 수정 내용을 정확히 입력해주세요.`,
                    type: 'is-danger',
                    position: 'is-bottom-right',
                })
            },


  },
  mounted() {
    this.getProject();
  }
};
</script>
