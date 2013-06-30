//********************************************************************
//  PorHoras.java       Author: Lewis/Loftus
//                      Adaptado por: GSyC
//
//  Representa un empleado al que se le paga por horas
//********************************************************************

public class PorHoras extends Empleado
{
   private int horasTrabajadas;

   //-----------------------------------------------------------------
   //  Construye un empleado por horas con la informaci�n que se le proporciona
   //-----------------------------------------------------------------
   public PorHoras (String nombre, String direcci�n, String tel�fono,
		    String n�meroSeguridadSocial, double salario)
   {
      super (nombre, direcci�n, tel�fono, n�meroSeguridadSocial, salario);

      horasTrabajadas = 0;
   }

   //-----------------------------------------------------------------
   //  A�ade la cantidad de horas especificadas a las horas acumuladas
   //  de un empleado
   //-----------------------------------------------------------------
   public void a�adirHoras (int m�sHoras)
   {
      horasTrabajadas += m�sHoras;
   }

   //-----------------------------------------------------------------
   //  Calcula y devuelve la paga de un empleado por horas
   //-----------------------------------------------------------------
   public double pagar()
   {
      double pago = salario * horasTrabajadas;

      horasTrabajadas = 0;

      return pago;
   }

   //-----------------------------------------------------------------
   //  Devuelve un string con la inforamaci�n de este empleado por horas
   //-----------------------------------------------------------------
   public String toString()
   {
      String resultado = super.toString();

      resultado += "Horas acumuladas: " + horasTrabajadas + "\n";

      return resultado;
   }
}
