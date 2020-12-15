<template>
  <div id="app">
    <div class = "nav" id="nav" >
      <button v-if="$store.state.token != ''" class = "btn btn-light" ><router-link v-bind:to="{ name: 'home' }">Home</router-link></button>
      <button v-if="$store.state.token != ''" class = "btn btn-light"><router-link v-bind:to="{ name: 'logout' }" >Logout</router-link></button>
     
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
  background-size: 1300px 100px;
  height: 100px;
  width: 100%;
  background-color: rgba(64, 64, 64, 100);
  margin-bottom: 100px;
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
