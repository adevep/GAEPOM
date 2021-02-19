<template>
  <div class="container is-max-desktop">
    <div class="notification is-accent">
      <h1><strong>가입 정보 입력</strong></h1>
      <b-field label="ID" type="" message="" align="left">
        <b-input
          v-model="userid"
          placeholder="아이디"
          maxlength="30"
          size="is-medium"
          rounded
        ></b-input>
      </b-field>

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

      <b-field label="Name" type="" message="" align="left">
        <b-input
          v-model="name"
          placeholder="이름"
          maxlength="30"
          size="is-medium"
          rounded
        ></b-input>
      </b-field>

      <b-field label="Age" type="" message="" align="left">
        <b-input
          v-model="age"
          placeholder="나이"
          maxlength="30"
          size="is-medium"
          rounded
        ></b-input>
      </b-field>

      <b-field label="E-mail" type="" message="" align="left">
        <b-input
          v-model="email"
          placeholder="이메일"
          maxlength="30"
          size="is-medium"
          rounded
        ></b-input>
      </b-field>

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

      <b-field label="Address" type="" message="" align="left">
        <b-input
          v-model="address"
          placeholder="주소"
          maxlength="30"
          size="is-medium"
          rounded
        ></b-input>
      </b-field>

      <b-field label="Position" align="left">
        <b-select v-model="position" expanded>
          <option value="개발자">개발자</option>
          <option value="기획자">기획자</option>
          <option value="디자이너">디자이너</option>
        </b-select>
      </b-field>

       <b-field label="Stack" align="left" v-if="position === '개발자'">
        <b-checkbox v-model="stacklist" native-value="Java">
          Java
        </b-checkbox>
        <b-checkbox v-model="stacklist" native-value="Python">
          Python
        </b-checkbox>
        <b-checkbox v-model="stacklist" native-value="Javascript">
          Javascript
        </b-checkbox>
        <b-checkbox v-model="stacklist" native-value="C">
          C
        </b-checkbox>
        <b-checkbox v-model="stacklist" native-value="R">
          R
        </b-checkbox>
        <b-checkbox v-model="stacklist" native-value="SQL">
          SQL
        </b-checkbox>
      </b-field>

      <b-field label="Stack" align="left" v-else-if="position === '기획자'">
        <b-checkbox v-model="stacklist" native-value="Gloo maps">
          Gloo maps
        </b-checkbox>
        <b-checkbox v-model="stacklist" native-value="Plectica">
          Plectica
        </b-checkbox>
        <b-checkbox v-model="stacklist" native-value="Trello">
          Trello
        </b-checkbox>
        <b-checkbox v-model="stacklist" native-value="Axure">
          Axure
        </b-checkbox>
        <b-checkbox v-model="stacklist" native-value="OVEN">
          OVEN
        </b-checkbox>
        <b-checkbox v-model="stacklist" native-value="Power Mockup">
          Power Mockup
        </b-checkbox>
      </b-field>

      <b-field label="Stack" align="left" v-else-if="position === '디자이너'">
        <b-checkbox v-model="stacklist" native-value="Sketch">
          Sketch
        </b-checkbox>
        <b-checkbox v-model="stacklist" native-value="Figma">
          Figma
        </b-checkbox>
        <b-checkbox v-model="stacklist" native-value="Photoshop">
          Photoshop
        </b-checkbox>
        <b-checkbox v-model="stacklist" native-value="Adobe XD">
          Adobe XD
        </b-checkbox>
        <b-checkbox v-model="stacklist" native-value="Framer X">
          Framer X
        </b-checkbox>
        <b-checkbox v-model="stacklist" native-value="Illustrator">
          Illustrator
        </b-checkbox>
      </b-field>

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
      <b-button
        type="is-primary"
        outlined
        v-on:click="submitUser()"
        position="is-centered"
        size="is-large"
        >회원가입</b-button
      >
    </div>
  </div>
</template>
<script>
import router from "../../router";
import axios from "axios";
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
      position: "",
      stacklist: [],
      file: ""
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
            "Content-Type": "multipart/form-data"
          }
        })
        .then(() => {
          alert("개폼 회원가입 완료");
        })
        .catch(() => {
          alert("개폼 회원가입 실패");
        });
      router.push({ name: "insertusercheck" });
    },
    handleFileUpload() {
      this.file = this.$refs.file.files[0];
    }
  }
};
</script>
