/**
 * NAME        : EVINGTONE NGOA
 * TITLE       : RUMOR MONGERING - FIXED PROBABILITY
 * NODE        : 1
 * LANGUAGE    : JAVA
 **/
 
import java.io.*;  
import java.net.*;  
import java.util.*;

public class MulticastSocketNodeOne 
{ 
	public static final int NODE_PORT_1 = 0001;
	public static final int NODE_PROPAGATION_PORT_6 = 0006;
	public static final int NODE_NEIGHBOR_PORT_7 = 0007;
	public static final String IP = "127.0.0.1";
	
	//Constructor
	public MulticastSocketNodeOne(String ip_address, int NODE_PORT_1, int NODE_PROPAGATION_PORT_6) 
	{
		 
		Scanner scanner = new Scanner(System.in);
			
		try
		{  
			/**
			 * identity port for current node_1 Socket
			 * Node_1
			 **/
			ServerSocket p = new ServerSocket(NODE_PORT_1);
			String msg = "Packet 1";
			System.out.println("Reg Number 55580 % 3 = 2 + 1 = 3. Therefore, following program works on Fixed probability");
		while(true){ //continually listens to propagation handler
			
			//Prompts the Response
			System.out.println("Message received from Propagation handler : " + msg);
			int max = 3;
			int min = 2;
			
			//Calls propagation Handler to send data
			Socket q = new Socket(ip_address, NODE_PROPAGATION_PORT_6);  
			System.out.println("Node 1 started!"); 
			DataOutputStream dout=new DataOutputStream(q.getOutputStream()); 

			//Msg to be sent
			
			String fromNode = "node1";
			String toNode = "";
			
			//Random Nodes
			Random rand = new Random();
			int randomNode = rand.nextInt((max - min) + 1) + min;
			System.out.println("Node Chosen between 2 and 3:  -> " + randomNode);
			System.out.println("The message sent ->" + msg);
			System.out.println("The message sent from node ->" + fromNode);
			
			//Neigbours [2 & 3 since current is 1] chosen randomly 
			switch(randomNode){
				 case 2 : 
					 toNode ="node2";
					 System.out.println("Swtch Node Chosen -> " + toNode);
					 break;
				 case 3 :
					 toNode ="node3";
					 System.out.println("Swtch Node Chosen -> " + toNode);
					 break;
				 default :
					 System.out.println("Invalid node : ");
					 break; 
			}
			
			//(writes) sends to propagation handler
			dout.writeUTF(msg);  
			dout.writeUTF(fromNode);
			dout.writeUTF(toNode);
			
			//Accepts communication from server
			Socket socketserverResponse = p.accept();//establishes connection 
			System.out.println("Received Response from Server!"); 	
			
			//Gets Response from Server
			DataInputStream res = new DataInputStream(socketserverResponse.getInputStream()); 
			msg = res.readUTF();


		}// end while

			
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
		 MulticastSocketNodeOne node_1 = new MulticastSocketNodeOne(IP , NODE_PORT_1, NODE_PROPAGATION_PORT_6); 
	}  
}  