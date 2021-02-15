<template>
  <v-row align="center" class="list px-3 mx-auto">
    <v-col cols="12" md="8">
      <v-text-field v-model="pfSubtitle" label="포트폴리오 제목으로 검색"></v-text-field>
    </v-col>

    <v-col cols="12" md="4">
      <v-btn small @click="searchPfSubtitle">
        검색
      </v-btn>
    </v-col>

    <v-col cols="12" sm="12">
      <v-card class="mx-auto" tile>
        <v-card-title>포트폴리오</v-card-title>

        <v-data-table
          :headers="headers"
          :items="portfolio"
          disable-pagination
          :hide-default-footer="true"
        >
          <template v-slot:[`item.actions`]="{ item }">
            <v-icon small class="mr-2" @click="editPortfolio(item.pfSeq)">mdi-pencil</v-icon>
            <v-icon small @click="deletePortfolio(item.pfSeq)">mdi-delete</v-icon>
          </template>
        </v-data-table>

        <v-card-actions v-if="portfolio.length > 0">
          <v-btn small color="error" @click="removeAllPortfolio">
            모든 포트폴리오 삭제
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import PortfolioService from "../services/PortfolioService";
export default {
  name: "portfolio-list",
  data() {
    return {
      portfolio: [],
      pfSubtitle: "",
      headers: [
        { text: "pfSeq", align: "start", sortable: false, value: "pfSeq" },
        { text: "userId", value: "userId", sortable: false },
        { text: "pfSubtitle", value: "pfSubtitle", sortable: false  },
        { text: "pfDuration", value: "pfDuration", sortable: false },
        { text: "pfDescription", value: "pfDescription", sortable: false },
        { text: "participation", value: "participation", sortable: false },
        { text: "pfLang", value: "pfLang", sortable: false },
        { text: "pfTools", value: "pfTools", sortable: false },
        { text: "pfDbms", value: "pfDbms", sortable: false },
        { text: "pfLink", value: "pfLink", sortable: false },
        { text: "pfCategory", value: "pfCategory", sortable: false },
        { text: "Status", value: "status", sortable: false },
        { text: "Actions", value: "actions", sortable: false },
      ],
    };
  },
  methods: {
    retrievePortfolio() {
      PortfolioService.getAll()
        .then((response) => {
          this.portfolio = response.data.map(this.getDisplayPortfolio);
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },

    refreshList() {
      this.retrievePortfolio();
    },

    removeAllPortfolio() {
      PortfolioService.deleteAll()
        .then((response) => {
          console.log(response.data);
          this.refreshList();
        })
        .catch((e) => {
          console.log(e);
        });
    },

    searchPfSubtitle() {
      PortfolioService.findByPfSubtitle(this.pfSubtitle)
        .then((response) => {
          this.portfolio = response.data.map(this.getDisplayPortfolio);
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    
    searchUserId() {
      PortfolioService.findPortfolioByUserId(this.userId)
        .then((response) => {
          this.portfolio = response.data.map(this.getDisplayPortfolio);
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },

    editPortfolio(pfSeq) {
      this.$router.push({ name: "portfolio-details", params: { id: pfSeq } });
    },

    deletePortfolio(pfSeq) {
      PortfolioService.delete(pfSeq)
        .then(() => {
          this.refreshList();
        })
        .catch((e) => {
          console.log(e);
        });
    },

    getDisplayPortfolio(portfolio) {
      return {
        pfSeq: portfolio.pfSeq,
        userId: portfolio.userId,
        pfSubtitle: portfolio.pfSubtitle.length > 20 ? portfolio.pfSubtitle.substr(0, 20) + "..." : portfolio.pfSubtitle,
        pfDuration: portfolio.pfDuration,
        pfDescription: portfolio.pfDescription.length > 30 ? portfolio.pfDescription.substr(0, 30) + "..." : portfolio.pfDescription,
        participation: portfolio.participation,
        pfLang: portfolio.pfLang,
        pfTools: portfolio.pfTools,
        pfDbms: portfolio.pfDbms,
        pfLink: portfolio.pfLink,
        pfCategory: portfolio.pfCategory,
        status: portfolio.published ? "published" : "Pending",
      };
    },
  },
  mounted() {
    this.retrievePortfolio();
  },
};
</script>

<style>
.list {
  max-width: 750px;
}
</style>