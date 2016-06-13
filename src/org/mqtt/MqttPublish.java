package org.mqtt;

import org.mqtt.MqttConnection;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

public class MqttPublish{
	//-------------------------------Private Method--------------------------------------
	private String topic;
	
	private String msg;
	
	private int qos;
	
	protected MqttClient client = null;
	//-------------------------------Public Method---------------------------------------
	/**
	 * Constructor
	 * @param msg The content to be published
	 */
	public MqttPublish (String topic, String msg, int qos) {
		this.topic = topic;
		this.msg = msg;
		this.qos = qos;
	}
	
	
	public void Publish() {
		client = MqttConnection.getClient();
		MqttMessage message = new MqttMessage();
		message.setPayload(msg.getBytes());
		message.setQos(qos);
		
		System.out.println("Publishing message");
		try {
			client.publish(topic, message);
			System.out.println("Message published");
		} catch (MqttPersistenceException e) {
			e.printStackTrace();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
}
