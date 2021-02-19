<template>
  <div class="profile">
    <profile-list-com></profile-list-com>
  </div>
</template>

<script>
import ProfileListCom from "../components/profileCom/profileListCom.vue";

export default {
  name: "Profile",
  components: {
    ProfileListCom
  }
};
</script>
