<template>
  <div id="app" class="container">
    <section>
      <b-table :data="isEmpty ? [] : apps" :hoverable="isHoverable">
        <b-table-column
          field="aplSeq"
          label="글"
          width="20"
          numeric
          centered
          v-slot="props"
        >
          {{ props.row.aplSeq }}
        </b-table-column>

        <b-table-column field="userId" label="아이디" v-slot="props" centered>
          {{ props.row.userId }}
        </b-table-column>

        <b-table-column
          field="aplPosi"
          label="지원 직무"
          v-slot="props"
          centered
        >
          {{ props.row.aplPosi }}
        </b-table-column>
        <b-table-column field="words" label="각오" v-slot="props" centered>
          {{ props.row.words }}
        </b-table-column>
        <b-table-column
          field="selected"
          label="진행사항"
          v-slot="props"
          centered
        >
          {{ props.row.selected }}
        </b-table-column>
        <b-table-column field="pjSeq" label="프로젝트" v-slot="props" centered>
          {{ props.row.pjSeq.pjTitle }}
        </b-table-column>
        <b-table-column
          field="aplDate"
          label="등록날짜"
          centered
          v-slot="props"
        >
          <span class="tag is-success">
            {{ new Date(props.row.aplDate).toLocaleDateString() }}
          </span>
        </b-table-column>
      </b-table>
    </section>
  </div>
</template>

<script>
import http from "../http-common";

export default {
  name: "Application",
  data() {
    const apps = [];
    return {
      loginUser: JSON.parse(sessionStorage.getItem("user")).userId,
      apps,
      isHoverable: true
    };
  },
  methods: {
    retrieveApps() {
      http
        .get("/app/getapp/user1")
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
