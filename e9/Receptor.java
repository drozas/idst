//********************************************************************#
//  Autor: David Rozas
//  Receptor.java      
//
//  Se ata a un puerto para recibir mensajes. Despues se lo devuelve
//  al emisor con el campo edad duplicado
//********************************************************************#
import java.io.*;
import java.net.*;

public class Receptor  {
    public static void main(String args[]) throws BindException  {
	IllegalArgumentException usoIncorrecto = 
	    new IllegalArgumentException ("Numero de argumentos incorrecto");

	//Excepciones agregadas:
	NumberFormatException puertoNoNumerico =
	    new NumberFormatException ("�Error en el argumento puerto!");

	BindException puertoOcupado =
	    new BindException ("�Error, ese puerto ya esta usado!");

	// Longitud para almacenar lo recibido
	final int longitud = 4096;

        try {
            if (args.length != 1) 
                throw usoIncorrecto;

            // Obtiene el puerto de args[0]
            int puerto = Integer.parseInt(args[0]);
	    if (puerto<=0)
	    {
		throw puertoNoNumerico;
	    }
	    //DatagramSocket dsocket;
            // Crea un socket UDP y se ata al puerto. 
	    //Capturamos la excepcion nosotros mismos, para lanzar nuestro mensaje
	    DatagramSocket dsocket;
	    try
	    {
		dsocket  = new DatagramSocket(puerto);
	    }
	    catch (BindException e)
	    {
		throw puertoOcupado;
	    }
	    	    
	    // Crea un DatagramPacket para almacenar en �l un mensaje. El 
	    // segundo par�metro da valor al atributo length del datagrama, 
	    // que antes de recibir indica cu�nto como m�ximo se guardar� 
	    // en el datagrama, y despu�s de recibir indica cu�nto se ha 
	    // recibido (que podr�a ser menos del m�ximo especificado).
	    DatagramPacket datagrama = new DatagramPacket
		(new byte[longitud], longitud);
            for(;;) {
                // Esta llamada bloquea el programa hasta que se
                // reciba un mensaje. Como m�ximo se leen tantos bytes
                // como indique datagrama.getLength(). 
		System.out.println("Esperando a recibir un nuevo mensaje...");
                dsocket.receive(datagrama);
		System.out.println("�Nuevo mensaje recibido!");

		// Ha llegado un datagrama. Asociamos un Stream a los
		// datos recibidos del datagrama
		ByteArrayInputStream in = new ByteArrayInputStream 
		    (datagrama.getData());
		ObjectInputStream objIn = new ObjectInputStream (in);

		// Mostramos el origen del datagrama
		System.out.println("-----------------------------------------------");
                System.out.println("Origen: " + datagrama.getAddress().getHostName() +
				   ": " + datagrama.getPort()); 
		// Extrae el objeto de clase Mensaje del Stream y lo muestra. 
		// Si lo que hubiera en el Stream no fuera de clase Mensaje 
		// ni de un descendiente de Mensaje, se elevar�a una excepci�n
		Mensaje m1 = (Mensaje) objIn.readObject();
		System.out.println ("Mensaje recibido: " + m1);
		System.out.println("-----------------------------------------------");		

		// Ajusta el atributo length para permitir leer hasta
		// <longitud> bytes en el pr�ximo receive. Si no se
		// hiciera esta llamada, el atributo length quedar�a
		// con el valor asignado por el �ltimo receive
		datagrama.setLength(longitud);
		
		// ##################################################################
		//Enviaremos al emisor otro mensaje igual, pero con la edad *2
		
		//Creamos un nuevo mensaje a partir de los datos del recibido
		Mensaje m2 = new Mensaje(m1.get_Nombre(),m1.get_Edad()*2);
		
		// Creamos un array de Bytes para meter el objeto
		ByteArrayOutputStream out = new ByteArrayOutputStream (longitud);

		// Y una "envoltura" para poder meterlo como "objeto"
		ObjectOutputStream objOut = new ObjectOutputStream (out);

		// Mete m en el Stream objOut
		objOut.writeObject (m2);

		//Creamos un datagrama udp, con la ip y el puerto del emisor
		DatagramPacket datagrama2 = new DatagramPacket(out.toByteArray(), out.size(),
							       datagrama.getAddress(),datagrama.getPort());
		//y lo enviamos 
		dsocket.send(datagrama2);
		System.out.println("�Enviado mensaje con campo edad duplicado!");
            }
        }
        catch (Exception e) {
            System.err.println(e);
	   
	    if (e instanceof NumberFormatException)
	    {
		System.err.println("El par�metro puerto debe ser num�rico y positivo\n");
	    }else if (e instanceof BindException)
	    {
		System.err.println("Puerto ocupado, prueba con otro");
	    }else if (e instanceof IllegalArgumentException)
	    {
		System.err.println("Uso: java Receptor <puerto>");
	    }
        }
    }
}
