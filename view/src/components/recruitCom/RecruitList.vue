<template>
  <div id="app" class="pt-5">
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link
      href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/@mdi/font@5.8.55/css/materialdesignicons.min.css"
    />
    <section>
      <div class="hero-body">
        <div class="container has-text-centered">
          <h1 class="title ">
            프로젝트 찾기
          </h1>
          <h2 class="subtitle centered">
            넥스트 삼산텍은 내 손에서!
          </h2>
          <nav
            class="breadcrumb has-dot-separator is-centered"
            aria-label="breadcrumbs"
          >
            <ul>
              <li><a href="/">홈페이지</a></li>
              <li class="is-active">
                <a href="#" aria-current="page">프로젝트</a>
              </li>
            </ul>
          </nav>
        </div>
      </div>
    </section>
    <section>
      <div class="container">
        <div class="section">
          <b-field grouped group-multiline class="mb-6">
            <b-field>
              <b-dropdown aria-role="list" v-model="selectedLoc">
                <template #trigger="{ active }">
                  <b-button
                    :label="selectedLoc"
                    :icon-right="active ? 'menu-up' : 'menu-down'"
                    centered
                  />
                </template>

                <b-dropdown-item
                  aria-role="listitem"
                  value="지역"
                  selected="selected"
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
                    :label="selectedCate"
                    :icon-right="active ? 'menu-up' : 'menu-down'"
                  />
                </template>
                <b-dropdown-item
                  aria-role="listitem"
                  value="카테고리"
                  selected="selected"
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
            <b-field class="ml-2">
              <b-input
                placeholder="프로젝트 타이틀 검색..."
                type="text"
                v-model="searchValue"
              ></b-input>
            </b-field>
          </b-field>
          <div class="container">
            <div
              class="card mt-4 mb-4"
              v-for="pj in filteredPjs"
              :key="pj.index"
            >
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
                  <b-tag type="is-primary" class="mr-2"
                    >{{ pj.location }}
                  </b-tag>
                  <b-tag type="is-primary">{{ pj.pjCategory }}</b-tag>
                  <br />
                  <footer class="card-footer"></footer>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>
<script>
import http from "../../http-common";

export default {
  name: "RecruitList",
  data() {
    const all = [];
    const allPjs = [];
    return {
      all,
      allPjs,
      selectedLoc: "지역",
      selectedCate: "카테고리",
      searchValue: "",
      pjLocation: null
    };
  },
  methods: {
    retrieveRecAndPj() {
      http
        .get("/recruit/gettotalpj?userId=user1")
        .then(response => {
          this.all = response.data;
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
        this.selectedLoc === "지역" &&
        this.selectedCate === "카테고리"
      ) {
        return this.allPjs;
      } else {
        if (this.searchValue.toString() != "") {
          var s = this.searchValue.toString();
          const loc = this.selectedLoc;
          const cate = this.selectedCate;
          if (this.selectedLoc != "지역") {
            return this.allPjs.filter(function(item) {
              return item.pjTitle.includes(s) && item.location == loc;
            });
          } else if (this.selectedCate != "카테고리") {
            return this.allPjs.filter(function(item) {
              return item.pjTitle.includes(s) && item.pjCategory == cate;
            });
          } else if (
            this.selectedCate != "카테고리" &&
            this.selectedLoc != "지역"
          ) {
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
        } else if (this.selectedCate != "카테고리") {
          const loc = this.selectedLoc;
          const cate = this.selectedCate;
          if (this.selectedLoc != "지역") {
            return this.allPjs.filter(function(item) {
              return item.pjCategory === cate && item.location == loc;
            });
          } else
            return this.allPjs.filter(function(item) {
              return item.pjCategory == cate;
            });
        } else if (this.selectedLoc != "지역") {
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
