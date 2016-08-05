/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.education.handlers;

import com.vng.zing.common.HReqParam;
import com.vng.zing.education.common.Auth;
import com.vng.zing.logger.ZLogger;
import com.vng.zing.stats.Profiler;
import com.vng.zing.stats.ThreadProfiler;
import com.vng.zing.education.common.Configuration;
import com.vng.zing.education.common.HttpHelper;
import com.vng.zing.education.common.UtilHelper;
import com.vng.zing.education.common.ZingHelper;
import hapax.Template;
import hapax.TemplateDataDictionary;
import hapax.TemplateDictionary;
import hapax.TemplateException;
import hapax.TemplateLoader;
import hapax.TemplateResourceLoader;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author baonh2
 */
public class BaseHandler extends HttpServlet {

    protected static final Logger _logger = ZLogger.getLogger(BaseHandler.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path = req.getServletPath();
        Profiler.createThreadProfilerInHttpProc(path + "." + req.getMethod(), req);
        try {
            if ("GET".equals(req.getMethod())) {
                doGet(req, resp);
            } else if ("POST".equals(req.getMethod())) {
                doPost(req, resp);
            }
        } catch (Exception ex) {
            _logger.error(ex.getMessage(), ex);
        } finally {
            Profiler.closeThreadProfiler();
        }
    }
    public boolean checkLogin(HttpServletRequest req , HttpServletResponse resp){
        Auth auth = (Auth) req.getAttribute(Auth.AUTH);
        if(auth != null && auth.isLogged){
            return true;
        }
        auth = Auth.getIdentity(req);
        if (!auth.isLogged) {
             try {              
                 resp.sendRedirect(Configuration.APP_DOMAIN + "/user/sign_up?continue=" + UtilHelper.encodeUrl(getFullUrl(req)));
                 return false;
             } catch (IOException ex) {
                 _logger.error(ex.getMessage());
             }
           return false;
        }
        return true;
    }

    protected TemplateDataDictionary getDictionary() {
        return getDictionary("vi");
    }
    
       public static String getQueryString(HttpServletRequest req){
        String query = req.getQueryString();
        return query != null ? query : "";
    }
    
    public static String getFullUrl(HttpServletRequest request){
        String queryString = request.getQueryString() != null ? "?"+request.getQueryString() : "";
        String uri = request.getScheme() + "://" +   // "http" + "://
             request.getServerName() +       // "myhost"
             ":" +                           // ":"
             request.getServerPort() +       // "8080"
             request.getRequestURI() +       // "/people"
             queryString;       // "lastname=Fox&age=30"
        return uri;
    }


    protected static TemplateDataDictionary getDictionary(String language) {
        ThreadProfiler profiler = Profiler.getThreadProfiler();
        try {
            profiler.push(BaseHandler.class, "getDictionary");
            TemplateDataDictionary dic = TemplateDictionary.create();
            dic.setVariable("domain", Configuration.getDomain());
            dic.setVariable("static_domain", Configuration.getStaticDomain());
            dic.setVariable("static_domain1", Configuration.getStaticDomain());
            dic.setVariable("static_domain2", Configuration.getStaticDomain());
            dic.setVariable("language", Configuration.getStaticDomain());
            dic.setVariable("app_name", Configuration.getAppName());
            dic.setVariable("js", Configuration.getJS());
      
            return dic;
        } finally {
            profiler.pop(BaseHandler.class, "getDictionary");
        }
    }

    protected TemplateDataDictionary getDictionary(HttpServletRequest request, HttpServletResponse resp) {
        String language = getLanguage(request, resp);
        return getDictionary(language);
    }

    protected static String getLanguage(HttpServletRequest req, HttpServletResponse resp) {
        String languageParam = HReqParam.getString(req, "language", null);
        String languageCookie = HttpHelper.getCookie(req, "language");
        if (languageCookie.isEmpty()) {
            languageCookie = HttpHelper.getCookie(req, "zlanguage");
        }

        if (languageCookie.equals(languageParam) == false && languageParam != null && resp != null) {
            HttpHelper.setCookie(resp, "", "language", languageParam);
        }

        String ret = languageParam != null ? languageParam : languageCookie;
        return (!ret.isEmpty()) ? ret : "vi";
    }

