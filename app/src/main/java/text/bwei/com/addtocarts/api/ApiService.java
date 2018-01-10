package text.bwei.com.addtocarts.api;


import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import text.bwei.com.addtocarts.bean.CartBean;
import text.bwei.com.addtocarts.bean.GoodsShowBean;
import text.bwei.com.addtocarts.bean.MessageBean;

public interface ApiService {  
    //查询商品详情75  
    //http://120.27.23.105/product/getProductDetail?pid=75&source=android  
    @GET("product/getProductDetail")  
    Flowable<MessageBean<GoodsShowBean>> getNews(@QueryMap Map<String,String> map);
  
    //查询购物车  
    //http://120.27.23.105/product/getCarts?uid=3802&source=android  
    @GET("product/getCarts")  
    Flowable<MessageBean<List<CartBean>>> getCart(@QueryMap Map<String,String> map);
  
  
    //删除购物车  
    //http://120.27.23.105/product/getCarts?uid=3802&source=android  
    @GET("product/deleteCart")  
    Flowable<MessageBean<List<CartBean>>> getDel(@QueryMap Map<String,String> map);  
  
    //添加购物车  
    //http://120.27.23.105/product/addCart?uid=3802&pid=75&source=android  
    @GET("product/addCart")  
    Flowable<MessageBean<List<CartBean>>> getAdd(@QueryMap Map<String,String> map);  
}  