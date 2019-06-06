/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicastreceiver7;

import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author Michael
 */
public class MulticastReceiver7 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{ //R PRINTS OUT RECEIVED MSG
            	InetAddress group = InetAddress.getByName("226.1.1.5"); //listening to node 5
            	MulticastSocket multicastSock = new MulticastSocket(3461);
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
		System.out.println("Cost from 5: " + cost);
            	multicastSock.close();              
            ///////////////////////////////////// R FROM THIS POINT DOWN JUST receiving COST
		group = InetAddress.getByName("226.1.1.3"); //listening to node 3
            	multicastSock = new MulticastSocket(3462);
            	multicastSock.joinGroup(group);
            
            	buffer =new byte[100];
            	
            	DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
            	multicastSock.receive(packet);
		cost = new String(buffer);
            	System.out.println("COST: "+ cost);
            	multicastSock.close();              
		   ///////////////////////////////////// R
		group = InetAddress.getByName("226.1.1.4"); //listening to node 4
            	multicastSock = new MulticastSocket(3464);
            	multicastSock.joinGroup(group);
            
            	buffer =new byte[100];
            
            	packet = new DatagramPacket(buffer,buffer.length);
            	multicastSock.receive(packet);
		cost = new String(buffer);
            	System.out.println("COST6: "+ cost);
            	multicastSock.close();              
			///////////////////////////////////// R
		group = InetAddress.getByName("226.1.1.6"); //listening to node 6
            	multicastSock = new MulticastSocket(3463);
            	multicastSock.joinGroup(group);
            
            	buffer =new byte[100];
            
            	packet = new DatagramPacket(buffer,buffer.length);
            	multicastSock.receive(packet);
		cost = new String(buffer);
            	System.out.println("COST4: "+ cost);
            	multicastSock.close();              




			
        }
        catch(Exception e){
        System.out.println(System.err);
	e.printStackTrace();
        
        }
        
        
        
        
        
    }
    
}
