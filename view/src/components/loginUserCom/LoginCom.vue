<template>
  <section>
    <div class="container is-max-desktop">
      <div class="notification is-accent">
        <b-field label="ID" type="" message="" align="left">
          <b-input
            v-model="userid"
            placeholder="아이디"
            maxlength="30"
            size="is-medium"
          ></b-input>
        </b-field>

        <b-field label="Password" type="" message="" align="left">
          <b-input
            v-model="password"
            type="password"
            placeholder="비밀번호"
            maxlength="30"
            size="is-medium"
          >
          </b-input>
        </b-field>
        <center>
        <b-button
          type="is-primary"
          outlined
          @click="login()"
          position="is-centered"
          size="is-large"
          >로그인</b-button
        > </center>
        <br /><br />
      </div>
    </div>
  </section>
</template>
<script>
import router from "../../router";
import axios from "axios";
import { mapActions } from "vuex";
export default {
  data() {
    return {
      userid: "",
      password: "",
    };
  },
  methods: {
    ...mapActions(['loginUserAct']),
    login() {
      axios
        .post(
          "http://localhost:80/login?userid=" +
            this.userid +
            "&password=" +
            this.password
        )
        .then(response => {
          sessionStorage.setItem("user", JSON.stringify(response.data));
          this.loginUserAct(response.data)
          router.push({ name: "mypage" });
        })
        .catch(() => {
          alert("ID와 Password를 확인해주세요.");
        });
    }
  }
};
</script>
