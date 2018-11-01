/**
 * NAME        : EVINGTONE NGOA
 * TITLE       : RUMOR MONGERING - FIXED PROBABILITY
 * NODE        : 6
 * LANGUAGE    : JAVA
 **/
 
import java.io.*;  
import java.net.*;  
public class MulticastSocketHandlePropagation 
{ 
	public static final int NODE_PORT_1 = 0001;
	public static final int NODE_PORT_2 = 0002;
	public static final int NODE_PORT_3 = 0003;
	public static final int NODE_PORT_4 = 0004;
	public static final int NODE_PORT_5 = 0005;
	public static final int NODE_PROPAGATION_PORT_6 = 0006;
	public static final int NODE_NEIGHBOR_PORT_7 = 0007;
	public static final int NODE_PORT = 0; // assigned to any node selected
	public static final String IP = "127.0.0.1";
	
	public MulticastSocketHandlePropagation(String ip_address, int NODE_PORT, int NODE_PROPAGATION_PORT_6)
	{
		try
		{  
			/**
			 * identity port for current server Socket
			 * MulticastSocketHandlePropagation 
			 **/
			ServerSocket q = new ServerSocket(NODE_PROPAGATION_PORT_6);  
			System.out.println("Propagation Handler started!"); 
			System.out.println("Waiting for nodes to connect..."); 
			while(true){
				//Establishes connection with node
				Socket nodeConn = q.accept();  
				System.out.println("handler connected!"); 
				DataInputStream dis = new DataInputStream(nodeConn.getInputStream());  
				
				//gets data from nodes	and manipulate			
				String	msg = dis.readUTF();  
				String	fromNode = dis.readUTF();  
				String	toNode = dis.readUTF();  

		

			
			//Processing Product						
			System.out.println("Processing the message from " + fromNode + " to " + toNode + " Message " + msg);
			
			int port =0;
					
			//Setting port according to request 
			if("node1".equals(toNode)){
				port = NODE_PORT_1;
				System.out.println("Port selected Propagatin to Port: " + port);
			}
			else if("node2".equals(toNode)){
				port= NODE_PORT_2;
				System.out.println("Port selected Propagatin to Port: " + port);
			}
			else if("node3".equals(toNode)){
				port = NODE_PORT_3;
				System.out.println("Port selected Propagatin to Port: " + port);
			}
			else if("node4".equals(toNode)){
				port = NODE_PORT_4;
				System.out.println("Port selected Propagatin to Port: " + port);
			}
			else if("node5".equals(toNode)){
				port = NODE_PORT_5;
				System.out.println("Port selected Propagatin to Port: " + port);
			}
			
			//Sends messages to the opted Node
			Socket opSocketNode = new Socket(ip_address, port);
			DataOutputStream opToNode = new DataOutputStream(opSocketNode.getOutputStream());
			opToNode.writeUTF(msg);
			
			//Sends messages to the neighbor handler node for 
			//Registering Nodes infected and stoping propagation at k node
			Socket neighborSocketNode = new Socket(ip_address, NODE_NEIGHBOR_PORT_7);
			DataOutputStream nbrToNode = new DataOutputStream(neighborSocketNode.getOutputStream());
			nbrToNode.writeUTF(msg);
			nbrToNode.writeUTF(fromNode);
			nbrToNode.writeUTF(toNode);
				}//end while

			//Closing connections from Nodes
			/**q.close(); 
			nodeConn.close(); 
			opSocketNode.close(); 
			neighborSocketNode.close(); **/
			
		
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
		MulticastSocketHandlePropagation handlePropagation = new MulticastSocketHandlePropagation(IP , NODE_PORT_1, NODE_PROPAGATION_PORT_6);
	}  
}  