<template>
  <div id="app">
    <div class = "text-center" id="nav">
      <button class = "btn"><router-link v-bind:to="{ name: 'home' }">Home</router-link></button>
      <button class = "btn"><router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link></button>
      
    </div>
    <router-view />
  </div>
</template>

<style scoped>
  #nav {
    
      }
 /* div#nav {  
   height: 100%;
   width: 20%; 
    float: top; 
     z-index: 1;
      left: 0;  
      top: 0;  
      padding-top: 20px;  
      padding-bottom: 20px;  
       overflow-x: hidden;  
       border-right: solid lightgrey 1px;} */
</style>