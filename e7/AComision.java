//*********************************************************************************#
//  David Rozas
//      
//  AComision.java                    
//
//  Representa un empleado al que se le paga por horas, pero que se lleva comision.
//  extiende a la clase por horas.
//*********************************************************************************#


public class AComision extends PorHoras
{
    private double totalVentas;
    private double porcentajeComision;


    //---------------------------------------------------------------------------
   //  Construye un empleado a comisi�n, con la informaci�n que se le proporciona
   //---------------------------------------------------------------------------
   public AComision (String nombre, String direcci�n, String tel�fono,
		    String n�meroSeguridadSocial, double salario, double porcentajeComision)
   {
       super (nombre, direcci�n, tel�fono, n�meroSeguridadSocial, salario);
       
       this.totalVentas=0;
       this.porcentajeComision=porcentajeComision;
	   
   }


    //---------------------------------------------------------------------------
   // A�ade el valor del par�metro a las ventas acumuladas en el atributo correspondiente
   //---------------------------------------------------------------------------
    public void a�adirVentas (double ventas)
    {
	this.totalVentas+=ventas;
    }
	
   //----------------------------------------------------------------------------------------
   //  Calcula y devuelve la paga de un empleado por comision-> especializa el de PorHoras!
   //----------------------------------------------------------------------------------------

    public double pagar()
    {
	double pagoDevuelto=0.0;
	
	//LLamamos a la funcion de la clase de la que hereda, y pagar() de la superior
	// ya nos inicializa horasTrabajadas
	pagoDevuelto= super.pagar() + (this.totalVentas * this.porcentajeComision) ;
	
	//Inicializamos nuestro atributo ventas
	this.totalVentas= 0.0;
	
	return pagoDevuelto;

    }    

   //-----------------------------------------------------------------#
   //  Devuelve un string con la inforamaci�n de este empleado por comision
   //-----------------------------------------------------------------#
   public String toString()
   {
      String resultado = super.toString();

      resultado += "Cobro total de ventas pendientes : " + this.totalVentas + "\n";
      resultado += "Porcentaje : " + this.porcentajeComision + "\n";

      return resultado;
   }

}