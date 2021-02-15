<template>
  <div id="app" class="container">
    <section>
      <b-field grouped group-multiline>
        <div
          v-for="(column, index) in pfsFieldName"
          :key="index"
          class="control"
        >
          <b-checkbox v-model="column.visible">
            {{ column.title }}
          </b-checkbox>
        </div>
      </b-field>

      <b-table :data="pfs">
        <b-table-column
          v-for="(column, index) in pfsFieldName"
          :key="index"
          :label="column.title"
          :visible="column.visible"
          v-slot="props"
        >
          {{ props.row[column.field] }}
        </b-table-column>
      </b-table>
    </section>
  </div>
</template>

<script>
//import PortfolioService from "../services/PortfolioService";
import http from "../http-common";

export default {
  name: "Portfolio",
  data() {
    const pfs = [];
    return {
      pfs,
      pfsFieldName: [
        { title: "번호", field: "pfSeq", visible: true },
        { title: "프로젝트 제목", field: "pfSubtitle", visible: true },
        { title: "프로젝트 기간", field: "pfDuration", visible: true },
        { title: "설명", field: "pfDescription", visible: true },
        { title: "참여도", field: "participation", visible: true },
        { title: "사용 언어", field: "pfLang", visible: true },
        { title: "사용 툴", field: "pfTools", visible: true },
        { title: "DBMS", field: "pfDbms", visible: true },
        { title: "참조 링크", field: "pfLink", visible: true },
        { title: "분야", field: "pfCategory", visible: true }
      ]
    };
  },
  methods: {
    retrievePfs() {
      http
        .get("/portfolio/")
        .then(response => {
          this.pfs = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
          this.errors.push(e);
        });
    }
  },
  mounted() {
    this.retrievePfs();
  }
};
</script>
