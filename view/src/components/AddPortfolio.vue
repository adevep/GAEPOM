<template>
  <div class="submit-form mt-3 mx-auto">
    <p class="headline">포트폴리오 등록하기</p>

    <div v-if="!submitted">
      <v-form ref="form" lazy-validation>
        <v-text-field
          v-model="portfolio.pfSubtitle"
          :rules="[(v) => !!v || '포트폴리오 제목은 필수 입력 항목입니다']"
          label="PfSubtitle"
          required
        ></v-text-field>
        <v-text-field
          v-model="portfolio.pfDuration"
          :rules="[(v) => !!v || '포트폴리오 제목은 필수 입력 항목입니다']"
          label="pfDuration"
          required
        ></v-text-field>
        <v-text-field
          v-model="portfolio.pfDescription"
          :rules="[(v) => !!v || '포트폴리오 제목은 필수 입력 항목입니다']"
          label="pfDescription"
          required
        ></v-text-field>
        <v-text-field
          v-model="portfolio.participation"
          :rules="[(v) => !!v || '포트폴리오 제목은 필수 입력 항목입니다']"
          label="participation"
          required
        ></v-text-field>
        <v-text-field
          v-model="portfolio.pfLang"
          :rules="[(v) => !!v || '포트폴리오 제목은 필수 입력 항목입니다']"
          label="pfLang"
          required
        ></v-text-field>
        <v-text-field
          v-model="portfolio.pfTools"
          :rules="[(v) => !!v || '포트폴리오 제목은 필수 입력 항목입니다']"
          label="pfTools"
          required
        ></v-text-field>
        <v-text-field
          v-model="portfolio.pfDbms"
          :rules="[(v) => !!v || '포트폴리오 제목은 필수 입력 항목입니다']"
          label="pfDbms"
          required
        ></v-text-field>
        <v-text-field
          v-model="portfolio.pfLink"
          :rules="[(v) => !!v || '포트폴리오 제목은 필수 입력 항목입니다']"
          label="pfLink"
          required
        ></v-text-field>
        <v-text-field
          v-model="portfolio.pfCategory"
          :rules="[(v) => !!v || '포트폴리오 제목은 필수 입력 항목입니다']"
          label="pfCategory"
          required
        ></v-text-field>
        <v-text-field
          v-model="portfolio.userId"
          :rules="[(v) => !!v || '포트폴리오 제목은 필수 입력 항목입니다']"
          label="userId"
          required
        ></v-text-field>
      </v-form>

      <v-btn color="primary" class="mt-3" @click="savePortfolio">SUBMIT</v-btn>
    </div>

    <div v-else>
      <v-card class="mx-auto">
        <v-card-title>
          정상적으로 등록되었습니다!
        </v-card-title>

        <v-card-subtitle>
          해당 버튼을 누르면 포트폴리오가 등록됩니다.
        </v-card-subtitle>

        <v-card-actions>
          <v-btn color="success" @click="newPortfolio">포트폴리오 등록</v-btn>
        </v-card-actions>
      </v-card>
    </div>
  </div>
</template>

<script>
// import PortfolioService from "../services/PortfolioService";
import axios from "axios";

export default {
  name: "add-portfolio",
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
        pfCategory: "",
        published: 0,
        userid: "",
      submitted: false,
    };
  },
  methods: {
    savePortfolio() {
      let formData = new FormData() {
        formData.append("pfSubtitle", this.pfSubtitle);
      },

      PortfolioService.create(data)
        .then((response) => {
          this.portfolio.pfSeq = response.data.pfSeq;
          console.log(response.data);
          this.submitted = true;
        })
        .catch((e) => {
          console.log(e);
        });
    },

    newPortfolio() {
      this.submitted = false;
      this.portfolio = {};
    },
  },
};
</script>

<style>
.submit-form {
  max-width: 300px;
}
</style>