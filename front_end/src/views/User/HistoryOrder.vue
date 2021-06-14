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
    <v-container class="ma-8 pa-4">
      <v-card
          v-for="o in orderList"
          :key="o.id"
          :color="colorList[o.id % colorList.length]"
          class="ma-8 pa-4"
      >
        <v-card-title class="headline">
          {{ o.productName }}
        </v-card-title>
        <v-card-subtitle class="mt-1">
          购买时间: {{ o.createTime }}
        </v-card-subtitle>
        <v-card-text> 购买价格： {{ o.cost }} </v-card-text>
        <v-card-actions class="pa-0 pl-2"> </v-card-actions>
      </v-card>
    </v-container>
  </div>
  </body>
</template>

<script>
import { getOrdersByUser } from "@/api/order";

export default {
  name: "HistoryOrder",
  data() {
    return {
      colorList: [
        "#FFAB91",
        "#26A69A",
        "#039BE5",
        "#546E7A",
        "#B39DDB",
        "#EF9A9A"
      ],
      orderList: [],
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
      ]
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
    logout() {
      window.localStorage.removeItem("userId");
      window.localStorage.removeItem("userPhone");
      window.localStorage.removeItem("username");
    }
  },
  mounted() {
    const uid = window.localStorage.getItem("userId");
    getOrdersByUser(uid).then(res => {
      console.log(res);
      this.orderList = res || [];
    });
  }
};
</script>
<style scoped>
body{

  background-color: #00b0ff;
}

</style>
