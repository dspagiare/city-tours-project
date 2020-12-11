<template>
  <div id="app">
    <div class = "text-center" id="nav">
      <button class = "btn"><router-link v-bind:to="{ name: 'home' }">Home</router-link></button>
      <button class = "btn"><router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link></button>
<<<<<<< HEAD
      
=======
     
>>>>>>> f047dfe7841fa86ff85c54ef041ede9f062d6799
    </div>
     <router-view />
   
  </div>
</template>
<<<<<<< HEAD


<style>

</style>
<style global> 
* {
  background-color: rgba(17, 171, 243, 0.87);  
  
=======
<script>

export default {
 
}
</script >
<style > 
#app{
background-color: rgba(17, 171, 243, 0.87);

>>>>>>> f047dfe7841fa86ff85c54ef041ede9f062d6799
}
.text-center {
  background-color: whitesmoke;
}
</style>
