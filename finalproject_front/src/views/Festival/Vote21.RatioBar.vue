<template>
  <b-progress class="mt-2 border-white" max="100" show-value v-if="data && data.length >= 0">
    <template v-for="(item, index) in data">
      <b-progress-bar
        :key="index + '-' + item.name"
        :value="item.ratio"
        :precision="2"
        :style="barStyle(item.party)"
      >
        {{item.name}}({{item.ratio}}%)
      </b-progress-bar>
    </template>
  </b-progress>
</template>
<script>

export default {
  props: {
    'partyList': {
      type: Object
    },
    'data': {
      type : [Array],
      default : []
    },
  },
  data() {
    return {
    }
  },
  mounted () {
  },
  methods: {
    barStyle(partyCode) {
      const ret = {};
      ret.backgroundColor = this.partyList[partyCode] ? this.partyList[partyCode].color : this.partyList["noparty"].color;
      ret.color ='#ffffff';
      return ret;
    }
  },
}
</script>
<style scoped>
.border-white {
  border: 1px solid #ffffff;
}
</style>