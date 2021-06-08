
import axios from "axios";
import { PRODUCT_MODULE } from "./_prefix";

export const getProductById = payload => {
  const { uid, productId } = payload;
  return axios.get(`${PRODUCT_MODULE}/${productId}?uid=${uid}`).then(res => {
    return res.data;
    });
}


export const getProductByKey = payload => {
    const { uid, key, page } = payload;
    return axios.get(`${PRODUCT_MODULE}/all/${page}?uid=${uid}&key=${key}`).then(res => {
        return res.data;
    });
}


export const getBoughtProduct = uid => {
    return axios.get(`${PRODUCT_MODULE}/uid/${uid}`).then(res => {
        return res.data;
    });
}


export const createProduct = payload => {
    const { name, intro, picture, cost, managerId, managerName, bought, manageable } = payload;
    return axios.post(`${PRODUCT_MODULE}/create`, {
        name,  intro, picture,  cost, managerId, managerName, bought, manageable
    }).then(res => {
        return res.data;
    });
}