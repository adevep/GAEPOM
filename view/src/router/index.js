import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";



Vue.use(VueRouter);
// 로그인한 유저는 reject
const rejectAuthUser = (to, from, next) => {
  if (sessionStorage.getItem("user") != null) {
    // 이미 로그인 된 유저니까 막아야 함
    alert("먼저 로그아웃을 해주세요!");
    // 홈으로 redirect
    next("/");
  } else {
    next();
  }
};

// if (store.state.isLogin === false) {
const onlyAuthUser = (to, from, next) => {
  if (sessionStorage.getItem("user") == null) {
    // 아직 로그인 안 된 유저니까 막아야 함
    alert("로그인 해주세요.");
    // 홈으로 redirect
    next("/");
  } else {
    next();
  }
};
const routes = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/login",
    name: "Login",
    // 라우터 들어오기전에 이 함수 실행
    beforeEnter: rejectAuthUser,
    component: () => import("../views/loginUserView/Login.vue")
  },
  {
    path: "/mypage",
    name: "mypage",
    beforeEnter: onlyAuthUser,
    component: () => import("../views/loginUserView/MyPage.vue")
  },
  {
    path: "/yourpage",
    name: "yourpage",
    beforeEnter: onlyAuthUser,
    component: () => import("../views/loginUserView/YourPage.vue")
  },
  {
    path: "/portfolios",
    name: "portfoliolist",
    component: () => import("../views/portfolioView/PortfolioList.vue")
  },
  {
    path: "/insertportfolio",
    name: "insertportfolio",
    component: () => import("../views/portfolioView/InsertPortfolio.vue")
  },
  {
    path: "/updateportfolio",
    name: "updatePortfolio",
    component: () => import("../views/portfolioView/UpdatePortfolio.vue")
  },
  {
    path: "/updateloginuser",
    name: "updateUser",
    beforeEnter: onlyAuthUser,
    component: () => import("../views/loginUserView/UpdateUser.vue")
  },
  {
    path: "/insertuser",
    name: "insertuser",
    beforeEnter: rejectAuthUser,
    component: () => import("../views/loginUserView/InsertUser.vue")
  },
  {
    path: "/profile",
    name: "Profile",
    component: () => import("../views/profileView/Profile.vue")
  },
  {
    path: "/project",
    name: "Project",
    component: () => import("../views/Project.vue")
  },
  {
    path: "/mentor",
    name: "Mentor",
    component: () => import("../views/Mentor.vue")
  },
  {
    path: "/signup",
    name: "SignUp",
    component: () => import("../views/SignUp.vue")
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
