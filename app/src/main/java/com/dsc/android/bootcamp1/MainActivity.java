package com.dsc.android.bootcamp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name, et_email, et_password, et_confirmpassword;
    private Button butt_signin;
    private String name, email, password, confirmpassword;
    private TinyDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        butt_signin.setOnClickListener(this);
        db = new TinyDB(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.butt_signin) {
            getInfo();
            register();
        }
    }

    //initialize the UI  components
    private void initView() {
        et_name = findViewById(R.id.et_username);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_confirmpassword = findViewById(R.id.et_confirmpassword);
        butt_signin = findViewById(R.id.butt_signin);
    }

    private void getInfo() {
        name = et_name.getText().toString().trim();
        email = et_email.getText().toString().trim();
        password = et_password.getText().toString();
        confirmpassword = et_confirmpassword.getText().toString();
        butt_signin.findViewById(R.id.butt_signin);
    }

    private void register() {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmpassword.isEmpty()) {
            Toast.makeText(this, "Please try again", Toast.LENGTH_LONG).show();
        } else {
            if (password.equals(confirmpassword)) {
                db.putString("name", name);
                db.putString("email", email);
                db.putString("password", password);
                Toast.makeText(this, "User Rgistered", Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, LoginActivity.class);
                startActivity(i);
                finish();

            } else {
                Toast.makeText(this, "Passwords did'nt match", Toast.LENGTH_LONG).show();
            }
        }
    }
}
