<template>
  <div class="container is-max-desktop" style="width: 550px; height: 50px;">
    <div class="notification is-accent">
      <h1>
        <strong
          >{{ userid }}님 <br />
          회원 정보 수정</strong
        >
      </h1>
      <br />
      <b-field label="Password" type="" message="" align="left">
        <b-input
          v-model="password"
          type="password"
          placeholder="비밀번호"
          maxlength="30"
          size="is-medium"
          rounded
          password-reveal
        >
        </b-input>
      </b-field>
      <span class="icon is-right has-text-primary is-clickable"
        ><i class="mdi mdi-eye mdi-24px"></i
      ></span>
      <br />
      <b-field label="Name" type="" message="" align="left">
        <b-input
          v-model="name"
          placeholder="이름"
          maxlength="30"
          size="is-medium"
          rounded
        ></b-input>
      </b-field>
      <br />
      <b-field label="Age" type="" message="" align="left">
        <b-input
          v-model="age"
          placeholder="나이"
          maxlength="30"
          size="is-medium"
          rounded
        ></b-input>
      </b-field>
      <br />
      <b-field label="E-mail" type="" message="" align="left">
        <b-input
          v-model="email"
          placeholder="이메일"
          maxlength="30"
          size="is-medium"
          rounded
        ></b-input>
      </b-field>
      <br />
      <b-field
        label="Phone-Number"
        type=""
        message="예시) 010-XXXX-XXXX"
        align="left"
      >
        <b-input
          v-model="phonenum"
          placeholder="전화번호"
          maxlength="30"
          size="is-medium"
          rounded
        ></b-input>
      </b-field>
      <br />
      <b-field label="Address" type="" message="" align="left">
        <b-input
          v-model="address"
          placeholder="주소"
          maxlength="30"
          size="is-medium"
          rounded
        ></b-input>
      </b-field>
      <br />
      <b-field label="Stack" type="" message="" align="left">
        <b-input
          v-model="stack"
          placeholder="기술"
          maxlength="30"
          size="is-medium"
          rounded
        ></b-input>
      </b-field>
      <br />
      <b-field label="Position" align="left">
        <b-select v-model="position" expanded>
          <option value="개발자">개발자</option>
          <option value="기획자">기획자</option>
          <option value="디자이너">디자이너</option>
        </b-select>
      </b-field>
      <br />
      <b-field label="Current UserImage" align="left">
        <img :src="imgURL + userimage" alt="" width="500" />
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
      <b-button
        type="is-primary"
        outlined
        v-on:click="submitUser()"
        position="is-centered"
        size="is-large"
        >정보수정</b-button
      >
    </div>
  </div>
</template>
<script>
import axios from "axios";
import router from "../../router";
import { mapState } from "vuex";

export default {
  data() {
    return {
      userid: "",
      password: "",
      name: "",
      age: "",
      email: "",
      phonenum: "",
      address: "",
      stack: "",
      position: "",
      userimage: "",
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
          this.stack = response.data.stack;
          this.position = response.data.position;
          this.userimage = response.data.userImage;
        })
        .catch((e) => {
          console.log(e);
          this.errors.push(e);
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
      formData.append("stack", this.stack);
      formData.append("position", this.position);
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
