/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicastreceiver6;

import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author Michael
 */
public class MulticastReceiver6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{ // 
           	InetAddress group = InetAddress.getByName("226.1.1.3");
            	MulticastSocket multicastSock = new MulticastSocket(3459);//Receive node 3
            	multicastSock.joinGroup(group);
            
            	byte [] buffer =new byte[100];
           	byte [] buffer2 =new byte[100];
            
            	DatagramPacket packet1 = new DatagramPacket(buffer,buffer.length);
		DatagramPacket packet2 = new DatagramPacket(buffer2,buffer2.length);
		String received, cost;
		System.out.println("Entering while");
		while(true){
			multicastSock.receive(packet1);
	    		multicastSock.receive(packet2);
			received = new String(buffer);
			cost = new String(buffer2);
			if (received.length() > 0){
				System.out.println("Received Packet");
				break;
			}
		}
            	System.out.println(received);
		System.out.println("Cost from 3: " + cost);
            	multicastSock.close();              
          ////////////////////////////////////////// PROMOTE COST TO 7
          	InetAddress group2 = InetAddress.getByName("226.1.1.6");
          	MulticastSocket multicastSock2= new MulticastSocket(3463); /// we bind here to port 3456 send to node 2
          	String msg2 ="5";
          	packet2 = new DatagramPacket(msg2.getBytes(),msg2.length(),group2,3463);
          	multicastSock2.send(packet2);
          	multicastSock2.close();     
            
                  
        }
        catch(Exception e){
        System.out.println(System.err);
	e.printStackTrace();
        
        }
        
        
        
        
        
    }
    
}
