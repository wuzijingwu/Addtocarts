package text.bwei.com.addtocarts.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import text.bwei.com.addtocarts.Main2Activity;
import text.bwei.com.addtocarts.R;
import text.bwei.com.addtocarts.bean.ChildBean;
import text.bwei.com.addtocarts.bean.GroupBean;
import text.bwei.com.addtocarts.view.AddDeleteView;


public class ExpandableAdapter extends BaseExpandableListAdapter {  
    private Context context;  
    private List<GroupBean> groupBeen=new ArrayList<>();
    private List<List<ChildBean>> childBeen=new ArrayList<>();
  
    private Main2Activity main;
  
    public ExpandableAdapter(Context context, List<GroupBean> groupBeen, List<List<ChildBean>> childBeen) {  
        this.context = context;  
        this.groupBeen = groupBeen;  
        this.childBeen = childBeen;  
        this.main = (Main2Activity) context;  
    }  
    //接口回调  
    private onDeleteGoods onDleGoods;  
  
    public interface onDeleteGoods{  
        void onDelGoods(int i,int i1,String pid);  
    }  
  
    public void setOnDeleteGoods(onDeleteGoods onDleGoods){  
        this.onDleGoods=onDleGoods;  
    }  
  
    @Override  
    public int getGroupCount() {  
        return groupBeen.size();  
    }  
  
    @Override  
    public int getChildrenCount(int i) {  
        return childBeen.get(i).size();  
    }  
  
    @Override  
    public Object getGroup(int i) {  
        return groupBeen.get(i).getSellerName();  
    }  
  
    @Override  
    public Object getChild(int i, int i1) {  
        return childBeen.get(i).get(i1).getTitle();  
    }  
  
    @Override  
    public long getGroupId(int i) {  
        return i;  
    }  
  
    @Override  
    public long getChildId(int i, int i1) {  
        return i1;  
    }  
  
    @Override  
    public boolean hasStableIds() {  
        return false;  
    }  
  
    //一级组  
    @Override  
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {  
        //加载视图  
        view=View.inflate(context, R.layout.ex_group_item ,null);
  
        final CheckBox groupCb= view.findViewById(R.id.group_checkbox);  
        TextView shopName= view.findViewById(R.id.shop_name);  
  
  
        shopName.setText(groupBeen.get(i).getSellerName());  
        groupCb.setChecked(groupBeen.get(i).isGropuCb());  
  
        //组复选按钮  
        groupCb.setOnClickListener(new View.OnClickListener() {  
            @Override  
            public void onClick(View view) {  
                boolean gchecked = groupCb.isChecked();  
                groupBeen.get(i).setGropuCb(gchecked);  
                //Main2Activity main= (Main2Activity) context;  
                for(GroupBean i: groupBeen){  
                    boolean gropuCb = i.isGropuCb();  
                    if(!gropuCb){  
                        main.allCheckbox.setChecked(false);  
                        break;  
                    }else{  
                        main.allCheckbox.setChecked(true);  
                    }  
                }  
                int size = childBeen.get(i).size();  
                if(gchecked){  
                    for(int r=0;r<size;r++){  
                        //Toast.makeText(context,"group按钮"+ gchecked+""+size, Toast.LENGTH_SHORT).show();  
                        childBeen.get(i).get(r).setChildCb(true);  
                    }  
                }else{  
                    for(int r=0;r<size;r++){  
                        //Toast.makeText(context,"group按钮"+ gchecked+""+size, Toast.LENGTH_SHORT).show();  
                        childBeen.get(i).get(r).setChildCb(false);  
                    }  
                }  
                notifyDataSetChanged();  
                main.changesum(childBeen);  
            }  
        });  
        return view;  
    }  
  
