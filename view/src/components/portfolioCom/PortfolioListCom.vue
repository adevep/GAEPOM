<template>
  <div class="portfoliolist">
    <section>
      <b-table
        :data="portfolio"
        ref="table"
        detailed
        detail-key="pfSeq"
        @details-open="(row) => $buefy.toast.open()"
      >
        <b-table-column
          field="pfSeq"
          label="번호"
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
          field="pfPosition"
          label="포지션"
          sortable
          v-slot="props"
        >
          {{ props.row.pfPosition }}
        </b-table-column>

          <b-table-column
          field="pfCategory"
          label="카테고리"
          centered
          v-slot="props"
        >
          <b-tag type="is-primary" size="is-medium">{{
            props.row.pfCategory
          }}</b-tag>
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

        <b-table-column
          field="pfLang"
          label="사용언어"
          sortable
          centered
          v-slot="props"
          v-if="pfPosition === '개발자'"
        >
          <b-tag type="is-info" size="is-medium">{{ props.row.pfLang }}</b-tag>
        </b-table-column>

        <b-table-column
          field="pfDbms"
          label="DBMS"
          sortable
          centered
          v-slot="props"
          v-if="pfPosition === '개발자'"
        >
          <b-tag type="is-success" size="is-medium">{{ props.row.pfDbms }}</b-tag>
        </b-table-column>

      
        <b-table-column label="수정 및 삭제" v-slot="props" centered>
          <b-button
            type="is-primary is-light"
            outlined
            v-on:click="updatePortfolio(props.row.pfSeq)"
            position="is-centered"
            size="is-small"
            >수정</b-button
          >
          <b-button
            type="is-danger is-light"
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
                  <b-field v-if="props.row.pfPosition === '개발자'">
                    사용 툴 :
                    <b-tag type="is-success" size="is-small">{{
                      props.row.pfTools
                    }}</b-tag>
                  </b-field>
                  <b-field v-if="props.row.pfPosition === '개발자'">
                    사용 언어 :
                    <b-tag type="is-info" size="is-small">{{
                      props.row.pfLang
                    }}</b-tag>
                  </b-field>
                  <b-field v-if="props.row.pfPosition === '개발자'">
                    사용 DBMS :
                    <b-tag type="is-success" size="is-small">{{
                      props.row.pfDbms
                    }}</b-tag>
                  </b-field>
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
          "http://localhost:80/portfolio/getlist?userid=" +
            JSON.parse(sessionStorage.getItem("user")).userId
        )
        .then((response) => {
          this.portfolio = response.data;
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
        .delete("http://localhost:80/portfolio/delete?pfSeq=" + pfSeq)
        .then(() => {
          this.portfolioInfoCall();
          this.success();
        })
        .catch(() => {
          this.danger();
        });
      this.portfolioInfoCall();
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
