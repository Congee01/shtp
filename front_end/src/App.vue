<template>
  <body>
  <v-app>
    <div>
      <router-view />
    </div>
  </v-app>
  </body>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "App",

  components: {},

  data: () => ({
    //
  }),
});
</script>

<style lang="scss">
body{
  background-color: #69f0ae;
  background-image: url("back.png");
}
a {
  text-decoration: none;
}
</style>
