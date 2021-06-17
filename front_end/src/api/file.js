import axios from "axios";
import { FILE_MODULE } from "./_prefix";


export const uploadFile = payload => {
  return axios.post(`${FILE_MODULE}/upload`, payload).then(res => {
    return res.data;
  });
};
