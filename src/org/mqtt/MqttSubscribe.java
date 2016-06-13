package org.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttSubscribe implements MqttCallback {
	private String topic;
	
	private MqttClient client;
	public MqttSubscribe (String topic) {
		this.topic = topic;
	}
	
	public void subscribe() {
		client = MqttConnection.getClient();
		try {
			client.setCallback(this);
			client.subscribe(topic);
			System.out.println("Subscribed to: " + topic);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void unsubscribe() {
		try {
			client.unsubscribe(topic);
			System.out.println("Unsubscribed to: " + topic);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println(message);
	}

	@Override
	public void connectionLost(Throwable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub
		
	}
}