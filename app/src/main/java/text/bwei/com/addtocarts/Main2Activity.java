package text.bwei.com.addtocarts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import text.bwei.com.addtocarts.adapter.ExpandableAdapter;
import text.bwei.com.addtocarts.bean.CartBean;
import text.bwei.com.addtocarts.bean.ChildBean;
import text.bwei.com.addtocarts.bean.GroupBean;
import text.bwei.com.addtocarts.presenter.NewsPresenter;
import text.bwei.com.addtocarts.view.IView;

public class Main2Activity extends AppCompatActivity implements IView {
  
    @BindView(R.id.exListView)  
    ExpandableListView exListView;  
    @BindView(R.id.all_chekbox)  
    public CheckBox allCheckbox;  
    @BindView(R.id.total_price)  
    TextView totalPrice;  
    @BindView(R.id.total_number)  
    TextView totalnumber;  
    @BindView(R.id.tv_go_to_pay)  
    TextView tvGoToPay;  
    @BindView(R.id.tv_go_to_del)  
    TextView tvGoToDel;  
  
    private ExpandableAdapter expandableAdapter;
  
    private boolean flagedit = true;  
    private boolean flagdel = false;  
  
  
    private NewsPresenter presenter;
  
    List<GroupBean> groupBeen = new ArrayList<>();
    List<List<ChildBean>> childBeen = new ArrayList<>();
  
