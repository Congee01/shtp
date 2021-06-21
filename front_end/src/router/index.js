import Vue from "vue";
import VueRouter from "vue-router";
import Login from "@/views/Login";
import HomePage from "@/views/User/HomePage.vue";
import Register from "@/views/Register";
import UserCenter from "@/views/User/UserCenter.vue";
import ProductPeek from "@/views/User/ProductPeek";
import HistoryOrder from "@/views/User/HistoryOrder";
import SellProduct from "@/views/User/SellProduct";
import EditProduct from "@/views/User/EditProduct";
import CreateProduct from "@/views/User/CreateProduct";
import BoughtProduct from "@/views/User/BoughtProduct";
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
    path: "/user/peek/:productId",
    name: "ProductPeek",
    component: ProductPeek
  },
  {
    path: "/user/:userId/history",
    name: "HistoryOrder",
    component: HistoryOrder
  },
  {
    path: "/user/:userId/sell",
    name: "SellProduct",
    component: SellProduct
  },
  {
    path: "/user/:userId/create",
    name: "createProduct",
    component: CreateProduct
  },
  {
    path: "/user/edit/:productId",
    name: "EditProduct",
    component: EditProduct
  },
  {
    path: "/user/:userId/bought",
    name: "BoughtProduct",
    component: BoughtProduct
  }
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes
});

export default router;
