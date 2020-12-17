<template>
  <div>
    <div id="btn">
      <button class="btn btn-primary" v-on:click.prevent="closePanel">
        Close Panel
      </button>
    </div>
    <div class="readIt">
      <h1 class="title">{{ landmark.name }}</h1>
      <h3 class="title">{{ landmark.venueType }}</h3>
      <p class="title">{{ landmark.address }}</p>
      <p class="description">{{ landmark.description }}</p>
      <div class="row">
        <div class="column">
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
                  {{ landmark.availableFromDay }} -
                  {{ landmark.availableToDay }}
                </td>
                <td>
                  {{ landmark.availableFromHour }} -
                  {{ landmark.availableToHour }}
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <div>
          <img v-bind:src="landmark.imgUrl" height="300" style="opacity: 1" />
        </div>
      </div>
    </div>
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
.column {
  display: flex;
  align-items: center;
}
.row {
  padding: 50px;
  display: flex;
  justify-content: space-around;
}
.description {
  text-align: center;
  font-family: "Gill Sans", Helvetica, Arial, sans-serif;
  font-size: 25px;
}
.title {
  text-align: center;
  font-family: "Gill Sans", Helvetica, Arial, sans-serif;
}
#btn {
  text-align: center;
}

.table {
  font-size: 20px;
  font-weight: bolder;
}
</style>
