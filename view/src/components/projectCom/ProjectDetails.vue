<template>
  <div class="ProjectDetails">
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link
      href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/@mdi/font@5.8.55/css/materialdesignicons.min.css"
    />
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
    />
    <link
      rel="stylesheet"
      href="//cdn.jsdelivr.net/gh/xpressengine/xeicon@2.3.1/xeicon.min.css"
    />
    <div class="container is-max-desktop pt-5">
      <section class="mt-6 mb-5" v-for="pj in allPjs" :key="pj.index">
        <article class="media">
          <figure class="media-left">
            <p class="image is-64x64">
              <img class="is-rounded" :src="imgURL + pj.userId.userImage" />
            </p>
          </figure>
          <div class="media-content">
            <div class="content">
              <p>
                <strong>{{ pj.userId.name }}</strong>
                <router-link
                  :to="{
                    name: 'yourpage',
                    params: { pickedid: pj.userId.userId }
                  }"
                  >@{{ pj.userId.userId }}</router-link
                >
                <br />
                {{ pj.userId.words }}
                {{ pj.userId.position }}
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
      <section v-for="pj in allPjs" :key="pj.index">
        <b-tabs type="is-boxed">
          <b-tab-item label="프로젝트">
            <b-taglist>
              <b-tag type="is-primary">{{ pj.pjCategory }}</b-tag>
              <b-tag type="is-primary is-light">{{ pj.location }}</b-tag>
              <b-tag type="is-link">{{ pj.pjTools }}</b-tag>
              <b-tag type="is-link is-light">{{ pj.needPosi }}</b-tag>
              <b-tag type="is-link is-light">{{
                new Date(pj.recDate).toLocaleDateString()
              }}</b-tag>
            </b-taglist>
            <h2 class="title is-4 mt-5">
              <i class="xi-lightbulb"></i> {{ pj.pjTitle }}
            </h2>
            <h2 class="subtitle">
              {{ pj.pjDescription }}
            </h2>
            <br />
            <h2 class="subtitle">
              · 모집 직무 및 인원 ： {{ pj.needPosi }} ／ {{ pj.needNum }}명
              <br />
              · 모집 기간 ： {{ pj.recDuration }} <br />
              · 사용 툴 ： {{ pj.pjTools }} <br />
              · 사용 언어 ： {{ pj.pjLang }} <br />
              · 사용 DBMS ： {{ pj.pjDbms }} <br />
              · 지역 ： {{ pj.location }} <br />
              · 우대사항 ： {{ pj.preference }} <br />
            </h2>

            <div
              v-if="
                loginUser.userId != pj.userId.userId &&
                  loginUser.userId != appliedUser
              "
            >
              <b-button
                label="지원하기"
                type="is-primary"
                size="is-medium"
                @click="isCardModalActive = true"
              />
            </div>
            <section>
              <b-modal v-model="isCardModalActive" :width="640" scroll="keep">
                <div class="modal-card" style="width: auto">
                  <header class="modal-card-head">
                    <p class="modal-card-title">프로젝트 지원하기</p>
                    <button
                      type="button"
                      class="delete"
                      @click="isCardModalActive = false"
                    />
                  </header>
                  <section class="modal-card-body">
                    <b-field label="나를 어필할 한마디를 적어주세요!">
                      <b-input
                        v-model="words"
                        type="text"
                        placeholder="나의 한마디"
                        required
                      >
                      </b-input>
                    </b-field>

                    <b-field label="지원 직무">
                      <b-select v-model="aplPosi" expanded>
                        <option
                          v-for="(value, index) in posiArray"
                          :key="index"
                          v-bind:value="value"
                          >{{ value }}</option
                        >
                      </b-select>
                    </b-field>
                  </section>
                  <footer class="modal-card-foot">
                    <b-button
                      label="Close"
                      @click="isCardModalActive = false"
                    />
                    <b-button
                      label="지원하기"
                      v-on:click="submitApp()"
                      type="is-primary"
                    />
                  </footer>
                </div>
              </b-modal>
            </section>

            <b-message
              title="NOTIFICATION"
              type="is-success"
              has-icon
              aria-close-label="Close message"
              v-if="
                loginUser.userId != pj.userId.userId &&
                  loginUser.userId == appliedUser
              "
            >
              이미 지원하신 프로젝트 입니다.

              <br />
              <router-link
                class="button"
                :to="{
                  name: 'mypage'
                }"
                >내 지원서 확인하기</router-link
              >
            </b-message>
            <b-message v-else></b-message>
          </b-tab-item>
        </b-tabs>
      </section>
    </div>
  </div>
</template>
<script>
import http from "../../http-common";
import { mapState } from "vuex";
import router from "../../router";

export default {
  name: "ProjectDetails",
  props: ["pjSeq"],
  data() {
    const all = [];
    const allPjs = [];
    const posiArray = [];
    const apps = [];
    return {
      loginUser: {
        userImage: "default.png",
        userImage2: JSON.parse(sessionStorage.getItem("user")).userImage,
        userId: JSON.parse(sessionStorage.getItem("user")).userId
      },
      all,
      allPjs,
      apps,
      appliedUser: "",
      posiArray,
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
          "/recruit/gettotalpj/" +
            this.pjSeq +
            "?userId=" +
            this.loginUser.userId
        )
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
          console.log(this.allPjs);

          var array2 = [];
          var posi = {};

          array.forEach(function(element) {
            posi = [element.needPosi.split(",")].reduce(function(r, o) {
              Object.keys(o).forEach(function(k) {
                r[k] = o[k];
              });
              return r;
            }, {});
            array2.push(posi);
          });
          this.posiArray = posi;
        })
        .catch(e => {
          console.log(e);
          this.errors.push(e);
        });
    },
    submitApp() {
      let formData = new FormData();

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
          router.push({ name: "AppSuccess" });
          this.success();

})
        .catch(function() {
        });
    },
    retrieveApps() {
      http
        .get("/app/getpjapp/" + this.pjSeq + "?userId=" + this.loginUser.userId)
        .then(response => {
          this.apps = response.data;
          var apps2 = "";
          this.apps.forEach(function(entry) {
            apps2 = entry;
          });
          this.appliedUser = apps2.userId;
        })
        .catch(e => {
          console.log(e);
          this.errors.push(e);
        });
    },
    success() {
      this.$buefy.notification.open({
        message: "성공적으로 완료되었습니다.",
        type: "is-success",
        position: "is-bottom-right"
      });
    },
    danger() {
      this.$buefy.notification.open({
        message: `다시 한번 확인해주세요.`,
        type: "is-danger",
        position: "is-bottom-right"
      });
    }
  },
  mounted() {
    this.retrieveRecAndPj();
    this.retrieveApps();
  }
};
</script>
