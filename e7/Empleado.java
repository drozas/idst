//********************************************************************
//  Empleado.java       Author: Lewis/Loftus
//                      Adapatado por: GSyC
//
//  Representa a un empleado general a sueldo de la organizaci�n
//********************************************************************

public class Empleado extends Miembro
{
   protected String n�meroSeguridadSocial;
   protected double salario;
   
   //-----------------------------------------------------------------
   //  Construye un Empleado con la informaci�n que se le proporciona
   //-----------------------------------------------------------------
   public Empleado (String nombre, String direcci�n, String tel�fono,
                    String n�meroSeguridadSocial, double salario)
   {
      super (nombre, direcci�n, tel�fono);

      this.n�meroSeguridadSocial = n�meroSeguridadSocial;
      this.salario = salario;
   }

   //-----------------------------------------------------------------
   //  Devuelve un string con la informaci�n del Empleado
   //-----------------------------------------------------------------
   public String toString()
   {
      String resultado = super.toString();

      resultado += "N�mero de la Seguridad Social: " + n�meroSeguridadSocial +"\n";

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
