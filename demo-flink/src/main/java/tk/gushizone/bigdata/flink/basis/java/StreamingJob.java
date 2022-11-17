package tk.gushizone.bigdata.flink.basis.java;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import tk.gushizone.bigdata.flink.FlinkJavaHelper;

public class StreamingJob {


    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> streamSource = env.readTextFile(FlinkJavaHelper.WC_TXT);
        streamSource.writeAsText(FlinkJavaHelper.WC_TXT + "-out").setParallelism(1);
        env.execute();

    }
}