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
        <b-tabs type="">
          <b-tab-item label="유저 정보">
            <h2 class="title is-4 mt-5"><i class="xi-mail"></i> 이메일</h2>
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

          <b-tab-item label="포트폴리오 관리">
            <portfolio-list></portfolio-list>
          </b-tab-item>

          <b-tab-item label="프로젝트 관리">
            <section>
              <b-tabs :size="medium" :type="boxed" :expanded="expanded">
                <b-tab-item
                  label="내가 작성한 모집중 프로젝트"
                  icon="account-multiple-plus"
                >
                  <my-projects></my-projects>
                </b-tab-item>
                <b-tab-item label="내가 작성한 진행중 프로젝트" icon="creation">
                  <my-tracking-projects></my-tracking-projects>
                </b-tab-item>
                <b-tab-item label="내 지원서 보기" icon="clipboard-account">
                  <application></application>
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
import Application from "@/components/applicationCom/Application.vue";
import MyProjects from "@/components/projectCom/MyProjects.vue";
import PortfolioList from "@/views/portfolioView/PortfolioList.vue";
import MyTrackingProjects from "@/components/projectTracking/MyTrackingProjects.vue";

export default {
  components: {
    Application,
    MyProjects,
    PortfolioList,
    MyTrackingProjects
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
    }
  }),
  computed: {
    ...mapState(["imgURL"])
  },
  methods: {
    // 유저 부분
    loginUserInfoCall() {
      axios
        .get(
          "http://localhost:80/user/get?userid=" +
            JSON.parse(sessionStorage.getItem("user")).userId
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
          "http://localhost:80/user/delete?userid=" + this.loginUser.userId
        )
        .then(() => {
          sessionStorage.removeItem("user");
          this.loginUserAct(null);
        })
        .catch(() => {
          this.success();
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
  }
};
</script>
<style scoped></style>
