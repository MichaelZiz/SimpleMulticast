/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicastreceiver3;

import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author Michael
 */
public class Multicastreceiver3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{ ///R from node 1
           	InetAddress group = InetAddress.getByName("226.1.1.1"); //listening to node 1
            	MulticastSocket multicastSock = new MulticastSocket(3457);
            	multicastSock.joinGroup(group);
            
            	byte [] buffer =new byte[100];
		byte [] buffer2 =new byte[100];
                byte [] buffer3 =new byte[100];
            
            	DatagramPacket packet1 = new DatagramPacket(buffer,buffer.length);//list
		DatagramPacket packet2 = new DatagramPacket(buffer2,buffer2.length);//msg
                DatagramPacket packet3 = new DatagramPacket(buffer3,buffer3.length);//cost
		String received, cost,list3;
		while(true){
			multicastSock.receive(packet1);
	    		multicastSock.receive(packet2);
                        multicastSock.receive(packet3);
                        list3 = new String(buffer);
			received = new String(buffer2);
			cost = new String(buffer3);
			if (received.length() > 0){
				System.out.println("Received Packet");
				break;
			}
		}
            	System.out.println(received);
		System.out.println("Cost from 1: " + cost);
            	multicastSock.close();       
          ///////////////////////////////////// Forwarder to NODE 6
          if(list3.contains("6")){
		InetAddress group2 = InetAddress.getByName("226.1.1.3");
          	MulticastSocket multicastSock2= new MulticastSocket(3459); /// we bind here to port 3459 3 sends to 6
          	String msg2 =received;
		String msg1 ="2";
          	packet1 = new DatagramPacket(msg2.getBytes(),msg2.length(),group2,3459);
	      	packet2 = new DatagramPacket(msg1.getBytes(),msg1.length(),group2,3459);
          	multicastSock2.send(packet1);
		multicastSock2.send(packet2);
          	multicastSock2.close();     
          }
		  //////////////
		  ///////////////////////////////////// Forwarder to NODE 5
		InetAddress group2 = InetAddress.getByName("226.1.1.3");
          	MulticastSocket multicastSock2= new MulticastSocket(3460); /// we bind here to port 3459 3 sends to 6
		String msg2 =received;
		String msg1 ="2";
          	packet1 = new DatagramPacket(msg2.getBytes(),msg2.length(),group2,3460);
	      	packet2 = new DatagramPacket(msg1.getBytes(),msg1.length(),group2,3460);
          	multicastSock2.send(packet1);
		multicastSock2.send(packet2);
          	multicastSock2.close();     
		  /////////////// NODE 7 PROMOTE LINK COST
		group2 = InetAddress.getByName("226.1.1.3"); // PROMOTE TO NODE 7
          	multicastSock2= new MulticastSocket(3462); /// we bind here to port 3459 3 sends to 6
          	int cost1 =5;
          	msg2 ="COST: "+ cost1;
          	packet2 = new DatagramPacket(msg2.getBytes(),msg2.length(),group2,3462);
          	multicastSock2.send(packet2);
          	multicastSock2.close();     
		/////////////////////////////////// listening to  node 2 for link cost
		group = InetAddress.getByName("226.1.1.2");
            	multicastSock = new MulticastSocket(3467);
            	multicastSock.joinGroup(group);
            
            	buffer =new byte[100];
            
            	DatagramPacket packet = new DatagramPacket(buffer,buffer.length);

		while(true){
			multicastSock.receive(packet);
			String receivedlisten = new String(buffer);
            		System.out.println(receivedlisten);
			if(receivedlisten.length() > 0){
				break;
			}
		}
		

            	multicastSock.close();              


			
        }
        catch(Exception e){
        System.out.println(System.err);
	e.printStackTrace();
        
        }
        
        
        
        
        
    }
    
}
