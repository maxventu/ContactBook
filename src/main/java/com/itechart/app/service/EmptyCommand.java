package com.itechart.app.service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Maxim on 11/25/2015.
 */
public class EmptyCommand {

    public String execute(HttpServletRequest request) {
/* � ������ ������ ��� ������� ��������� � �����������
* ������������� �� �������� ����� ������ */
        String page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
}