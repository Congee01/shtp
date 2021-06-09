<template>
  <div id="layout">
    <v-app-bar color="#409EFF" dense dark>
      <v-app-bar-nav-icon @click="direct('/user')">
        <v-icon>mdi-home</v-icon></v-app-bar-nav-icon
      >

      <v-toolbar-title @click="direct('/user')" class="cursor">
        Second-Hand-Trading-Platform
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
      <!-- 搜索商品 -->
      <v-row class="mt-10">
        <v-col>
          <v-chip color="#00B0FF" label text-color="white">
            <v-icon left>
              SHTP
            </v-icon>
            搜索商品
          </v-chip>
        </v-col>
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
        <ProductItem
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
        </ProductItem>
      </v-row>
      <v-row class="d-flex justify-center">
        <v-pagination
          class="mt-4"
          v-model="searchCurrentPage"
          :length="searchTotalPage"
          circle
        ></v-pagination>
      </v-row>
      <v-row class="ma-12">
        <v-divider></v-divider>
      </v-row>
      <v-row class="mt-8 mb-2">
        <v-chip class="ma-2" color="#00B0FF" label text-color="white">
          <v-icon left>
            SHTP
          </v-icon>
          我已经购买的商品
        </v-chip>
      </v-row>
      <v-row>
        <product-item
          cols="12"
          md="4"
          v-for="product in boughtProductList"
          :key="product"
          :productName="product.name"
          :productId="product.id"
          :description="product.intro"
          :cost="product.cost"
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
import ProductItem from "@/components/ProductItem.vue";
import { createOrder } from "@/api/order";
import { getBoughtProduct, getProductByKey } from "@/api/product";

export default {
  name: "HomePage",
  components: {
    ProductItem
  },
  data() {
    return {
      password: "",
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
          optionName: "登出",
          link: "/"
        }
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
      //
      boughtProductList: []
    };
  },
  watch: {
    searchCurrentPage: function() {
      this.handleSearchProduct();
    },

    searchText: function() {
      this.handleSearchProduct();
    }
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

    handleBuyCourse() {
      const uid = window.localStorage.getItem("userId");
      createOrder({
        productId: this.currentProductId,
        productName: this.currentProductName,
        cost: this.currentProductPrice,
        userId: uid,
        status: 2
      }).then(res => {
        this.dialog = false;
        this.msg =
          (res.code === 1 ? "「购买成功」！" : "「购买失败」！") + res.msg;
        this.dialog2 = true;
        this.getUserBoughtCourses();
      });
    },

    handleSearchProduct() {
      console.log("1");
      const uid = window.localStorage.getItem("userId");
      getProductByKey({
        uid,
        key: this.searchText,
        page: this.searchCurrentPage
      }).then(res => {
        console.log(res);
        this.searchProduct = res.list;
        this.searchTotalPage = res.pages;
      });
    },

    getUserBoughtProducts() {
      console.log("2");
      const uid = window.localStorage.getItem("userId");
      getBoughtProduct(uid).then(res => {
        console.log(res);
        this.boughtProductList = res || [];
      });
    }
  },
  mounted() {
    this.handleSearchProduct();
    this.getUserBoughtProducts();
  }
};
</script>

<style scoped></style>
