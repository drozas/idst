//********************************************************************
//  TestComplejos.java   Autor: GSyC
//
//  Programa para probar el uso de varios objetos de la clase Complejo
//********************************************************************

public class TestComplejos
{

   //-----------------------------------------------------------------------
   //  Crea algunos numeros complejos y realiza varias operaciones con ellos
   //-----------------------------------------------------------------------
    public static void main (String[] args)
    {

	Complejo c1, c2, c3, c4;

	c1 = new Complejo (2, 3);
	c2 = new Complejo (2, 3);
	c3 = new Complejo (0, 1);


	if (c1 == c2) 
	    System.out.println ("c1 y c2 referencian al mismo objeto");
	else 
	    System.out.println ("c1 y c2 NO referencian al mismo objeto");

	if (c1.equals (c2)) 
	    System.out.println 
		("c1 (" + c1 + ") y c2 (" + c2 + ") son iguales");
	else 
	    System.out.println 
		("c1 (" + c1 + ") y c2 (" + c2 + ") NO son iguales");
		 
	c4 = c1.suma (c2);
	System.out.println ("(" + c1 + ")" + " + " + "(" + c2 + ") = " + c4);

	c4 = c2.resta (c3);
	System.out.println ("(" + c2 + ")" + " - " + "(" + c3 + ") = " + c4);

	System.out.println ("Parte real de " +  "(" + c1 + ") = " + c1.parteReal());
	System.out.println ("Parte imaginaria de " + "(" + c1 + ") = " + c1.parteImaginaria());

	if (c3.esImaginarioPuro())
	    System.out.println ("(" + c3 + ") es imaginario puro");
	else if (c3.esRealPuro())
	    System.out.println ("(" + c3 + ") es real puro");

    }
}
