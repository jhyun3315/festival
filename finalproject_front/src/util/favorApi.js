import {api} from "./api.js";

//즐겨찾기 등록
async function favorAdd(festivalId,success,fail){
    api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
    api.post("/favor",{festivalId}).then(success).catch(fail)
}

//즐겨찾기 조회
async function favorGet(success,fail){
    api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
    api.get("/favor").then(success).catch(fail)
}

//즐겨찾기 삭제
async function favorDelete(festivalId,success,fail){
    api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
    api.delete(`/favor/${festivalId}`).then(success).catch(fail)
}

export { favorAdd,favorGet,favorDelete };
