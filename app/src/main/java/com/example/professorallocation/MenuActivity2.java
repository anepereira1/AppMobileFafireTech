package com.example.professorallocation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MenuActivity2 extends AppCompatActivity {

    private Button btCurso;
    private Button btDepartment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        btCurso.setOnClickListener(view -> {
            Intent menssageiro;
            menssageiro = new intent(getApplicationContext(),
                    MainActivity.class().startActivity(menssageiro));

            btCurso = findViewById(R.id.btCurso);
            btDepartment = findViewById(R.id.btDepartamento);



    });

        btDepartment.setOnClickListener();

    }

    private class intent extends Intent {
        public intent(Context applicationContext, Class<MainActivity> mainActivityClass) {
        }
    }
}