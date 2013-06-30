//********************************************************************#
//  Autor: David Rozas
//  tipoB.java      
//
//  Representa la clase de vehículos con motor de hidrógeno
//********************************************************************#
    import java.util.Random;


public class tipoB extends Amotor
{
    //Valores constantes de tipoB
    private static final char claseB='B';
    private static final double precio_gasB=0.005;
    private static final double penalizacionB=1.5;

    private static Random generador= new Random();
    //-----------------------------------------------------------------
    //  Construye un vehiculo de tipo B
    //-----------------------------------------------------------------
    public tipoB (int identificador, double velocidad, double consumo,double riesgoExplosion) 
	
    {
	//llamamos al constructor de la clase superior, pasándole valores "específicos", como
	//claseB,precio_gasB o penalizacionB
	super (identificador,claseB,velocidad,consumo,precio_gasB,riesgoExplosion,penalizacionB);
    }
    
    //-----------------------------------------------------------------
    //  Devuelve un string con la información del vehiculo tipoB
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
	explota=  generador.nextDouble(); //valor aleatorio para simular la prob. de explosión

	//Aumentamos el tiempo que ha tardado en recorrer este km
	this.tiempoReal= this.tiempoReal + (1/this.velocidad);
	//Aumentamos la penalizacion acumulada con su propia penalizacion
	this.penalizacionAcumulada= this.penalizacionAcumulada + this.penalizacion;
	//El tiempo compensado, es el tiempo acumulado, mas la penalizacion total
	this.tiempoCompensado= this.tiempoReal + this.penalizacionAcumulada;
	//El gasto acumulado, sera el gasto anterior, más consumo por precio
	this.gastoAcumulado= this.gastoAcumulado + (this.consumo * this.precioGasolina);
	//y miramos si explota
	if ( this.riesgoExplosion>explota)
	{
	    this.haExplotado=true;
	}
	
    }

}
