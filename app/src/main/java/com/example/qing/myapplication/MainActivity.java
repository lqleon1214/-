package com.example.qing.myapplication;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.qing.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    Employee employee = new Employee(1,"kgdsjdr");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setEmployee(employee);

    }
}
    