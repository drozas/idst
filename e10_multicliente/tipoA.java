//********************************************************************#
//  Autor: David Rozas
//  tipoA.java      
//
//  Representa la clase de vehículos con motor de gasolina
//********************************************************************#
    import java.util.Random;


public class tipoA extends Amotor
{
    //Valores constantes de tipoA
    private static final char claseA='A';
    private static final double precio_gasA=3;
    private static final double penalizacionA=3;

    private static Random generador= new Random();
    //-----------------------------------------------------------------
    //  Construye un vehiculo de tipo A
    //-----------------------------------------------------------------
    public tipoA (int identificador, double velocidad, double consumo, double riesgoExplosion) 
	
    {
	//llamamos al constructor de la clase superior, pasándole valores "específicos", como
	//claseA,precio_gasA o penalizacionA
	super (identificador,claseA,velocidad,consumo,precio_gasA,riesgoExplosion,penalizacionA);
    }
    
    //-----------------------------------------------------------------
    //  Devuelve un string con la información del vehiculo tipoA
    //-----------------------------------------------------------------
    public String toString()
    {
	
	String resultado = super.toString();
	resultado += "\n";
	return resultado;
    }
    
    
    //-----------------------------------------------------------------
    //  Método necesario para cumplir la especificacion capazDeCompetir
    //-----------------------------------------------------------------  
    public void correUnKilometro () 
    {
	double explota;
	explota= generador.nextDouble(); //valor aletaorio, para simular la probabilidad de explosión

	//Aumentamos el tiempo que tardó  en recorrer este km
	this.tiempoReal= this.tiempoReal + (1/this.velocidad);
	//Aumentamos la penalizacion acumulada con su propia penalizacion
	this.penalizacionAcumulada= this.penalizacionAcumulada + this.penalizacion;
	//El tiempo compensado, es el tiempo acumulado, mas la penalizacion total
	this.tiempoCompensado= this.tiempoReal + this.penalizacionAcumulada;
	//El gasto acumulado, sera el gasto anterior, más consumo por precio de gasolina 
	this.gastoAcumulado= this.gastoAcumulado + (this.consumo * this.precioGasolina);
      
	//y miramos si explota
	if (this.riesgoExplosion>explota)
	{
	    this.haExplotado=true;
	}
    }
	

}
