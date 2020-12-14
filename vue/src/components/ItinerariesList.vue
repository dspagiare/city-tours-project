<template>
  <div class="itinerary-list">
    <div class="header">
      <h1>Details for Itinerary : {{ this.itinerary_name }}</h1>

    <div class="itinerary-list">
        <div class="header">
            <h1 >Details for Itinerary : {{this.itinerary_name}}</h1>

            <p>Add or Remove landmarks, or change your itinerary's starting location here</p>
        </div>
            <div class="isloading" >
                <img src="../assets/giphy.gif" v-if="isLoading" />
            </div >
            <div class="isNotLoading" v-if="!isLoading">
        <table class="table" >
            <thead>
                <tr>
                <th>Location</th>
                <th>Type</th>
                <th>Address</th>
                <th>Index</th>
                <th>How Was It?</th>
                <th>Delete Location</th>
                
                </tr>
            </thead>
            <draggable :list="landmarks.id" @end="onEnd" tag="tbody">
                <tr :id="landmark.id" v-for="(landmark) in landmarks" :key="landmark.id">    
                <td>{{landmark.name}}</td>
                <td>{{landmark.type}}</td>
                <td>{{landmark.address}}</td>
                <td>{{landmark.id}}</td>
                <td><img class="thumbs" src="../assets/thumbs-up.png" v-on:click="landmark.thumbsUp + 1" @mouseover="mouseOver">
                    <img class="thumbs" src="../assets/thumbs-down-icon.png" v-on:click="landmark.thumbsUp + -1" @mouseover="mouseOver">
                </td>
                <td><button class="btn btn-outline-danger" @click='deleteTableRow(landmark.id)' >Delete</button></td>
                </tr>
            </draggable>  
        </table>

                <div class='itin-button'>
                    <button class="btn btn-outline-info">Generate Directions</button>
                    <button class="btn btn-outline-info" @click='deleteItinerary()' >Delete Itinerary</button>
                </div>
            </div>
              <button class="btn btn-outline-info" v-on:click="showForm = !showForm">
          Edit Itinerary
        </button>
        <form v-if="showForm" v-on:submit="update()">
          <div class="field">
            <label for="itinerary_name">Title</label>
            <input
              name="itinerary_name"
              class="form-control"
              type="text"
              v-model="itinerary_name"
              placeholder="Enter New Itinerary Name"
            />
            <label for="itinerary_date">Date</label>
            <input
              name="itinerary_date"
              type="date"
              v-model="itinerary_date"
              placeholder="Enter New Date Of Yinzer Tour"
            />
          </div>
          <div class="actions">
            <button type="submit" class="btn-submit">Save Changes</button>
          </div>
          <button v-on:click="showForm = !showForm" class="btn-cancel">
            Cancel
          </button>
        </form>
      </div>
        <div class="map-search">
        <map-search /> 
        </div>
    </div>
    <map-search />
  </div>
</template>

<script>
import MapSearch from "../components/MapSearch";
import LandmarksService from "../services/LandmarksService";
import draggable from "vuedraggable";
import ItineraryService from "../services/ItineraryService";

export default {
  name: "itinName",
  props: ["itineraries"],
  components: {
    draggable,
    MapSearch,
  },

  data() {
    return {
      oldIndex: "",
      newIndex: "",
      counter: 2,
      thumbsCounter: 0,
      active: false,
      landmarks: [],
      isLoading: true,
      itinerary_name: "",
      itinerary_date: "",
      showForm: false,
    };
  },
  methods: {
    update() {
      const itinerary = {
        id: this.$route.params.id,
        title: this.itinerary_name,
        date: this.itinerary_date,
      };

      ItineraryService.editItinerary(
        itinerary,
        this.$store.state.currentUser
      ).then();
    },
    onEnd(evt) {
      console.log(evt);
      this.newIndex = evt.newIndex;
      this.oldIndex = evt.oldIndex;
    },
    deleteTableRow(landId) {
      this.counter--;
      //this.landmarks.splice(id, 1);
      ItineraryService.deleteLandmarkFromItinerary(
        this.$route.params.id,
        landId,
        this.$store.state.user
      ).then();
    },

    mouseOver() {
      this.active = !this.active;
      this.class = "thumbs:hover";
    },
    deleteItinerary() {
      ItineraryService.deleteItinerary(
        this.$route.params.id,
        this.$store.state.user
      ).then(this.$router.push("/"));
    },
  },
  created() {
    //  ItineraryService.getUserItineraries(this.$store.state.currentUser).then( (response) => {
    //     this.itineraries.id = response.data;
    // });
    LandmarksService.getLandmarksForItinerary(this.$route.params.id).then(
      (response) => {
        this.landmarks = response.data;
        this.isLoading = false;
      }
    );
    ItineraryService.getItineraryDetails(this.$route.params.id).then(
      (response) => {
        this.itinerary_name = response.data.name;
        this.itinerary_date = response.data.date;
      }
    );
  },
};
</script>

<style scoped>
h1 {
   padding-bottom: 40px;
 }
 p{
   padding-bottom: 40px;
 }
.itinerary-list {
    color: #2c3e50;
    width: 100%;
    right: 0;
    border-color: black;
    padding-left: 50px;
    padding-right: 50px;
    margin-top: 100px;
}
.map-search{
    height: 100vh;
    overflow: hidden;
}
.isNotLoading {
    cursor: move;
}
.thumbs {
    height: 20px;
    width: 20px;
    padding-block-start: 2px;
    border: .5px solid black;
    border-radius: 3px;
    display: inline-block;
    margin: 0 10px 0 10px;
}
.thumbs:hover {
  background: blue;
}
.btn.btn-outline-danger {
  margin-left: 20px;
}
.form-control {
  display: block;
  width: 80%;
  height: 30px;
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: #495057;
  border: 1px solid #ced4da;
  border-radius: 0.25rem;
}
</style>