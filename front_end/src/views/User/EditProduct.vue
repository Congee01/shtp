<template>
  <div>
    <app-bar />
    <v-container class="pl-16 pr-16">
      <form class="pa-12 grey lighten-5 mt-8">
        <v-text-field v-model="productInfo.id" label="商品ID"></v-text-field>
        <v-text-field
          v-model="productInfo.name"
          label="商品名称"
        ></v-text-field>
        <v-text-field
          v-model="productInfo.intro"
          label="商品简介"
        ></v-text-field>
        <v-text-field
          v-model="productInfo.cost"
          label="商品价格"
        ></v-text-field>

        <v-text-field
          v-model="productInfo.createTime"
          label="创建时间"
        ></v-text-field>
        <v-text-field
          v-model="productInfo.managerName"
          label="创建用户名称"
        ></v-text-field>
        <v-btn @click="handleEditSubmit">
          Save
        </v-btn>
        <router-link :to="`/user/:userId/sell`">
          <v-btn>
            close
          </v-btn>
        </router-link>
      </form>
    </v-container>
  </div>
</template>

<script>
import { getProductById, updateProduct } from "@/api/product";
import AppBar from "@/components/AppBar.vue";

export default {
  name: "EditProduct",
  components: {
    AppBar,
  },
  data() {
    return {
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
      productInfo: {},
      currentEditProduct: {
        productId: "",
        productName: "",
        intro: "",
        picture: "",
        createTime: "",
        deleteTime: "",
        cost: 0,
        managerId: "",
        managerName: "",
      },
    };
  },
  methods: {
    loadProduct() {
      console.log("load");
      const { productId } = this.$route.params;
      const uid = window.localStorage.getItem("userId");
      getProductById({ uid, productId }).then((res) => {
        console.log(res);
        this.productInfo = res;
        this.currentEditProduct = res;
        console.log(this.currentEditProduct);
        console.log(this.productInfo);
      });
    },
    handleEditSubmit() {
      console.log("edit");
      updateProduct(this.productInfo).then((res) => {
        this.dialog = false;
        console.log(res);
        this.alertMsg = res.msg;
        this.showAlert = true;
        setTimeout(() => {
          this.showAlert = false;
        }, 1000);
      });
    },
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
  },
  mounted() {
    this.loadProduct();
  },
};
</script>

<style scoped>
body {
}
</style>
