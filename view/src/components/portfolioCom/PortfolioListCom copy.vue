<template>
  <div class="portfoliolist">
    <section>
      <b-table
        :data="portfolio"
        ref="table"
        :opened-detailed="defaultOpenedDetails"
        detailed
        detail-key="pfSeq"
        @details-open="(row) => $buefy.toast.open()"
        aria-next-label="Next page"
        aria-previous-label="Previous page"
        aria-page-label="Page"
        aria-current-label="Current page"
      >
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

        <b-table-column
          field="pfSubtitle"
          label="제목"
          sortable
          centered
          v-slot="props"
        >
          <a @click="props.toggleDetails(props.row)">
            {{ props.row.pfSubtitle }}
          </a>
        </b-table-column>

        <b-table-column
          field="pfDuration"
          label="기간"
          sortable
          centered
          v-slot="props"
        >
          {{ props.row.pfDuration }}
        </b-table-column>

        <!-- <b-table-column
          field="pfDescription"
          label="설명"
          sortable
          centered
          v-slot="props"
        >
          {{ props.row.pfDescription }}
        </b-table-column>

        <b-table-column
          field="participation"
          label="참여도"
          sortable
          centered
          v-slot="props"
        >
          {{ props.row.participation }}
        </b-table-column>

        <b-table-column
          field="pfPosition"
          label="포지션"
          sortable
          centered
          v-slot="props"
        >
          {{ props.row.pfPosition }}
        </b-table-column>

        <b-table-column
          field="pfTools"
          label="사용툴"
          sortable
          centered
          v-slot="props"
        >
          {{ props.row.pfTools }}
        </b-table-column> -->

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

        <b-table-column
          field="pfCategory"
          label="카테고리"
          centered
          v-slot="props"
        >
          {{ props.row.pfCategory }}
        </b-table-column>

        <b-table-column
          label="수정 및 삭제"
          v-slot="props"
          centered
          >
           <b-button
            type="is-danger"
            outlined
            v-on:click="updatePortfolio(props.row.pfSeq)"
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
        <template #detail="props">
          <article class="media">
            <div class="media-content">
              <div class="content">
                <p>
                  <strong>{{ props.row.pfSubtitle }}</strong>
                  <br />
                  프로젝트 설명 :
                  <strong>{{ props.row.pfDescription }}</strong>
                  <br />
                  참여도 :
                  <strong>{{ props.row.participation }}%</strong>
                  <br />
                  관련 링크 : <strong>{{ props.row.pfLink }}</strong>
                  <br />
                  카테고리 :
                  <strong>{{ props.row.pfCategory }}</strong> <br />
                  사용 툴 : <strong>{{ props.row.pfTools }}</strong>
                  <br />
                  사용 언어 : <strong>{{ props.row.pfLang }}</strong>
                  <br />
                  사용 DBMS : <strong>{{ props.row.pfDbms }}</strong>
                  <br />
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
import axios from "axios";
import router from "../../router";

export default {
  name: "portfoliolist",
  data() {
    const portfolio = [];
    return {
      portfolio,
      isHoverable: true,
      cc: 4,
    };
  },
  methods: {
    portfolioInfoCall() {
      axios
        .get(
          "/portfolios?userid=" +
            JSON.parse(sessionStorage.getItem("user")).userId
        )
        .then((response) => {
          this.portfolio = response.data;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    updatePortfolio(pfseq) {
      return this.$router.push({
        name: "updatePortfolio",
        params: { pfSeq: pfseq },
      });
    },
    deletePortfolio(pfSeq) {
      axios
        .delete("http://localhost:80/deleteportfolio/" + pfSeq)
        .then(() => {
          this.portfolio = null;
          this.success();
          this.portfolioInfoCall();
          router.push({ name: "mypage" });
        })
        .catch(() => {
          this.danger();
        });
      router.push({ name: "Home" });
    },
    success() {
      this.$buefy.notification.open({
        message: "포트폴리오가 삭제되었습니다.",
        type: "is-success",
        position: "is-bottom-right",
      });
    },
    danger() {
      this.$buefy.notification.open({
        message: "포트폴리오 삭제에 실패했습니다.",
        type: "is-danger",
        position: "is-bottom-right",
      });
    },
  },
  mounted() {
    this.portfolioInfoCall();
  },
};
</script>
