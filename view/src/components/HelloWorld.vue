<template>
  <div class="hello">
    <section class="hero is-link is-fullheight-with-navbar">
      <div class="hero-body">
        <div class="container">
          <p class="title">
            개폼난다
          </p>
          <p class="subtitle">
            개폼에 오신 걸 환영합니다.
          </p>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: "HelloWorld",
  props: {
    msg: String
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
