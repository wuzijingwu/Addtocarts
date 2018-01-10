package text.bwei.com.addtocarts.view;

public interface IView {
        void OnSuccess(Object o,String tag);  
        void OnFailed(Exception e,String tag);  
}  