<template>
  <div class="home">
    <div class="main">
      <h2>
        Welcome to Yinzer Tours! Designed to help you plan a day of sight seeing
        throughout the beautiful city of Pittsburgh, PA
      </h2>
      <p>
        Below is a list of landmarks based on popularity, Click "Create
        Itinerary" in the Navigation bar to start building your route!
      </p>
      <landmarks-list />
      <router-view />
    </div>

    <navigation-bar />
  </div>
</template>

<script>
import LandmarksList from "../components/LandmarksList.vue";
import NavigationBar from "../components/NavigationBar.vue";

export default {
  components: {
    NavigationBar,
    LandmarksList,
  },
  name: "home",
};
</script>

<style scoped>
h1 {
  padding-top: 20px;
  padding-bottom: 20px;
}
h2 {
  padding-bottom: 20px;
  padding-top: 20px;
  font-family: "Gill Sans", Helvetica, Arial, sans-serif;
  text-align: center;
}
.pane {
  display: inline-block;
}
p {
  padding-bottom: 10px;
  text-align: center;
  font-family: "Gill Sans", Helvetica, Arial, sans-serif;
  font-size: 20px;
}
.home {
  font-family: Avenir, Helvetica, san-serif;
}
.main {
  margin-top: auto;
  height: 100%;
  overflow: scroll;
  color: black;
  width: 85%;
  position: absolute;
  right: 0;
  padding-left: 50px;
  padding-right: 50px;
  padding-bottom: 8rem;
}
</style>
