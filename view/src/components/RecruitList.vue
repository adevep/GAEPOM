<template>
  <div class="RecruitList">
    <!-- Bar containing all sort inputs -->
    <!-- <div id="sort-bar">
    <select name="sortBy" id="select" v-model="sortBy">
      <option value="alphabetically">Alphabetically</option>
      <option value="location">pjlocation</option>
    </select>
    <button v-on:click="ascending = !ascending" class="sort-button">
      <i v-if="ascending" class="fa fa-sort-up"></i>
      <i v-else class="fa fa-sort-down"></i>
    </button>
    <input type="text" v-model="pjLocation" id="location-input"></input>
    <input type="text" v-model="searchValue" placeholder="Search" id="search-input"></input>
    <i class="fa fa-search"></i>
  </div> -->

    <div class="container">
      <div class="card" v-for="pj in allPjs" :key="pj.index">
        <header class="card-header">
          <p class="card-header-title">
            <router-link
              :to="{ name: 'details', params: { pjSeq: pj.pjSeq } }"
              >{{ pj.pjTitle }}</router-link
            >
          </p>
        </header>
        <div class="card-content">
          <div class="content">
            {{ pj.pjDescription }}
            <b-tag type="is-info">{{ pj.location }}</b-tag>
            <br />
            <footer class="card-footer"></footer>
          </div>
        </div>
      </div>
    </div>

    <hr />
    <b-pagination
      :total="total"
      v-model="current"
      :range-before="rangeBefore"
      :range-after="rangeAfter"
      :order="order"
      :size="size"
      :simple="isSimple"
      :rounded="isRounded"
      :per-page="perPage"
      :icon-prev="prevIcon"
      :icon-next="nextIcon"
      aria-next-label="Next page"
      aria-previous-label="Previous page"
      aria-page-label="Page"
      aria-current-label="Current page"
    >
    </b-pagination>
  </div>
</template>
<script>
import http from "../http-common";

export default {
  name: "RecruitList",
  data() {
    const all = [];
    const allPjs = [];
    // const result = [...recs, ...pjs];
    // console.log(result);
    return {
      all,
      allPjs,
      // allPjs: {
      //   applications: [],
      //   location: "",
      //   needNum: 0,
      //   needPosi: "",
      //   pjCategory: "",
      //   pjDbms: "",
      //   pjDescription: "",
      //   pjDuration: "",
      //   pjLang: "",
      //   pjSeq: 0,
      //   pjTitle: "",
      //   pjTools: "",
      //   preference: "",
      //   recDate: "",
      //   recDuration: "",
      //   recSeq: [],
      //   recStatus: 0,
      //   trackSeq: null,
      //   userId: []
      // },
      // result,
      sortBy: "alphabetically",
      searchValue: "",
      pjLocation: null,
      total: all.length,
      //pagination은 나중에 연동시켜야함
      current: 10,
      perPage: 1,
      rangeBefore: 3,
      rangeAfter: 1,
      order: "",
      size: "",
      isSimple: false,
      isRounded: false,
      prevIcon: "chevron-left",
      nextIcon: "chevron-right"
    };
  },
  methods: {
    retrieveRecAndPj() {
      http
        .get("/recruit/gettotalpj?userId=user1")
        .then(response => {
          this.all = response.data;
          console.log(response.data);
          //Joined된 데이터 나누기
          var array = [];
          this.all.forEach(function(element) {
            var allPj;
            allPj = [element[0], element[1]].reduce(function(r, o) {
              Object.keys(o).forEach(function(k) {
                r[k] = o[k];
              });
              return r;
            }, {});
            array.push(allPj);
          });
          this.allPjs = array;
          console.log(this.allPjs);
        })
        .catch(e => {
          console.log(e);
          this.errors.push(e);
        });
    },
    retrieveRecs() {
      http
        .get("/recruit/getrecs?userId=user1")
        .then(response => {
          this.recs = response.data;
        })
        .catch(e => {
          console.log(e);
          this.errors.push(e);
        });
    },
    retrievePjs() {
      http
        .get("/recruit/getpjs?userId=user1")
        .then(response => {
          this.pjs = response.data;
        })
        .catch(e => {
          console.log(e);
          this.errors.push(e);
        });
    }
  },
  //   computed: {
  //   filteredPjs() {
  //     let tempPjs = this.pjs

  //     if (this.searchValue != '' && this.searchValue) {
  //         tempPjs = tempPjs.filter((item) => {
  //           return item.title
  //             .toUpperCase()
  //             .includes(this.searchValue.toUpperCase())
  //         })
  //       }

  //       if (this.pjLocation)
  //       tempPjs = tempPjs.filter((item) => {
  //         return (item.location <= this.maxCookingTime)
  //       })

  //         tempPjs = tempPjs.sort((a, b) => {
  //             if (this.sortBy == 'alphabetically') {
  //                 let fa = a.title.toLowerCase(), fb = b.title.toLowerCase()

  //               if (fa < fb) {
  //                 return -1
  //               }
  //               if (fa > fb) {
  //                 return 1
  //               }
  //               return 0
  //             } else if (this.sortBy == 'cookingTime') {
  //               return a.cookingTime - b.cookingTime
  //         }
  //         })

  //         if (!this.ascending) {
  //         	tempPjs.reverse()
  //         }

  //         return tempPjs
  //   }
  // },
  mounted() {
    // this.retrieveRecs();
    // this.retrievePjs();
    this.retrieveRecAndPj();
    this.retrievePjs();
  }
};
</script>
