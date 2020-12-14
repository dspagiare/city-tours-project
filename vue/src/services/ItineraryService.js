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
    changeItineraryName( itinerary, user) {
        return axios.put(`/itineraries`, itinerary, user);
    },
    deleteItinerary(id, user) {
        return axios.delete(`/itineraries/${id}`, user);
    },
    addLandmarkToItinerary(id, landId, user ) {
        return axios.post(`/itineraries/${id}/landmarks/${landId}`, user);
    },
    deleteLandmarkFromItinerary(id, landId, user){
        return axios.delete(`/itineraries/${id}/landmarks/${landId}`, user);
    }
}