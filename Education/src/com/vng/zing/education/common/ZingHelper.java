package com.vng.zing.education.common;

import com.vng.zing.jni.zcommonx.ZCommonX;
import com.vng.zing.jni.zcommonx.zcommon__IntegralHolder;
import com.vng.zing.jni.zcommonx.zcommon__StrHolder;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import nl.flotsam.xeger.Xeger;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.thrift.TBase;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 *
 * @author anhlh
 */
public class ZingHelper {

    private final static String base64chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    private static final Xeger xeger = new Xeger("[a-zA-Z0-9]{20}");

    public static List<Integer> Shuffle(List<Integer> list) {
        List<Integer> result = new ArrayList<Integer>(list);
        Random rand = new Random();
        for (int index = 0; index < list.size(); ++index) {
            int i = rand.nextInt() % list.size();
            if (i < 0) {
                i = -i;
            }
            int temp = result.get(index);
            result.set(index, result.get(i));
            result.set(i, temp);
        }
        return result;
    }

    public static long getTime(String strDate, String format) {
        if (format == null) {
            format = "dd/MM/yyyy HH:mm:ss";
        }
        long result = 0;
        try {
            if (strDate != null && !strDate.isEmpty()) {
                DateFormat formatter;
                Date date;
                formatter = new SimpleDateFormat(format);
                date = (Date) formatter.parse(strDate);
                result = date.getTime();
            } else {
                result = System.currentTimeMillis();
            }
        } catch (ParseException e) {
            System.out.println("Exception :" + e);
            return result;
        }
        return result;
    }

