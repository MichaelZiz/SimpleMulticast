/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicastreceiver4;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author Michael
 */
public class MulticastReceiver4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{ ///R from node 2
           	InetAddress group = InetAddress.getByName("226.1.1.2"); //listening to node 1
            	MulticastSocket multicastSock = new MulticastSocket(3458);
            	multicastSock.joinGroup(group);
            
            	byte [] buffer =new byte[100];
            	byte [] buffer2 =new byte[100];
                byte [] buffer3 =new byte[100];
			
		DatagramPacket packet1 = new DatagramPacket(buffer,buffer.length);//list
		DatagramPacket packet2 = new DatagramPacket(buffer2,buffer2.length);//the msg
                DatagramPacket packet3 = new DatagramPacket(buffer3,buffer3.length);//the cost
		String received, cost,list1;
		System.out.println("Entering while");
		while(true){
                        multicastSock.receive(packet1);
			multicastSock.receive(packet2);
	    		multicastSock.receive(packet3);
			received = new String(buffer2);
			cost = new String(buffer3);
			if (received.length() > 0){
				System.out.println("Received Packet");
				break;
			}
		}
            	System.out.println(received);
		System.out.println("Cost from 2: " + cost);
            	multicastSock.close();       
          ///////////////////////////////////// Forwarder to NODE 5 only cost
		TimeUnit.MILLISECONDS.sleep(5);
		InetAddress group2 = InetAddress.getByName("226.1.1.4");
         	MulticastSocket multicastSock2= new MulticastSocket(3466); /// we bind here to port 3459 3 sends to 6
          	int cost1 =5;
     	   	 String msg2 ="COST: "+ cost1;
  	        packet2 = new DatagramPacket(msg2.getBytes(),msg2.length(),group2,3466);
     	     	multicastSock2.send(packet2);
     	     	multicastSock2.close();     
		  //////////////
		  ///////////////////////////////////// Forwarder to NODE 7 only cost
		group2 = InetAddress.getByName("226.1.1.4");
      	    	multicastSock2= new MulticastSocket(3464); /// we bind here to port 3459 3 sends to 6
		cost1 =4;
          	msg2 ="COST: "+ cost1;
          	packet2 = new DatagramPacket(msg2.getBytes(),msg2.length(),group2,3464);
          	multicastSock2.send(packet2);
          	multicastSock2.close();     
	

			
        }
        catch(Exception e){
        System.out.println(System.err);
	e.printStackTrace();
        
        }
        
        
        
        
        
    }
    
}
