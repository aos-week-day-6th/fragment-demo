package com.example.rathana.fragment_demo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class ListMessageFragment extends Fragment {

    List<String> inboxMessages=new ArrayList<>();
    ListView listView;
    //OnSendData callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //callback= (OnSendData) context;
    }

    public void setInboxMessages(List<String> inboxMessages) {
        this.inboxMessages = inboxMessages;
    }

    private  static ListMessageFragment fragment;
    public static ListMessageFragment newInstance(){
        if(fragment==null)
            fragment=new ListMessageFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_list_message,container,false);

        listView=view.findViewById(R.id.listView);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //bindData

        /*inboxMessages.add("012121245");
        inboxMessages.add("012778412");
        inboxMessages.add("0969592334");
        inboxMessages.add("015146532");
        inboxMessages.add("011457823");
        inboxMessages.add("0977545122");
        inboxMessages.add("016161718");
        inboxMessages.add("015000012");*/

        ArrayAdapter<String> adapter=new ArrayAdapter(
                getActivity(),
                android.R.layout.simple_list_item_1,
                inboxMessages
        );
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view , int position, long id) {
                //((MainActivity) getActivity()).onReceiveData(inboxMessages.get(position));
                //callback.onSendPhoneNumber(inboxMessages.get(position));

                EventBus.getDefault().post(new MessageEvent(inboxMessages.get(position)));
            }
        });

    }

    interface  OnSendData{
        void onSendPhoneNumber(String phone);
    }
}

