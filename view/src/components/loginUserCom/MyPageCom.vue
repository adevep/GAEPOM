<template>
  <div class="mypage">
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link
      href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
      rel="stylesheet"
    />

    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/@mdi/font@5.8.55/css/materialdesignicons.min.css"
    />
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
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
              <img class="is-rounded" :src="imgURL + loginUser.userImage" />
            </p>
          </figure>
          <div class="media-content">
            <div class="content" align="left">
              <p>
                <strong>{{ loginUser.name }}</strong> <br />
                <small>{{ loginUser.userId }}</small>
              </p>
            </div>

            <nav class="level is-mobile">
              <div class="level-left">
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
            <!-- <br /> -->
            <!-- <section class="section is-medium"> -->
            <h2 class="title is-4 mt-5"><i class="xi-mail"></i> 이메일</h2>
            <!-- <h1 class="title"><b-icon icon="xi-mail"></b-icon>이메일</h1> -->
            <h2 class="subtitle">
              {{ loginUser.email }}
            </h2>
            <br />
            <h1 class="title is-4 mt-2"><i class="xi-call"></i> 연락처</h1>
            <h2 class="subtitle">
              {{ loginUser.phoneNum }}
            </h2>
            <br />
            <h1 class="title is-4 mt-2"><i class="xi-user"></i> 포지션</h1>
            <h2 class="subtitle">
              {{ loginUser.position }}
            </h2>
            <br />
            <h1 class="title is-4 mt-2"><i class="xi-puzzle"></i> 기술 스택</h1>
            <h2 class="subtitle">
              {{ loginUser.stack }}
            </h2>
            <br />
            <h1 class="title is-4 mt-2"><i class="xi-maker"></i> 주소</h1>
            <h2 class="subtitle">
              {{ loginUser.address }}
            </h2>
            <br />
            <b-button type="is-primary is-light" @click="updateUser()"
              >정보수정</b-button
            >
            &nbsp;
            <b-button type="is-danger is-light" @click="deleteUser()"
              >회원탈퇴</b-button
            >
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
                      <!-- {{ new Date(props.row.date).toLocaleDateString() }} -->
                    </span>
                  </b-table-column>

                  <b-table-column label="변경" v-slot="props">
                    <span>
                      <!-- <b-icon pack="fas"
                        :icon="props.row.gender === 'Male' ? 'mars' : 'venus'">
                    </b-icon> -->
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
                            <!-- <small>@{{ props.row.user.first_name }}</small> -->
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
              <b-taglist>
                <b-button tag="router-link" to="/insertportfolio">
                  포트폴리오 등록
                </b-button>
              </b-taglist>
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
    loginUser: {
      userImage: "default.png",
      userId: "",
      name: "",
      email: "",
      phoneNum: "",
      position: "",
      stack: ""
    },
    portfolio: [],
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
    // 포트폴리오 부분
    retrievePortfolios() {
      axios
        .get(
          "/portfolios?userid=" +
            JSON.parse(sessionStorage.getItem("user")).userId
        )
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
    insertPortfolio() {
      let formData = new FormData();

      formData.append(
        "userid",
        JSON.parse(sessionStorage.getItem("user")).userId
      );
      formData.append("pfSubtitle", this.pfSubtitle);
      formData.append("pfDuration", this.pfDuration);
      formData.append("pfDescription", this.pfDescription);
      formData.append("participation", this.participation);
      formData.append("pfLang", this.pfLang);
      formData.append("pfTools", this.pfTools);
      formData.append("pfDbms", this.pfDbms);
      formData.append("pfLink", this.pfLink);
      formData.append("pfCategory", this.pfCategory);

      axios.post("http://localhost:80/insertportfolio", formData);
      router.push({ name: "Home" });
    },
    updatePortfolio(pfseq) {
      return this.$router.push({
        name: "updatePortfolio",
        params: { pfSeq: pfseq }
      });
    },
    deletePortfolio(pfseq) {
      axios
        .delete("http://localhost:80/deleteportfolio/" + pfseq)
        .then(() => {
          this.portfolio = null;
          router.push({ name: "mypage" });
        })
        .catch(function() {});
      return this.$router.push({ name: "Home" });
    },
    // 유저 부분
    loginUserInfoCall() {
      axios
        .get(
          "/getuser?userid=" + JSON.parse(sessionStorage.getItem("user")).userId
        )
        .then(response => {
          this.loginUser = response.data;
        })
        .catch(() => {
          alert("로그인 유저 마이페이지 조회 실패");
        });
    },
    updateUser() {
      router.push({ name: "updateUser" });
    },
    deleteUser() {
      axios
        .delete(
          "http://localhost:80/deleteuser?userid=" + this.loginUser.userId
        )
        .then(() => {
          sessionStorage.removeItem("user");
          this.loginUser = null;
          this.success();
        })
        .catch(() => {
          this.danger();
        });
      router.push({ name: "Home" });
    },
    success() {
      this.$buefy.notification.open({
        message: "회원 탈퇴가 완료되었습니다.",
        type: "is-success",
        position: "is-bottom-right"
      });
    },
    danger() {
      this.$buefy.notification.open({
        message: `회원 탈퇴를 실패했습니다.`,
        type: "is-danger",
        position: "is-bottom-right"
      });
    }
  },
  mounted() {
    this.loginUserInfoCall();
    this.retrievePortfolios();
  }
};
</script>
<style scoped></style>
