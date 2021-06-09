<template>
  <v-card>
    <v-card-title class="ma-4 pa-2">
      {{ productName }}
    </v-card-title>
    <img :src="ProductPicture" alt="Tulip"/>
    <v-card-text class="text">
      {{ description }}
    </v-card-text>
    <v-card-action>
      <router-link :to="`/user/edit/${productId}`">
        <v-btn text v-show="manageable">管理商品</v-btn>
      </router-link>
    </v-card-action>
  </v-card>
</template>

<script>
export default {
  name: "ProductItemSell",
  props: {
    productName:{
      type: String,
      default: "线性代数"
    },
    productId: {
      type: Number,
      default: 0
    },
    description: {
      type: String,
      default: "商品简介"
    },
    cost: {
      type: Number,
      default: 0
    },
    bought: {
      type: Boolean,
      default: false
    },
    manageable: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      ProductPicture: require("../assets/defaultImg.jpg")
    }
  },
  computed: {
    text: function() {
      return this.description.length < 60
          ? this.description
          : this.description.substring(0, 60) + "...";
    },
  },
}
</script>

<style scoped>
.text {
  height: 85px;
  overflow: hidden;
}
</style>