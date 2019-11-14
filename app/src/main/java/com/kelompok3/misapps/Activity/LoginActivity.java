package com.kelompok3.misapps.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kelompok3.misapps.API.API;
import com.kelompok3.misapps.API.Service;
import com.kelompok3.misapps.R;
import com.kelompok3.misapps.SharedPreferences.TinyDB;
import com.kelompok3.misapps.Util.Font;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.kelompok3.misapps.Util.Consts.KEY_CELL_PHONE;
import static com.kelompok3.misapps.Util.Consts.KEY_EMAIL;
import static com.kelompok3.misapps.Util.Consts.KEY_IS_LOGIN;
import static com.kelompok3.misapps.Util.Consts.KEY_NAMA;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.headWelcome)
    TextView headWelcome;
    @BindView(R.id.headLogin)
    TextView headLogin;

    @BindView(R.id.etPhoneNumber)
    TextInputEditText etPhoneNumber;
    @BindView(R.id.etPassword)
    TextInputEditText etPassword;
    @BindView(R.id.btnLogin)
    MaterialButton btnLogin;

    Service service;

    TinyDB tinyDB = null;

    Font font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tinyDB = new TinyDB(getBaseContext());

        if (tinyDB.getBoolean(KEY_IS_LOGIN)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        service = API.getClient().create(Service.class);

        font = new Font(this);
        headWelcome.setTypeface(font.productSansBold());
        headLogin.setTypeface(font.productSansBold());
    }

    @OnClick(R.id.btnLogin)
    public void doLogin(View view) {
        String cell_phone = etPhoneNumber.getText().toString();
        String password = etPassword.getText().toString();

        Call<JsonObject> authRequest = service.getAuth(
                cell_phone,
                password
        );

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Login...");
        progressDialog.show();

        authRequest.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject object = response.body();
                    JsonArray array = object.get("result").getAsJsonArray();

                    progressDialog.dismiss();

                    JsonObject objectUser = array.get(0).getAsJsonObject();
                    String full_name = objectUser.get("full_name").getAsString();
                    String email = objectUser.get("email").getAsString();
                    String cell_phone = objectUser.get("cell_phone").getAsString();

                    String var_result = object.get("var_result").getAsString();

                    if (var_result.equals("1")) {

                        tinyDB.putString(KEY_NAMA, full_name);
                        tinyDB.putString(KEY_EMAIL, email);
                        tinyDB.putString(KEY_CELL_PHONE, cell_phone);
                        tinyDB.putBoolean(KEY_IS_LOGIN, true);


                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                authRequest.cancel();
            }
        });
    }
}
