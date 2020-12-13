<template>

  <div class="home">
  <div class="main">
    <h1>Yinzer Tours</h1>
    <h2>Welcome to Yinzer Tours!  Designed to help you plan a day of sight seeing throughout the beautiful city of Pittsburgh, PA</h2>
    <p>Below is a list of landmarks based on popularity, Click "Create Itinerary" in the Navigation bar to start building your route! </p>
    <landmarks-list />
  </div> 
    <navigation-bar />
    
  </div>
  
</template>

<script>
import LandmarksList from '../components/LandmarksList.vue';
import NavigationBar from '../components/NavigationBar.vue';

export default {
  components: { 
    NavigationBar,
    LandmarksList
    },        
  name: "home",
 
};
</script>

<style scoped>
 

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
 .pane {
    display: inline-block;
    overflow-y: scroll;
    max-height:450px;
  }
 p{
   padding-bottom: 10px;
 }
.home {
  
  max-width: 1100px;
  margin: 0 auto;
  padding-left: 50px;
  padding-right: 50px;
  color: antiquewhite;  
  font-family: Avenir, Helvetica, san-serif;
  
  overflow: visible;
  z-index: 100;
}
.main {
  width: 79%;
  position: absolute;
  right: 0;
  background-color: rgba(17, 171, 243, 0.87) !important;
  height: 100vh;
  padding-left: 50px;
  padding-right: 50px;
}
</style>
