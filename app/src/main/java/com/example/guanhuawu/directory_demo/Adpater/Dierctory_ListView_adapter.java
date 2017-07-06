package com.example.guanhuawu.directory_demo.Adpater;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.guanhuawu.directory_demo.R;

import java.util.List;

/**
 * Created by guanhua.wu on 2017/7/6.
 */

public class Dierctory_ListView_adapter extends BaseAdapter {
    Context mContext;
    List list = null;

    public Dierctory_ListView_adapter(Context context, List list) {
        mContext = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        Log.e("12", "getCount: "+list.size() );
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item1,null);
            holder.Member = (TextView)convertView.findViewById(R.id.Member);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
            holder.Member.setText("123");
        return  convertView;
    }
}

class ViewHolder {
    TextView Member;
}
