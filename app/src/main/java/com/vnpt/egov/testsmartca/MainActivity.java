package com.vnpt.egov.testsmartca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rdEnvironmentProduction)
    RadioButton rdEnvironmentProduction;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rdGroupEnvironment)
    RadioGroup rdGroupEnvironment;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btnMappingVNPTSmartCA)
    Button btnMappingVNPTSmartCA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnMappingVNPTSmartCA})
    public void onViewClicked(@NonNull View view) {
        Intent intent;
        Bundle data = new Bundle();
        if (view.getId() == R.id.btnMappingVNPTSmartCA) {
            intent = new Intent(MainActivity.this, MappingActivity.class);
            data.putString("activity", MainActivity.this.getLocalClassName());
            intent.putExtras(data);
            startActivity(intent);
        }
    }
}