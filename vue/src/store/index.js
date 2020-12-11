import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    landmarks: [
        {
          id: 1,
          name:"Cathedral of Learning",
          address: "4200 Fifth Ave, Pittsburgh, PA 15260",
          type: "National Historic Landmark",
          description: "The Cathedral of Learning is a building that serves as the centerpiece of the University of Pittsburgh's main campus in the Oakland neighborhood "
        },
        {
          id: 2,
          name:"Canton Avenue",
          address: "Canton Ave, Pittsburgh, PA 15216",
          type: "City Feature",
          description: "Canton Avenue, in Pittsburgh, Pennsylvania's Beechview neighborhood, is the steepest officially recorded public street in the United States. Canton Avenue is 630 ft long and is claimed to include a 37 percent grade 21 ft feet long."
        }, 
        {
          id: 3,
          name:"Frick Park",
          address: "1981 Beechwood Blvd, Pittsburgh, PA 15217",
          type: "Park",
          description: "Frick Park is the largest municipal park in Pittsburgh, Pennsylvania, covering 644 acres. It is one of Pittsburgh's four historic large parks. The park began when Henry Clay Frick, upon his death in 1919, bequeathed 151 acres south of Clayton, his Point Breeze mansion."
        },
        {
          id: 4,
          name:"Carnegie Museum of Natural History",
          address: "4400 Forbes Ave, Pittsburgh, PA 15213",
          type: "Museum",
          description: "Frick Park is the largest municipal park in Pittsburgh, Pennsylvania, covering 644 acres. It is one of Pittsburgh's four historic large parks. The park began when Henry Clay Frick, upon his death in 1919, bequeathed 151 acres south of Clayton, his Point Breeze mansion."
        },
        {
          id: 5,
          name:"Carrie Furnace",
          address: "801 Carrie Furnace Blvd, Rankin, PA 15104",
          type: "National Historic Landmark",
          description: "Carrie Furnace is a former blast furnace located along the Monongahela River in the Pittsburgh area industrial town of Swissvale, Pennsylvania, and it had formed a part of the Homestead Steel Works. The Carrie Furnaces were built in 1884 and they operated until 1982"
        }, 
        {
          id: 6,
          name: "Phipps Conservatory and Botanical Gardens",
          address: "1 Schenley Drive, Pittsburgh, PA 15213-3830",
          type: "National Historic Landmark",
          description: "A green oasis in the middle of Pittsburgh’s vibrant Oakland neighborhood, Phipps has provided a world-class garden experience to its visitors since 1893. Visit to discover breathtaking seasonal flower shows and special exhibits.",
        },
        {
          id: 7,
          name: "Mount Washington",
          address: "160 Grandview Ave, Pittsburgh, PA 15211",
          type: "City Feature",
          description: "Charming Mount Washington is known for its panoramic city views, from spots including Grandview Avenue’s Restaurant Row, home to upscale seafood and Italian restaurants. ",
        },
        {
          id: 8,
          name: "Carnegie Science Center",
          address: "One Allegheny Avenue, Pittsburgh, Pennsylvania",
          type: "Museum",
          description: "On the north bank of the Allegheny River is the huge Carnegie Science Center, which offers more than 250 hands-on exhibits. While visitors are exploring, they will learn about the ways that science and technology influence every aspect of our lives, from energy use, food production, and industrial processing to space exploration.",
        },
        {
          id: 9,
          name: "Duquesne Incline",
          address: "1197 W Carson St, Pittsburgh, PA 15219",
          type: "National Historic Landmark",
          description: "Opened in 1877, this landmark incline railway offers sweeping views of the city & a history museum.",
        },
        {
          id: 10,
          name: "Schenley Park",
          address: "1 Overlook Dr, Pittsburgh, PA 15217",
          type: "Park",
          description: "Schenley Park is a large municipal park located in Pittsburgh, Pennsylvania, between the neighborhoods of Oakland, Greenfield, and Squirrel Hill. It is also listed on the National Register of Historic Places as a historic district. In 2011, the park was named one of America's Coolest City Parks ",
        },
        {
          id: 11,
          name: "Kennywood",
          address: "4800 Kennywood Blvd, West Mifflin, PA 15122",
          type: "Amusement Park",
          description: "Kennywood is an amusement park located in West Mifflin, Pennsylvania just southeast of Pittsburgh. The park first opened on May 30, 1899, as a trolley park attraction at the end of the Mellon family's Monongahela Street Railway. ",
        },
        {
          id: 12,
          name: "The Andy Warhol Museum",
          address: "117 Sandusky St, Pittsburgh, PA 15212",
          type: "Museum",
          description: "The Andy Warhol Museum is located on the North Shore of Pittsburgh, Pennsylvania, in the United States. It is the largest museum in North America dedicated to a single artist. The museum holds an extensive permanent collection of art and archives from the Pittsburgh-born pop art icon Andy Warhol. ",
        },
        {
          id: 13,
          name: "Bicycle Heaven",
          address: "1800 Preble & Columbus Ave, Pittsburgh, PA 15233",
          type: "Museum",
          description: "Bicycle Heaven is the World's largest bicycle museum and bike shop.",
        },
        {
          id: 14,
          name: "Strip District",
          address: "2101 Smallman St, Pittsburgh, PA 15222",
          type: "Historic Neighborhood",
          description: "What was once a primarily industrial neighborhood is now packed with restaurants, international markets, museums and shops. The Strip District, which runs alongside the Allegheny River in Pittsburgh, measures only half a square mile, but it has plenty of attractions and eateries to fill up your day (and your stomach).",
        },
        {
          id: 15,
          name: "Three Rivers Heritage Trail",
          address: "Three Rivers Heritage Trail, Pittsburgh, PA 15203",
          type: "Park",
          description: "An asphalt link between some of Pittsburgh's most notable spots, the Three Rivers Heritage Trail lines the banks of the Allegheny, Monongahela and Ohio waterways. The 24-mile-long path connects the downtown area to the surrounding neighborhoods and makes it easy for people to travel across the city without getting in a car or on a bus. ",
        },
        ],
    itineraries: [],
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    }
  }
})
