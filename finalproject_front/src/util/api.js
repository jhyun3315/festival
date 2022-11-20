import axios from "axios";

export default axios.create({
    baseURL:process.env.VUE_APP_BASE_URL,
    headers:{
        "Content-Type": "application/json;charset=utf-8",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Headers": "*",
        'Access-Control-Allow-Methods': 'POST, GET, PUT, DELETE'
    }
})
