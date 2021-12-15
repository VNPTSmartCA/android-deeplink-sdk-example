package com.vnpt.egov.testsmartca;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.vnpt.egov.vnptsmartcaandroidsdk.ParameterNameTransaction;
import com.vnpt.egov.vnptsmartcaandroidsdk.Transaction;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MappingActivity extends AppCompatActivity {
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tvEnvironment)
    TextView tvEnvironment;
    @SuppressLint({"NonTConstantResourceId", "NonConstantResourceId"})
    @BindView(R.id.tvClientId)
    TextView tvClientId;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tvTranId)
    TextView tvTranId;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tvStatusCode)
    TextView tvStatusCode;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tvMessage)
    TextView tvMessage;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btnMappingVNPTSmartCA)
    Button btnMappingVNPTSmartCA;

    private final String tranId = "38d0a6fb-e014-4633-8f95-7d31616a1bce";
    private final String clientId = "partnerId01";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapping);
        ButterKnife.bind(this);
        /*
        * You need choose environment to develop.
        * Have 2 choices: DEVELOPMENT and PRODUCTION
        * */
        Transaction.getInstance().setEnvironment(Transaction.ENVIRONMENT.PRODUCTION);
        tvEnvironment.setText("Production Environment");
        tvClientId.setText("Client ID: " + clientId);
        tvTranId.setText("Transaction ID: " + tranId);
    }

    private void requestMapping() {
        Map<String, String> eventValue = new HashMap<>();
        //Client Required
        eventValue.put(ParameterNameTransaction.CLIENT_ID, clientId);
        eventValue.put(ParameterNameTransaction.TRAN_ID, tranId);
        Transaction.getInstance().requestVNPTSmartCACallback(this, eventValue);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btnMappingVNPTSmartCA)
    public void onViewClicked() {
        requestMapping();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Transaction.getInstance().REQUEST_CODE_VNPT_SMARTCA && resultCode == 0) {
            tvStatusCode.setText("Status Code: " + data.getExtras().getInt("status"));
            tvMessage.setText("Message: " + data.getExtras().getString("message"));
        } else {
            tvMessage.setText("message: " + this.getString(R.string.not_receive_info_err));
        }
    }
}
