<template>
  <body>
  <div>
    <v-app-bar color="#69f0ae" dense dark>
      <v-app-bar-nav-icon @click="direct('/user')">
        <v-icon>mdi-home</v-icon></v-app-bar-nav-icon
      >

      <v-toolbar-title @click="direct('/user')" class="cursor">
        欢迎进入二手交易平台
      </v-toolbar-title>

      <v-spacer></v-spacer>

      <v-menu left bottom>
        <template v-slot:activator="{ on, attrs }">
          <v-btn icon v-bind="attrs" v-on="on">
            <v-icon>mdi-dots-vertical</v-icon>
          </v-btn>
        </template>

        <v-list>
          <v-list-item
              v-for="opt in optionList"
              :key="opt.optionName"
              @click="direct(opt.link)"
          >
            <v-list-item-title>{{ opt.optionName }}</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </v-app-bar>
    <v-container class="ma-8 pa-4">
      <v-row>
        <router-link :to="`/user/:userId/create`">
          <v-btn class="ma-4 mb-8" dark color="indigo">
            创建商品
          </v-btn>
        </router-link>
      </v-row>
      <v-row>
        <product-item-sell
            v-for="product in productList"
            :key="product.id"
            :productName="product.name"
            :picture="product.picture"
            :productId="product.id"
            :description="product.intro"
            :cost="product.cost"
            :bought="product.bought"
            :manageable="product.manageable"
            :product-color="colorList[product.id % colorList.length]"
        >
        </product-item-sell>
      </v-row>
    </v-container>
  </div>
  </body>
</template>

<script>
import ProductItemSell from "@/components/ProductItemSell";
import { getManageableProduct } from "@/api/product";

export default {
  name: "SellProduct",
  components: {
    ProductItemSell
  },
  data() {
    return {
      colorList: ["#26A69A", "#00B0FF", "#5C6BC0", "#FFB300", "#E57373"],
      productList: null,
      optionList: [
        {
          optionName: "历史订单",
          link: `/user/${window.localStorage.getItem("userId")}/history`
        },
        {
          optionName: "个人中心",
          link: `/user/${window.localStorage.getItem("userId")}`
        },
        {
          optionName: "我要当卖家",
          link: `/user/${window.localStorage.getItem("userId")}/sell`
        },
        {
          optionName: "我的购买",
          link: `/user/${window.localStorage.getItem("userId")}/bought`
        },
        {
          optionName: "登出",
          link: "/"
        }
      ]
    };
  },
  methods: {
    direct(link) {
      if (link === "/") {
        this.logout();
      }
      if (this.$route.path !== link) {
        this.$router.push(link);
      }
    },
    logout() {
      window.localStorage.removeItem("userId");
      window.localStorage.removeItem("userPhone");
      window.localStorage.removeItem("username");
    }
  },
  mounted() {
    const uid = window.localStorage.getItem("userId");
    if (uid) {
      getManageableProduct(uid).then(res => {
        console.log(res);
        this.productList = res || [];
      });
    }
  }
};
</script>
<style scoped>
body{
  background-color: #69f0ae;
  background-image: url("back.png");
}
</style>
