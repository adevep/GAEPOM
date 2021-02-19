<template>
  <div class="RecruitList">
    <!-- Bar containing all sort inputs -->
    <b-field>
      <b-input
        placeholder="프로젝트 타이틀 검색..."
        type="text"
        v-model="searchValue"
      ></b-input>

      <b-dropdown class="ml-3" aria-role="list" v-model="selectedLoc">
        <template #trigger="{ active }">
          <b-button
            label="지역"
            type="is-primary"
            :icon-right="active ? 'menu-up' : 'menu-down'"
            centered
          />
        </template>

        <!-- <b-button label="지역별" type="is-primary" slot="trigger" /> -->
        <b-dropdown-item aria-role="listitem" :value="null" selected="selected"
          >전체</b-dropdown-item
        >
        <b-dropdown-item aria-role="listitem" value="서울"
          >서울</b-dropdown-item
        >
        <b-dropdown-item aria-role="listitem" value="경기"
          >경기</b-dropdown-item
        >
        <b-dropdown-item aria-role="listitem" value="인천"
          >인천</b-dropdown-item
        >
        <b-dropdown-item aria-role="listitem" value="대전"
          >대전</b-dropdown-item
        >
        <b-dropdown-item aria-role="listitem" value="세종"
          >세종</b-dropdown-item
        >
        <b-dropdown-item aria-role="listitem" value="충북"
          >충북</b-dropdown-item
        >
        <b-dropdown-item aria-role="listitem" value="충남"
          >충남</b-dropdown-item
        >
        <b-dropdown-item aria-role="listitem" value="강원"
          >강원</b-dropdown-item
        >
        <b-dropdown-item aria-role="listitem" value="부산"
          >부산</b-dropdown-item
        >
        <b-dropdown-item aria-role="listitem" value="울산"
          >울산</b-dropdown-item
        >
        <b-dropdown-item aria-role="listitem" value="대구"
          >대구</b-dropdown-item
        >
        <b-dropdown-item aria-role="listitem" value="경북"
          >경북</b-dropdown-item
        >
        <b-dropdown-item aria-role="listitem" value="경남"
          >경남</b-dropdown-item
        >
        <b-dropdown-item aria-role="listitem" value="광주"
          >광주</b-dropdown-item
        >
        <b-dropdown-item aria-role="listitem" value="전북"
          >전북</b-dropdown-item
        >
        <b-dropdown-item aria-role="listitem" value="전남"
          >전남</b-dropdown-item
        >
        <b-dropdown-item aria-role="listitem" value="제주"
          >제주</b-dropdown-item
        >
      </b-dropdown>
      <b-dropdown aria-role="list" v-model="selectedCate">
        <template #trigger="{ active }">
          <b-button
            label="카테고리별"
            type="is-primary"
            :icon-right="active ? 'menu-up' : 'menu-down'"
          />
        </template>
        <b-dropdown-item aria-role="listitem" :value="null" selected="selected"
          >전체</b-dropdown-item
        >
        <b-dropdown-item aria-role="listitem" value="웹앱"
          >웹앱</b-dropdown-item
        >
        <b-dropdown-item aria-role="listitem" value="모바일앱"
          >모바일앱</b-dropdown-item
        >
        <b-dropdown-item aria-role="listitem" value="데이터사이언스"
          >데이터사이언스</b-dropdown-item
        >
        <b-dropdown-item aria-role="listitem" value="게임개발"
          >게임개발</b-dropdown-item
        >
      </b-dropdown>
    </b-field>

    <div class="container">
      <div class="card mt-4 mb-4" v-for="pj in filteredPjs" :key="pj.index">
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
            <b-tag type="is-primary" class="mr-2">{{ pj.location }} </b-tag>
            <b-tag type="is-primary">{{ pj.pjCategory }}</b-tag>
            <br />
            <footer class="card-footer"></footer>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import http from "../http-common";

export default {
  name: "RecruitList",
  data() {
    const all = [];
    const allPjs = [];
    return {
      loginUser: JSON.parse(sessionStorage.getItem("user")).userId,
      all,
      allPjs,
      selectedLoc: null,
      selectedCate: null,
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
        .get("/recruit/gettotalpj?userId=" + this.loginUser)
        .then(response => {
          this.all = response.data;
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
        })
        .catch(e => {
          console.log(e);
          this.errors.push(e);
        });
    }
  },
  mounted() {
    this.retrieveRecAndPj();
  },
  computed: {
    filteredPjs: function() {
      if (
        this.searchValue.toString() === "" &&
        this.selectedLoc === null &&
        this.selectedCate === null
      ) {
        return this.allPjs;
      } else {
        if (this.searchValue.toString() != "") {
          var s = this.searchValue.toString();
          const loc = this.selectedLoc;
          const cate = this.selectedCate;
          if (this.selectedLoc != null) {
            return this.allPjs.filter(function(item) {
              return item.pjTitle.includes(s) && item.location == loc;
            });
          } else if (this.selectedCate != null) {
            return this.allPjs.filter(function(item) {
              return item.pjTitle.includes(s) && item.pjCategory == cate;
            });
          } else if (this.selectedCate != null && this.selectedLoc != null) {
            return this.allPjs.filter(function(item) {
              return (
                item.pjTitle.includes(s) &&
                item.pjCategory == cate &&
                item.selectedLoc == loc
              );
            });
          } else
            return this.allPjs.filter(function(item) {
              return item.pjTitle.includes(s);
            });
        } else if (this.selectedCate != null) {
          const loc = this.selectedLoc;
          const cate = this.selectedCate;
          if (this.selectedLoc != null) {
            return this.allPjs.filter(function(item) {
              return item.pjCategory === cate && item.location == loc;
            });
          } else
            return this.allPjs.filter(function(item) {
              return item.pjCategory == cate;
            });
        } else if (this.selectedLoc != null) {
          const loc = this.selectedLoc;
          console.log(loc);
          return this.allPjs.filter(function(item) {
            return item.location === loc;
          });
        }
        return this.allPjs;
      }
    }
  }
};
</script>