    public static Calendar getCalendarFromDate(String date) {
        String[] arrDate = date.split("/");
        if (arrDate != null && arrDate.length == 3) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_MONTH, 1);
            cal.set(Calendar.MONTH, Integer.valueOf(arrDate[1]) - 1);
            cal.set(Calendar.YEAR, Integer.valueOf(arrDate[2]));
            cal.set(Calendar.DATE, Integer.valueOf(arrDate[0]));
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            return cal;
        }
        return null;
    }

    public static String getStrDate(long time, String format) {
        if (format == null || format.isEmpty()) {
            format = "dd - MM - yyyy";
        }
        DateFormat dateFormat = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        return dateFormat.format(cal.getTime());
    }

    public static int getDayOfTime(long time) {
        int result = (int) (time / 86400000);
        return result;
    }

    public static String escapeHTML(String html) {
        if (html != null) {
            return html.replaceAll("\\<.*?\\>", "");
        }
        return "";
    }

    public static String subWord(String str, int countCharacter) {
        String rs = str;
        if (str != null && str.length() > countCharacter) {
            rs = str.substring(0, countCharacter);
            int pos = rs.lastIndexOf(" ");
            if (pos > 0) {
                rs = rs.substring(0, pos);
                rs += " ...";
            }
        }
        return rs;
    }

    public static String htmlEncoder(String str) {
        if (str == null) {
            return "";
        }
        String rs = str;
        rs = rs.replace("&", "&#38");
        rs = rs.replace("<", "&#60");
        return rs;
    }

    public static String htmlEncoder2(String s) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c > 127 || c == '"' || c == '<' || c == '>' || c == '\'' || c == '&') {
                out.append("&#").append((int) c).append(";");
            } else {
                out.append(c);
            }
        }
        return out.toString();
    }

    public static String textToHtml(String str) {
        if (str == null) {
            return "";
        }
        String rs = str;
        rs = rs.replace("\n", "<br/>");
//        rs = rs.replaceAll("((https?|ftp|file)://[^<>[:space:]]+[[:alnum:]/])", "<a href=\"$1\" target='_blank'>$1</a>");
//        rs = rs.replaceAll("(https?://[^<>[:space:]]+[[:alnum:]/])", "<a href=\"$1\" target='_blank'>$1</a>");
        rs = rs.replaceAll("(https?://.*[-a-zA-Z0-9])", "<a href=\"$1\" target='_blank'>$1</a>");
//        rs = rs.replaceAll("(https?://.*)", "<a href=\"$1\" target='_blank'>$1</a>");

        return rs;
    }

    public static byte[] longToByteArray(long data) {
        return new byte[]{
            (byte) (data >>> 56),
            (byte) (data >>> 48),
            (byte) (data >>> 40),
            (byte) (data >>> 32),
            (byte) (data >>> 24),
            (byte) (data >>> 16),
            (byte) (data >>> 8),
            (byte) (data)
        };
    }

    public static long byteArrayToLong(byte[] array) {
        return ((long) (array[0] & 0xff) << 56)
                | ((long) (array[1] & 0xff) << 48)
                | ((long) (array[2] & 0xff) << 40)
                | ((long) (array[3] & 0xff) << 32)
                | ((long) (array[4] & 0xff) << 24)
                | ((long) (array[5] & 0xff) << 16)
                | ((long) (array[6] & 0xff) << 8)
                | ((long) (array[7] & 0xff));
    }

    public static String getFileType(String filename) {
        String rs = "";
        int pos = filename.lastIndexOf(".");
        if (pos > -1) {
            rs = filename.substring(pos + 1, filename.length());
            rs = rs.toLowerCase();
        }
        return rs;
    }

    public static String filterUnicodeToASCII(String str) {
        String rs = "";
        String[] marTViet = new String[]{"à", "á", "ạ", "ả", "ã", "â", "ầ", "ấ", "ậ", "ẩ", "ẫ", "ă", "ằ", "ắ", "ặ", "ẳ", "ẵ", "è", "é", "ẹ", "ẻ", "ẽ", "ê", "ề", "ế", "ệ", "ể", "ễ", "ì", "í", "ị", "ỉ", "ĩ", "ò", "ó", "ọ", "ỏ", "õ", "ô", "ồ", "ố", "ộ", "ổ", "ỗ", "ơ", "ờ", "ớ", "ợ", "ở", "ỡ", "ù", "ú", "ụ", "ủ", "ũ", "ư", "ừ", "ứ", "ự", "ử", "ữ", "ỳ", "ý", "ỵ", "ỷ", "ỹ", "đ", "À", "Á", "Ạ", "Ả", "Ã", "Â", "Ầ", "Ấ", "Ậ", "Ẩ", "Ẫ", "Ă", "Ằ", "Ắ", "Ặ", "Ẳ", "Ẵ", "È", "É", "Ẹ", "Ẻ", "Ẽ", "Ê", "Ề", "Ế", "Ệ", "Ể", "Ễ", "Ì", "Í", "Ị", "Ỉ", "Ĩ", "Ò", "Ó", "Ọ", "Ỏ", "Õ", "Ô", "Ồ", "Ố", "Ộ", "Ổ", "Ỗ", "Ơ", "Ờ", "Ớ", "Ợ", "Ở", "Ỡ", "Ù", "Ú", "Ụ", "Ủ", "Ũ", "Ư", "Ừ", "Ứ", "Ự", "Ử", "Ữ", "Ỳ", "Ý", "Ỵ", "Ỷ", "Ỹ", "Đ"};
        String[] marKoDau = new String[]{"a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "i", "i", "i", "i", "i", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "u", "u", "u", "u", "u", "u", "u", "u", "u", "u", "u", "y", "y", "y", "y", "y", "d", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "E", "E", "E", "E", "E", "E", "E", "E", "E", "E", "E", "I", "I", "I", "I", "I", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "U", "U", "U", "U", "U", "U", "U", "U", "U", "U", "U", "Y", "Y", "Y", "Y", "Y", "D"};
        rs = str;
        for (int index = 0; index < marTViet.length; ++index) {
            rs = rs.replace(marTViet[index], marKoDau[index]);

        }
        return rs;
    }

    public static String strtrim(String str) {
        String rs = str;
        int length;
        do {
            length = rs.length();
            rs = rs.trim();
            rs = rs.replace("  ", " ");
        } while (length != rs.length());
        if (rs.length() == 1 && " ".equals(rs)) {
            rs = "";
        }
        return rs;
    }

    public static String base64Encode(String str) {
        String r = "", p = "";
        int c = str.length() % 3;

        if (c > 0) {
            for (; c < 3; c++) {
                p += "=";
                str += "\0";
            }
        }

        for (c = 0; c < str.length(); c += 3) {
            if (c > 0 && (c / 3 * 4) % 76 == 0) {
                r += "\r\n";
            }

            int n = (str.charAt(c) << 16) + (str.charAt(c + 1) << 8)
                    + (str.charAt(c + 2));

            int n1 = (n >> 18) & 63, n2 = (n >> 12) & 63, n3 = (n >> 6) & 63, n4 = n & 63;

            r += "" + base64chars.charAt(n1) + base64chars.charAt(n2)
                    + base64chars.charAt(n3) + base64chars.charAt(n4);
        }

        return r.substring(0, r.length() - p.length()) + p;

    }

    public static String base64Decode(String str) {
        str = str.replaceAll("[^" + base64chars + "=]", "");

        String p = (str.charAt(str.length() - 1) == '='
                ? (str.charAt(str.length() - 2) == '=' ? "AA" : "A") : "");
        String r = "";
        str = str.substring(0, str.length() - p.length()) + p;

        for (int c = 0; c < str.length(); c += 4) {
            int n = (base64chars.indexOf(str.charAt(c)) << 18)
                    + (base64chars.indexOf(str.charAt(c + 1)) << 12)
                    + (base64chars.indexOf(str.charAt(c + 2)) << 6)
                    + base64chars.indexOf(str.charAt(c + 3));

            r += "" + (char) ((n >>> 16) & 0xFF) + (char) ((n >>> 8) & 0xFF)
                    + (char) (n & 0xFF);
        }
        return r.substring(0, r.length() - p.length());
    }

    public static String encodeMedia(int userId, long itemId) {
        String str = userId + "-" + itemId;
        return ZingHelper.base64Encode(str);
    }

    public static long decodeMedia(String code) {
        long audioId = 0;
        String str = ZingHelper.base64Decode(code);
        String[] arrStr = str.split("-");
        if (arrStr.length > 1) {
            try {
                audioId = Long.valueOf(arrStr[1]);
            } catch (NumberFormatException ex) {
            }
        }
        return audioId;
    }

	public static String sha1(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(str.getBytes());
			byte byteData[] = md.digest();
			//convert the byte to hex format method 1
			return HexStringUtil.byteArrayToHexString(byteData);
		} catch (Exception ex) {
			return "";
		}
	}

	public static String sha256(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(str.getBytes());
			byte byteData[] = md.digest();
			//convert the byte to hex format method 1
			return HexStringUtil.byteArrayToHexString(byteData);
		} catch (Exception ex) {
			return "";
		}
	}

	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte byteData[] = md.digest();
			//convert the byte to hex format method 1
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (Exception ex) {
			return "";
		}
	}
    
    public static String md5(byte[] data) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(data);
			byte byteData[] = md.digest();
			//convert the byte to hex format method 1
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (Exception ex) {
			return "";
		}
	}

    public static String noise64(long itemId) {
        long secretKey = 509678724;
        zcommon__StrHolder data = new zcommon__StrHolder();
        ZCommonX.zcommon__noise64(data, itemId, secretKey);
        return data.getValue();
    }

    public static long denoise64(String id) {
        try {
            long secretKey = 509678724;
            zcommon__IntegralHolder value = new zcommon__IntegralHolder();
            zcommon__StrHolder data = new zcommon__StrHolder();
            data.setValue(id);
            ZCommonX.zcommon__denoise64(value, data, secretKey);
            return value.getValue();
        } catch (Exception ex) {
            return -1;
        }
    }

    public static String byteArrayToStr(byte[] bytes) {
        String result = "";
        Charset charset = Charset.defaultCharset();
        CharsetDecoder decoder = charset.newDecoder();
        CharBuffer cb;
        try {
            cb = decoder.decode(ByteBuffer.wrap(bytes));
            result = cb.toString();
        } catch (CharacterCodingException ex) {
        }
        return result;
    }

    public static byte[] serializer(TBase value) {
        byte[] result = null;
        TSerializer t = new TSerializer();
        try {
            result = t.serialize(value);
        } catch (TException ex) {
        }
        return result;
    }

    public static void deserialize(TBase value, byte[] data) {
        TDeserializer d = new TDeserializer();
        try {
            d.deserialize(value, data);
        } catch (TException ex) {
        }
    }

    public static String encodeURL(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (Exception ex) {
        }
        return "";
    }

    public static String decodeURL(String url) {
        try {
            return URLDecoder.decode(url, "UTF-8");
        } catch (Exception ex) {
        }
        return "";
    }

    public static String hashMediaName(String filename) {
        String rs = "";
        int pos = filename.lastIndexOf(".");
        if (pos > -1) {
            String name = filename.substring(0, pos);
            String type = filename.substring(pos + 1, filename.length());
            type = type.toLowerCase();

            name = ZingHelper.strtrim(name);
            name = ZingHelper.filterUnicodeToASCII(name);
            name = ZingHelper.md5(name);
            name += "_" + System.currentTimeMillis();
            rs = name + "." + type;
        }
        return rs;
    }

    public static ByteBuffer getByteBufferFromFile(String path) {
        ByteBuffer buff = null;

        File fcover = new File(path);
        if (fcover.exists()) {
            FileInputStream fileStream;
            try {
                fileStream = new FileInputStream(fcover);
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                byte[] read = new byte[2048];
                int i = 0;
                while ((i = fileStream.read(read)) > 0) {
                    byteArray.write(read, 0, i);
                }
                fileStream.close();
                buff = ByteBuffer.wrap(byteArray.toByteArray());
            } catch (Exception ex) {
            }
        }
        return buff;
    }

    private static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {

        int original_width = imgSize.width;
        int original_height = imgSize.height;
        int bound_width = boundary.width;
        int bound_height = boundary.height;
        int new_width = original_width;
        int new_height = original_height;

        // first check if we need to scale width
        if (original_width > bound_width) {
            //scale width to fit
            new_width = bound_width;
            //scale height to maintain aspect ratio
            new_height = (new_width * original_height) / original_width;
        }

        // then check if we need to scale even with the new height
        if (new_height > bound_height) {
            //scale height to fit instead
            new_height = bound_height;
            //scale width to maintain aspect ratio
            new_width = (new_height * original_width) / original_height;
        }

        return new Dimension(new_width, new_height);
    }

    public static byte[] resizeImage(ByteArrayInputStream data, int img_width, int img_height) {
        BufferedImage originalImage;
        try {
            originalImage = ImageIO.read(data);
            int type = (originalImage.getType() == 0) ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
            Dimension origDimentsion = new Dimension(originalImage.getWidth(), originalImage.getHeight());
            Dimension fitDimentsion = new Dimension(img_width, img_height);

            Dimension dimentsion = getScaledDimension(origDimentsion, fitDimentsion);
            if (origDimentsion.width != dimentsion.width || origDimentsion.height != dimentsion.height) {

                BufferedImage resizedImage = new BufferedImage(dimentsion.width, dimentsion.height, type);
                Graphics2D g = resizedImage.createGraphics();
                g.drawImage(originalImage, 0, 0, dimentsion.width, dimentsion.height, null);
                g.dispose();
                ByteArrayOutputStream outstream = new ByteArrayOutputStream();
                ImageIO.write(resizedImage, "png", outstream);
                return outstream.toByteArray();
            } else {
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                byte[] read = new byte[2048];
                int i = 0;
                data.reset();
                while ((i = data.read(read)) > 0) {
                    byteArray.write(read, 0, i);
                }
                data.close();
                return byteArray.toByteArray();
            }
        } catch (IOException ex) {
        }
        return null;
    }

    public static String xssRemove(String content) {
        if (content == null) {
            return "";
        }
        return content.replaceAll("(?i)<script.*?>.*?</script.*?>", "") // case 1
                .replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "") // case 2
                .replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");     // case 3
    }

    public static String safeStr(String content) {
        content = xssRemove(content);
        content = escapeHTML(content);
        content = strtrim(content);
        return content;
    }

    public static int dayOfMonth(int month, int year) {
        int day = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12: {
                day = 31;
                break;
            }
            case 4:
            case 6:
            case 9:
            case 11: {
                day = 30;
                break;
            }
            case 2: {
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                    day = 29;
                } else {
                    day = 28;
                }
                break;
            }
        }

        return day;
    }

    public static String dayOfWeek(int day) {
        switch (day) {
            case Calendar.SUNDAY: {
                return "Chủ Nhật";
            }
            case Calendar.MONDAY: {
                return "Thứ Hai";
            }
            case Calendar.TUESDAY: {
                return "Thứ Ba";
            }
            case Calendar.WEDNESDAY: {
                return "Thứ Tư";
            }
            case Calendar.THURSDAY: {
                return "Thứ Năm";
            }
            case Calendar.FRIDAY: {
                return "Thứ Sáu";
            }
            case Calendar.SATURDAY: {
                return "Thứ Bảy";
            }
            default: {
                return "";
            }
        }
    }

    public static String cutString(String text, int num) {
        if (text == null || num >= text.length()) {
            return text;
        }
        return text.substring(0, num) + "...";
    }

    public static String getIPAddress() {
        String ipAddr = "10.30.58.221";
        try {
            InetAddress ip = InetAddress.getLocalHost();
            // loopback address
            if (ip.isLoopbackAddress()) {
                // get all network interfaces
                Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
                while (interfaces.hasMoreElements()) {
                    NetworkInterface current = interfaces.nextElement();
                    if (!current.isUp() || current.isLoopback() || current.isVirtual()) {
                        continue;
                    }
                    Enumeration<InetAddress> addresses = current.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress currentAddr = addresses.nextElement();
                        if (currentAddr.isLoopbackAddress()) {
                            continue;
                        }
                        if (currentAddr.isMulticastAddress()) {
                            continue;
                        }
                        if (currentAddr.getHostAddress().startsWith("10.30")) {
                            ipAddr = currentAddr.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
        }

        return ipAddr;
    }

    public static String getServerName() {
        String prjName = "";
        try {
            prjName = System.getProperty("zappname");
            if ((prjName == null) || (prjName.isEmpty())) {
                prjName = System.getProperty("zname");
            } else if ((prjName == null) || (prjName.isEmpty())) {
                prjName = System.getProperty("ZNAME");
            }
            if ((prjName == null) || (prjName.isEmpty())) {
                prjName = "zalopagechat-mw";
            }
        } catch (Exception ex) {
        }
        return prjName;
    }

    public static byte[] getByteArrayFromByteBuffer(ByteBuffer bb) {
        byte[] img = Arrays.copyOfRange(bb.array(), bb.position(), bb.limit());
        return img;
    }

    public static String getRandomHash() {
        String ret = "";
        try {
            ret = xeger.generate();
        } catch (Exception ex) {

        }
        return ret;
    }

    public static String quote(String string) {
        if (string == null || string.length() == 0) {
            return "\"\"";
        }

        string = string.replaceAll("\'\'", "\"");
        char c = 0;
        int i;
        int len = string.length();
        StringBuilder sb = new StringBuilder(len + 4);
        String t;

        sb.append('"');
        for (i = 0; i < len; i += 1) {
            c = string.charAt(i);
            switch (c) {
                case '\\':
                case '"':
                    sb.append('\\');
                    sb.append(c);
                    break;
                case '/':
                    //                if (b == '<') {
                    sb.append('\\');
                    //                }
                    sb.append(c);
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                default:
                    if (c < ' ') {
                        t = "000" + Integer.toHexString(c);
                        sb.append("\\u" + t.substring(t.length() - 4));
                    } else {
                        sb.append(c);
                    }
            }
        }
        sb.append('"');
        return sb.toString();
    }

    public static String getStringFromMapData(Map<String, Object> mapData, String key, String defaultValue) {
        String result = defaultValue;
        if (mapData.get(key) != null) {
            try {
                result = safeStr(String.valueOf(mapData.get(key)));
            } catch (Exception e) {
            }

        }
        return result;
    }

    public static int getIntFromMapData(Map<String, Object> mapData, String key, int defaultValue) {
        int result = defaultValue;
        if (mapData.get(key) != null) {
            try {
                result = Integer.parseInt(mapData.get(key).toString());
            } catch (Exception ex) {

            }
        }
        return result;
    }

    public static long getLongFromMapData(Map<String, Object> mapData, String key, long defaultValue) {
        long result = defaultValue;
        if (mapData.get(key) != null) {
            try {
                result = Long.parseLong(mapData.get(key).toString());

            } catch (Exception ex) {

            }
        }
        return result;
    }

    public static FileItem getFileItemFromMapData(Map<String, Object> mapData, String key) {
        FileItem result = null;
        if (mapData.get(key) != null) {
            try {
                result = (FileItem) mapData.get(key);
            } catch (Exception ex) {

            }
        }
        return result;
    }

    public static String getLinkImage(long photoId) {
        long secretCode = 231075831;
        String noiseId = com.vng.zing.jni.zcommonx.wrapper.ZCommonX.noise64(photoId, secretCode);
        String domain = "http://photo.page.zdn.vn/";
        return String.format("%s%s", domain, noiseId);
    }

    public static String noiseIdWithPageId(long itemId, int pageId) {
        zcommon__StrHolder data = new zcommon__StrHolder();
        ZCommonX.zcommon__noise64(data, itemId, pageId);
        return data.getValue();
    }

    public static long denoiseIdWithPageId(String id, int pageId) {
        try {
            zcommon__IntegralHolder value = new zcommon__IntegralHolder();
            zcommon__StrHolder data = new zcommon__StrHolder();
            data.setValue(id);
            ZCommonX.zcommon__denoise64(value, data, pageId);
            return value.getValue();
        } catch (Exception ex) {
            return -1;
        }
    }

    public static boolean isIosDevide(HttpServletRequest req) {
        String userAgent = req.getHeader("User-Agent");
        if (userAgent == null) {
            return false;
        }
        boolean ret = (userAgent.toLowerCase().contains("ipad")
                || userAgent.toLowerCase().contains("iphone")
                || userAgent.toLowerCase().contains("ipod"));

        return ret;

    }


    
    public static String safeStrExceptTags(String content) {
        content = xssRemove(content);
        content = escapeHtmlExceptTags(content, new String[]{"strong", "em", "ul", "li"});
        content = strtrim(content);
        return content;
    }
    
    public static String escapeHtmlExceptTags(String unsafe, String[] tags) {
        Whitelist whitelist = Whitelist.none();
        whitelist.addTags(tags);

        String safe = Jsoup.clean(unsafe, whitelist);
        return StringEscapeUtils.unescapeXml(safe);
    }

}
