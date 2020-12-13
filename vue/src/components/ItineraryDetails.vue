<template>
  <!--<div>
    <table class="table table-striped table-hover" >
      <thead>
        <tr>
          <th>Index</th>
          <th>Location Name</th>
          <th>Location Type</th>
          <th>Address</th>
          <th>Up Votes</th>
        </tr>
      </thead>
    <tbody >
      <tr v-for="landmark in landmarks" v-bind:key="landmark.id">
        <td>
            <label class="form-checkbox">
                <input type="checkbox" :value="landmark.id" v-model="selected">
					<i class="form-icon"></i>
            </label>
		</td>
        <td>{{landmark.id}}</td>
        <td>{{landmark.name}}</td>
        <td>{{landmark.type}}</td>
        <td>{{landmark.address}}</td>
        <td>{{landmark.thumbsUp}}</td>
      </tr>
    </tbody>
  </table>

  </div> 
  
</template>

<script>
import LandmarksService from '../services/LandmarksService'
export default {
  
  data: () => ({

    landmarks: [],
  }),

  created(){
    LandmarksService.getLandmarksForItinerary.then( (response) => {
      this.landmarks = response.data;
    })
  }
}
</script> -->

<style>
 
 h1 {
   padding-top: 20px;
   padding-bottom: 20px;
 }
 h2 {
   padding-bottom: 20px;
 }
 .table {
   background-color: lightgray;
   
 }
 .pane {
    display: inline-block;
    overflow-y: scroll;
    max-height:400px;
  }
 p{
   padding-bottom: 10px;
 }
.landmarks {
  
  max-width: 1100px;
  margin: 0 auto;
  padding-left: 50px;
  padding-right: 50px;
  color: antiquewhite; 
  overflow: visible;
  z-index: 100;
}
.main {
  width: 100%;
  position: absolute;
  right: 0;
  background-color: rgba(17, 171, 243, 0.87) !important;
  height: 100vh;
  padding-left: 50px;
  padding-right: 50px;
}
</style>