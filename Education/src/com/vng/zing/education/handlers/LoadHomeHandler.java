/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.education.handlers;

import com.vng.zing.education.cache.CheckSumCache;
import com.vng.zing.education.model.HomeModel;
import com.vng.zing.stats.Profiler;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author baonh2
 */
public class LoadHomeHandler extends BaseHandler {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Profiler.getThreadProfiler().push(this.getClass(), "LoadHomeHandler");
        try {
            String language = getLanguage(req, resp);
            String etag = CheckSumCache.getVersionHome(language.toLowerCase().trim());
            String clientEtag = getClientETag(req);
            if (clientEtag.equals(etag)) {
                printHTMLNotModified(resp, clientEtag);
                return;
            }
            byte[] data = HomeModel.getHomeHTML(language);
            if(etag == null){
                etag = CheckSumCache.getVersionHome(language.toLowerCase().trim());// get new etag
            }
            printWithGZip(data, etag, resp);
        } catch (Exception ex) {
            _logger.error(ex.getMessage(), ex);
        } finally {
            Profiler.getThreadProfiler().pop(this.getClass(), "LoadHomeHandler");
        }
    }
    
}
