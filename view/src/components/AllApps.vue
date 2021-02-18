<template>
  <div id="app" class="container">
    <section>
      <b-table
        :data="apps"
        ref="table"
        :hoverable="isHoverable"
        :opened-detailed="defaultOpenedDetails"
        detailed
        detail-key="userId"
        @details-open="row => $buefy.toast.open(`Expanded ${row.userId}`)"
        :show-detail-icon="showDetailIcon"
      >
        <b-table-column
          field="aplSeq"
          label="글"
          width="40"
          numeric
          centered
          v-slot="props"
        >
          {{ props.row.aplSeq }}
        </b-table-column>
        <b-table-column
          field="pjSeq"
          label="프로젝트"
          sortable
          v-slot="props"
          centered
        >
          {{ props.row.pjSeq.pjTitle }}
        </b-table-column>
        <b-table-column
          field="userId"
          label="지원자"
          sortable
          centered
          v-slot="props"
        >
          <template v-if="showDetailIcon">
            {{ props.row.userId }}
          </template>
          <template v-else>
            <a @click="props.toggleDetails(props.row)">
              {{ props.row.userId }}
            </a>
          </template>
        </b-table-column>
        <b-table-column
          field="aplPosi"
          label="지원 직무"
          sortable
          v-slot="props"
          centered
        >
          {{ props.row.aplPosi }}
        </b-table-column>
        <b-table-column
          field="selected"
          label="진행 사항"
          sortable
          v-slot="props"
          centered
        >
          {{ props.row.selected }}
        </b-table-column>

        <b-table-column
          field="aplDate"
          label="지원 날짜"
          sortable
          centered
          v-slot="props"
        >
          <span class="tag is-success">
            {{ new Date(props.row.aplDate).toLocaleDateString() }}
          </span>
        </b-table-column>
        <b-table-column
          field="selected"
          label="결정"
          sortable
          centered
          v-slot="props"
        >
          <a
            v-if="props.row.selected == 0"
            class="tag is-warn"
            @click="acceptApp(props.row.aplSeq, props.row)"
          >
            수락 {{ props.row.selected }}
          </a>
          <a
            v-else-if="props.row.selected == 1"
            class="tag is-success"
            @click="rejectApp(props.row.aplSeq, props.row)"
          >
            확정 {{ props.row.selected }}
          </a>
          <a
            v-else-if="props.row.selected == 2"
            class="tag is-danger"
            @click="acceptApp(props.row.aplSeq, props.row)"
          >
            거절 {{ props.row.selected }}
          </a>
        </b-table-column>
        <template #detail="props">
          <article class="media">
            <figure class="media-left">
              <p class="image is-64x64">
                <img
                  src="https://buefy.org/static/img/placeholder-128x128.png"
                />
              </p>
            </figure>
            <div class="media-content">
              <div class="content">
                <p>
                  <big> 각오 한마디 </big>
                  <strong>{{ props.row.userID }} </strong>
                  <br />
                  {{ props.row.words }}
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
import http from "../http-common";

export default {
  name: "AllApps",
  props: ["pjSeq"],
  data() {
    const apps = [];
    return {
      loginUser: JSON.parse(sessionStorage.getItem("user")).userId,
      apps,
      defaultOpendDetails: [1],
      showDetailcon: true,
      isHoverable: true,
      pjSeq2: this.$route.params.pjSeq
    };
  },
  methods: {
    retrieveApps() {
      http
        .get("/app/getpjapp/"+ this.pjSeq2 + "?userId=" + this.loginUser)
        .then(response => {
          this.apps = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
          this.errors.push(e);
        });
    },
    acceptApp(id, app) {
      {
        app.selected = 1;
      }
      http
        .put(`/app/update/${id}?userId=` + this.loginUser, app)
        .then(response => {
          console.log("업데이트 성공?");
          console.log(response.data.selected);
        })
        .catch(e => {
          console.log("실패했나?");
          console.log(e);
          this.errors.push(e);
        });
    },
    rejectApp(id, app) {
      {
        app.selected = 2;
      }
      http
        .put(`/app/update/${id}?userId=` + this.loginUser, app)
        .then(response => {
          console.log("업데이트 성공?");
          console.log(response.data.selected);
        })
        .catch(e => {
          console.log("실패했나?");
          console.log(e);
          this.errors.push(e);
        });
    }
  },
  mounted() {
    this.retrieveApps();
    this.retrievePjs();
  }
};
</script>
