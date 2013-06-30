//********************************************************************
//  Miembro.java       Author: Lewis/Loftus
//                     Adaptado por: GSyC
//
//  Representa un miembro gen�rico del personal de una organizaci�n
//********************************************************************

abstract public class Miembro implements Comparable
{
   protected String nombre;
   protected String direcci�n;
   protected String tel�fono;

   //-----------------------------------------------------------------
   //  Construye un miembro con la informaci�n que se le proporciona
   //-----------------------------------------------------------------
   public Miembro (String nombre, String direcci�n, String tel�fono)
   {
      this.nombre = nombre;
      this.direcci�n = direcci�n;
      this.tel�fono = tel�fono;
   }

   //-----------------------------------------------------------------
   //  Devuelve un string con la informaci�n del miembro del personal
   //-----------------------------------------------------------------
   public String toString()
   {

      String resultado =   "**********************************************";
      
      resultado+= "\nNombre: " + nombre + "\n";
      resultado += "Direcci�n: " + direcci�n + "\n";
      resultado += "Tel�fono: " + tel�fono + "\n";

      return resultado;
   }

   //-----------------------------------------------------------------
   //  Las clases derivadas deben definir un m�todo pagar para cada 
   //  tipo de miembro del personal
   //-----------------------------------------------------------------
   public abstract double pagar();


   //-----------------------------------------------------------------
   //  Usa el nombre para ordenar, y si no, por el campo tel�fono
   //-----------------------------------------------------------------

   public int compareTo (Object other)
   {
      int result;

      if ( nombre.equals(((Miembro)other).nombre) )
         result = tel�fono.compareTo(((Miembro)other).tel�fono);
      else
         result = nombre.compareTo(((Miembro)other).nombre);

      return result;
   }
}
