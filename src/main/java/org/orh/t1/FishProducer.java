package org.orh.t1;

import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.orh.Constant;

/**
 * 鱼生产者
 */
public class FishProducer {

	public static void main(String[] args) {
		Producer<String, String> producer = new KafkaProducer<>(initProperties());

		String topic = "fish-topic"; // 鱼主题

		Scanner s = new Scanner(System.in); // 从控制台得到输入，发送给broker
		System.out.println("请输入要生产的鱼：");

		while (true) {
			String line = s.nextLine();
			System.out.println("生产鱼：" + line);
			producer.send(new ProducerRecord<String, String>(topic, line));

			if (line.equals("exit")) {
				break;
			}
			System.out.println("请输入要生产的鱼：");
		}
		s.close();
		producer.close();
	}

	public static Properties initProperties() {
		Properties props = new Properties();
		props.put("bootstrap.servers", Constant.ZK_SERVER);
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		return props;
	}
}
