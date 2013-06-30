//********************************************************************#
//  Autor: David Rozas
//  Receptor.java      
//
//  Recibe peticiones de ejecucion de juegos desde el cliente; y 
//  se encarga también de la marcha del cajero.
//********************************************************************#
import java.io.*;
import java.net.*;

public class ServidorJuegos {
    public static void main(String args[]) {
	IllegalArgumentException usoIncorrecto = 
	    new IllegalArgumentException ("Numero de argumentos incorrecto");

	//Excepciones agregadas:
	NumberFormatException puertoNoNumerico =
	    new NumberFormatException ("¡Error en el argumento puerto!");

	BindException puertoOcupado =
	    new BindException ("¡Error, ese puerto ya esta usado!");

	// Longitud para almacenar lo recibido
	final int longitud = 10000;
	
	int tJuego;
	int precioJuego;
	int valorBillete;
	int tBillete;
	int portCliente;
	java.net.InetAddress ipCliente;
	String resultadoEjecucionMus;
	String respuestaCajero;
	String nombreCarrera="";
	int cochesA,cochesB,cochesC,cochesD,km;
	String resultadoEjecucionCarrera;
	boolean precioJusto;
	
	DatagramPacket datagrama;//datagramas de recepcion
	DatagramPacket datagrama2; //datagramas de envio
	ByteArrayInputStream in;
	ObjectInputStream objIn;
	ByteArrayOutputStream out;
	ObjectOutputStream objOut;
	tMCSSondeo mSondeoPrecio;
	tMSCSondeo respPrecio;
	tMCSCajero mCajero;
	tMSCCajero respCajero;
	tMCSMus mMus;
	tMSCMus respMus;
	tMCSAuto mAuto;
	tMSCAuto respAuto;


	//Inicializamos el cajero. Hemos de establecer los valores acorde a lo que esperamos del cliente
	Cajero mi_cajero= new Cajero();
	
	//Inicializacion con 5 billetes de cada.Usaremos de 50,20,10 y 5
	mi_cajero.anadirBillete(100,10);
	mi_cajero.anadirBillete(50,10);
	mi_cajero.anadirBillete(20,10);
	mi_cajero.anadirBillete(10,10);
	mi_cajero.anadirBillete(5,10);
	System.out.println("###Inicializando contenido del cajero..." + "\n" + mi_cajero.toString());
	

        try {
            if (args.length != 1) 
                throw usoIncorrecto;

            // Obtiene el puerto de args[0]
            int puerto = Integer.parseInt(args[0]);
	    if (puerto<=0)
	    {
		throw puertoNoNumerico;
	    }
            // Crea un socket UDP y se ata al puerto. 
            DatagramSocket dsocket = new DatagramSocket(puerto);
	    
	    // Crea un DatagramPacket para almacenar en él un mensaje.
	    datagrama = new DatagramPacket (new byte[longitud], longitud);
            for(;;) {


		//###########Recepcion de datagrama de sondeo de precio #############
                // Esta llamada bloquea el programa hasta que se reciba un mensaje
		System.out.println("Esperando a recibir nuevos clientes...");
                dsocket.receive(datagrama);
		System.out.println("¡Nuevo cliente recibido");
		// Ha llegado un datagrama. Asociamos un Stream a los datos recibidos del datagrama
		in = new ByteArrayInputStream (datagrama.getData());
		objIn = new ObjectInputStream (in);
		// Extrae el objeto de clase Mensaje del Stream y lo muestra. 
		mSondeoPrecio = (tMCSSondeo) objIn.readObject();
		// Ajusta el atributo length para permitir leer hasta <longitud> bytes en el próximo receive.
		datagrama.setLength(longitud);
		//##########################################################################

		//######### Consulta de tipo del juego y su precio########################

		tJuego=mSondeoPrecio.getTPrograma();
		precioJuego= juego2Precio(tJuego);
		//#######################################################################
		//A partir de aquí, dejaremos jugar a este cliente solo a este juego si 
		//ingresa la cantidad suficiente
		
		switch(tJuego){
		case 1: 
		    System.out.println("Información del cliente :");
		    System.out.println("Origen: " + datagrama.getAddress() +
				       ": " + datagrama.getPort()); 
		    System.out.println("Ha elegido jugar al Mus");
		    //Recogemos su ip y port
		    portCliente= datagrama.getPort();
		    ipCliente=datagrama.getAddress();
		    
		    //##### Respuesta a Precio de juego#################
		    respPrecio = new tMSCSondeo(tJuego,precioJuego);
		
		    // Creamos un array de Bytes para meter el objeto
		    out = new ByteArrayOutputStream (longitud);
		    
		    // Y una "envoltura" para poder meterlo como "objeto"
		    objOut = new ObjectOutputStream (out);

		    // Mete m en el Stream objOut
		    objOut.writeObject (respPrecio);

		    //Creamos un datagrama udp, con la ip y el puerto del emisor
		    datagrama2 = new DatagramPacket(out.toByteArray(), out.size(),
						    ipCliente,portCliente);
		    //y lo enviamos 
		    dsocket.send(datagrama2);
		    System.out.println("Enviado mensaje de sondeo de precio... ");
		    //########################################################################################
		    
		    
		    //############# Recepcion de mensaje de ingreso de billete ##############################
		    System.out.println("Esperando mensaje de ingreso de billete...");
		    dsocket.receive(datagrama);
		    System.out.println("Mensaje con ingreso de billete recibido...");
		    
		    // Ha llegado un datagrama. Asociamos un Stream a los
		    // datos recibidos del datagrama
		    in = new ByteArrayInputStream (datagrama.getData());
		    objIn = new ObjectInputStream (in);
		   
		    // ni de un descendiente de Mensaje, se elevaría una excepción
		    mCajero = (tMCSCajero) objIn.readObject();
		    // Ajusta el atributo length para permitir leer hasta <longitud> bytes en el próximo receive. 
		    datagrama.setLength(longitud);
		    //#####################################################################################

		    //########### Análisis del billete ingresado#########################################
		    valorBillete= mCajero.getValorBillete();
		    //Nos devolvera la posicion del billete a incrementar
		    tBillete= valorBillete2TipoBillete(valorBillete);
		    //##################################################################################

		    //################## Acceso al cajero y envío de Vueltas ###########################
		    //Ingresamos el billete de 50 euros-> pos 0
		    mi_cajero.ingresarUnBillete(tBillete);
		    System.out.println("###Hemos ingresado un billete ..." + mi_cajero.toString());
		    
		    //Tenemos que controlar el caso en el que el valor del billet cubra "justo" el 
		    // valor del juego. En cuyo caso, no devolvemos vueltas, pero si permitimos la ejecucion
		    
		    if (!(esPrecioJusto(valorBillete,precioJuego)))
		    {
			//Le damos el cambio si no es precio exacto
			respuestaCajero=mi_cajero.sacarDinero(valorBillete-precioJuego);
		    }else{
			//Si no, le escribimos un mensaje diciendo que no tiene que recoger cambio
			respuestaCajero="Gracias por introducir importe exacto.";

		    }
		    
		    //TENDREMOS QUE CONTROLAR EN EL SERVER EL CASO DE NO HABER SIDO POSIBLE DAR CAMBIO
		    //EN CUYO CASO, TENDREMOS QUE VOLVER A RESTAR EL BILLETE INGRESADO!!!
		    if (respuestaCajero.equals(""))
		    {
			System.out.println("No se pudo satisfacer la cantidad");
			mi_cajero.restarUnBillete(tBillete);
			//Y ya, enviamos el mensaje al cliente...
			//##### Respuesta a Consulta de Cajero con cadena vacia  #################
			respCajero = new tMSCCajero(tJuego,respuestaCajero);
			
			// Creamos un array de Bytes para meter el objeto
			out = new ByteArrayOutputStream (longitud);
		    
			// Y una "envoltura" para poder meterlo como "objeto"
			objOut = new ObjectOutputStream (out);

			// Mete m en el Stream objOut
			objOut.writeObject (respCajero);

			//Creamos un datagrama udp, con la ip y el puerto del emisor
			datagrama2 = new DatagramPacket(out.toByteArray(), out.size(),
							ipCliente,portCliente);
			//y lo enviamos 
			dsocket.send(datagrama2);
			System.out.println("Enviado mensaje de respuesta del cajero... ");
			//########################################################################################

		    }else{
			System.out.println("###Traza-> cambio devuelto al cliente :" + respuestaCajero);
		    
		    
			System.out.println("###Traza cajero despues de dar el cambio: " + mi_cajero.toString());
		    
			//El cliente se enterara, pq si no fue posible le enviaremos la cadena vacia

			//Y ya, enviamos el mensaje al cliente...
			//##### Respuesta a Consulta de Cajero con vueltas  #################
			respCajero = new tMSCCajero(tJuego,respuestaCajero);
			
			// Creamos un array de Bytes para meter el objeto
			out = new ByteArrayOutputStream (longitud);
		    
			// Y una "envoltura" para poder meterlo como "objeto"
			objOut = new ObjectOutputStream (out);

			// Mete m en el Stream objOut
			objOut.writeObject (respCajero);

			//Creamos un datagrama udp, con la ip y el puerto del emisor
			datagrama2 = new DatagramPacket(out.toByteArray(), out.size(),
							ipCliente,portCliente);
			//y lo enviamos 
			dsocket.send(datagrama2);
			System.out.println("Enviado mensaje de respuesta del cajero... ");
			//########################################################################################
			
			//############# Recepcion de ejecución del juego Mus ##############################
			System.out.println("Esperando mensaje de ejecución del juego del Mus...");
			dsocket.receive(datagrama);
			System.out.println("Mensaje de ejecución del Mus recibido...");
			
			// Ha llegado un datagrama. Asociamos un Stream a los
			// datos recibidos del datagrama
			in = new ByteArrayInputStream (datagrama.getData());
			objIn = new ObjectInputStream (in);
			
			// ni de un descendiente de Mensaje, se elevaría una excepción
			mMus = (tMCSMus) objIn.readObject();
			
			// Ajuste para poder leer<longitud> bytes en el próximo receive.
			datagrama.setLength(longitud);
			//#####################################################################################

			//##################### Ejecución del Mus ########################################
			//En el caso del mus, el cliente no nos aporta nada, ya que todo es aleatorio
			//Así que, nos limitamos a ejecutarlo
			EjecutaMus partidaMus= new EjecutaMus();
			resultadoEjecucionMus= partidaMus.getResultado();
			//#################################################################################
			
			//############### Envío de mensaje con resultados de ejecución #####################
			respMus = new tMSCMus(tJuego,resultadoEjecucionMus);
			
			// Creamos un array de Bytes para meter el objeto
			out = new ByteArrayOutputStream (longitud);
			
			// Y una "envoltura" para poder meterlo como "objeto"
			objOut = new ObjectOutputStream (out);
			
			// Mete m en el Stream objOut
			objOut.writeObject (respMus);
			
			//Creamos un datagrama udp, con la ip y el puerto del emisor
			datagrama2 = new DatagramPacket(out.toByteArray(), out.size(),
							ipCliente,portCliente);
			//y lo enviamos 
			dsocket.send(datagrama2);
			System.out.println("Enviado mensaje con el resultado de ejecutar el Mus... ");
			//########################################################################################
		    }
		    break;
		case 2:
		    
		    /////////////////////////////////////////////////////////////////////////////////


		    System.out.println("Información del cliente :");
		    System.out.println("Origen: " + datagrama.getAddress() +
				       ": " + datagrama.getPort()); 
		    System.out.println("Ha elegido jugar a los Autos Locos");
		    //Recogemos su ip y port
		    portCliente= datagrama.getPort();
		    ipCliente=datagrama.getAddress();
		    
		    //##### Respuesta a Precio de juego#################
		    respPrecio = new tMSCSondeo(tJuego,precioJuego);
		
		    // Creamos un array de Bytes para meter el objeto
		    out = new ByteArrayOutputStream (longitud);
		    
		    // Y una "envoltura" para poder meterlo como "objeto"
		    objOut = new ObjectOutputStream (out);

		    // Mete m en el Stream objOut
		    objOut.writeObject (respPrecio);

		    //Creamos un datagrama udp, con la ip y el puerto del emisor
		    datagrama2 = new DatagramPacket(out.toByteArray(), out.size(),
								   ipCliente,portCliente);
		    //y lo enviamos 
		    dsocket.send(datagrama2);
		    System.out.println("Enviado mensaje de sondeo de precio... ");
		    //########################################################################################
		    
		    
		    //############# Recepcion de mensaje de ingreso de billete ##############################
		    System.out.println("Esperando mensaje de ingreso de billete...");
		    dsocket.receive(datagrama);
		    System.out.println("Mensaje con ingreso de billete recibido...");
		    
		    // Ha llegado un datagrama. Asociamos un Stream a los
		    // datos recibidos del datagrama
		    in = new ByteArrayInputStream (datagrama.getData());
		    objIn = new ObjectInputStream (in);
		   
		    // ni de un descendiente de Mensaje, se elevaría una excepción
		    mCajero = (tMCSCajero) objIn.readObject();
		    // Ajusta el atributo length para permitir leer hasta <longitud> bytes en el próximo receive. 
		    datagrama.setLength(longitud);
		    //#####################################################################################

		    //########### Análisis del billete ingresado#########################################
		    valorBillete= mCajero.getValorBillete();
		    //Nos devolvera la posicion del billete a incrementar
		    tBillete= valorBillete2TipoBillete(valorBillete);
		    //##################################################################################

		    //################## Acceso al cajero y envío de Vueltas ###########################
		    //Ingresamos el billete de 50 euros-> pos 0
		    mi_cajero.ingresarUnBillete(tBillete);
		    System.out.println("###Hemos ingresado un billete ..." + mi_cajero.toString());
		    
		    //Tenemos que controlar el caso en el que el valor del billet cubra "justo" el 
		    // valor del juego. En cuyo caso, no devolvemos vueltas, pero permitimos la ejecucion.
		    
		    if (!(esPrecioJusto(valorBillete,precioJuego)))
		    {
			//Le damos el cambio si no es precio exacto
			respuestaCajero=mi_cajero.sacarDinero(valorBillete-precioJuego);
		    }else{
			//Si no, le escribimos un mensaje diciendo que no tiene que recoger cambio
			respuestaCajero="Gracias por introducir importe exacto.";

		    }

		    //TENDREMOS QUE CONTROLAR EN EL SERVER EL CASO DE NO HABER SIDO POSIBLE DAR CAMBIO
		    //EN CUYO CASO, TENDREMOS QUE VOLVER A RESTAR EL BILLETE INGRESADO!!!
		    
		    if (respuestaCajero.equals(""))
		    {
			System.out.println("No se pudo satisfacer la cantidad");
			mi_cajero.restarUnBillete(tBillete);
			//Y ya, enviamos el mensaje al cliente...
			//##### Respuesta a Consulta de Cajero con cadena vacia  #################
			respCajero = new tMSCCajero(tJuego,respuestaCajero);
			
			// Creamos un array de Bytes para meter el objeto
			out = new ByteArrayOutputStream (longitud);
		    
			// Y una "envoltura" para poder meterlo como "objeto"
			objOut = new ObjectOutputStream (out);

			// Mete m en el Stream objOut
			objOut.writeObject (respCajero);

			//Creamos un datagrama udp, con la ip y el puerto del emisor
			datagrama2 = new DatagramPacket(out.toByteArray(), out.size(),
							ipCliente,portCliente);
			//y lo enviamos 
			dsocket.send(datagrama2);
			System.out.println("Enviado mensaje de respuesta del cajero... ");
			//########################################################################################

		    }else{
			System.out.println("###Traza-> cambio devuelto al cliente :" + respuestaCajero);
		    
		    
			System.out.println("###Traza cajero despues de dar el cambio: " + mi_cajero.toString());
		    
			//El cliente se enterara, pq si no fue posible le enviaremos la cadena vacia

			//Y ya, enviamos el mensaje al cliente...
			//##### Respuesta a Consulta de Cajero con vueltas  #################
			respCajero = new tMSCCajero(tJuego,respuestaCajero);
			
			// Creamos un array de Bytes para meter el objeto
			out = new ByteArrayOutputStream (longitud);
		    
			// Y una "envoltura" para poder meterlo como "objeto"
			objOut = new ObjectOutputStream (out);

			// Mete m en el Stream objOut
			objOut.writeObject (respCajero);

			//Creamos un datagrama udp, con la ip y el puerto del emisor
			datagrama2 = new DatagramPacket(out.toByteArray(), out.size(),
							ipCliente,portCliente);
			//y lo enviamos 
			dsocket.send(datagrama2);
			System.out.println("Enviado mensaje de respuesta del cajero... ");
			//########################################################################################
			
			//############# Recepcion de ejecución del juego Autos Locos ##############################
			System.out.println("Esperando mensaje de ejecución del juego de los Autos Locos...");
			dsocket.receive(datagrama);
			System.out.println("Mensaje de ejecución de juego de Autos Locos recibido...");
			
			// Ha llegado un datagrama. Asociamos un Stream a los
			// datos recibidos del datagrama
			in = new ByteArrayInputStream (datagrama.getData());
			objIn = new ObjectInputStream (in);
			
			// ni de un descendiente de Mensaje, se elevaría una excepción
			mAuto = (tMCSAuto) objIn.readObject();
			
			// Ajuste para poder leer<longitud> bytes en el próximo receive.
			datagrama.setLength(longitud);
			//#####################################################################################

			//##################### Ejecución de los Autos Locos ###################################
			//En el caso de los autos, tendremos que hacer la llamada al constructor con los
			//parámetros que nos ha enviado el cliente
			nombreCarrera= mAuto.getNombreCarrera();
			cochesA= mAuto.getCochesA();
			cochesB= mAuto.getCochesB();
			cochesC= mAuto.getCochesC();
			cochesD= mAuto.getCochesD();
			km= mAuto.getKm();

			EjecutaCarrera carreraLoca= new EjecutaCarrera(nombreCarrera,cochesA,cochesB,
								       cochesC,cochesD,km);
			resultadoEjecucionCarrera= carreraLoca.getResultado();


			//#################################################################################
			
			//############### Envío de mensaje con resultados de ejecución #####################
			respAuto = new tMSCAuto(tJuego,resultadoEjecucionCarrera);
			
			// Creamos un array de Bytes para meter el objeto
			out = new ByteArrayOutputStream (longitud);
			
			// Y una "envoltura" para poder meterlo como "objeto"
			objOut = new ObjectOutputStream (out);
			
			// Mete m en el Stream objOut
			objOut.writeObject (respAuto);
			
			//Creamos un datagrama udp, con la ip y el puerto del emisor
			datagrama2 = new DatagramPacket(out.toByteArray(), out.size(),
							ipCliente,portCliente);
			//y lo enviamos 
			dsocket.send(datagrama2);
			System.out.println("Enviado mensaje con el resultado de ejecutar los Autos Locos... ");
			//########################################################################################
		    }


			/////////////////////////////////////////////////////////////////////////////////////
		    break;
		}

            }
        }
        catch (Exception e) {
            System.err.println(e);
	   
	    if (e instanceof NumberFormatException)
		{
		System.err.println("El parámetro puerto debe ser numérico y positivo\n");
	    }else if (e instanceof BindException)
	    {
		System.err.println("Puerto ocupado, prueba con otro");
	    }else if (e instanceof IllegalArgumentException)
	    {
		System.err.println("Uso: java Receptor <puerto>");
	    }
        }
    }
    
