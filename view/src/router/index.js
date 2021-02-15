import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home
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
    path: "/portfolio",
    name: "Portfolio",
    component: () => import("../views/Portfolio.vue")
  },
  {
    path: "/login",
    name: "Login",
    component: () => import("../views/Login.vue")
  },
  {
    path: "/signup",
    name: "SignUp",
    component: () => import("../views/SignUp.vue")
  },
  {
    path: "/mypage",
    name: "MyPage",
    component: () => import("../views/MyPage.vue")
  }
];

const router = new VueRouter({
  mode: "history",
  routes
});

export default router;
