//********************************************************************#
//  Autor: David Rozas
//  Emisor.java      
//
//  Envia un mensaje, y recibe la respuesta con el campo edad duplicado
//********************************************************************#


import java.io.*;
import java.net.*;

public class Emisor {
    public static void main(String args[]) {
	
	IllegalArgumentException usoIncorrecto = 
	    new IllegalArgumentException ("Numero de argumentos incorrecto");

	//Excepciones agregadas:
	NumberFormatException puertoNoNumerico =
	    new NumberFormatException ("�Error en el argumento puerto!");

	UnknownHostException hostDesconocido =
	    new UnknownHostException ("�Error al intentar obtener la ip del host! ");

	final int longitud = 4096;

	try { 
	    // Comprueba el numero de argumentos 
	    if (args.length != 2) 
		throw usoIncorrecto;
	    
	    // Obtiene el nombre de la maquina de la linea de mandatos
	    String nombre_maquina = args[0];
	    // Obtiene la direccion IP asociada a nombre_maquina
	    InetAddress direccionIP;
	    //La capturamos nosotros mismos, para lanzar nuestro mensaje
	    try
	    {
		direccionIP =  InetAddress.getByName(nombre_maquina);
	    }
	    catch (UnknownHostException e)
	    {
		throw hostDesconocido;
	    }
	    // Obtiene el nombre del puerto de la linea de mandatos
	    int puerto = Integer.parseInt(args[1]);
	    
	    //Si el puerto no es correcto, lanzamos excepcion
	    if (puerto<=0) 
	    {
		throw puertoNoNumerico;
	    }
		
	    
	    // Crea un objeto de clase Mensaje 
	    Mensaje m = new Mensaje ("Marta", 21);

	    // Crea un Stream
	    ByteArrayOutputStream out = new ByteArrayOutputStream (longitud);
	    ObjectOutputStream objOut = new ObjectOutputStream (out);

	    // Mete m en el Stream objOut
	    objOut.writeObject (m);

	    // Inicializa un datagrama UDP con: Stream, direccion y
	    // puerto
	    DatagramPacket datagrama = new DatagramPacket
		(out.toByteArray(), out.size(), direccionIP, puerto);

	    // Crea un socket para datagramas UDP
	    DatagramSocket dsocket = new DatagramSocket();
	    
	    // Envia el datagrama a traves del socket
	    dsocket.send(datagrama);
	    System.out.println("�Mensaje enviado!");
	    // ##############################################################

	    //Creamos un nuevo datagrama para recibir la respuesta
	    DatagramPacket datagrama2 = new DatagramPacket
		(new byte[longitud], longitud);

	    System.out.println("Esperando a recibir mensaje con campo edad duplicado...");
	    // Nos bloqueamos hasta recibir el mensaje 
	    dsocket.receive(datagrama2);
	    System.out.println("�Mensaje recibido!");

	    // Una vez ha llegado el mensaje....
	    //Lo recogemos en un array de Bytes
	    ByteArrayInputStream in = new ByteArrayInputStream (datagrama2.getData());
	    //Y usamos una "envoltura", para poder usarlo como objeto
	    ObjectInputStream objIn = new ObjectInputStream (in);

	    // Mostramos el origen del datagrama
	    System.out.println("-----------------------------------------------");
	    System.out.println("Origen: " + datagrama2.getAddress().getHostName() +
			       ": " + datagrama2.getPort()); 

	    // Extrae el objeto de clase Mensaje del Stream 
	    Mensaje m1 = (Mensaje) objIn.readObject();
	    System.out.println ("Mensaje recibido: " + m1);
	    System.out.println("-----------------------------------------------");
	    // Cierra el socket. No modificamos el setLength, pq no vamos a seguir recibiendo
	    dsocket.close();
	}
	catch (Exception e) {
	    System.err.println(e);
	    if (e instanceof NumberFormatException)
	    {
		System.err.println("El par�metro puerto debe ser num�rico y positivo\n");
	
	    }else if (e instanceof UnknownHostException)
	    {
		System.err.println("Ese host no tiene asociada ninguna IP\n");
	    }else if (e instanceof IllegalArgumentException)
	    {
		System.err.println("Uso: java Emisor <nombre_maquina> <puerto>\n");
	    }
	}
    }
} 
