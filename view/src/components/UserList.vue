<template>
  <div class="col-md-6">
    <h4>Users List</h4>
    <ul>
      <li v-for="(user, id) in users" :key="id">
        {{ user.name }}
      </li>
    </ul>
    <button @click="retrieveUsers()">
      Retrieve user {{ users }} data from database
    </button>
  </div>
</template>

<script>
import AppService from "../services/ProjectService";
export default {
  name: "UserList",
  data() {
    return {
      users: []
    };
  },
  methods: {
    retrieveUsers() {
      alert(1);
      AppService.getAll()
        .then(response => {
          alert(2);
          this.users = response.data;
          alert(3);
          console.log(response.data);
        })
        .catch(e => {
          alert(4);
          console.log(e);
          this.errors.push(e);
        });
    }
  },
  mounted() {
    this.retrieveUsers();
  }
};
</script>
