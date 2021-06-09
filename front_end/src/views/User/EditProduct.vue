<template>
  <v-container>
    <v-text-field
      v-model="productInfo.id"
      label="商品ID"
    ></v-text-field>
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
  </v-container>
</template>

<script>
import {getProductById, updateProduct} from "@/api/product";

export default {
  name: "EditProduct",
  data() {
    return {
      productInfo:{},
      currentEditProduct: {
        productId: "",
        productName: "",
        intro: "",
        picture:"",
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
      console.log("load")
      const { productId } = this.$route.params;
      const uid = window.localStorage.getItem("userId");
      getProductById({ uid, productId }).then(res => {
        console.log(res);
        this.productInfo = res;
        this.currentEditProduct=res;
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
  },
  mounted() {
    this.loadProduct();
  },
};
</script>

<style scoped></style>
