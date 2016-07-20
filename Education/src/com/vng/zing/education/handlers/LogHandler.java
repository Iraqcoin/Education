/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.education.handlers;

import com.vng.zing.common.HReqParam;
import com.vng.zing.logger.ZLogger;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author vyndk
 */
public class LogHandler extends BaseHandler {

    private static final Logger _logger = ZLogger.getLogger(LogHandler.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String msg = HReqParam.getString(req, "msg", "");
            if (!msg.isEmpty()) {
                StringBuilder cookies = new StringBuilder("");
                if (req.getCookies() != null) {
                    for (Cookie c : req.getCookies()) {
                        cookies.append(c.getName()).append('=').append(c.getValue()).append(";");
                    }
                }
                _logger.info("[ZPLogger] " + msg + ". Cookie: " + cookies.toString());
            }
        } catch (Exception ex) {
            _logger.error(ex.getMessage(), ex);
        }
    }
}
