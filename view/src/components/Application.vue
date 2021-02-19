<template>
  <div id="app" class="container">
    <section>
      <!-- <v-for"app in apps" :key="app.index">
         -->
      <b-table :data="isEmpty ? [] : apps" :hoverable="isHoverable">
        <b-table-column
          field="aplSeq"
          label="글"
          width="20"
          numeric
          centered
          v-slot="props"
        >
          {{ props.row.aplSeq }}
        </b-table-column>

        <b-table-column field="userId" label="아이디" v-slot="props" centered>
          {{ props.row.userId }}
        </b-table-column>

        <b-table-column
          field="aplPosi"
          label="지원 직무"
          v-slot="props"
          centered
        >
          {{ props.row.aplPosi }}
        </b-table-column>
        <b-table-column field="words" label="각오" v-slot="props" centered>
          <b-field>
            {{ props.row.words }}
            <a @click="isCardModalActive=true" class="tag is-warn pr-2 prl-2">
              변경
            </a>
          </b-field>
          <!-- <b-field v-if="editWords">
            <b-input  v-model="words"></b-input>
            <a
            v-on:click="updateApp(props.row.aplSeq, props.row)"
            class="tag is-warn pr-2 prl-2"
          >
          </b-field> -->
        </b-table-column>
        <!-- <b-table-column v-if="editWords" field="words" label="각오" v-slot="props" centered>
          <b-input v-model="words" maxlength="20"></b-input>
          <a
            v-on:click="updateApp(props.row.aplSeq, props.row)"
            class="tag is-warn pr-2 prl-2"
          >
            변경
          </a>
        </b-table-column> -->
          <b-button
            label="지원하기"
            type="is-primary"
            size="is-medium"
            @click="isCardModalActive = true"
          />
        <section>
          <b-modal v-model="isCardModalActive" :width="640" scroll="keep">
            <div class="modal-card" style="width: auto">
              <header class="modal-card-head">
                <p class="modal-card-title">프로젝트 지원하기</p>
                <button type="button" class="delete" @click="$emit('close')" />
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
                <b-button label="Close" @click="isCardModalActive = false" />
                <b-button
                  label="지원하기"
                  v-on:click="submitApp()"
                  type="is-primary"
                />
              </footer>
            </div>
          </b-modal>
        </section>

        <b-table-column
          field="selected"
          label="진행사항"
          v-slot="props"
          centered
        >
          <span v-if="props.row.selected == 0" class="tag is-warn">
            지원중
          </span>
          <span v-else-if="props.row.selected == 1" class="tag is-success">
            합격
          </span>
          <span v-else-if="props.row.selected == 2" class="tag is-danger">
            거절
          </span>
        </b-table-column>
        <b-table-column field="pjSeq" label="프로젝트" v-slot="props" centered>
          {{ props.row.pjSeq.pjTitle }}
        </b-table-column>
        <b-table-column
          field="aplDate"
          label="등록날짜"
          centered
          v-slot="props"
        >
          <span class="tag is-success">
            {{ new Date(props.row.aplDate).toLocaleDateString() }}
          </span>
        </b-table-column>
        <b-table-column field="aplSeq" label="삭제" centered v-slot="props">
          <a class="tag is-warn" @click="deleteApp(props.row.aplSeq)">
            삭제
          </a>
        </b-table-column>
      </b-table>
    </section>
  </div>
</template>

<script>
import http from "../http-common";

export default {
  name: "Application",
  data() {
    const apps = [];
    return {
      loginUser: JSON.parse(sessionStorage.getItem("user")).userId,
      apps,
      isHoverable: true,
      words: "",
      editWords: false
    };
  },
  methods: {
    retrieveApps() {
      http
        .get("/app/getapp/" + this.loginUser)
        .then(response => {
          this.apps = response.data;
          var apps2 = "";
          this.apps.forEach(function(entry) {
            apps2 = entry;
          });
          this.words = apps2.words;
          console.log(response.data);
          console.log(apps2);
        })
        .catch(e => {
          console.log(e);
          this.errors.push(e);
        });
    },
    updateApp(id, app) {
      {
        app.words = this.words;
      }
      http
        .put(`/app/update/${id}?userId=` + this.loginUser, app)
        .then(response => {
          this.retrieveApps();
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
          this.errors.push(e);
        });
    },
    deleteApp(id) {
      http
        .delete(`/app/delete/${id}?userId=` + this.loginUser)
        .then(response => {
          console.log(response.data);
          this.retrieveApps();
        })
        .catch(e => {
          console.log(e);
          this.errors.push(e);
        });
    }
  },
  mounted() {
    this.retrieveApps();
  }
};
</script>
