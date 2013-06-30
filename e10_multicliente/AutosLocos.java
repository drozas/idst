//********************************************************************#
//  Autor: David Rozas
//  AutosLocos.java      
//
//  Muestra el uso de la clase carrera
//********************************************************************#

    import cs1.Keyboard;

public class AutosLocos
{
    
    
    public static void main (String[] args) throws menorQueCeroException
    {
	int cochesA,cochesB,cochesC,cochesD;
	int km;
	String nombreCarrera;
	Carrera carrera_loca; 
	menorQueCeroException noHayKm =
	    new menorQueCeroException ("¡El nº de km tiene que ser mayor que 0!");

	//Seguiremos generando carreras, hasta la última carrera, que tendra que
	//recibir el nombre de FIN
	do 
	{
	    System.out.print(" --> Introduzca el nombre de la carrera : " );
	    nombreCarrera=Keyboard.readString();
	    System.out.print(" --> Introduzca el nº de km de la carrera : ");
	    km=Keyboard.readInt();
	    System.out.print(" --> Introduzca la cantidad de coches de tipo A : ");
	    cochesA= Keyboard.readInt();
	    System.out.print(" --> Introduzca la cantidad de coches de tipo B : ");
	    cochesB= Keyboard.readInt();
	    System.out.print(" --> Introduzca la cantidad de coches de tipo C : ");
	    cochesC= Keyboard.readInt();
	    System.out.print(" --> Introduzca la cantidad de coches de tipo D : ");
	    cochesD= Keyboard.readInt();
	    
	    
	    carrera_loca= new Carrera(nombreCarrera,cochesA,cochesB,cochesC,cochesD,km);
	    
	    //Levantamos excepcion si la cantidad de km es menor que 0
	    if (carrera_loca.get_kilometros()<=0)
	    {
		throw noHayKm;
	    }else{
		
		System.out.println("# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #");
		System.out.println(" # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #");
		System.out.println(" ¡ Comienza la carrera : " + carrera_loca.get_nombre() + " ! " );
		System.out.println(" Nº de kilómetros del circuito :  " + carrera_loca.get_kilometros() );
		System.out.println(" Nº de participantes : " + carrera_loca.get_nTotal());
		System.out.println("# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #");
		System.out.println(" # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #");
		carrera_loca.PreparadosListosYa();
	    }
	}while( !(nombreCarrera.equals("FIN")) );
	
    }
}
