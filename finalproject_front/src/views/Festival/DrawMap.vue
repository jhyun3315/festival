<template>
    <div id="map-wrapper" class="map-wrapper">
  
    </div>
  </template>
  
  <script>
  import * as d3 from 'd3'
  
  const MAP_GEOJSON = require('./map.geo.json');
  const MAP_AREA = require('./map.area.json');
  const LOCAL_SEAT_FINAL = require('./localseat.final').default;
  
  const Vote21RatioBar = require('./Vote21.RatioBar').default;
  export default {
    components: {
      "vote21-ratio-bar" : Vote21RatioBar,
    },
    props: {
    },
    data() {
      return {
        province: undefined,            // 마우스가 지역구 위에 있을 때 정보
        currentProvince: undefined,     // 지역구를 클릭했을 때 정보
        mapArea: MAP_AREA,              // 지역구 지역정보
  
        localSeatData : {},             // 정당별 당선자수 요약
        partyList : {
          theminjoo : {name: "더불어민주당", no:1, localLock:false, ratioLock:true, color:"#004EA2"},
          unitedfuture : {name: "미래통합당", no:2, localLock:false, ratioLock:true, color:"#EF426F"},
          minsaengdang : {name: "민생당", no:3, localLock:false, ratioLock:false, color:"#0BA95F"},
          futurekorea : {name: "미래한국당", no:4, localLock:true, ratioLock:false, color:"#EF426F"},
          platformparty : {name: "더불어시민당", no:5, localLock:true, ratioLock:false, color:"#0088D2"},
          justice21 : {name: "정의당", no:6, localLock:false, ratioLock:false, color:"#FFCA05"},
          ourrepublican : {name: "우리공화당", no:7, localLock:false, ratioLock:false, color:"#009900"},
          minjung : {name: "민중당", no:8, localLock:false, ratioLock:false, color:"#F47920"},
          koreaeconomy : {name: "한국경제당", no:9, localLock:true, ratioLock:false, color:"#009900"},
          people21 : {name: "국민의당", no:10, localLock:true, ratioLock:false, color:"#EA5504"},
          proparknew : {name: "친박신당", no:11, localLock:false, ratioLock:false, color:"#BBBBBB"},
          openminjoo : {name: "열린민주당", no:12, localLock:true, ratioLock:false, color:"#004098"},
          etcparty : {name: "기타", localLock:false, ratioLock:false, color:"#BBBBBB"},
          noparty : {name: "무소속", localLock:false, ratioLock:true, color:"#AAAAAA"},
          undecided : {name: "무당층", localLock:true, ratioLock:false, color:"#CCCCCC"},
        },
        localSeatFinal : LOCAL_SEAT_FINAL,
      }
    },
    computed: {
    },
    created() {
      this.summarize();
    },
    mounted() {
      this.drawMap();
    },
  
    methods: {
      // 정당별 당선자수 요약
      summarize() {
        this.localSeatData = {};
        Object.keys(this.localSeatFinal).forEach(localSeatCode => {
          let candidate = null;
          const localSeat = this.localSeatFinal[localSeatCode];
          localSeat.some((item) => {
            if(item.elected === true) {
              candidate = item;
              return true;
            }
          });
          if(candidate) {
            if(candidate.party in this.localSeatData) {
              this.localSeatData[candidate.party]++;
            } else {
              this.localSeatData[candidate.party] = 1;
            }
          }
        });
        // console.log(this.localSeatData);
      },
      // 지역구의 지역 정보 찾기
      findArea(code) {
        // console.log(code);
        if(code in this.mapArea) {
          return Array.isArray(this.mapArea[code]) && this.mapArea[code].length >= 2 ? this.mapArea[code][1] : "";
        }
        return "";
      },
      // 지역구의 당선자 찾기
      findCandidate(code) {
        let candidate = null;
        const localSeat = this.localSeatFinal[code];
        localSeat.some((item) => {
          if(item.elected === true) {
            candidate = item;
            return true;
          }
        });
  
        return candidate;
      },
      // 선택된 지역구
      selectProvince(province) {
        if(province) {
          // console.log(province);
          // console.log(this.findCandidate(province.SGG_Code));
          province.candidate = this.findCandidate(province.SGG_Code);
        }
        this.province = province;
      },
  
      // 지역구 정보 열기
      openInfo(province) {
        // console.log(province);
        if(province) {
          province.candidate = this.findCandidate(province.SGG_Code);
          province.final = this.localSeatFinal[province.SGG_Code];
        }
        this.currentProvince = province;
      },
      // 지역구 정보 닫기
      closeInfo() {
        this.currentProvince = undefined;
      },
  
      partyColor(code) {
        let color = null;
        const localSeatCode = "" + code;
        if(localSeatCode in this.localSeatFinal) {
          const localSeat = this.localSeatFinal[localSeatCode];
          let candidate = null;
          localSeat.some((item) => {
            if(item.elected === true) {
              candidate = item;
              return true;
            }
          });
          if(candidate) {
            const party = candidate.party;
            color = this.partyList[party].color;
          }
        }
        return color;
      },
  
  
      // https://codepen.io/lantoine/pen/ExxEGxg
      // https://m.blog.naver.com/PostView.nhn?blogId=jhc9639&logNo=221195987416&proxyReferer=https%3A%2F%2Fwww.google.com%2F
      drawMap() {
        // 지도정보
        const geojson = MAP_GEOJSON;
        // 지도의 중심점 찾기
        const center = d3.geoCentroid(geojson);
  
        let centered = undefined;
  
        // 현재의 브라우저의 크기 계산
        const divWidth = document.getElementById("map-wrapper").clientWidth;
        const width = (divWidth < 1000) ? divWidth * 0.9 : 1000;
        const height = width * 1;
  
        // 지도를 그리기 위한 svg 생성
        const svg = d3
        // .select('.d3')
          .select('.map-wrapper')
          .append('svg')
          .attr('width', width)
          .attr('height', height);
  
        // 배경 그리기
        const background = svg.append('rect')
          .attr('class', 'background')
          .attr('width', width)
          .attr('height', height)
  
        // 지도가 그려지는 그래픽 노드(g) 생성
        const g = svg.append('g');
        // const effectLayer = g.append('g').classed('effect-layer', true);
        // 지도가 그려지는 그래픽 노드
        const mapLayer = g.append('g').classed('map-layer', true);
        // 아이콘이 그려지는 그래픽 노드
        const iconsLayer = g.append('g').classed('icons-layer', true);
  
        // 지도의 출력 방법을 선택(메로카토르)
        let projection = d3.geoMercator()
          .scale(1)
          .translate([0, 0]); 
  
        // svg 그림의 크기에 따라 출력될 지도의 크기를 다시 계산
        const path = d3.geoPath().projection(projection);
        const bounds = path.bounds(geojson);
        const widthScale = (bounds[1][0] - bounds[0][0]) / width; 
        const heightScale = (bounds[1][1] - bounds[0][1]) / height; 
        const scale = 0.95 / Math.max(widthScale, heightScale);
        const xoffset = width/2 - scale * (bounds[1][0] + bounds[0][0]) /2 + 0; 
        const yoffset = height/2 - scale * (bounds[1][1] + bounds[0][1])/2 + 0; 
        const offset = [xoffset, yoffset];
        projection.scale(scale).translate(offset);
  
        const color = d3.scaleLinear()
          .domain([1, 20])
          .clamp(true)
          // .range(['#08304b', '#08304b']);
          .range(['#595959', '#595959']);
  
        const _this = this;
        // Get province color
        function fillFn(d){
          // console.log(d, nameLength(d));
          // console.log(d.properties);
  
          const pcolor = _this.partyColor(d.properties.SGG_Code);
          if(pcolor) {
            return pcolor;
          }
  
          return color(nameLength(d));
        }
  
        function clicked(d) {
          var x, y, k;
  
          // Compute centroid of the selected path
          if (d && centered !== d) {
            var centroid = path.centroid(d);
            x = centroid[0];
            y = centroid[1];
            k = 4;
            centered = d;
            _this.openInfo(d.properties);
          } else {
            x = width / 2;
            y = height / 2;
            k = 1;
            centered = null;
            _this.closeInfo();
          }
  
          // Highlight the clicked province
          mapLayer.selectAll('path')
            .style('fill', function(d){
              return centered && d===centered ? '#D5708B' : fillFn(d);
          });
  
          // Zoom
          g.transition()
            .duration(750)
            .attr('transform', 'translate(' + width / 2 + ',' + height / 2 + ')scale(' + k + ')translate(' + -x + ',' + -y + ')');
        }
  
  
        function mouseover(d){
          // Highlight hovered province
          d3.select(this).style('fill', '#1483ce');
          // d3.select(this).style('fill', '#004EA2');
          if(d) {
            // console.log(d.properties);
            _this.selectProvince(d.properties);
          }
        }
  
        function mouseout(d){
          _this.selectProvince(undefined);
          // Reset province color
          mapLayer.selectAll('path')
            .style('fill', (d) => {
              return centered && d===centered ? '#D5708B' : fillFn(d);
            });
        }
  
        // Get province name length
        function nameLength(d){
          const n = nameFn(d);
          return n ? n.length : 0;
        }
  
        // Get province name
        function nameFn(d){
          return d && d.properties ? d.properties.name : null;
        }
  
        // Add background
        background
          .on('click', clicked);
  
        // 지도 그리기
        mapLayer
          .selectAll('path')
          .data(geojson.features)
          .enter().append('path') 
          .attr('d', path)
          .attr('vector-effect', 'non-scaling-stroke')
          .style('fill', fillFn)
          .on('mouseover', mouseover)
          .on('mouseout', mouseout)
          .on('click', clicked);
  
        const iconsInfo = [
          {
            "name":"서울",
            "lat" : "37.532600",
            "lon" : "126.904612"
          },
          {
            "name":"대전",
            "lat" : "36.3730178",
            "lon" : "127.2483736"
          }
        ]; 
  
        // 확대/축소 이벤트 처리
        const zoomed = () =>{
          mapLayer.attr('transform', d3.event.transform)
          iconsLayer.attr('transform', d3.event.transform)
        }
        const zoom = d3.zoom().scaleExtent([1, 8]).on('zoom', zoomed)
        svg.call(zoom)
      }
    }
  }
  
  </script>
  
  <style lang="scss">
  .map-wrapper {
    position:relative;
    text-align: center;
  
    // svg {
    //   border: 1px solid #aaaaaa;
    // }
  
    .province-title {
      position: absolute;
      top: 10px;
      left: 40px;
      width: 600px;
      color: white;
      z-index: 100;
      pointer-events: none;
      font-size: 0.9rem;
    }
    .province-info {
      // background: white;
      position: absolute;
      color: white;
      top: 50px;
      right: 60px;
      width: 600px;
      height: 500px;
      z-index: 101;
      font-size: 0.9rem;
      pointer-events: none;
    }
    .province-state {
      // background: white;
      position: absolute;
      color: white;
      bottom: 200px;
      right: 10px;
      width: 300px;
      z-index: 102;
      pointer-events: none;
    }
    .province-summary {
      // background: white;
      position: absolute;
      color: white;
      bottom: 10px;
      right: 10px;
      width: 300px;
      z-index: 103;
      pointer-events: none;
    }
    .province-state > ul, .province-summary > ul {
      list-style: none;
    }
    .background {
      /* fill: #021019; */
      fill: transparent;
      pointer-events: all;
    }
    .map-layer {
      fill: #08304b;
      stroke: #021019;
      stroke-width: 1px;
    }
  }
  </style>
  