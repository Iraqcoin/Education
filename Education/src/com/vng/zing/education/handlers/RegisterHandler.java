/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.education.handlers;

import com.vng.zing.education.common.Configuration;
import com.vng.zing.logger.ZLogger;
import hapax.Template;
import hapax.TemplateDataDictionary;
import hapax.TemplateDictionary;
import hapax.TemplateException;
import hapax.TemplateLoader;
import hapax.TemplateResourceLoader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author root
 */
public class RegisterHandler extends HttpServlet {

    private static Logger _logger = ZLogger.getLogger(LoginHandler.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {

           // Auth auth = Auth.getIdentity(req);
//            if (!auth.isLogged) {
               renderLoginPage(req, resp);
//            } else {
//                resp.sendRedirect("/reporting");
//            }

        } catch (Exception ex) {
            _logger.error(ex);
        }
    }

    private void renderLoginPage(HttpServletRequest req, HttpServletResponse resp) throws TemplateException {

        TemplateDataDictionary dic = getDictionary();
        print(applyTemplate(dic, "signup_admin", req), resp);
    }

    protected TemplateDataDictionary getDictionary() {
        TemplateDataDictionary dic = TemplateDictionary.create();
        dic.setVariable("domain", Configuration.APP_DOMAIN);
        dic.setVariable("static_domain", Configuration.STATIC_DOMAIN);
        return dic;
    }

    protected String applyTemplate(TemplateDataDictionary dic, String tplName, HttpServletRequest req) throws TemplateException {
        TemplateLoader templateLoader = TemplateResourceLoader.create("com/vng/zing/education/view/");
        Template template = templateLoader.getTemplate(tplName);

        if (template != null) {
            return template.renderToString(dic);
        }
        return "";
    }

    protected void print(Object obj, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Connection", "Close");
            response.setStatus(HttpServletResponse.SC_OK);
            out = response.getWriter();
            out.print(obj);
            out.flush();
        } catch (IOException ex) {
            _logger.error(ex.getMessage(), ex);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