    protected String getClientETag(HttpServletRequest req) {
        String ifNoneMatchHeader = req.getHeader("If-None-Match");
        return ifNoneMatchHeader != null ? ifNoneMatchHeader.trim() : "";
    }


    public static void addScript(String scriptUrl, TemplateDataDictionary layoutDic) {
        addScript(scriptUrl, layoutDic, null);
    }

    public static void addScript(String scriptUrl, TemplateDataDictionary layoutDic, String extra) {
        TemplateDataDictionary scriptSession = layoutDic.addSection("SCRIPTS");
        scriptSession.setVariable("script_url", scriptUrl);
        if (null != extra && !extra.isEmpty()) {
            scriptSession.setVariable("extra", extra);
        }
    }

    public static String applyTemplate(TemplateDataDictionary dic, String tplName) throws TemplateException {
        TemplateLoader templateLoader = TemplateResourceLoader.create("com/vng/zing/education/view/");
        Template template = templateLoader.getTemplate(tplName);
        return template.renderToString(dic);
    }

    protected void print(Object obj, HttpServletResponse response) {
        print(obj, "text/html;charset=UTF-8", null, response);
    }

    protected void printWithGZip(byte[] obj, HttpServletResponse response) {
        printWithGZip(obj, null, "text/html;charset=UTF-8", response);
    }

    protected void printWithGZip(byte[] obj, String etag, HttpServletResponse response) {
        printWithGZip(obj, etag, "text/html;charset=UTF-8", response);
    }

    protected void printJSONWithGZip(byte[] obj, HttpServletResponse response) {
        printWithGZip(obj, null, "text/json;charset=UTF-8", response);
    }

    private void printWithGZip(byte[] obj, String etag, String contentType, HttpServletResponse response) {
        ThreadProfiler profiler = Profiler.getThreadProfiler();
        profiler.push(BaseHandler.class, "writeResponseHTML");

        try {
            response.setContentType(contentType);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Connection", "Close");
            response.setHeader("Content-Encoding", "gzip");
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentLength(obj.length);
            if (etag != null) {
                response.setHeader("ETag", etag);
            }

            ServletOutputStream stream = null;
            BufferedInputStream buf = null;
            stream = response.getOutputStream();

            ByteArrayInputStream arrInput = new ByteArrayInputStream(obj);
            buf = new BufferedInputStream(arrInput);

            byte[] bb = new byte[response.getBufferSize()];
            int readByte;
            while ((readByte = buf.read(bb, 0, bb.length)) != -1) {
                stream.write(bb, 0, readByte);
            }
            buf.close();
            stream.flush();
            stream.close();
        } catch (IOException ex) {
            _logger.error(ex.getMessage(), ex);
        } finally {
            profiler.pop(BaseHandler.class, "writeResponseHTML");

        }
    }

    protected void printJSON(Object json, HttpServletResponse response) {
        print(json, "text/json;charset=UTF-8", null, response);
    }

    protected void printHTMLNotModified(HttpServletResponse resp, String etag) {
        printNotModified(resp, etag, "text/html;charset=UTF-8");
    }

    protected void printJSONNotModified(HttpServletResponse resp, String etag) {
        printNotModified(resp, etag, "text/json;charset=UTF-8");
    }

    private void printNotModified(HttpServletResponse resp, String etag, String contentType) {
        resp.setContentType(contentType);
        resp.setHeader("Connection", "Close");
        resp.setHeader("ETag", etag);
        resp.setHeader("Content-Length", "0");
        resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
    }

    protected void print(Object obj, String etag, HttpServletResponse response) {
        print(obj, "text/html;charset=UTF-8", etag, response);
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

    protected String buildEtag(byte[] data) {
        return ZingHelper.md5(data);
    }
}
