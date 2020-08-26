package com.example.hr_project1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Settings_Activity  extends AppCompatActivity {
  private EditText userName;
  private EditText email;
  private EditText password;
  private EditText rePassword;
  private Button update;
  private ImageView imageView;
  private int number;
  private int stat = 1;
  private TextView textView;
  private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userName = findViewById(R.id.etName);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        rePassword = findViewById(R.id.etRePassword);
        update = findViewById(R.id.btnUpdate);

        imageView = findViewById(R.id.ivLog);
        Bundle bundle = getIntent().getExtras();
        Login_Response postResponse  = bundle.getParcelable("user");
        userName.setText( postResponse.getUser().getUser_name());
        email.setText(postResponse.getUser().getEmail());


        addListenerOnButton();
    }

    private void addListenerOnButton() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(Settings_Activity.this, Profile_Activity.class);
                startActivity(settingsIntent);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    try {
                        String pass = Encrypt.encrypt(password.getText().toString());
                        createPut(pass);
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }
                }

        });
    }

    private void createPut(String pass) {
        final Context context = this;

        Bundle bundle = getIntent().getExtras();
        Login_Response postResponse  = bundle.getParcelable("user");
        number = postResponse.getUser().getUser_id();

        Put put = new Put(number,userName.getText().toString(),pass.trim(),email.getText().toString());

        Call<Update_Response> call = RetrofitClientInstance.getRetrofitInstance().create(Login_Api.class).createPut(put);

        call.enqueue(new Callback<Update_Response>() {
            @Override
            public void onResponse(Call<Update_Response> call, Response<Update_Response> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, getString(R.string.response_error), Toast.LENGTH_LONG).show();
                    return;
                }
                Update_Response putResponse = response.body();
                if (stat == putResponse.getStatus()) {
                    Toast.makeText(context, getString(R.string.update), Toast.LENGTH_LONG).show();
                    Intent registerIntent = new Intent(Settings_Activity.this, DeviceTakeInOutActivity.class);
                    startActivity(registerIntent);
                } else {
                    Toast.makeText(context, getString(R.string.error), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Update_Response> call, Throwable t) {
                Toast.makeText(context,getString(R.string.fail), Toast.LENGTH_LONG).show();
            }
        });
    }
}
