package text.bwei.com.addtocarts.presenter;


import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import text.bwei.com.addtocarts.bean.CartBean;
import text.bwei.com.addtocarts.bean.GoodsShowBean;
import text.bwei.com.addtocarts.bean.MessageBean;
import text.bwei.com.addtocarts.model.Model;
import text.bwei.com.addtocarts.view.IView;

public class NewsPresenter implements IPresenter{  
  
    private IView iView;
    private DisposableSubscriber<MessageBean<GoodsShowBean>> subscriber;
    private DisposableSubscriber<MessageBean<List<CartBean>>> subscriber2;
    private DisposableSubscriber<MessageBean<List<CartBean>>> subscriber3;  
    private DisposableSubscriber<MessageBean<List<CartBean>>> subscriber4;  
  
    public void attachView(IView iView){  
        this.iView=iView;  
    }  
  
    @Override  
    public void getData(Map<String, String> map, String tag) {  
        Model model = new Model(this);
        model.getData(map,tag);  
    }  
  
    public void detachView(){  
        if(iView!=null){  
            iView=null;  
        }  
        if(subscriber!=null){  
            if(!subscriber.isDisposed()){  
                subscriber.dispose();  
            }  
        }  
        if(subscriber2!=null){  
            if(!subscriber2.isDisposed()){  
                subscriber2.dispose();  
            }  
        }  
        if(subscriber3!=null){  
            if(!subscriber3.isDisposed()){  
                subscriber3.dispose();  
            }  
        }  
        if(subscriber4!=null){  
            if(!subscriber4.isDisposed()){  
                subscriber4.dispose();  
            }  
        }  
    }  
  
    //查询购物车  
    public void get(Flowable<MessageBean<GoodsShowBean>> flowable , final String tag) {  
        subscriber = flowable.subscribeOn(Schedulers.io())  
                .observeOn(AndroidSchedulers.mainThread())  
                .subscribeWith(new DisposableSubscriber<MessageBean<GoodsShowBean>>() {  
                    @Override  
                    public void onNext(MessageBean<GoodsShowBean> listMessageBean) {  
                        if (listMessageBean != null) {  
                            GoodsShowBean data = listMessageBean.getData();  
                            iView.OnSuccess(data,tag);  
                        }  
                    }  
  
                    @Override  
                    public void onError(Throwable t) {  
                        iView.OnFailed(new Exception(t),tag);  
                    }  
  
                    @Override  
                    public void onComplete() {  
  
                    }  
                });  
    }  
  
    //查询商品详情75  
    public void get2(Flowable<MessageBean<List<CartBean>>> flowable, final String tag) {  
        subscriber2 = flowable.subscribeOn(Schedulers.io())  
                .observeOn(AndroidSchedulers.mainThread())  
                .subscribeWith(new DisposableSubscriber<MessageBean<List<CartBean>>>() {  
                    @Override  
                    public void onNext(MessageBean<List<CartBean>> list) {  
                        if (list != null) {  
                            List<CartBean> data = list.getData();  
                            if(data!=null){  
                                iView.OnSuccess(data,tag);  
                            }  
                        }  
                    }  
  
                    @Override  
                    public void onError(Throwable t) {  
                        iView.OnFailed(new Exception(t),tag);  
                    }  
  
                    @Override  
                    public void onComplete() {  
  
                    }  
                });  
    }  
  
    //删除购物车  
    public void get3(Flowable<MessageBean<List<CartBean>>> flowable, final String tag) {  
        subscriber3 = flowable.subscribeOn(Schedulers.io())  
                .observeOn(AndroidSchedulers.mainThread())  
                .subscribeWith(new DisposableSubscriber<MessageBean<List<CartBean>>>() {  
                    @Override  
                    public void onNext(MessageBean<List<CartBean>> list) {  
                        if (list != null) {  
                            String code = list.getMsg();  
                            iView.OnSuccess(code,tag);  
                        }  
                    }  
  
                    @Override  
                    public void onError(Throwable t) {  
                        iView.OnFailed(new Exception(t),tag);  
                    }  
  
                    @Override  
                    public void onComplete() {  
  
                    }  
                });  
    }  
  
    //添加购物车  
    public void get4(Flowable<MessageBean<List<CartBean>>> flowable, final String tag) {  
        subscriber4 = flowable.subscribeOn(Schedulers.io())  
                .observeOn(AndroidSchedulers.mainThread())  
                .subscribeWith(new DisposableSubscriber<MessageBean<List<CartBean>>>() {  
                    @Override  
                    public void onNext(MessageBean<List<CartBean>> list) {  
                        if (list != null) {  
                            String code = list.getMsg();  
                            iView.OnSuccess(code,tag);  
                        }  
                    }  
  
                    @Override  
                    public void onError(Throwable t) {  
                        iView.OnFailed(new Exception(t),tag);  
                    }  
  
                    @Override  
                    public void onComplete() {  
  
                    }  
                });  
    }  
}  