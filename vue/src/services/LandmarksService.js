import axios from 'axios';

export default {

  list() {
    return axios.get('/landmarks')
  },
  getLandmarksForItinerary(itinerary_id) {
    return axios.get(`/landmarks/${itinerary_id}`)
  }

}