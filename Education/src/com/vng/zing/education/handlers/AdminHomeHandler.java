/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.education.handlers;

import static com.vng.zing.education.handlers.BaseHandler._logger;
import com.vng.zing.stats.Profiler;
import com.vng.zing.stats.ThreadProfiler;
import hapax.TemplateDataDictionary;
import hapax.TemplateException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author root
 */
public class AdminHomeHandler extends BaseHandler {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Profiler.getThreadProfiler().push(this.getClass(), "AdminHomeHandler");
        try {
            String data = buildHomeHTML(req, resp);
            print(data, resp);
        } catch (Exception ex) {
            _logger.error(ex.getMessage(), ex);
        } finally {
            Profiler.getThreadProfiler().pop(this.getClass(), "AdminHomeHandler");
        }

    }

    public String buildHomeHTML(HttpServletRequest req, HttpServletResponse resp) throws TemplateException, IOException {
        ThreadProfiler profiler = Profiler.getThreadProfiler();
        profiler.push(LoadHomeHandler.class, "buildHomeHTML");
        TemplateDataDictionary layout = getDictionary();
        String result = applyTemplate(layout, "admin");
        profiler.pop(LoadHomeHandler.class, "buildHomeHTML");
        return result;
    }
}
