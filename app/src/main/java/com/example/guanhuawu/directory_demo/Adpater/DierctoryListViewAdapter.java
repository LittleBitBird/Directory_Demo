package com.example.guanhuawu.directory_demo.Adpater;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.guanhuawu.directory_demo.Helper.Concert;
import com.example.guanhuawu.directory_demo.Helper.FirstMapDemo;
import com.example.guanhuawu.directory_demo.R;
import com.example.guanhuawu.directory_demo.persist.ContactPerson;

import java.util.List;
import java.util.Map;

/**
 * Created by guanhua.wu on 2017/7/6.
 */

public class DierctoryListViewAdapter extends BaseAdapter {
    Context mContext;
    List<ContactPerson> personList = null;
    Map<String, List> contactMap = null;

    public DierctoryListViewAdapter(Context context, Map map) {
        mContext = context;
        this.contactMap = map;
        Log.e("Length", "DierctoryListViewAdapter: " + FirstMapDemo.getPersonNumber(contactMap));
    }

    @Override
    public int getCount() {
        return FirstMapDemo.getPersonNumber(contactMap);
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
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item1, null);
            holder.member = (TextView) convertView.findViewById(R.id.tvMember);
            holder.charIndex = (TextView) convertView.findViewById(R.id.tvCharIndex);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.charIndex.setVisibility(View.GONE);
        ContactPerson person = FirstMapDemo.findPersonByPosition(contactMap, i);
        holder.member.setText(person.getSurName() + "" + person.getFirstName());
        holder.charIndex = showIndex(i, holder.charIndex);
        return convertView;
    }

    public TextView showIndex(int i, TextView charIndex) {
        ContactPerson nowPerson = FirstMapDemo.findPersonByPosition(contactMap, i);
        ContactPerson lastPerson = FirstMapDemo.findPersonByPosition(contactMap, i - 1);

        String nowFirstChar = Concert.getPingYin(nowPerson.getSurName()).substring(0, 1);
        String lastFirstChar;
        if (i == 0) {
            charIndex.setVisibility(View.VISIBLE);
            charIndex.setText(nowFirstChar.toUpperCase());
        } else {
            lastFirstChar = Concert.getPingYin(lastPerson.getSurName()).substring(0, 1);
            if (nowFirstChar.equals(lastFirstChar)) {
                //首字母相同什么都不做
            } else {
                //首字母不同，添加Index
                charIndex.setVisibility(View.VISIBLE);
                charIndex.setText(nowFirstChar.toUpperCase());
            }
        }
        return charIndex;
    }
}

class ViewHolder {
    TextView member;
    TextView charIndex;
}
