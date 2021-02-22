<template>
  <div class="ProjectDetails">
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
    />
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link
      href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
      rel="stylesheet"
    />

    <link
      rel="stylesheet"
      href="//cdn.jsdelivr.net/gh/xpressengine/xeicon@2.3.1/xeicon.min.css"
    />
    <div class="container is-max-desktop pt-5">
      <section class="mt-6 mb-5">
        <article class="media">
          <figure class="media-left">
            <p class="image is-64x64">
              <img class="is-rounded" :src="imgURL + anotherUser.userImage" />
            </p>
          </figure>
          <div class="media-content">
            <div class="content" align="left">
              <p>
                <strong>{{ anotherUser.name }}</strong> <br />
                <small>{{ anotherUser.userId }}</small>
              </p>
            </div>

            <nav class="level is-mobile">
              <div class="level-left">
                <a class="level-item">
                  <span class="icon is-small"
                    ><i class="fas fa-reply"></i
                  ></span>
                </a>
                <a class="level-item">
                  <span class="icon is-small"
                    ><i class="fas fa-retweet"></i
                  ></span>
                </a>
                <a class="level-item">
                  <span class="icon is-small"
                    ><i class="fas fa-heart"></i
                  ></span>
                </a>
              </div>
            </nav>
          </div>
        </article>
      </section>
      <section>
        <b-tabs type="is-boxed">
          <b-tab-item label="유저 정보">
            <h2 class="title is-4 mt-5"><i class="xi-mail"></i> 이메일</h2>
            <h2 class="subtitle">
              {{ anotherUser.email }}
            </h2>
            <br />
            <h1 class="title is-4 mt-2"><i class="xi-call"></i> 연락처</h1>
            <h2 class="subtitle">
              {{ anotherUser.phoneNum }}
            </h2>
            <br />
            <h1 class="title is-4 mt-2"><i class="xi-user"></i> 포지션</h1>
            <h2 class="subtitle">
              {{ anotherUser.position }}
            </h2>
            <br />
            <h1 class="title is-4 mt-2"><i class="xi-puzzle"></i> 기술 스택</h1>
            <h2 class="subtitle">
              {{ anotherUser.stack }}
            </h2>
            <br />
            <h1 class="title is-4 mt-2"><i class="xi-maker"></i> 주소</h1>
            <h2 class="subtitle">
              {{ anotherUser.address }}
            </h2>
          </b-tab-item>
          <b-tab-item label="포트폴리오">
            <div id="portfolios" class="container">
              <section>
                <b-table
                  :data="portfolio"
                  ref="table"
                  :opened-detailed="defaultOpenedDetails"
                  detailed
                  detail-key="pfSeq"
                  @details-open="
                    (row) => $buefy.toast.open(`Expanded ${row.pfSubtitle}`)
                  "
                  :show-detail-icon="showDetailIcon"
                  aria-next-label="Next page"
                  aria-previous-label="Previous page"
                  aria-page-label="Page"
                  aria-current-label="Current page"
                >
                  <b-table-column
                    field="pfSeq"
                    label="번호"
                    width="70"
                    numeric
                    v-slot="props"
                  >
                    {{ props.row.pfSeq }}
                  </b-table-column>

                  <b-table-column
                    field="pfSubtitle"
                    label="제목"
                    sortable
                    v-slot="props"
                  >
                    <template v-if="showDetailIcon">
                      {{ props.row.pfSubtitle }}
                    </template>
                    <template v-else>
                      <a @click="props.toggleDetails(props.row)">
                        {{ props.row.pfSubtitle }}
                      </a>
                    </template>
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
                    <span class="tag is-success">
                      {{ props.row.pfDuration }}
                    </span>
                  </b-table-column>

                  <b-table-column
                    field="pfLang"
                    label="사용언어"
                    sortable
                    centered
                    v-slot="props"
                    v-if="pfPosition === '개발자'"
                  >
                    <b-tag type="is-info" size="is-medium">{{
                      props.row.pfLang
                    }}</b-tag>
                  </b-table-column>

                  <b-table-column
                    field="pfDbms"
                    label="DBMS"
                    sortable
                    centered
                    v-slot="props"
                    v-if="pfPosition === '개발자'"
                  >
                    <b-tag type="is-dark" size="is-medium">{{
                      props.row.pfDbms
                    }}</b-tag>
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
                            카테고리 :
                            <strong>{{ props.row.pfCategory }}</strong> <br />
                            사용 툴 : <strong>{{ props.row.pfTools }}</strong>
                            <br />
                            사용 언어 : <strong>{{ props.row.pfLang }}</strong>
                            <br />
                            사용 DBMS : <strong>{{ props.row.pfDbms }}</strong>
                            <br />
                          </p>
                        </div>
                      </div>
                    </article>
                  </template>
                </b-table>
              </section>
            </div>
          </b-tab-item>
          <b-tab-item label="프로젝트">
            <section>
              <b-tabs :size="medium" :type="boxed" :expanded="expanded">
                <b-tab-item
                  label="모집중인 프로젝트"
                  icon="account-multiple-plus"
                >
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
                            :to="{
                              name: 'details',
                              params: { pjSeq: props.row.pjSeq },
                            }"
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
                          field="pjDurationn"
                          label="기간"
                          sortable
                          v-slot="props"
                          centered
                        >
                          {{ props.row.pjDuration }}
                        </b-table-column>
                      </b-table>
                    </section>
                  </div>
                </b-tab-item>
                <b-tab-item label="진행중인 프로젝트" icon="creation">
                  <your-tracking-projects
                    v-bind:auser="this.$route.params.pickedid"
                  />
                </b-tab-item>
              </b-tabs>
            </section>
          </b-tab-item>
        </b-tabs>
      </section>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";
