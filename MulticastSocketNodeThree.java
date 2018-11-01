/**
 * NAME        : EVINGTONE NGOA
 * TITLE       : RUMOR MONGERING - FIXED PROBABILITY
 * NODE        : 3
 * LANGUAGE    : JAVA
 **/
 
import java.io.*;  
import java.net.*;  
import java.util.*;

public class MulticastSocketNodeThree 
{ 
	public static final int NODE_PORT_3 = 0003;
	public static final int NODE_PORT_1 = 0001;
	public static final int NODE_PORT_5 = 0005;
	public static final int NODE_PROPAGATION_PORT_6 = 0006;
	public static final int NODE_NEIGHBOR_PORT_7 = 0007;
	public static final String IP = "127.0.0.1";
	
	//Constructor
	public MulticastSocketNodeThree(String ip_address, int NODE_PORT_1, int NODE_PROPAGATION_PORT_6) 
	{
		 
		Scanner scanner = new Scanner(System.in);
			
		try
		{  
			/**
			 * identity port for current node_3 Socket
			 * Node_3
			 **/
			ServerSocket p = new ServerSocket(NODE_PORT_3);
		while(true){
			//Accepts communication from propagation Handler
			Socket socketserverResponse = p.accept();//establishes connection 
			System.out.println("Received Response from propagation Handler!"); 	

			//Gets Response from propagation Handler
			DataInputStream res = new DataInputStream(socketserverResponse.getInputStream()); 
			String msg = res.readUTF();
			
			//Prompts the Response
			System.out.println("Message received from handler : " + msg);
			
			int max = 2;// Represents node 5 since there is no randomising with only given 2 numbers but only range of given values
			int min = 1;//node 1
			
			//Msg to be sent
			String fromNode = "node3";
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
				 case 1 :
					 toNode ="node1";
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
		 MulticastSocketNodeThree node_3 = new MulticastSocketNodeThree(IP , NODE_PORT_1, NODE_PROPAGATION_PORT_6); 
	}  
}  