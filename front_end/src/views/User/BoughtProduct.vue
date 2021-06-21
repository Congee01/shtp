<template>
  <body>
  <template>
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
    </div>
  </template>
  <v-container class="ma-8 pa-4">
  <v-row>
    <product-item
        cols="12"
        md="4"
        v-for="product in boughtProductList"
        :key="product.id"
        :productName="product.name"
        :productId="product.id"
        :description="product.intro"
        :cost="product.cost"
        :picture="product.picture"
        :bought="product.bought"
        :manageable="product.manageable"
        @buy-course="showDialog"
    >
    </product-item>
  </v-row>
  </v-container>
  </body>
</template>

<script>
import {getBoughtProduct} from "@/api/product";
import ProductItem from "@/components/ProductItem";

export default {
  name: "BoughtProduct",
  components: {
    ProductItem
  },
  data() {
    return {
      boughtProductList: [],
      currentProductId:"",
      currentProductName:"",
      currentProductPrice:"",
      dialog:false,
      optionList: [
        {
          optionName: "历史订单",
          link: `/user/${window.localStorage.getItem("userId")}/history`,
        },
        {
          optionName: "个人中心",
          link: `/user/${window.localStorage.getItem("userId")}`,
        },
        {
          optionName: "我要当卖家",
          link:`/user/${window.localStorage.getItem("userId")}/sell`
        },
        {
          optionName: "我的购买",
          link: `/user/${window.localStorage.getItem("userId")}/bought`
        },
        {
          optionName: "登出",
          link: "/",
        },
      ],
    }
  },
  methods:{
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
    },
    showDialog(productId, productName, productPrice) {
      this.currentProductId = productId;
      this.currentProductName = productName;
      this.currentProductPrice = productPrice;
      this.dialog = true;
    },
    getUserBoughtProducts() {
      console.log("buy");
      const uid = window.localStorage.getItem("userId");
      getBoughtProduct(uid).then(res => {
        console.log(res);
        this.boughtProductList = res || [];
      });
    }
  },
  mounted() {
    this.getUserBoughtProducts();
  }
}
</script>

<style scoped>

</style>