<template>
  <div>
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

              <v-btn color="warning" text @click="handleRecharge">
                充值
              </v-btn>

              <v-btn color="warning" text @click="dialog = false">
                取消
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </template>

      <app-bar />

      <v-container class="pl-16 pr-16">
        <form class="pa-12 grey lighten-5 mt-8">
          <v-text-field
            v-model="userInfo.id"
            label="ID"
            readonly
          ></v-text-field>
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
          <v-btn color="#00b0ff" dark @click.stop="dialog = true">
            充值
          </v-btn>
          <v-spacer></v-spacer>
        </form>
      </v-container>
    </div>
  </div>
</template>

<script>
import { getUser, recharge } from "@/api/user";
import AppBar from "@/components/AppBar.vue";

export default {
  name: "UserCenter",
  components: {
    AppBar,
  },
  data() {
    return {
      userInfo: {
        id: 0,
        uname: "",
        phone: "",
        password: null,
        picture: null,
        balance: 0,
        createTime: "",
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
      dialog: false,
      showAlert: false,
      value: 0,
      msg: "",
    };
  },

  methods: {
    handleRecharge() {
      recharge({ userId: this.userInfo.id, value: this.value }).then((res) => {
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
      getUser(userId).then((res) => {
        this.userInfo = res || {};
      });
    },
  },

  mounted() {
    this.refreshUserInfo();
  },
};
</script>

<style scoped>
body {
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
