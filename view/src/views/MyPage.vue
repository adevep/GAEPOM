<template>
  <div class="mypage">
    <section>
      <b-collapse :open="false" aria-id="contentIdForA11y1">
        <template #trigger>
          <b-button
            label="포트폴리오 조회"
            type="is-primary"
            aria-controls="contentIdForA11y1"
          />
        </template>
        <div class="notification">
          <div class="content">
            <portfolio></portfolio>
          </div>
        </div>
      </b-collapse>
    </section>
  </div>
</template>
<script>
import Portfolio from "@/components/Portfolio.vue";

export default {
  name: "App",
  components: {
    Portfolio
  }
};
</script>
