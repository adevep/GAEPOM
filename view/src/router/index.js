import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Login from "../views/loginUserView/Login.vue";
import MyPage from "../views/loginUserView/MyPage.vue";
import InsertUser from "../views/loginUserView/InsertUser.vue";
import UpdateUser from "../views/loginUserView/UpdateUser.vue";

Vue.use(VueRouter);
// 로그인한 유저는 reject
const rejectAuthUser = (to, from, next) => {
  if (sessionStorage.getItem("user") != null) {
    // 이미 로그인 된 유저니까 막아야 함
    alert("이미 로그인을 하였습니다.");
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
    component: Login
  },
  {
    path: "/mypage",
    name: "mypage",
    beforeEnter: onlyAuthUser,
    component: MyPage,
    children: [
      {
        path: "update",
        name: "updateUser",
        component: UpdateUser
      }
    ]
  },
  {
    path: "/insertuser",
    name: "insertuser",
    component: InsertUser
  },
  {
    path: "/profile",
    name: "Profile",
    component: () => import("../views/Profile.vue")
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
