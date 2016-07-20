/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.education.common;

import com.vng.zing.stats.Profiler;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.Checksum;
import java.util.zip.GZIPOutputStream;

/**
 *
 * @author thangnc
 */
public class UtilHelper {

    public static byte[] gzipString(String str) throws IOException {
        Profiler.getThreadProfiler().push(UtilHelper.class, "gzipString");
        try {
            if (str == null || str.length() == 0) {
                return null;
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream(str.length());
            GZIPOutputStream gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes());

            gzip.close();
            out.close();

            return out.toByteArray(); // I would return compressedBytes instead String
        } finally {
            Profiler.getThreadProfiler().pop(UtilHelper.class, "gzipString");
        }
    }

    public static long checksum(byte[] bytes) {
        Checksum checksum = new CRC32();
        checksum.update(bytes, 0, bytes.length);
        return checksum.getValue();
    }

    public static List<String> strToListStr(String str, String seperater) {
        List<String> result = new ArrayList<>();
        for (String subStr : str.split(seperater)) {
            String value = subStr.trim();
            if (value.isEmpty() == false) {
                result.add(value);
            }
        }
        return result;
    }
}