import router from "../../router";
import http from "../../http-common";
import YourTrackingProjects from "@/components/projectTracking/YourTrackingProjects.vue";

export default {
  components: {
    YourTrackingProjects,
  },
  data: () => ({
    pjs: [],
    anotherUser: {
      userImage: "default.png",
      userId: "",
      name: "",
      email: "",
      phoneNum: "",
      position: "",
      stack: "",
    },
    yourid: null,
    portfolio: [],
    currentUserId: JSON.parse(sessionStorage.getItem("user")).userId,
    currentUserName: JSON.parse(sessionStorage.getItem("user")).name,
    currentUserPosition: JSON.parse(sessionStorage.getItem("user")).position,
    currentUserAddress: JSON.parse(sessionStorage.getItem("user")).address,

    pfSubtitle: "",
    pfDuration: "",
    pfDescription: "",
    participation: "",
    pfLang: "",
    pfTools: "",
    pfDbms: "",
    pfLink: "",
    pfCategory: "",
    defaultOpendDetails: [1],
    showDetailcon: true,
    isHoverable: true,
  }),
  computed: {
    ...mapState(["imgURL"]),
  },
  methods: {
    retrievePortfolios() {
      axios
        .get(
          "http://localhost:80/portfolio/getlist?userid=" +
            this.$route.params.pickedid
        )
        .then((response) => {
          this.portfolio = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    anotherUserInfoCall() {
      axios
        .get(
          "http://localhost:80/user/get?userid=" + this.$route.params.pickedid
        )
        .then((response) => {
          this.anotherUser = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    getPortfolio(user) {
      this.$store.state.anotherUser = user;
      router.push({ name: "getPortfolio" });
    },
    retrievePjs() {
      http
        .get("/recruit/gethostedpj/" + this.$route.params.pickedid)
        .then(response => {
          this.pjs = response.data;
        })
        .catch((e) => {
          console.log(e);
          this.errors.push(e);
        });
    },
  },
  mounted() {
    this.anotherUserInfoCall();
    this.retrievePortfolios();
    this.retrievePjs();
  },
};
</script>
<style scoped></style>
