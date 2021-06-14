<template>
  <body>
  <div>
    <v-container class="pl-16 pr-16">
      <!-- alert -->
      <v-alert
          class="alert"
          outlined
          type="success"
          text
          v-show="showSuccessDialog"
          transition="scroll-y-transition"
      >
        登录成功，欢迎{{ username }} !
      </v-alert>
      <!-- alert -->
      <v-alert
          class="alert"
          outlined
          type="warning"
          text
          v-show="showFailDialog"
          transition="scroll-y-transition"
      >
        {{ msg }}
      </v-alert>

      <div class="in-middle">
        <img :src="img" alt="Tulip"/>
      </div>
      <form class="pa-12 grey lighten-5 mt-10 form">
        <div class="title">
          <h1>二手交易平台</h1>
        </div>

        <v-text-field  v-model="phone" label="手机号码"></v-text-field>
        <v-text-field

            v-model="password"
            label="密码"
            type="password"

        ></v-text-field>
        <h2><v-btn  class="ml-0 mt-8 info" color="#69f0ae" dark  @click="handleLogin">
          登录
        </v-btn></h2>
      </form>
      <v-btn
          text

          class="float-right ma-4"
          @click="
          () => {
            this.$router.push('/register');
          }
        "
      >
        还没有账号？点击此处去注册
      </v-btn>
    </v-container>
  </div>
  </body>
</template>

<script>
import { login } from "@/api/user";

export default {
  name: "Login",
  data() {
    return {
      phone: "",
      password: "",
      showSuccessDialog: false,
      showFailDialog: false,
      msg: "",
      username: "",
      img: require("../assets/网站标题.png"),
    };
  },
  methods: {
    handleLogin() {
      login({ phone: this.phone, password: this.password }).then((res) => {
        console.log(res);
        if (res.code === 1) {
          window.localStorage.setItem("userId", res.data.id);
          window.localStorage.setItem("userPhone", res.data.phone);
          window.localStorage.setItem("username", res.data.uname);
          window.localStorage.setItem("userRole", res.data.userRole);
          this.username = res.data.uname;
          this.showSuccessDialog = true;
          setTimeout(() => {
            this.showSuccessDialog = false;
          }, 1000);
          this.$router.push("/user")
        } else {
          this.msg = res.msg;
          this.showFailDialog = true;

          setTimeout(() => {
            this.showFailDialog = false;
          }, 1000);
        }
      });
    },
  },
};
</script>

<style scoped>
body{
  background-color: #69f0ae;
  background-image: url("back.png");
}
.form {
  position: relative;
  top: 20px;
}
.alert {
  position: fixed;
  left: 50%;
  top: 100px;
  z-index: 999;
}
.in-middle{
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1;
}
h1{
  font-family: "Calibri Light",serif;
  font-weight: bolder;
  text-align: center;
}
h2{
  text-align: center;
color: #69f0ae;
}


</style>
