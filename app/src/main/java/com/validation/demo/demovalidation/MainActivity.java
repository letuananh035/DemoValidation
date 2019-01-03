package com.validation.demo.demovalidation;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.validation.demo.demovalidation.Custom.Customer;
import com.validation.demo.demovalidation.Custom.Email;
import com.validation.demo.demovalidation.Custom.EmailProcess;

import core.Validation;
import core.process.FactoryProcess;
import core.result.Result;
import core.result.ResultCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FactoryProcess.register(Email.class, EmailProcess.class);

        setContentView(R.layout.activity_main);
        final TextView textName = findViewById(R.id.textName);
        final TextView textAge = findViewById(R.id.textAge);
        final TextView textEmail = findViewById(R.id.textEmail);

        final EditText editName = findViewById(R.id.edit_name);
        final EditText editAge = findViewById(R.id.edit_age);
        final EditText editEmail = findViewById(R.id.edit_email);

        Button btn = findViewById(R.id.submit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer age = 0;

                try{
                    age = Integer.valueOf(editAge.getText().toString());
                }catch (Exception e){

                }

                Customer customer = new Customer(editName.getText().toString(), age );
                customer.email = editEmail.getText().toString();
                customer.onValidated("name", new ResultCallback() {
                    @Override
                    public void update(Result result) {
                        if(result.isFailed()){
                            textName.setTextColor(Color.RED);
                        }else{
                            textName.setTextColor(Color.BLACK);
                        }
                    }
                });
                customer.onValidated("age", new ResultCallback() {
                    @Override
                    public void update(Result result) {
                        if(result.isFailed()){
                            textAge.setTextColor(Color.RED);
                        }else{
                            textAge.setTextColor(Color.BLACK);
                        }
                    }
                });
                customer.onValidated("email", new ResultCallback() {
                    @Override
                    public void update(Result result) {
                        if(result.isFailed()){
                            textEmail.setTextColor(Color.RED);
                        }else{
                            textEmail.setTextColor(Color.BLACK);
                        }
                    }
                });
                Validation.getInstance().runObserver(customer);
            }
        });

    }
}
