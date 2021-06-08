<template>
  <div>
    <v-container class="pl-16 pr-16">
      <v-row>
        <v-col>
          <form class="pa-12 grey lighten-5 mt-8">
            <v-text-field
              v-model="product.name"
              label="商品名称"
              readonly
            ></v-text-field>
            <v-text-field
              v-model="product.id"
              label="商品ID"
              readonly
            ></v-text-field>
            <v-textarea
              v-model="product.intro"
              label="商品简介"
              readonly
            ></v-textarea>
            <v-text-field
              v-model="product.createTime"
              label="创建时间"
              readonly
            ></v-text-field>
            <v-text-field
              v-model="product.managerId"
              label="创建用户"
              readonly
            ></v-text-field>
            <v-text-field
              v-model="product.cost"
              label="商品价格(元)"
              readonly
            ></v-text-field>
          </form>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import { getProductById } from "@/api/product";

export default {
  name: "ProductPeek",
  data(){
    return{
      product:{},
    }
  },
  methods: {
    loadProduct() {
      const { productId } = this.$route.params;
      const uid = window.localStorage.getItem("userId");
      getProductById({ uid, productId }).then(res => {
        this.product = res;
      });
    }
  },
  mounted() {
    this.loadProduct();
  }
};
</script>

<style scoped></style>
