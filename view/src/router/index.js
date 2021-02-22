import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";

Vue.use(VueRouter);

// 라우터 가드
// 로그인 유저 거절
const rejectAuthUser = (to, from, next) => {
  if (sessionStorage.getItem("user") != null) {
    next("/");
  } else {
    next();
  }
};
// 비 로그인 유저 거절
const onlyAuthUser = (to, from, next) => {
  if (sessionStorage.getItem("user") == null) {
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
    component: () => import("../views/projectView/Project.vue")
  },
  {
    path: "/projecttracking",
    name: "ProjectTracking",
    component: () => import("../views/ProjectTracking.vue")
  },
  {
    path: "/insertproject",
    name: "insertproject",
    component: () => import("../views/projectView/InsertProject.vue")
  },
  {
    path: "/project/:pjNum/update",
    name: "updateproject",
    component: () => import("../views/projectView/UpdateProject.vue"),
    props: true
  },
  {
    path: "/project/:pjSeq/details",
    name: "details",
    beforeEnter: onlyAuthUser,
    component: () => import("../components/projectCom/ProjectDetails.vue"),
    props: true
  },
  {
    path: "/project/:pjSeq/apps",
    name: "AllApps",
    component: () => import("../components/applicationCom/AllApps.vue"),
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
    path: "/projecttrackinginsert",
    name: "ProjectTrackingInsert",
    component: () => import("@/views/trackingView/ProjectTrackingInsert.vue")
  },
  {
    path: "/projecttrackingdetail",
    name: "ProjectTrackingDetail",
    beforeEnter: onlyAuthUser,
    props: true,
    component: () => import("@/views/trackingView/ProjectTrackingDetail.vue")
  },
  {
    path: "/projecttrackingupdate",
    name: "ProjectTrackingUpdate",
    component: () => import("@/views/trackingView/ProjectTrackingUpdate.vue")
  },
  {
    path: "/appsent",
    name: "AppSuccess",
    component: () => import("@/components/applicationCom/AppSuccess.vue")
  },
  {
    path: "/:catchAll(.*)",
    component: () => import("@/NotFound.vue")
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
