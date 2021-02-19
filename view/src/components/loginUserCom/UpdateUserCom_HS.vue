<template>
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

      <b-field
        label="Current UserImage"
        align="left"
        message="<주의> 이미지 재등록 없을시 기본 이미지로 변경"
      >
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
      <center>
        <b-button
          type="is-primary"
          outlined
          v-on:click="submitUser()"
          position="is-centered"
          size="is-large"
          >정보수정</b-button
        >
      </center>
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
