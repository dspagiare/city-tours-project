<template>
  
    <div class ="main">
    <itineraries-list />
   
    </div>
</template>

<script>
import ItinerariesList from "../components/ItinerariesList.vue";

export default {
  components: {ItinerariesList},


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

 h1 {
   padding-top: 20px;
   padding-bottom: 20px;
 }
 h2 {
   padding-bottom: 20px;
 }
 p{
   padding-bottom: 10px;
 }
.main {
  
  width: 100%;
  overflow: visible;
  
}
</style>
