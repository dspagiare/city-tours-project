import axios from 'axios';

// let user = this.$store.state.currentUser;


export default {

    getUserItineraries(user) {
        return axios.get('/itineraries', user);
    },
    getItineraryDetails(id, user) {
        return axios.get(`/itineraries/${id}`, user);
    },
    saveItinerary(itinerary, user) {
        return axios.post('/itineraries', itinerary, user);
    },
    changeItineraryName(id, itinerary, user) {
        return axios.put(`/itineraries/${id}`, itinerary.name, user);
    },
    deleteItinerary(id, user) {
        return axios.delete(`/itineraries/${id}`, user);
    }
}