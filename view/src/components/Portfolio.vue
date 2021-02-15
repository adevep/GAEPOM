<template>
  <div v-if="currentPortfolio" class="edit-form py-3">
    <p class="headline">포트폴리오 수정하기</p>

    <v-form ref="form" lazy-validation>
      <v-text-field
        v-model="currentPortfolio.pfSubtitle"
        :rules="[(v) => !!v || '포트폴리오 제목은 필수 입력 항목입니다']"
        label="pfSubtitle"
        required
      ></v-text-field>
      <v-text-field
        v-model="currentPortfolio.pfDuration"
        :rules="[(v) => !!v || '포트폴리오 제목은 필수 입력 항목입니다']"
        label="pfDuration"
        required
      ></v-text-field>
      <v-text-field
        v-model="currentPortfolio.pfDescription"
        :rules="[(v) => !!v || '포트폴리오 제목은 필수 입력 항목입니다']"
        label="pfDescription"
        required
      ></v-text-field>
      <v-text-field
        v-model="currentPortfolio.participation"
        :rules="[(v) => !!v || '포트폴리오 제목은 필수 입력 항목입니다']"
        label="participation"
        required
      ></v-text-field>
      <v-text-field
        v-model="currentPortfolio.pfLang"
        :rules="[(v) => !!v || '포트폴리오 제목은 필수 입력 항목입니다']"
        label="pfLang"
        required
      ></v-text-field>
      <v-text-field
        v-model="currentPortfolio.pfTools"
        :rules="[(v) => !!v || '포트폴리오 제목은 필수 입력 항목입니다']"
        label="pfTools"
        required
      ></v-text-field>
      <v-text-field
        v-model="currentPortfolio.pfDbms"
        :rules="[(v) => !!v || '포트폴리오 제목은 필수 입력 항목입니다']"
        label="pfDbms"
        required
      ></v-text-field>
      <v-text-field
        v-model="currentPortfolio.pfLink"
        :rules="[(v) => !!v || '포트폴리오 제목은 필수 입력 항목입니다']"
        label="pfLink"
        required
      ></v-text-field>
      <v-text-field
        v-model="currentPortfolio.pfCategory"
        :rules="[(v) => !!v || '포트폴리오 제목은 필수 입력 항목입니다']"
        label="pfCategory"
        required
      ></v-text-field>

      <label><strong>Status:</strong></label>
      {{ currentPortfolio.published ? "갱신 완료" : "미갱신" }}

      <v-divider class="my-5"></v-divider>

      <v-btn v-if="currentPortfolio.published"
        @click="updatePublished(0)"
        color="primary" small class="mr-2"
      >
        미갱신
      </v-btn>

      <v-btn v-else
        @click="updatePublished(1)"
        color="primary" small class="mr-2"
      >
        갱신 완료
      </v-btn>

      <v-btn color="error" small class="mr-2" @click="deletePortfolio">
        삭제
      </v-btn>

      <v-btn color="success" small @click="updatePortfolio">
        수정
      </v-btn>
    </v-form>

    <p class="mt-3">{{ message }}</p>
  </div>

  <div v-else>
    <p>조회하려는 포트폴리오의 인덱스값(pfSeq)을 입력해 주세요!</p>
  </div>
</template>

<script>
import PortfolioService from "../services/PortfolioService";

export default {
  name: "portfolio",
  data() {
    return {
      currentPortfolio: null,
      message: "",
    };
  },
  methods: {
    getPortfolio(pfSeq) {
      PortfolioService.get(pfSeq)
        .then((response) => {
          this.currentPortfolio = response.data;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },

    updatePublished(status) {
      var data = {
        pfSeq: this.currentPortfolio.pfSeq,
        pfSubtitle: this.currentPortfolio.pfSubtitle,
        pfDuration: this.currentPortfolio.pfDuration,
        pfDescription: this.currentPortfolio.pfDescription,
        participation: this.currentPortfolio.participation,
        pfLang: this.currentPortfolio.pfLang,
        pfTools: this.currentPortfolio.pfTools,
        pfDbms: this.currentPortfolio.pfDbms,
        pfLink: this.currentPortfolio.pfLink,
        pfCategory: this.currentPortfolio.pfCategory,
        published: status,
      };

      PortfolioService.update(this.currentPortfolio.pfSeq, data)
        .then((response) => {
          this.currentPortfolio.published = status;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },

    updatePortfolio() {
      PortfolioService.update(this.currentPortfolio.pfSeq, this.currentPortfolio)
        .then((response) => {
          console.log(response.data);
          this.message = "포트폴리오가 성공적으로 수정되었습니다!";
        })
        .catch((e) => {
          console.log(e);
        });
    },

    deletePortfolio() {
      PortfolioService.delete(this.currentPortfolio.pfSeq)
        .then((response) => {
          console.log(response.data);
          this.$router.push({ name: "portfolio" });
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
  mounted() {
    this.message = "";
    this.getPortfolio(this.$route.params.pfSeq);
  },
};
</script>

<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>