    //------------------------------------------------------------------
    //    Nos devuelve la pos del billete en funcion de su valor ¿¿¿pq me ha obligado a meterle static??
    //------------------------------------------------------------------
    private static int valorBillete2TipoBillete(int valor)
    {
	int valorDevuelto=0;
	switch(valor){
	case 100: valorDevuelto=0;
	    break;
	case 50: valorDevuelto=1;
	    break;
	case 20: valorDevuelto=2;
		break;
	case 10: valorDevuelto=3;
	    break;
	case 5: valorDevuelto=4;
	    break;
	    default : valorDevuelto=-1;
		break;
	}
	
	System.out.println("La posicion en el array de ese billete es : " + valorDevuelto);
	return valorDevuelto;
    }
    
    //------------------------------------------------------------------
    //    Nos devuelve el precio del juego, en funcion del tipo de programa ¿¿¿pq me ha obligado a meterle static??
    //------------------------------------------------------------------
    private static int juego2Precio(int juego)
    {
	int valorDevuelto=0;
	    switch(juego){
	    case 1: valorDevuelto=15;
		break;
	    case 2: valorDevuelto=20;
		break;
	    default : valorDevuelto=-1;
		break;
	    }
	    
	    System.out.println("###Jugar a ese juego cuesta : " + valorDevuelto + " euros");
	    return valorDevuelto;
    }
    
    //------------------------------------------------------------------
    //    Nos devuelve un booleano diciendo si el valor del billete
    //    cubre exactamente el precio del juego¿¿¿pq me ha obligado a meterle static??
    //------------------------------------------------------------------
    private static boolean esPrecioJusto(int valorBillete, int precioJuego)
    {
	boolean res;
	
	if (valorBillete==precioJuego)
	{
	    res=true;
	    System.out.println("###es precio exacto");
	}else{
	    res=false;
	    System.out.println("###no es precio exacto, hay que dar vueltas");
	}
	
	return res;
    }
    
    
    
}
