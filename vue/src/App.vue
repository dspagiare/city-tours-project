<template>
  <div id="app">
    
    <div class = "nav" id="nav">
      
      <button class = "btn btn-light"><router-link v-bind:to="{ name: 'home' }">Home</router-link></button>
      <button class = "btn btn-light"><router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link></button>
     
    </div>
   
     <router-view />
  
  </div>
</template>
<script>
export default {
  
}
</script >
<style > 
body{
  margin-top: 100px;
  
        overflow-x:hidden;
        overflow-y:hidden;

}
#app{
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    margin-top: 100px;
    
  
}
.nav {
  position: fixed; 
  top: 0; 
  overflow: hidden;
  background-image: url('assets/option9.png');
  background-repeat: no-repeat;
  background-size: 150vh 100px;
  height: 100px;
  width: 100%;
  background-color: rgba(64, 64, 64, 100);
 
  
}
.img{
  height: 100%;
  width: 100%;
  
  
}
.btn.btn-light{
  height: 75%;
  width: 80px;
  
  margin-left: 40px;
  margin-top: 10px;
  color: black;
  
}
</style>
