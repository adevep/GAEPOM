<template>
  <div class="col-md-6">
    <h4>Apps List</h4>
    <ul>
      <li v-for="(app, id) in applications" :key="id">
        {{ app.userId }}
        {{ app.aplPosi }}
      </li>
    </ul>
    <button @click="retrieveApps()">
      Retrieve app {{ applications }} data from database
    </button>
  </div>
</template>

<script>
import AppService from "../services/AppService";
//import axios from "axios";
export default {
  name: "AppList",
  data() {
    return {
      applications: []
    };
  },
  methods: {
    retrieveApps() {
      AppService.getAll()
        .then(response => {
          this.applications = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
          this.errors.push(e);
        });
    }
  },
  mounted() {
    this.retrieveApps();
  }
};
</script>
