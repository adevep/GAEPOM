<template>
  <div id="app" class="container is-fluid">
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1.0, shrink-to-fit=no"
    />

    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/@mdi/font@5.8.55/css/materialdesignicons.min.css"
    />
    <b-navbar>
      <template #brand>
        <b-navbar-item tag="router-link" :to="{ path: '/' }">
          <img src="@/assets/logo.png" alt="Gaepom logo" /> 개폼
        </b-navbar-item>
      </template>
      <template #start>
        <b-navbar-item tag="router-link" :to="{ path: '/' }">
          HOME
        </b-navbar-item>
        <b-navbar-item tag="router-link" :to="{ path: '/profile' }">
          프로필
        </b-navbar-item>
        <b-navbar-item tag="router-link" :to="{ path: '/mentor' }">
          이주의 멘토
        </b-navbar-item>
        <b-navbar-dropdown label="프로젝트">
          <b-navbar-item tag="router-link" :to="{ path: '/project' }">
            프로젝트 모집
          </b-navbar-item>
          <b-navbar-item tag="router-link" :to="{ path: '/projectTracking' }">
            프로젝트 트래커
          </b-navbar-item>
        </b-navbar-dropdown>
      </template>
      <template #end>
        <div class="navbar-menu">
          <div class="navbar-end">
            <b-dropdown
              append-to-body
              aria-role="menu"
              scrollable
              max-height="200"
              trap-focus
              v-if="loginCheck == null"
            >
              <template #trigger>
                <a class="navbar-item" role="button">
                  <span>로그인</span>
                  <b-icon icon="menu-down"></b-icon>
                </a>
              </template>
              <b-dropdown-item has-link aria-role="listitem"
                ><router-link to="/login">로그인</router-link></b-dropdown-item
              >
              <b-dropdown-item has-link aria-role="listitem"
                ><router-link to="/insertuser"
                  >회원가입</router-link
                ></b-dropdown-item
              >
            </b-dropdown>
            <b-dropdown
              position="is-bottom-left"
              append-to-body
              aria-role="menu"
              trap-focus
              v-if="loginCheck != null"
            >
              <template #trigger>
                <a class="navbar-item" role="button">
                  <span>마이페이지</span>
                  <b-icon icon="menu-down"></b-icon>
                </a>
              </template>
              <b-dropdown-item has-link aria-role="listitem"
                ><router-link to="/mypage"
                  >마이페이지</router-link
                ></b-dropdown-item
              >
              <b-dropdown-item has-link aria-role="listitem"
                ><router-link to="/insertproject"
                  >모집글 작성</router-link
                ></b-dropdown-item
              >
              <b-dropdown-item has-link aria-role="listitem"
                ><router-link to="/insertportfolio"
                  >포트폴리오 작성</router-link
                ></b-dropdown-item
              >
              <b-dropdown-item aria-role="listitem" @click="logout()"
                >로그아웃</b-dropdown-item
              >
            </b-dropdown>
          </div>
        </div>
      </template>
    </b-navbar>
    <router-view />
    <default-footer></default-footer>
  </div>
</template>
<script>
import { mapActions } from "vuex";
import router from "./router";
import DefaultFooter from "@/components/Footer.vue";
export default {
  components: { DefaultFooter },
  data() {
    return {
      loginCheck: sessionStorage.getItem("user")
    };
  },
  watch: {
    "$store.state.loginUser": function() {
      this.loginCheck = this.$store.state.loginUser;
    }
  },
  methods: {
    ...mapActions(["loginUserAct"]),
    logout() {
      if (sessionStorage.getItem("user") != null) {
        sessionStorage.removeItem("user");
        this.loginUserAct(null);
        router.push({ name: "Home" });
      } else {
        alert("로그인을 먼저 해주세요");
        router.push({ name: "Home" });
      }
    }
  }
};
</script>
