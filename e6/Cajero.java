//*******************************************************************#
//  Cajero.java   
//  
//  David Rozas
//  Clase que alberga diversos tipos de billetes
//*******************************************************************#

public class Cajero
{
    private MontonDeBilletes[] billetes;
    private int contador;
    private int dineroTotal;
    
   //-----------------------------------------------------------------
   //  Crea el array de billetes inicial
   //-----------------------------------------------------------------
   public Cajero ()
   {
       this.billetes = new MontonDeBilletes[4];
       this.dineroTotal=0;
       this.contador=0;
   }
    
   //-----------------------------------------------------------------
   //  Añade los valores de un billete al array
   //-----------------------------------------------------------------
    public void anadirBillete(int valor, int nBilletes)
    {

	//Controlamos que no se salga del rango
	if (this.contador<billetes.length)
	{
	    this.billetes[contador]= new MontonDeBilletes(valor,nBilletes);
	    this.contador= this.contador + 1;
	    this.dineroTotal= this.dineroTotal + (valor*nBilletes);
	}else{
	    System.out.println("Ya no caben más tipos de billetes en el array");
	}
    }

    //----------------------------------------------------------------
    // Convierte a un string la informacion relativa al cajero
    //---------------------------------------------------------------
    public String toString()
    {
	String cadenaDevuelta="";
	int i=0;

	cadenaDevuelta+="Dinero total : " + this.dineroTotal + "\n";

	for (i=0; i<this.contador; i++)
	{
	    cadenaDevuelta+= billetes[i].toString() + "\n";
	}

	return cadenaDevuelta;
    }

   //-----------------------------------------------------------------
   //  Devuelve la cantidad total de que dispone el cajero
   //-----------------------------------------------------------------
    public int getTotal()
    {
	int valorDevuelto=0;

	valorDevuelto=this.dineroTotal;
	return valorDevuelto;
    }
    
   //-----------------------------------------------------------------
   //  Devuelve cuantos tipos de billetes hay
   //-----------------------------------------------------------------
    public int getSize()
    {
	int valorDevuelto=0;

	valorDevuelto=this.contador;
	return valorDevuelto;
    }


   //-----------------------------------------------------------------
   //  Recorre el array, y nos dice si queda algun billete de algun tipo
   //-----------------------------------------------------------------
    
    public boolean quedanBilletes()
    {
	boolean hayAlguno=false;
	int i=0;
	
	//Recorremos todos los montones, mirando si hay alguno
	while( (i< this.billetes.length) && (!hayAlguno) )
	{
	    if (this.billetes[i].quedanBilletes())
	    {
		hayAlguno=true;
	    }
	    
	    i=i+1;
	}
	return hayAlguno;
    }


   //-----------------------------------------------------------------
   //  Devuelve la cantidad de dinero que le piden en los billetes más
   //  grandes posibles (si puede)
   //-----------------------------------------------------------------

    public void sacarDinero (int cantidad)
    {
	boolean fuePosible=true;
	int[] nBilletesDevolver= new int[this.billetes.length];
	int i=0;
	int cantidadAux=0;
	
	//Inicializamos el array de billetes a devolver
	for (i=0; i<this.billetes.length; i++)
	{
	  nBilletesDevolver[i]=0;
	}
	
	cantidadAux=cantidad;

	//Comprobamos que tengamos dinero suficiente, y que nos den valores lógicos
	if ( ( cantidad <= this.getTotal()  ) && cantidadAux>0)
	{

	    i=0;
	    //Recorremos todos los montones ordenados por su valor
	    while ((i< this.billetes.length) && (cantidad>0))
	    {

		//Damos todos los billetes posibles del maximo valor
		while ( (cantidad>=this.billetes[i].getValor()) && this.billetes[i].quedanBilletes() )
		    {
			//Le descontamos a cantidad el valor del billete
			cantidad=cantidad-this.billetes[i].getValor();
			//Anotamos en un array que vamos a dar un billete de ese tipo
			nBilletesDevolver[i]=nBilletesDevolver[i] + 1;
			//Y lo descontamos en el cajero
			this.billetes[i].setnBilletes(this.billetes[i].getnBilletes() - 1);
		    }
		i++;
	    }

	    fuePosible= cantidad==0;
	   
	}else{
	    fuePosible=false;
	}



	
	//Salida de datos por pantalla, si concluyo correctamente; o vuelta-atrás si no fue posible
	//Controlamos tambien que no nos metieran valores negativos
	if ((fuePosible) &&(cantidadAux>0) )
	{
	    System.out.println("Recoja : ");
	    //Si fue posible, mostramos por pantalla
	    for(i=0; i<this.billetes.length; i++)
	    {
		if (nBilletesDevolver[i]>0)
	        {
		    System.out.println(nBilletesDevolver[i] + " de " + this.billetes[i].getValor() + " euros");
		}
	    }
	    //Y le restamos el total sacado al dinero total.
	    this.dineroTotal= this.dineroTotal-cantidadAux;
	}else{
	   //Si no, tenemos que reponer los billetes que fuimos sacando...
	    for( i=0; i<this.billetes.length; i++)
	    {
		this.billetes[i].setnBilletes(this.billetes[i].getnBilletes()+nBilletesDevolver[i]);
	    }
	   
	    	   
	   System.out.println("No se puede satisfacer esa cantidad");
	}
     
    }


   //-----------------------------------------------------------------
   //  Copia los valores de otro cajero
   //-----------------------------------------------------------------

    public void copiarDe (Cajero cajeroACopiar)
    {
	//Le pasamos una copia del puntero!
	int i=0;

	//Asi que copiamos los valores
	for (i=0; i<this.billetes.length; i++)
	{
	    this.anadirBillete(cajeroACopiar.billetes[i].getValor(), cajeroACopiar.billetes[i].getnBilletes());
	}
    }
   //------------------------------------------------------------------------
   //  Ordena los billetes, en funcion de las cantidades, no de sus valores!
   //-------------------------------------------------------------------------

    public void ordenarPorCantidades ()
    {
	//Utiliza el metodo de ordenación por inserción

	Sorts.insertionSort(this.billetes);
    }
}