import {api} from "./api.js";


//모든 축제
async function getEveryFestival(success, fail) {
    await api.get(`/festival/list`, {params:{every:true}}).then(success).catch(fail);
}

//현재 진행중
async function getNowFestival(success, fail) {
    await api.get(`/festival/list`, {params:{now:true}}).then(success).catch(fail);
}

//지역 검색
async function getAreaFestival(area,success, fail) {
    await api.get(`/festival/list`, {params:{area:area}}).then(success).catch(fail);
}

export { getAreaFestival,getEveryFestival,getNowFestival};
