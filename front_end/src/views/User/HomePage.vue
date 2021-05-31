<template>
  <v-text-field
      v-model="password"
      label="密码"
      type="password"
  ></v-text-field>
</template>

<script>
export default {
  name: "HomePage",
  data(){
    return{
      password:""
    };
  }
}
</script>

<style scoped>

</style>