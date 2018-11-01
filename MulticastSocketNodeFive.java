/**
 * NAME        : EVINGTONE NGOA
 * TITLE       : RUMOR MONGERING - FIXED PROBABILITY
 * NODE        : 5
 * LANGUAGE    : JAVA
 **/
 
import java.io.*;  
import java.net.*;  
import java.util.*;

public class MulticastSocketNodeFive 
{ 
	public static final int NODE_PORT_3 = 0003;
	public static final int NODE_PORT_2 = 0002;
	public static final int NODE_PORT_5 = 0005;
	public static final int NODE_PORT_1 = 0001;
	public static final int NODE_PROPAGATION_PORT_6 = 0006;
	public static final int NODE_NEIGHBOR_PORT_7 = 0007;
	public static final String IP = "127.0.0.1";
	
	//Constructor
	public MulticastSocketNodeFive(String ip_address, int NODE_PORT_1, int NODE_PROPAGATION_PORT_6) 
	{
		 
		Scanner scanner = new Scanner(System.in);
			
		try
		{  
			/**
			 * identity port for current node_5 Socket
			 * Node_3
			 **/
			ServerSocket p = new ServerSocket(NODE_PORT_5);
		while(true){
			//Accepts communication from propagation Handler
			Socket socketserverResponse = p.accept();//establishes connection 
			System.out.println("Received Response from propagation Handler!"); 	

			//Gets Response from propagation Handler
			DataInputStream res = new DataInputStream(socketserverResponse.getInputStream()); 
			String msg = res.readUTF();
			
			//Prompts the Response
			System.out.println("Message received from handler : " + msg);
			
			int max = 3;// Represents node 3
			int min = 2;//node 2
			
			//Msg to be sent
			String fromNode = "node5";
			String toNode = "";
			
			//Random Nodes
			Random rand = new Random();
			int randomNode = rand.nextInt((max - min) + 1) + min;
			System.out.println("Node Chosen between 1[Node1] and 2[Node5]:  -> " + randomNode);
			System.out.println("The message sent ->" + msg);
			
			// SInce current Node 3 is connected by 2 neighbors directly, we need to
			// randomize according to 2 nodes hence give only 2 values to randomize but 
			// each value represent node that surrounds it
			//Neigbours [1 & 5 since current is 3] chosen randomly 
			switch(randomNode){
				 case 2 :
					 toNode ="node2";
					 break;
				 case 3 : 
					 toNode ="node3";
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
		 MulticastSocketNodeFive node_5 = new MulticastSocketNodeFive(IP , NODE_PORT_1, NODE_PROPAGATION_PORT_6); 
	}  
}  