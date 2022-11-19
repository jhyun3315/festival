import axios from "axios";

export default axios.create({
    baseURL:"http://localhost:80",
    headers:{
        "Content-Type" : "application/json",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Headers": "*",
        'Access-Control-Allow-Methods': 'POST, GET, PUT, DELETE'
    }
})
