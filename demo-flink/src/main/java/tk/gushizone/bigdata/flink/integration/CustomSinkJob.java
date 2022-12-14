package tk.gushizone.bigdata.flink.integration;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import tk.gushizone.bigdata.flink.integration.bean.Employee;
import tk.gushizone.bigdata.flink.integration.sink.FlinkToMySQLSink;

import java.sql.Date;

public class CustomSinkJob {

    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        Date date = new Date(System.currentTimeMillis());
        DataStreamSource<Employee> streamSource = env.fromElements(
                new Employee("hei", 10, date),
                new Employee("bai", 20, date),
                new Employee("ying", 30, date));
        streamSource.addSink(new FlinkToMySQLSink());
        env.execute();
    }
}