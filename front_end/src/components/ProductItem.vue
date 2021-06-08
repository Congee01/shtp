<template>
  <v-card>
    <v-card-title class="title">
      {{ ProductName }}
    </v-card-title>
    <img :src="ProductPicture" alt="Tulip"/>
    <v-card-text class="text">
      {{ text }}
    </v-card-text>
    <v-card-action>
      <v-btn text v-show="true" @click="handlePeek">
        浏览商品
      </v-btn>
    </v-card-action>
  </v-card>
</template>

<script>
import Vue from "vue";
export default Vue.extend({
  name: "ProductItem",
  props: {
    ProductName:{
      type: String,
      default: "线性代数"
    },
    ProductId: {
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
      this.$emit("buy-product", this.ProductId, this.ProductName, this.cost);
    },
    handlePeek() {
      this.$router.push(`/user/peek/${this.ProductId}`);
    }
  },
  computed: {
    text: function() {
      return this.description.length < 60
        ? this.description
        : this.description.substring(0, 60) + "...";
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
