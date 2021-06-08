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
      <v-btn text v-show="true" @click="handlePeek">
        浏览商品
      </v-btn>
    </v-card-action>
  </v-card>
</template>

<script lang="ts">
import Vue from "vue";
export default Vue.extend({
  name: "ProductItem",
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
      chip: ["免费", "已购"],
      chipColor: ["success", "primary"],
      ProductPicture: require("../assets/defaultImg.jpg")
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
    }
  }
});
</script>

<style scoped>
.text {
  height: 85px;
  overflow: hidden;
}
</style>
