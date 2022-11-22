import  {api} from "./api.js";

//댓글 작성
async function writeComment(comment,success, fail) {
    api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
    await api.post(`/comment`,comment).then(success).catch(fail);
}

//댓글 가져오기
async function commentList(boardId,success, fail) {
    await api.get(`/comment/${boardId}`).then(success).catch(fail);
}


export { writeComment, commentList };
  