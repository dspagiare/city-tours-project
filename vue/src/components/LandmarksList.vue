<template>
  <div class="landmarks">
    <div class="isloading">
      <img src="../assets/giphy.gif" v-if="isLoading" />
    </div>
    <div class="isNotLoading" v-if="!isLoading">
      <div class="pane">
        <p class="searchBar">
          Search Landmarks by Name:
          <input
            type="text"
            id="searchLandmarks"
            placeholder="Search by name"
            v-model="searchText"
          />
        </p>
        <table class="table table-hover">
          <thead>
            <tr>
              <th>
                <label class="form-checkbox">
                  <input type="checkbox" v-model="selectAll" @click="select" />
                  <i class="form-icon"></i>
                </label>
              </th>
              <th>Location Name</th>
              <th>Location Type</th>
              <th>Address</th>
              <th>Up Votes</th>
              <th>Landmark Details</th>
            </tr>
          </thead>
          <tbody>
            <tr
              id="landmarksTable"
              v-for="landmark in filterBySearchText"
              v-bind:key="landmark.id"
            >
              <td>
                <label class="form-checkbox">
                  <input
                    type="checkbox"
                    :value="landmark.id"
                    v-model="selected"
                  />
                  <i class="form-icon"></i>
                </label>
              </td>
              <td>{{ landmark.name }}</td>
              <td>{{ landmark.venueType }}</td>
              <td>{{ landmark.address }}</td>
              <td>{{ landmark.numThumbsUp }}</td>
              <td>
                <button
                  class="btn btn-secondary"
                  v-on:click.prevent="showPanel(landmark)"
                >
                  Show Details
                </button>
                <slideout-panel class="slp"></slideout-panel>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="buttons">
        <button class="btn btn-outline-primary" v-on:click="addLandmarkToItin">
          Add Landmark
        </button>
        <label for="itineraries">Select an Itinerary:</label>
        <select name="itineraries" id="itineraries" v-model="selectedItinerary">
          <option></option>
          <option v-for="itinerary in itineraries" :key="itinerary.id">
            {{ itinerary.name }}
          </option>
        </select>
      </div>
    </div>
  </div>
</template>

<script>
import LandmarkDetails from "../components/LandmarkDetails.vue";
import ItineraryService from "../services/ItineraryService.js";
import LandmarksService from "../services/LandmarksService.js";

export default {
  components: {},
  name: "landmarks",
  data: () => ({
    landmarks: [],
    selected: [],
    selectAll: false,
    isLoading: true,
    itineraries: [],
    selectedItinerary: 0,
    landmarkDetailsForm: {
      openOn: "right",
    },
    searchText: "",
  }),
  computed: {
    filterBySearchText() {
      if (this.searchText != "") {
        return this.landmarks.filter((landmark) => {
          return landmark.name.startsWith(this.searchText);
        });
      } else {
        return this.landmarks;
      }
    },
  },

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
        openOn: "right",
        width: 900,
        props: { landmark },
      });
    },
    addLandmarkToItin() {
      if (this.selectedItinerary != 0) {
        ItineraryService.addLandmarkToItinerary(
          this.selectedItinerary,
          //this.$route.params.id,
          this.selected,
          this.$store.state.currentUser
        )
          .then((response) => {
            if (response.status === 200) {
              window.location.reload();
            }
          })
          .catch(() => {
            alert(
              "One or more selected Landmarks already exist on the itinerary. Please try again."
            );
            window.location.reload();
          });
      } else {
        return alert("Please select an itenary to add landmark.");
      }
    },
  },
  created() {
    ItineraryService.getUserItineraries(this.$store.state.currentUser).then(
      (response) => {
        this.itineraries = response.data;
      }
    );
    LandmarksService.list().then((response) => {
      this.landmarks = response.data;
      this.isLoading = false;
    });
  },
};
</script>

<style scoped>
#searchLandMarks {
  color: blue;
}
.searchBar {
  font-family: "Gill Sans", "Helevetica", "Arial", "sans=serif";
  font-size: 20px;
  font-weight: 500;
}
tbody {
  font-size: 15px;
}
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
  max-height: 500px;
  width: 100%;
}
.landmarks {
  padding-left: 50px;
  color: black;
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
.LandmarkDetails {
  padding: 20px;
}
.slp {
  opacity: 0.2;
}
</style>
