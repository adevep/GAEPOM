<template>
  <div class="hello">
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link
      href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
      rel="stylesheet"
    />
    <link
      href="https://fonts.googleapis.com/css2?family=Noto+Serif+KR:wght@200&display=swap"
      rel="stylesheet"
    />
    <section class="hero is-white is-fullheight">
      <div class="hero-body">
        <div class="container">
          <div class="columns  is-vcentered reverse-columns">
            <div
              class="column
          is-10-mobile is-offset-1-mobile
          is-10-tablet is-offset-1-tablet
          is-5-desktop is-offset-1-desktop
          is-5-widescreen is-offset-1-widescreen
          is-5-fullhd is-offset-1-fullhd"
            >
              <h1 class="title titled is-1 mb-6">
                프로젝트를 위해 모이는 사람들
              </h1>
              <h2
                class=" subtitled subtitle has-text-grey is-4 has-text-weight-normal is-family-sans-serif"
              >
                당신만을 위한, 당신이 찾던 그 팀<br />
                당신이 완성할 그 프로젝트 <br />
                지금 여기서 시작하세요<br />
              </h2>
              <div class="buttons">
                <b-button
                  class="button is-black"
                  tag="router-link"
                  to="/profile"
                  type="is-link"
                >
                  팀원찾기
                </b-button>
                 <b-button
                  class="button is-light"
                  tag="router-link"
                  to="/project"
                  type="is-link"
                >
                  프로젝트
                </b-button>
              </div>
            </div>
            <div
              class="column
          is-10-mobile is-offset-1-mobile
          is-10-tablet is-offset-1-tablet
          is-4-desktop is-offset-1-desktop
          is-4-widescreen is-offset-1-widescreen
          is-4-fullhd is-offset-1-fullhd"
            >
              <figure class="image is-square">
                <img src="../assets/main.png" />
              </figure>
            </div>
          </div>
        </div>
      </div>
    </section>
    <section class="hero is-medium has-text-centered">
      <div class="hero-body">
        <div class="container">
          <div class="columns is-centered">
            <div class="column is-8">
              <figure class="image is-medium">
                <img src="../assets/main2.png" />
              </figure>
              <h1 class="title titled 40by40">
                개발자들을 위한 플랫폼, '개폼'
              </h1>

              <h2 class="subtitle subtitled is-4">
                지금 이 곳에서 함께 프로젝트를 진행할 동료들을 모집하거나 직접
                프로젝트에 참여해보세요! <br />
                그리고 완성된 프로젝트를 자랑해 보세요. 피드백과 좋아요를 받을
                수 있습니다. 당신의 멋진 아이디어를 실현해 보세요!
              </h2>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: "HelloWorld",
  data: () => ({})
};
</script>
