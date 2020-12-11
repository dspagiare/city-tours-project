import axios from 'axios';

// let user = this.$store.state.currentUser;

const http = axios.create({
    baseURL: "http://localhost:8080"
});
export default {

    getUserItineraries(user) {
        return http.get('/itineraries', user);
    },
    getItineraryDetails(id, user) {
        return http.get(`/itineraries/${id}`, user);
    },
    saveItinerary(itinerary, user) {
        return http.post('/itineraries', itinerary, user);
    },
    changeItineraryName(id, itinerary, user) {
        return http.put(`/itineraries/${id}`, itinerary.name, user);
    },
    deleteItinerary(id, user) {
        return http.delete(`/itineraries/${id}`, user);
    }
}