<template>

    <div class="itinerary-list">
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
                <td><button @click='deleteTableRow(landmark.id)' >Delete</button></td>
                </tr>
            </draggable>  
        </table>
         
        <p><strong>Previous Index: </strong>{{oldIndex}}</p>
        <p><strong>New Index: </strong>{{newIndex}}</p>
        <div class='itin-button'>
            <button>Generate Directions</button>
            <button @click='deleteItinerary()' >Delete Itinerary</button>

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
   
    components: {
         draggable,
         MapSearch
        
    },
    data() {
        return {
            // myArray: [
            //     { 
            //         name: "Location One", 
            //         id: 0,
            //         address: "123 thing street",
            //         type: 'park',
            //         thumbsUp: 5
            //     },
            //    { 
            //         name: "Location Two", 
            //         id: 1,
            //         address: "456 thing street",
            //         type: 'landmark',
            //         thumbsUp: 10
            //     },
            //     { 
            //         name: "Location Three", 
            //         id: 2,
            //         address: "789 thing street",
            //         type: 'city feature',
            //         thumbsUp: 12
            //     },
            // ],
            oldIndex: '',
            newIndex: '',
            counter: 2,
            thumbsCounter: 0,
            active: false,
            landmarks: [],
            isLoading: true,
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
            ItineraryService.deleteLandmarkFromItinerary(this.$route.params.id, landId, this.$store.state.user)      
        },
       
        mouseOver(){
            this.active = !this.active;
            this.class="thumbs:hover";   
        },
        deleteItinerary() {
            ItineraryService.deleteItinerary(this.$route.params.id, this.$store.state.user).then(
                this.$router.push("/")
            )
        }

    },
    created(){
        //  ItineraryService.getUserItineraries(this.$store.state.currentUser).then( (response) => {
        //     this.itineraries.id = response.data;
        // });
    LandmarksService.getLandmarksForItinerary(this.$route.params.id).then( (response) => {
      this.landmarks = response.data;
      this.isLoading = false;
    })
  }
    
}
</script>

<style scoped>


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
    
    height: 100vh;
    padding-left: 50px;
    padding-right: 50px;
}
.thumbs {
    height: 20px;
    width: 20px;
    padding-block-start: 2px;
    border: .5px solid #ddd;
    background: lightgray;
    display: inline-block;
   margin: 0 10px 0 10px;
  
}
.thumbs:hover {
  background: green;
}
</style>