<template>

  <div class="landmarks">
    <div class="isloading" >
        <img src="../assets/giphy.gif" v-if="isLoading" />
    </div>
    <div class="isNotLoading" v-if="!isLoading">
   <div  class="text-uppercase text-bold">id selected: {{selected}}</div>
   <div  class="pane">
   <table class="table table-striped table-hover" >
     <thead>
        <tr>
			<th>
                <label class="form-checkbox">
                    <input type="checkbox" v-model="selectAll" @click="select">
                    <i class="form-icon"></i>
                    </label>
			</th>
          <th>Index</th>
          <th>Location Name</th>
          <th>Location Type</th>
          <th>Address</th>
          <th>Up Votes</th>
      </tr>
    </thead>
    <tbody >
      <tr v-for="landmark in landmarks" v-bind:key="landmark.id">
        <td>
            <label class="form-checkbox">
                <input type="checkbox" :value="landmark.id" v-model="selected">
					<i class="form-icon"></i>
            </label>
		</td>
        <td>{{landmark.id}}</td>
        <td>{{landmark.name}}</td>
        <td>{{landmark.type}}</td>
        <td>{{landmark.address}}</td>
        <td>{{landmark.thumbsUp}}</td>
      </tr>
    </tbody>
  </table>
   </div>
   <div class="buttons">
    <button class="add-landmark" v-on:click="addLandmarkToItin">Add Landmark</button>
     <label for="cars">Select an Itinerary:</label>
      <select name="itineraries" id="itineraries" v-model="selectedItinerary" >
        <option></option> 
        <option v-for="itinerary in itineraries" :key="itinerary.id" >{{itinerary.itinerary_id}}</option>

      </select>
  </div>
    </div>
  </div>
</template>

<script>
import ItineraryService from "../services/ItineraryService.js";
import landmarksService from '../services/LandmarksService.js'

export default {
  components: { 
    },        
 name: "landmarks",
 data: () => ({
    landmarks: [],
		selected: [],
    selectAll: false,
    isLoading: true,
    itineraries: [],
    selectedItinerary: 0


	}),
	methods: {
		select() {
			this.selected = [];
			if (!this.selectAll) {
				for (let i in this.landmarks) {
					this.selected.push(this.landmarks[i].id);
				}
			}
        },
        
    addLandmarkToItin() {
      ItineraryService.addLandmarkToItinerary(this.selected, this.selectedItinerary, this.$store.state.currentUser)
      }
    },  
   created() {
            
            ItineraryService.getUserItineraries(this.$store.state.currentUser).then( (response) => {
                this.itineraries = response.data;
            });
            landmarksService.list().then( (response) => {
                this.landmarks = response.data;
                this.isLoading = false;
            });
        }
};
</script>

<style scoped>
 .add-landmark{
   margin-right: 20px
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
 .pane {
    display: inline-block;
    overflow-y: scroll;
    max-height:400px;
  }
 p{
   padding-bottom: 10px;
 }
.landmarks {
  
  max-width: 1100px;
  margin: 0 auto;
  padding-left: 50px;
  padding-right: 50px;
  color:black; 
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
.buttons {
  border: 5px black;
  margin: 10px;
}
</style>
