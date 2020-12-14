<template>

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
        <map-search /> 
    </div>
</template>

<script>
 import MapSearch from "../components/MapSearch"
 import LandmarksService from "../services/LandmarksService"
 import draggable from 'vuedraggable'
 import ItineraryService from '../services/ItineraryService'


export default {
    name: "itinName",
    props: ["itineraries"],
    components: {
         draggable,
         MapSearch
    },
    
    data() {
        return {
           
            oldIndex: '',
            newIndex: '',
            counter: 2,
            thumbsCounter: 0,
            active: false,
            landmarks: [],
            isLoading: true,
            itinerary_name: ''
        }
    },
    methods: {
        onEnd(evt) {
            console.log(evt)
            this.newIndex = evt.newIndex;
            this.oldIndex = evt.oldIndex;
        },
        deleteTableRow(landId) { 
            this.counter--;
            //this.landmarks.splice(id, 1);
            ItineraryService.deleteLandmarkFromItinerary(this.$route.params.id, landId, this.$store.state.user).then(
                this.$router.push("/")
            )      
        },
       
        mouseOver(){
            this.active = !this.active;
            this.class="thumbs:hover";   
        },
        deleteItinerary() {
            ItineraryService.deleteItinerary(this.$route.params.id, this.$store.state.user).then(
            )
        },
      
    },
    created(){
        //  ItineraryService.getUserItineraries(this.$store.state.currentUser).then( (response) => {
        //     this.itineraries.id = response.data;
        // });
    LandmarksService.getLandmarksForItinerary(this.$route.params.id).then( (response) => {
      this.landmarks = response.data;
      this.isLoading = false;
    });
    ItineraryService.getItineraryDetails(this.$route.params.id).then((response) => {
        this.itinerary_name = response.data.name;
         }
    );
  },
    // beforeUpdated() {
    //         LandmarksService.getLandmarksForItinerary(this.$route.params.id).then( (response) => {
    //             this.landmarks = response.data;
    //             this.isLoading = false;
    //         });
    //         ItineraryService.getItineraryDetails(this.$route.params.id).then((response) => {
    //             this.itinerary_name = response.data.name;
    //         });
    
    // }
}
</script>

<style scoped>
h1 {
   padding-top: 20px;
   padding-bottom: 40px;
 }
 p{
   padding-bottom: 40px;
 }
.itinerary-list {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: #2c3e50;
    padding: 4em;
    cursor: move;
    width: 100%;
    position: absolute;
    right: 0;
    border-width: 2px;
    border-color: black;
    height: 100vh;
    padding-left: 50px;
    padding-right: 50px;
}
.thumbs {
    height: 20px;
    width: 20px;
    padding-block-start: 2px;
    border: .5px solid black;
  
    display: inline-block;
   margin: 0 10px 0 10px;
  
}
.thumbs:hover {
  background: blue;
}
.btn.btn-outline-danger {
    margin-left: 20px;
}
</style>