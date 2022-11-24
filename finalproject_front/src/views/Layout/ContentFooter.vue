<template>
  <footer class="footer px-4">
    <b-row align-v="center" class="justify-content-lg-between">
      <b-col lg="6">
        <div class="copyright text-center text-lg-left text-muted">
          © {{ year }}
          <a
            href="https://www.creative-tim.com"
            class="font-weight-bold ml-1"
            target="_blank"
            >Creative Tim</a
          >
        </div>
      </b-col> 
      <b-col lg="6">
        <b-nav align="center" class="nav-footer justify-content-lg-end">
          <b-nav-item
            href="https://lab.ssafy.com/sjb378/finalfestival"
            target="_blank"
          >
            ssafy8기 대전6반 4조
          </b-nav-item>
          <b-nav-item href="https://github.com/deokisys" target="_blank">
            서준배
          </b-nav-item>
          <b-nav-item href="https://github.com/jhyun3315" target="_blank">
            이지현
          </b-nav-item>
        </b-nav>
      </b-col>
    </b-row>
  </footer>
</template>
<script>
export default {
  data() {
    return {
      year: new Date().getFullYear(),
    };
  },
};
</script>
<style></style>
