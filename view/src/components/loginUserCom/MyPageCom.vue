<template>
  <div class="mypage">
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/@mdi/font@5.8.55/css/materialdesignicons.min.css"
    />
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
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
        <b-tabs type="">
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
          
          <b-tab-item label="포트폴리오 관리">
                  <portfolio-list></portfolio-list>
          </b-tab-item>

          <b-tab-item label="프로젝트 관리">
            <section>
              <b-tabs :size="medium" :type="boxed" :expanded="expanded">
                <b-tab-item
                  label="내가 주최한 프로젝트 모집글"
                  icon="google-photos"
                >
                  <my-projects></my-projects>
                </b-tab-item>
                <b-tab-item
                  label="내가 주최한 프로젝트 트래킹글"
                  icon="google-photos"
                >
                주최한 프로젝트 트래킹 글 테이블
                </b-tab-item>
                <b-tab-item label="내 지원서" icon="library-music">
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
import Application from "@/components/Application.vue";
import MyProjects from "@/components/MyProjects.vue";
import PortfolioList from "@/views/portfolioView/PortfolioList.vue";
export default {
  components: {
    Application,
    MyProjects,
    PortfolioList
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
  }),
  computed: {
    ...mapState(["imgURL"])
  },
  methods: {
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
  }
};
</script>
<style scoped></style>
