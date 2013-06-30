//********************************************************************#
//  Autor: David Rozas
//  Carrera.java      
//
//  Representa a los participantes de una carrera
//********************************************************************#

    import java.util.Random;

public class Carrera
{
    private Vehiculo[] listaParticipantes;
    private int kmCarrera;
    private String nombre;
    private static Random generador= new Random();
    menorQueCeroException cocheNegativo =
	new menorQueCeroException ("¡Algún valor de cantidad de coches es negativo!");
    menorQueCeroException noHayCoches =
	new menorQueCeroException ("¡El nº de participantes tiene que ser mayor que 0!");
    //-----------------------------------------------------------------
    //  Construye una carrera de vehiculos de distinos tipos
    //-----------------------------------------------------------------
    public Carrera(String nombre, int cantidadCochesTipoA, int cantidadCochesTipoB,
		   int cantidadCochesTipoC, int cantidadCochesTipoD, int kilometros) throws menorQueCeroException
    {
	int identif=1;//índice de dorsales
	int i;
	int desde, hasta;
	int cantidadTotal= cantidadCochesTipoA + cantidadCochesTipoB + cantidadCochesTipoC + cantidadCochesTipoD;
	
	//Haremos saltar una excepcion, si algun nº de coches es negativo o no hay coches
	if ( cantidadCochesTipoA<0 || cantidadCochesTipoB<0 || cantidadCochesTipoC<0 || cantidadCochesTipoD<0 )
	{
	    throw cocheNegativo;
	}else  if (cantidadTotal<=0){
	    throw noHayCoches;
	}else{
	    
	    //Generamos todos en una misma lista
	    listaParticipantes= new Vehiculo[cantidadTotal];
	    
	    //Llamamos a sus constructores: multiplicamos las velocidades por una cantidad, para
	    //que la penalización no haga tanto lastre
	    
	    //Generamos coches con motor a Gasolina
	    desde=0;
	    hasta=cantidadCochesTipoA;
	    for(i=desde;i<hasta;i++)
	    {
		listaParticipantes[i]= new tipoA(identif,generador.nextDouble()*250,
						 generador.nextDouble(),generador.nextDouble());
		identif++;
	    }
	
	    //Generamos coches con motor de Hidrógeno
	    desde= desde + cantidadCochesTipoA;
	    hasta= desde + cantidadCochesTipoB;
	    for(i=desde;i<hasta;i++)
	    {
		listaParticipantes[i]= new tipoB(identif,generador.nextDouble()*200,
						 generador.nextDouble(),generador.nextDouble());
		identif++;
	    }
	
	    //Generamos coches con motor de Fluzo
	    desde= desde + cantidadCochesTipoB;
	    hasta= desde + cantidadCochesTipoC;
	    for(i=desde;i<hasta;i++)
	    {
		listaParticipantes[i]= new tipoC(identif,generador.nextDouble()*170,
						 generador.nextDouble(),generador.nextDouble());
		identif++;
	    }
	    
	    //Generamos coches sin motor
	    desde= desde + cantidadCochesTipoC;
	    hasta= desde + cantidadCochesTipoD;
	    for(i=desde ;i<hasta;i++)
	    {
		listaParticipantes[i]= new tipoD(identif,generador.nextDouble()*40);
		identif++;
	    }
	
	    //y recogemos el resto de parametros
	    this.kmCarrera= kilometros;
	    this.nombre= nombre;
	}
    }

    //-----------------------------------------------------------------
    //  Devuelve el nombre de la carrera
    //-----------------------------------------------------------------
    public String get_nombre()
    {
	String res= this.nombre;
	return res;
    }

    //-----------------------------------------------------------------
    //  Devuelve el nº de kilómetros de la carrera
    //-----------------------------------------------------------------
    public int get_kilometros()
    {
	int res= this.kmCarrera;
	return res;
    }

    //-----------------------------------------------------------------
    //  Devuelve el nº total de partipantes
    //-----------------------------------------------------------------
    public int get_nTotal()
    {
	int res= listaParticipantes.length;
	return res;
    }

    //-----------------------------------------------------------------------
    //  Recorre la lista de vehículos, comprobando que no han explotado todos
    //-----------------------------------------------------------------------
    public boolean quedaAlguno()
    {
	int i=0;
	boolean hayAlguno=false;
	
	//salimos del bucle si queda alguno, o si ya comprobamos todos
	while( (i<listaParticipantes.length) && (!hayAlguno))
	{
	    if (listaParticipantes[i].SigueEnCarrera() )
	    {
		hayAlguno=true;
	    }
	    i++;
	}
	
	return hayAlguno;

    }


   //-----------------------------------------------------------------
   //  Pone en marcha la carrera. Devuelve los resultados en un String
   //-----------------------------------------------------------------
   public String PreparadosListosYa ()
   {
       String res="";
       int km_recorridos =0;
       int i=0;
    
       //Simulación de la carrera km a km. Salimos cuando hayamos recorrido todos los km
       //o no haya coches compitiendo
       while ( (km_recorridos<=this.get_kilometros() ) && (this.quedaAlguno() ) )
	   {

	       //Pintamos los que no hayan explotado: cada 10 km y en el último km
	       if((km_recorridos%10)==0 ||(km_recorridos==this.get_kilometros()) )
	       {
		   res+="\n Kilómetro : " + km_recorridos;
		   res+="\nIdent\t Tipo\t TºReal\t Penaliz\tTºCompens\tGasto\t\n";
		   this.ordenar();
		   res+=this.toString();
	       }
	       
	       //Modificamos los valores cada km recorrido
	       for (i=0; i<listaParticipantes.length; i++)
	       {
		   listaParticipantes[i].correUnKilometro();
	       }


	       km_recorridos++;
	   }

       //Si salimos del bucle sin recorrer todos los km, es que todos explotaron antes de acabar
       if (km_recorridos< this.get_kilometros() )
       {
	   res+="\n Ningún vehículo acabó la carrera.";
	   res+="\n En el km: " + km_recorridos + " todos los vehículos han explotado.";
       }else{
	   res+="\n ¡ Fin de la carrera !"; 
       }
       
       //Devolvemos el resultado de la carrera
       return res;
   }


   //-----------------------------------------------------------------
   //  Ordena segun el método de inserción
   //-----------------------------------------------------------------

    public void ordenar()
    {
	
	Sorts.insertionSort(listaParticipantes);
    }

   //-----------------------------------------------------------------
   //  Devuelve en un string la informacion de todos los participantes
   //-----------------------------------------------------------------

    public String toString()
    {
	int i=0;
	String cadenaDevuelta="";
	
	for (i=0; i< listaParticipantes.length; i++)
	{
	    //Devolvemos solo los que no han explotado
	    if ( listaParticipantes[i].SigueEnCarrera())
		{
		    cadenaDevuelta+= listaParticipantes[i].toString();
	
		}
	}
	
	return cadenaDevuelta;
    }

}
	
