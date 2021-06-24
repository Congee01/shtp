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
:root {
  --light-font: #ffffff;
  --dark-font: #000000;
}

html {
  overflow: auto;
}
#app{
  background-image: url("./assets/bg.jpg");
  background-repeat: no-repeat;
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
