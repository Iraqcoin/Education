/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.education.model;

import com.vng.zing.education.cache.CheckSumCache;
import com.vng.zing.education.common.Configuration;
import com.vng.zing.education.common.UtilHelper;
import com.vng.zing.education.handlers.BaseHandler;
import com.vng.zing.logger.ZLogger;
import com.vng.zing.stats.Profiler;
import com.vng.zing.stats.ThreadProfiler;

import hapax.TemplateDataDictionary;
import hapax.TemplateException;
import java.io.IOException;
import org.apache.log4j.Logger;

/**
 *
 * @author baonh2
 */
public class HomeModel extends BaseHandler {

    private static Logger logger = ZLogger.getLogger(HomeModel.class);

    public static byte[] getHomeHTML(String language) throws Exception {

        //Get from cache
        byte[] dataFromCache = CheckSumCache.getCacheHome(language.toLowerCase().trim());
        if (dataFromCache != null) {
            return dataFromCache;
        }
        dataFromCache = buildHomeHTML(language);
        CheckSumCache.setCacheHome(language.toLowerCase().trim(), dataFromCache);
        return dataFromCache;
    }

    public static byte[] buildHomeHTML(String language) throws TemplateException, IOException {
        ThreadProfiler profiler = Profiler.getThreadProfiler();
        profiler.push(HomeModel.class, "buildHomeHTML");
        TemplateDataDictionary layout = getDictionary(language);
        TemplateDataDictionary content = layout.clone();
        content.setVariable("language", language);


        content.setVariable("numRenderItem", String.valueOf(Configuration.getNumRenderItem()));

        layout.setVariable("BODY", applyTemplate(content, "home"));
        layout.setVariable("initJSCall", "homeReady();");
        byte[] result = UtilHelper.gzipString(applyTemplate(layout, "layout"));
        profiler.pop(HomeModel.class, "buildHomeHTML");
        return result;
    }

   
}
