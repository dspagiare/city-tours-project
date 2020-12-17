<template>
  <div class="reg">
    <div id="register" class="text-center">
      <form class="form-register" @submit.prevent="register">
        <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
        <div class="alert alert-danger" role="alert" v-if="registrationErrors">
          {{ registrationErrorMsg }}
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
        <input
          type="password"
          id="confirmPassword"
          class="form-control"
          placeholder="Confirm Password"
          v-model="user.confirmPassword"
          required
        />
        <router-link :to="{ name: 'login' }">Have an account?</router-link>
        <div class="butt-space">
          <button class="btn btn-lg btn-primary btn-block" type="submit">
            Create Account
          </button>
        </div>
      </form>
    </div>
    <div class="row">
      <div class="column">
        <a
          href="https://www.coursereport.com/blog/campus-spotlight-tech-elevator-pittsburgh"
        >
          <img
            id="TE"
            src="https://coursereport-s3-production.global.ssl.fastly.net/rich/rich_files/rich_files/4521/s1200/tech-elevator-pittsburgh-campus-spotlight.png"
          />
        </a>
      </div>
      <div class="column">
        <a
          href="https://www.visitpittsburgh.com/things-to-do/tours-sightseeing/"
        >
          <img
            id="PITT"
            src="https://i.vimeocdn.com/video/566530274_1280x720.jpg"
          />
        </a>
      </div>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style scoped>
.row {
  overflow: scroll;
}
.row {
  display: flex;
  justify-content: space-around;
}
.column {
  flex: 40%;
  display: flex;
  justify-content: space-around;
  padding-bottom: 100px;
}

@media only scren and (max-width: 400px) {
  #TE {
    height: 300px;
    width: 300px;
  }
  .column {
    align-content: center;
  }
  #PITT {
    height: 300px;
    width: 300px;
    align-items: flex-end;
  }
}
#TE {
  height: 300px;
  width: 715px;
  margin-top: 50px;
  align-items: flex-start;
}
#PITT {
  height: 300px;
  width: 715px;
  margin-top: 50px;
}
.butt-space {
  padding-top: 20px;
  padding-left: 80px;
  padding-bottom: 20px;
  text-align: center;
}
#register {
  display: block;
  width: 100%;
  max-width: 350px;
  margin: 0 auto;
  height: 100%;
}
button {
  background-color: #316786;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 50%;
}
button:hover {
  opacity: 0.8;
}
</style>
