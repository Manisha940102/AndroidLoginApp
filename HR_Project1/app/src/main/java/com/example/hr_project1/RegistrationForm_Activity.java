package com.example.hr_project1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationForm_Activity extends AppCompatActivity {
    private EditText name;
    private EditText password;
    private EditText repassword;
    private EditText email;
    private Button reg;
    private int stat = 1;
    String AES = "AES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        name = findViewById(R.id.etUserName);
        password = findViewById(R.id.etUserPassword);
        repassword = findViewById(R.id.etRepass);
        email = findViewById(R.id.etEmail);
        reg = findViewById(R.id.btnRegister);

        addListenerOnButton();
    }

    private void addListenerOnButton() {
        final Context context = this;
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().trim().length() == 0 ){
                    Toast.makeText(context, getString(R.string.empty_name), Toast.LENGTH_LONG).show();
                }
                else if( password.getText().toString().trim().length() == 0){
                    Toast.makeText(context, getString(R.string.empty_password), Toast.LENGTH_LONG).show();
                }
                else if(repassword.getText().toString().trim().length() == 0){
                    Toast.makeText(context, getString(R.string.empty_Repassword), Toast.LENGTH_LONG).show();
                }
                else if(email.getText().toString().trim().length() == 0){
                    Toast.makeText(context, getString(R.string.empty_email), Toast.LENGTH_LONG).show();
                }
                else if( ! password.getText().toString().equals(repassword.getText().toString())){
                    Toast.makeText(context, getString(R.string.password), Toast.LENGTH_LONG).show();
                }
                else if( password.getText().toString().equals(repassword.getText().toString())){
                    try {
                        String pass = Encrypt.encrypt(password.getText().toString());
                        createAddUser(pass);
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
                else
                    Toast.makeText(context, getString(R.string.field), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void createAddUser(String pass) {
        final Context context = this;

        AddUser adduser = new AddUser(name.getText().toString(),pass.trim(),email.getText().toString());

        Call<Register_Add> call = RetrofitClientInstance.getRetrofitInstance().create(Login_Api.class).createAddUser(adduser);

        call.enqueue(new Callback<Register_Add>() {
            @Override
            public void onResponse(Call<Register_Add> call, Response<Register_Add> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, getString(R.string.response_error), Toast.LENGTH_LONG).show();
                    return;
                }
                Register_Add addResponse = response.body();
                if (stat == addResponse.getStatus()) {
                    Intent intent = new Intent(context, LoginMainActivity.class);
                    startActivity(intent);
                    Toast.makeText(context,getString(R.string.registration), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, getString(R.string.error), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Register_Add> call, Throwable t) {
                Toast.makeText(context,getString(R.string.fail), Toast.LENGTH_LONG).show();
            }
        });
    }
}
