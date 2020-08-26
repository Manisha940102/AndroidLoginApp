package com.example.hr_project1;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceTakeInOutActivity extends AppCompatActivity {

    public final int CUSTOMIZED_REQUEST_CODE_IN = 0x0000ffff;
    public final int CUSTOMIZED_REQUEST_CODE_OUT = 0x0000eeee;
    private Button in;
    private Button out;
    public int number;
    private int stat = 1;
    public int val;
    public String serialNo;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        in = findViewById(R.id.etButton2);
        out = findViewById(R.id.etButton3);

        addListenerOnButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.menuAbout:
                Toast.makeText(this, "You clicked about", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menuSettings:
               // Toast.makeText(this, "You clicked settings", Toast.LENGTH_SHORT).show();
                Intent settingsIntent = new Intent(DeviceTakeInOutActivity.this, Settings_Activity.class);
                Bundle bundle = getIntent().getExtras();
                settingsIntent.putExtras(bundle);
                startActivity(settingsIntent);
                break;

            case R.id.menuLogout:
                Toast.makeText(this, "You clicked logout", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }

    private void addListenerOnButton() {
        in.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                val = 1;
                new IntentIntegrator(DeviceTakeInOutActivity.this).setRequestCode(CUSTOMIZED_REQUEST_CODE_IN).initiateScan();
            }
        });

        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val = 0;
                new IntentIntegrator(DeviceTakeInOutActivity.this).setRequestCode(CUSTOMIZED_REQUEST_CODE_OUT).initiateScan();
            }
        });
    }

    private void createTake () {
        final Context context = this;
        Intent intent = getIntent();

        Bundle bundle = getIntent().getExtras();
        Login_Response postResponse  = bundle.getParcelable("user");
        number = postResponse.getUser().getUser_id();

        Take take = new Take(serialNo,number,val);
        Call<Response_Take> call = RetrofitClientInstance.getRetrofitInstance().create(Login_Api.class).createTake(take);

        call.enqueue(new Callback<Response_Take>() {
            @Override
            public void onResponse(Call<Response_Take> call, Response<Response_Take> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context,  getString(R.string.response_error), Toast.LENGTH_LONG).show();
                    return;
                }
                Response_Take responseTake = response.body();
                if (stat == responseTake.getStatus()) {
                    Toast.makeText(context, getString(R.string.send), Toast.LENGTH_LONG).show();
                    startActivity(intent);
                } else {
                    Toast.makeText(context, getString(R.string.error), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Response_Take> call, Throwable t) {
                Toast.makeText(context,getString(R.string.fail), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == CUSTOMIZED_REQUEST_CODE_IN && resultCode == RESULT_OK) {
            IntentResult result = IntentIntegrator.parseActivityResult(resultCode, data);
            Toast.makeText(this, getString(R.string.scanned_in )+ result.getContents(), Toast.LENGTH_LONG).show();
            serialNo = result.getContents();
            createTake();

        } else if (requestCode == CUSTOMIZED_REQUEST_CODE_OUT && resultCode == RESULT_OK) {
            IntentResult result = IntentIntegrator.parseActivityResult(resultCode, data);
            Toast.makeText(this, getString(R.string.scanned_out ) + result.getContents(), Toast.LENGTH_LONG).show();
            serialNo = result.getContents();
            createTake();

        } else
            super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

