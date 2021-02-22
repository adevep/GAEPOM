<template>
  <div id="yourTrackingProjects" class="container">
    <section>
      <b-table :data="yourpj" ref="table" :hoverable="isHoverable">
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
      </b-table>
    </section>
  </div>
</template>
<script>
export default {
  name: "YourTrackingProjects",
  props: {
    auser: String
  },
  data() {
    return {
      pjs: [],
      yourpj: [],
      defaultOpendDetails: [1],
      showDetailcon: true,
      isHoverable: true
    };
  },
  mounted() {
    this.trackList();
  },
  methods: {
    trackList: function() {
      let yourid = this.auser;
      this.axios
        .get("/track/gettrackinglist")
        .then(response => {
          this.pjs = response.data;
          this.yourpj = this.pjs.filter(function(item) {
            return item.project.userId.userId == yourid;
          });
        })
        .catch(error => {
          console.log("에러" + error);
        });
    }
  }
};
</script>
