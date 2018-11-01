/**
 * NAME        : EVINGTONE NGOA
 * TITLE       : RUMOR MONGERING - FIXED PROBABILITY
 * NODE        : 2
 * LANGUAGE    : JAVA
 **/
 
import java.io.*;  
import java.net.*;  
import java.util.*;

public class MulticastSocketNodeTwo 
{ 
	public static final int NODE_PORT_1 = 0001;
	public static final int NODE_PORT_2 = 0002;
	public static final int NODE_PROPAGATION_PORT_6 = 0006;
	public static final int NODE_NEIGHBOR_PORT_7 = 0007;
	public static final String IP = "127.0.0.1";
	
	//Constructor
	public MulticastSocketNodeTwo(String ip_address, int NODE_PORT_1, int NODE_PROPAGATION_PORT_6) 
	{
		 
		Scanner scanner = new Scanner(System.in);
			
		try
		{  
			/**
			 * identity port for current node_2 Socket
			 * Node_2
			 **/
			ServerSocket p = new ServerSocket(NODE_PORT_2);
			while(true){
			//Accepts communication from propagation Handler
			Socket socketserverResponse = p.accept();//establishes connection 
			System.out.println("Received Response from propagation Handler!"); 	

			//Gets Response from propagation Handler
			DataInputStream res = new DataInputStream(socketserverResponse.getInputStream()); 
			String msg = res.readUTF();
			
			//Prompts the Response
			System.out.println("Message received from handler : " + msg);
			
			int max = 2;
			int min = 1;
			
			//Msg to be sent
			String fromNode = "node2";
			String toNode = "";
			
			//Random Nodes
			Random rand = new Random();
			int randomNode = rand.nextInt((max - min) + 1) + min;
			System.out.println("Node Chosen between 2 and 3:  -> " + randomNode);
			System.out.println("The message sent ->" + msg);
			
			// SInce current Node 2 is connected by 3 neighbors directly, we need to
			// randomize according to 3 nodes hence give only 3 values to randomize but 
			// each value represent node that surrounds it
			//Neigbours [4 & 5 since current is 2] chosen randomly 
			switch(randomNode){
				 case 1 :
					 toNode ="node4";
					 break;
				 case 2 : 
					 toNode ="node5";
					 break;
				 default :
					 System.out.println("Invalid node : ");
					 break; 
			}
			
			System.out.println("The message sent from node ->" + fromNode + " to " + toNode);
			
			//initiates propagation Handler to send data
			System.out.println("initiating connection with handler!"); 
			Socket q = new Socket(ip_address, NODE_PROPAGATION_PORT_6);  
			DataOutputStream dout=new DataOutputStream(q.getOutputStream()); 
			//sends to propagation handler to send to opted node
			dout.writeUTF(msg);  
			dout.writeUTF(fromNode);
			dout.writeUTF(toNode);
		}//end while

			
		}
		catch(Exception e)
		{
			System.out.println(e);
		} 
	}
	
	/**
	 * Main Program
	 **/
	public static void main(String[] args) 
	{  
		 MulticastSocketNodeTwo node_2 = new MulticastSocketNodeTwo(IP , NODE_PORT_1, NODE_PROPAGATION_PORT_6); 
	}  
}  