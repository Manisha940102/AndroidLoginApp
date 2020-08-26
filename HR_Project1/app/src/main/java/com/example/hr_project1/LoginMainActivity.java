package com.example.hr_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginMainActivity extends AppCompatActivity {
    private static final String TAG = LoginMainActivity.class.getSimpleName();
    private EditText name;
    private EditText password;
    private Button login;
    private Button register;
    private int stat = 1;
    String AES = "AES";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.etUserName);
        password = findViewById(R.id.etUserPassword);
        login = findViewById(R.id.etButton);
        register = findViewById(R.id.btnRegister);

        addListenerOnButton();
    }

    private void addListenerOnButton() {
        final Context context = this;

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().trim().length() == 0 && password.getText().toString().trim().length() == 0){
                    Toast.makeText(context, getString(R.string.empty), Toast.LENGTH_LONG).show();
                }
                else if(name.getText().toString().trim().length() > 0 && password.getText().toString().trim().length() == 0){
                    Toast.makeText(context, getString(R.string.empty_password), Toast.LENGTH_LONG).show();
                }
                else if(name.getText().toString().trim().length() == 0 && password.getText().toString().trim().length() > 0){
                    Toast.makeText(context, getString(R.string.empty_name), Toast.LENGTH_LONG).show();
                }
                else{
                    try {
                        String pass = Encrypt.encrypt(password.getText().toString());
                        createPost(pass);
                    } catch (Exception ex){
                       ex.printStackTrace();
                    }
               }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginMainActivity.this, RegistrationForm_Activity.class);
                startActivity(registerIntent);
            }
        });

    }

    private void createPost(String pass) {
        final Context context = this;

        Post post = new Post(name.getText().toString(),pass.trim());

        Call<Login_Response> call = RetrofitClientInstance.getRetrofitInstance().create(Login_Api.class).createPost(post);

        call.enqueue(new Callback<Login_Response>() {
            @Override
            public void onResponse(Call<Login_Response> call, Response<Login_Response> response) {
                Login_Response postResponse = response.body();
                if (!response.isSuccessful()) {
                    Toast.makeText(context, getString(R.string.response_error), Toast.LENGTH_LONG).show();
                    return;
                }else{
                    Intent intent = new Intent(context, DeviceTakeInOutActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("user", postResponse);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<Login_Response> call, Throwable t) {
                Toast.makeText(context,getString(R.string.fail), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i(TAG,"OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "OnPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "OnStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "OnRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "OnDestroy");
    }
}

