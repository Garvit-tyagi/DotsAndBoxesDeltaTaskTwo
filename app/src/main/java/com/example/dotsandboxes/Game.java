package com.example.dotsandboxes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class Game extends AppCompatActivity {
private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }
     public void fourSquare(View v){
        view=findViewById(R.id.textView);
        view.setVisibility(View.GONE);

        view=findViewById(R.id.fourGridButton);
        view.setVisibility(View.GONE);

        view=findViewById(R.id.fiveGridButton);
    view.setVisibility(View.GONE);

    view=findViewById(R.id.sixGridButton);
    view.setVisibility(View.GONE);

    view=findViewById(R.id.fourGridView);
    view.setVisibility(View.VISIBLE);
}
    public void fiveSquare(View v){
        view=findViewById(R.id.textView);
        view.setVisibility(View.GONE);

        view=findViewById(R.id.fourGridButton);
        view.setVisibility(View.GONE);

        view=findViewById(R.id.fiveGridButton);
        view.setVisibility(View.GONE);

        view=findViewById(R.id.sixGridButton);
        view.setVisibility(View.GONE);

        view=findViewById(R.id.fiveGridView);
        view.setVisibility(View.VISIBLE);
    }
    public void sixSquare(View v){
        view=findViewById(R.id.textView);
        view.setVisibility(View.GONE);

        view=findViewById(R.id.fourGridButton);
        view.setVisibility(View.GONE);

        view=findViewById(R.id.fiveGridButton);
        view.setVisibility(View.GONE);

        view=findViewById(R.id.sixGridButton);
        view.setVisibility(View.GONE);

        view=findViewById(R.id.sixGridView);
        view.setVisibility(View.VISIBLE);
    }
}
