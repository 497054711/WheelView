package com.weidongjian.meitu.wheelviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/22.
 */

public class ProvinceCityActivity extends Activity {
    private LoopView lv_province,lv_city;
    private ArrayList<String> listProvinces;
    private ArrayList<String> listCitys;
    private String result;
    private ProvinceCity provinceCity;
    private List<ProvinceCity.Sub> subSelector;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.province_city);
        lv_province= (LoopView) this.findViewById(R.id.lv_province);
        lv_city= (LoopView) this.findViewById(R.id.lv_city);
        listProvinces=new ArrayList<>();
        listCitys=new ArrayList<>();

        InputStream is = null;
        try {
            is = getAssets().open("ProvinceCity.txt");
            int lenght = is.available();
            byte[]  buffer = new byte[lenght];
            is.read(buffer);
            result = new String(buffer, "utf8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        provinceCity= new Gson().fromJson(result,ProvinceCity.class);
        listProvinces.clear();
        for(ProvinceCity.Provinces provinces:provinceCity.getProvincesCitys()){
            listProvinces.add(provinces.getName());
        }
        lv_province.setNotLoop();
        lv_province.setItems(listProvinces);
        lv_city.setNotLoop();
        listCitys.clear();
        for(ProvinceCity.Sub subItem:provinceCity.getProvincesCitys().get(0).getSub()){
            listCitys.add(subItem.getName());
        }
        lv_city.setItems(listCitys);
        lv_province.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                subSelector=provinceCity.getProvincesCitys().get(index).getSub();
                listCitys.clear();
                Log.i("index==","size=="+provinceCity.getProvincesCitys().size()+"==index=="+index+"");
                if(subSelector==null){
                    return;
                }
                for(ProvinceCity.Sub subItem:subSelector){
                    listCitys.add(subItem.getName());
                }
                lv_city.setCurrentPosition(0);
                lv_city.setItems(listCitys);
            }
        });
    }

    public class ProvinceCity{
        private List<Provinces> provincesCitys;

        public List<Provinces> getProvincesCitys() {
            return provincesCitys;
        }

        public void setProvincesCitys(List<Provinces> provincesCitys) {
            this.provincesCitys = provincesCitys;
        }

        public class Provinces{
            private String name;
            private List<Sub> sub;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<Sub> getSub() {
                return sub;
            }

            public void setSub(List<Sub> sub) {
                this.sub = sub;
            }
        }
        public class Sub{
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
