<template>
  <div id="app">
<<<<<<< HEAD
 
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
=======
    <div class = "text-center" id="nav">
      <button class = "btn"><router-link v-bind:to="{ name: 'home' }">Home</router-link></button>
      <button class = "btn"><router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link></button>
      
>>>>>>> 1a3b813757534da53fc67966aa6bc3c6f6b5bf12
    </div>
    <router-view />

  </div>
</template>

<<<<<<< HEAD
<script>

export default {
    
}
</script>
<style>

</style>
=======
<style global> 
* {
  background-color: rgba(17, 171, 243, 0.87);
  
}


</style>
>>>>>>> 1a3b813757534da53fc67966aa6bc3c6f6b5bf12
