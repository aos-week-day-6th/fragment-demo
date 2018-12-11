package com.example.rathana.fragment_demo;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
implements ListMessageFragment.OnSendData {

    private List<String> inboxMessages=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        inboxMessages.add("012121245");
        inboxMessages.add("012778412");
        inboxMessages.add("0969592334");
        inboxMessages.add("015146532");
        inboxMessages.add("011457823");
        inboxMessages.add("0977545122");
        inboxMessages.add("016161718");
        inboxMessages.add("015000012");

        //add Fragment

        FragmentTransaction transaction=getSupportFragmentManager()
                .beginTransaction();
        ListMessageFragment fragment=ListMessageFragment.newInstance();
        fragment.setInboxMessages(this.inboxMessages);
        transaction.replace(R.id.listMessageContainer,fragment);
        //transaction.replace(R.id.detailMessageContainer,DetailMessageFragment.newInstance());

        transaction.commit();
    }

    public void onReplaceFragmentClicked(View view) {
        FragmentTransaction t=getSupportFragmentManager().beginTransaction();
        t.replace(R.id.listMessageContainer,BlankFragment.newInstance());
        t.addToBackStack(null);
        t.commit();

    }

    public void onReceiveData(String phoneNumber){
        Toast.makeText(this, phoneNumber, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSendPhoneNumber(String phone) {
        Toast.makeText(this, phone, Toast.LENGTH_SHORT).show();
    }


    //subscribe event

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subScribeMessageEvent(MessageEvent messageEvent){
        //Toast.makeText(this, messageEvent.getPhoneNumber(), Toast.LENGTH_SHORT).show();

        FragmentTransaction t=getSupportFragmentManager().beginTransaction();
        DetailMessageFragment fragment= DetailMessageFragment.newInstance();
        fragment.setPhoneNumber(messageEvent.getPhoneNumber());
        t.replace(R.id.listMessageContainer,fragment);
        t.addToBackStack(null);
        t.commit();
    }


    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
