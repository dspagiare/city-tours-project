<template>

  <div class="home">
    <navigation-bar />
  </div>
  
</template>

<script>
import NavigationBar from '../components/NavigationBar.vue';
export default {
  components: { NavigationBar },
  name: "home"
};
</script>
<style scoped>
 
  .home {
    padding-left: 50px;
    padding-right: 50px;
    color: antiquewhite;
    background-color: blue;
    font-family: Avenir, Helvetica, san-serif;
    height: 100vh;
    width: 100vw;
    overflow: visible;
    z-index: 100;
  }
 h1 {
   padding-top: 20px;
   padding-bottom: 20px;
 }
 h2 {
   padding-bottom: 20px;
 }
 .table {
   background-color: lightgray;
 }

 p{
   padding-bottom: 10px;
 }

</style>
