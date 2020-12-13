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
                  <option value="restaurant">Landmark</option>
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
          <button class="ui button" v-on:click="findNearByLocations">
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
    <div class="ten wide column segemnt ui" ref="map"></div>
  </div>
</template>
<script
  defer
  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC044Lz-PYzRTw3JHlYN7IIX4UBRnOHyBw&libraries=places"
></script>
<script>
import axios from "axios";
export default {
  data() {
    return {
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
    // findNearByLocations() {
    //   let map;
    //   let service;
    //   let infowindow;
    //   function initialize() {
    //     let loc = new google.maps.LatLng(40.440624, -79.995888);
    //     map = new google.maps.Map(this.$ref["map"], {
    //       center: loc,
    //       zoom: 15,
    //       mapTypeId: google.maps.MapTypeId.ROADMAP,
    //     });
    //     let request = {
    //       location: loc,
    //       radius: 5000,
    //       type: ["resturant"],
    //     };
    //     service = new google.maps.places.PlacesService(map);
    //     service.nearbySearch(request, callback);
    //   }
    //   function callback(results, status) {
    //     if (status == google.maps.places.PlacesService.OK) {
    //       for (let i = 0; i < results.length; i++) {
    //         results[i].push(this.places);
    //       }
    //     }
    //   }
    // },
    findNearByLocations() {
      const URL = `https://cors-anywhere.herokuapp.com/https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=${
        this.lat
      },${this.lng}&type=${this.type}&radius=${this.radius *
        1000}&key=AIzaSyC044Lz-PYzRTw3JHlYN7IIX4UBRnOHyBw
      }`;
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
    // async getStreetAddressFrom(lat, long) {
    //   try {
    //     let { data } = await axios.get(
    //       "https://maps.googleapis.com/maps/api/geocode/json?latlng=" +
    //         lat +
    //         "," +
    //         long +
    //         "&key=AIzaSyC044Lz-PYzRTw3JHlYN7IIX4UBRnOHyBw"
    //     );
    //     if (data.error_message) {
    //       console.log(data.error_message);
    //     } else {
    //       this.address = data.results[0].formatted_address;
    //     }
    //   } catch (error) {
    //     console.log(error.message);
    //   }
    // },
    locatorButtonPressed() {
      navigator.geolocation.getCurrentPosition((position) => {
        this.lat = position.coords.latitude;
        this.lng = position.coords.longitude;
        this.displayGoogleMap();
        // this.getStreetAddressFrom(
        //   position.coords.latitude,
        //   position.coords.longitude
        // );
      });
      (error) => {
        console.log(error.message);
      };
    },
    displayGoogleMap() {
      let map = new google.maps.Map(this.$ref["map"], {
        zoom: 15,
        center: new google.maps.address(this.address),
        mapTypeId: google.maps.MapTypeId.ROADMAP,
      });
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
  },
};
</script>

<style scoped>


</style>
