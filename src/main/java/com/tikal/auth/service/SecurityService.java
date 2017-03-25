package com.tikal.auth.service;

/**
 * Created by Sopher on 23/03/2017.
 */
public interface SecurityService {

    String findLoggedInUserName();

    void autologin(String userName, String password);
}
