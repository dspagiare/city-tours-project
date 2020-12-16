<template>
  <div>
    <button class="btn btn-primary" v-on:click.prevent="closePanel">
      Close Panel
    </button>
    <!-- const pic = {{ landmark.landmark_img_url}}; -->
    <!-- <div :style="cssProps"> -->
    <!-- </div> -->

    <h1>{{ landmark.name }}</h1>
    <h3>{{ landmark.venueType }}</h3>
    <p>{{ landmark.address }}</p>
    <p>{{ landmark.description }}</p>

    <table class="table">
      <thead>
        <tr>
          <th>Days Open</th>
          <th>Hours Open</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>
            {{ landmark.availableFromDay }} - {{ landmark.availableToDay }}
          </td>
          <td>
            {{ landmark.availableFromHour }} - {{ landmark.availableToHour }}
          </td>
        </tr>
      </tbody>
    </table>
    <div v-bind:style="{ backgroundImage: ` url (${landmark.imgUrl})` }"></div>
    <!-- <img v-bind:src="landmark.imgUrl" /> -->
    <!-- <div v-bind:style="{ backgroundImage: ` url (${landmark.imgUrl})` }"></div> -->
  </div>
</template>
<script>
export default {
  name: "LandmarkDetails",
  props: ["landmark"],
  data() {},

  methods: {
    closePanel() {
      this.$emit("closePanel", {});
    },
  },
};
</script>
<style scoped>
.LandmarkDetails {
  padding: 20px;
}
</style>
