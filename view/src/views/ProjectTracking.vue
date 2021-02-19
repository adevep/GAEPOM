<template>
  <div class="projectTracking">
    <h1>This is projectTracking page</h1>
    <TrackingList msg="리스트 출력" />
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
