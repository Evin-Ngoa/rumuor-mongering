/**
 * NAME        : EVINGTONE NGOA MWAILONGO
 * REG         : P15/55580/2012
 * TITLE       : RUMOR MONGERING - FIXED PROBABILITY
 * INSTITUTION : UNIVERSITY OF NAIROBI
 * UNIT        : DISTRIBUTED SYSTEMS
 * CODE        : CSC 315
 * NODE        : 4
 * LANGUAGE    : JAVA
 **/
 
import java.io.*;  
import java.net.*;  
import java.util.*;

public class MulticastSocketNodeFour 
{ 
	public static final int NODE_PORT_4 = 0004;
	public static final int NODE_PORT_1 = 0001;
	public static final int NODE_PORT_2 = 0002;
	public static final int NODE_PROPAGATION_PORT_6 = 0006;
	public static final int NODE_NEIGHBOR_PORT_7 = 0007;
	public static final String IP = "127.0.0.1";
	
	//Constructor
	public MulticastSocketNodeFour(String ip_address, int NODE_PORT_1, int NODE_PROPAGATION_PORT_6) 
	{
		 
		Scanner scanner = new Scanner(System.in);
			
		try
		{  
			/**
			 * identity port for current node_4 Socket
			 * Node_4
			 **/
			ServerSocket p = new ServerSocket(NODE_PORT_4);
		while(true){
			//Accepts communication from propagation Handler
			Socket socketserverResponse = p.accept();//establishes connection 
			System.out.println("Received Response from propagation Handler!"); 	

			//Gets Response from propagation Handler
			DataInputStream res = new DataInputStream(socketserverResponse.getInputStream()); 
			String msg = res.readUTF();
			
			//Prompts the Response
			System.out.println("Message received from handler : " + msg);
			
			
			//Msg to be sent
			String fromNode = "node4";
			String toNode = "";
			
			//Current Node 4 only connected to 2 based on given arrangement no need to randomize
			int randomNode = 2; 
			System.out.println("Node Chosen between 1[Node1] and 2[Node5]:  -> " + randomNode);
			System.out.println("The message sent ->" + msg);
			
			//Neigbours [2 since current is 4]
			switch(randomNode){
				 case 2 : 
					 toNode ="node2";
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
		 MulticastSocketNodeFour node_4 = new MulticastSocketNodeFour(IP , NODE_PORT_1, NODE_PROPAGATION_PORT_6); 
	}  
}  