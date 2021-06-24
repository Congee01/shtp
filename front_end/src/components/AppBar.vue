<template>
  <v-app-bar color="#47341d" dense dark class="app-font">
    <v-app-bar-nav-icon @click="direct('/user')">
      <v-icon>mdi-home</v-icon></v-app-bar-nav-icon
    >

    <v-toolbar-title @click="direct('/user')" class="cursor">
      欢迎进入二手交易平台
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
</template>

<script>
export default {
  name: "AppBar",
  data() {
    return {
      optionList: [
        {
          optionName: "历史订单",
          link: `/user/${window.localStorage.getItem("userId")}/history`,
        },
        {
          optionName: "个人中心",
          link: `/user/${window.localStorage.getItem("userId")}`,
        },
        {
          optionName: "我要当卖家",
          link: `/user/${window.localStorage.getItem("userId")}/sell`,
        },
        {
          optionName: "我的购买",
          link: `/user/${window.localStorage.getItem("userId")}/bought`,
        },
        {
          optionName: "登出",
          link: "/",
        },
      ],
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
    },
  },
};
</script>

<style scoped>
.app-font {
  color: var(--light-font);
}
</style>
