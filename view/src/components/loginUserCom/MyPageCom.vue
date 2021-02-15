<template>
  <div>
    <router-view class="box"></router-view>
    <span
      class="card"
      style="width: 500px; height: 800px; background-color: #ffffe0;"
    >
      <div class="card-image">
        <figure class="image is-4by3">
          <img :src="imgURL + userInfo.userImage" alt="Placeholder image" />
        </figure>
      </div>

      <div class="card-content">
        <div class="media">
          <div class="media-left"></div>
          <div class="media-content">
            <p class="title is-4" v-text="userInfo.userId"></p>
            <p class="subtitle is-6" v-text="userInfo.name"></p>
          </div>
        </div>

        <div class="content">
          이메일 : {{ userInfo.email }} <br />
          전화번호 : {{ userInfo.phoneNum }} <br />
          포지션 : {{ userInfo.position }} <br />
          stack : {{ userInfo.stack }} <br />
          <b-button type="is-primary is-light" @click="updateUser()"
            >정보수정</b-button
          >
          &nbsp;
          <b-button type="is-danger is-light" @click="deleteUser()"
            >회원탈퇴</b-button
          >
        </div>
      </div>
    </span>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";
import router from "../../router";
export default {
  data: () => ({}),
  computed: {
    ...mapState(["userInfo", "imgURL"]),
  },
  methods: {
    updateUser() {
      return this.$router.push({ name: "updateUser" });
    },
    deleteUser() {
      let formData = new FormData();

      formData.append("userId", this.userInfo.userId);
      formData.append("password", this.userInfo.password);
      formData.append("name", this.userInfo.name);
      formData.append("age", this.userInfo.age);
      formData.append("email", this.userInfo.email);
      formData.append("phoneNum", this.userInfo.phonenum);
      formData.append("address", this.userInfo.address);
      formData.append("stack", this.userInfo.stack);
      formData.append("position", this.userInfo.position);
      formData.append("file", this.userInfo.file);

      axios
        .post("http://localhost:80/deleteuser", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then(() => {
          sessionStorage.removeItem("user");
          this.userInfo = null;
          console.log("SUCCESS!!");
          router.push({ name: "mypage" });
        })
        .catch(function() {
          console.log("FAILURE!!");
        });
      return this.$router.push({ name: "Home" });
    },
  },
};
</script>
<style scoped>
.card {
  position: static;
  border: 1px solid black;
  float: left;
  margin: 1px;
  padding: 130px 50px;
  width: 1000px;
  height: 1000px;
}
.box {
  position: static;
  float: right;
  width: 500px;
  height: 100px;
}
</style>
