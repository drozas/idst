//******************************************************************#
//  PruebCajero.java   
//  
//  David Rozas
//  Utiliza la clase cajero, con billetes de 50,20,10 y 5 euros
//******************************************************************#

    import cs1.Keyboard;

public class PruebaCajero
{
    

   public static void main (String[] args)
   {
       //Creamos el array de cajero
       Cajero mi_cajero= new Cajero();
       Cajero mi_cajero_aux= new Cajero();
       int valor=0;
       int nBilletes=0;
       int cantidad=0;
       int i=0;
       
       //Introducción de valores iniciales
       System.out.println("************** Inicialización del cajero **************");

       //Funciona para todo tipo de billetes. Pero nos los tienen que introducir de mayor a menor valor.
       for (i=0; i<4; i++)
       {
	   System.out.print("Introduzca el valor del tipo " + (i+1) + " de billetes : ");
	   valor = Keyboard.readInt();
	   System.out.print("Introduzca la cantidad de billetes : ");
	   nBilletes= Keyboard.readInt();
	   mi_cajero.anadirBillete(valor,nBilletes);
       }

       //guardamos los valores en un auxiliar, que es donde se ordenaran por cantidad para ordenarlos.
       mi_cajero_aux.copiarDe(mi_cajero);
       mi_cajero_aux.ordenarPorCantidades();
       System.out.println("Billetes del cajero : ");
       System.out.println(mi_cajero_aux.toString());


       //El programa se ejecuta mientras haya algun billete
       while (mi_cajero.quedanBilletes())
       {
	   System.out.println(" ********************** Bienvenid@ al cajero ********************** "); 
	   System.out.println();
	   System.out.println("> Introduzca la cantidad que desea sacar : ");
	   cantidad= Keyboard.readInt();
       
	   mi_cajero.sacarDinero(cantidad);

       }

   }


}