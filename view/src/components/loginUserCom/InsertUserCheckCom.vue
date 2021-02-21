<template>
  <section></section>
</template>

<script>
import router from "../../router";
export default {
  methods: {
    confirm() {
      this.$buefy.dialog.confirm({
        message: "로그인 페이지로 이동하시겠습니까?",
        onConfirm: () => router.push({ name: "login" }),
        onCancel: () => router.push({ name: "Home" })
      });
    }
  },
  mounted() {
    this.confirm();
  }
};
</script>
