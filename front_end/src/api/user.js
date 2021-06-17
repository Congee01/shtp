import axios from "axios";
import { USER_MODULE } from "./_prefix";

export const register = payload => {
  console.log(payload);
  const { uname, phone, password, picture } = payload;
  return axios
    .post(`${USER_MODULE}/register`, {
      uname,
      phone,
      password,
      picture
    })
    .then(res => {
      return res.data;
    });
};

export const login = payload => {
  console.log(payload);
  const { phone, password } = payload;
  return axios.post(`${USER_MODULE}/login`, { phone, password }).then(res => {
    return res.data;
  });
};

export const recharge = payload => {
  console.log(payload);
  const { userId, value } = payload;
  return axios.post(`${USER_MODULE}/recharge`, { userId, value }).then(res => {
    return res.data;
  });
};

export const getUser = uid => {
  console.log(uid);
  return axios.get(`${USER_MODULE}/${uid}`).then(res => {
    return res.data;
  });
};
