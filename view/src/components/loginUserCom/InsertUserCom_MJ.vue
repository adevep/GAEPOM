<template>
  <ValidationObserver ref="observer" v-slot="{ handleSubmit }" autocomplete="off">
    <div class="container is-max-desktop">
      <div class="notification is-accent">
        <h1><strong>가입 정보 입력</strong></h1>
        <BInputWithValidation
          rules="required|id-minmax:6,15|id-char"
          label="ID"
          placeholder="아이디"
          v-model="userid"
        />

        <BInputWithValidation
          rules="required|pw-minmax:6,15|pw-char"
          label="Password"
          type="password"
          placeholder="비밀번호"
          vid="password"
          v-model="password"
        />

        <BInputWithValidation
          rules="required|name-char|name-minmax:2,4"
          label="Name"
          placeholder="이름"
          v-model="name"
        />

        <BInputWithValidation
          rules="required|numeric|max:2"
          label="Age"
          placeholder="나이"
          v-model="age"
        />

        <BInputWithValidation
          rules="required|email"
          type="email"
          label="Email"
          placeholder="이메일"
          v-model="email"
        />

        <BInputWithValidation
          rules="required|phonenum-char"
          label="PhoneNumber"
          placeholder="전화번호"
          v-model="phonenum"
        />

        <BInputWithValidation
          rules="required|max:2"
          label="Address"
          placeholder="예시) 서울"
          v-model="address"
        />
        
        <BSelectWithValidation
          rules="required"
          label="Position"
          placeholder="희망직무"
          v-model="position"
        >
          <option value="개발자">개발자</option>
          <option value="기획자">기획자</option>
          <option value="디자이너">디자이너</option>
        </BSelectWithValidation>

        <BCheckboxesWithValidation rules="required" label="Stack">
          <b-checkbox v-model="stack" native-value="Java">Java</b-checkbox>
          <b-checkbox v-model="stack" native-value="JavaScript">JavaScript</b-checkbox>
          <b-checkbox v-model="stack" native-value="Vue.js">Vue.js</b-checkbox>
          <b-checkbox v-model="stack" native-value="PPT">PPT</b-checkbox>
          <b-checkbox v-model="stack" native-value="Excel">Excel</b-checkbox>
      </BCheckboxesWithValidation>

        <b-field label="User-Image" align="left">
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
          <button class="button is-success" v-on:click="handleSubmit(submit)">
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
 * ? BSelectWithValidation | Buefy의 <b-select>와 동일 + 유효성 검증 로직 적용
 * ? BInputWithValidation | Buefy의 <b-input>과 동일 + 유효성 검증 로직 적용
 * ? BCheckboxesWithValidation | Buefy의 <b-checkbox>와 동일 + 유효성 검증 로직 적용
 */
import { ValidationObserver } from "vee-validate";
import BSelectWithValidation from "../inputs/BSelectWithValidation";
import BInputWithValidation from "../inputs/BInputWithValidation";
import BCheckboxesWithValidation from "../inputs/BCheckboxesWithValidation";

import router from "../../router";
import axios from "axios";

export default {
  name: "BuefyForm",
  components: {
    ValidationObserver,
    BSelectWithValidation,
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
      stack: [],
      position: "",
      file: ""
    };
  },
  methods: {
    submit() {
      let formData = new FormData();

      formData.append("userId", this.userid);
      formData.append("password", this.password);
      formData.append("name", this.name);
      formData.append("age", this.age);
      formData.append("email", this.email);
      formData.append("phoneNum", this.phonenum);
      formData.append("address", this.address);
      formData.append("stack", this.stack);
      formData.append("position", this.position);
      formData.append("file", this.file);

      axios
        .post("http://localhost:80/insertuser", formData, {
          headers: {
            "Content-Type": "multipart/form-data"
          }
        })
        .then(() => {
          alert("개폼 회원가입 완료");
        })
        .catch(() => {
          alert("개폼 회원가입 실패");
        });
      router.push({ name: "Home" });
    },
    resetForm() {
      this.userid = "";
      this.password = "";
      this.name = "";
      this.age = "";
      this.email = "";
      this.phonenum = "";
      this.address = "";
      this.stack = [];
      this.position = "";
      this.file = "";
      requestAnimationFrame(() => {
        this.$refs.observer.reset();
      });
    },
    handleFileUpload() {
      this.file = this.$refs.file.files[0];
    }
  }
};
</script>
