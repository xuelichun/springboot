package com.bonc.common;

import org.apache.hadoop.hbase.util.Bytes;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * <p>Title: BONC - ConvertHelper </p>
 * 
 * <p>Description: 数据转换的辅助类 </p>
 * 
 * <p>Copyright: Copyright BONC(c) 2013 - 2025 </p>
 * 
 * <p>Company: 北京东方国信科技股份有限公司 </p>
 * 
 * @author yangdx
 * @version 1.0.0
 */
public class ConvertHelper {

    /**
     * 字节列
     */
    private static final String[][] BYTE_COL = new String[][]{
            {"mobile", "r", "2", "03"},
            {"startTime", "r", "7", "01"},
            {"startTimess", "v", "2", "2"},
            {"endTime", "v", "6", "01"},
            {"endTimess", "v", "10", "2"},
            {"flowType", "v", "0", "1"},
            {"elspTimes", "v", "14", "2"},
            {"flowUp", "v", "18", "2"},
            {"flowDown", "v", "22", "2"},
            {"totalFlow", "v", "26", "2"},
            {"RATType", "v", "30", "0"},
            {"terminalIP", "v", "31", "02"},
            {"visitIP", "v", "35", "02"},
            {"statusCode", "v", "39", "1"},
            {"sgsnIP", "v", "41", "02"},
            {"ggsnIP", "v", "45", "02"},
            {"srcPort", "v", "49", "4"},
            {"desPort", "v", "51", "4"},
            {"recordIp", "v", "53", "0"},
            {"mergeCnt", "v", "54", "0"},
    };
    /**
     * 字节列
     */
    private static final String[][] BYTE_COL_NEW = new String[][]{
            {"mobile", "r", "2", "03"},
            {"startTime", "r", "7", "01"},
            {"startTimess", "v", "2", "2"},
            {"endTime", "v", "6", "01"},
            {"endTimess", "v", "10", "2"},
            {"flowType", "v", "0", "1"},
            {"elspTimes", "v", "14", "2"},
            {"flowUp", "v", "18", "8"},
            {"flowDown", "v", "26", "8"},
            {"totalFlow", "v", "34", "8"},
            {"RATType", "v", "42", "0"},
            {"terminalIP", "v", "43", "02"},
            {"visitIP", "v", "47", "02"},
            {"statusCode", "v", "51", "1"},
            {"sgsnIP", "v", "53", "02"},
            {"ggsnIP", "v", "57", "02"},
            {"srcPort", "v", "61", "4"},
            {"desPort", "v", "63", "4"},
            {"recordIp", "v", "65", "0"},
            {"mergeCnt", "v", "66", "0"},
    };
    /**
     * 字符串列
     */
    private static final String[] S_COL_STR = new String[]{"56", "areaCode", "CINumber", "terminalType", "userAgent", "APN", "IMSI", "contentType", "featInfo"};
    private static final String[] S_COL_STR_NEW = new String[]{"68", "areaCode", "CINumber", "terminalType", "userAgent", "APN", "IMSI", "contentType", "featInfo"};

    /**
     * 字节数据转为对应的map
     *
     * @param byteCol 字节列
     * @param strCol  字符串列
     * @param b       数据对象
     * @return 转换后的map对象
     */
    public static Map<String, String> byte2Map(String[][] byteCol, String[] strCol, byte[][] b) {
        Map<String, String> map = new HashMap<String, String>();

        for (int i = 0; i < byteCol.length; i++) {
            if ("03".equals(byteCol[i][3])) {
                map.put(byteCol[i][0], bytestPhone(Bytes.copy(b["r".equals(byteCol[i][1]) ? 0 : 1], Integer.parseInt(byteCol[i][2]), 5)));
            }

            if ("01".equals(byteCol[i][3])) {
                map.put(byteCol[i][0], TimeCoder.decode(Bytes.toInt((Bytes.copy(b["r".equals(byteCol[i][1]) ? 0 : 1], Integer.parseInt(byteCol[i][2]), 4)))));
            }

            if ("02".equals(byteCol[i][3])) {
                map.put(byteCol[i][0], bytes2Ip((Bytes.copy(b["r".equals(byteCol[i][1]) ? 0 : 1], Integer.parseInt(byteCol[i][2]), 4))));
            }

            if ("0".equals(byteCol[i][3])) {
                map.put(byteCol[i][0], "" + (Bytes.copy(b["r".equals(byteCol[i][1]) ? 0 : 1], Integer.parseInt(byteCol[i][2]), 1))[0]);
            }

            if ("1".equals(byteCol[i][3])) {
                map.put(byteCol[i][0], "" + Bytes.toShort((Bytes.copy(b["r".equals(byteCol[i][1]) ? 0 : 1], Integer.parseInt(byteCol[i][2]), 4))));
            }

            if ("2".equals(byteCol[i][3])) {
                map.put(byteCol[i][0], "" + Bytes.toInt((Bytes.copy(b["r".equals(byteCol[i][1]) ? 0 : 1], Integer.parseInt(byteCol[i][2]), 4))));
            }
            if ("4".equals(byteCol[i][3])) {
                map.put(byteCol[i][0], "" + Bytes.toInt(Bytes.add(new byte[]{0, 0}, Bytes.copy(b["r".equals(byteCol[i][1]) ? 0 : 1], Integer.parseInt(byteCol[i][2]), 4))));
            }
            if("8".equals(byteCol[i][3])){
                map.put(byteCol[i][0], ""+ Bytes.toLong((Bytes.copy(b["r".equals(byteCol[i][1])?0:1],Integer.parseInt(byteCol[i][2]),8))));
            }
        }

        String s = new String(Bytes.copy(b[1], Integer.parseInt(strCol[0]), b[1].length - Integer.parseInt(strCol[0])));
        if (s != null && !"".equals(s)) {
            for (int i = 1; i < strCol.length; i++) {
                String[] str = s.split("\\|", -1);
                map.put(strCol[i], str[i - 1]);
            }
        }

        return map;
    }

    /**
     * 字节数据转转成对应的map对象
     *
     * @param data 字节数据
     * @return Map对象
     */
    public static Map<String, String> byte2Map(byte[][] data, String flag) {
        if (flag.equals("new")){
            return byte2Map(BYTE_COL_NEW, S_COL_STR_NEW, data);
        }
        return byte2Map(BYTE_COL, S_COL_STR, data);
    }
    public static Map<String, String> byte2Map(byte[][] data) {
        return byte2Map(BYTE_COL, S_COL_STR, data);
    }
    /**
     * 字节数据转为电话号码
     *
     * @param phone 数据byte
     * @return 电话号码
     */
    public static String bytestPhone(byte[] phone) {
        byte[] no0 = Bytes.toBytes(0L);
        long b = Bytes.toLong(Bytes.add(Bytes.copy(no0, 0, 3), Bytes.copy(phone, 0, 5)));

        String msisdn = Long.toString(b);
        return msisdn;
    }

    /**
     * 字节数组转换成IP
     *
     * @param bytes 字节数据
     * @return IP地址
     */
    public static String bytes2Ip(byte[] bytes) {
        int temp = Bytes.toInt(bytes);
        StringBuilder sb = new StringBuilder();
        sb.append((temp >> 24) & 0xFF)
                .append('.')
                .append((temp >> 16) & 0xFF)
                .append('.')
                .append((temp >> 8) & 0xFF)
                .append('.')
                .append(temp & 0xFF);

        return sb.toString();
    }

}





