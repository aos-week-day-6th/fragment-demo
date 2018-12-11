package com.example.rathana.fragment_demo;


public class MessageEvent {

    String phoneNumber;

    public MessageEvent() {
    }

    public MessageEvent(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
