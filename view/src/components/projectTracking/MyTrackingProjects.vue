<template>
  <div id="app" class="container">
    <section>
      <b-table :data="proj" ref="table" :hoverable="isHoverable">
        <b-table-column
          field="aplSeq"
          label="글"
          width="40"
          numeric
          centered
          v-slot="props"
        >
          {{ props.row.project.pjSeq }}
        </b-table-column>
        <b-table-column
          field="pjCategory"
          label="분야"
          sortable
          v-slot="props"
          centered
        >
          {{ props.row.project.pjCategory }}
        </b-table-column>
        <b-table-column
          field="pjTitle"
          label="프로젝트"
          sortable
          v-slot="props"
          centered
        >
          <router-link
            :to="{
              name: 'ProjectTrackingDetail',
              params: { track: props.row }
            }"
            >{{ props.row.project.pjTitle }}</router-link
          >
        </b-table-column>
        <b-table-column
          field="pjDescription"
          label="설명"
          sortable
          v-slot="props"
          centered
        >
          {{ props.row.project.pjDescription }}
        </b-table-column>
        <b-table-column
          field="pjSeq"
          label="관리"
          sortable
          v-slot="props"
          centered
        >
          <b-button @click="deleteTracking(props.row.trackSeq)">삭제</b-button>
        </b-table-column>
      </b-table>
    </section>
  </div>
</template>
<script>
export default {
  name: "MyTrackingProjects",
  data() {
    return {
      loginUser: JSON.parse(sessionStorage.getItem("user")).userId,
      pjs: [],
      proj: [],
      defaultOpendDetails: [1],
      showDetailcon: true,
      isHoverable: true
    };
  },
  created() {
    this.trackList();
  },
  methods: {
    trackList: function() {
      this.axios
        .get("/track/gettrackinglist")
        .then(response => {
          this.pjs = response.data;
          this.proj = this.pjs.filter(function(item) {
            return (
              item.project.userId.userId ==
              JSON.parse(sessionStorage.getItem("user")).userId
            );
          });
        })
        .catch(error => {
          console.log("에러" + error);
        });
    },
    deleteTracking: function(id) {
      this.axios
        .delete("/track/deleteprojecttracking", {
          params: {
            trackSeq: id
          }
        })
        .then(response => {
          console.log(response);
          this.trackList();
          this.success();
        })
        .catch(ex => {
          console.warn("ERROR!!!!! : ", ex);
        });
    },
    success() {
      this.$buefy.notification.open({
        message: "글이 삭제되었습니다.",
        type: "is-success",
        position: "is-bottom-right"
      });
    }
  }
};
</script>
