<template>
  <body>
    <div>
      <v-app-bar color="#409EFF" dense dark>
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
    </div>
  </body>
</template>

<script>
export default {
  name: "UserLayout",
  data() {
    return {
      optionList: [
        {
          optionName: "历史订单",
          link: "/user/history"
        },
        {
          optionName: "个人中心",
          link: `/user/${window.localStorage.getItem("userId")}`
        },
        {
          optionName: "我要当卖家",
          link: "/user/sell"
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
  }
};
</script>

<style scoped>
body {
  background-image: url("back.png");
  background-color: #69f0ae;
}
.cursor {
  cursor: move;
  background-color: #69f0ae;
}
</style>
