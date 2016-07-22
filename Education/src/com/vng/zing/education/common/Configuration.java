/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.education.common;

import com.vng.zing.configer.ZConfig;
import com.vng.zing.education.cache.ZCommonCache;

/**
 *
 * @author baonh2
 */
public class Configuration {

    public static final String APP_DOMAIN = ZConfig.Instance.getString(Configuration.class, "app", "domain", "http://m.page.zaloapp.com/v2");
    public static final String STATIC_DOMAIN = ZConfig.Instance.getString(Configuration.class, "app", "static-domain", "http://stc.m.page.zaloapp.com/v2");
    public static final int NUM_ITEM_PER_PAGE = ZConfig.Instance.getInt(Configuration.class, "app", "paging-num", 20);
    private static final String APP_VERSION = ZConfig.Instance.getString(Configuration.class, "app", "app-version", "");
    private static final String APP_NAME = ZConfig.Instance.getString(Configuration.class, "app", "app-name", "");
    private static final int NUM_RENDER_ITEM = ZConfig.Instance.getInt(Configuration.class, "app", "render-num", 6);
    private static final int NUM_CACHE_ITEM = ZConfig.Instance.getInt(Configuration.class, "app", "cache-num", 20);
    private static final int SHOWED_CATE = ZConfig.Instance.getInt(Configuration.class, "app", "showed-cate", 10003);
    private static final int BANNER_NUMBER = ZConfig.Instance.getInt(Configuration.class, "app", "banner-num", 3);
    private static final int PROMOTION_NUMBER = ZConfig.Instance.getInt(Configuration.class, "app", "promotion-num", 5);
    public static final ZCommonCache _ZCommonCache = new ZCommonCache("main");
    public static final int DISTINCT_PAGING_NUMBER = ZConfig.Instance.getInt(Configuration.class, "app", "distinct-paging-num", 10);
    public static final String LINK_STORE = ZConfig.Instance.getString(Configuration.class, "app", "link-store", "");
    public static final String SCRIPT = ZConfig.Instance.getString(Configuration.class, "app", "script", "");
    public static String buildStoreLink (long pageID){
        return String.format(LINK_STORE + "%s" , pageID);
    }
    public static enum LISTING_TYPE {

        CATEGORY(0),PROMOTE(1);

        private int val;

        private LISTING_TYPE(int val) {
            this.val = val;
        }

        public void setVal(int val) {
            this.val = val;
        }
        
        public int getVal() {
            return this.val;
        }
    }

    private static final String JS = ZConfig.Instance.getString(Configuration.class, "static", "js", "");

    public static String getDomain() {
        return Configuration.APP_DOMAIN;
    }

    public static String getStaticDomain() {
        return Configuration.STATIC_DOMAIN;
    }

    public static int getNumItemPerPage() {
        return Configuration.NUM_ITEM_PER_PAGE;
    }

    public static String getAppVersion() {
        return Configuration.APP_VERSION;
    }

    public static String getJS() {
        return Configuration.JS;
    }

    public static String getAppName() {
        return Configuration.APP_NAME;
    }

    public static int getNumRenderItem() {
        return NUM_RENDER_ITEM;
    }

    public static int getNumCacheItem() {
        return NUM_CACHE_ITEM;
    }

    public static int getShowedCate() {
        return SHOWED_CATE;
    }

    public static int getBannerNumber() {
        return BANNER_NUMBER;
    }

    public static int getPromotionNumber() {
        return PROMOTION_NUMBER;
    }

}
