<template>

  <div class="landmarks">
    <div class="isloading" >
        <img src="../assets/giphy.gif" v-if="isLoading" />
    </div>
    <div class="isNotLoading" v-if="!isLoading">
   <div  class="pane">
   <table class="table table-hover" >
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
          <th>Landmark Details</th>
      </tr>
    </thead>
    <tbody >
      <tr v-for="landmark in landmarks" v-bind:key="landmark.id" >    
        
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
        <td><button v-on:click.prevent="showPanel(landmark)">Show Details</button><slideout-panel></slideout-panel></td>
      </tr>
    </tbody>
  </table>
   </div>
   <div class="buttons">
    <button class="btn btn-outline-primary" v-on:click="addLandmarkToItin">Add Landmark</button>
     <label for="itineraries">Select an Itinerary:</label>
      <select name="itineraries" id="itineraries" v-model="selectedItinerary" >
        <option></option> 
        <option v-for="itinerary in itineraries" :key="itinerary.id" >{{itinerary.itinerary_id}}</option>

      </select>
  </div>
    </div>
  </div>
</template>

<script>
import LandmarkDetails from "../components/LandmarkDetails.vue"
import ItineraryService from "../services/ItineraryService.js";
import LandmarksService from '../services/LandmarksService.js'

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
    selectedItinerary: 0,
    landmarkDetailsForm: {
        openOn: "right"
      }
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
        showPanel(landmark) {
        this.$showPanel({
          component: LandmarkDetails,
          cssClass: "LandmarkDetails",
          props: {landmark}
        
      });
    },   
    addLandmarkToItin() {
      ItineraryService.addLandmarkToItinerary(this.selected, this.selectedItinerary, this.$store.state.currentUser);
      window.location.reload();
    }
  }, 
  created() {
            ItineraryService.getUserItineraries(this.$store.state.currentUser).then( (response) => {
                this.itineraries = response.data;
            });
            LandmarksService.list().then( (response) => {
                this.landmarks = response.data;
                this.isLoading = false;
            });
        }
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
 .pane {
    display: inline-block;
    overflow-y: scroll;
    max-height:500px;
    width: 100%;
  }
.landmarks {
  padding-left: 50px;
  color:black; 
  
}
.buttons {
  border: 5px black;
  margin: 10px;
  padding-left: 20px;
  display: flex;
}
.btn.btn-outline-primary {
  margin-right: 20px;
}
</style>
