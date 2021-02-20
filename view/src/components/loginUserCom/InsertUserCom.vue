<template>
  <ValidationObserver
    ref="observer"
    v-slot="{ handleSubmit }"
    autocomplete="off"
  >
    <div class="container is-max-desktop">
      <div class="notification is-accent" align="center">
        <h1><strong>가입 정보 입력</strong></h1>
        <br />
        <BInputWithValidation
          rules="required|id-char|id-minmax:6,15"
          label="아이디"
          v-model="userid"
          size="is-medium"
          align="left"
          rounded
        />

        <BInputWithValidation
          rules="required|pw-char|pw-minmax:6,15"
          label="비밀번호"
          autocomplete="new-password"
          type="password"
          v-model="password"
          size="is-medium"
          align="left"
          rounded
        />

        <BInputWithValidation
          rules="required|name-char|name-minmax:2,4"
          label="이름"
          placeholder="예시) 홍길동"
          v-model="name"
          size="is-medium"
          align="left"
          rounded
        />

        <BInputWithValidation
          rules="required|numeric|max:2"
          label="나이"
          v-model="age"
          size="is-medium"
          align="left"
          rounded
        />

        <BInputWithValidation
          rules="required|email|max:30"
          type="email"
          label="이메일"
          v-model="email"
          size="is-medium"
          align="left"
          rounded
        />

        <BInputWithValidation
          rules="required|phonenum-char|max:13"
          label="전화번호"
          placeholder="예시) 01X-XXXX-XXXX"
          v-model="phonenum"
          size="is-medium"
          align="left"
          rounded
        />

        <BInputWithValidation
          rules="required|max:25"
          label="주소"
          placeholder="예시) 경기도 고양시 일산서구(시군구까지)"
          v-model="address"
          size="is-medium"
          align="left"
          rounded
        />

        <BSeletWithValidation
          rules="required"
          label="희망직무"
          v-model="position"
          size="is-medium"
          align="left"
          rounded
        >
          <option value="개발자">개발자</option>
          <option value="기획자">기획자</option>
          <option value="디자이너">디자이너</option>
        </BSeletWithValidation>
        <br />
        <BCheckboxesWithValidation
          rules="required"
          v-if="position === '개발자'"
        >
          <b-field label="기술" align="left">
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
            <b-checkbox v-model="stacklist" native-value="SQL">SQL</b-checkbox>
          </b-field>
        </BCheckboxesWithValidation>
        <BCheckboxesWithValidation
          rules="required"
          label="Stack"
          v-else-if="position === '기획자'"
        >
          <b-field label="기술" align="left">
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
          </b-field>
        </BCheckboxesWithValidation>
        <BCheckboxesWithValidation
          rules="required"
          label="Stack"
          v-else-if="position === '디자이너'"
        >
          <b-field label="기술" align="left">
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
          </b-field>
        </BCheckboxesWithValidation>
        <b-field label="프로필 사진" align="left">
          <input
            type="file"
            id="file"
            ref="file"
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
            <span>회원가입</span>
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

import router from "../../router";
import axios from "axios";

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
      stacklist: [],
      file: "",
    };
  },
  methods: {
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
        .post("http://localhost:80/insertuser", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then(() => {
          alert("개폼 회원가입 완료");
        })
        .catch(() => {
          alert("개폼 회원가입 실패");
        });
      router.push({ name: "insertusercheck" });
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
};
</script>