<template>
  <ValidationObserver
    ref="observer"
    v-slot="{ handleSubmit }"
    autocomplete="off"
  >
    <div class="container is-max-desktop">
      <div class="notification is-accent">
        <center>
          <h1>
            <strong
              >{{ userid }} 님 <br />
              회원 정보 수정</strong
            >
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
          rounded
        />

        <BInputWithValidation
          rules="required|name-char|name-minmax:2,4"
          label="Name"
          placeholder="이름"
          v-model="name"
          size="is-medium"
          rounded
        />

        <BInputWithValidation
          rules="required|numeric|max:2"
          label="Age"
          placeholder="나이"
          v-model="age"
          size="is-medium"
          rounded
        />

        <BInputWithValidation
          rules="required|email|max:30"
          type="email"
          label="Email"
          placeholder="이메일"
          v-model="email"
          size="is-medium"
          rounded
        />

        <BInputWithValidation
          rules="required|phonenum-char|max:13"
          label="PhoneNumber"
          placeholder="전화번호"
          v-model="phonenum"
          size="is-medium"
          rounded
        />

        <BInputWithValidation
          rules="required|max:10"
          label="Address"
          placeholder="예시) 경기도 고양시"
          v-model="address"
          size="is-medium"
          rounded
        />

        <BSeletWithValidation
          rules="required"
          label="Position"
          placeholder="희망직무"
          v-model="position"
          size="is-medium"
          rounded
        >
          <option value="개발자">개발자</option>
          <option value="기획자">기획자</option>
          <option value="디자이너">디자이너</option>
        </BSeletWithValidation>

        <BCheckboxesWithValidation
          rules="required"
          label="Stack"
          v-if="position === '개발자'"
        >
          <b-checkbox v-model="stacklist" native-value="Java">Java</b-checkbox>
          <b-checkbox v-model="stacklist" native-value="Python"
            >Python</b-checkbox
          >
          <b-checkbox v-model="stacklist" native-value="JavaScript"
            >JavaScript</b-checkbox
          >
          <b-checkbox v-model="stacklist" native-value="C">C</b-checkbox>
          <b-checkbox v-model="stacklist" native-value="R">R</b-checkbox>
          <b-checkbox v-model="stacklist" native-value="SQL">SQL</b-checkbox>
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
          <b-checkbox v-model="stacklist" native-value="OVEN">OVEN</b-checkbox>
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
            class="button is-success"
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
</template>

<script>
/**
 * *  vee-validation 사용
 * ? BSeletWithValidation | Buefy의 <b-select>와 동일 + 유효성 검증 로직 구현
 * ? BInputWithValidation | Buefy의 <b-input>과 동일 + 유효성 검증 로직 구현
 * ? BCheckboxesWithValidation | Buefy의 <b-checkbox>와 동일 + 유효성 검증 로직 구현
 */
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
    BCheckboxesWithValidation,
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
      // 이미지 파일 받아주는 변수
      file: null,
    };
  },
  methods: {
    userUpdateInfoCall() {
      axios
        .get(
          "/getuser?userid=" + JSON.parse(sessionStorage.getItem("user")).userId
        )
        .then((response) => {
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
        .catch((e) => {
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
        .put("http://localhost:80/updateuser", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((response) => {
          sessionStorage.setItem("user", JSON.stringify(response.data));
          alert("유저정보 수정 성공");
          router.push({ name: "mypage" });
        })
        .catch(() => {
          alert("유저정보 수정 실패");
        });
    },
    resetForm() {
      this.userid = "";
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
  },
  computed: {
    ...mapState(["imgURL"]),
  },
  mounted() {
    this.userUpdateInfoCall();
  },
};
</script>
