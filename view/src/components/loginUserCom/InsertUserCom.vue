<template>
     <div class="container is-max-desktop">
  <div class="notification is-accent">
      <h1><strong>가입 정보 입력</strong></h1>
        <b-field label="ID" type=""
            message="" align="left">
            <b-input v-model="userid" placeholder="아이디" maxlength="30" size="is-medium" rounded></b-input>
        </b-field> <br>
        <b-field label="Password"
            type=""
            message="" align="left">
            <b-input v-model="password" type="password" placeholder="비밀번호" maxlength="30" size="is-medium" rounded password-reveal>
            </b-input>
        </b-field> <br>
        <b-field label="Name" type=""
            message="" align="left">
            <b-input v-model="name" placeholder="이름" maxlength="30" size="is-medium" rounded></b-input>
        </b-field> <br>
        <b-field label="Age" type=""
            message="" align="left">
            <b-input v-model="age" placeholder="나이" maxlength="30" size="is-medium" rounded></b-input>
        </b-field> <br>
        <b-field label="E-mail" type=""
            message="" align="left">
            <b-input v-model="email" placeholder="이메일" maxlength="30" size="is-medium" rounded></b-input>
        </b-field> <br>
        <b-field label="Phone-Number" type=""
            message="예시) 010-XXXX-XXXX" align="left">
            <b-input v-model="phonenum" placeholder="전화번호" maxlength="30" size="is-medium" rounded></b-input> 
        </b-field> <br>
        <b-field label="Address" type=""
            message="" align="left">
            <b-input v-model="address" placeholder="주소" maxlength="30" size="is-medium" rounded></b-input>
        </b-field> <br>
         <b-field label="Stack" type=""
            message="" align="left">
            <b-input v-model="stack" placeholder="기술" maxlength="30" size="is-medium" rounded></b-input>
        </b-field> <br>
        <b-field label="Position" align="left">
            <b-select v-model="position" expanded>
                <option value="개발자">개발자</option>
                <option value="기획자">기획자</option>
                <option value="디자이너">디자이너</option>
            </b-select>
        </b-field> <br>
        <b-field label="User-Image" align="left">
     <input type="file" id="file" ref="file" v-on:change="handleFileUpload()"/>
        </b-field> <br> <br>
          <b-button type="is-primary" outlined v-on:click="submitUser()" position="is-centered" size="is-large">회원가입</b-button>
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
      stack: "",
      position: "",
      file: "",
    };
  },
   methods: {
            submitUser() {

            let formData = new FormData();

            formData.append('userId', this.userid);
            formData.append('password', this.password);
            formData.append('name', this.name);
            formData.append('age', this.age);
            formData.append('email', this.email);
            formData.append('phoneNum', this.phonenum);
            formData.append('address', this.address);
            formData.append('stack', this.stack);
            formData.append('position', this.position);
            formData.append('file', this.file);

            axios.post( 'http://localhost:80/insertuser',
                formData,
                {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
              }
            ).then(function(){
          alert('SUCCESS!!');
        })
        .catch(function(){
          alert('FAILURE!!');
        });
        router.push({ name: "Home" });
      },
             handleFileUpload(){
            this.file = this.$refs.file.files[0];
        },
  }
};
</script>