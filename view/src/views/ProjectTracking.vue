<template>
  <div class="projectTracking">
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
