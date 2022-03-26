package com.hknp.controller.api.open;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class ServletConnection {


    protected abstract void doGetCartItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    protected abstract void doPostCartItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    protected abstract void doPutCartItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    protected abstract void doDeleteCartItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    public abstract void doGetAddressById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    public abstract void doPostAddressById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    public abstract void doPutAddressById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    public abstract void doGetType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    protected abstract void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    protected abstract void doPutNewPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
