//********************************************************************#
//  David Rozas
//
//  Organizaci�n.java                           
//
//  Demuestra el polimorfismo a trav�s de la herencia
//********************************************************************#

public class Organizaci�nNueva
{
   //-----------------------------------------------------------------
   //  Crea el personal de la organizaci�n y les paga
   //-----------------------------------------------------------------
   public static void main (String[] args)
   {
      Personal elPersonal = new Personal();

      elPersonal.d�aDePaga();

      System.out.println("Personal de la Organizaci�n");
      System.out.println("===========================");
      System.out.println(elPersonal);
      elPersonal.ordenar();
      System.out.println("Personal ORDENADO de la Organizaci�n");
      System.out.println("====================================");
      System.out.println(elPersonal);     
   }

}
