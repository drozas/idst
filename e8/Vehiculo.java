//********************************************************************#
//  Autor: David Rozas
//  Vehiculo.java      
//
//  Representa un vehículo genérico
//********************************************************************#
import java.text.DecimalFormat;
    
abstract public class Vehiculo implements CapazDeCompetir, Comparable
{
    protected int identificador;
    protected double velocidad;
    protected char clase;
    protected double tiempoReal;
    protected double tiempoCompensado;
    protected double gastoAcumulado;
    protected boolean haExplotado;
    protected double penalizacion;
    protected double penalizacionAcumulada;
    
    //-----------------------------------------------------------------
   //  Construye un vehículo con la información que se le proporciona
   //-----------------------------------------------------------------
    public Vehiculo (int identificador, char clase, double velocidad, double penalizacion)
   {
       this.identificador= identificador;//específico de cada subtipo
       this.clase= clase;//específico de cada subtipo
       this.penalizacion=penalizacion;//específico de cada subtipo
       this.velocidad= velocidad;//sera un aleatorio
       this.tiempoReal= 0.0;
       this.tiempoCompensado= 0.0;
       this.gastoAcumulado=0.0;
       this.haExplotado=false;
       this.penalizacionAcumulada=0.0;

   }

    //-----------------------------------------------------------------
    //  Devuelve la clase del vehículo
    //-----------------------------------------------------------------
    public char get_clase()
    {
	char res= this.clase;
	return res;
    }

    //-----------------------------------------------------------------
    //  Devuelve el identif. del vehiculo
    //-----------------------------------------------------------------
    public int get_id()
    {
	int res= this.identificador;
	return res;
    }


   //-----------------------------------------------------------------
   //  Devuelve un string con la información del vehículo
   //-----------------------------------------------------------------
   public String toString()
   {

      String resultado ="";
      DecimalFormat fmt = new DecimalFormat ("00.##");
      DecimalFormat fmt_gasto= new DecimalFormat("00.########");
      
      //(los atributos que estan comentados no se muestran por hacer una salida acorde a la especificacion;
      //pero los dejo comentados por si es necesario hacer trazas)
      resultado+= this.identificador +"\t";
      resultado+= this.clase +"\t";
      //resultado+= fmt.format(this.velocidad) +"\t";
      resultado+= fmt.format(this.tiempoReal) + "s" + "\t";
      resultado+= fmt.format(this.penalizacionAcumulada) +"s" +"\t \t";   
      resultado+= fmt.format(this.tiempoCompensado) + "s" +"\t \t";
      resultado+= fmt_gasto.format(this.gastoAcumulado)+ "e" +"\t";
      //resultado+= this.haExplotado +"\t";

      return resultado;
   }

    //-----------------------------------------------------------------
    //  Las clases derivadas deberan implementar este metodo
    //-----------------------------------------------------------------
    public abstract void correUnKilometro();
    

   //-----------------------------------------------------------------
   //  Usa el tiempo compensado para comparar
   //-----------------------------------------------------------------

   public int compareTo (Object other)
   {
      int result;

         
      if (this.tiempoCompensado== ((Vehiculo)other).tiempoCompensado)
      {
	  result=0;
      }else if (this.tiempoCompensado< ((Vehiculo)other).tiempoCompensado)
		 
      {
	  result=-1;
      }else{
	  result=1;
      }
		 
      return result;
   }

   //-----------------------------------------------------------------
   //  Devuelve un booleano que nos dice si exploto o no 
   //-----------------------------------------------------------------

    public boolean SigueEnCarrera()
    {
	boolean res= !(this.haExplotado);
	return res;
    }
}
