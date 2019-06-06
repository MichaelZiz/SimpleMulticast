/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicastreceiver5;

import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author Michael
 */
public class MulticastReceiver5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{ // RECEIVER FOR NODE 3
           	InetAddress group = InetAddress.getByName("226.1.1.2"); //RECEIVING FROM NODE 3
            	MulticastSocket multicastSock = new MulticastSocket(3465);
            	multicastSock.joinGroup(group);
            
            	byte [] buffer =new byte[100];
		byte [] buffer2 =new byte[100];
                byte [] buffer3 =new byte[100];
            
            	DatagramPacket packet1 = new DatagramPacket(buffer,buffer.length);//list
		DatagramPacket packet2 = new DatagramPacket(buffer2,buffer2.length);//msg
                DatagramPacket packet3 = new DatagramPacket(buffer3,buffer3.length);//cost
		String received, cost,list1;
		System.out.println("Entering while");
		while(true){
			multicastSock.receive(packet1);//list
	    		multicastSock.receive(packet2);//msg
                        multicastSock.receive(packet3);//cost
	    		list1= new String(buffer);
			received = new String(buffer2);
			cost = new String(buffer3);
			if (received.length() > 0){
				System.out.println("Received Packet");
				break;
			}
		}
            	System.out.println(received);
		System.out.println(cost);
            	multicastSock.close();             
           ///////////////////////////////////// FORWARDER TO NODE 7
            if(list1.contains("7")){
		InetAddress group2 = InetAddress.getByName("226.1.1.5");
          	MulticastSocket multicastSock2= new MulticastSocket(3461); /// we bind here to port 3459 3 sends to 6
		String msg2 =received;
		String msg1 ="2";
          	packet1 = new DatagramPacket(msg2.getBytes(),msg2.length(),group2,3461);//send msg
	      	packet2 = new DatagramPacket(msg1.getBytes(),msg1.length(),group2,3461);//send cost
          	multicastSock2.send(packet1);
		multicastSock2.send(packet2);
          	multicastSock2.close();     
            }
		//////////////////////////////////////////////////////
		group = InetAddress.getByName("226.1.1.2"); //receiving from node 2 cost
            	multicastSock = new MulticastSocket(3465);
            	multicastSock.joinGroup(group);
            
            	buffer =new byte[100];
            
            	DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
		while(true){
			multicastSock.receive(packet);
			cost = new String(buffer);
            		System.out.println(cost);
			if (cost.length() > 0){
				break;
			}
		}


            	multicastSock.close();              
		//////////////////////////////////////////////////////
		group = InetAddress.getByName("226.1.1.4"); //receiving from node 4 cost
            	multicastSock = new MulticastSocket(3466);
            	multicastSock.joinGroup(group);
            
            	buffer =new byte[100];
            
		while(true){
			multicastSock.receive(packet);
			cost = new String(buffer);
            		System.out.println(cost);
			if (cost.length() > 0){
				break;
			}
		}
            	multicastSock.close();              
		///////////////////////////////////////



			
        }
        catch(Exception e){
        System.out.println(System.err);
	e.printStackTrace();
        
        }
        
        
        
        
        
    }
    
}
