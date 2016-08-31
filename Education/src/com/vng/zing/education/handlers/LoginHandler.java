/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.education.handlers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vng.zing.common.HReqParam;
import com.vng.zing.education.common.Auth;
import com.vng.zing.education.common.Configuration;
import com.vng.zing.education.dto.UserRegisterDTO;
import static com.vng.zing.education.handlers.BaseHandler._logger;
import com.vng.zing.education.model.UserRegisterModel;
import com.vng.zing.logger.ZLogger;
import com.vng.zing.stats.Profiler;
import com.vng.zing.stats.ThreadProfiler;
import hapax.Template;
import hapax.TemplateDataDictionary;
import hapax.TemplateDictionary;
import hapax.TemplateException;
import hapax.TemplateLoader;
import hapax.TemplateResourceLoader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author root
 */
public class LoginHandler extends HttpServlet {

    private static Logger _logger = ZLogger.getLogger(LoginHandler.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String action = HReqParam.getString(req, "action","");
        try {
            if(action.equals("check")){
                Auth auth = Auth.getIdentity(req);
                if(!auth.isLogged)
                    printJSON(parseErrorToJson(-1,Configuration.getDomain() + "/user/sign_in"), resp);
                else
                    printJSON(parseErrorToJson(auth.userId ,""), resp);
                return;
            }
            else if(action.equals("logout")){
                Auth.removeCookie(Auth.AUTH,Configuration.getDomain(), req, resp);
                resp.sendRedirect(Configuration.getDomain());
            }
            String continues = HReqParam.getString(req, "continue", Configuration.getDomain());
            Auth auth = Auth.getIdentity(req);
            if (!auth.isLogged) {
                renderLoginPage(req, resp);
            } else {
                resp.sendRedirect(continues);
            }
        } catch (Exception ex) {
            _logger.error(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data = HReqParam.getString(req, "data", "");
        JsonObject jsonData = new JsonParser().parse(data).getAsJsonObject();
        String continues = HReqParam.getString(req, "continue", Configuration.getDomain());
        String username = jsonData  != null ? jsonData.getAsJsonPrimitive("username").getAsString() : "";
        String passw = jsonData != null ? jsonData.getAsJsonPrimitive("passw").getAsString() : "";
        int remember = jsonData.getAsJsonPrimitive("remember") != null ? jsonData.getAsJsonPrimitive("remember").getAsInt(): 0;
        if (username.length() < 5 || passw.length() < 5) {
            printJSON(parseErrorToJson(-1,""), resp);
            return;
        }
        UserRegisterDTO dto = UserRegisterModel.getInstances().checkLogin(username, passw);
        if (dto != null && dto.getId() > 0) {
            if (remember == 1) {
                Auth.createCookie(dto.getHashname(), Configuration.COOKIE_DOMAIN, resp, "1");
            }
            printJSON(parseErrorToJson(dto.getId(),continues), resp);
        }else{
            printJSON(parseErrorToJson(-1,""), resp);
        }
    }

    private void renderLoginPage(HttpServletRequest req, HttpServletResponse resp) throws TemplateException {

        TemplateDataDictionary dic = getDictionary();
        print(applyTemplate(dic, "login_admin", req), resp);
    }

    protected TemplateDataDictionary getDictionary() {
        TemplateDataDictionary dic = TemplateDictionary.create();
        dic.setVariable("domain", Configuration.APP_DOMAIN);
        dic.setVariable("static_domain", Configuration.STATIC_DOMAIN);
        return dic;
    }

    private JsonObject parseErrorToJson(int id,String callBack) {
        JsonObject results = new JsonObject();
        if (id < 0) {
            results.addProperty("error", -1);
            results.addProperty("callBack", callBack);
        } else {
            results.addProperty("error", id);
            if(!callBack.isEmpty())
                 results.addProperty("callBack", callBack);
        }
        return results;
    }

    protected void printJSON(Object json, HttpServletResponse response) {
        print(json, "text/json;charset=UTF-8", null, response);
    }

    protected String applyTemplate(TemplateDataDictionary dic, String tplName, HttpServletRequest req) throws TemplateException {
        TemplateLoader templateLoader = TemplateResourceLoader.create("com/vng/zing/education/view/");
        Template template = templateLoader.getTemplate(tplName);

        if (template != null) {
            return template.renderToString(dic);
        }
        return "";
    }

    protected void print(Object obj, String contentType, String etag, HttpServletResponse response) {
        ThreadProfiler profiler = Profiler.getThreadProfiler();
        profiler.push(BaseHandler.class, "writeResponseHTML");
        PrintWriter out = null;
        try {
            response.setContentType(contentType);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Connection", "Close");
            if (etag != null) {
                response.setHeader("ETag", etag);
            }
            response.setStatus(HttpServletResponse.SC_OK);
            out = response.getWriter();
            out.print(obj);
            out.flush();
        } catch (IOException ex) {
            _logger.error(ex.getMessage(), ex);
        } finally {
            profiler.pop(BaseHandler.class, "writeResponseHTML");
            if (out != null) {
                out.close();
            }
        }
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
