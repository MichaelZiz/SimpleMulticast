/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicastreceiver1;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.ArrayList;
/**
 * 
 * @author Michael
 */
public class MulticastReceiver1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
			
        
        //////////////////////////////////
        Vertex vertex1 = new Vertex("1");
		Vertex vertex2 = new Vertex("2");
		Vertex vertex3 = new Vertex("3");
		Vertex vertex4 = new Vertex("4");
		Vertex vertex5 = new Vertex("5");
                Vertex vertex6 = new Vertex("6");
                Vertex vertex7 = new Vertex("7");
		
		vertex1.addNeighbour(new Edge(2,vertex1,vertex2));
		vertex1.addNeighbour(new Edge(5,vertex1,vertex3));
		vertex2.addNeighbour(new Edge(6,vertex2,vertex3));
		vertex2.addNeighbour(new Edge(4,vertex2,vertex4));
		vertex2.addNeighbour(new Edge(5,vertex2,vertex5));
		vertex3.addNeighbour(new Edge(2,vertex3,vertex5));
                vertex3.addNeighbour(new Edge(2,vertex3,vertex6));
		vertex3.addNeighbour(new Edge(5,vertex3,vertex7));
                vertex4.addNeighbour(new Edge(5,vertex4,vertex5));
                vertex4.addNeighbour(new Edge(4,vertex4,vertex7));
                vertex5.addNeighbour(new Edge(2,vertex5,vertex7));
                vertex6.addNeighbour(new Edge(5,vertex6,vertex7));
	
		DijkstraShortestPath shortestPath = new DijkstraShortestPath();
		shortestPath.computeShortestPaths(vertex1);
		
		
		System.out.println("minimum distance");
		
		
		System.out.println("Minimum distance from 1 to 2: "+vertex2.getDistance());
		System.out.println("Minimum distance from 1 to 3: "+vertex3.getDistance());
		System.out.println("Minimum distance from 1 to 4: "+vertex4.getDistance());
                System.out.println("Minimum distance from 1 to 5: "+vertex5.getDistance());
                System.out.println("Minimum distance from 1 to 6: "+vertex6.getDistance());
                System.out.println("Minimum distance from 1 to 7: "+vertex7.getDistance());
		
		System.out.println("Calculating Paths");
	
		System.out.println("Shortest Path from 1 to 2: "+shortestPath.getShortestPathTo(vertex2));
		System.out.println("Shortest Path from 1 to 3: "+shortestPath.getShortestPathTo(vertex3));
		System.out.println("Shortest Path from 1 to 4: "+shortestPath.getShortestPathTo(vertex4));// forward to 2
		System.out.println("Shortest Path from 1 to 5: "+shortestPath.getShortestPathTo(vertex5));	
                System.out.println("Shortest Path from 1 to 6: "+shortestPath.getShortestPathTo(vertex6)); // forward to 3
                System.out.println("Shortest Path from 1 to 7: "+shortestPath.getShortestPathTo(vertex7));  // forward to 2
                String msg21,msg22,msg31;
                msg21=shortestPath.getShortestPathTo(vertex7).toString();//list1[1,2,5,7]
                msg22=shortestPath.getShortestPathTo(vertex4).toString();//list2[1,2,4]
                msg31=shortestPath.getShortestPathTo(vertex6).toString();//list3[1,3,6]
                System.out.println("Sending: Multicast Project");
        ////////////////////////////////////////
        // TODO code application logic here
        try{ 
          ///////////////////////////////////// Forwarder to NODE 2 actual msg
		Scanner scan = new Scanner(System.in);
		String s = scan.nextLine();
                if(msg21.contains("2") && msg22.contains("2") ){
		InetAddress group2 = InetAddress.getByName("226.1.1.1");
                MulticastSocket multicastSock2= new MulticastSocket(3456); 
                String msg2 ="MULTICAST PROJECT";
		String msg1 ="2";
                DatagramPacket packet1 = new DatagramPacket(msg21.getBytes(),msg21.length(),group2,3456);//string of forwarders 2 list1
		DatagramPacket packet2 = new DatagramPacket(msg22.getBytes(),msg22.length(),group2,3456);//string of forwarders 1 list2
                DatagramPacket packet3 = new DatagramPacket(msg2.getBytes(),msg2.length(),group2,3456);//send msg
                DatagramPacket packet4 = new DatagramPacket(msg1.getBytes(),msg1.length(),group2,3456);//cost
          	multicastSock2.send(packet1);
		multicastSock2.send(packet2);
                multicastSock2.send(packet3);
		multicastSock2.send(packet4);
          	multicastSock2.close();     
                }
		  //////////////	Forwarder to NODE 3
                if(msg31.contains("3")){
		InetAddress group2 = InetAddress.getByName("226.1.1.1");
          	MulticastSocket multicastSock2= new MulticastSocket(3457); 
                //msg2 =received;
                String msg2 ="MULTICAST PROJECT";
		String msg1 ="5";
          	DatagramPacket packet1 = new DatagramPacket(msg31.getBytes(),msg31.length(),group2,3457);//send the string with forward nodes
                DatagramPacket packet2 = new DatagramPacket(msg2.getBytes(),msg2.length(),group2,3457);//send msg
	      	DatagramPacket packet3 = new DatagramPacket(msg1.getBytes(),msg1.length(),group2,3457);//send cost
          	multicastSock2.send(packet1);
		multicastSock2.send(packet2);
                multicastSock2.send(packet3);
          	multicastSock2.close();     
                }
		System.out.println("here");	
        }
        catch(Exception e){
        System.out.println(System.err);
        
        }
        
        
        
        
        
    }
    
}