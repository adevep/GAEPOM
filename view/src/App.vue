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
      </template>
      <template #end>
        <b-dropdown aria-role="list" v-if="userInfo == null">
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
        <b-dropdown aria-role="list" v-else>
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
          <b-dropdown-item aria-role="listitem" @click="logoutActions(userInfo)"
            >로그아웃</b-dropdown-item
          >
        </b-dropdown>
      </template>
    </b-navbar>
    <router-view />
  </div>
</template>

<script>
// store의 state를 연결
import { mapState, mapActions } from "vuex";
export default {
  data() {
    return {};
  },
  computed: {
    ...mapState(["userInfo"])
  },
  methods: {
    // store에 action의 login을 이안에서 사용가능하게 선언
    ...mapActions(["logoutActions"])
  }
};
</script>
