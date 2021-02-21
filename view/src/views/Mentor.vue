<template>
  <div id="app" class="container is-max-desktop pt-5">
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link
      href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/@mdi/font@5.8.55/css/materialdesignicons.min.css"
    />

    <section class="hero ">
      <div class="hero-body">
        <div class="container">
          <!-- <div class="columns">
            <div class="column is-8 is-offset-2">
              <h1 class="title mb-6 center">[ 이주의 멘토 ] - 2월 마지막주</h1>
              <h3 class="title mb-6 center"> 구글 최연소 PM 권희성<br> "여러분도 갈 수 있어요, 구글"</h3>
              <figure class="image is-16by16">
                <img src="../assets/hee_blue.png" alt="" ratio="16by9" class="center" />
              </figure>
            </div>
          </div> -->

          <section>
            <div class="hero-body">
              <div class="container has-text-centered">
                <h1 class="title is-2 ">
                  [ 이주의 멘토 ] <br />
                  2월 마지막주
                </h1>
                <h2 class="subtitle is-3 centered mt-2">
                  구글 최연소 PM 권희성,
                  "여러분도 갈 수 있어요, 구글"
                </h2>
                <article>
                  <b-image
                    :src="require('@/assets/hee_blue.png')"
                    alt="HEE"
                    width="50%"
                  ></b-image>
                </article>
              </div>
            </div>
          </section>
          <section class="section">
            <div class="columns">
              <div class="column is-8 is-offset-2">
                <div class="content is-medium">
                  <h2 class="subtitle is-4 mb-6">2021년 2월 20일</h2>
                  <h1 class="title mb-6">
                    2월의 마지막 주 멘토, 개발자 권희성을 소개합니다.
                  </h1>
                  <p>
                    미취학 아동 시절, 한국어보다 컴퓨터 언어를 먼저 뗐다는 그!
                    <br />
                    학부시절, 동기, 선후배.. 심지어는 교수님의 프로그램
                    버그까지..! 오류란 오류는 다잡아내고 다녔다는 한국대 컴공의
                    전설로 불리우는 권희성씨를 모셨습니다.
                    <br /><br /><strong>
                      2월 22일 오후 3시! <br />ZOOM 링크가 이 곳에
                      공개됩니다.</strong
                    ><br /><br />
                    국내 최고의 대학인 한국대를 졸업하고도 학문의 끈을 놓을 수
                    없어 하바드대 석사과정까지 마치고, 마이크로 소프트를 거쳐 현
                    구글 재직중인 권희성씨의 인생 스토리 기대하셔도 좋습니다.<br />
                  </p>
                </div>
              </div>
            </div>
          </section>
          <div class="is-divider"></div>

          <section>
            <div class="section-light skills" id="skills">
              <div class="container">
                <div class="columns is-multiline">
                  <div class="column is-12 about-me">
                    <h1 class="title has-text-centered section-title">
                      Skills
                    </h1>
                  </div>
                  <div class="column is-6">
                    <h1>Java</h1>
                    <progress class="progress" value="95" max="100"
                      >30%</progress
                    >
                    <h1>Javascript</h1>
                    <progress class="progress" value="80" max="100"
                      >30%</progress
                    >
                    <h1>Spring</h1>
                    <progress class="progress" value="70" max="100"
                      >45%</progress
                    >
                    <h1>SQL</h1>
                    <progress class="progress" value="67" max="100"
                      >60%</progress
                    >
                  </div>
                  <div class="column is-6">
                    <h1>HTML</h1>
                    <progress class="progress" value="40" max="100"
                      >30%</progress
                    >
                    <h1>CSS</h1>
                    <progress class="progress" value="55" max="100"
                      >30%</progress
                    >
                    <h1>VueJS</h1>
                    <progress class="progress" value="50" max="100"
                      >45%</progress
                    >
                    <h1>React</h1>
                    <progress class="progress" value="30" max="100"
                      >60%</progress
                    >
                  </div>
                </div>
              </div>
            </div>
          </section>
        </div>
      </div>
    </section>
    <section class="hero ">
      <div class="hero-body">
        <div class="container">
          <section class="section">
            <div class="columns is-variable is-8">
              <div class="column is-5 is-offset-1 ">
                <div class="content is-medium">
                  <h2 class="subtitle is-5 has-text-grey">Education</h2>
                  <h1 class="title has-text-black is-3">학력사항</h1>
                  <p class="has-text-dark">
                    <strong>한국대학교 컴퓨터공학과</strong><br />
                    학부 조기수료 (2016.02) <br /><br />
                    <strong>하바드대 컴퓨터공학</strong> <br />석사 수료
                    (2018.02)
                  </p>
                </div>
              </div>
              <div class="column is-5">
                <div class="content is-medium">
                  <h2 class="subtitle is-5 has-text-grey">Career</h2>
                  <h1 class="title has-text-black is-3">경력사항</h1>
                  <p class="has-text-dark">
                    <strong>前 마이크로 소프트 재직</strong><br />(2018.02 -
                    2019.06) <br /><br />
                    <strong>現 구글 미래사업전략부 팀장</strong><br />(2019.06 -
                    현재)
                  </p>
                </div>
              </div>
            </div>
          </section>
          <div class="is-divider"></div>
          <section class="section">
            <div class="columns is-variable is-8">
              <div class="column is-5 is-offset-1">
                <div class="content is-medium">
                  <h2 class="subtitle is-5 has-text-grey">Book</h2>
                  <h1 class="title has-text-black is-3">대표 저서</h1>
                  <p class="has-text-dark">
                    화제의 자바 바이블!<br />
                    <strong>「JAVA! 자 봐, 별것아냐!」 </strong
                    ><br />(17.02)<br /><br />
                    1년 연속 베스트셀러! <br /><strong
                      >「앗! 컴퓨터어 한국어보다 쉽다!」</strong
                    ><br />(18.09) <br />
                  </p>
                </div>
              </div>
              <div class="column is-5">
                <div class="content is-medium">
                  <h2 class="subtitle is-5 has-text-grey">Broadcast</h2>
                  <h1 class="title has-text-black is-3" mb-6>방송 출연</h1>
                  <p class="has-text-dark">
                    <strong> tvN 「유 퀴즈 온더 블럭」134회</strong><br />
                    '구글을 놀라게 한 한국대 청년' <br /><br />
                    <strong>SBS 「놀라운 대회 스타킹!」100회</strong><br />
                    '100회 특집! 세상에 이런 신동은 없었다! 코딩천재 권희성
                    어린이' <br />
                  </p>
                </div>
              </div>
            </div>
          </section>
        </div>
      </div>
    </section>
  </div>
</template>
