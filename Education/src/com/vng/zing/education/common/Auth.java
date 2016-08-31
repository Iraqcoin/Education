package com.vng.zing.education.common;

import com.vng.zing.education.dto.UserRegisterDTO;
import com.vng.zing.education.model.CacheUserModel;
import com.vng.zing.education.model.UserRegisterModel;
import com.vng.zing.logger.ZLogger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author minhnvc
 */
public class Auth {

    private static final Logger _log = ZLogger.getLogger(Auth.class);
    public static final String AUTH = "zsid";
    public static final int MAX_AGE = 60 * 60 * 24 * 14;
    public int userId = 0;
    public String userName = "";
    public String displayName = "";
    public long dayOfBirth = 0;
    public long certDate = 0;
    public int ownerId = 0;
    public int gender = 0;
    public String ownerName = "";
    public int avatarVersion = 0;
    public String avatarUrl = "";
    public boolean isLogged = false;

    public static Auth getIdentity(HttpServletRequest req) {
        Auth auth = new Auth();
        String zsession = "";

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(AUTH)) {
                    zsession = cookie.getValue();
                    break;
                }
            }
            if (!zsession.isEmpty()) {
                try {
                    UserRegisterDTO profile = CacheUserModel.getCache(zsession);
                    if(profile == null)
                       profile =  UserRegisterModel.getInstances().getDataById(Integer.parseInt(zsession));
                    if (profile != null) {
                        auth.userId = profile.getId();
                        auth.userName = profile.getUsername();
                        auth.displayName = profile.getFirst_name() + " " +  profile.getLast_name();
                        auth.isLogged = true;
                    }
                } catch (Exception ex) {
                    _log.error(ex.getMessage(), ex);
                }
            }
        }
        req.setAttribute(AUTH, auth);
        return auth;
    }

    public static void removeCookie(String domain, HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(AUTH)) {
                    _log.info("remove cookie : domain=" + domain + " | value=" + cookie.getValue());

                    cookie.setDomain(domain);
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    cookie.setHttpOnly(true);
                    cookie.setVersion(1);
                    resp.setHeader("p3p", "CP=\"NOI DSP COR NID CURa ADMa DEVa PSAa PSDa OUR BUS COM INT OTC PUR STA\"");
                    resp.addCookie(cookie);
                    break;
                }
            }
        }
    }

    public static void removeCookie(String name, String domain, HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    _log.info("remove cookie : domain=" + domain + " | value=" + cookie.getValue());

                   // cookie.setDomain(domain);
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    cookie.setHttpOnly(true);
                    cookie.setVersion(1);
                    resp.setHeader("p3p", "CP=\"NOI DSP COR NID CURa ADMa DEVa PSAa PSDa OUR BUS COM INT OTC PUR STA\"");
                    resp.addCookie(cookie);
                    break;
                }
            }
        }
    }

    public static void createCookie(String session, String domain, HttpServletResponse resp, String longtime) {
        Cookie cookie = new Cookie(AUTH, session);
        if (longtime != null) {
            if ("1".equals(longtime) || "on".equalsIgnoreCase(longtime)) {
                cookie.setMaxAge(MAX_AGE);
            }
        }
        //cookie.setDomain(domain);
        cookie.setPath("/");
        cookie.setVersion(1);
        cookie.setHttpOnly(true);
        resp.setHeader("p3p", "CP=\"NOI DSP COR NID CURa ADMa DEVa PSAa PSDa OUR BUS COM INT OTC PUR STA\"");
        resp.addCookie(cookie);
    }

    public static void createCookie(String cookieName, String value, String domain, HttpServletResponse resp, String longtime) {
        Cookie cookie = new Cookie(cookieName, value);
        if (longtime != null) {
            if ("1".equals(longtime) || "on".equalsIgnoreCase(longtime)) {
                cookie.setMaxAge(MAX_AGE);
            }
        }
        cookie.setDomain(domain);
        cookie.setPath("/");
        cookie.setVersion(1);
        cookie.setHttpOnly(true);
        resp.setHeader("p3p", "CP=\"NOI DSP COR NID CURa ADMa DEVa PSAa PSDa OUR BUS COM INT OTC PUR STA\"");
        resp.addCookie(cookie);
    }

    public static String getCookie(String cookieName, HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return "";
    }
}
