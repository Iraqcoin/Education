/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.education.cache;

import com.vng.zing.education.common.ZingHelper;
import com.vng.zing.education.model.HomeModel;
import com.vng.zing.logger.ZLogger;

import java.util.Calendar;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author vyndk
 */
public class CheckSumCache {

    private static final Logger logger = ZLogger.getLogger(CheckSumCache.class);
    private static final Map<String, byte[]> cache = new ConcurrentHashMap<>();
    private static final Map<String, String> cacheVersion = new ConcurrentHashMap<String, String>();

    private static final Timer timer = new Timer();

    public static byte[] getCacheHome(String key) {
        return cache.get(key);
    }

    public static void setCacheHome(String key, byte[] data) {
        cache.put(key, data);
        cacheVersion.put(key, ZingHelper.md5(data));
    }
    
    public static String getVersionHome(String key) {
        return cacheVersion.get(key);
    }


    public static void initTimerUpdateCache() {
        logger.info("Initializing initTimerUpdateCache at Home...");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        timer.scheduleAtFixedRate(new CheckSumCache.UpdateCacheHandler(), cal.getTime(), 86400L * 1000L);
        logger.info("Finish initTimerUpdateCache at Home...");
    }

    public static void updateCacheHome() {
        try {
            logger.info("updateCacheHome");
            String[] languages = {"vi", "en", "my"};
            for (String language : languages) {
                byte[] data = HomeModel.buildHomeHTML(language);
                setCacheHome(language, data);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    private static class UpdateCacheHandler extends TimerTask {

        @Override
        public void run() {
            int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            if (hour != 0) {
                return;
            }
            CheckSumCache.updateCacheHome();
        }
    }
}
