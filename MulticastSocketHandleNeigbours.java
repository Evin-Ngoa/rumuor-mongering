/**
 * NAME        : EVINGTONE NGOA MWAILONGO
 * REG         : P15/55580/2012
 * TITLE       : RUMOR MONGERING - FIXED PROBABILITY
 * INSTITUTION : UNIVERSITY OF NAIROBI
 * UNIT        : DISTRIBUTED SYSTEMS
 * CODE        : CSC 315
 * NODE        : 7
 * LANGUAGE    : JAVA
 **/
 
import java.io.*;  
import java.net.*;  
public class MulticastSocketHandleNeigbours 
{ 
	public static final int NODE_PORT_1 = 0001;
	public static final int NODE_PORT_2 = 0002;
	public static final int NODE_PORT_3 = 0003;
	public static final int NODE_PORT_4 = 0004;
	public static final int NODE_PORT_5 = 0005;
	public static final int NODE_PORT = 0; // assigned to any node selected
	public static final int NODE_NEIGHBOR_PORT_7 = 0007;
	public static final int NODE_PROPAGATION_PORT_6 = 0006;
	public static final String IP = "127.0.0.1";

	
	public MulticastSocketHandleNeigbours(String ip_address, int NODE_PORT, int NODE_NEIGHBOR_PORT_7)
	{
		try
		{  
			/**
			 * identity port for current server Socket
			 * MulticastSocketHandleNeigbours 
			 **/
			ServerSocket q = new ServerSocket(NODE_NEIGHBOR_PORT_7);  
			System.out.println("Neighbors Handler started!"); 
			System.out.println("Waiting for nodes to connect..."); 
			
			//Create Negative flags to see which node has been infected since all nodes report to node one upon 
			//infected thus assisting in stoping after k nodes transmission
			int node_one_flag = 0, node_two_flag = 0, node_three_flag = 0, node_four_flag = 0, node_five_flag = 0;
			int  stop_transmission_flag = 0;
			
			//Establishes connection with node
			Socket nodeConn = q.accept();  
			System.out.println("Nodes connected with Neighbors Handler!"); 
			DataInputStream dis = new DataInputStream(nodeConn.getInputStream());  
			
			//gets data from nodes	and manipulate			
			String	msg = dis.readUTF();  
			String	fromNode = dis.readUTF();  
			String	toNode = dis.readUTF();  
			System.out.println("Nodes Passed: from " + fromNode + " to " + toNode); 
			
			//On receiving message from a node
			//Setting port according to request 
			//Updating flags if message from node and to node just to make sure
			//all nodes have been infected
			if("node1".equals(fromNode) || "node1".equals(toNode)){
				//set node1 flag infected
				node_one_flag = 1;
				System.out.println("Node 1 flag updated!"); 
			}
			if("node2".equals(fromNode) || "node2".equals(toNode)){
				//set node2 flag infected
				node_two_flag = 1;
				System.out.println("Node 2 flag updated!"); 
			}
			if("node3".equals(fromNode) || "node3".equals(toNode)){
				//set node3 flag infected
				node_three_flag = 1;
				System.out.println("Node 3 flag updated!"); 
			}
			if("node4".equals(fromNode) || "node4".equals(toNode)){
				//set node4 flag infected
				node_four_flag = 1;
				System.out.println("Node 4 flag updated!"); 
			}
			if("node5".equals(fromNode) || "node5".equals(toNode)){
				//set node5 flag infected
				node_five_flag = 1;
				System.out.println("Node 5 flag updated!"); 
			}
			
			//Stop Transmission  after k nodes in my case I use 4 node
			if( node_one_flag == 1 && node_two_flag == 1 && node_three_flag == 1 && node_four_flag == 1){
				//set timeout to zero and stop transmission immediatetely in the propagationHandler
				 stop_transmission_flag = 1;
			}
			System.out.println("Stop Flag : " + stop_transmission_flag); 

			
			//Sends stop flag to transmission handler to check the status and stop transmission
			Socket propSocketNode = new Socket(ip_address, NODE_PROPAGATION_PORT_6);
			DataOutputStream propToNode = new DataOutputStream(propSocketNode.getOutputStream());
			opToNode.writeInt(stop_transmission_flag);

			//Closing connections from Nodes
			q.close(); 
			nodeConn.close(); 
			//propSocketNode.close(); 
			
		
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
		MulticastSocketHandleNeigbours handleNeigbours = new MulticastSocketHandleNeigbours(IP , NODE_PORT, NODE_NEIGHBOR_PORT_7);
	}  
}  