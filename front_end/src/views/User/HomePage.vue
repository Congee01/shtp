<template>
  <div id="layout">
    <app-bar />

    <v-container class="ma-8 pa-4">
      <!-- 搜索商品 -->
      <v-row class="mt-10">
        <v-col>
          <v-text-field
            v-model="searchText"
            outlined
            label="搜索商品"
            append-icon="mdi-magnify"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <product-item
          cols="12"
          md="4"
          v-for="product in searchProduct"
          :key="product.id"
          :productName="product.name"
          :productId="product.id"
          :description="product.intro"
          :cost="product.cost"
          :picture="product.picture"
          :bought="product.bought"
          :manageable="product.manageable"
          @buy-product="showDialog"
        >
        </product-item>
      </v-row>
      <v-row class="d-flex justify-center">
        <v-pagination
          color="#ec9830"
          class="mt-4"
          v-model="searchCurrentPage"
          :length="searchTotalPage"
          circle
        ></v-pagination>
      </v-row>
      <v-row class="ma-12">
        <v-divider></v-divider>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import ProductItem from "@/components/ProductItem.vue";
import { getProductByKey } from "@/api/product";
import AppBar from "@/components/AppBar.vue";

export default {
  name: "HomePage",
  components: {
    ProductItem,
    AppBar,
  },
  data() {
    return {
      password: "",
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
      dialog: false,
      dialog2: false,
      msg: "",
      currentProductId: 0,
      currentProductName: "",
      currentProductPrice: 0,
      currentTab: 0, // 0 1 2
      searchText: "",
      searchProduct: [],
      searchTotalPage: 1,
      searchCurrentPage: 1,
    };
  },
  watch: {
    searchCurrentPage: function() {
      this.handleSearchProduct();
    },

    searchText: function() {
      this.handleSearchProduct();
    },
  },
  methods: {
    showDialog(productId, productName, productPrice) {
      this.currentProductId = productId;
      this.currentProductName = productName;
      this.currentProductPrice = productPrice;
      this.dialog = true;
    },

    handleSearchProduct() {
      console.log("search");
      const uid = window.localStorage.getItem("userId");
      getProductByKey({
        uid,
        key: this.searchText,
        page: this.searchCurrentPage,
      }).then((res) => {
        console.log(res);
        this.searchProduct = res.list;
        this.searchTotalPage = res.pages;
      });
    },
  },
  mounted() {
    this.handleSearchProduct();
  },
};
</script>

<style scoped>
body {
  color: #47341d;
}
</style>
