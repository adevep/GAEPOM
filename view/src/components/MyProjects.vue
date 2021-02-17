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
          label="프로젝트"
          sortable
          v-slot="props"
          centered
        >
          {{ props.row.pjDescription }}
        </b-table-column>
        <b-table-column
          field="pjSeq"
          label="지원자 보기"
          sortable
          v-slot="props"
          centered
        >
          <router-link
            :to="{ name: 'AllApps', params: { pjSeq: props.row.pjSeq } }"
            >클릭</router-link
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
            :to="{
              name: 'updateproject',
              params: { pjNum: props.row.pjSeq }
            }"
            >클릭</router-link
          >
        </b-table-column>
      </b-table>
    </section>
  </div>
</template>
<script>
import http from "../http-common";

export default {
  name: "MyProjects",
  data() {
    const pjs = [];
    return {
      loginUser: JSON.parse(sessionStorage.getItem("user")).userId,
      pjs,
      defaultOpendDetails: [1],
      showDetailcon: true,
      isHoverable: true
    };
  },
  methods: {
    retrievePjs() {
      http
        .get("/recruit/gethostedpj/" + this.loginUser)
        .then(response => {
          this.pjs = response.data;
          console.log(response.data);
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
