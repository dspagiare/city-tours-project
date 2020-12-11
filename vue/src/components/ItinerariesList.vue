<template>

<div class="itinerary">
   <div class="text-uppercase text-bold">id selected: {{selected}}</div>
   <div class="pane">
   <table class="table table-striped table-hover" >
     <thead>
        <tr>
			<th>
                <label class="form-checkbox">
                    <input type="checkbox" v-model="selectAll" @click="select">
                    <i class="form-icon"></i>
                    </label>
			</th>
          <th>Index</th>
          <th>Location Name</th>
          <th>Location Type</th>
          <th>Address</th>
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
      </tr>
    </tbody>
  </table>
   </div>
  </div>
</template>

<script>
import itineraryService from '../services/ItineraryService.js'

export default {
  components: { 
    },        
 name: "landmarks",
 data: () => ({
        landmarks: [],
		selected: [],
		selectAll: false
	}),
	methods: {
		select() {
			this.selected = [];
			if (!this.selectAll) {
				for (let i in this.$store.state.landmarks) {
					this.selected.push(this.$store.state.landmarks[i].id);
				}
			}
        },
    },  
   created() {
            itineraryService.list().then( (response) => {
                this.landmarks = response.data;
            });
        }
};
</script>

<style scoped>
 

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
    max-height:450px;
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
  font-family: Avenir, Helvetica, san-serif;
  
  overflow: visible;
  z-index: 100;
}
.main {
  width: 79%;
  position: absolute;
  right: 0;
  background-color: rgba(17, 171, 243, 0.87) !important;
  height: 100vh;
  padding-left: 50px;
  padding-right: 50px;
}
</style>