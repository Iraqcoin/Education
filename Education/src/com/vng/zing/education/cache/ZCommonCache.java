/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.education.cache;

import com.vng.zing.common.CompressMed;
import com.vng.zing.common.IZStructor;
import com.vng.zing.common.ZCommonDef;
import com.vng.zing.configer.ZConfig;
import com.vng.zing.jni.zicache.ZiCache;
import com.vng.zing.zidb.thrift.TValue;
import org.apache.log4j.Logger;

/**
 *
 * @author anhlh
 */
public class ZCommonCache {

    private static final Logger _logger = Logger.getLogger(ZCommonCache.class);
    protected final String _name;
    protected ZiCache<String, TValue> _innerCache;
    protected final IZStructor<String> _keyStructor = new IZStructor.StringStructor();
    protected final IZStructor<TValue> _valueStructor = new TValueStructor();

    public static class TValueStructor implements IZStructor<TValue> {

        @Override
        public TValue ctor() {
            return new TValue();
        }
    }

    public ZCommonCache(String name) {
        _name = name;
        initCache();
    }

    private void initCache() {
        long tblSize = ZConfig.Instance.getLong(ZCommonCache.class, _name, "tbl_size", ZCommonDef.CacheTblSizeDefault);
        long lruSize = ZConfig.Instance.getLong(ZCommonCache.class, _name, "lru_size", ZCommonDef.CacheLruSizeDefault);
        long maxNfSize = ZConfig.Instance.getLong(ZCommonCache.class, _name, "max_size_queue_load", ZCommonDef.CacheMaxSizeQueueLoadDefault);
        int nloadsAtime = ZConfig.Instance.getInt(ZCommonCache.class, _name, "nloads_atime", ZCommonDef.CacheNLoadsAtimeDefault);
        int nthreadsLoad = ZConfig.Instance.getInt(ZCommonCache.class, _name, "nthreads_load", ZCommonDef.CacheNThreadsLoadDefault);
        int expSeconds = ZConfig.Instance.getInt(ZCommonCache.class, _name, "item_exp_seconds", ZCommonDef.CacheItemExpSecondsDefault);
        String strCompressMed = ZConfig.Instance.getString(ZCommonCache.class, _name, "compress_med", ZCommonDef.CacheCompressMedDefault.toString());
        CompressMed compressMed = CompressMed.findByName(strCompressMed);
        if (compressMed == null) {
            compressMed = CompressMed.NOCOMP;
        }
        int thresholdComp = ZConfig.Instance.getInt(ZCommonCache.class, _name, "threshold_comp", ZCommonDef.CacheThresholdCompDefault);
        //
        _innerCache = new ZiCache<>(_keyStructor, _valueStructor, tblSize, lruSize);

        _innerCache.setMaxNfSize(maxNfSize); //native invoke
        _innerCache.setNumLoadsAtime(nloadsAtime);
        _innerCache.setNumThreadsLoad(nthreadsLoad);
        _innerCache.setExpTime(expSeconds); //native invoke
        _innerCache.setCompressMed(compressMed); //native invoke
        _innerCache.setThresholdComp(thresholdComp); //native invoke
        //
        //
        tblSize = _innerCache.getTblSize();
        lruSize = _innerCache.getLruSize();
        maxNfSize = _innerCache.getMaxNfSize();
        nloadsAtime = _innerCache.getNumLoadsAtime();
        nthreadsLoad = _innerCache.getNumThreadsLoad();
        expSeconds = _innerCache.getExpTime();
        compressMed = _innerCache.getCompressMed();
        thresholdComp = _innerCache.getThresholdComp();
        //
        _logger.info("initCache("
                + "tblSize=" + tblSize
                + ", lruSize=" + lruSize
                + ", maxNfSize=" + maxNfSize
                + ", nloadsAtime=" + nloadsAtime
                + ", nthreadsLoad=" + nthreadsLoad
                + ", expSeconds=" + expSeconds
                + ", compressMed=" + compressMed
                + ", thresholdComp=" + thresholdComp
                + ")");
        
       
    }

    public boolean putCache(String key, TValue value, com.vng.zing.zcommon.thrift.PutPolicy policy) {
        return _innerCache.put(key, value, com.vng.zing.common.PutPolicy.fromTPutPolicy(policy));
    }

    public boolean putCache(String key, TValue value, int expSeconds, com.vng.zing.zcommon.thrift.PutPolicy policy) {
        return _innerCache.put(key, value, true, expSeconds, com.vng.zing.common.PutPolicy.fromTPutPolicy(policy));
    }

    public boolean removeCache(String key) {
        return _innerCache.remove(key);
    }

    public TValue getCache(String key) {
        return _innerCache.get(key);
    }

    public boolean existCache(String key) {
        return _innerCache.exist(key, true);
    }

}
