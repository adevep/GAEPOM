<template>
  <!-- <div id="app">
    <div id="nav">
      <router-link to="/">HOME</router-link> |
      <router-link to="/profile">프로필</router-link> |
      <router-link to="/project">프로젝트</router-link> |
      <router-link to="/mentor">이주의 멘토</router-link> |
      <router-link to="/signup">회원가입</router-link> |
      <router-link to="/login">로그인</router-link>
    </div>
    <router-view />
  </div> -->

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
          <b-navbar-item href="#">
            프로젝트 트래커
          </b-navbar-item>
        </b-navbar-dropdown>
      </template>
      <template #end>
        <b-navbar-item tag="div">
          <div class="buttons">
            <b-button tag="router-link" to="/signup" type="is-link">
              회원가입
            </b-button>
            <b-button tag="router-link" to="/login" type="is-link">
              로그인
            </b-button>
            <b-button tag="router-link" to="/mypage" type="is-link">
              마이페이지
            </b-button>
          </div>
        </b-navbar-item>
      </template>
    </b-navbar>
    <router-view />
  </div>
</template>
