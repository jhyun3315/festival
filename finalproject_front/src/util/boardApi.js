import  {api,fileApi} from "./api.js";

//게시글 등록
async function articleWrite(article, success, fail) {
  fileApi.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await fileApi.post(`/board`,article).then(success).catch(fail);
}

//게시글 리스트
async function articleList(param,success, fail) {
  await api.get(`/board`,{ params: param }).then(success).catch(fail);
}

//이미지 가져오기
function getImage(boardId){
  return `${process.env.VUE_APP_BASE_URL}/board/image/${boardId}`;
}

//게시글 보기
async function getArticle(boardId,success, fail) {
  await api.get(`/board/${boardId}`).then(success).catch(fail);
}

//게시글 삭제
async function articleDelete(boardId,success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.delete(`/board/${boardId}`).then(success).catch(fail);
}

//게시글 수정
async function articleUpdate(article, success, fail) {
  fileApi.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await fileApi.put(`/board`,article).then(success).catch(fail);
}

export { articleWrite, articleList, getImage, getArticle ,articleDelete,articleUpdate};
  