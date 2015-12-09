package com.itechart.app.service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Maxim on 11/25/2015.
 */
public class EmptyCommand {

    public String execute(HttpServletRequest request) {
/* в случае ошибки или прямого обращения к контроллеру
* переадресация на страницу ввода логина */
        String page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
}