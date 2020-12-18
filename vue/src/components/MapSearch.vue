<template>
  <div class="ui grid">
    <div class="six wide column">
      <form class="ui segment large form">
        <div class="ui segment">
          <!-- <div class="field">
            <div class="ui right icon input large">
              <input
                type="text"
                placeholder="Enter your address or click locator button"
                v-model="coordinates"
              />
              <i
                class="dot circle link icon"
                v-on:click="locatorButtonPressed"
              ></i>
            </div>
          </div> -->
          <div class="field">
            <div class="two fields">
              <div class="field">
                <b-button @click.prevent="drawMarkers">
                  Draw Markers
                </b-button>
              </div>
              <div class="field">
                <b-button @click="clearMarkers">Clear Markers</b-button>
              </div>
              <div class="field">
                <b-button @click="drawDirections">
                  Generate Route
                </b-button>
              </div>
            </div>
          </div>
        </div>
      </form>
      <div class="ui segment" style="max-height:500px;overflow:scroll">
        <div class="ui divided items">
          <div class="item" v-for="place in places" v-bind:key="place.id">
            <div class="content">
              <div class="header">{{ place.name }}</div>
              <div class="meta">{{ place.vicinity }}</div>
              <div class="meta">{{ place.address }}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="ten wide column segemnt ui" ref="map">
      <div>
        <div>
          <h2>Enter your location below.</h2>
          <label>
            <gmap-autocomplete @place_changed="setPlace"> </gmap-autocomplete>
            <button @click="addMarker">Add</button>
          </label>
          <br />
        </div>
        <br />
        <gmap-map
          ref="map"
          :center="center"
          :zoom="12"
          style="width:100%;  height: 400px;"
        >
          <gmap-marker
            :key="index"
            v-for="(m, index) in markers"
            :position="getPosition(m.position)"
            :clickable="true"
            :draggable="true"
            @click="toggleInfo(m.position, index)"
          ></gmap-marker>
          <gmap-info-window
            :options="infoOptions"
            :position="infoPosition"
            :opened="infoOpened"
            @closeclick="infoOpened = false"
          >
            {{ infoContent }}
          </gmap-info-window>
          <gmap-polygon
            :paths.sync="paths"
            v-bind:options="{ strokeColor: '008000' }"
          >
          </gmap-polygon>
        </gmap-map>
      </div>
    </div>
  </div>
</template>
<script
  async
  defer
  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC044Lz-PYzRTw3JHlYN7IIX4UBRnOHyBw&libraries=places,geometry"
  type="text/javascript"
></script>
<script>
import LandmarksService from "@/services/LandmarksService";
import axios from "axios";
export default {
  data() {
    return {
      paths: [],
      map: null,
      center: { lat: 40.4406, lng: -79.9959 },
      markers: [],
      currentPlace: null,
      lat: 0,
      lng: 0,
      type: "",
      radius: "",
      places: [],
      address: "",
      infoPosition: null,
      infoContent: null,
      infoOpened: false,
      infoCurrentKey: null,
      infoOptions: {
        pixelOffset: {
          width: 0,
          height: -35,
        },
      },
    };
  },
  created() {
    LandmarksService.getLandmarksForItinerary(this.$route.params.id).then(
      (response) => {
        this.places = response.data;
        this.isLoading = false;
        // this.drawMarkers();
      }
    );
  },
  mounted() {
    // this.geolocate();
    // // this.getRoute();
    // // this.displayGoogleMap();
  },

  computed: {
    coordinates() {
      return this.address;
    },
  },
  methods: {
    getPosition: function(marker) {
      return {
        lat: parseFloat(marker.lat),
        lng: parseFloat(marker.lng),
      };
    },
    toggleInfo: function(marker, key) {
      this.infoPosition = this.getPosition(marker);
      this.infoContent = marker.name;
      if (this.infoCurrentKey == key) {
        this.infoOpened = !this.infoOpened;
      } else {
        this.infoOpened = true;
        this.infoCurrentKey = key;
      }
    },
    drawMarkers() {
      console.log("I called drawMarkers");
      this.places.forEach((place) => {
        let name = place.name;
        let lat = place.landLat;
        let lng = place.landLon;
        let marker = { name: name, lat: parseFloat(lat), lng: parseFloat(lng) };
        this.markers.push({ position: marker });
        console.log(marker);
        console.log(lat);
        console.log("where am i?");
      });
    },

    drawDirections() {
      this.markers.forEach((m) => {
        let lat = m.position.lat;
        let lng = m.position.lng;
        let path = { lat: parseFloat(lat), lng: parseFloat(lng) };
        this.paths.push(path);
      });
    },
    clearMarkers() {
      this.paths = [];
      this.markers = [];
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
    // created() {
    //   function displayGoogleMap() {
    //     let map = new window.google.maps.Map(document.getElementById("map"), {
    //       zoom: 15,
    //       center: new window.google.maps.LatLng(this.lat, this.lng),
    //     });
    //     let infoWindow = new window.google.maps.InfoWindow();
    //     this.places.forEach((place) => {
    //       const lat = place.geometry.location.lat;
    //       const lng = place.gemoetry.location.lng;
    //       let marker = new window.google.maps.Marker({
    //         position: new window.google.maps.LatLng(lat, lng),
    //         map: map,
    //       });
    //       google.map.event.addListener(marker, "click", () => {
    //         infoWindow.setContent(
    //           `<div class="ui header">${place.name}</div><p>${place.vicinity}</p>`
    //         );
    //         infoWindow.open(map, marker);
    //       });
    //     });
    //   }
    // },

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
