package com.example.mobileappfinal.GUI_layer.Pay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileappfinal.DTO.Pay;
import com.example.mobileappfinal.Data_layer.Cart.CartDatabase;
import com.example.mobileappfinal.Data_layer.Pay.PayDatabase;
import com.example.mobileappfinal.GUI_layer.Home.HomeActivity;
import com.example.mobileappfinal.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PaymentActivity extends AppCompatActivity {

    private ImageView btnBack;
    private EditText etUserName, etUserAddress, etUserPhoneNumber;
    private RadioGroup rgPaymentMethod;
    private RadioButton rbBankTransfer;
    private RadioButton rbCOD;
    private Button btnSubmit;

    private CartDatabase cartDatabase;
    private PayDatabase payDatabase;

    private List<Map<String, Object>> pays = new ArrayList<>();
    private List<String> product_ids = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        cartDatabase = new CartDatabase();
        payDatabase = new PayDatabase();

        setAndGetAllView();
        setEventClickButtonSubmit();
    }

    private void setEventClickButtonSubmit() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get values from the form
                String fullName = etUserName.getText().toString();
                String address = etUserAddress.getText().toString();
                String phoneNumber = etUserPhoneNumber.getText().toString();

                int selectedPaymentMethodId = rgPaymentMethod.getCheckedRadioButtonId();
                String paymentMethod = "";
                if (selectedPaymentMethodId == rbBankTransfer.getId()) {
                    paymentMethod = "Chuyển khoản";
                } else if (selectedPaymentMethodId == rbCOD.getId()) {
                    paymentMethod = "COD";
                }

                // Check if any of the fields is empty
                if (fullName.isEmpty() || address.isEmpty() || phoneNumber.isEmpty() || paymentMethod.isEmpty()) {
                    Toast.makeText(PaymentActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    for (Map<String, Object> pay : pays){
                        payDatabase.addPay(pay);
                    }

                    for (String product_id : product_ids){
                        cartDatabase.deleteCartItem(product_id);
                    }

                    Toast.makeText(PaymentActivity.this, "Order Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PaymentActivity.this, HomeActivity.class));
                }
            }
        });

    }

    private void setAndGetAllView() {
        etUserName = findViewById(R.id.etFullName);
        etUserAddress = findViewById(R.id.etAddress);
        etUserPhoneNumber = findViewById(R.id.etPhoneNumber);

        rgPaymentMethod = findViewById(R.id.rgPaymentMethod);
        rbBankTransfer = findViewById(R.id.rbBankTransfer);
        rbCOD = findViewById(R.id.rbCOD);

        btnSubmit = findViewById(R.id.btnSubmit);

        Intent intent = getIntent();
        pays = (List<Map<String, Object>>) intent.getSerializableExtra("pays");
        product_ids = intent.getStringArrayListExtra("product_ids");
    }
}
