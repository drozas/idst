//********************************************************************#
//  Autor: David Rozas
//  ClienteJuegos.java      
//
//  Envia peticiones a un servidor de juegos
//********************************************************************#


import java.io.*;
import java.net.*;
import cs1.Keyboard;

public class ClienteJuegos {
    public static void main(String args[]) {
	
	IllegalArgumentException usoIncorrecto = 
	    new IllegalArgumentException ("Numero de argumentos incorrecto");

	//Excepciones agregadas:
	NumberFormatException puertoNoNumerico =
	    new NumberFormatException ("�Error en el argumento puerto!");

	UnknownHostException hostDesconocido =
	    new UnknownHostException ("�Error al intentar obtener la ip del host! ");

	final int longitud = 10000;
	int opcJuego;
	int precioJuego;
	char opcBillete;
	int valorBillete;
	String vueltas;
	String salidaPrograma;
	boolean hayVueltas;
	String nombreCarrera="";
	int cochesA,cochesB,cochesC,cochesD,km;
	ByteArrayOutputStream out;
	ObjectOutputStream objOut;
	DatagramPacket datagrama;//para env�o
	DatagramPacket datagrama2;//para recepci�n
	ByteArrayInputStream in;
	ObjectInputStream objIn;
	tMCSSondeo mSondeoPrecio;
	tMSCSondeo respSondeoPrecio;
	tMCSCajero mCajero;
	tMSCCajero respCajero;
	tMCSMus mMus;
	tMSCMus respMus;
	tMCSAuto mAutos;
	tMSCAuto respAutos;	

	
	try { 
	    // Comprueba el numero de argumentos 
	    if (args.length != 2) 
		throw usoIncorrecto;
	    
	    // Obtiene el nombre de la maquina de la linea de mandatos
	    String nombre_maquina = args[0];
	    // Obtiene la direccion IP asociada a nombre_maquina
	    InetAddress direccionIP = InetAddress.getByName(nombre_maquina);
	    
	    // Obtiene el nombre del puerto de la linea de mandatos
	    int puerto = Integer.parseInt(args[1]);
	    
	    //Si el puerto no es correcto, lanzamos excepcion
	    if (puerto<=0) 
	    {
		throw puertoNoNumerico;
	    }
	    // Crea un socket para datagramas UDP
	    DatagramSocket dsocket = new DatagramSocket();
	    	
	    do
	    {
		System.out.println("*******************************************************************");
		System.out.println("                 Bienvenid@ al servicio de juegos");
		System.out.println("*******************************************************************");
		System.out.println();
		System.out.println();
		//Mostramos opciones de juego, mientras no elija la opcion correcta
		do{
		    System.out.println("Elige un juego:");
		    System.out.println("\t 1. Mus");
		    System.out.println("\t 2. Autos Locos");
		    System.out.println("\t 3. Terminar");
		    System.out.println();
		    System.out.print("\t Elecci�n : ");
		    opcJuego= Keyboard.readInt();
		}while((opcJuego<1)&&(opcJuego>3));
		
		switch(opcJuego){
		case 1: 
		    System.out.println("Has elegido jugar al Mus");

		    //###### Env�o de paquete para consulta de juego #################
		    System.out.println("Consultando precio del juego al servidor ... ");
		    //Creamos un objeto de mensaje de sondeo de precio
		    mSondeoPrecio= new tMCSSondeo(opcJuego);
		    //Creamos un Stream para ese tipo
		    out = new ByteArrayOutputStream (longitud);
		    objOut = new ObjectOutputStream (out);
		    // Mete m en el Stream objOut
		    objOut.writeObject (mSondeoPrecio);
		    // Inicializa un datagrama UDP con: Stream, direccion y puerto 
		    datagrama = new DatagramPacket
		    (out.toByteArray(), out.size(), direccionIP, puerto);
		    // Envia el datagrama a traves del socket
		    dsocket.send(datagrama);
		    //###############################################################

		    //####### Recepci�n de datagrama de consulta de precio #############
		    System.out.println("Petici�n de precio al servidor enviada. Esperando respuesta...");
		    //Creamos un nuevo datagrama para recibir la respuesta
		    datagrama2 = new DatagramPacket (new byte[longitud], longitud);
		    // Nos bloqueamos hasta recibir el mensaje 
		    dsocket.receive(datagrama2);
		    System.out.println("Recibida respuesta...");
		    // Una vez ha llegado el mensaje....
		    //Lo recogemos en un array de Bytes
		    in = new ByteArrayInputStream (datagrama2.getData());
		    //Y usamos una "envoltura", para poder usarlo como objeto
		    objIn = new ObjectInputStream (in);
		    // Extrae el objeto de clase tMSCSondeo del Stream. Aqu� nos devolver� el precio 
		    // del juego que solicitamos
		    respSondeoPrecio = (tMSCSondeo) objIn.readObject();
		    // Ajusta el atributo para poder leer correctamente en el proximo receive
		    datagrama2.setLength(longitud);
		    //#########################################################################

		    
		    //###### Informaci�n de coste de juego, y men� de cobro al cliente #######
		    //Sacamos el precio que nos han devuelto
		    precioJuego= respSondeoPrecio.getPrecio();
		    //Mostramos informacion al cliente
		    System.out.println("Precio del juego : " + precioJuego);
		    System.out.println("Elija un billete:");
		    System.out.println("\t A. Billete de 100 euros");
		    System.out.println("\t B. Billete de 50 euros");
		    System.out.println("\t C. Billete de 20 euros");
		    System.out.println("\t D. Billete de 10 euros");
		    System.out.println("\t E. Billete de 5 euros");
		    //Leeremos, hasta que nos metan un billete correcto, y que sea suficiente para el coste del juego
		    do
		    {
			System.out.print(" Billete : ");
			opcBillete= Keyboard.readChar();
			valorBillete= Billete2Valor(opcBillete);
			//Si es menor que 0 (es opc incorrecta) o no llega para pagar, lo decimos
			if( (valorBillete<0) || (valorBillete<precioJuego))
			{
			    System.out.println("No es posible jugar a este juego con este billete.");
			}
		    }while( (valorBillete<0) || (valorBillete<precioJuego));
		    //##############################################################################

		    
		    //############### Peticion de vueltas al cajero ###############################
		    //Ahora, enviamos un nuevo paquete al cajero, solicitando las vueltas
		    System.out.println("Enviando peticion de vueltas al servidor... ");
		    //Creamos un objeto de mensaje de sondeo de precio
		    mCajero= new tMCSCajero(opcJuego,valorBillete);
		    //Stream para ese tipo 
		    out = new ByteArrayOutputStream (longitud);
		    objOut = new ObjectOutputStream (out);
		    // Mete m en el Stream objOut
		    objOut.writeObject (mCajero);
		    // Inicializa un datagrama UDP con: Stream, direccion y puerto 
		    datagrama = new DatagramPacket
		    (out.toByteArray(), out.size(), direccionIP, puerto);
		    // Envia el datagrama a traves del socket
		    dsocket.send(datagrama);		    
		    //#############################################################################


		    //####### Recepci�n de datagrama de consulta de vueltas al Cajero  #############
		    System.out.println("Petici�n de vueltas al cajero enviada. Esperando respuesta...");
		    //Creamos un nuevo datagrama para recibir la respuesta
		    datagrama2 = new DatagramPacket (new byte[longitud], longitud);
		    // Nos bloqueamos hasta recibir el mensaje 
		    dsocket.receive(datagrama2);
		    System.out.println("Recibida respuesta...");
		    // Una vez ha llegado el mensaje....
		    //Lo recogemos en un array de Bytes
		    in = new ByteArrayInputStream (datagrama2.getData());
		    //Y usamos una "envoltura", para poder usarlo como objeto
		    objIn = new ObjectInputStream (in);
		    // Extrae el objeto de clase tMSCajero del Stream. Aqu� nos devolver� el cambio. Si nos 
		    // devolvio un string vacio, es que no ha sido posible dar las vueltas
		    respCajero = (tMSCCajero) objIn.readObject();
		    // Ajusta el atributo para poder leer correctamente en el proximo receive
		    datagrama2.setLength(longitud);
		    //#########################################################################		    
		    
		    //Miramos si fue posible recoger el cambio
		    vueltas= respCajero.getInfoCambio();
		    if (vueltas.equals(""))
		    {
			System.out.println("-------------------------------------------");
			System.out.println("No fue posible darle el cambio");
			System.out.println("Recoja su billete.");	
			System.out.println("-------------------------------------------");
		    }else{
			System.out.println("-------------------------------------------");
			System.out.println(vueltas);
			System.out.println("-------------------------------------------");
			//############### Peticion de ejecuci�n del mus ###############################
			//Ahora, enviamos un nuevo paquete al server, inf�rmandole de que ya puede 
			//ejecutar el juego del mus
			System.out.println("Solicitando ejecuci�n de juego del mus... ");
			//Creamos un objeto de mensaje de solicitud de ejecucion de mus
			mMus= new tMCSMus(opcJuego);
			//CStream para ese tipo 
			out = new ByteArrayOutputStream (longitud);
			objOut = new ObjectOutputStream (out);
			// Mete m en el Stream objOut
			objOut.writeObject (mMus);
			// Inicializa un datagrama UDP con: Stream, direccion y puerto
			datagrama = new DatagramPacket (out.toByteArray(), out.size(), direccionIP, puerto);
			// Envia el datagrama a traves del socket
			dsocket.send(datagrama);		    

			//#############################################################################


			//####### Recepci�n de datagrama de resultado de ejecuci�n del mus  #############
			System.out.println("Solicitud de ejecuci�n de Mus enviada. Esperando respuesta...");
			//Creamos un nuevo datagrama para recibir la respuesta���Solo lo reinstancio, no)
			datagrama2 = new DatagramPacket (new byte[longitud], longitud);
			// Nos bloqueamos hasta recibir el mensaje 
			dsocket.receive(datagrama2);
			System.out.println("Recibida respuesta...");
			// Una vez ha llegado el mensaje....
			//Lo recogemos en un array de Bytes
			in = new ByteArrayInputStream (datagrama2.getData());
			//Y usamos una "envoltura", para poder usarlo como objeto
			objIn  = new ObjectInputStream (in);
			// Extrae el objeto de clase tMSCMus
			respMus = (tMSCMus) objIn.readObject();
			// Ajusta el atributo para poder leer correctamente en el proximo receive
			datagrama2.setLength(longitud);
			//#############################################################################	
			
			//###### Mostramos la salida de la ejecuci�n del Mus ##########################
			System.out.println("Recibida salida del mus....");
			System.out.println("--------------------------------------------------------------------------");
			System.out.println(respMus.getResultadoMus());
			System.out.println("--------------------------------------------------------------------------");
			//#############################################################################

		    }
		    break;

		case 2: 
		    System.out.println("Has elegido jugar a los Autos Locos");

		    //###### Env�o de paquete para consulta de juego #################
		    System.out.println("Consultando precio del juego al servidor ... ");
		    //Creamos un objeto de mensaje de sondeo de precio
		    mSondeoPrecio= new tMCSSondeo(opcJuego);
		    //Creamos un Stream para ese tipo
		    out = new ByteArrayOutputStream (longitud);
		    objOut = new ObjectOutputStream (out);
		    // Mete m en el Stream objOut
		    objOut.writeObject (mSondeoPrecio);
		    // Inicializa un datagrama UDP con: Stream, direccion y puerto 
		    datagrama = new DatagramPacket
		    (out.toByteArray(), out.size(), direccionIP, puerto);
		    // Envia el datagrama a traves del socket
		    dsocket.send(datagrama);
		    //###############################################################

		    //####### Recepci�n de datagrama de consulta de precio #############
		    System.out.println("Petici�n de precio al servidor enviada. Esperando respuesta...");
		    //Creamos un nuevo datagrama para recibir la respuesta
		    datagrama2 = new DatagramPacket (new byte[longitud], longitud);
		    // Nos bloqueamos hasta recibir el mensaje 
		    dsocket.receive(datagrama2);
		    System.out.println("Recibida respuesta...");
		    // Una vez ha llegado el mensaje....
		    //Lo recogemos en un array de Bytes
		    in = new ByteArrayInputStream (datagrama2.getData());
		    //Y usamos una "envoltura", para poder usarlo como objeto
		    objIn = new ObjectInputStream (in);
		    // Extrae el objeto de clase tMSCSondeo del Stream. Aqu� nos devolver� el precio del juego dado
		    //y para controlar que funcione, sino, pues por ej. un neg
		    respSondeoPrecio = (tMSCSondeo) objIn.readObject();
		    // Ajusta el atributo para poder leer correctamente en el proximo receive
		    datagrama2.setLength(longitud);
		    //#########################################################################

		    
		    //###### Informaci�n de coste de juego, y men� de cobro al cliente #######
		    //Sacamos el precio que nos han devuelto
		    precioJuego= respSondeoPrecio.getPrecio();
		    //Mostramos informacion al cliente
		    System.out.println("Precio del juego : " + precioJuego);
		    System.out.println("Elija un billete:");
		    System.out.println("\t A. Billete de 100 euros");
		    System.out.println("\t B. Billete de 50 euros");
		    System.out.println("\t C. Billete de 20 euros");
		    System.out.println("\t D. Billete de 10 euros");
		    System.out.println("\t E. Billete de 5 euros");
		    //Leeremos, hasta que nos metan un billete correcto, y que sea suficiente para el coste del juego
		    do
		    {
			System.out.print(" Billete : ");
			opcBillete= Keyboard.readChar();
			valorBillete= Billete2Valor(opcBillete);
			//Si es menor que 0 (es opc incorrecta) o no llega pa pagar, lo decimos
			if( (valorBillete<0) || (valorBillete<precioJuego))
			{
			    System.out.println("No es posible jugar a este juego con este billete.");
			}
		    }while( (valorBillete<0) || (valorBillete<precioJuego));
		    //##############################################################################

		    
		    //############### Peticion de vueltas al cajero ###############################
		    //Ahora, enviamos un nuevo paquete al cajero, solicitando las vueltas
		    System.out.println("Enviando peticion de vueltas al servidor... ");
		    //Creamos un objeto de mensaje de sondeo de precio
		    mCajero= new tMCSCajero(opcJuego,valorBillete);
		    //Stream para ese tipo 
		    out = new ByteArrayOutputStream (longitud);
		    objOut = new ObjectOutputStream (out);
		    // Mete m en el Stream objOut
		    objOut.writeObject (mCajero);
		    // Inicializa un datagrama UDP con: Stream, direccion y puerto 
		    datagrama = new DatagramPacket
		    (out.toByteArray(), out.size(), direccionIP, puerto);
		    // Envia el datagrama a traves del socket
		    dsocket.send(datagrama);		    
		    //#############################################################################


		    //####### Recepci�n de datagrama de consulta de vueltas al Cajero  #############
		    System.out.println("Petici�n de vueltas al cajero enviada. Esperando respuesta...");
		    //Creamos un nuevo datagrama para recibir la respuesta
		    datagrama2 = new DatagramPacket (new byte[longitud], longitud);
		    // Nos bloqueamos hasta recibir el mensaje 
		    dsocket.receive(datagrama2);
		    System.out.println("Recibida respuesta...");
		    // Una vez ha llegado el mensaje....
		    //Lo recogemos en un array de Bytes
		    in = new ByteArrayInputStream (datagrama2.getData());
		    //Y usamos una "envoltura", para poder usarlo como objeto
		    objIn = new ObjectInputStream (in);
		    // Extrae el objeto de clase tMSCajero del Stream. Aqu� nos devolver� el cambio. Si nos 
		    // devolvio un string vacio, es que no ha sido posible dar las vueltas
		    respCajero = (tMSCCajero) objIn.readObject();
		    // Ajusta el atributo para poder leer correctamente en el proximo receive
		    datagrama2.setLength(longitud);
		    //#########################################################################		    
		    
		    //Miramos si fue posible recoger el cambio
		    vueltas= respCajero.getInfoCambio();

		    if (vueltas.equals(""))
		    {
			System.out.println("-------------------------------------------");
			System.out.println("No fue posible darle el cambio");
			System.out.println("El billete ha sido descontado del cajero. Rec�jalo.");	
			System.out.println("-------------------------------------------");
		    }else{
			System.out.println("-------------------------------------------");
			System.out.println(vueltas);
			System.out.println("-------------------------------------------");
			//############### Peticion de ejecuci�n de Autos locos ###############################
			//Ahora, enviamos un nuevo paquete al server, inf�rmandole de que ya puede 
			//ejecutar el juego de los autos locos.

			//Solicitamos la entrada de los datos del juego: este juego se ejecutar�
			// hasta que el nombre de la carrera sea fin...���haber como lo implementamos???
						
			System.out.print(" --> Introduzca el nombre de la carrera : " );
			nombreCarrera=Keyboard.readString();
			System.out.print(" --> Introduzca el n� de km de la carrera : ");
			km=Keyboard.readInt();
			System.out.print(" --> Introduzca la cantidad de coches de tipo A : ");
			cochesA= Keyboard.readInt();
			System.out.print(" --> Introduzca la cantidad de coches de tipo B : ");
			cochesB= Keyboard.readInt();
			System.out.print(" --> Introduzca la cantidad de coches de tipo C : ");
			cochesC= Keyboard.readInt();
			System.out.print(" --> Introduzca la cantidad de coches de tipo D : ");
			cochesD= Keyboard.readInt();
			
			System.out.println("Solicitando ejecuci�n de juego de los Autos Locos... ");
			//Creamos un objeto de mensaje de solicitud de ejecucion de Autos, con todos
			//los parametros
			mAutos= new tMCSAuto(opcJuego,nombreCarrera,cochesA,cochesB,
					     cochesC,cochesD,km);
			//CStream para ese tipo 
			out = new ByteArrayOutputStream (longitud);
			objOut = new ObjectOutputStream (out);
			// Mete m en el Stream objOut
			objOut.writeObject (mAutos);
			// Inicializa un datagrama UDP con: Stream, direccion y puerto
			datagrama = new DatagramPacket (out.toByteArray(), out.size(), direccionIP, puerto);
			// Envia el datagrama a traves del socket
			dsocket.send(datagrama);		    
			
			//#############################################################################
			
			
			//####### Recepci�n de datagrama de resultado de ejecuci�n de autos locos  #############
			System.out.println("Solicitud de ejecuci�n de Autos Locos enviada. Esperando respuesta...");
			//Creamos un nuevo datagrama para recibir la respuesta
			datagrama2 = new DatagramPacket (new byte[longitud], longitud);
			// Nos bloqueamos hasta recibir el mensaje 
			dsocket.receive(datagrama2);
			System.out.println("Recibida respuesta...");
			// Una vez ha llegado el mensaje....
			//Lo recogemos en un array de Bytes
			in = new ByteArrayInputStream (datagrama2.getData());
			//Y usamos una "envoltura", para poder usarlo como objeto
			objIn  = new ObjectInputStream (in);
			// Extrae el objeto de clase tMSCMus
			respAutos = (tMSCAuto) objIn.readObject();
			// Ajusta el atributo para poder leer correctamente en el proximo receive
			datagrama2.setLength(longitud);
			//#############################################################################	
			
			//###### Mostramos la salida de la ejecuci�n del Mus ##########################
			System.out.println("Recibida salida de los Autos Locos...");
			System.out.println("--------------------------------------------------------------------------");
			System.out.println(respAutos.getResultadoAutos());
			System.out.println("--------------------------------------------------------------------------");
			//#############################################################################

		    }

		    break;
		
		case 3: System.out.println("� Adios !");
		    break;
		}
		    
		
		
	    }while(opcJuego!=3);
	    
	    // ##############################################################


	    // Cierra el socket. 
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
		System.err.println("Uso: java ClienteJuegos <nombre_maquina> <puerto>\n");
	    }
	}
    }
	
	//------------------------------------------------------------------
	//    Nos devuelve el valor del billete en funcion de la opcion: ���pq me ha obligado a meterle static??
	//------------------------------------------------------------------
	private static int Billete2Valor(char billete)
	{
	    int valorDevuelto=0;
	    switch(billete){
	    case 'A': valorDevuelto=100;
		break;
	    case 'B': valorDevuelto=50;
		break;
	    case 'C': valorDevuelto=20;
		break;
	    case 'D': valorDevuelto=10;
		break;
	    case 'E': valorDevuelto=5;
		break;
	    default : valorDevuelto=-1;
		break;
	    }
	    
	    //System.out.println("El valor de ese billete es de : " + valorDevuelto + " euros");
	    return valorDevuelto;
	}
		
} 
