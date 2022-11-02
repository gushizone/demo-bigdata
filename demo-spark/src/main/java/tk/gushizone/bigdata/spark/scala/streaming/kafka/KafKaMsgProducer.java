package tk.gushizone.bigdata.spark.scala.streaming.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author gushizone
 * @date 2022/10/31 15:16
 */
public class KafKaMsgProducer extends Thread {

    KafkaProducer<Integer, String> producer;
    /**
     * 主题
     */
    String topic;

    public KafKaMsgProducer(String topic) {
        // 构建连接配置
        Properties properties = new Properties();

        // 若要配多个服务器，用逗号隔开
        // 注：服务器要开放端口，若云服务器还要在server.properties配置内网IP和外网IP
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "spark-streaming-producer");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                IntegerSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());

        // 构造Client无非是：new 或 工厂模式
        producer = new KafkaProducer<>(properties);
        this.topic = topic;
    }

    @Override
    public void run() {
        int num = 0;
        while (num < 20) {
            try {
                String msg = "kafka practice msg: " + num;
                // 发送消息send()！！! 同步调用
                // Future.get()会阻塞，等待返回结果......
                RecordMetadata recordMetadata = producer.send(new ProducerRecord<>(topic, msg)).get();
                // 等上面get到结果了，才能执行这里
                System.out.println("offset: " + recordMetadata.offset() + " | " +
                        "partition: " + recordMetadata.partition() + " | " +
                        "topic: " + recordMetadata.topic());
                TimeUnit.SECONDS.sleep(2);
                num++;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // 传入test主题
        new KafKaMsgProducer("spark-streaming-topic").start();
    }
} 
