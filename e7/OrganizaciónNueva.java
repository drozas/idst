//********************************************************************#
//  David Rozas
//
//  Organización.java                           
//
//  Demuestra el polimorfismo a través de la herencia
//********************************************************************#

public class OrganizaciónNueva
{
   //-----------------------------------------------------------------
   //  Crea el personal de la organización y les paga
   //-----------------------------------------------------------------
   public static void main (String[] args)
   {
      Personal elPersonal = new Personal();

      elPersonal.díaDePaga();

      System.out.println("Personal de la Organización");
      System.out.println("===========================");
      System.out.println(elPersonal);
      elPersonal.ordenar();
      System.out.println("Personal ORDENADO de la Organización");
      System.out.println("====================================");
      System.out.println(elPersonal);     
   }

}
