<template>
  <div id="app">
    <div class="text-center" id="nav">
      <button class="btn">
        <router-link v-bind:to="{ name: 'home' }">Home</router-link>
      </button>
      <button class="btn">
        <router-link
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
          >Logout</router-link
        >
      </button>
    </div>
    <router-view />
  </div>
</template>
<script>
export default {};
</script >
<style >
#app {
  background-color: rgba(17, 171, 243, 0.87);
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
.text-center {
  background-color: whitesmoke;
}
</style>
