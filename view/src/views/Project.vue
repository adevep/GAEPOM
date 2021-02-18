<template>
  <div class="project">
    <h1>This is project page</h1>
    <recruit-list></recruit-list>
    <!-- <project-details></project-details> -->
  </div>
</template>
<script>
import RecruitList from "../components/RecruitList.vue";
//import ProjectDetails from "../components/ProjectDetails.vue";

export default {
  name: "Project",
  components: {
    RecruitList
    //ProjectDetails
  }
};
</script>
