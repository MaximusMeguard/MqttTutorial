/**************************************************************************************
 *                                   MQTT TUTORIAL
 * This MQTT Tutorial has a redundant external service for connecting, publishing, and
 * subscribing. This is to allow users to see how to create this separate classes when
 * necessary for managing multiple personal services which have access to MQTT.
 * 
 *  @author MaximusMeguard
 *************************************************************************************/
package org.mqtt;

import org.mqtt.MqttConnection;
import org.mqtt.MqttPublish;

public class MqttTest {
	public static void main (String[] args) {
		//---------------------------------Connection Settings-------------------------------------
		String address = "tcp://localhost";
		String port = "1883";
		String clientID = "User1";
		boolean isCleanSession = true;
		int qos = 2;
		
		System.out.println("Starting MQTT test");
		
		/*
		 * Configure connection to the broker.
		 */
		MqttConnection connection = new MqttConnection(address, port, clientID, isCleanSession);
		
		/*
		 * Create new publishing service
		 * 1st Argument: Topic
		 * 2nd Argument: Message to publish
		 * 3rd Argument: Quality of Service
		 */
		MqttPublish mp = new MqttPublish("Topic", "Content", qos);
		
		/*
		 * Create new subscription service.
		 * Argument: Topic
		 */
		MqttSubscribe ms = new MqttSubscribe("Topic");
		
		/*
		 * Connect to the broker with the configured address and port.
		 */
		connection.Connect();
		
		/*
		 * Checking connection status with the broker.
		 */
		boolean status = connection.isConnected();
		System.out.println("isConnected: " + status);
		
		/*
		 * Subscribe to a topic.
		 */
		ms.subscribe();
		
		/*
		 * Publish a message to the broker.
		 */
		mp.Publish();
		
		/*
		 * Delay for 10s.
		 */
		try {
			Thread.sleep(10000);
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		/*
		 * Unsubscribe to topic.
		 */
		ms.unsubscribe();
		
		/*
		 * Delay for 10s.
		 */
		try {
			Thread.sleep(3000);
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		/*
		 * Disconnecting client with broker.
		 */
		connection.Disconnect();
		
		/*
		 * Checking connection status with the broker.
		 */
		status = connection.isConnected();
		System.out.println("isConnected: " + status);
	}
}
