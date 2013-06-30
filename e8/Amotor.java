//********************************************************************#
//  Autor: David Rozas
//  Amotor.java      
//
//  Representa una clase genérica de vehículos a motor
//********************************************************************#
    import java.text.DecimalFormat;

abstract public class Amotor extends Vehiculo
{
    protected double consumo;   
    protected double precioGasolina;
    protected double riesgoExplosion;
 
    //-----------------------------------------------------------------
    //  Construye un vehiculo a gasolina
    //-----------------------------------------------------------------
    public Amotor (int identificador, char clase, double velocidad, double consumo,
		   double precioGasolina, double riesgoExploxion, double penalizacion)
    {
	super (identificador,clase,velocidad,penalizacion);
	
	this.consumo= consumo;//sera un aleatorio
	this.riesgoExplosion= riesgoExploxion *0.1;//sera un aleatorio. Lo "dopamos", para que exploten con menos frecuencia
	this.precioGasolina= precioGasolina;//específico de los subtipos de Amotor
   
    }

   //-----------------------------------------------------------------
   //  Devuelve un string con la información del vehiculo a gasolina
   //-----------------------------------------------------------------
   public String toString()
   {
      String resultado = super.toString();
      DecimalFormat fmt = new DecimalFormat ("0.##");
      
      //(los atributos que estan comentados no se muestran por hacer una salida acorde a la especificacion;
      //pero los dejo comentados por si es necesario hacer trazas)
      //resultado += fmt.format(this.) +"\t";
      //resultado += fmt.format(this.gastoAcumulado) +"\t";
      //resultado += fmt.format(this.precioGasolina) +"\t";
      //resultado += fmt.format(this.riesgoExplosion) +"\t";
      
      return resultado;
   }
    
    //-----------------------------------------------------------------
    //  Las clases derivadas deberan implementar este metodo
    //-----------------------------------------------------------------
    public abstract void correUnKilometro ();
}
