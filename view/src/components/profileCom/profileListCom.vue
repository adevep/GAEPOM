<template>
  <div>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Cards List - Bulma Templates</title>
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
          <div v-for="(card, id) in cardData" :key="id" class="column is-4">
            <div class="card large">
              <div class="card-image">
                <figure class="image is-16by9">
                   <img :src="imgURL + card.userImage" alt="Image"> 
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
                     <p class="title is-4 no-padding">{{card.name}}</p> 
                      <p>
                     <span class="title is-6"> 
                    <!-- <a href='javascript:void(0);'> {{card.userId}} </a> </span> </p> -->
                    <router-link :to="{ name: 'yourpage', params: { pickedid: card.userId } }">{{ card.userId }}</router-link> </span> </p>
                    <p class="subtitle is-6">{{card.position}}</p>
                  </div>
                </div>
                <div class="content">
                    {{card.stack}} 
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
      cardData: [JSON.parse(sessionStorage.getItem("user"))]
    };
  },
  methods: {
    getUserList() {
      axios
        .get("http://localhost:80/getuserlist")
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
    ...mapState(["imgURL"])
  }
};
</script>
