package org.mqtt;

//import org.mqtt.MqttPublish;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttConnection {
	private String address;
	
	private String port;
	
	private String clientID;

	protected static MqttClient client;
	
	private MemoryPersistence persistence = new MemoryPersistence();
	
	private boolean isCleanSession = false;
	
	private static boolean connectionStatus;
	
	//---------------------------------------------------Public Method---------------------------------------------
	
	/**
	 * Constructor
	 * @param address The address of broker
	 * @param port The connection port
	 * @param isVolatile 
	 */
	public MqttConnection (String address, String port, String clientID, boolean isCleanSession) {
		this.address = address;
		this.port = port;
		this.clientID = clientID;
		this.isCleanSession = isCleanSession;
	}
	
	public void Connect() {
		String broker = address + ":" + port;
		System.out.println("Broker address: " + broker);
		
		try {
			if(isCleanSession) {
				client = new MqttClient(broker, clientID, persistence);
				System.out.println("Setting up MqttClient in a Persistence memory mode");
			}
			
			else {
				client = new MqttClient(broker, clientID);
				System.out.println("Setting up MqttClient in a non-volatile memory mode");
			}
			System.out.println("Connecting...");
			client.connect();
			
			connectionStatus = client.isConnected();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void Disconnect() {
		try {
			client.disconnect();
			System.out.println("Connection Ended");
			connectionStatus = client.isConnected();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isConnected () {
		return connectionStatus;
	}
	
	public static MqttClient getClient() {
		return client;
	}
}
