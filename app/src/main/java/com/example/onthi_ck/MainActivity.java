package com.example.onthi_ck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText pl_email,pl_password;
    Button btnDangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        pl_email = findViewById(R.id.pl_email);
        pl_password = findViewById(R.id.pl_password);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = pl_email.getText().toString();
                String password = pl_password.getText().toString();

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
                    Toast.makeText(MainActivity.this, "Vui long nhap du", Toast.LENGTH_SHORT).show();
                else
                    Dangnhap(email,password);
            }
        });
    }

    private void Dangnhap(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Toast.makeText(MainActivity.this, "Dung", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this,Action.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Sai", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}