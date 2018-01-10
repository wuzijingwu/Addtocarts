package text.bwei.com.addtocarts.bean;

public class MessageBean <T>{
  
  
    private String code;  
    private T data;  
    private String msg;  
    private SellerBean seller;  
  
    public String getCode() {  
        return code;  
    }  
  
    public void setCode(String code) {  
        this.code = code;  
    }  
  
    public T getData() {  
        return data;  
    }  
  
    public void setData(T data) {  
        this.data = data;  
    }  
  
    public String getMsg() {  
        return msg;  
    }  
  
    public void setMsg(String msg) {  
        this.msg = msg;  
    }  
  
    public SellerBean getSeller() {  
        return seller;  
    }  
  
    public void setSeller(SellerBean seller) {  
        this.seller = seller;  
    }  
  
  
  
    public static class SellerBean {  
        /** 
         * description : 我是商家19 
         * icon : http://120.27.23.105/images/icon.png 
         * name : 商家19 
         * productNums : 999 
         * score : 5.0 
         * sellerid : 19 
         */  
  
        private String description;  
        private String icon;  
        private String name;  
        private int productNums;  
        private double score;  
        private int sellerid;  
  
        public String getDescription() {  
            return description;  
        }  
  
        public void setDescription(String description) {  
            this.description = description;  
        }  
  
        public String getIcon() {  
            return icon;  
        }  
  
        public void setIcon(String icon) {  
            this.icon = icon;  
        }  
  
        public String getName() {  
            return name;  
        }  
  
        public void setName(String name) {  
            this.name = name;  
        }  
  
        public int getProductNums() {  
            return productNums;  
        }  
  
        public void setProductNums(int productNums) {  
            this.productNums = productNums;  
        }  
  
        public double getScore() {  
            return score;  
        }  
  
        public void setScore(double score) {  
            this.score = score;  
        }  
  
        public int getSellerid() {  
            return sellerid;  
        }  
  
        public void setSellerid(int sellerid) {  
            this.sellerid = sellerid;  
        }  
    }  
}  