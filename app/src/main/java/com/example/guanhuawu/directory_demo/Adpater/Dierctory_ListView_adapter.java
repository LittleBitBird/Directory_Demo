package com.example.guanhuawu.directory_demo.Adpater;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.guanhuawu.directory_demo.R;
import com.example.guanhuawu.directory_demo.persist.Contact_person;

import java.util.List;

/**
 * Created by guanhua.wu on 2017/7/6.
 */

public class Dierctory_ListView_adapter extends BaseAdapter {
    Context mContext;
    List<Contact_person> personList = null;

    public Dierctory_ListView_adapter(Context context, List list) {
        mContext = context;
        this.personList = list;
        Log.e("Length", "Dierctory_ListView_adapter: "+list.size() );
    }

    @Override
    public int getCount() {
        return personList.size();
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
            holder.Char_Index = (TextView)convertView.findViewById(R.id.Char_Index);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
            holder.Char_Index.setVisibility(View.GONE);
            holder.Member.setText(personList.get(i).getSurname().substring(1)+""+personList.get(i).getFirst_name());

            holder.Char_Index = Show_Index(i,holder.Char_Index);
        return  convertView;
    }

    public TextView Show_Index(int i,TextView Char_Index){
        String now_FirstChar = personList.get(i).getSurname().substring(0,1);
        String last_FirstChar;
        if(i==0){
            Char_Index.setVisibility(View.VISIBLE);
            Char_Index.setText(now_FirstChar.toUpperCase());
        }else {
            last_FirstChar = personList.get(i-1).getSurname().substring(0,1);
            if(now_FirstChar.equals(last_FirstChar)){
                //首字母相同什么都不做
            }else{
                //首字母不同，添加Index
                Char_Index.setVisibility(View.VISIBLE);
                Char_Index.setText(now_FirstChar.toUpperCase());
            }
        }

        return  Char_Index;
    }
}

class ViewHolder {
    TextView Member;
    TextView Char_Index;
}
