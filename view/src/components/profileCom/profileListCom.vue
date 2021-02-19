<template>
  <div>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <b-field>
      <div id="sort-bar">
        <b-input
          type="text"
          v-model="searchStack"
          placeholder="Search"
        ></b-input>
      </div>
      <b-dropdown aria-role="list" v-model="selectedLoc">
        <template #trigger="{ active }">
          <b-button
            :label="selectedLoc"
            type="is-primary"
            :icon-right="active ? 'menu-up' : 'menu-down'"
          />
        </template>
        <!-- <b-button label="지역별" type="is-primary" slot="trigger" /> -->
        <b-dropdown-item aria-role="listitem" value="지역" selected="selected"
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
            type="is-primary"
            :icon-right="active ? 'menu-up' : 'menu-down'"
          />
        </template>
        <b-dropdown-item aria-role="listitem" value="포지션" selected="selected"
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
    <!-- <title>Cards List - Bulma Templates</title> -->
    <link
      rel="stylesheet"
      href="https://unpkg.com/bulma@0.9.0/css/bulma.min.css"
    />

    <!-- <link rel="stylesheet" href="../css/cards.css" /> -->

    <div class="container">
      <div class="section">
        <div class="columns">
          <div class="column has-text-centered">
            <h1 class="title" style="color: ghostwhite;">
              개폼에 등록된 인재들!
            </h1>
            <br />
          </div>
        </div>
        <div id="app" class="row columns is-multiline">
          <div v-for="(card, id) in filteredData" :key="id" class="column is-4">
            <div class="card large">
              <div class="card-image">
                <figure class="image is-16by9">
                  <img :src="imgURL + card.userImage" alt="Image" />
                </figure>
              </div>
              <div class="card-content">
                <div class="media">
                  <!-- 왼쪽 조그맣게 사진 넣는 곳-->
                  <!-- <div class="media-left">
                    <figure class="image is-48x48">
                      <img :src="card.avatar" alt="Image">
                    </figure>
                  </div> -->
                  <div class="media-content">
                    <p class="title is-4 no-padding">{{ card.name }}</p>
                    <p>
                      <span class="title is-6">
                        <!-- <a href='javascript:void(0);'> {{card.userId}} </a> </span> </p> -->
                        <router-link
                          :to="{
                            name: 'yourpage',
                            params: { pickedid: card.userId },
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
                <div class="content">
                  {{ card.stack }}
                  <div class="background-icon">
                    <span class="icon-twitter"></span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <footer class="footer">
      <div class="container">
        <div class="content has-text-centered">
          <div class="soc">
            <a href="#"
              ><i class="fa fa-github-alt fa-lg" aria-hidden="true"></i
            ></a>
            <a href="#"
              ><i class="fa fa-youtube fa-lg" aria-hidden="true"></i
            ></a>
            <a href="#"
              ><i class="fa fa-facebook fa-lg" aria-hidden="true"></i
            ></a>
            <a href="#"
              ><i class="fa fa-twitter fa-lg" aria-hidden="true"></i
            ></a>
          </div>
          <p>
            <strong>Bulma</strong> by
            <a href="http://jgthms.com">Jeremy Thomas</a>. The source code is
            licensed
            <a href="http://opensource.org/licenses/mit-license.php">MIT</a>.
            <br />
          </p>
        </div>
      </div>
    </footer>
  </div>
</template>
<script src="../js/cardsData.js"></script>
<script
  src="https://kit.fontawesome.com/7dc3015a44.js"
  crossorigin="anonymous"
></script>
<script src="https://cdn.jsdelivr.net/npm/vue"></script>

<script>
import axios from "axios";
import { mapState } from "vuex";
//import router from "../../router";
export default {
  data() {
    return {
      cardData: [JSON.parse(sessionStorage.getItem("user"))],
      searchStack: "",
      selectedLoc: "지역",
      selectedPos: "포지션",
    };
  },
  methods: {
    getUserList() {
      axios
        .get("http://localhost:80/getuserlist")
        .then((response) => {
          this.cardData = response.data;
        })
        .catch(() => {
          console.log("프로필 조회 실패");
        });
    },
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
                  item.address === loc &&
                  item.position === pos
                );
              });
            } else {
              return this.cardData.filter(function(item) {
                return (
                  (item.stack.toUpperCase().includes(upstk) ||
                    item.stack.toLowerCase().includes(lowstk)) &&
                  item.address === loc
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
                return item.address === loc && item.position === pos;
              });
            } else {
              return this.cardData.filter(function(item) {
                return item.address === loc;
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
    },
  },
};
</script>
