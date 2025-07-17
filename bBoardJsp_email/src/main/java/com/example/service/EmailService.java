package com.example.service;

public interface EmailService {
    String sendVerificationEmail(String email) throws Exception;

    public boolean sendTempPassword(String email) throws Exception;
}
