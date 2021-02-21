<template>
  <div id="app" class="container is-max-desktop pt-5">
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link
      href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
      rel="stylesheet"
    />
    <section>
      <div class="hero-body">
        <div class="container has-text-centered">
          <h1 class="title ">
            로그인
          </h1>
          <h2 class="subtitle centered">
            오늘도 환영합니다.
          </h2>
        </div>
      </div>
    </section>
    <section>
      <div class="container is-max-desktop">
        <div class="columns is-mobile">
          <div class="column is-half is-offset-one-quarter">
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
                size="is-medium"
                >로그인</b-button
              >
            </center>
            <br /><br />
          </div>
        </div>
      </div>
    </section>
  </div>
</template>
<script>
import router from "../../router";
import axios from "axios";
import { mapActions } from "vuex";
export default {
  data() {
    return {
      userid: "",
      password: ""
    };
  },
  methods: {
    ...mapActions(["loginUserAct"]),
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
          this.loginUserAct(response.data);
          this.success();
          router.push({ name: "mypage" });
        })
        .catch(() => {
          this.danger();
        });
    },
    success() {
      this.$buefy.notification.open({
        message: "로그인 성공!",
        type: "is-success",
        position: "is-top"
      });
    },
    danger() {
      this.$buefy.notification.open({
        message: `ID와 Password를 확인해주세요.`,
        type: "is-danger",
        position: "is-top"
      });
    }
  }
};
</script>
