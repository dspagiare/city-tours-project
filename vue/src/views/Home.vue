<template>

  <div class="home">
  <div class="main">
    <h1>Yinzer Tours</h1>
    <h2>Welcome to Yinzer Tours!  Designed to help you plan a day of sight seeing throughout the beautiful city of Pittsburgh, PA</h2>
    <p>Below is a list of landmarks based on popularity, Click "Create Itinerary" in the Navigation bar to start building your route! </p>
   <div class="text-uppercase text-bold">id selected: {{selected}}</div>
   <div class="pane">
   <table class="table table-striped table-hover">
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
      <tr v-for="landmark in $store.state.landmarks" v-bind:key="landmark.id">
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
  
    <navigation-bar />
  
  </div>
</template>

<script>

import NavigationBar from '../components/NavigationBar.vue';

export default {
  components: { 
    NavigationBar,
    },        
  name: "home",
 data: () => ({

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
		}
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
.home {
  
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
