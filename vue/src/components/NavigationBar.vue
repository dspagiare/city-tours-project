
<template>
    <div id = "sideBar">
        <button class = "btn-block" v-on:click="showForm= !showForm">Add New Itinerary</button>
        <form  v-if ="showForm" v-on:submit="saveItinerary">
            <input class = "form-control" type ="text" placeholder="Enter New Itinerary Name" v-model='newItinerary.name'/>
            <button v-on:click="showForm = false" class="btn-submit">Save</button>
            <button v-on:click="showForm = !showForm" class="btn-cancel">Cancel</button>
        </form>
        
        <div>

        </div>
    </div>
</template>

<script>
export default {
    
    data() {
        
        return {
            newItinerary: {
                name: '',
            },
            showForm: false,
        }
    },
    methods: {
         saveItinerary(){
        //     SOMETHING SOMETHING CONNECT TO DB THAT DOESNT EXIST YET AND CREATE NEW ITINERARY 
        //     WITH USER INPUT NAME USING this.newItineraryName
        },
        viewItineraryDetails(){
            // ON CLICK WILL ROUTE TO ITINERARY DETAILS PAGE OF CLICKED ITINERARY
        }
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

//  functions need to:
//     -route to home page(should be hidden in Home.vue)
//     -add a new itinerary
//         user needs to name that itinerary
//     - Logout