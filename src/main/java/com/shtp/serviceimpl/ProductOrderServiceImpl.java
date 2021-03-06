package com.shtp.serviceimpl;

import com.shtp.mapperservice.ProductOrderMapper;
import com.shtp.mapperservice.UserMapper;
import com.shtp.po.ProductOrder;
import com.shtp.po.User;
import com.shtp.service.ProductOrderService;
import com.shtp.util.Constant;
import com.shtp.vo.ProductOrderVO;
import com.shtp.vo.ResultVO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {
    @Resource
    private ProductOrderMapper productOrderMapper;
    @Resource
    private UserMapper userMapper;


    @Override
    public ResultVO<ProductOrderVO> insertCourseOrder(ProductOrderVO orderVO) {
        ProductOrder order;
        try{
            if((order = productOrderMapper.queryMostRecentOrder(orderVO.getUserId(), orderVO.getProductId())) != null
                    && !order.getStatus().equals(Constant.ORDER_STATUS_CANCEL)){
                return new ResultVO<>(Constant.REQUEST_FAIL, "当前用户已经购买过此商品，或者有上一笔未支付的订单", new ProductOrderVO(order));
            }
            order = new ProductOrder(orderVO);
            Integer status = order.getStatus();
            if(status.equals(Constant.ORDER_STATUS_UNPAID)){
                // 当订单状态为未支付时，检查用户余额是否足够
                User user = userMapper.selectByPrimaryKey(order.getUserId());
                if(user.getBalance()>=orderVO.getCost()){
                    userMapper.decreaseBalance(user.getId(), order.getCost());
                    // 支付成功，将订单存入数据库
                    order.setStatus(Constant.ORDER_STATUS_SUCCESS);
                    productOrderMapper.insert(order);
                }else {
                    // 将订单存入数据库
                    productOrderMapper.insert(order);
                    // 若用户余额不足，则不保存订单，返回失败信息
                    return new ResultVO<>(Constant.REQUEST_FAIL, "用户余额不足，未支付订单已保存", new ProductOrderVO(order));
                }
            }else if(status.equals(Constant.ORDER_STATUS_SUCCESS) || status.equals(Constant.ORDER_STATUS_WAIT)) {
                // 若订单状态为已支付或等待发货，则直接存入数据库
                productOrderMapper.insert(order);
                return new ResultVO<>(Constant.REQUEST_SUCCESS, "订单保存成功", new ProductOrderVO(order));
            }
        }catch (Exception e){
            return new ResultVO<>(Constant.REQUEST_FAIL, e.toString());
        }
        return new ResultVO<>(Constant.REQUEST_SUCCESS, "商品购买成功", new ProductOrderVO(order));
    }


    @Override
    public List<ProductOrderVO> getProductOrders(Integer uid) {
        List<ProductOrderVO> ret = new ArrayList<>();
        List<ProductOrder> orderList = productOrderMapper.selectByUserId(uid);
        for(ProductOrder order: orderList){
            ret.add(new ProductOrderVO(order));
        }
        return ret;
    }
}
