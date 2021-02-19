import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";



Vue.use(VueRouter);
// 로그인 유저 거절
const rejectAuthUser = (to, from, next) => {
  if (sessionStorage.getItem("user") != null) {
    alert("먼저 로그아웃을 해주세요!");
    next("/");
  } else {
    next();
  }
};

// 비 로그인 유저 거절
const onlyAuthUser = (to, from, next) => {
  if (sessionStorage.getItem("user") == null) {
    alert("로그인 해주세요.");
    next("/login");
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
    name: "login",
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
    beforeEnter: onlyAuthUser,
    component: () => import("../views/portfolioView/PortfolioList.vue")
  },
  {
    path: "/insertportfolio",
    name: "insertportfolio",
    beforeEnter: onlyAuthUser,
    component: () => import("../views/portfolioView/InsertPortfolio.vue")
  },
  {
    path: "/updateportfolio",
    name: "updatePortfolio",
    beforeEnter: onlyAuthUser,
    component: () => import("../views/portfolioView/UpdatePortfolio.vue")
  },
  {
    path: "/updateuser",
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
    path: "/insertusercheck",
    name: "insertusercheck",
    beforeEnter: rejectAuthUser,
    component: () => import("../views/loginUserView/InsertUserCheck.vue")
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

    path: "/projectTracking",
    name: "ProjectTracking",
    component: () => import("../views/ProjectTracking.vue")
  },
  {
    path: "/insertproject",
    name: "insertproject",
    component: () => import("../views/InsertProject.vue")
  },
  {
    path: "/project/:pjNum/update",
    name: "updateproject",
    component: () => import("../views/UpdateProject.vue"),
    props: true
  },
  {
    path: "/project/:pjSeq/details",
    name: "details",
    component: () => import("../components/ProjectDetails.vue"),
    props: true
  },
  {
    path: "/project/:pjSeq/apps",
    name: "AllApps",
    component: () => import("../components/AllApps.vue"),
    props: true
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

  },
  {
    path: "/projectTrackingInsert",
    name: "ProjectTrackingInsert",
    component: () => import("@/views/trackingView/ProjectTrackingInsert.vue")
  },
  {
    path: "/projectTrackingDetail",
    name: "ProjectTrackingDetail",
    props: true,
    component: () => import("@/views/trackingView/ProjectTrackingDetail.vue")
  },
  {
    path: "/projectTrackingUpdate",
    name: "ProjectTrackingUpdate",
    component: () => import("@/views/trackingView/ProjectTrackingUpdate.vue")
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
