<template>
  <div class="hello">
    <!-- <section class="hero is-link is-fullheight-with-navbar">
      <div class="hero-body">
        <div class="container">
          <p class="title">
            개폼난다
          </p>
          <p class="subtitle" v-if="$store.state.userInfo != null">
            {{ userInfo.name }}님 개폼에 오신 걸 환영합니다.
          </p>
        </div>
      </div>
    </section> -->
    <section class="hero is-white is-fullheight">
      <div class="hero-body">
        <div class="container">
          <div class="columns  is-vcentered reverse-columns">
            <div
              class="column
          is-10-mobile is-offset-1-mobile
          is-10-tablet is-offset-1-tablet
          is-5-desktop is-offset-1-desktop
          is-5-widescreen is-offset-1-widescreen
          is-5-fullhd is-offset-1-fullhd"
            >
              <h1 class="title titled is-1 mb-6">
                프로젝트를 위해 모이는 사람들
              </h1>
              <h2
                class=" subtitled subtitle has-text-grey is-4 has-text-weight-normal is-family-sans-serif"
              >
                당신만을 위한, 당신이 찾던 그 팀</br>
                당신이 완성할 그 프로젝트 </br>
                지금 여기서 시작하세요</br>
              </h2>
              <div class="buttons">
                <button class="button is-black">팀원찾기</button>
                <button class="button">프로젝트</button>
              </div>
            </div>
            <div
              class="column
          is-10-mobile is-offset-1-mobile
          is-10-tablet is-offset-1-tablet
          is-4-desktop is-offset-1-desktop
          is-4-widescreen is-offset-1-widescreen
          is-4-fullhd is-offset-1-fullhd"
            >
              <figure class="image is-square">
                <img src="../assets/main.png" />
              </figure>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "HelloWorld",
  props: {
    msg: String
  },
  data: () => ({}),
  computed: {
    ...mapState(["userInfo"])
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
