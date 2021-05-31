<template>
  <v-card>
    <v-card-title>
      {{ ProductName }}
      <v-chip
        small
        class="ml-4"
        v-show="status === 1 || status === 0"
        :color="chipColor[status]"
      >
        {{ chip[status] }}
      </v-chip>
    </v-card-title>
    <v-card-picture class="picture">
      <img src="ProductPicture" />
    </v-card-picture>
    <v-card-text class="text">
      {{ text }}
    </v-card-text>
    <v-card-action>
      <v-btn text v-show="status === -1" @click="handlePeek">
        浏览商品
      </v-btn>
      <v-btn
        text
        v-show="status === -1 || (status === 0 && !bought)"
        @click="buyProduct"
      >
        {{ cost === 0 ? "免费购买" : "购买商品" }}
      </v-btn>
    </v-card-action>
  </v-card>
</template>

<script>
export default Vue.extend({
  name: "ProductItem",
  props: {
    ProductName: {
      type: String,
      default: name
    },
    ProductId: {
      type: Number,
      default: 0
    },
    ProductPicture: {
      type: String,
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
      chipColor: ["success", "primary"]
    };
  },
  methods: {
    buyProduct() {
      this.$emit("buy-product", this.ProductId, this.ProductName, this.cost);
    },
    handlePeek() {
      this.$router.push(`/student/peek/${this.ProductId}`);
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
