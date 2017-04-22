package com.weidongjian.meitu.wheelviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2017/4/22.
 */

public class DateActivity extends Activity {
    private LoopView lv_year,lv_month,lv_day;
    private List<String> listYear,listMonth,listDay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.date);
        lv_year= (LoopView) this.findViewById(R.id.lv_year);
        lv_month= (LoopView) this.findViewById(R.id.lv_month);
        lv_day= (LoopView) this.findViewById(R.id.lv_day);
        listYear=new ArrayList<>();
        listMonth=new ArrayList<>();
        listDay=new ArrayList<>();
        for(int i=1897;i<2018;i++){
            listYear.add(i+"年");
        }
        lv_year.setItems(listYear);
        for(int i=1;i<13;i++){
            listMonth.add(i+"月");
        }
        lv_month.setItems(listMonth);
        lv_year.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {

            }
        });
        lv_month.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {

            }
        });
        for(int i=1;i<30;i++){
            listDay.add(i+"日");
        }

        lv_day.setItems(listDay);
    }
    private int getDay(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2010); // 2010年
        c.set(Calendar.MONTH, 5); // 6 月
        System.out.println("------------" + c.get(Calendar.YEAR) + "年" + (c.get(Calendar.MONTH) + 1) + "月的天数和周数-------------");
        System.out.println("天数：" + c.getActualMaximum(Calendar.DAY_OF_MONTH));
        System.out.println("周数：" + c.getActualMaximum(Calendar.WEEK_OF_MONTH));
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
