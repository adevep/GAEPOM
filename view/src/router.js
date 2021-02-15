import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      alias: "/portfolio",
      name: "portfolio",
      component: () => import("./components/PortfolioList")
    },
    {
      path: "/portfolio/:pfSeq",
      name: "portfolio-details",
      component: () => import("./components/Portfolio")
    },
    {
      path: "/add",
      name: "add",
      component: () => import("./components/AddPortfolio")
    }
  ]
});