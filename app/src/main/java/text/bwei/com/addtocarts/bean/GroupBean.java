package text.bwei.com.addtocarts.bean;

public class GroupBean {
    private String sellerName;  
    private boolean gropuCb;  
  
    public GroupBean(String sellerName, boolean gropuCb) {  
        this.sellerName = sellerName;  
        this.gropuCb = gropuCb;  
    }  
  
    public String getSellerName() {  
        return sellerName;  
    }  
  
    public void setSellerName(String sellerName) {  
        this.sellerName = sellerName;  
    }  
  
    public boolean isGropuCb() {  
        return gropuCb;  
    }  
  
    public void setGropuCb(boolean gropuCb) {  
        this.gropuCb = gropuCb;  
    }  
  
    @Override  
    public String toString() {  
        return "GroupBean{" +  
                "sellerName='" + sellerName + '\'' +  
                ", gropuCb=" + gropuCb +  
                '}';  
    }  
} 