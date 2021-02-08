<template>
  <div class="project">
    <h1>This is project page</h1>
    <section>

<div id="project-list">
              <h2>지원자 리스트</h2>
              <!-- <i class="fas fa-search" @mouseenter="applicationListView"></i>
              <div id="search-result" @mouseleave="applicationListHidden"> -->
                  <div v-for="list in application" v-bind:key="list" @click="insertApplicationList(list.userId)">
                      
                      <div>
                        <h3 v-html="list.aplSeq"></h3>
                        <p>
                            {{list.userId}} | 
                            
                        </p>
                    <!-- </div> -->
                  </div>
              </div>
</div>
    </section>



  </div>
</template>
<script>
//import Vue from "vue";
import axios from "axios";
//import Navigation from "@/components/Navigation";
//import ResizableTextarea from '../assets/ResizableTextarea.js';
//import VueInputAutowidth from 'vue-input-autowidth';
//Vue.use(VueInputAutowidth);

function getUserId() {
    return localStorage.getItem('userId');
}
const userId = getUserId();
if(userId === null) {
    location.assign('/login.html');
}



export default {
  name: 'applicant',
  components: {
        //Navigation,
        //ResizableTextarea
  },
  data() {
    return {
      applicationList:[]
    }
  },
  mounted() {
    axios
      .get('http://localhost:80/getApplicationList/' + userId)
      .then(response => {
        this.applicationList = response.data;
      })
  },
  methods: {  
    applicationListView(userId) { // 주최자 아이디가 내 아이디와 일치할때만 볼수있는 지원자 리스트
      axios
                .get('http://localhost:80/getApplicationList/' + userId) //이 서버주소로부터 데이터를 불러옴
                .then(response => {
                    this.applicationList = response.data;
                })
    },
    applicationListHidden() {
            document.querySelector('#search-result').style.display="none";
        },
    insertApplicationList(userId){ //지원자 참여자로 업데이트 되는 기능 (selected 의 넘버 변화)
      axios
                .put('http://localhost:80/getApplicationList/',{ //수정 필요
                    userId: userId,
                })
                .then(response => {
                  this.applicationList = response.data;
                    //location.reload(true);
                })

    },
    // deleteApplicationList(aplSeq){ //주최자가 지원자 선택해 떨구기 (불합격)
    //   axios
    //             .delete('http://localhost:80/deleteApplication/'+aplSeq)
    //             .then(response => {
    //                 location.reload(true);
    //             })
    // },
    // insertApplication(userId) {//지원했을때 내 아이디가 지원자리스트에 추가
    //   axios
    //     .post('http://localhost:80//insertApplication/',{ //서버리스트에 userId라는 데이터 정보를 추가
    //         userId: userId
    //     })
    //     .then(response => {
    //       if(response.status==200) {
    //                     location.reload(true);
    //                 } else {
    //                     alert("이미 지원한 프로젝트입니다.");
    //                 }
    //     })
    // },
    //  deleteApplication(userId) {//지원 취소 (지원자 리스트에서 내 아이디 삭제)
    //         axios
    //             .delete('http://localhost:80/deleteApplication/'+userId)
    //             .then(response => {
    //                 location.reload(true);
    //             })
    //     },


  }
  /*
    모집글 클릭
    - 모집글 출력(지원하기:주최자아닐시 / )



    1. 주최자 시점
        - 지원자 리스트 보기
        - 지원자 클릭해서 참여자로 추가
        - 지원자 클릭해서 떨구기

    2. 지원자 시점
        - 클릭해서 지원하기
        - 클릭해서 지원 취소하기

        ====================================
  - Backend 

      모집글 CRUD
      프로젝트 CRUD (모집글쪽에서 다 같이 수정가능하게 만들어야함) 
      애플리케이션 CRUD
      지원자 참여자로 업데이트 되는 기능 (selected 의 넘버 변화)
      Position front단에서 선택가능하게 하기 위해 데이터 편한 포멧으로 split하기

  - Frontend

      모집글 출력 (지원하기 버튼 (주최자 아닐때만 보이게 할것) 
      모집글 상세 출력 (주최자이면 업뎃 버튼을 볼 수 있게 할것) 
      마이페이지 프로젝트 모집글 생성, 업뎃, 지원자 셀렉
      프로젝트 모집글 검색 

  */



  
}
</script>