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
       this.billetes = new MontonDeBilletes[5];//x
       this.dineroTotal=0;
       this.contador=0;
   }
    
   //-----------------------------------------------------------------
   //  A�ade los valores de un billete al array
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
	    System.out.println("Ya no caben m�s tipos de billetes en el array");
	}
    }

    //-------------------------------------------------------------------------
    // Suma un billete ���pero con el array ya definido.
    // Tendremos que haber fijado desde fuera que billete es.
    //-------------------------------------------------------------------------
    public void ingresarUnBillete(int pos)
    {
	//Controlamos que no se salga del rango
	if (pos<= billetes.length)
	{
	    //Aumentamos su n� en 1
	    System.out.println("Ingresando un billete de : " + this.billetes[pos].getValor() + " euros ...");
	    this.billetes[pos].setnBilletes(this.billetes[pos].getnBilletes()+1);
	    //Y aumentamos la cantidad de dinero total, pero no el contador
	    //NO HEMOS CREADO UN MONTON MAS, SINO QUE HEMOS SUMADO UN BILLETE QUE NOS HAN DADO
	    this.dineroTotal= this.dineroTotal + this.billetes[pos].getValor();
	}else{
	    System.out.println("Esa pos no existe");
	}
    }
    //-----------------------------------------------------------------------
    // Resta un billete con el array ya definido. Se usa para el rollback
    // en el servidor de juegos
    //----------------------------------------------------------------------
    public void restarUnBillete(int pos)
    {
	//Controlamos que no se salga de rango
	if (pos <=billetes.length)
	{
	    //Decrementamos su n� en 1
	    System.out.println("Devolviendo un billete de : " + this.billetes[pos].getValor() + "euros ...");
	    this.billetes[pos].setnBilletes(this.billetes[pos].getnBilletes()-1);
	    //Y decrementamos la cantidad total
	    this.dineroTotal= this.dineroTotal - this.billetes[pos].getValor();
	}else{
	    System.out.println("Esa pos no existe");
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
   //  Devuelve la cantidad de dinero que le piden en los billetes m�s
   //  grandes posibles (si puede)
    // Para usarlo en nuestro servidor le daremos la cantidad con el precio
    // del juego ya restado.
    // Devolvemos un String con la info del cambio: si es vacia, es
    // que no se pudo satisfacer la cantidad
   //-----------------------------------------------------------------

    public String sacarDinero (int cantidad)
    {
	boolean fuePosible=true;
	int[] nBilletesDevolver= new int[this.billetes.length];
	int i=0;
	int cantidadAux=0;
	String res="";

	//Inicializamos el array de billetes a devolver
	for (i=0; i<this.billetes.length; i++)
	{
	  nBilletesDevolver[i]=0;
	}
	
	cantidadAux=cantidad;

	//Comprobamos que tengamos dinero suficiente, y que nos den valores l�gicos
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



	
	//Salida de datos por pantalla, si concluyo correctamente; o vuelta-atr�s si no fue posible
	//Controlamos tambien que no nos metieran valores negativos
	if ((fuePosible) &&(cantidadAux>0) )
	{
	    //res+="\nRecoja : ";
	    //Si fue posible, mostramos por pantalla
	    for(i=0; i<this.billetes.length; i++)
	    {
		if (nBilletesDevolver[i]>0)
	        {
		    res+=" \n"+ nBilletesDevolver[i] + " de " + this.billetes[i].getValor() + " euros";
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
	   
	    	   
	   res="";
	}
	return res;
     
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
	//Utiliza el metodo de ordenaci�n por inserci�n

	Sorts.insertionSort(this.billetes);
    }

    
}
