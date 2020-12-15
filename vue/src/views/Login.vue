<template>
  <div id="login" class="text-center">
    <b-container class="container">
      <h1>
        Welcome to Yinzer Tours, the main place for visitors to plan out the
        landmarks and sites you wish to visit!
      </h1>
    </b-container>

    <form class="form-signin" @submit.prevent="login">
      <!--   
      <h2 class="h2 mb-3 font-weight-normal">Login</h2> -->
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >
        Thank you for registering, please sign in.
      </div>

      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />

      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <button type="submit">Sign in</button>
    </form>
    <router-link :to="{ name: 'register' }">Need an account?</router-link>
    <photo-gallery />
  </div>
</template>

<script>
import authService from "../services/AuthService";
import PhotoGallery from "./PhotoGallery.vue";

export default {
  name: "login",
  components: {
    PhotoGallery,
  },
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>
<style scoped>

.form-signin {
  display: block;
  width: 100%;
  max-width: 350px;
  margin: 0 auto;
 
 
}
.text-center{
 overflow-y:scroll; 
 position:relative;
 height: 90vh;
}
button {
  background-color: #316786;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 50%;
  border-radius: 4px;
}
button:hover {
  opacity: 0.8;
}
</style>
