<template>
  <div class="projectTracking">
    <h1 style="font-size:200%;">당신의 꿈을 펼쳐라</h1>
    <tracking-list />
  </div>
</template>

<script>
// @ is an alias to /src
import TrackingList from "@/components/projectTracking/TrackingList.vue";


export default {
  name: "ProjectTracking",
  components: {
    TrackingList,
  }
};
</script>
