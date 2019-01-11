package com.bonc.hbase;

import com.alibaba.fastjson.JSON;
import com.bonc.common.ConfHelper;
import com.bonc.common.TimeCoder;
import com.bonc.utils.DateHelper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author : xuelc
 * @Description:
 * @Date : Created in 09:30 2018/12/12.
 * @Modified By:
 */
public class HbaseTest {
    /**
     * 日志管理对象
     */
    private Logger logger = LoggerFactory.getLogger(HbaseTest.class);


    /**
     * Hbase 表名，列簇、列名
     */
    private static final String S_FLOW_DETAIL = "userflow";

    // 分析图标数据
    private static final String S_CHART_DETAIL = "datacount";

    // 列簇、列名
    private static final String FLOW_FAMILY = "f";
    private static final String FLOW_QUALIFIER = "q";

    /**
     * 换库并行时间
     */
    private static final String PARALLEL_HBASE_TIME_BEGIN=ConfHelper.getValue("PARALLEL_HBASE_TIME_BEGIN");
    private static final String PARALLEL_HBASE_TIME_END= ConfHelper.getValue("PARALLEL_HBASE_TIME_END");


    public static void main(String[] args) {
        String phone="15604364062";
        String beginTime="21081201000000";
        String endTime="21081212235959";
        System.out.println();
         //queryDetailByteList(phone,beginTime,endTime);
        System.out.println(DateHelper.parseDate(beginTime, "yyyyMMddHHmmss").plusDays(1).toString("yyyyMMddHHmmss"));
    }



    /**
     * 执行上网明细查询
     *
     * @param tablePrefix
     *            表名前缀
     * @param dateList
     *            日期集合
     * @param mobo5s
     *            电话号码byte
     * @param mobo_has
     *            电话号码hash码
     * @return 数据集合
     */
    private  List<byte[][]> doQueryDetail(String tablePrefix,
                                         List<String> dateList, byte[] mobo5s, byte[] mobo_has) {
        // 定义返回值
        List<byte[][]> resultList = Collections.synchronizedList(new ArrayList());
        // 线程集合

        List<Thread> threads = Lists.newArrayList();
        for (String date : dateList) {
            // 处理开始行结束行,以及表名
            byte[] startRow = Bytes.add(mobo_has, mobo5s,
                    Bytes.toBytes(TimeCoder.encode(date)));
            byte[] endRow = Bytes.add(
                    mobo_has,
                    mobo5s,
                    Bytes.toBytes(TimeCoder.encode(DateHelper
                            .parseDate(date, "yyyyMMddHHmmss").plusDays(1)
                            .toString("yyyyMMddHHmmss")) + 1));
            String tableName = tablePrefix + StringUtils.substring(date, 0, 6);

            // 根据zk地址，执行扫描查询
            String[] zkPaths = ConfHelper.getValue("C_ZKROOT").split(",");
            for (String zkpath : zkPaths) {
                // 实例化一个线程进行查询
                Thread thread = new Thread(new HTableScanner(
                        ConfHelper.getValue("C_QUORUM"),
                        ConfHelper.getValue("C_PORT"), zkpath, tableName,
                        startRow, endRow, resultList));
                thread.setName(zkpath + ":" + tableName + ":"
                        + Bytes.toHex(startRow) + "--"
                        + Bytes.toHex(endRow));
                thread.start();

                threads.add(thread);
            }
        }

            return resultList;
    }

    /**
     * 查询上网记录明细
     *
     * @param phone
     *            电话号码
     * @param beginTime
     *            开始时间
     * @param endTime
     *            结束时间
     * @return 字节数据集合
     */
    public  List<byte[][]> queryDetailByteList(String phone, String beginTime,
                                              String endTime) {
        // 处理rowkey hash
        byte[] mobo_hash = Bytes.toBytes((short) (phone.hashCode() & 0x7fff));
        byte[] mobo5s = Bytes.copy(Bytes.toBytes(Long.valueOf(phone)), 3, 5);
        // 处理开始时间与结束时间的天数
        List<String> dateList = getBetweenDays(DateHelper.parseDate(beginTime),
                DateHelper.parseDate(endTime), "yyyyMMddHHmmss");
        System.out.println(JSON.toJSONString(dateList));
        // 执行查询
        return doQueryDetail(S_FLOW_DETAIL, dateList, mobo5s, mobo_hash);
    }

