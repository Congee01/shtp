<template>
  <body>
  <div>
    <!-- alert -->
    <v-alert
        class="alert"
        outlined
        type="success"
        text
        v-show="showAlert"
        transition="scroll-y-transition"
    >
      {{ msg }}
    </v-alert>
    <template>
      <v-dialog v-model="dialog" max-width="400">
        <v-card>
          <v-card-title class="headline">
            账户充值
          </v-card-title>
          <v-text-field
              v-model="value"
              label="充值金额"
              class="pa-6"
          ></v-text-field>

          <v-card-actions>
            <v-spacer></v-spacer>

            <v-btn color="green darken-1" text @click="handleRecharge">
              充值
            </v-btn>

            <v-btn color="green darken-1" text @click="dialog = false">
              取消
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </template>
    <template>
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
      </div>
    </template>
    <v-container class="pl-16 pr-16">
      <form class="pa-12 grey lighten-5 mt-8">
        <v-text-field v-model="userInfo.id" label="ID" readonly></v-text-field>
        <v-text-field
            v-model="userInfo.uname"
            label="用户名"
            readonly
        ></v-text-field>
        <v-text-field
            v-model="userInfo.phone"
            label="手机号"
            readonly
        ></v-text-field>
        <v-text-field
            v-model="userInfo.createTime"
            label="注册时间"
            readonly
        ></v-text-field>
        <v-text-field
            v-model="userInfo.balance"
            label="账户余额"
            readonly
        ></v-text-field>
        <v-btn color="#69f0ae" dark @click.stop="dialog = true">
          充值
        </v-btn>
        <v-spacer></v-spacer>


      </form>
    </v-container>
  </div>
  </body>
</template>

<script>
import { getUser, recharge } from "@/api/user";

export default {
  name: "UserCenter",
  data() {
    return {
      userInfo: {
        id: 0,
        uname: "",
        phone: "",
        password: null,
        picture: null,
        balance: 0,
        createTime: ""
      },
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
          link:`/user/${window.localStorage.getItem("userId")}/sell`
        },
        {
          optionName: "登出",
          link: "/",
        },
      ],
      dialog: false,
      showAlert: false,
      value: 0,
      msg: ""
    };
  },

  methods: {
    handleRecharge() {
      recharge({ userId: this.userInfo.id, value: this.value }).then(res => {
        console.log(res);
        if (res && res.code === 1) {
          this.msg = res.msg;
          this.dialog = false;
          this.showAlert = true;
          this.refreshUserInfo();
          setTimeout(() => {
            this.showAlert = false;
          }, 1000);
        }
      });
    },
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
    refreshUserInfo() {
      const userId = window.localStorage.getItem("userId");
      getUser(userId).then(res => {
        this.userInfo = res || {};
      });
    }
  },

  mounted() {
    this.refreshUserInfo();
  }
};
</script>

<style scoped>
body{
  background-color: #00b0ff;
  background-image: url("back.png");
}
.alert {
  position: fixed;
  left: 50%;
  top: 100px;
  z-index: 999;
}
</style>
