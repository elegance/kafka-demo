package org.orh.t1;

import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.orh.Constant;

public class CatConsumer {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in); // 从控制台得到输入，发送给broker
		System.out.println("请输入consumerName名称："); // fishConsumer1/fishConsumer2/fishConsumer3
		String consumerName = s.nextLine();
		
		System.out.println("请输入group名称："); // group1/group1/group2
		String groupId = s.nextLine();
		
		System.out.println("ok, consumer is ready...");
		
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(initProperties(groupId));
		consumer.subscribe(Arrays.asList("fish-topic"));

		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				System.out.printf("consumerName = %s, groupId = %s, offset = %d, key = %s, value = %s\n", consumerName,
						groupId, record.offset(), record.key(), record.value());
			}
		}
	}

	public static Properties initProperties(String groupId) {
		Properties props = new Properties();
		props.put("bootstrap.servers", Constant.ZK_SERVER);
		props.put("group.id", groupId);
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		return props;
	}
}
