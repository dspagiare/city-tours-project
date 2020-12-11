
<template>
    <div id = "sideBar">
        <button class = "btn-block" v-on:click="showForm= !showForm">Add New Itinerary</button>
        <form  v-if ="showForm" v-on:submit="saveItinerary">
            <input class = "form-control" type ="text" placeholder="Enter New Itinerary Name" v-model='newItinerary.name'/>
            <input class = "form-control" type ="text" placeholder="Enter Date Of Yinzer Tour" v-model='newItinerary.itineraryDate'/>
            <button v-on:click="showForm = false" class="btn-submit">Save</button>
            <button v-on:click="showForm = !showForm" class="btn-cancel">Cancel</button>
        </form>
        <div v-for="itinerary in itineraries" v-bind:key="itinerary.id" v-on:click="viewItineraryDetails(itinerary.id, this.$store.currentUser)">
               <router-link :to="`/${itinerary.id}`">{{ itinerary.name }}</router-link>
        </div>
    </div>
</template>

<script>
import ItineraryService from "../services/ItineraryService";

export default {
    name: "navigation-bar",
    data() {
        return {
            itineraries: [
                {
                    name: "asdjasd",
                    id: "1"
                },
                {
                    name: "poop poop",
                    id: "12311"
                }
            ],
            newItinerary: {
                name: '',
                id: '',
                itineraryDate: '',
            },
            showForm: false,
        }
    },
    methods: {

        // saveItinerary saves new Itinerary to DB and routes user to newItinerary's
        // details page
         saveItinerary(){
             ItineraryService.saveItinerary(this.newItinerary, this.$store.currentUser).then((response) => {
                 if (response.status === 201) {
                     this.$router.push(`itineraries/${this.newItinerary.id}`)
                 }
             })
        },

        // ON CLICK WILL ROUTE USER TO ITINERARY DETAILS PAGE OF CLICKED ITINERARY
        viewItineraryDetails(id){
            this.$router.push(`itineraries/${id}`)
        }
    },
    cretaed() {
        ItineraryService.getUserItineraries().then((response) => {
            this.itineraries = response.data;
        });
    }
}
</script>

<style>
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
    top: 25%;
    padding-top: 20px;
    padding-bottom: 20px;  
    overflow-x: hidden;  
    border-right: solid lightgrey 1px;
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