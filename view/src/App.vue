<template>
  <v-app>
    <v-app-bar app dark>
      <div class="d-flex align-center mr-2">
        개폼 | 개폼난다
      </div>

      <v-btn to="/portfolio" text>
        포트폴리오 조회
      </v-btn>

      <v-btn to="/add" text>
        포트폴리오 등록
      </v-btn>
    </v-app-bar>

    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: "app",
};
</script>