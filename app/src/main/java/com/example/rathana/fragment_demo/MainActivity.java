package com.example.rathana.fragment_demo;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    FragmentTransaction transaction=getSupportFragmentManager()
            .beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add Fragment

        transaction.replace(R.id.listMessageContainer,ListMessageFragment.newInstance());
        transaction.replace(R.id.detailMessageContainer,DetailMessageFragment.newInstance());

        transaction.commit();
    }

    public void onReplaceFragmentClicked(View view) {
        FragmentTransaction t=getSupportFragmentManager().beginTransaction();
        t.replace(R.id.listMessageContainer,BlankFragment.newInstance());
        t.addToBackStack(null);
        t.commit();

    }
}
