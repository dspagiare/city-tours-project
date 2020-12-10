<template>

  <div class="home">
    <div>
    <h1>Yinzer Tours</h1>
    <h2>Welcome to Yinzer Tours!  Designed to help you plan a day of sight seeing throughout the beautiful city of Pittsburgh, PA</h2>
    <p>Below is a list of landmarks based on popularity, Click "Create Itinerary" in the Navigation bar to start building your route! </p>
    </div>
   <table class="table">
     <thead>
        <tr>
          <th>Index</th>
          <th>Location Name</th>
          <th>Location Type</th>
          <th>Address</th>
      </tr>
    </thead>
    <tbody v-for="landmark in landmarks" v-bind:key="landmark.id">
      <tr>
        <td>{{landmark.id}}</td>
        <td>{{landmark.name}}</td>
        <td>{{landmark.type}}</td>
        <td>{{landmark.address}}</td>
      </tr>
    </tbody>
  </table>
  </div>
</template>

<script>


export default {
  name: "home",
  data() {
    return{
      landmarks: [
        {
          id: 1,
          name:"Cathedral of Learning",
          address: "4200 Fifth Ave, Pittsburgh, PA 15260",
          type: "National Historic Landmark",
          description: "The Cathedral of Learning is a building that serves as the centerpiece of the University of Pittsburgh's main campus in the Oakland neighborhood "
        },
        {
          id: 2,
          name:"Canton Avenue",
          address: "Canton Ave, Pittsburgh, PA 15216",
          type: "City Feature",
          description: "Canton Avenue, in Pittsburgh, Pennsylvania's Beechview neighborhood, is the steepest officially recorded public street in the United States. Canton Avenue is 630 ft long and is claimed to include a 37 percent grade 21 ft feet long."
        }, 
        {
          id: 3,
          name:"Frick Park",
          address: "1981 Beechwood Blvd, Pittsburgh, PA 15217",
          type: "Park",
          description: "Frick Park is the largest municipal park in Pittsburgh, Pennsylvania, covering 644 acres. It is one of Pittsburgh's four historic large parks. The park began when Henry Clay Frick, upon his death in 1919, bequeathed 151 acres south of Clayton, his Point Breeze mansion."
        },
        {
          id: 4,
          name:"Carnegie Museum of Natural History",
          address: "4400 Forbes Ave, Pittsburgh, PA 15213",
          type: "Museum",
          description: "Frick Park is the largest municipal park in Pittsburgh, Pennsylvania, covering 644 acres. It is one of Pittsburgh's four historic large parks. The park began when Henry Clay Frick, upon his death in 1919, bequeathed 151 acres south of Clayton, his Point Breeze mansion."
        },
        {
          id: 5,
          name:"Carrie Furnace",
          address: "801 Carrie Furnace Blvd, Rankin, PA 15104",
          type: "National Historic Landmark",
          description: "Carrie Furnace is a former blast furnace located along the Monongahela River in the Pittsburgh area industrial town of Swissvale, Pennsylvania, and it had formed a part of the Homestead Steel Works. The Carrie Furnaces were built in 1884 and they operated until 1982"
        }, 
        ]
    }
  },
  components: {

    }
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
