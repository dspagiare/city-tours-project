
<template>
    <div id = "sideBar">
        <button class = "btn-block" v-on:click="showForm= !showForm">Add New Itinerary</button>
        <form  v-if ="showForm" v-on:submit.prevent="saveItinerary">
            <input class = "form-control" type ="text" placeholder="Enter New Itinerary Name" v-model='newItinerary.name'/>
            <input type = "date" name ="Tour Date" v-model="newItinerary.itinerary_date"/>
            <input type="submit" v-on:submit="saveItinerary" class="btn-submit">
            <button v-on:click="showForm = !showForm" class="btn-cancel">Cancel</button>
        </form>
        <div v-for="itinerary in itineraries" v-bind:key="itinerary.itinerary_id">
               <router-link :to="`/itinerary/${itinerary.itinerary_id}`" tag="button" class="btn-itinName">{{ itinerary.name }}</router-link>
        </div>
    </div>
</template>

<script>
import ItineraryService from "../services/ItineraryService.js";

export default {
    name: "navigation-bar",
    created() {
        ItineraryService.getUserItineraries(this.$store.state.currentUser).then( (response) => {
            this.itineraries = response.data;
        });
    },
    // beforeUpdate(){
    //     ItineraryService.getUserItineraries(this.$store.state.currentUser).then( (response) => {
    //         this.itineraries = response.data;
    //     });
    // },
    data: () => ({ 
            itineraries: [],
            newItinerary: {
                name: '',
                id: '',
                itinerary_date: '',
            },
            showForm: false,
        }),
    methods: {
        // // saveItinerary saves new Itinerary to DB and routes user to newItinerary's
        // details page
         saveItinerary(){
             if (this.newItinerary.name === '' || this.newItinerary.itinerary_date === '') {
                return alert("Put in the stuff before you submit");
                
             }
             ItineraryService.saveItinerary(this.newItinerary, this.$store.state.currentUser).then((response) => {
                 this.showForm = false;
                 if (response.status === 201) {
                     this.$router.push(`/itineraries/${this.newItinerary.id}`)
                 }
             })
        },
    },
    
}
</script>

<style>
.btn-itinName {
    margin-top: 10px;
    width: 150px;
    background-color:yellow;
    border-radius: 4px;
}
.btn-cancel {
    color: #fff;
    background-color: #dc3545;
    border-color: #dc3545
}
.btn-submit {
    color: #fff;
    background-color: #0062cc;
    border-color: #005cbf;
}
div#sideBar {  
    height: 100%;
    width: 20%;  
    position: fixed;  
    z-index: 1;
    left: 0;
    
    padding-top: 20px;
    padding-bottom: 20px;  
    overflow-x: hidden;  
    border-right: solid lightgrey 1px;
    background-color: salmon;
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



//  needs to display a list of all itineraries for user

//  functions need to
//     -add a new itinerary
//         user needs to name that itinerary