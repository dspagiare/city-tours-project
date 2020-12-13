<template>
  <div class = "home">
    <div class ="main">
      <h1>Details for Itinerary *ITINERARY NAME*:</h1>
      <p>Add or Remove landmarks, or change your itinerary's starting location here</p>
    <!-- <itinerary-details /> -->
    <itineraries-list />
    </div>
    <navigation-bar />
  </div>  
</template>

<script>
import NavigationBar from '../components/NavigationBar.vue'
import ItinerariesList from '../components/ItinerariesList.vue'

export default {
  components: {ItinerariesList,  NavigationBar },

}


</script>

<style>

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
