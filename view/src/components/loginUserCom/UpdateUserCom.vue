<template>
  <div id="app" class="container is-max-desktop pt-5">
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link
      href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
      rel="stylesheet"
    />
    <section>
      <div class="hero-body">
        <div class="container has-text-centered">
          <h1 class="title ">
            회원정보 수정
          </h1>
          <h2 class="subtitle centered">
            정보는 소중해요.
          </h2>
          <nav
            class="breadcrumb has-dot-separator is-centered"
            aria-label="breadcrumbs"
          >
            <ul>
              <li><a href="/">홈페이지</a></li>
              <li class="is-active">
                <a href="#" aria-current="page">회원정보</a>
              </li>
            </ul>
          </nav>
        </div>
      </div>
    </section>
    <section>
      <ValidationObserver
        ref="observer"
        v-slot="{ handleSubmit }"
        autocomplete="off"
      >
        <div class="container is-max-desktop">
          <div class="notification is-accent">
            <center>
              <h1>
                <strong>{{ userid }} 님 <br /> </strong>
              </h1>
            </center>
            <br />
            <BInputWithValidation
              rules="required|pw-char|pw-minmax:6,15"
              label="Password"
              autocomplete="new-password"
              type="password"
              placeholder="비밀번호"
              v-model="password"
              size="is-medium"
            />

            <BInputWithValidation
              rules="required|name-char|name-minmax:2,4"
              label="Name"
              placeholder="이름"
              v-model="name"
              size="is-medium"
            />

            <BInputWithValidation
              rules="required|numeric|max:2"
              label="Age"
              placeholder="나이"
              v-model="age"
              size="is-medium"
            />

            <BInputWithValidation
              rules="required|email|max:30"
              type="email"
              label="Email"
              placeholder="이메일"
              v-model="email"
              size="is-medium"
            />

            <BInputWithValidation
              rules="required|phonenum-char|max:13"
              label="PhoneNumber"
              placeholder="전화번호"
              v-model="phonenum"
              size="is-medium"
            />

            <BInputWithValidation
              rules="required|max:25"
              label="Address"
              placeholder="예시) 경기도 고양시 일산서구(시군구까지)"
              v-model="address"
              size="is-medium"
            />

            <BSeletWithValidation
              rules="required"
              label="Position"
              placeholder="희망직무"
              v-model="position"
              size="is-medium"
            >
              <option value="개발자">개발자</option>
              <option value="기획자">기획자</option>
              <option value="디자이너">디자이너</option>
            </BSeletWithValidation>
            <br />
            <BCheckboxesWithValidation
              rules="required"
              label="Stack"
              v-if="position === '개발자'"
            >
              <b-checkbox v-model="stacklist" native-value="Java"
                >Java</b-checkbox
              >
              <b-checkbox v-model="stacklist" native-value="Python"
                >Python</b-checkbox
              >
              <b-checkbox v-model="stacklist" native-value="JavaScript"
                >JavaScript</b-checkbox
              >
              <b-checkbox v-model="stacklist" native-value="C">C</b-checkbox>
              <b-checkbox v-model="stacklist" native-value="R">R</b-checkbox>
              <b-checkbox v-model="stacklist" native-value="SQL"
                >SQL</b-checkbox
              >
            </BCheckboxesWithValidation>
            <BCheckboxesWithValidation
              rules="required"
              label="Stack"
              v-else-if="position === '기획자'"
            >
              <b-checkbox v-model="stacklist" native-value="Gloo maps"
                >Gloo maps</b-checkbox
              >
              <b-checkbox v-model="stacklist" native-value="Plectica"
                >Plectica</b-checkbox
              >
              <b-checkbox v-model="stacklist" native-value="Trello"
                >Trello</b-checkbox
              >
              <b-checkbox v-model="stacklist" native-value="Axure"
                >Axure</b-checkbox
              >
              <b-checkbox v-model="stacklist" native-value="OVEN"
                >OVEN</b-checkbox
              >
              <b-checkbox v-model="stacklist" native-value="Power Mockup"
                >Power Mockup</b-checkbox
              >
            </BCheckboxesWithValidation>
            <BCheckboxesWithValidation
              rules="required"
              label="Stack"
              v-else-if="position === '디자이너'"
            >
              <b-checkbox v-model="stacklist" native-value="Sketch"
                >Sketch</b-checkbox
              >
              <b-checkbox v-model="stacklist" native-value="Figma"
                >Figma</b-checkbox
              >
              <b-checkbox v-model="stacklist" native-value="Photoshop"
                >Photoshop</b-checkbox
              >
              <b-checkbox v-model="stacklist" native-value="Adobe XD"
                >Adobe XD</b-checkbox
              >
              <b-checkbox v-model="stacklist" native-value="Framer X"
                >Framer X</b-checkbox
              >
              <b-checkbox v-model="stacklist" native-value="Illustrator"
                >Illustrator</b-checkbox
              >
            </BCheckboxesWithValidation>
            <br />
            <b-field
              label="Current UserImage"
              align="left"
              message="<주의> 이미지 재등록 없을시 기본 이미지로 변경"
            >
              <img :src="imgURL + userimage" alt="" width="300" />
            </b-field>
            <br />
            <b-field align="left">
              <input
                type="file"
                id="file"
                ref="file"
                placeholder="Add profile picture"
                v-on:change="handleFileUpload()"
              />
            </b-field>
            <br />
            <br />
            <div class="buttons">
              <button
                class="button is-warning is-light"
                v-on:click="handleSubmit(submitUser)"
              >
                <span>정보수정</span>
              </button>
              <button class="button" @click="resetForm">
                <span>재입력</span>
              </button>
            </div>
          </div>
        </div>
      </ValidationObserver>
    </section>
  </div>
