import  api from "./api.js";

//가입
async function join(user, success, fail) {
  await api.post(`/user`,JSON.stringify(user)).then(success).catch(fail);
}

//로그인
async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

//수정
async function update(user, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.put(`/user/`,JSON.stringify(user)).then(success).catch(fail);
}

//아이디 확인
async function idcheck(userid, success, fail) {
  await api.put(`/user/${userid}`,JSON.stringify(user)).then(success).catch(fail);
}

//로그아웃
async function logout(success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/logout`).then(success).catch(fail);
}

//유저 정보 가져오기 - 마이페이지
async function findById(success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user`).then(success).catch(fail);
}

//토큰재생산
async function tokenRegeneration(success, fail) {
  api.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await api.post(`/user/refresh`).then(success).catch(fail);
}



export { join, login, update,idcheck, logout, findById, tokenRegeneration };
  