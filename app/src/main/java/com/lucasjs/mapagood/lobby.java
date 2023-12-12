package com.lucasjs.mapagood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class lobby extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
    }

    public void iralALogin(View view){
        Intent intent = new Intent(lobby.this, IniciodeSesion.class);
        startActivity(intent);
    }
}