    int i;  
    int i1;  
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main2);  
        ButterKnife.bind(this);  
  
        //获取二级列表适配器  
        expandableAdapter = new ExpandableAdapter(Main2Activity.this, groupBeen, childBeen);  
        exListView.setAdapter(expandableAdapter);  
  
        exListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {  
            @Override  
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {  
                return true;  
            }  
        });  
  
  
        presenter = new NewsPresenter();  
        presenter.attachView(this);  
        //http://120.27.23.105/product/getCarts?uid=3802&source=android  
        Map<String, String> map = new HashMap<>();  
        map.put("uid", "3802");  
        presenter.getData(map, "cart");  
  
  
        expandableAdapter.setOnDeleteGoods(new ExpandableAdapter.onDeleteGoods() {  
            @Override  
            public void onDelGoods(int i, int i1, String pid) {  
                Main2Activity.this.i = i;  
                Main2Activity.this.i1 = i1;  
  
                Map<String, String> map = new HashMap<>();  
                map.put("uid", "3802");  
                map.put("pid", pid);  
                presenter.getData(map, "del");  
  
            }  
        });  
  
    }  
  
  
    @Override  
    public void OnSuccess(Object o, String tag) {  
        if (tag.equals("cart")) {  
            if (o != null) {  
                List<CartBean> data = (List<CartBean>) o;
                for (CartBean i : data) {  
                    GroupBean groupBean = new GroupBean(i.getSellerName(), false);  
                    this.groupBeen.add(groupBean);  
                    List<CartBean.ListBean> list = i.getList();  
                    List<ChildBean> ls = new ArrayList<>();  
                    for (CartBean.ListBean w : list) {  
                        String[] split = w.getImages().split("\\|");  
                        ChildBean childBean = new ChildBean(w.getTitle(), split[0], w.getPrice(), 1, false, false, w.getPid());  
                        ls.add(childBean);  
                    }  
                    this.childBeen.add(ls);  
  
                }  
                for (int i = 0; i < expandableAdapter.getGroupCount(); i++) {  
                    exListView.expandGroup(i);  
                }  
            }  
        } else if (tag.equals("del")) {  
            if (o != null) {  
                String msg = (String) o;  
  
                if (this.i != -1 && this.i1 != -1) {  
                    int size = childBeen.get(i).size();  
                    if (size == 1) {  
                        childBeen.remove(i);  
                        groupBeen.remove(i);  
                    } else {  
                        childBeen.get(i).remove(i1);  
                    }  
                      
                    changesum(childBeen);  
  
                    this.i = -1;  
                    this.i1 = -1;  
                    if(flagdel){  
                        delGoods();  
                    }  
                }  
            }  
        }  
        expandableAdapter.notifyDataSetChanged();  
    }  
  
    @Override  
    public void OnFailed(Exception e, String tag) {  
          
    }  
  
    @OnClick({R.id.back, R.id.edit, R.id.all_chekbox,R.id.tv_go_to_del})  
    public void onViewClicked(View view) {  
        switch (view.getId()) {  
            case R.id.back:  
                finish();  
                break;  
            case R.id.edit:  
                if(flagedit){  
                    tvGoToPay.setVisibility(View.GONE);  
                    tvGoToDel.setVisibility(View.VISIBLE);  
                }else{  
                    tvGoToPay.setVisibility(View.VISIBLE);  
                    tvGoToDel.setVisibility(View.GONE);  
                }  
  
                for (List<ChildBean> i1 : childBeen) {  
                    for (int r = 0; r < i1.size(); r++) {  
                        i1.get(r).setBtn(flagedit);  
                    }  
                }  
                flagedit = !flagedit;  
                expandableAdapter.notifyDataSetChanged();  
                break;  
            case R.id.all_chekbox:  
                boolean checked = allCheckbox.isChecked();  
                //改变一级item复选框  
                for (GroupBean i : groupBeen) {  
                    i.setGropuCb(checked);  
                }  
                //改变二级item复选框  
                for (List<ChildBean> i1 : childBeen) {  
                    for (int r = 0; r < i1.size(); r++) {  
                        i1.get(r).setChildCb(checked);  
                    }  
                }  
                expandableAdapter.notifyDataSetChanged();  
                changesum(childBeen);  
                break;  
            case R.id.tv_go_to_del:  
                if(childBeen.size()!=0){  
                    for (List<ChildBean> i1 : childBeen) {  
                        for (int r = 0; r < i1.size(); r++) {  
                            boolean childCb1 = i1.get(r).isChildCb();  
                            if (childCb1) {  
                                flagdel=true;  
                                delGoods();  
                                if(allCheckbox.isChecked()){  
                                    allCheckbox.setChecked(false);  
                                }  
                                break;  
                            }  
                        }  
                        if(flagdel){  
                            break;  
                        }  
                    }  
                }  
                //Toast.makeText(Main2Activity.this,flagdel+"",Toast.LENGTH_SHORT).show();  
                break;  
        }  
    }  
  
    //递归删除  
    private void delGoods(){  
        Boolean flag=false;  
        for(int p=0;p<childBeen.size();p++) {  
            for (int r = 0; r < childBeen.get(p).size(); r++) {  
                boolean childCb1 = childBeen.get(p).get(r).isChildCb();  
                if(p==childBeen.size()-1&&r==childBeen.get(p).size()-1){  
                    flagdel=false;  
                }  
                if (childCb1) {  
                    int pid = childBeen.get(p).get(r).getPid();  
                    this.i = p;  
                    this.i1 = r;  
                    Map<String, String> map = new HashMap<>();  
                    map.put("uid", "3802");  
                    map.put("pid", pid+"");  
                    presenter.getData(map, "del");  
                    flag=!flag;  
                    break;  
                }  
            }  
            if(flag){  
                break;  
            }  
        }  
    }  
      
    //计算和数量总价  
    public void changesum(List<List<ChildBean>> childBeen) {  
        int count = 0;  
        double sum = 0;  
        for (List<ChildBean> i1 : childBeen) {  
            for (int r = 0; r < i1.size(); r++) {  
                boolean childCb1 = i1.get(r).isChildCb();  
                if (childCb1) {  
                    double price = i1.get(r).getPrice();  
                    int num = i1.get(r).getNum();  
                    sum += price * num;  
                    count++;  
                }  
            }  
        }  
        totalPrice.setText("￥" + sum);  
        totalnumber.setText("共有商品:" + count + "件");  
    }  
}  