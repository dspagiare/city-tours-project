<template>
  <div>
    <button class="btn btn-primary" v-on:click.prevent="closePanel">
      Close Panel
    </button>
    <img v-bind:src="landmark.imgUrl" />
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
  </div>
</template>
<script>
export default {
  name: "LandmarkDetails",
  props: ["landmark"],
  data() {
    return {};
  },
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
