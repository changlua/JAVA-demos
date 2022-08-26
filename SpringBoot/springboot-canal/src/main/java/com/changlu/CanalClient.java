package com.changlu;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.google.protobuf.ByteString;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @Description:
 * @Author: changlu
 * @Date: 9:28 AM
 */
public class CanalClient {

    public static void main(String[] args) throws Exception{
        //1、获取canal连接对象
        CanalConnector canalConnector = CanalConnectors.newSingleConnector(
                new InetSocketAddress("192.168.10.105", 11111),
                "example",
                "",
                ""
        );

        //2、使用连接器来进行连接，并进行订阅
        canalConnector.connect();
        canalConnector.subscribe("test.*");//订阅test数据库中的所有表

        //3、轮询get来获取message对象（无论获取多少条都会一次放置在一个Message对象中）
        while (true) {
            //4、一个Message中有多个entry，
            Message message = canalConnector.get(100);
            List<CanalEntry.Entry> entries = message.getEntries();
            if (entries.size() <= 0) {
                System.out.println("当前没有数据，休息一下...");
                Thread.sleep(1000);
            }else {
                //一个entry中包含表名、entry类型以及数据storevalue(需要进行反序列化)等其他
                for (CanalEntry.Entry entry : entries) {
                    String tableName = entry.getHeader().getTableName();//获取表名
                    CanalEntry.EntryType entryType = entry.getEntryType();//获取entry的类型
                    //在mysql中指定binlog存储的是row模式，这里来进行校验筛选
                    if (CanalEntry.EntryType.ROWDATA.equals(entryType)){
                        //storevalue中可以获取到事件类型、操作前后的数据
                        ByteString storeValue = entry.getStoreValue();
                        //反序列化
                        CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(storeValue);
                        //开始对反序列化的数据进行操作：包含事件类型以及数据
                        CanalEntry.EventType eventType = rowChange.getEventType();
                        List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();
                        for (CanalEntry.RowData rowData : rowDatasList) {
                            //原始数据
                            List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
                            JSONObject beforeData = buildColumns(beforeColumnsList);
                            //最新数据
                            List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
                            JSONObject afterData = buildColumns(afterColumnsList);
                            //输出数据
                            System.out.println("TableName:" + tableName
                                    +
                                    ",EventType:" + eventType +
                                    ",After:" + beforeData +
                                    ",After:" + afterData);
                        }
                    }else{
                        System.out.println(String.format("当前类型为%s，暂不支持！", entryType.toString()));
                    }
                }
            }

        }
    }

    public static JSONObject buildColumns(List<CanalEntry.Column> data) {
        JSONObject res = new JSONObject();
        for (CanalEntry.Column column : data) {
            res.put(column.getName(), column.getValue());
        }
        return res;
    }

}
