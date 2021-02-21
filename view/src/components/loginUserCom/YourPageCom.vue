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
            <br />
            <b-button type="is-primary is-light" @click="updateUser()"
              >정보수정</b-button
            >
            &nbsp;
            <b-button type="is-danger is-light" @click="deleteUser()"
              >회원탈퇴</b-button
            >
            <br />
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
                    row => $buefy.toast.open(`Expanded ${row.pfSubtitle}`)
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

                  <b-table-column label="변경" v-slot="props">
                    <span>
                      <b-button
                        type="is-info"
                        outlined
                        @click="updatePortfolio(props.row.pfSeq)"
                        position="is-centered"
                        size="is-small"
                        >수정</b-button
                      >
                      <b-button
                        type="is-danger"
                        outlined
                        v-on:click="deletePortfolio(props.row.pfSeq)"
                        position="is-centered"
                        size="is-small"
                        >삭제</b-button
                      >
                    </span>
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
          <b-tab-item label="프로젝트 관리">
            <section>
              <b-tabs :size="medium" :type="boxed" :expanded="expanded">
                <b-tab-item
                  label="내가 작성한 프로젝트 모집글"
                  icon="account-multiple-plus"
                >
                  <my-projects></my-projects>
                </b-tab-item>
                <b-tab-item
                  label="내가 작성한 프로젝트 트래킹글"
                  icon="creation"
                >
                  주최한 프로젝트 트래킹 글 테이블
                </b-tab-item>
                <b-tab-item label="내 지원서 보기" icon="clipboard-account">
                  <application></application>
                </b-tab-item>
              </b-tabs>
            </section>
          </b-tab-item>
          <b-tab-item label="댓글"> </b-tab-item>
        </b-tabs>
      </section>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";
import router from "../../router";
import Application from "@/components/applicationCom/Application.vue";
import MyProjects from "@/components/projectCom/MyProjects.vue";

export default {
  components: {
    Application,
    MyProjects
  },
  data: () => ({
    // 페이지 생성 오류때문에 빈 변수 가진 객체 사용해줌
    anotherUser: {
      userImage: "default.png",
      userId: "",
      name: "",
      email: "",
      phoneNum: "",
      position: "",
      stack: ""
    },
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
    pfCategory: ""
  }),
  computed: {
    ...mapState(["imgURL"])
  },
  methods: {
    retrievePortfolios() {
      axios
        .get("/portfolios?userid=" + this.$route.params.pickedid)
        .then(response => {
          this.portfolio = response.data;
          // console.log(this.portfolio[0].pfLang)
          // this.portfolio[0].pfLang = this.portfolio[0].pfLang.split(",")
          // console.log(this.portfolio[0].pfLang)
        })
        .catch(() => {
          alert("조회 실패");
        });
    },
    anotherUserInfoCall() {
      axios
        .get(
          "http://localhost:80/user/get?userid=" + this.$route.params.pickedid
        )
        .then(response => {
          this.anotherUser = response.data;
        })
        .catch(e => {
          console.log(e);
        });
    },
    getPortfolio(user) {
      this.$store.state.anotherUser = user;
      router.push({ name: "getPortfolio" });
    }
  },
  mounted() {
    this.anotherUserInfoCall();
    this.retrievePortfolios();
  }
};
</script>
<style scoped></style>
