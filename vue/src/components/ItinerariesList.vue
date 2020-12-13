<template>
  <div class="itinerary-list">
    <h1>Itinerary 1</h1>
    <div id="app" class="container"></div>

    <table class="table">
      <thead>
        <tr>
          <th>Location</th>
          <th>Type</th>
          <th>Address</th>
          <th>Index</th>
          <th>How Was It?</th>
          <th>Delete Location</th>
        </tr>
      </thead>
      <draggable :list="myArray.id" @end="onEnd" tag="tbody">
        <tr
          :id="element.id"
          v-for="(element, index) in myArray"
          :key="element.id"
        >
          <td>{{ element.name }}</td>
          <td>{{ element.type }}</td>
          <td>{{ element.address }}</td>
          <td>{{ element.id }}</td>
          <td>
            <img
              class="thumbs"
              src="../assets/thumbs-up.png"
              v-on:click="element.thumbsUp + 1"
              @mouseover="mouseOver"
            />
            <img
              class="thumbs"
              src="../assets/thumbs-down-icon.png"
              v-on:click="element.thumbsUp + -1"
              @mouseover="mouseOver"
            />
          </td>
          <td><button @click="deleteTableRow(index)">Delete</button></td>
        </tr>
      </draggable>
    </table>

    <p><strong>Previous Index: </strong>{{ oldIndex }}</p>
    <p><strong>New Index: </strong>{{ newIndex }}</p>

    <button class="btn btn-cancel deleteItinerary" v-on:click="deleteItinerary">
      Delete Itinerary
    </button>
  </div>
</template>

<script>
import draggable from "vuedraggable";

export default {
  components: {
    draggable,
  },
  data() {
    return {
      myArray: [
        {
          name: "Location One",
          id: 0,
          address: "123 thing street",
          type: "park",
          thumbsUp: 5,
        },
        {
          name: "Location Two",
          id: 1,
          address: "456 thing street",
          type: "landmark",
          thumbsUp: 10,
        },
        {
          name: "Location Three",
          id: 2,
          address: "789 thing street",
          type: "city feature",
          thumbsUp: 12,
        },
      ],
      oldIndex: "",
      newIndex: "",
      counter: 2,
      thumbsCounter: 0,
      active: false,
    };
  },
  methods: {
    onEnd(evt) {
      console.log(evt);
      this.newIndex = evt.newIndex;
      this.oldIndex = evt.oldIndex;
    },
    deleteTableRow(idx) {
      this.counter--;
      this.myArray.splice(idx, 1);
    },

    mouseOver() {
      this.active = !this.active;
      this.class = "thumbs:hover";
    },
  },
};
</script>

<style scoped>
.itinerary-list {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  padding: 4em;
  cursor: move;
  width: 100%;
  position: absolute;
  right: 0;
  background-color: rgba(17, 171, 243, 0.87) !important;
  height: 100vh;
  padding-left: 50px;
  padding-right: 50px;
}
.thumbs {
  height: 20px;
  width: 20px;
}
.thumbs:hover {
  background: green;
}
</style>