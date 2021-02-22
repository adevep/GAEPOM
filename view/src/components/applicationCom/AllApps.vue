<template>
  <div id="app" class="container is-max-desktop pt-5">
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link
      href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
      rel="stylesheet"
    />
    <section>
      <div class="hero-body">
        <div class="container has-text-centered">
          <h1 class="title ">
            지원자 수정
          </h1>
          <h2 class="subtitle centered">
            함께할 팀원을 선택하세요.
          </h2>
          <nav
            class="breadcrumb has-dot-separator is-centered"
            aria-label="breadcrumbs"
          >
            <ul>
              <li><a href="/">홈페이지</a></li>
              <li><a href="/mypage">마이페이지</a></li>
              <li><a href="/project">프로젝트</a></li>
              <li class="is-active">
                <a href="#" aria-current="page">지원자 수정</a>
              </li>
            </ul>
          </nav>
        </div>
      </div>
    </section>
    <section>
      <b-table
        :data="isEmpty ? [] : filteredApps"
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
            class="tag is-success"
            @click="acceptApp(props.row.aplSeq, props.row)"
          >
            수락
          </a>
          <a
            class="tag is-danger"
            @click="rejectApp(props.row.aplSeq, props.row)"
          >
            거절
          </a>
        </b-table-column>
        <b-table-column
          field="selected"
          label="결정"
          sortable
          centered
          v-slot="props"
        >
          <a v-if="props.row.selected == 0" class="tag is-warn">
            수락
          </a>
          <a v-else-if="props.row.selected == 1" class="tag is-success">
            확정
          </a>
          <a v-else-if="props.row.selected == 2" class="tag is-danger">
            거절
          </a>
        </b-table-column>
        <template #detail="props">
          <article class="media">
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

    <b-message
      title="NOTIFICATION"
      type="is-success"
      has-icon
      aria-close-label="Close message"
      v-if="checkCount == needNum"
    >
      팀원들이 확정되었습니다. 팀원들과 함께 프로젝트 트래킹 페이지를
      만들어볼까요?
      <br />
      <router-link
        class="button"
        :to="{
          name: 'ProjectTrackingInsert',
          params: { pjseq: this.$route.params.pjSeq }
        }"
        >프로젝트 작성하기</router-link
      >
    </b-message>
    <b-message v-else></b-message>
  </div>
</template>
<script>
import http from "../../http-common";

export default {
  name: "AllApps",
  data() {
    const apps = [];
    return {
      loginUser: JSON.parse(sessionStorage.getItem("user")).userId,
      apps,
      accepted: "",
      needNum: "",
      defaultOpendDetails: [1],
      showDetailcon: true,
      isHoverable: true,
      pjSeq2: this.$route.params.pjSeq,
      checkCount: 0
    };
  },
  methods: {
    retrieveApps() {
      http
        .get("/app/getpjapp/" + this.pjSeq2 + "?userId=" + this.loginUser)
        .then(response => {
          this.apps = response.data;
          http
            .get(
              "/recruit/getbypj/" + this.pjSeq2 + "?userId=" + this.loginUser
            )
            .then(response => {
              this.needNum = response.data;
              http
                .get(
                  "/recruit/getbypjcheckcount/" +
                    this.pjSeq2 +
                    "?userId=" +
                    this.loginUser
                )
                .then(response => {
                  this.checkCount = response.data;
                })
                .catch(e => {
                  console.log(e);
                });
            })
            .catch(e => {
              console.log(e);
            });
        })
        .catch(e => {
          console.log(e);
          this.errors.push(e);
        });
    },
    acceptApp(id, app) {
      {
        if (app.selected != 1 && this.checkCount < this.needNum) {
          app.selected = 1;
          this.checkCount += 1;
        }
      }
      http
        .put(`/app/update/${id}?userId=` + this.loginUser, app)
        .then(response => {
          console.log(response.data);
          http
            .put(
              "/recruit/updatereccount/" +
                this.pjSeq2 +
                "?userId=" +
                this.loginUser +
                "&checkCount=" +
                this.checkCount
            )
            .then(response => {
              console.log(response.data);
            })
            .catch(e => {
              console.log(e);
            });
        })

        .catch(e => {
          console.log(e);
          this.errors.push(e);
        });
    },
    rejectApp(id, app) {
      {
        if (app.selected != 2 && this.checkCount > 0) {
          app.selected = 2;
          this.checkCount -= 1;
        }
      }
      http
        .put(`/app/update/${id}?userId=` + this.loginUser, app)
        .then(response => {
          console.log(response.data.selected);

          http
            .put(
              "/recruit/updatereccount/" +
                this.pjSeq2 +
                "?userId=" +
                this.loginUser +
                "&checkCount=" +
                this.checkCount
            )
            .then(response => {
              console.log(response.data);
            })
            .catch(e => {
              console.log(e);
            });
        })
        .catch(e => {
          console.log(e);
          this.errors.push(e);
        });
    },
    confirmCustom() {
      this.$buefy.dialog.confirm({
        title: "팀원들이 확정되었습니다.",
        message: `축하합니다.`,
        cancelText: "?",
        confirmText: "팀원들과 함께 프로젝트 트래킹 페이지 만들기.",
        type: "is-success",
        onConfirm: () => this.$buefy.toast.open("User agreed")
      });
    }
  },
  mounted() {
    this.retrieveApps();
  },
  computed: {
    filteredApps: function() {
      var countFiltered;
      if (this.needNum != null) {
        countFiltered = this.apps.filter(function(element) {
          return element.selected == 1;
        }).length;
        console.log(this.needNum);
        console.log("계산값" + countFiltered);
        if (countFiltered >= this.needNum) {
          return this.apps.filter(function(item) {
            return item.selected == 1;
          });
        }
      }
      return this.apps;
    },
    acceptedApps: function() {
      var countFiltered;
      if (this.needNum != null) {
        console.log(this.needNum);
        countFiltered = this.apps.filter(function(element) {
          return element.selected == 1;
        }).length;
        return countFiltered;
      } else return countFiltered;
    }
  }
};
</script>
