//********************************************************************
//  Empleado.java       Author: Lewis/Loftus
//                      Adapatado por: GSyC
//
//  Representa a un empleado general a sueldo de la organización
//********************************************************************

public class Empleado extends Miembro
{
   protected String númeroSeguridadSocial;
   protected double salario;
   
   //-----------------------------------------------------------------
   //  Construye un Empleado con la información que se le proporciona
   //-----------------------------------------------------------------
   public Empleado (String nombre, String dirección, String teléfono,
                    String númeroSeguridadSocial, double salario)
   {
      super (nombre, dirección, teléfono);

      this.númeroSeguridadSocial = númeroSeguridadSocial;
      this.salario = salario;
   }

   //-----------------------------------------------------------------
   //  Devuelve un string con la información del Empleado
   //-----------------------------------------------------------------
   public String toString()
   {
      String resultado = super.toString();

      resultado += "Número de la Seguridad Social: " + númeroSeguridadSocial +"\n";

      return resultado;
   }

   //-----------------------------------------------------------------
   //  Devuelve lo que se le paga al empleado
   //-----------------------------------------------------------------
   public double pagar()
   {
      return salario;
   }
}
