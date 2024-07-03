package com.i2i.sms.service;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Admin;

import java.util.List;

public interface AdminService {
    Admin addAdmin(String adminName, String adminPassword) throws StudentManagementException;

    boolean deleteAdminById(int adminId) throws StudentManagementException;

    List<Admin> getAllAdmins() throws StudentManagementException;

    boolean isAdminExists(String adminName, String adminPass) throws StudentManagementException;

}
