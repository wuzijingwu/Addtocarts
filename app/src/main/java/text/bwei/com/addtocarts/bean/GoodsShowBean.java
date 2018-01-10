package text.bwei.com.addtocarts.bean;

public class GoodsShowBean {
  
        private double bargainPrice;  
        private String createtime;  
        private String detailUrl;  
        private String images;  
        private int itemtype;  
        private int pid;  
        private double price;  
        private int pscid;  
        private int salenum;  
        private int sellerid;  
        private String subhead;  
        private String title;  
  
        public double getBargainPrice() {  
            return bargainPrice;  
        }  
  
        public void setBargainPrice(double bargainPrice) {  
            this.bargainPrice = bargainPrice;  
        }  
  
        public String getCreatetime() {  
            return createtime;  
        }  
  
        public void setCreatetime(String createtime) {  
            this.createtime = createtime;  
        }  
  
        public String getDetailUrl() {  
            return detailUrl;  
        }  
  
        public void setDetailUrl(String detailUrl) {  
            this.detailUrl = detailUrl;  
        }  
  
        public String getImages() {  
            return images;  
        }  
  
        public void setImages(String images) {  
            this.images = images;  
        }  
  
        public int getItemtype() {  
            return itemtype;  
        }  
  
        public void setItemtype(int itemtype) {  
            this.itemtype = itemtype;  
        }  
  
        public int getPid() {  
            return pid;  
        }  
  
        public void setPid(int pid) {  
            this.pid = pid;  
        }  
  
        public double getPrice() {  
            return price;  
        }  
  
        public void setPrice(double price) {  
            this.price = price;  
        }  
  
        public int getPscid() {  
            return pscid;  
        }  
  
        public void setPscid(int pscid) {  
            this.pscid = pscid;  
        }  
  
        public int getSalenum() {  
            return salenum;  
        }  
  
        public void setSalenum(int salenum) {  
            this.salenum = salenum;  
        }  
  
        public int getSellerid() {  
            return sellerid;  
        }  
  
        public void setSellerid(int sellerid) {  
            this.sellerid = sellerid;  
        }  
  
        public String getSubhead() {  
            return subhead;  
        }  
  
        public void setSubhead(String subhead) {  
            this.subhead = subhead;  
        }  
  
        public String getTitle() {  
            return title;  
        }  
  
        public void setTitle(String title) {  
            this.title = title;  
        }  
  
    @Override  
    public String toString() {  
        return "GoodsShowBean{" +  
                "bargainPrice=" + bargainPrice +  
                ", createtime='" + createtime + '\'' +  
                ", detailUrl='" + detailUrl + '\'' +  
                ", images='" + images + '\'' +  
                ", itemtype=" + itemtype +  
                ", pid=" + pid +  
                ", price=" + price +  
                ", pscid=" + pscid +  
                ", salenum=" + salenum +  
                ", sellerid=" + sellerid +  
                ", subhead='" + subhead + '\'' +  
                ", title='" + title + '\'' +  
                '}';  
    }  
}  