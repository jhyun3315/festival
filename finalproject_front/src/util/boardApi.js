import  {api,fileApi} from "./api.js";

//게시글 등록
async function articleWrite(article, success, fail) {
  fileApi.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await fileApi.post(`/board`,article).then(success).catch(fail);
}

//게시글 확인
async function articleList(success, fail) {
  await api.get(`/board`).then(success).catch(fail);
}

export { articleWrite, articleList };
  