<template>
  <div>
    <div class="area_title">
      <h1>{{area | areaFilter}}</h1>
    </div>
    <div id="map-wrapper" class="map-wrapper"></div>
  </div>
</template>

<script>
import * as d3 from "d3";

const MAP_GEOJSON = require("./Map/TL_SCCO_CTPRVN.json");
const MAP_AREA = require("./Map/map.area.json");

export default {
  components: {},
  props: {
    area:String
  },
  data() {
    return {
      province: undefined, // 마우스가 지역구 위에 있을 때 정보
      currentProvince: undefined, // 지역구를 클릭했을 때 정보
      mapArea: MAP_AREA, // 지역구 지역정보
    };
  },
  computed: {},
  mounted() {
    this.drawMap();
  },

  methods: {
    // 지역구의 지역 정보 찾기
    findArea(code) {
      // console.log(code);
      if (code in this.mapArea) {
        return Array.isArray(this.mapArea[code]) &&
          this.mapArea[code].length >= 2
          ? this.mapArea[code][1]
          : "";
      }
      return "";
    },

    // 지역구의 당선자 찾기
    findCandidate(code) {
      let candidate = null;
    },
    // 선택된 지역구
    selectProvince(province) {
      if (province) {
        // console.log(province);
        // console.log(this.findCandidate(province.SGG_Code));
        province.candidate = this.findCandidate(province.SGG_Code);
      }
    },

    // // 지역구 정보 열기
    openInfo(province) {
      // console.log(province);
      if (province) {
        province.candidate = this.findCandidate(province.SGG_Code);
      }
      this.currentProvince = province;
    },

    // 지역구 정보 닫기
    closeInfo() {
      this.currentProvince = undefined;
    },
    getFestival(area) {
      this.$emit("getFestival", area);
    },
    drawMap() {
      // 지도정보
      const geojson = MAP_GEOJSON;
      // 지도의 중심점 찾기
      const center = d3.geoCentroid(geojson);

      let centered = undefined;

      // 현재의 브라우저의 크기 계산
      const divWidth = document.getElementById("map-wrapper").clientWidth;
      const width = divWidth < 1000 ? divWidth * 0.9 : 1000;
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
          .range(['#cdcdd6', '#cdcdd6']);
  
        const _this = this;
        // Get province color
        function fillFn(d){
          // console.log(d, nameLength(d));
          // console.log(d.properties); 
          return color(nameLength(d));
        }
  
        
        let clicked=(d)=>{ //---------------------------------------------------------------클릭시 진행
          var x, y, k;

          if(d!==undefined){
            console.log(d.properties.CTP_KOR_NM);
            this.getFestival(d.properties.CTP_KOR_NM)
          }
          // Compute centroid of the selected path
          if (d && centered !== d) {
            var centroid = path.centroid(d);
            x = centroid[0];
            y = centroid[1];
            k = 4;
            centered = d; 
          } else {
            x = width / 2;
            y = height / 2;
            k = 1;
            centered = null; 
          }
  
          // Highlight the clicked province
          mapLayer.selectAll('path')
            .style('fill', function(d){
              return centered && d===centered ? '#FF9900' : fillFn(d);
          });
   
          // g.transition()
          //   .duration(750)
          //   .attr('transform', 'translate(' + width / 2 + ',' + height / 2 + ')scale(' + k + ')translate(' + -x + ',' + -y + ')');
        }
  
  
        function mouseover(d){
          // Highlight hovered province
          d3.select(this).style('fill', '#00CCFF');
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
              return centered && d===centered ? '#FF9900' : fillFn(d);
            });
        }
  
        // Get province name length
        function nameLength(d){
          const n = nameFn(d);
          return n ? n.length : 0;
        }

        // Highlight the clicked province
        mapLayer.selectAll("path").style("fill", function (d) {
          return centered && d === centered ? "#FF9900" : fillFn(d);
        });

        // g.transition()
        //   .duration(750)
        //   .attr('transform', 'translate(' + width / 2 + ',' + height / 2 + ')scale(' + k + ')translate(' + -x + ',' + -y + ')');
      //};

      function mouseover(d) {
        // Highlight hovered province
        d3.select(this).style("fill", "#00CCFF");
        // d3.select(this).style('fill', '#004EA2');
        if (d) {
          // console.log(d.properties);
          _this.selectProvince(d.properties);
        }
      }

      function mouseout(d) {
        _this.selectProvince(undefined);
        // Reset province color

        mapLayer.selectAll("path").style("fill", (d) => {
          return centered && d === centered ? "#FF9900" : fillFn(d);
        });
      }

      // Get province name length
      function nameLength(d) {
        const n = nameFn(d);
        return n ? n.length : 0;
      }

      // Get province name
      function nameFn(d) {
        return d && d.properties ? d.properties.name : null;
      }

      // Add background
      background.on("click", clicked);

      // 지도 그리기
      mapLayer
        .selectAll("path")
        .data(geojson.features)
        .enter()
        .append("path")
        .attr("d", path)
        .attr("vector-effect", "non-scaling-stroke")
        .style("fill", fillFn)
        .on("mouseover", mouseover)
        .on("mouseout", mouseout)
        .on("click", clicked);

      const iconsInfo = [
        {
          name: "서울",
          lat: "37.532600",
          lon: "126.904612",
        },
        {
          name: "대전",
          lat: "36.3730178",
          lon: "127.2483736",
        },
      ];

      // 확대/축소 이벤트 처리
      // const zoomed = () =>{
      // mapLayer.attr('transform', d3.event.transform)
      // iconsLayer.attr('transform', d3.event.transform)
      // }
      // const zoom = d3.zoom().scaleExtent([1, 8]).on('zoom', zoomed)
      // svg.call(zoom)
    },
  },
  filters:{
    areaFilter(area){
      if(area===""){
        return "지역을 선택해주세요"
      }else{
        return area+"지역"
      }
    }
  }
};
</script>

<style lang="scss">
.area_title{
  display: flex;
  justify-content: center;
}
.map-wrapper {
  position: relative;
  text-align: center;

  // svg {
  //   border: 1px solid #aaaaaa;
  // }

  .province-title {
    position: absolute;
    top: 10px;
    left: 40px;
    width: 600px;
    color: rgb(77, 75, 75);
    z-index: 100;
    pointer-events: none;
    font-size: 0.9rem;
  }
  .province-info {
    // background: white;
    position: absolute;
    color: rgb(77, 75, 75);
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
    color: rgb(77, 75, 75);
    bottom: 200px;
    right: 10px;
    width: 300px;
    z-index: 102;
    pointer-events: none;
  }
  .province-summary {
    // background: white;
    position: absolute;
    color: rgb(77, 75, 75);
    bottom: 10px;
    right: 10px;
    width: 300px;
    z-index: 103;
    pointer-events: none;
  }
  .province-state > ul,
  .province-summary > ul {
    list-style: none;
  }
  .background {
    /* fill: #021019; */
    fill: transparent;
    pointer-events: all;
  }
  .map-layer {
    fill: #cdcdd6;
    stroke: #2b2c2c;
    stroke-width: 1px;
  }
}
</style>