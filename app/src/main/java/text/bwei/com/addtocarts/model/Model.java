package text.bwei.com.addtocarts.model;


import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import text.bwei.com.addtocarts.bean.CartBean;
import text.bwei.com.addtocarts.bean.GoodsShowBean;
import text.bwei.com.addtocarts.bean.MessageBean;
import text.bwei.com.addtocarts.presenter.NewsPresenter;
import text.bwei.com.addtocarts.utils.RetrofitUtils;

public class Model implements IModel{  
  
    private NewsPresenter presenter;
    public Model(NewsPresenter presenter) {  
        this.presenter = presenter;  
    }  
  
    @Override  
    public void getData(Map<String, String> map, String tag) {  
        if(tag.equals("cart")){  
            Flowable<MessageBean<List<CartBean>>> flowable = RetrofitUtils.getInstance().getApiService().getCart(map);
            presenter.get2(flowable,tag);  
        }else if(tag.equals("goods")){  
            Flowable<MessageBean<GoodsShowBean>> flowable = RetrofitUtils.getInstance().getApiService().getNews(map);
            presenter.get(flowable,tag);  
        }else if(tag.equals("del")){  
            Flowable<MessageBean<List<CartBean>>> flowable = RetrofitUtils.getInstance().getApiService().getDel(map);
            presenter.get3(flowable,tag);  
        }else if(tag.equals("add")){  
            Flowable<MessageBean<List<CartBean>>> flowable = RetrofitUtils.getInstance().getApiService().getAdd(map);  
            presenter.get4(flowable,tag);  
        }  
  
    }  
}  