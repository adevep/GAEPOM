<template>
  <div class="ProjectDetails">
    <div class="container is-max-desktop pt-5">
      <section class="mt-6 mb-5">
        <article class="media">
          <figure class="media-left">
            <p class="image is-64x64">
              <img class="is-rounded" :src="imgURL + anotherUser.userImage" />
            </p>
          </figure>
          <div class="media-content">
            <div class="content" align="left">
              <p>
                <strong>{{ anotherUser.name }}</strong> <br />
                <small>{{ anotherUser.userId }}</small>
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
      <section>
        <b-tabs type="is-boxed">
          <b-tab-item label="유저 정보">
            <br />
            <h5 class="subtitle is-5" align="left">
              ▶ 이메일 : {{ anotherUser.email }}
            </h5>
            <br />
            <h5 class="subtitle is-5" align="left">
              ▶ 전화번호 : {{ anotherUser.phoneNum }}
            </h5>
            <br />
            <h5 class="subtitle is-5" align="left">
              ▶ 포지션 : {{ anotherUser.position }}
            </h5>
            <br />
            <h5 class="subtitle is-5" align="left">
              ▶ 기술 : {{ anotherUser.stack }}
            </h5>
            <br />
            <h5 class="subtitle is-5" align="left">
              ▶ 주소 : {{ anotherUser.address }}
            </h5>
            <br />

            <section>
              <b-modal v-model="userId" :width="640" scroll="keep">
                <div class="modal-card" style="width: auto">
                  <header class="modal-card-head">
                    <p class="modal-card-title">프로젝트 지원하기</p>
                    <button type="button" class="delete" />
                    @click="$emit('close')"
                  </header>
                  <section class="modal-card-body">
                    <b-field label="나를 어필할 한마디를 적어주세요!">
                      <b-input
                        v-model="userId"
                        type="text"
                        placeholder="나의 한마디"
                        required
                      >
                      </b-input>
                    </b-field>

                    <b-field label="지원 직무">
                      <b-select v-model="userId" expanded>
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
                    <b-button label="Close" />
                    @click="isCardModalActive = false"
                    <b-button label="지원하기" type="is-primary" />
                    v-on:click="submitApp()"
                  </footer>
                </div>
              </b-modal>
            </section>
          </b-tab-item>
          <b-tab-item label="포트폴리오">
            <div id="portfolios" class="container">
              <section>
                <b-table
                  :data="portfolio"
                  ref="table"
                  paginated
                  per-page="3"
                  aria-next-label="Next page"
                  aria-previous-label="Previous page"
                  aria-page-label="Page"
                  aria-current-label="Current page"
                >
                  <b-table-column
                    field="pfSeq"
                    label="번호"
                    width="80"
                    numeric
                    v-slot="props"
                  >
                    {{ props.row.pfSeq }}
                  </b-table-column>

                  <b-table-column
                    field="pfSubtitle"
                    label="제목"
                    sortable
                    v-slot="props"
                    width="80"
                  >
                    {{ props.row.pfSubtitle }}
                  </b-table-column>

                  <b-table-column
                    field="pfDuration"
                    label="기간"
                    sortable
                    centered
                    v-slot="props"
                    width="80"
                  >
                    {{ props.row.pfDuration }}
                  </b-table-column>

                  <b-table-column
                    field="participation"
                    label="포지션"
                    sortable
                    centered
                    v-slot="props"
                  >
                    {{ props.row.pfPosition }}
                  </b-table-column>

                  <b-table-column
                    field="pfDescription"
                    label="설명"
                    sortable
                    centered
                    v-slot="props"
                    width="80"
                  >
                    {{ props.row.pfDescription }}
                  </b-table-column>

                  <b-table-column
                    field="participation"
                    label="참여도"
                    sortable
                    centered
                    v-slot="props"
                    width="120"
                  >
                    {{ props.row.participation }}%
                  </b-table-column>

                    <b-table-column
                    field="pfLink"
                    label="Link"
                    sortable
                    centered
                    v-slot="props"
                  >
                    {{ props.row.pfLink }}
                  </b-table-column>

                  <b-table-column
                    field="pfCategory"
                    label="카테고리"
                    sortable
                    centered
                    width="150"
                    v-slot="props"
                  >
                    {{ props.row.pfCategory }}
                  </b-table-column>

                  <b-table-column
                    field="pfTools"
                    label="Tool"
                    sortable
                    centered
                    v-slot="props"
                  >
                    {{ props.row.pfTools }}
                  </b-table-column>

                  <b-table-column
                    field="pfLang"
                    label="언어"
                    sortable
                    centered
                    v-slot="props"
                  >
                    {{ props.row.pfLang }}
                  </b-table-column>

                  <b-table-column
                    field="pfDbms"
                    label="DB"
                    sortable
                    centered
                    v-slot="props"
                  >
                    {{ props.row.pfDbms }}
                  </b-table-column>

                </b-table>
              </section>
            </div>
          </b-tab-item>
          <b-tab-item label="프로젝트 관리">
            <b-taglist>
              <b-tag type="is-primary">1</b-tag>
              <b-tag type="is-primary is-light">2</b-tag>
              <b-tag type="is-link">3</b-tag>
              <b-tag type="is-link is-light">4</b-tag>
              <b-tag type="is-link is-light">5</b-tag>
            </b-taglist>
          </b-tab-item>
          <b-tab-item label="댓글">
            <b-taglist>
              <b-tag type="is-primary">1</b-tag>
              <b-tag type="is-primary is-light">2</b-tag>
              <b-tag type="is-link">3</b-tag>
              <b-tag type="is-link is-light">4</b-tag>
              <b-tag type="is-link is-light">5</b-tag>
            </b-taglist>
          </b-tab-item>
        </b-tabs>
      </section>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";
import router from "../../router";
export default {
  data: () => ({
    // 페이지 생성 오류때문에 빈 변수 가진 객체 사용해줌
    anotherUser: {
      userImage: "default.png",
      userId: "",
      name: "",
      email: "",
      phoneNum: "",
      position: "",
      stack: "",
    },
    portfolio: [],
    currentUserId: JSON.parse(sessionStorage.getItem("user")).userId,
    currentUserName: JSON.parse(sessionStorage.getItem("user")).name,
    currentUserPosition: JSON.parse(sessionStorage.getItem("user")).position,
    currentUserAddress: JSON.parse(sessionStorage.getItem("user")).address,

    pfSubtitle: "",
    pfDuration: "",
    pfDescription: "",
    participation: "",
    pfLang: "",
    pfTools: "",
    pfDbms: "",
    pfLink: "",
    pfCategory: "",
  }),
  computed: {
    ...mapState(["imgURL"]),
  },
  methods: {
    retrievePortfolios() {
      axios
        .get("/portfolios?userid=" + this.$route.params.pickedid)
        .then((response) => {
          this.portfolio = response.data;
          // console.log(this.portfolio[0].pfLang)
          // this.portfolio[0].pfLang = this.portfolio[0].pfLang.split(",")
          // console.log(this.portfolio[0].pfLang)
        })
        .catch(() => {
          alert("조회 실패");
        });
    },
    anotherUserInfoCall() {
      axios
        .get("http://localhost:80/user/get?userid=" + this.$route.params.pickedid)
        .then((response) => {
          this.anotherUser = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    getPortfolio(user) {
      this.$store.state.anotherUser = user;
      router.push({ name: "getPortfolio" });
    },
  },
  mounted() {
    this.anotherUserInfoCall();
    this.retrievePortfolios();
  },
};
</script>
<style scoped></style>
