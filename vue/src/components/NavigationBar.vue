<template>
  <div id="sideBar">
    <button class="btn btn-primary" v-on:click="showForm = !showForm">
      ADD NEW ITINERARY
    </button>
    <form v-if="showForm" v-on:submit.prevent="saveItinerary">
      <input
        class="form-control"
        type="text"
        placeholder="Enter New Itinerary Name"
        v-model="newItinerary.name"
      />
      <input
        type="date"
        name="Tour Date"
        v-model="newItinerary.itinerary_date"
      />
      <input type="submit" v-on:submit="saveItinerary" class="btn-submit" />
      <button v-on:click="showForm = !showForm" class="btn-cancel">
        Cancel
      </button>
    </form>
    <div
      v-for="itinerary in itineraries"
      v-bind:key="itinerary.itinerary_id"
      v-on:click="forceRefresh()"
    >
      <router-link
        :to="`/itinerary/${itinerary.itinerary_id}`"
        tag="button"
        class="btn btn-primary btn-lg"
        >{{ itinerary.name }}</router-link
      >
    </div>
  </div>
</template>
<script>
import ItineraryService from "../services/ItineraryService.js";
export default {
  name: "navigation-bar",
  created() {
    ItineraryService.getUserItineraries(this.$store.state.currentUser).then(
      (response) => {
        this.itineraries = response.data;
      }
    );
  },

  data: () => ({
    itineraries: [],
    newItinerary: {
      name: "",
      id: "",
      itinerary_date: "",
    },
    showForm: false,
    isLoading: true,
  }),
  methods: {
    forceRefresh() {
      window.location.reload();
    },
    saveItinerary() {
      let arr = [];

      this.itineraries.forEach((itinerary) => {
        arr.push(itinerary.name);
      });

      if (
        this.newItinerary.name === "" ||
        this.newItinerary.itinerary_date === ""
      ) {
        return alert("Put in the stuff before you submit");
      }
      if (arr.includes(this.newItinerary.name)) {
        return alert("New itinerary name must not be a duplicate");
      } else {
        ItineraryService.saveItinerary(
          this.newItinerary,
          this.$store.state.currentUser
        ).then(() => {
          this.showForm = false;

          ItineraryService.getUserItineraries(
            this.$store.state.currentUser
          ).then((response) => {
            this.itineraries = response.data;
          });
        });
      }
    },
  },
};
</script>
<style>
.btn.btn-primary {
  background: #0b6623;
  flex-direction: column;
  align-items: center;
  vertical-align: middle;
  height: 80px;
  width: 250px;
  line-height: 65px;
  border-color: white;
  font-weight: bold;
  font-size: 20px;
  margin-top: 30px;
}
.btn.btn-primary.btn-lg {
  margin-top: 20px;
  height: 80px;
  width: 200px;
  background: #e14a2a;
  color: white;
  border-color: white;
  align-content: center;
  vertical-align: middle;
}
.btn-cancel {
  color: #fff;
  background-color: #dc3545;
  border-color: #dc3545;
}
.btn-submit {
  color: #fff;
  background-color: #0062cc;
  border-color: #005cbf;
}
div#sideBar {
  height: 100%;
  width: 15%;
  position: fixed;
  z-index: 1;
  left: 0;
  padding-top: 10px;
  padding-bottom: 10px;
  overflow-x: hidden;
  border-right: solid lightgrey 1px;
  background-image: url("../assets/incline.jpg");
  border-block-color: black;
  border-bottom-color: black;
  border-right-color: black;
  display: flex;
  flex-direction: column;
  align-items: center;
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
div#sideBar {
  height: 100%;
  width: 15%;

  position: fixed;
  z-index: 1;
  left: 0;
  padding-top: 10px;
  padding-bottom: 10px;
  overflow-x: hidden;
  border-right: solid lightgrey 1px;
  background-image: url("../assets/incline.jpg");

  border-block-color: black;
  border-bottom-color: black;
  border-right-color: black;
  display: flex;
  flex-direction: column;
  align-items: center;
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

// needs to display a list of all itineraries for user // functions need to //
-add a new itinerary // user needs to name that itinerary
