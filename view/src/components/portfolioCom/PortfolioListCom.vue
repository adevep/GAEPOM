<template>
  <div id="portfolios" class="container">
    <section>
      <b-table
        :data="portfolio"
        ref="table"
        paginated
        per-page="3"
        :opened-detailed="defaultOpenedDetails"
        detailed
        detail-key="userId"
        aria-next-label="Next page"
        aria-previous-label="Previous page"
        aria-page-label="Page"
        aria-current-label="Current page"
      >
        <b-table-column
          field="pfSeq"
          label="글"
          width="30"
          numeric
          v-slot="props"
        >
          {{ props.row.pfSeq }}
        </b-table-column>

        <!-- <b-table-column field="id" label="ID" sortable v-slot="props">
          <a @click="props.toggleDetails(props.row)">
            {{ props.row.currentUserId }} 
            {{ currentUserId }}
          </a>
        </b-table-column> -->

        <b-table-column
          field="pfSubtitle"
          label="포트폴리오 제목"
          sortable
          v-slot="props"
        >
          {{ props.row.pfSubtitle }}
        </b-table-column>

        <b-table-column
          field="pfDuration"
          label="포트폴리오 기간"
          sortable
          centered
          v-slot="props"
        >
          {{ props.row.pfDuration }}
        </b-table-column>

        <b-table-column
          field="pfDescription"
          label="포트폴리오 설명"
          sortable
          centered
          v-slot="props"
        >
          {{ props.row.pfDescription }}
        </b-table-column>

        <b-table-column
          field="participation"
          label="포트폴리오 참여도"
          sortable
          centered
          v-slot="props"
        >
          {{ props.row.participation }}
        </b-table-column>

        <b-table-column
          field="participation"
          label="포트폴리오 포지션"
          sortable
          centered
          v-slot="props"
        >
          {{ props.row.pfPosition }}
        </b-table-column>

        <b-table-column
          field="pfTools"
          label="포트폴리오 사용툴"
          sortable
          centered
          v-slot="props"
        >
          {{ props.row.pfTools }}
        </b-table-column>

        <b-table-column
          field="pfLang"
          label="포트폴리오 사용언어"
          sortable
          centered
          v-slot="props"
          v-if="props.row.pfPosition === '개발자'"
        >
          {{ props.row.pfLang }}
        </b-table-column>

        <b-table-column
          field="pfDbms"
          label="포트폴리오 DBMS"
          sortable
          centered
          v-slot="props"
          v-if="props.row.pfPosition === '개발자'"
        >
          {{ props.row.pfDbms }}
        </b-table-column>

        <b-table-column
          field="pfLink"
          label="포트폴리오 외부주소"
          sortable
          centered
          v-slot="props"
        >
          {{ props.row.pfLink }}
        </b-table-column>

        <b-table-column
          field="pfCategory"
          label="포트폴리오 카테고리"
          sortable
          centered
          v-slot="props"
        >
          {{ props.row.pfCategory }}
        </b-table-column>

        <b-table-column label="변경" sortable centered v-slot="props">
          <b-button
            type="is-info"
            outlined
            @click="updatePortfolio(props.row.pfSeq)"
            position="is-centered"
            size="is-small"
            >수정</b-button
          >
          <b-button
            type="is-danger"
            outlined
            v-on:click="deletePortfolio(props.row.pfSeq)"
            position="is-centered"
            size="is-small"
            >삭제</b-button
          >
        </b-table-column>

        <template #detail="">
          <article class="media">
            <figure class="media-left">
              <!-- <p class="image is-64x64">
                <img src="../../assets/jpg" />
              </p> -->
            </figure>
            <div class="media-content">
              <div class="content">
                <p>
                  <strong>{{ currentUserName }} </strong>
                  <small>@{{ currentUserId }}</small>
                  <br />
                  {{ currentUserAddress }}
                  <br />
                  {{ currentUserPosition }}
                </p>
              </div>
            </div>
          </article>
        </template>
      </b-table>
    </section>
  </div>
</template>

<script>
import http from "../../http-common";
import axios from "axios";
import router from "../../router";

export default {
  name: "portfoliolist",
  data() {
    const portfolio = [];
    return {
      portfolio,
      currentUserId: JSON.parse(sessionStorage.getItem("user")).userId,
      currentUserName: JSON.parse(sessionStorage.getItem("user")).name,
      currentUserPosition: JSON.parse(sessionStorage.getItem("user")).position,
      currentUserAddress: JSON.parse(sessionStorage.getItem("user")).address,
      defaultOpenedDetails: [1],
      isHoverable: true,
      cc: 4,
    };
  },
  methods: {
    retrievePortfolios() {
      http
        .get(
          "/portfolios?userid=" +
            JSON.parse(sessionStorage.getItem("user")).userId
        )
        .then((response) => {
          this.portfolio = response.data;
        })
        .catch(() => {
          alert("조회 실패");
        });
    },
    updatePortfolio(pfseq) {
      return this.$router.push({
        name: "updatePortfolio",
        params: { pfSeq: pfseq },
      });
    },
    deletePortfolio(pfseq) {
      axios
        .delete("http://localhost:80/deleteportfolio/" + pfseq)
        .then(() => {
          sessionStorage.removeItem("portfolio");
          this.portfolio = null;
          this.retrievePortfolios();
          router.push({ name: "portfoliolist" });
        })
        .catch(function() {});
      return this.$router.push({ name: "Home" });
    },
  },
  mounted() {
    this.retrievePortfolios();
  },
};
</script>
