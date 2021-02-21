<template>
  <div id="app" class="container">
    <section>
      <b-table :data="pjs" ref="table" :hoverable="isHoverable">
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
          <button @click="deleteTracking(props.row.trackSeq)">삭제</button>
          <!-- <p>{{props.row}}</p> -->
        </b-table-column>
        <!-- <b-table-column
          field="update"
          label="수정하기"
          sortable
          v-slot="props"
          centered
        >
          <router-link
            class="tag is-warn"
            :to="{
              name: 'updateproject',
              params: { pjNum: props.row.pjSeq }
            }"
            >수정</router-link
          >
        </b-table-column> -->
      </b-table>
    </section>
  </div>
</template>
<script>
export default {
  name: "MyTrackingProjects",
  data() {
    const pjs = [];
    return {
      tracks: [],
      loginUser: JSON.parse(sessionStorage.getItem("user")).userId,
      pjs,
      defaultOpendDetails: [1],
      showDetailcon: true,
      isHoverable: true
    };
  },
  methods: {
    trackList: function() {
      this.axios
        .get("/gettrackinglistaxios")
        .then(response => {
          this.pjs = response.data;
          this.tracks = this.pjs;
          console.log("==========list==========");
          console.log(response);
          console.log("==========list==========");
        })
        .catch(error => {
          console.log("에러" + error);
        });
    },
    deleteTracking: function(id) {
      this.axios
        .delete("/deleteprojecttracking", {
          params: {
            trackSeq: id
          }
        })
        .then(response => {
          console.log(response);
          this.trackList();
        })
        .catch(ex => {
          console.warn("ERROR!!!!! : ", ex);
        });
    }
  },

  mounted() {
    this.trackList();
  }
};
</script>
