/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.education.common;

import com.vng.zing.logger.ZLogger;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author vyndk
 */
public class HttpHelper {

    private static final Logger _log = ZLogger.getLogger(HttpHelper.class);

    public static String sendPostRequest(String url, Map<String, String> data) {
        StringBuilder result = new StringBuilder();
        try {
            URL ulrConn = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) ulrConn.openConnection();
            /*
             * Post parameter
             */
            String strData = buildParameter(data);

            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(strData.length()));
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(strData);
            writer.flush();

            /*
             * read data result
             */
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
        } catch (Exception ex) {
            _log.error(ex.getMessage() + ex);
        }

        return result.toString();
    }

    public static String sendGetRequest(String url, Map<String, String> data) {
        StringBuilder result = new StringBuilder();
        try {
            String strData = buildParameter(data);
            if (!strData.isEmpty()) {
                url += "?" + strData;
            }
            URL ulrConn = new URL(url);
            URLConnection conn = ulrConn.openConnection();
            /*
             * read data result
             */
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
        } catch (Exception ex) {
            _log.error(ex.getMessage() + ex);
        }

        return result.toString();
    }

    private static String buildParameter(Map<String, String> data) {
        StringBuilder result = new StringBuilder();

        try {
            if (data != null) {
                Set<Map.Entry<String, String>> setEntry = data.entrySet();
                Iterator it = setEntry.iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    result.append("&");
                    result.append(URLEncoder.encode(entry.getKey().toString(), "UTF-8"));
                    result.append("=");
                    result.append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
                }
            }
        } catch (UnsupportedEncodingException ex) {
            _log.error(ex.getMessage() + ex);
        }
        if (result.indexOf("&") > -1) {
            result.deleteCharAt(0);
        }
        return result.toString();
    }

    public static String getCookie(HttpServletRequest req, String name) {
        String result = "";
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    result = cookie.getValue();
//					break; // get last token
                }
            }
        }
        return result;
    }

    public static void setCookie(HttpServletResponse resp, String domain, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(domain);
        cookie.setPath("/");
        resp.addCookie(cookie);
    }

    public static void removeSession(HttpServletResponse resp, String domain, String name) {
        Cookie cookie = new Cookie(name, "");
        cookie.setDomain(domain);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        boolean isAjax = false;
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            isAjax = true;
        }
        return isAjax;
    }
}