    //二级组  
    @Override  
    public View getChildView(final int i, final int i1, boolean b, View v, ViewGroup viewGroup) {  
        //加载视图  
        v=View.inflate(context, R.layout.ex_child_item ,null);  
  
        final CheckBox childCb = v.findViewById(R.id.child_checkbox);  
        TextView shopTitle= v.findViewById(R.id.shop_title);  
        TextView shopPrice= v.findViewById(R.id.shop_price);  
        //ImageView shopImg=v.findViewById(R.id.shop_img);  
        //draweeView.setImageURI(uri);  
        SimpleDraweeView shopImg = v.findViewById(R.id.shop_img);  
        final AddDeleteView adv = v.findViewById(R.id.adv);
        Button shop_delete=v.findViewById(R.id.shop_delete);  
  
        childCb.setChecked(childBeen.get(i).get(i1).isChildCb());  
        String images = childBeen.get(i).get(i1).getImages();  
        //Glide.with(context).load(images).into(shopImg);  
        shopImg.setImageURI(images);  
        shopTitle.setText(childBeen.get(i).get(i1).getTitle());  
        shopPrice.setText(childBeen.get(i).get(i1).getPrice()+"");  
        adv.setNumber(childBeen.get(i).get(i1).getNum());  
  
        //final Main2Activity main= (Main2Activity) context;  
        //控制删除按钮的显隐  
        if(childBeen.get(i).get(i1).isBtn()){  
            shop_delete.setVisibility(View.VISIBLE);  
        }else{  
            shop_delete.setVisibility(View.INVISIBLE);  
        }  
        //删除按钮监听  
        shop_delete.setOnClickListener(new View.OnClickListener() {  
            @Override  
            public void onClick(View view) {  
  
                onDleGoods.onDelGoods(i,i1,childBeen.get(i).get(i1).getPid()+"");  
            }  
        });  
  
        //加减器逻辑  
        adv.setOnAddDelClickListener(new AddDeleteView.OnAddDelClickListener() {  
            @Override  
            public void onAddClick(View v) {  
                int number = adv.getNumber();  
                number++;  
                adv.setNumber(number);  
                childBeen.get(i).get(i1).setNum(number);  
                main.changesum(childBeen);  
            }  
  
            @Override  
            public void onDelClick(View v) {  
                int number = adv.getNumber();  
                if(number==1){  
                    Toast.makeText(context,"用户最小数量为1",Toast.LENGTH_SHORT).show();  
                }  
                number--;  
                adv.setNumber(number);  
                childBeen.get(i).get(i1).setNum(number);  
                main.changesum(childBeen);  
            }  
        });  
  
        //二级组的复选框监听  
        childCb.setOnClickListener(new View.OnClickListener() {  
            @Override  
            public void onClick(View view) {  
                boolean flag=false;  
                boolean cchecked = childCb.isChecked();  
                childBeen.get(i).get(i1).setChildCb(cchecked);  
                //Toast.makeText(context,"child按钮"+ cchecked+""+i1, Toast.LENGTH_SHORT).show();  
               // Main2Activity main= (Main2Activity) context;  
                for (List<ChildBean> i1:childBeen){  
                    for(int r=0;r<i1.size();r++) {  
                        boolean childCb1 = i1.get(r).isChildCb();  
                        if(!childCb1){  
                            main.allCheckbox.setChecked(false);  
                            groupBeen.get(i).setGropuCb(false);  
                            flag=true;  
                            break;  
                        }else{  
                            main.allCheckbox.setChecked(true);  
                            groupBeen.get(i).setGropuCb(true);  
                        }  
                    }  
                    if(flag){  
                        break;  
                    }  
                }  
  
                int size = childBeen.get(i).size();  
                for(int x=0;x<size;x++) {  
                    boolean childCb1 = childBeen.get(i).get(x).isChildCb();  
                    if(!childCb1){  
                        groupBeen.get(i).setGropuCb(false);  
                        break;  
                    }else{  
                        groupBeen.get(i).setGropuCb(true);  
                    }  
                }  
                notifyDataSetChanged();  
                main.changesum(childBeen);  
            }  
        });  
  
  
        return v;  
    }  
  
    @Override  
    public boolean isChildSelectable(int i, int i1) {  
        return false;  
    }  
}  