</template>

<script>
import { ValidationObserver } from "vee-validate";
import BSeletWithValidation from "../veeInputs/BSeletWithValidation";
import BInputWithValidation from "../veeInputs/BInputWithValidation";
import BCheckboxesWithValidation from "../veeInputs/BCheckboxesWithValidation";

import axios from "axios";
import router from "../../router";
import { mapState } from "vuex";

export default {
  components: {
    ValidationObserver,
    BSeletWithValidation,
    BInputWithValidation,
    BCheckboxesWithValidation
  },
  data() {
    return {
      userid: "",
      password: "",
      name: "",
      age: "",
      email: "",
      phonenum: "",
      address: "",
      position: "",
      userimage: "",
      stacklist: [],
      file: null
    };
  },
  methods: {
    userUpdateInfoCall() {
      axios
        .get(
          "http://localhost:80/user/get?userid=" +
            JSON.parse(sessionStorage.getItem("user")).userId
        )
        .then(response => {
          this.userid = response.data.userId;
          this.name = response.data.name;
          this.age = response.data.age;
          this.email = response.data.email;
          this.phonenum = response.data.phoneNum;
          this.address = response.data.address;
          this.stacklist = response.data.stack.split(",");
          this.position = response.data.position;
          this.userimage = response.data.userImage;
        })
        .catch(e => {
          console.log(e);
        });
    },
    submitUser() {
      let formData = new FormData();

      formData.append("userId", this.userid);
      formData.append("password", this.password);
      formData.append("name", this.name);
      formData.append("age", this.age);
      formData.append("email", this.email);
      formData.append("phoneNum", this.phonenum);
      formData.append("address", this.address);
      formData.append("position", this.position);
      formData.append("stacklist", this.stacklist);
      formData.append("file", this.file);

      axios
        .put("http://localhost:80/user/update", formData, {
          headers: {
            "Content-Type": "multipart/form-data"
          }
        })
        .then(response => {
          sessionStorage.setItem("user", JSON.stringify(response.data));
          this.success();
          router.push({ name: "mypage" });
        })
        .catch(() => {
          this.danger();
        });
    },
    resetForm() {
      this.password = "";
      this.name = "";
      this.age = "";
      this.email = "";
      this.phonenum = "";
      this.address = "";
      this.position = "";
      this.stacklist = [];
      this.file = "";
      requestAnimationFrame(() => {
        this.$refs.observer.reset();
      });
    },
    handleFileUpload() {
      this.file = this.$refs.file.files[0];
    },
    success() {
      this.$buefy.notification.open({
        message: "회원정보 수정이 완료되었습니다.",
        type: "is-success",
        position: "is-bottom-right"
      });
    },
    danger() {
      this.$buefy.notification.open({
        message: `회원정보 내용을 정확히 입력해주세요.`,
        type: "is-danger",
        position: "is-bottom-right"
      });
    }
  },
  computed: {
    ...mapState(["imgURL"])
  },
  mounted() {
    this.userUpdateInfoCall();
  }
};
</script>
