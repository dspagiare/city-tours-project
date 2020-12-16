<template>
  <div>
    <button class="btn-block" v-on:click="showForm = !showForm">
      Edit Itinerary
    </button>
    <form v-if="showForm" v-on:submit.prevent="update()">
      <div class="field">
        <label for="itinerary_name">Title</label>
        <input
          type="text"
          v-model="itinerary.itinerary_name"
          placeholder="Enter New Itinerary Name"
        />
        <label for="itinerary_date">Date</label>
        <input
          type="text"
          v-model="itinerary.itinerary_date"
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
</template>

<script>
import ItineraryService from "../services/ItineraryService";
export default {
  name: "itinerary-details",
  props: ["itinerary_id"],
  data() {
    return {
      itinerary: {
        itinerary_name: "",
        itinerary_date: "",
      },

      showForm: false,
    };
  },
  created() {
    ItineraryService.getItineraryDetails().then((response) => {
      this.itinerary = response.data;
    });
  },
  methods: {
    update() {
      const itinerary = {
        id: this.itinerary_id,
        title: this.itinerary_name,
        date: this.itinerary_date,
      };
      ItineraryService.editItinerary(itinerary).then((response) => {
        if (response.status === 200) {
          //WHERE DO WE WANT THIS TO GO? BACK TO HOME SCREE
          this.$router.push("/");
        }
      });
    },
  },
};
</script>

<style>
</style>