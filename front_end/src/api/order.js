import axios from "axios";
import { ORDER_MODULE } from "./_prefix";

export const createOrder = payload => {
  const { productId, productName, cost, userId, status } = payload;
  return axios.post(`${ORDER_MODULE}/create`, {
      productId,
      productName,
      cost,
      userId,
      status
    })
    .then(res => {
      return res.data;
    });
};

export const updateOrder = payload => {
  const { orderId, orderStatus } = payload;
  return axios.post(`${ORDER_MODULE}/update?orderId=${orderId}&orderStatus=${orderStatus}`).then(res => {
      return res.data;
    });
};

export const getOrdersByUser = uid => {
  return axios.get(`${ORDER_MODULE}/uid/${uid}`).then(res => {
    return res.data;
  });
};