    /**
     * 获取某两个时间点的所有天数
     *
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @param pattern   返回格式
     * @return 某两个时间点之间的所有天数
     */
    public static List<String> getBetweenDays(DateTime beginTime, DateTime endTime, String pattern) {
        List<String> retList = new ArrayList<String>();

        // 处理时间
        while (beginTime.isBefore(endTime)) {
            //System.out.println("61@@@"+beginTime.toString(pattern)+"@@@"+beginTime+"@@@"+endTime);
            retList.add(beginTime.toString(pattern));
            // 天数+1
            beginTime = beginTime.plusDays(1);
        }

        return retList;
    }



    /**
     * 扫描表的线程对象
     */
    private class HTableScanner implements Runnable {

        /**
         * 息主机连接信
         */
        private String quorum;

        /**
         * 端口号
         */
        private String port;

        /**
         * zk地址
         */
        private String zkpath;

        /**
         * 表名
         */
        private String tableName;

        /**
         * 开始行
         */
        private byte[] startRow;

        /**
         * 结束行
         */
        private byte[] endRow;

        /**
         * 结果集
         */
        private Collection<byte[][]> dataList;

        public HTableScanner(String quorum, String port, String zkpath,
                             String tableName, byte[] startRow, byte[] endRow,
                             Collection<byte[][]> dataList) {
            this.quorum = quorum;
            this.port = port;
            this.zkpath = zkpath;
            this.tableName = tableName;
            this.startRow = startRow;
            this.endRow = endRow;
            this.dataList = dataList;
        }

        /**
         * 执行扫描
         */
        @Override
        public void run() {
            logger.debug("do scan table:{}, zk: {}", tableName, zkpath);

            // 定义table
            HTableInterface hTable = null;
            ResultScanner rs = null;

            try {
                List<byte[][]> tempList = Lists.newLinkedList();

                // 记录时间
                long begin = System.currentTimeMillis();

                // 执行扫描
                Result result = null;
                hTable = genHtable();
                rs = hTable.getScanner(genScan());
                while (null != (result = rs.next())) {
                    tempList.add(new byte[][] {
                            result.getRow(),
                            result.getValue(Bytes.toBytes(FLOW_FAMILY),
                                    Bytes.toBytes(FLOW_QUALIFIER)) });
                }

                // 打印执行结果日志
                logger.info(
                        "zk: {}, table:{}, sacn times:{}, datasize:{}",
                        zkpath,
                        tableName,
                        TimeUnit.MILLISECONDS.toMillis(System
                                .currentTimeMillis() - begin), tempList.size());

                // 加入返回集合
                dataList.addAll(tempList);

            } catch (IOException e) {
                logger.error("do scan table:{}, zk: {} error!", tableName,
                        zkpath, e);
            } finally {
                if (null != rs) {
                    rs.close();
                }
                if (null != hTable) {
                    try {
                        hTable.close();
                    } catch (IOException e) {
                        // e.printStackTrace();
                    }
                }
            }
        }

        /**
         * 实例化Htable
         *
         * @return Htable
         * @throws IOException
         */
        private HTableInterface genHtable() throws IOException {
            Configuration conf = HBaseConfiguration.create();

            conf.set("hbase.zookeeper.quorum", quorum);
            conf.set("hbase.zookeeper.property.clientPort", port);
            conf.set("zookeeper.session.timeout", "900000");
            conf.set("zookeeper.znode.parent", zkpath);

            return new HTable(conf, tableName);
        }

        /**
         * 实例化一个扫描器
         *
         * @return Sacn对象
         */
        private Scan genScan() {
            // 并实例化一个扫描器
            Scan scan = new Scan(startRow, endRow);
            scan.setCaching(1024);
            // scan.setBatch(5000);

            return scan;
        }
    }
}