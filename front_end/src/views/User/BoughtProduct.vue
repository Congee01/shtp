<template>
  <div>
    <app-bar />

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
  </div>
</template>

<script>
import { getBoughtProduct } from "@/api/product";
import ProductItem from "@/components/ProductItem";
import AppBar from '@/components/AppBar.vue';

export default {
  name: "BoughtProduct",
  components: {
    ProductItem,
    AppBar,
  },
  data() {
    return {
      boughtProductList: [],
      currentProductId: "",
      currentProductName: "",
      currentProductPrice: "",
      dialog: false,
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
          link: `/user/${window.localStorage.getItem("userId")}/sell`,
        },
        {
          optionName: "我的购买",
          link: `/user/${window.localStorage.getItem("userId")}/bought`,
        },
        {
          optionName: "登出",
          link: "/",
        },
      ],
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
      getBoughtProduct(uid).then((res) => {
        console.log(res);
        this.boughtProductList = res || [];
      });
    },
  },
  mounted() {
    this.getUserBoughtProducts();
  },
};
</script>

<style scoped></style>
