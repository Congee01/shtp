<template>
  <v-card class="ma-4 pa-2">
    <v-card-title class="ma-4 pa-2">
      <h1>商品名称：{{ productName }}</h1>
      <v-chip
        small
        class="ml-4"
        v-show="status === 1 || status === 0"
        :color="chipColor[status]"
      >
        {{ chip[status] }}
      </v-chip>
    </v-card-title>
    <v-spacer></v-spacer>
    <img :src="pictureUrl" alt="Tulip" />

    <v-card-text class="text">
      {{ text }}
    </v-card-text>

    <v-card-action>
      <h3>
        <v-btn v-show="true" color="warning" @click="handlePeek">
          浏览商品
        </v-btn>
      </h3>
    </v-card-action>
  </v-card>
</template>

<script lang="ts">
import Vue from "vue";
export default Vue.extend({
  name: "ProductItem",
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
      chip: ["免费", "已购"],
      chipColor: ["primary", "success"],
      // pictureUrl: require("../assets/51231e29-650a-4745-8259-7182183fd722.jpg"),
    };
  },
  methods: {
    buyProduct() {
      this.$emit("buy-product", this.productId, this.productName, this.cost);
    },
    handlePeek() {
      this.$router.push(`/user/peek/${this.productId}`);
    },
  },
  computed: {
    pictureUrl() {
      // return `http://localhost:8081/img/${this.picture}`;
      return require("../assets/" + this.picture);
      // return `C:\\Users\\Alice Cheang\\Desktop\\shtp\\img\\${this.picture}`
    },
    text: function() {
      return this.description.length < 20
        ? this.description
        : this.description.substring(0, 20) + "...";
    },

    // 0 免费  1 已购
    status: function() {
      if (this.cost === 0) {
        return 0;
      } else if (this.bought) {
        return 1;
      }
      return -1;
    },

    chippedColor() {
      return this.status < 0 ? null : this.chipColor[this.status];
    },
  },
});
</script>

<style scoped>
h1 {
  font-family: "Arial Black";
  font-weight: bolder;
  font-size: large;
  color: #000000;
}

h3 {
  text-align: right;
}
.text {
  color: #000000;
  font-size: large;
  font-weight: bold;
  height: 85px;
  overflow: hidden;
}
img {
  width: 300px;
  height: 300px;
  border: 3px solid #e6b252;
}
</style>
