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
#app{
  background-image: url("back.png");
  background-size: cover;
  margin:0;
  padding:0
}
body{

}
a {
  text-decoration: none;
}
</style>
