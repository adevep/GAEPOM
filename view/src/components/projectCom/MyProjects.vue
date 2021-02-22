<template>
  <div id="app" class="container">
    <section>
      <b-table :data="pjs" ref="table" :hoverable="isHoverable">
        <b-table-column
          field="aplSeq"
          label="글"
          width="40"
          numeric
          centered
          v-slot="props"
        >
          {{ props.row.pjSeq }}
        </b-table-column>
        <b-table-column
          field="pjCategory"
          label="분야"
          sortable
          v-slot="props"
          centered
        >
          {{ props.row.pjCategory }}
        </b-table-column>
        <b-table-column
          field="pjTitle"
          label="프로젝트"
          sortable
          v-slot="props"
          centered
        >
          <router-link
            :to="{ name: 'details', params: { pjSeq: props.row.pjSeq } }"
            >{{ props.row.pjTitle }}</router-link
          >
        </b-table-column>
        <b-table-column
          field="pjDescription"
          label="설명"
          sortable
          v-slot="props"
          centered
        >
          {{ props.row.pjDescription }}
        </b-table-column>
        <b-table-column
          field="pjSeq"
          label="지원현황"
          sortable
          v-slot="props"
          centered
        >
          <router-link
            class="tag is-success"
            :to="{ name: 'AllApps', params: { pjSeq: props.row.pjSeq } }"
            >지원자</router-link
          >
        </b-table-column>
        <b-table-column
          field="update"
          label="수정하기"
          sortable
          v-slot="props"
          centered
        >
          <router-link
            class="tag is-warn"
            :to="{
              name: 'updateproject',
              params: { pjNum: props.row.pjSeq }
            }"
            >수정</router-link
          >
        </b-table-column>
      </b-table>
    </section>
  </div>
</template>
<script>
import http from "../../http-common";

export default {
  name: "MyProjects",
  data() {
    const pjs = [];
    return {
      loginUser: JSON.parse(sessionStorage.getItem("user")).userId,
      pjs,
      defaultOpendDetails: [1],
      showDetailcon: true,
      isHoverable: true,
    };
  },
  methods: {
    retrievePjs() {
      http
        .get("/recruit/gethostedpj/" + this.loginUser)
        .then(response => {
          this.pjs = response.data;
        })
        .catch(e => {
          console.log(e);
          this.errors.push(e);
        });
    }
  },
  mounted() {
    this.retrievePjs();
  }
};
</script>
