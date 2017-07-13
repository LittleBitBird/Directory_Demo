package com.example.guanhuawu.directory_demo.helper;

import com.example.guanhuawu.directory_demo.persist.ContactPerson;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by guanhua.wu on 2017/7/11.
 */

public class MapContactHelper {

    public Map<String, List> contactMap = new HashMap<>();

    public static String[] MAP_KEY = {"a", "b", "c", "d", "e", "f", "g", "h",
            "i", "j", "k", "l", "m", "n", "o", "p",
            "q", "r", "s", "t", "u", "v", "w", "x",
            "y", "z"};

    public void createMap() {

    }

    //依据提供的位置与map获得该位置上的对象；
    public static ContactPerson findPersonByPosition(Map<String, List> contactMap, int position) {
        int destIndex = position;//目的索引
        int nowIndex = 0;//现在位置

        if (position == 0) {//指向第一个
            for (int i = 0; i < MAP_KEY.length; i++) {
                if (contactMap.containsKey(MAP_KEY[i])) {
                    List<ContactPerson> personList = contactMap.get(MAP_KEY[i]);
                    if (personList.size() > 0)
                        return personList.get(0);
                    else return null;
                }
            }
        }

        for (int i = 0; i < MAP_KEY.length; i++) {
            if (contactMap.containsKey(MAP_KEY[i])) {//对26个首字母遍历，如果该key存在进入下一步
                List<ContactPerson> personList = contactMap.get(MAP_KEY[i]);//获得对应key的list
                nowIndex = nowIndex + personList.size();//指针增长
                if (nowIndex == destIndex) {//找到对象，则按照26字母的顺序，获得下一个key的list的第一个对象
                    int j = i + 1;
                    while (!contactMap.containsKey(MAP_KEY[j]) && j < MAP_KEY.length) {
                        j++;
                    }
                    personList = contactMap.get(MAP_KEY[j]);
                    return personList.get(0);
                } else if (destIndex > nowIndex) {//无事发生，继续循环

                } else {//现在的指针超过了目的索引
                    nowIndex = nowIndex - personList.size();//返回上个list的第一个对象处,目的索引一定在这个list中
                    int j = 0;
                    while (j < personList.size()) {//从第一个开始找，知道找到该索引
                        nowIndex++;
                        j++;
                        if (nowIndex == destIndex) {
                            return personList.get(j);
                        }
                    }
                }
            }
        }
        return null;
    }

    public static int getPersonNumber(Map<String, List> contactMap) {
        int personLength = 0;
        for (List<ContactPerson> personList : contactMap.values()) {
            personLength = personLength + personList.size();
        }
        return personLength;
    }

    //list转化为map
    public static Map<String, List> convertFromListToMap(List<ContactPerson> personList) {
        Map<String, List> contactMap = new HashMap<>();
        for (int i = 0; i < MAP_KEY.length; i++) {
            List<ContactPerson> persons = new ArrayList<>();
            for (ContactPerson person : personList) {
                String str = person.getSurName();
                if (Concert.getPingYin(str).startsWith(MAP_KEY[i])) {
                    persons.add(person);
                }
            }
            if (persons.size() > 0) {
                if (persons.size() > 1) {//数目大于1 进行排序
                    persons = sortPersonList(persons);
                }
                contactMap.put(MAP_KEY[i], persons);
            }
        }
        return contactMap;
    }

    //依据ContactPerson中的surName的拼音给personList排序
    public static List<ContactPerson> sortPersonList(List<ContactPerson> personList) {
        Collections.sort(personList, new Comparator<ContactPerson>() {
            @Override
            public int compare(ContactPerson t0, ContactPerson t1) {
                String o1 = "";
                String o2 = "";
                if (t0 != null) {
                    String str = t0.getSurName();
                    o1 = str;
                }
                if (t1 != null) {
                    String str = t1.getSurName();
                    o2 = str;
                }
                Collator instance = Collator.getInstance(Locale.CHINA);
                return instance.compare(o1, o2);//o1 o2不能为null
            }
        });
        return personList;
    }

    //依据key获得顺序定位
    public static int getIndexofKey(String key, Map<String, List> contactMap) {
        int index = 0;
        for (int i = 0; i < MAP_KEY.length; i++) {
            if (contactMap.containsKey(MAP_KEY[i])) {
                if (key.equals(MAP_KEY[i])) {
                    break;
                }
                index = index + contactMap.get(MAP_KEY[i]).size();
            }
        }
        return index;
    }

}
