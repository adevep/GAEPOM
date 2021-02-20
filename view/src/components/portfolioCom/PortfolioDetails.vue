<template>
  <div class="ProjectDetails">
    <div class="container is-max-desktop pt-5">
      <section class="mt-6 mb-5">
        <article class="media">
          <figure class="media-left">
            <p class="image is-64x64">
              <img class="is-rounded" :src="imgURL + loginUser.userImage2" />
            </p>
          </figure>
          <div class="media-content">
            <div class="content" v-for="pf in allPortfolios" :key="pf.index">
              <p>
                <strong>{{ pf.userId.name }}</strong>
                <router-link
                  :to="{
                    name: 'yourpage',
                    params: { pickedid: pf.userId.userId }
                  }"
                  >@{{ pf.userId.userId }}</router-link
                >
                <br />
                {{ pf.userId.words }}
                {{ pf.userId.position }}
              </p>
            </div>
            <nav class="level is-mobile">
              <div class="level-left">
                <a class="level-item">
                  <span class="icon is-small"
                    ><i class="fas fa-reply"></i
                  ></span>
                </a>
                <a class="level-item">
                  <span class="icon is-small"
                    ><i class="fas fa-retweet"></i
                  ></span>
                </a>
                <a class="level-item">
                  <span class="icon is-small"
                    ><i class="fas fa-heart"></i
                  ></span>
                </a>
              </div>
            </nav>
          </div>
        </article>
      </section>
      <section v-for="pf in allPortfolios" :key="pf.index">
        <b-tabs type="is-boxed">
          <b-tab-item label="포트폴리오">
            <b-taglist>
              <b-tag type="is-primary">{{ pf.pfCategory }}</b-tag>
              <b-tag type="is-primary is-light">{{ pf.pfPosition }}</b-tag>
              <b-tag type="is-link">{{ pf.pfLang }}</b-tag>
              <b-tag type="is-link">{{ pf.pfTools }}</b-tag>
              <b-tag type="is-link">{{ pf.pfDbms }}</b-tag>
              <b-tag type="is-link is-light">{{
                new Date(pf.pfDuration).toLocaleDateString()
              }}</b-tag>
            </b-taglist>
            <h4 class="title is-4">{{ pf.pfSubtitle }}</h4>
            <h5 class="subtitle is-5">
              저의 프로젝트는 "{{ pf.pfDescription }}" 입니다.
              <br />
              프로젝트 포지션은 "{{ pf.pfPosition }}" 입니다.
              <br />
              프로젝트 기간은 "{{ pf.recDuration }}"입니다.
              <br />
              사용언어는 {{ pf.pfLang }}와 같습니다.
              <br />
              사용툴은 {{ pf.pfTools }}와 같습니다.
              <br />
              사용DBMS는 {{ pf.pfDbms }}와 같습니다.
              <br />
              잘 부탁드립니다. :)
            </h5>
            <br />
            <h4 class="title is-4">포트폴리오 외부링크: </h4>
            <h5 class="subtitle is-5">{{ pf.pfLink }}</h5>

          </b-tab-item>
        </b-tabs>
      </section>
    </div>
  </div>
</template>

<script>
import http from "../http-common";
import { mapState } from "vuex";

export default {
  name: "PortfolioDetails",
  props: ["pfSeq"],
  data() {
    const all = [];
    const allPortfolios = [];
    return {
      loginUser: {
        userImage: "default.png",
        userImage2: JSON.parse(sessionStorage.getItem("user")).userImage,
        userId: JSON.parse(sessionStorage.getItem("user")).userId
      },
      all,
      allPortfolios,
      aplPosi: "",
      words: "",
      isImageModalActive: false,
      isCardModalActive: false
    };
  },
  computed: {
    ...mapState(["imgURL"])
  },
  methods: {
    retrieveRecAndPj() {
      http
        .get(
          "/portfolio/gettotalpj/" +
            this.pjSeq +
            "?userId=" +
            this.loginUser.userId
        )
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
          this.allPortfolios = array;
          console.log(this.allPortfolios);

          //needPosi 나누기
          var array2 = [];
          var posi = {};

          array.forEach(function(element) {
            posi = [element.needPosi.split(",")].reduce(function(r, o) {
              Object.keys(o).forEach(function(k) {
                //var n = "p";
                r[k] = o[k];
              });
              return r;
            }, {});
            array2.push(posi);
            console.log(posi);
          });
          this.posiArray = posi;
          //console.log(this.allPortfolios.shift().pjSeq);
        })
        .catch(e => {
          console.log(e);
          this.errors.push(e);
        });
    },
    submitApp() {
      let formData = new FormData();

      //   formData.append("aplSeq", this.allPortfolios.shift().aplSeq);
      formData.append("userId", this.loginUser.userId);
      formData.append("words", this.words);
      formData.append("aplPosi", this.aplPosi);
      formData.append("selected", 0);
      formData.append("pjSeq", this.pjSeq);

      http
        .post("/app/create", formData, {
          headers: {
            "Content-Type": "multipart/form-data"
          }
        })
        .then(function() {
          alert("SUCCESS!!");
          //this.success();
        })
        .catch(function() {
          alert("FAILURE!!");
          //this.danger();
        });
    },
    success() {
                this.$buefy.notification.open({
                    message: '성공적으로 완료되었습니다.',
                    type: 'is-success',
                    position: 'is-bottom-right',
                })
            },
    danger() {
                this.$buefy.notification.open({
                    message: `다시 한번 확인해주세요.`,
                    type: 'is-danger',
                    position: 'is-bottom-right',
                })
            },
  },
  mounted() {
    this.retrieveRecAndPj();
  }
};
</script>
