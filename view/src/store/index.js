import Vue from "vue";
import Vuex from "vuex";
//import router from "../router";
//import axios from "axios";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    // 기본 이미지 url
    imgURL: "http://localhost:80/upload/"
  },
  mutations: {},
  actions: {}
});
