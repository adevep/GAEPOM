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
        <b-button
          type="is-primary"
          outlined
          @click="
            loginActions({
              userid: userid,
              password: password
            })
          "
          position="is-centered"
          size="is-large"
          >로그인</b-button
        >
        <br /><br />
      </div>
    </div>
  </section>
</template>
<script>
/*
helper 함수 종류

mapState - state를 연결해주는 함수

mapGetters - getters를 연결해주는 함수

mapMutations - mutations를 연결해주는 함수

mapActions - actions를 연결해주는 함수
*/
import { mapState, mapActions } from "vuex";
export default {
  data() {
    return {
      userid: null,
      password: null
    };
  },
  computed: {
    ...mapState([])
  },
  methods: {
    // store에 action의 login을 이안에서 사용가능하게 선언
    ...mapActions(["loginActions"])
  }
};
</script>
