import Vue from "vue";
import Vuex from "vuex";
import router from "../router";
import axios from "axios";

Vue.use(Vuex);

export default new Vuex.Store({
  // 컴포넌트간 공유 데이터 state
  state: {
    // 기본 이미지 url
    imgURL: "http://localhost:80/upload/",
    // 로그인한 유저 받아주는 변수
    userInfo: JSON.parse(sessionStorage.getItem("user")),
    // 각종 많은 유저들 받아줄 변수
    allUsers: []
  },
  getters: {
    // getters 사용 안했음, 연습용
    allUsersCount: state => {
      return state.allUsers.length;
    },
    countOfPosition: state => {
      let count = 0;
      state.allUsers.forEach(user => {
        if (user.position === "개발자") count++;
      });
      return count;
    },
    percentOfDeveloper: (state, getters) => {
      return Math.round(
        (getters.countOfPosition / getters.allUsersCount) * 100
      );
    }
  },
  // state값 변화 로직
  // mutation (state, {data1, data2}) { }
  mutations: {
    // 로그인 성공
    login(state, payload) {
      if (payload !== null) {
        sessionStorage.setItem("user", JSON.stringify(payload));
        state.userInfo = JSON.parse(sessionStorage.getItem("user"));
      }
    },
    // 로그아웃
    logout(state, payload) {
      if (payload !== null) {
        sessionStorage.removeItem("user");
        state.userInfo = null;
      }
    },
    userInfoChange(state, payload) {
      if (payload !== null) {
        sessionStorage.setItem("user", JSON.stringify(payload));
        alert(state.userInfo);
      }
    }
  },

  // 비즈니스 로직 비동기 가능
  // actions는 바로 state값 변화 시키지 않고 mutations 이용해 commit하는 형태로 사용
  /*
  action의 핸들러는 context 객체를 전달인자로 받습니다. 
  context 객체는 store의 메소드와 속성들을 가지고 있는 객체입니다. 
  그래서 context.commit를 호출하여 Mutation의 핸들러를 호출하거나, 
  context.state와 context.getters를 통해 state와 getter에 접근 할 수 있습니다.
  mutations 실행 : commit('함수명', 'parameter')
  actions 실행 : dispatch('함수명', 'parameter')
  */
  actions: {
    loginActions({ commit }, loginObj) {
      axios
        .post(
          "http://localhost:80/login?userid=" +
            loginObj.userid +
            "&password=" +
            loginObj.password
        )
        .then(response => {
          commit("login", response.data);
          router.push({ name: "mypage" });
        })
        .catch(e => {
          alert("ID와 Password를 확인해주세요.");
          console.log(e);
        });
    },
    logoutActions({ commit }, loginObj) {
      commit("logout", loginObj);
      router.push({ name: "Home" });
    }
  }
});
