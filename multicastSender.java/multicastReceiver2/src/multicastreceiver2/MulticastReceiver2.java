/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicastreceiver2;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author Michael
 */
public class MulticastReceiver2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{ ///R from node 1
           	InetAddress group = InetAddress.getByName("226.1.1.1"); //listening to node 1
		//System.out.println("1");
            	MulticastSocket multicastSock = new MulticastSocket(3456);
		//System.out.println("2");
            	multicastSock.joinGroup(group);
            	System.out.println("Joined group");
            	byte [] buffer =new byte[100];
		byte [] buffer2 =new byte[100];
                byte [] buffer3 =new byte[100];
		byte [] buffer4 =new byte[100];
            	DatagramPacket packet1 = new DatagramPacket(buffer,buffer.length);
	    	DatagramPacket packet2 = new DatagramPacket(buffer2,buffer2.length);
                DatagramPacket packet3 = new DatagramPacket(buffer3,buffer3.length);
	    	DatagramPacket packet4 = new DatagramPacket(buffer4,buffer4.length);
		String received, cost,list1,list2;
		System.out.println("Entering while");
		while(true){
			multicastSock.receive(packet1);//used for first list1 [1,2,5,7]
	    		multicastSock.receive(packet2);//used for second list2[1,2,4]
                        multicastSock.receive(packet3);//used for msg
	    		multicastSock.receive(packet4);//used for cost
                        list1 = new String(buffer);
                        list2 = new String(buffer2);
			received = new String(buffer3);// this is the msg in buffer 3
			cost = new String(buffer4);//cost is in buffer 4
			if (received.length() > 0){
				System.out.println("Received Packet");
				break;
			}
		}
            	System.out.println(received);
		System.out.println("Cost from 1:" + cost);
            	multicastSock.close();  
                System.out.println(list1);
          ///////////////////////////////////////////////////////////
  		 if(list1.contains("5")){
		System.out.println("Sending to 5");
		InetAddress group2 = InetAddress.getByName("226.1.1.2");
          	MulticastSocket multicastSock2= new MulticastSocket(3465); /// we bind here to port 3459 3 sends to 6
		int cost1 =5;
          	String msg2 ="COST from 2:"+ cost1;                
          	packet1 = new DatagramPacket(list1.getBytes(),list1.length(),group2,3465);//list
                packet2 = new DatagramPacket(received.getBytes(),received.length(),group2,3465);//msg
                packet3 = new DatagramPacket(msg2.getBytes(),msg2.length(),group2,3465);//list
          	multicastSock2.send(packet1);
                multicastSock2.send(packet2);
                multicastSock2.send(packet3);
          	multicastSock2.close();     
                }
	
          ///////////////////////////////////// Forwarder to NODE 4 actual msg
          if(list2.contains("4")){
		InetAddress group2 = InetAddress.getByName("226.1.1.2");
         	MulticastSocket multicastSock2= new MulticastSocket(3458); /// we bind here to port 3459 3 sends to 6
          	String msg2 =received;
		String msg1 ="4";
          	packet1 = new DatagramPacket(list2.getBytes(),list2.length(),group2,3458);//list
	      	packet2 = new DatagramPacket(msg2.getBytes(),msg2.length(),group2,3458);//msg
                packet3 = new DatagramPacket(msg1.getBytes(),msg1.length(),group2,3458);//cost
          	multicastSock2.send(packet1);
		multicastSock2.send(packet2);
                multicastSock2.send(packet3);		
          	multicastSock2.close();   
          }
		  //////////////
		  ///////////////////////////////////// Forwarder to NODE 3 only cost
	
		TimeUnit.MILLISECONDS.sleep(5);
		InetAddress group2 = InetAddress.getByName("226.1.1.2");
          	MulticastSocket multicastSock2= new MulticastSocket(3467); /// we bind here to port 3459 3 sends to 6
		int cost1 =6;
          	String msg2 ="COST from 2: "+ cost1;
          	packet2 = new DatagramPacket(msg2.getBytes(),msg2.length(),group2,3467);
          	multicastSock2.send(packet2);
          	multicastSock2.close();     

//////////////////////////////////////////////////// Forwarder to NODE 5 only cost

///////////////////////////////////////////////////////////////////////////////////////////////////////////
			
        }
        catch(Exception e){
        System.out.println(System.err);
	e.printStackTrace();
        
        }
        
        
        
        
        
    }
    
}
