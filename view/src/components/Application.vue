<template>
  <div id="app" class="container">
    <section>
      <b-field grouped group-multiline>
        <div
          v-for="(column, index) in appsFieldName"
          :key="index"
          class="control"
        >
          <b-checkbox v-model="column.visible">
            {{ column.title }}
          </b-checkbox>
        </div>
      </b-field>

      <b-table :data="apps">
        <b-table-column
          v-for="(column, index) in appsFieldName"
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
//import AppService from "../services/AppService";
import http from "../http-common";

export default {
  name: "Application",
  data() {
    const apps = [];
    return {
      apps,
      appsFieldName: [
        { title: "번호", field: "aplSeq", visible: true },
        { title: "아이디", field: "userId", visible: true },
        { title: "지원 직무", field: "aplPosi", visible: true },
        { title: "각오", field: "words", visible: true },
        { title: "진행 사항", field: "selected", visible: true },
        { title: "등록 날짜", field: "aplDate", visible: true }
      ]
    };
  },
  methods: {
    retrieveApps() {
      http
        .get("/app/getlist")
        .then(response => {
          this.apps = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
          this.errors.push(e);
        });
    }
  },
  mounted() {
    this.retrieveApps();
  }
};
</script>
