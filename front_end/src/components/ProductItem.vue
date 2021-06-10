<template>

  <v-card class="ma-4 pa-2"   >
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
    <h2><img :src="pictureUrl" alt="Tulip" /></h2>

    <v-card-text class="text">
      {{ text}}
    </v-card-text>

    <v-card-action>
      <h3><v-btn v-show="true" @click="handlePeek">
        浏览商品
      </v-btn></h3>
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
      default: "线性代数"
    },
    productId: {
      type: Number,
      default: 0
    },
    picture:{
      type:String,
      default: "defaultImg"
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
      chip: ["免费", "已购"],
      chipColor: ["success", "primary"],
      pictureUrl: require("../assets/"+this.picture),
    };
  },
  methods: {
    buyProduct() {
      this.$emit("buy-product", this.productId, this.productName, this.cost);
    },
    handlePeek() {
      this.$router.push(`/user/peek/${this.productId}`);
    }
  },
  computed: {
    text: function() {
      return this.description.length < 30
        ? this.description
        : this.description.substring(0, 30) + "...";
    },

    // 0 免费  1 已购
    status: function() {
      if (this.cost === 0) {
        return 0;
      } else if (this.bought) {
        return 1;
      }
      return -1;
    }
  }
});
</script>

<style scoped>
h1 {
  font-weight: normal;
  font-size: large;
  color: #3d5afe;
}
h2 {
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1;
}
h3{
  text-align: right;
}
.text {
  font-size: large;
  font-weight: bold;
  height: 85px;
  overflow: hidden;
}
</style>
