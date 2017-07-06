package com.example.guanhuawu.directory_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Directory extends AppCompatActivity {
    @BindView(R.id.BeginText)
    TextView BeginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.BeginText)
    public void Test(){
        BeginText.setText("ButterKnife");
    }
}
