<template>
  <div class="ProjectDetails">
    <div class="container is-max-desktop pt-5">
      <section class="mt-6 mb-5">
        <article class="media">
          <figure class="media-left">
            <p class="image is-64x64">
              <img
                class="is-rounded"
                src="https://bulma.io/images/placeholders/128x128.png"
              />
            </p>
          </figure>
          <div class="media-content">
            <div class="content" v-for="pj in allPjs" :key="pj.index">
              <p>
                <strong>{{ pj.userId.name }}</strong>
                <small>@{{ pj.userId.userId }}</small>
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
            <h4 class="title is-4">{{ pj.pjTitle }}</h4>
            <h5 class="subtitle is-5">
              프로젝트는 "{{ pj.pjDescription }}" 입니다.
              <br />
              저희는 {{ pj.needNum }}명의 {{ pj.needPosi }}를 찾고 있습니다.
              <br />
              모집기간은 "{{ pj.recDuration }}"입니다.
              <br />

              <!-- {{ pj.recStatus }} -->
              사용툴은 {{ pj.pjTools }}와 같습니다.
              <br />
              사용언어는 {{ pj.pjLang }}와 같습니다.
              <br />
              사용DBMS는 {{ pj.pjDbms }}와 같습니다.
              <br />
              저희와 함께하시려면 {{ pj.location }}로 오세요.
            </h5>
            <br />
            <h4 class="title is-4">우대사항:</h4>
            <h5 class="subtitle is-5">{{ pj.preference }}</h5>
            <!-- <div v-if="loginuser!=pj.userId.userId">
            <b-button>지원하기 </b-button>
            </div> -->
            <section>
              <b-button
                label="지원하기"
                type="is-primary"
                size="is-medium"
                @click="isCardModalActive = true"
              />

              <b-modal v-model="isCardModalActive" :width="640" scroll="keep">
                <div class="modal-card" style="width: auto">
                  <header class="modal-card-head">
                    <p class="modal-card-title">프로젝트 지원하기</p>
                    <button
                      type="button"
                      class="delete"
                      @click="$emit('close')"
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
          </b-tab-item>
          <b-tab-item label="댓글"></b-tab-item>
        </b-tabs>
      </section>
    </div>
  </div>
</template>
<script>
import http from "../http-common";

export default {
  name: "ProjectDetails",
  props: ["pjSeq"],
  data() {
    const all = [];
    const allPjs = [];
    const posiArray = [];
    return {
      loginUser: JSON.parse(sessionStorage.getItem("user")).userId,
      all,
      allPjs,
      posiArray,
      aplPosi: "",
      words: "",
      isImageModalActive: false,
      isCardModalActive: false
    };
  },
  methods: {
    retrieveRecAndPj() {
      http
        .get("/recruit/gettotalpj/" + this.pjSeq + "?userId=" + this.loginUser)
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
          //console.log(this.allPjs.shift().pjSeq);
        })
        .catch(e => {
          console.log(e);
          this.errors.push(e);
        });
    },
    submitApp() {
      let formData = new FormData();

      //   formData.append("aplSeq", this.allPjs.shift().aplSeq);
      formData.append("userId", this.allPjs.shift().userId.userId);
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
        })
        .catch(function() {
          alert("FAILURE!!");
        });
    }
  },
  mounted() {
    this.retrieveRecAndPj();
  }
};
</script>
