//********************************************************************#
//  Autor: David Rozas
//  EjecutaCarrera.java      
//
//  Ejecuta una carrera, y guarda el resultado en un String
//********************************************************************#

 
public class EjecutaCarrera
{
    private String res="";

    //-----------------------------------------------------------------
    //  Construye una carrera, y guarda el resultado en un string
    //-----------------------------------------------------------------
    public EjecutaCarrera (String nombreCarrera, int cochesA, int cochesB, int cochesC,
			   int cochesD, int km) throws menorQueCeroException
    {
	Carrera carrera_loca; 
	menorQueCeroException noHayKm =
	    new menorQueCeroException ("¡El nº de km tiene que ser mayor que 0!");

	//Seguiremos generando carreras, hasta la última carrera, que tendra que
	//recibir el nombre de FIN
	//do 
	//{   
	    //Llamamos al constructor de la carrera
	    //System.out.println("llamamos al contructor de carrera");
	    carrera_loca= new Carrera(nombreCarrera,cochesA,cochesB,
				      cochesC,cochesD,km);
	    
	    //Levantamos excepcion si la cantidad de km es menor que 0
	    if (carrera_loca.get_kilometros()<=0)
	    {
		throw noHayKm;
	    }else{
		
		res+="\n# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #";
		res+="\n # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #";
		res+="\n ¡ Comienza la carrera : " + carrera_loca.get_nombre() + " ! " ;
		res+="\n Nº de kilómetros del circuito :  " + carrera_loca.get_kilometros() ;
		res+="\n Nº de participantes : " + carrera_loca.get_nTotal();
		res+="\n# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #";
		res+="\n # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #";
		res+=carrera_loca.PreparadosListosYa();
	    }
	//}while( !(nombreCarrera.equals("FIN")) );
	
    }


    //-----------------------------------------------------------------
    //  Devuelve el atributo resultado
    //--------------------------------------------------------------------
    public String getResultado()
    {
	String resultado = this.res;
	
	return res;
    }


}
