<template>
  <div class="ui grid">
    <!-- <div class="six wide column">
      <form class="ui segment large form">
        <div class="ui segment">
          <div class="field">
            <div class="ui right icon input large">
              <input
                type="text"
                placeholder="Enter your address"
                v-model="coordinates"
              />
              <i
                class="dot circle link icon"
                v-on:click="locatorButtonPressed"
              ></i>
            </div>
          </div>
          <div class="field">
            <div class="two fields">
              <div class="field">
                <select v-model="type">
                  <option value="museum">Landmark</option>
                </select>
              </div>
              <div class="field">
                <select v-model="radius">
                  <option value="5">5 Miles</option>
                  <option value="10">10 Miles</option>
                  <option value="15">15 Miles</option>
                  <option value="20">20 Miles</option>
                </select>
              </div>
            </div>
          </div>
          <button
            class="ui button"
            :bind="places"
            v-on:click.prevent="getRoute"
          >
            Generate Route
          </button>
        </div>
      </form>
      <div class="ui segment" style="max-height:500px;overflow:scroll">
        <div class="ui divided items">
          <div class="item" v-for="place in places" v-bind:key="place.id">
            <div class="content">
              <div class="header">{{ place.name }}</div>
              <div class="meta">{{ place.vicinity }}</div>
            </div>
          </div>
        </div>
      </div>
    </div> -->
    <div class="twelve wide column segemnt ui" id="map">
      <div>
        <div>
          <h2>Search and add a pin</h2>
          <label>
            <gmap-autocomplete @place_changed="setPlace"> </gmap-autocomplete>
            <button @click="addMarker">Add</button>
          </label>
          <br />
          <button class="ui button" :bind="places" @:click.prevent="getRoute">
            Generate Route
          </button>
        </div>
        <br />
        <gmap-map
          :center="center"
          :zoom="12"
          style="width:100%;  height: 400px;"
        >
          <gmap-marker
            :key="index"
            v-for="(m, index) in markers"
            :position="m.position"
            @click="center = m.position"
          ></gmap-marker>
        </gmap-map>
      </div>
    </div>
  </div>
</template>
<script
  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC044Lz-PYzRTw3JHlYN7IIX4UBRnOHyBw&libraries=places,geometry"
  type="text/javascript"
></script>
<script>
import LandmarksService from "@/services/LandmarksService";
import axios from "axios";
export default {
  data() {
    return {
      map: null,
      center: { lat: 40.4406, lng: 70.9959 },
      markers: [],
      currentPlace: null,
      lat: 0,
      lng: 0,
      type: "",
      radius: "",
      places: [],
      address: "",
      dist: 0,
      filteredPlace: [],
    };
  },
  created() {
    LandmarksService.getLandmarksForItinerary(this.$route.params.id).then(
      (response) => {
        this.places = response.data;

        this.isLoading = false;
      }
    );
  },
  mounted() {
    this.geolocate();
    // this.getRoute();
    // this.displayGoogleMap();
  },

  computed: {
    coordinates() {
      return `${this.lat}, ${this.lng}`;
    },
  },
  methods: {
    getRoute: function() {
      this.directionsService = new google.maps.DirectionsService();
      this.directionsDisplay = new google.maps.DirectionsRenderer();
      // this.directionsDisplay.setMap(this.$refs.map.$mapObject);
      let vm = this;
      vm.directionsService.route(
        {
          origin: new window.google.maps.LatLng(37.66992908, -122.4469157), // Can be coord or also a search query
          destination: new window.google.map.LatLng(
            37.7683909618184,
            122.51089453697205
          ),
          travelMode: "DRIVING",
        },
        function(response, status) {
          if (status === "OK") {
            vm.directionsDisplay.setDirections(response); // draws the polygon to the map
          } else {
            console.log("Directions request failed due to " + status);
          }
        }
      );
    },

    setPlace(place) {
      this.currentPlace = place;
    },
    addMarker() {
      if (this.currentPlace) {
        const marker = {
          lat: this.currentPlace.geometry.location.lat(),
          lng: this.currentPlace.geometry.location.lng(),
        };
        this.markers.push({ position: marker });
        this.places.push(this.currentPlace);
        this.center = marker;
        this.currentPlace = null;
      }
    },
    geolocate: function() {
      navigator.geolocation.getCurrentPosition((position) => {
        this.center = {
          lat: position.coords.latitude,
          lng: position.coords.longitude,
        };
      });
    },
    created() {
      function displayGoogleMap() {
        let map = new window.google.maps.Map(document.getElementById("map"), {
          zoom: 15,
          center: new window.google.maps.LatLng(this.lat, this.lng),
        });
        let infoWindow = new window.google.maps.InfoWindow();
        this.places.forEach((place) => {
          const lat = place.geometry.location.lat;
          const lng = place.gemoetry.location.lng;
          let marker = new window.google.maps.Marker({
            position: new window.google.maps.LatLng(lat, lng),
            map: map,
          });
          google.map.event.addListener(marker, "click", () => {
            infoWindow.setContent(
              `<div class="ui header">${place.name}</div><p>${place.vicinity}</p>`
            );
            infoWindow.open(map, marker);
          });
        });
      }
    },

    findNearByLocations() {
      const URL = `https://cors-anywhere.herokuapp.com/https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=${
        this.lat
      },${this.lng}&type=${this.type}&radius=${this.radius *
        1000}&key=AIzaSyC044Lz-PYzRTw3JHlYN7IIX4UBRnOHyBw`;
      console.log(URL);
      axios
        .get(URL)
        .then((response) => {
          this.places = response.data.results;
        })

        .catch((error) => {
          console.log(error.response.data.error);
          console.log(error.message);
        });
    },
    async getStreetAddressFrom(lat, long) {
      try {
        let { data } = await axios.get(
          "https://maps.googleapis.com/maps/api/geocode/json?latlng=" +
            lat +
            "," +
            long +
            "&key=AIzaSyC044Lz-PYzRTw3JHlYN7IIX4UBRnOHyBw"
        );
        if (data.error_message) {
          console.log(data.error_message);
        } else {
          this.address = data.results[0].formatted_address;
        }
      } catch (error) {
        console.log(error.message);
      }
    },
    locatorButtonPressed() {
      navigator.geolocation.getCurrentPosition((position) => {
        this.lat = position.coords.latitude;
        this.lng = position.coords.longitude;

        this.getStreetAddressFrom(
          position.coords.latitude,
          position.coords.longitude
        );
      });
      (error) => {
        console.log(error.message);
      };
    },
  },
};
</script>

<style scoped>
.ui.grid {
  margin-top: 20px;
  height: 100hv;
}
</style>
