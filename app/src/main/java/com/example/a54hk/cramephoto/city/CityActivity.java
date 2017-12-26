package com.example.a54hk.cramephoto.city;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.a54hk.cramephoto.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        //        从Assets中读取
        String phoneCodeString = getFromAssets("country.json");
//        通过GSON 解析成实体类的形式
        Gson gson = new Gson();
        Bean bean = gson.fromJson(phoneCodeString ,Bean.class);

    }
    /**
     * 从assets读取
     */
    public String getFromAssets(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
