//********************************************************************
//  Miembro.java       Author: Lewis/Loftus
//                     Adaptado por: GSyC
//
//  Representa un miembro genérico del personal de una organización
//********************************************************************

abstract public class Miembro implements Comparable
{
   protected String nombre;
   protected String dirección;
   protected String teléfono;

   //-----------------------------------------------------------------
   //  Construye un miembro con la información que se le proporciona
   //-----------------------------------------------------------------
   public Miembro (String nombre, String dirección, String teléfono)
   {
      this.nombre = nombre;
      this.dirección = dirección;
      this.teléfono = teléfono;
   }

   //-----------------------------------------------------------------
   //  Devuelve un string con la información del miembro del personal
   //-----------------------------------------------------------------
   public String toString()
   {

      String resultado =   "**********************************************";
      
      resultado+= "\nNombre: " + nombre + "\n";
      resultado += "Dirección: " + dirección + "\n";
      resultado += "Teléfono: " + teléfono + "\n";

      return resultado;
   }

   //-----------------------------------------------------------------
   //  Las clases derivadas deben definir un método pagar para cada 
   //  tipo de miembro del personal
   //-----------------------------------------------------------------
   public abstract double pagar();


   //-----------------------------------------------------------------
   //  Usa el nombre para ordenar, y si no, por el campo teléfono
   //-----------------------------------------------------------------

   public int compareTo (Object other)
   {
      int result;

      if ( nombre.equals(((Miembro)other).nombre) )
         result = teléfono.compareTo(((Miembro)other).teléfono);
      else
         result = nombre.compareTo(((Miembro)other).nombre);

      return result;
   }
}
