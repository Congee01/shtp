<template>
  <body>
  <div>
    <v-app-bar color="cyan darken-3" dense dark>
      <v-app-bar-nav-icon @click="direct('/')">
        <v-icon>mdi-home</v-icon></v-app-bar-nav-icon
      >

      <v-toolbar-title @click="direct('/')" class="cursor">
        Course Learning
      </v-toolbar-title>

      <v-spacer></v-spacer>
      <v-btn text @click="direct('/login')">
        登录/注册
      </v-btn>
    </v-app-bar>
    <router-view />
  </div>
  </body>
</template>

<script>
export default {
  name: "DefaultLayout",
  data() {
    return {};
  },
  methods: {
    direct(link) {
      if (this.$route.path !== link) {
        this.$router.push(link);
      }
    },
  },
};
</script>

<style scoped>
body{
  background-image: url("back.png");
  background-color: #69f0ae;
}
.cursor {
  cursor: pointer;
}
</style>
