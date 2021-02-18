<template>
  <div id="app" class="container">
    <section>
      <!-- <v-for"app in apps" :key="app.index">
         -->
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
          <b-input v-model="words" maxlength="30"></b-input>
          <a
            @click="updateApp(props.row.aplSeq, props.row)"
            class="tag is-warn pr-2 prl-2"
          >
            변경
          </a>
        </b-table-column>
        <b-table-column
          field="selected"
          label="진행사항"
          v-slot="props"
          centered
        >
          <span v-if="props.row.selected == 0" class="tag is-warn">
            지원중
          </span>
          <span v-else-if="props.row.selected == 1" class="tag is-success">
            합격
          </span>
          <span v-else-if="props.row.selected == 2" class="tag is-danger">
            합격
          </span>
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
        <b-table-column field="aplSeq" label="삭제" centered v-slot="props">
          <a class="tag is-warn" @click="deleteApp(props.row.aplSeq)">
            {{ props.row.aplSeq }}삭제
          </a>
        </b-table-column>
        <!-- <b-table-column field="edit" label="삭제" centered v-slot="props">
          <a @click="deleteApp(props.row.aplSeq)" class="tag is-danger">
            삭제
          </a>
        </b-table-column> -->
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
      isHoverable: true,
      words: ""
    };
  },
  methods: {
    retrieveApps() {
      http
        .get("/app/getapp/" + this.loginUser)
        .then(response => {
          this.apps = response.data;
          var apps2 = "";
          this.apps.forEach(function(entry) {
            apps2 = entry;
          });
          this.words = apps2.words;
          console.log(response.data);
          console.log(apps2);
        })
        .catch(e => {
          console.log(e);
          this.errors.push(e);
        });
    },
    updateApp(id, app) {
      {
        app.words = this.words;
      }
      http
        .put(`/app/update/${id}?userId=` + this.loginUser, app)
        .then(response => {
          this.retrieveApps();
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
          this.errors.push(e);
        });
    },
    deleteApp(id) {
      http
        .delete(`/app/delete/${id}?userId=` + this.loginUser)
        .then(response => {
          console.log(response.data);
          this.retrieveApps();
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
