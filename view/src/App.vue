<template>
  <div id="app" class="container is-fluid">
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
        <!-- <b-navbar-dropdown label="포트폴리오">
          <b-navbar-item tag="router-link" :to="{ path: '/insertportfolio' }">
            프로젝트 등록
          </b-navbar-item>
          <b-navbar-item tag="router-link" :to="{ path: '/portfolios' }">
            모든 포트폴리오
          </b-navbar-item>
        </b-navbar-dropdown> ?-->

      </template>
      <template #end>
        <b-dropdown aria-role="list">
          <template #trigger="{ active }">
            <b-button
              label="로그인/회원가입"
              type="is-primary"
              :icon-right="active ? 'menu-up' : 'menu-down'"
            />
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
        <b-dropdown aria-role="list">
          <template #trigger="{ active }">
            <b-button
              label="My Menu"
              type="is-primary"
              :icon-right="active ? 'menu-up' : 'menu-down'"
            />
          </template>
          <b-dropdown-item has-link aria-role="listitem"
            ><router-link to="/mypage">마이페이지</router-link></b-dropdown-item
          >
          <b-dropdown-item has-link aria-role="listitem"
            ><router-link to="/insertproject"
              >모집글작성</router-link
            ></b-dropdown-item
          >
          <b-dropdown-item aria-role="listitem" @click="logout()"
            >로그아웃</b-dropdown-item
          >
        </b-dropdown>
      </template>
    </b-navbar>
    <router-view />
  </div>
</template>

<script>
//import { mapState, mapActions } from "vuex";
import router from "./router";

export default {
  data() {
    return {
      loginUser: JSON.parse(sessionStorage.getItem("user")),
    };
  },
  computed: {},
  methods: {
    logout() {
      if (sessionStorage.getItem("user") != null) {
        sessionStorage.removeItem("user");
        router.push({ name: "Home" });
      } else {
        alert("로그인을 먼저 해주세요");
        router.push({ name: "Home" });
      }
    },
  },
};
</script>
