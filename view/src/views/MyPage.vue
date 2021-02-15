<template>
  <div class="mypage">
    <section>
      <b-collapse :open="false" aria-id="contentIdForA11y1">
        <template #trigger>
          <b-button
            label="내 지원서 보기"
            type="is-primary"
            aria-controls="contentIdForA11y1"
          />
        </template>
        <div class="content">
          <application></application>
        </div>
      </b-collapse>
    </section>
    <br />
    <section>
      <b-collapse :open="false" aria-id="contentIdForA11y1">
        <template #trigger>
          <b-button
            label="지원자 보기"
            type="is-primary"
            aria-controls="contentIdForA11y1"
          />
        </template>
        <div class="content">
          <all-apps></all-apps>
        </div>
      </b-collapse>
    </section>
  </div>
</template>
<script>
import Application from "@/components/Application.vue";
import AllApps from "../components/AllApps.vue";

export default {
  name: "App",
  components: {
    Application,
    AllApps
  }
};
</script>
