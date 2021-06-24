<template>
  <div>
    <app-bar />
    <div>
      <!-- alert -->
      <v-alert
        class="alert"
        outlined
        type="success"
        text
        v-show="showSuccessDialog"
        transition="scroll-y-transition"
      >
        商品创建成功！
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
      <v-container class="pl-16 pr-16">
        <form class="pa-12 grey lighten-5 mt-8">
          <v-text-field
            v-model="productInfo.name"
            label="商品名称"
          ></v-text-field>
          <v-textarea v-model="productInfo.intro" label="商品简介"></v-textarea>
          <v-text-field
            v-model="productInfo.cost"
            label="商品价格(元)"
          ></v-text-field>
          <v-form ref="uploadFileForm">
            <v-file-input
              hide-input
              prepend-icon="mdi-plus"
              v-model="ImgInfo"
              @change="handleUploadFile"
            >
            </v-file-input>
          </v-form>
          <div>
            <img :src="ImgInfo" />
          </div>
          <v-btn class="ml-0 mt-8 info" color="warning" @click="submit">
            确认
          </v-btn>
        </form>
      </v-container>

      <!-- 提示对话框 -->
      <v-dialog v-model="dialog" width="500">
        <v-card>
          <v-card-title> </v-card-title>

          <v-card-text>
            商品创建成功。
          </v-card-text>

          <v-divider></v-divider>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="primary" text @click="dialog = false">
              确认
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
  </div>
</template>

<script>
import { createProduct } from "@/api/product";
import { uploadFile } from "@/api/file";
import AppBar from "@/components/AppBar.vue";

export default {
  name: "CreateProduct",
  components: {
    AppBar,
  },
  data() {
    return {
      productInfo: {
        name: "",
        intro: "",
        picture: "",
        cost: 0,
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
      ImgInfo: null,
      dialog: false,
      showSuccessDialog: false,
      showFailDialog: false,
      msg: "",
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
    async handleUploadFile() {
      console.log("up");
      const formData = new window.FormData();
      formData.append("file", this.ImgInfo);
      const res = await uploadFile(formData);
      this.productInfo.picture = res.data.name;
      console.log(res);
    },
    submit() {
      const uid = window.localStorage.getItem("userId");
      const uname = window.localStorage.getItem("username");
      const payload = {
        ...this.productInfo,
        managerId: uid,
        managerName: uname,
        bought: false,
        manageable: true,
      };
      console.log("submit");
      console.log(payload);
      createProduct(payload).then((res) => {
        console.log(res);
        if (res.code === 1) {
          this.showSuccessDialog = true;
          setTimeout(() => {
            this.showSuccessDialog = false;
          }, 1000);
        } else {
          this.showFailDialog = true;
          this.msg = res.msg;
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
body {
}
.alert {
  position: fixed;
  left: 50%;
  top: 100px;
  z-index: 999;
}
</style>
