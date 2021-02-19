<template>
  <div class="ProjectDetails">
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
            <br />
            <h5 class="subtitle is-5" align="left">
              ▶ 이메일 : {{ loginUser.email }}
            </h5>
            <br />
            <h5 class="subtitle is-5" align="left">
              ▶ 전화번호 : {{ loginUser.phoneNum }}
            </h5>
            <br />
            <h5 class="subtitle is-5" align="left">
              ▶ 포지션 : {{ loginUser.position }}
            </h5>
            <br />
            <h5 class="subtitle is-5" align="left">
              ▶ 기술 : {{ loginUser.stack }}
            </h5>
            <br />
            <h5 class="subtitle is-5" align="left">
              ▶ 주소 : {{ loginUser.address }}
            </h5>
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
                  paginated
                  per-page="3"
                  aria-next-label="Next page"
                  aria-previous-label="Previous page"
                  aria-page-label="Page"
                  aria-current-label="Current page"
                >
                  <b-table-column
                    field="pfSeq"
                    label="번호"
                    width="80"
                    numeric
                    v-slot="props"
                  >
                    {{ props.row.pfSeq }}
                  </b-table-column>

                  <b-table-column field="id" label="ID" sortable>
                    {{ currentUserId }}
                  </b-table-column>

                  <b-table-column
                    field="pfSubtitle"
                    label="제목"
                    sortable
                    v-slot="props"
                    width="80"
                  >
                    {{ props.row.pfSubtitle }}
                  </b-table-column>

                  <b-table-column
                    field="pfDuration"
                    label="기간"
                    sortable
                    centered
                    v-slot="props"
                    width="80"
                  >
                    {{ props.row.pfDuration }}
                  </b-table-column>

                  <b-table-column
                    field="pfDescription"
                    label="설명"
                    sortable
                    centered
                    v-slot="props"
                    width="80"
                  >
                    {{ props.row.pfDescription }}
                  </b-table-column>

                  <b-table-column
                    field="participation"
                    label="참여도"
                    sortable
                    centered
                    v-slot="props"
                    width="120"
                  >
                    {{ props.row.participation }}%
                  </b-table-column>

                  <b-table-column
                    field="pfLang"
                    label="언어"
                    sortable
                    centered
                    v-slot="props"
                  >
                    {{ props.row.pfLang }}
                  </b-table-column>

                  <b-table-column
                    field="pfTools"
                    label="Tool"
                    sortable
                    centered
                    v-slot="props"
                  >
                    {{ props.row.pfTools }}
                  </b-table-column>

                  <b-table-column
                    field="pfDbms"
                    label="DB"
                    sortable
                    centered
                    v-slot="props"
                  >
                    {{ props.row.pfDbms }}
                  </b-table-column>

                  <b-table-column
                    field="pfLink"
                    label="Link"
                    sortable
                    centered
                    v-slot="props"
                  >
                    {{ props.row.pfLink }}
                  </b-table-column>

                  <b-table-column
                    field="pfCategory"
                    label="카테고리"
                    sortable
                    centered
                    width="150"
                    v-slot="props"
                  >
                    {{ props.row.pfCategory }}
                  </b-table-column>

                  <b-table-column
                    label="변경"
                    sortable
                    centered
                    v-slot="props"
                    width="150"
                  >
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
                  </b-table-column>
                </b-table>
              </section>
            </div>

            <b-taglist>
              <b-button tag="router-link" to="/insertportfolio" type="is-info">
                포트폴리오 등록
              </b-button>
            </b-taglist>
          </b-tab-item>
          <b-tab-item label="프로젝트 관리">
            <section>
              <b-collapse :open="false" aria-id="contentIdForA11y1">
                <template #trigger>
                  <b-button
                    label="내 지원서 보기"
                    type="is-primary"
                    aria-controls="contentIdForA11y1"
                  />
                </template>
                <div class="content">
                  <application></application>
                </div>
              </b-collapse>
            </section>
            <br />

            <section>
              <b-collapse :open="false" aria-id="contentIdForA11y1">
                <template #trigger>
                  <b-button
                    label="내 주최 프로젝트 보기"
                    type="is-primary"
                    aria-controls="contentIdForA11y1"
                  />
                </template>
                <div class="content">
                  <my-projects></my-projects>
                </div>
              </b-collapse>
            </section>

            <b-taglist>
              <b-tag type="is-primary">1</b-tag>
              <b-tag type="is-primary is-light">2</b-tag>
              <b-tag type="is-link">3</b-tag>
              <b-tag type="is-link is-light">4</b-tag>
              <b-tag type="is-link is-light">5</b-tag>
            </b-taglist>
          </b-tab-item>
          <b-tab-item label="댓글">
            <b-taglist>
              <b-tag type="is-primary">1</b-tag>
              <b-tag type="is-primary is-light">2</b-tag>
              <b-tag type="is-link">3</b-tag>
              <b-tag type="is-link is-light">4</b-tag>
              <b-tag type="is-link is-light">5</b-tag>
            </b-taglist>
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
import Application from "@/components/Application.vue";
import MyProjects from "@/components/MyProjects.vue";
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
          alert("회원 탈퇴 완료");
        })
        .catch(() => {
          alert("회원 탈퇴 실패");
        });
      router.push({ name: "Home" });
    }
  },
  mounted() {
    this.loginUserInfoCall();
    this.retrievePortfolios();
  }
};
</script>
<style scoped></style>
