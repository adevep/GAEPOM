<template>
  <div class="portfoliolist">
    <section>
      <b-table :data="portfolio" :hoverable="isHoverable" ref="table">
        <b-table-column
          field="pfSeq"
          label="글"
          width="80"
          sortable
          centered
          numeric
          v-slot="props"
        >
          {{ props.row.pfSeq }}
        </b-table-column>

        <b-table-column field="pfSubtitle" label="제목" sortable centered v-slot="props">
          {{ props.row.pfSubtitle }}
        </b-table-column>

        <b-table-column field="pfDuration" label="기간" sortable centered v-slot="props">
          {{ props.row.pfDuration }}
        </b-table-column>

        <b-table-column field="pfDescription" label="설명" sortable centered v-slot="props">
          {{ props.row.pfDescription }}
        </b-table-column>

        <b-table-column field="participation" label="참여도" sortable centered v-slot="props">
          {{ props.row.participation }}
        </b-table-column>

        <b-table-column field="pfPosition" label="포지션" sortable centered v-slot="props">
          {{ props.row.pfPosition }}
        </b-table-column>

        <b-table-column field="pfTools" label="사용툴" sortable centered v-slot="props">
          {{ props.row.pfTools }}
        </b-table-column>

        <b-table-column
          field="pfLang"
          label="사용언어"
          sortable
          centered
          v-slot="props"
          v-if="pfPosition === '개발자'"
        >
          {{ props.row.pfLang }}
        </b-table-column>

        <b-table-column
          field="pfDbms"
          label="DBMS"
          sortable
          centered
          v-slot="props"
          v-if="pfPosition === '개발자'"
        >
          {{ props.row.pfDbms }}
        </b-table-column>

        <b-table-column field="pfLink" label="외부주소" centered v-slot="props">
          {{ props.row.pfLink }}
        </b-table-column>

        <b-table-column field="pfCategory" label="카테고리" centered v-slot="props">
          {{ props.row.pfCategory }}
        </b-table-column>

        <b-table-column label="수정하기" centered>
          <b-button
            type="is-primary is-light"
            outlined
            @click="updatePortfolio()"
            position="is-centered"
            size="is-small"
            >수정</b-button
          >
          <b-button
            type="is-danger is-light"
            outlined
            @click="deletePortfolio()"
            position="is-centered"
            size="is-small"
            >삭제</b-button
          >
        </b-table-column>
      </b-table>
    </section>
  </div>
</template>

<script>
import axios from "axios";
import router from "../../router";

export default {
  data: () => ({
    portfolio: [],
    // pfSubtitle: "",
    // pfDuration: [],
    // pfDescription: "",
    // participation: "",
    // pfPosition: "",
    // pfLang: [],
    // pfTools: [],
    // pfDbms: [],
    // pfLink: "",
    // pfCategory: "",
    showDetailcon: true,
    isHoverable: true,
    cc: 4,
  }),
  methods: {
    retrievePortfolios() {
      axios
        .get(
          "/portfolios?userid=" +
            JSON.parse(sessionStorage.getItem("user")).userId
        )
        .then((response) => {
          this.portfolio = response.data;
          // this.pfDuration[0] = new Date(this.portfolio.pfDuration.split("-")[0]);
          // this.pfDuration[1] = new Date(this.portfolio.pfDuration.split("-")[1]);
          console.log(response.data);
        })
        .catch((e) => {
          console.warn(e);
          alert("포트폴리오 조회 실패 | " + e);
        });
    },
    // updatePortfolio(pfseq) {
    //   return this.$router.push({
    //     name: "updatePortfolio",
    //     params: { pfSeq: pfseq },
    //   });
    // },
    updatePortfolio() {
      router.push({ name: "updatePortfolio" });
    },
    // deletePortfolio(pfseq) {
    //   axios
    //     .delete("http://localhost:80/deleteportfolio/" + pfseq)
    //     .then(() => {
    //       sessionStorage.removeItem("portfolio");
    //       this.portfolio = null;
    //       this.retrievePortfolios();
    //       router.push({ name: "portfoliolist" });
    //     })
    //     .catch(function() {});
    //   return this.$router.push({ name: "Home" });
    // },
    deletePortfolio(pfseq) {
      axios
        .delete("http://localhost:80/deleteportfolio/" + pfseq)
        .then(() => {
          sessionStorage.removeItem("portfolio");
          this.portfolio = null;
          this.retrievePortfolios();
          router.push({ name: "portfoliolist" });
        })
        .catch(e => {
          console.warn("포트폴리오 리스트 조회 실패 | " + e)
        });
      return this.$router.push({ name: "Home" });
    },
  },
  mounted() {
    this.retrievePortfolios();
  },
};
</script>
