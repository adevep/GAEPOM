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
            팀원 찾기
          </h1>
          <h2 class="subtitle centered">
            팀플의 꽃, 팀원을 찾으세요.
          </h2>
          <nav
            class="breadcrumb has-dot-separator is-centered"
            aria-label="breadcrumbs"
          >
            <ul>
              <li><a href="/">홈페이지</a></li>
              <li class="is-active">
                <a href="#" aria-current="page">프로필</a>
              </li>
            </ul>
          </nav>
        </div>
      </div>
    </section>
    <section>
      <div class="container">
        <div class="section">
          <div class="columns">
            <b-field grouped group-multiline class="mb-6">
              <b-dropdown aria-role="list" v-model="selectedLoc">
                <template #trigger="{ active }">
                  <b-button
                    :label="selectedLoc"
                    :icon-right="active ? 'menu-up' : 'menu-down'"
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
              <b-dropdown aria-role="list" v-model="selectedPos">
                <template #trigger="{ active }">
                  <b-button
                    :label="selectedPos"
                    :icon-right="active ? 'menu-up' : 'menu-down'"
                  />
                </template>
                <b-dropdown-item
                  aria-role="listitem"
                  value="포지션"
                  selected="selected"
                  >전체</b-dropdown-item
                >
                <b-dropdown-item aria-role="listitem" value="개발자"
                  >개발자</b-dropdown-item
                >
                <b-dropdown-item aria-role="listitem" value="기획자"
                  >기획자</b-dropdown-item
                >
                <b-dropdown-item aria-role="listitem" value="디자이너"
                  >디자이너</b-dropdown-item
                >
              </b-dropdown>
            </b-field>
            <b-field class="ml-2">
              <b-input
                type="text"
                expanded
                v-model="searchStack"
                placeholder="기술 검색 ex Java"
              ></b-input>
            </b-field>
            <div class="column has-text-centered"></div>
          </div>
          <div id="app" class="row columns is-multiline">
            <div
              v-for="(card, id) in filteredData"
              :key="id"
              class="column is-3"
            >
              <div class="card medium">
                <div class="card-image">
                  <figure class="image is-16by11">
                    <img :src="imgURL + card.userImage" alt="Image" />
                  </figure>
                </div>
                <div class="card-content">
                  <div class="media">
                    <div class="media-content">
                      <p class="title is-4 no-padding">{{ card.name }}</p>
                      <p>
                        <span class="title is-5">
                          <router-link
                            :to="{
                              name: 'yourpage',
                              params: { pickedid: card.userId }
                            }"
                            >{{ card.userId }}</router-link
                          >
                        </span>
                      </p>
                      <p class="subtitle is-6">{{ card.position }}</p>
                      <br />
                      <p class="subtitle is-6">{{ card.address }}</p>
                    </div>
                  </div>
                  <b-tag type="is-primary">
                    {{ card.stack }}
                  </b-tag>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>
<script src="../js/cardsData.js"></script>
<script>
import axios from "axios";
import { mapState } from "vuex";
export default {
  data() {
    return {
      cardData: [JSON.parse(sessionStorage.getItem("user"))],
      searchStack: "",
      selectedLoc: "지역",
      selectedPos: "포지션"
    };
  },
  methods: {
    getUserList() {
      axios
        .get("http://localhost:80/user/getlist")
        .then(response => {
          this.cardData = response.data;
        })
        .catch(() => {
          console.log("프로필 조회 실패");
        });
    }
  },
  mounted() {
    this.getUserList();
  },
  computed: {
    ...mapState(["imgURL"]),
    filteredData: function() {
      var upstk = this.searchStack.toUpperCase();
      var lowstk = this.searchStack.toLowerCase();
      const loc = this.selectedLoc;
      const pos = this.selectedPos;
      if (
        this.searchStack === "" &&
        this.selectedLoc === "지역" &&
        this.selectedPos === "포지션"
      ) {
        return this.cardData;
      } else {
        if (this.searchStack !== "") {
          if (this.selectedLoc !== "지역") {
            if (this.selectedPos !== "포지션") {
              return this.cardData.filter(function(item) {
                return (
                  (item.stack.toUpperCase().includes(upstk) ||
                    item.stack.toLowerCase().includes(lowstk)) &&
                  item.address.includes(loc) &&
                  item.position === pos
                );
              });
            } else {
              return this.cardData.filter(function(item) {
                return (
                  (item.stack.toUpperCase().includes(upstk) ||
                    item.stack.toLowerCase().includes(lowstk)) &&
                  item.address.includes(loc)
                );
              });
            }
          } else {
            if (this.selectedPos !== "포지션") {
              return this.cardData.filter(function(item) {
                return (
                  (item.stack.toUpperCase().includes(upstk) ||
                    item.stack.toLowerCase().includes(lowstk)) &&
                  item.position === pos
                );
              });
            } else {
              return this.cardData.filter(function(item) {
                return (
                  item.stack.toUpperCase().includes(upstk) ||
                  item.stack.toLowerCase().includes(lowstk)
                );
              });
            }
          }
        } else {
          if (this.selectedLoc !== "지역") {
            if (this.selectedPos !== "포지션") {
              return this.cardData.filter(function(item) {
                return item.address.includes(loc) && item.position === pos;
              });
            } else {
              return this.cardData.filter(function(item) {
                return item.address.includes(loc);
              });
            }
          } else {
            if (this.selectedPos !== "포지션") {
              return this.cardData.filter(function(item) {
                return item.position === pos;
              });
            }
          }
        }
        return this.cardData;
      }
    }
  }
};
</script>
