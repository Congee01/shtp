<template>
  <v-card class="ma-4 pa-2">
    <v-card-title class="ma-4 pa-2">
      <h1>{{ productName }}</h1>
    </v-card-title>
    <img :src="pictureUrl" alt="Tulip" />
    <v-card-text class="text">
      {{ text }}
    </v-card-text>
    <v-card-action>
      <router-link :to="`/user/edit/${productId}`">
        <h3><v-btn v-show="manageable">管理商品</v-btn></h3>
      </router-link>
    </v-card-action>
  </v-card>
</template>

<script>
export default {
  name: "ProductItemSell",
  props: {
    productName: {
      type: String,
      default: "线性代数",
    },
    productId: {
      type: Number,
      default: 0,
    },
    picture: {
      type: String,
      default: "defaultImg",
    },
    description: {
      type: String,
      default: "商品简介",
    },
    cost: {
      type: Number,
      default: 0,
    },
    bought: {
      type: Boolean,
      default: false,
    },
    manageable: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      pictureUrl: "",
    };
  },
  computed: {
    text: function() {
      return this.description.length < 20
        ? this.description
        : this.description.substring(0, 20) + "...";
    },
  },
  mounted() {
    this.pictureUrl = require("../assets/" + this.picture);
  },
};
</script>

<style scoped>
h1 {
  font-family: "Arial Black";
  font-weight: bolder;
  font-size: large;
  color: #69f0ae;
}
h3 {
  text-align: right;
}
.text {
  font-size: large;
  font-weight: bold;

  height: 85px;
  overflow: hidden;
}
img {
  width: 300px;
  height: 300px;
  border: 3px solid #69f0ae;
}
</style>
