<template>
  
    <div class ="main">
      <h1>Details for Itinerary *ITINERARY NAME*:</h1>
      <p>Add or Remove landmarks, or change your itinerary's starting location here</p>
    <!-- <itinerary-details /> -->
    <itineraries-list />
    </div>
</template>

<script>

import ItinerariesList from '../components/ItinerariesList.vue'

export default {
  components: {ItinerariesList,  },

}


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

 p{
   padding-bottom: 10px;
 }

.main {
  width: 100%;
  margin-top: 100px;
  right: 0;
  border-color: black;
  border-width: 10px;
  height: 100vh;
  padding-left: 50px;
  padding-right: 50px;
}
</style>
