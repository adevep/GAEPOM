<template>
  <div>
    <router-view class="box"></router-view>
    <span
      class="card"
      style="width: 500px; height: 800px; background-color: #ffffe0;"
    >
      <!-- <div class="card-image">
        <figure class="image is-4by3">
          <img :src="imgURL + portfolioInfo.userImage" alt="Placeholder image" />
        </figure>
      </div> -->
      <div class="card-content">
        <div class="media">
          <div class="media-left"></div>
          <div class="media-content">
            <!-- <p class="title is-4" v-text="portfolioInfo.pfSubtitle"></p>
            <p class="subtitle is-6" v-text="portfolioInfo.pfDescription"></p> -->
          </div>
        </div>

        <div class="content">
          <!-- 프로젝트 참여 ID : {{ portfolioInfo.userid }} <br /> -->
          프로젝트 제목 : {{ portfolioInfo.pfSubtitle }} <br />
          프로젝트 설명 : {{ portfolioInfo.pfDescription }} <br />
          프로젝트 기간 : {{ portfolioInfo.pfDuration }} <br />
          프로젝트 참여도 : {{ portfolioInfo.participation }} <br />
          프로젝트 사용언어 : {{ portfolioInfo.pfLang }} <br />
          프로젝트 사용툴 : {{ portfolioInfo.pfTools }} <br />
          프로젝트 DBMS : {{ portfolioInfo.pfDbms }} <br />
          프로젝트 외부주소 : {{ portfolioInfo.pfLink }} <br />
          프로젝트 카테고리 : {{ portfolioInfo.pfCategory }} <br />
          <b-button type="is-primary is-light" @click="updatePortfolio()"
            >포트폴리오 수정</b-button
          >
          &nbsp;
          <b-button type="is-danger is-light" @click="deletePortfolio()"
            >포트폴리오 삭제</b-button
          >
        </div>
      </div>
    </span>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";
import router from "../../router";

export default {
  data: () => ({}),
  computed: {
    ...mapState(["portfolioInfo"])
    // ...mapState(["portfolioInfo", "imgURL"])
  },
  methods: {
    updatePortfolio() {
      return this.$router.push({ name: "updatePortfolio" });
    },
    deletePortfolio() {
      let formData = new FormData();

      formData.append(
        "userid",
        JSON.parse(sessionStorage.getItem("user")).userId
      );
      formData.append("pfSubtitle", this.portfolioInfo.pfSubtitle);
      formData.append("pfDuration", this.portfolioInfo.pfDuration);
      formData.append("pfDescription", this.portfolioInfo.pfDescription);
      formData.append("participation", this.portfolioInfo.participation);
      formData.append("pfLang", this.portfolioInfo.pfLang);
      formData.append("pfTools", this.portfolioInfo.pfTools);
      formData.append("pfDbms", this.portfolioInfo.pfDbms);
      formData.append("pfLink", this.portfolioInfo.pfLink);
      formData.append("pfCategory", this.portfolioInfo.pfCategory);

      axios
        .delete("http://localhost:80/deleteportfolio", formData, {
          headers: {
            "Content-Type": "multipart/form-data"
          }
        })
        .then(() => {
          sessionStorage.removeItem("portfolio");
          this.portfolioInfo = null;
          console.log("SUCCESS!!");
          router.push({ name: "myportfolio" });
        })
        .catch(function() {
          console.log("FAILURE!!");
        });
      return this.$router.push({ name: "Home" });
    }
  }
};
</script>
<style scoped>
.card {
  position: static;
  border: 1px solid black;
  float: left;
  margin: 1px;
  padding: 130px 50px;
  width: 1000px;
  height: 1000px;
}
.box {
  position: static;
  float: right;
  width: 500px;
  height: 100px;
}
</style>
