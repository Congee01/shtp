package com.shtp.serviceimpl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shtp.mapperservice.ProductMapper;
import com.shtp.mapperservice.ProductOrderMapper;
import com.shtp.po.Product;
import com.shtp.po.ProductOrder;
import com.shtp.service.ProductService;
import com.shtp.util.Constant;
import com.shtp.util.PageInfoUtil;
import com.shtp.vo.ProductVO;
import com.shtp.vo.ResultVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;
    @Resource
    private ProductOrderMapper productOrderMapper;


    @Override
    public PageInfo<ProductVO> getProducts(Integer currPage, Integer pageSize, Integer uid, String key) {
        if(currPage==null || currPage<1) currPage=1;
        PageHelper.startPage(currPage, pageSize);
        PageInfo<Product> po = new PageInfo<>(productMapper.queryAll(key));
        return getProductVOPageInfo(uid, po);
    }

    @Override
    public List<ProductVO> getBoughtProduct(Integer uid) {
        List<ProductVO> ret = new ArrayList<>();
        List<Product> productList = productMapper.selectByUserId(uid);
        for(Product product:productList){
            ret.add(new ProductVO(product,true,false));
        }
        return ret;
    }

    @Override
    public List<ProductVO> getManageableProducts(Integer uid) {
        List<ProductVO> ret = new ArrayList<>();
        List<Product> productList = productMapper.selectByManagerId(uid);
        for(Product product:productList){
            ret.add(new ProductVO(product,false,true));
        }
        return ret;
    }

    @Override
    public ProductVO getProduct(Integer productId, Integer uid) {
        Product product = productMapper.selectByPrimaryKey(productId);
        boolean bought = false;
        boolean manageable = false;
        if(uid != null && uid > 0) {
            ProductOrder order = productOrderMapper.queryMostRecentOrder(uid, productId);
            if(order != null)
                bought = order.getStatus().equals(Constant.ORDER_STATUS_SUCCESS);
            manageable = uid.equals(product.getManagerId());
        }
        return new ProductVO(product,bought,manageable);
    }

    @Override
    public ResultVO<ProductVO> createCourse(ProductVO productVO) {
        productVO.setCreateTime(new Date());
        for(Product product: productMapper.selectByManagerId(productVO.getManagerId())){
            if (product.getName().equals(productVO.getName()))
                return new ResultVO<>(Constant.REQUEST_FAIL, "已存在同名商品！");
        }
        Product product = new Product(productVO);
        if(productMapper.insert(product) > 0){
            return new ResultVO<ProductVO>(Constant.REQUEST_SUCCESS, "商品创建成功。", new ProductVO(product, false, true));
        }
        return new ResultVO<>(Constant.REQUEST_FAIL, "服务器错误");
    }

    @Override
    public ResultVO<ProductVO> updateProduct(ProductVO productVO) {
        Product product=new Product(productVO);
        if(productMapper.updateByPrimaryKey(product) > 0){
            return new ResultVO<>(Constant.REQUEST_SUCCESS,"商品信息更新成功",new ProductVO(product));
        }
        else{
            return new ResultVO<>(Constant.REQUEST_FAIL,"商品信息更新失败");
        }
    }


    private PageInfo<ProductVO> getProductVOPageInfo(Integer uid, PageInfo<Product> po) {
        PageInfo<ProductVO> result = PageInfoUtil.convert(po, ProductVO.class);
        if(uid != null && uid > 0){
            List<ProductVO> voList = result.getList();
            for(ProductVO vo: voList){
                ProductOrder order = productOrderMapper.queryMostRecentOrder(uid, vo.getId());
                if(order != null)
                    vo.setBought(order.getStatus().equals(Constant.ORDER_STATUS_SUCCESS));
                vo.setManageable(uid.equals(vo.getManagerId()));
            }
        }
        return result;
    }


}
