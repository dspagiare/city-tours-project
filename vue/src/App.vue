<template>
  <div id="app">
    <div class = "text-center" id="nav">
      <button class = "btn"><router-link v-bind:to="{ name: 'home' }">Home</router-link></button>
      <button class = "btn"><router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link></button>
      
    </div>
    <router-view />
  </div>
</template>

<style global> 
* {
  background-color: rgba(17, 171, 243, 0.87);
  
}


</style>