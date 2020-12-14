<template>
  <div class="ui grid">
    <div class="six wide column">
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
          <button class="ui button" v-on:click.prevent="findNearByLocations">
            Find nearby locations
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
    </div>
    <div
      class="ten wide column segemnt ui"
      style="width:100%;height:400px;"
      id="map"
      ref="map"
    ></div>
    <map-loader />
  </div>
</template>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC044Lz-PYzRTw3JHlYN7IIX4UBRnOHyBw&libraries=places"></script>
<script></script>

<script>
import MapLoader from "@/components/MapLoader";
import axios from "axios";
export default {
  components: {
    MapLoader,
  },
  data() {
    return {
      map: null,
      lat: 0,
      lng: 0,
      type: "",
      radius: "",
      places: [],
      address: "",
    };
  },

  computed: {
    coordinates() {
      return `${this.lat}, ${this.lng}`;
    },
  },
  methods: {
    displayGoogleMap() {
      let mapProp = {
        center: new google.maps.LatLng(51.508742, -0.12059),
        zoom: 15,
      };
      let map = new google.maps.Map(document.getElementById("map"), mapProp);
      let infoWindow = new google.maps.InfoWindow();
      this.places.forEach((place) => {
        const lat = place.geometry.location.lat;
        const lng = place.gemoetry.location.lng;
        let marker = new google.maps.Marker({
          position: new google.maps.LatLng(lat, lng),
          map: map,
        });
        google.maps.even.addListener(marker, "click", () => {
          infoWindow.setContent(
            `<div class="ui header">${place.name}</div><p>${place.vicinity}</p>`
          );
          infoWindow.open(map, marker);
        });
      });
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
          this.displayGoogleMap();
        })

        .catch((error) => {
          console.log(err.response.data.error);
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
    displayGoogleMap() {
      // let infoWindow = new google.maps.InfoWindow();
      // this.places.forEach((place) => {
      //   const lat = place.geometry.location.lat;
      //   const lng = place.gemoetry.location.lng;
      //   let marker = new google.maps.Marker({
      //     position: new google.maps.LatLng(lat, lng),
      //     map: map,
      //   });
      //   google.maps.even.addListener(marker, "click", () => {
      //     infoWindow.setContent(
      //       `<div class="ui header">${place.name}</div><p>${place.vicinity}</p>`
      //     );
      //     infoWindow.open(map, marker);
      //   });
      // });
    },
  },
};
</script>

<style scoped>
.ui.grid {
  margin-top: 20px;
}
</style>
