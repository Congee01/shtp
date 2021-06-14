<template>
  <body>
  <div>
    <v-app-bar color="#69f0ae" dense dark>
      <v-app-bar-nav-icon @click="direct('/user')">
        <v-icon>mdi-home</v-icon></v-app-bar-nav-icon
      >

      <v-toolbar-title @click="direct('/user')" class="cursor">
        Second-Hand-Trading-Platform
      </v-toolbar-title>

      <v-spacer></v-spacer>

      <v-menu left bottom>
        <template v-slot:activator="{ on, attrs }">
          <v-btn icon v-bind="attrs" v-on="on">
            <v-icon>mdi-dots-vertical</v-icon>
          </v-btn>
        </template>

        <v-list>
          <v-list-item
              v-for="opt in optionList"
              :key="opt.optionName"
              @click="direct(opt.link)"
          >
            <v-list-item-title>{{ opt.optionName }}</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </v-app-bar>
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
          <v-btn @click="buyProduct">{{
              product.cost === 0 ? "免费购买" : "购买商品"
            }}</v-btn>
        </v-col>
      </v-row>
    </v-container>
    <!-- 购买提示对话框 -->
    <v-dialog v-model="dialog" width="500">
      <v-card>
        <v-card-title>购买商品</v-card-title>

        <v-card-text>
          是否花费 「{{ product.cost }}元」购买商品 「{{ product.name }}」？
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="handleBuyProduct">
            确认
          </v-btn>
          <v-btn color="primary" text @click="dialog = false">
            取消
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <!-- 购买结果 -->
    <v-dialog v-model="dialog2" width="500">
      <v-card>
        <v-card-title>购买商品</v-card-title>

        <v-card-text>
          {{ msg }}
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="dialog2 = false">
            确认
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
  </body>
</template>

<script>
import { getProductById } from "@/api/product";
import { createOrder } from "@/api/order";

export default {
  name: "ProductPeek",
  data() {
    return {
      optionList: [
        {
          optionName: "历史订单",
          link: `/user/${window.localStorage.getItem("userId")}/history`
        },
        {
          optionName: "个人中心",
          link: `/user/${window.localStorage.getItem("userId")}`
        },
        {
          optionName: "我要当卖家",
          link: `/user/${window.localStorage.getItem("userId")}/sell`
        },
        {
          optionName: "登出",
          link: "/"
        }
      ],
      product: {},
      dialog: false,
      dialog2: false,
      msg: "",
      currentProductId: 0,
      currentProductName: "",
      currentProductPrice: 0,
      currentTab: 0, // 0 1 2
    };
  },
  methods: {
    direct(link) {
      if (link === "/") {
        this.logout();
      }
      if (this.$route.path !== link) {
        this.$router.push(link);
      }
    },
    buyProduct() {
      console.log("buy1");
      this.dialog = true;
    },
    loadProduct() {
      const { productId } = this.$route.params;
      const uid = window.localStorage.getItem("userId");
      getProductById({ uid, productId }).then(res => {
        this.product = res;
      });
    },
    handleBuyProduct() {
      console.log("buy");
      const uid = window.localStorage.getItem("userId");
      createOrder({
        productId: this.product.id,
        productName: this.product.name,
        cost: this.product.cost,
        userId: uid,
        status: 2
      }).then(res => {
        this.dialog = false;
        this.msg =
          (res.code === 1 ? "「购买成功」！" : "「购买失败」！") + res.msg;
        this.dialog2 = true;
      });
    },
    logout() {
      window.localStorage.removeItem("userId");
      window.localStorage.removeItem("userPhone");
      window.localStorage.removeItem("username");
    }
  },
  mounted() {
    this.loadProduct();
  }
};
</script>

<style scoped>
body{
  background-color: #69f0ae;
  background-image: url("back.png");
}
</style>
