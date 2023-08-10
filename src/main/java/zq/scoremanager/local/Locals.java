package zq.scoremanager.local;

import jakarta.servlet.http.HttpServletRequest;

public class Locals {

    public static ThreadLocal<HttpServletRequest> servletRequest = new ThreadLocal<>();

}
