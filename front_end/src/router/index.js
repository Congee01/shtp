import Vue from "vue";
import VueRouter from "vue-router";

import Login from "@/views/Login";
import HomePage from "@/views/User/HomePage.vue";
import Register from "@/views/Register";
import UserCenter from "@/views/User/UserCenter.vue";
import ProductPeek from "@/views/User/ProductPeek";
Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Login",
    component: Login
  },
  {
    path: "/register",
    name: "Register",
    component: Register
  },
  {
    path: "/user",
    name: "user",
    component: HomePage,
    children: [
      {
        path: "/",
        name: "UserHomePage",
        component: HomePage
      }

    ]
  },
  {
    path: "/user/:userId",
    name: "UserCenter",
    component: UserCenter
  },
  {
    path: "/user/:productId",
    name: "ProductPeek",
    component: ProductPeek
  }
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes
});


export default router;
