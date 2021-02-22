import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    // 기본 이미지 url
    imgURL: "http://localhost:80/upload/",
    // 로그인 유저 체크 버튼 바꿔주는 변수
    loginUser: sessionStorage.getItem("user"),
  },
  mutations: {
    loginUserMut: (state, payload) => {
      state.loginUser = payload;
    },
  },
  actions: {
    loginUserAct: ({ commit }, payload) => {
      commit("loginUserMut", payload);
    },
  },
});
