//********************************************************************#
//  Autor: David Rozas
//  tipoC.java      
//
//  Representa la clase de veh�culos con motor de Fluzo
//********************************************************************#
    import java.util.Random;


public class tipoC extends Amotor
{
    //Valores constantes de tipo C
    private static final char claseC='C';
    private static final double precio_gasC=0.000001;
    private static final double penalizacionC=1;

    private static Random generador= new Random();
    //-----------------------------------------------------------------
    //  Construye un vehiculo de tipo C
    //-----------------------------------------------------------------
    public tipoC (int identificador,double velocidad, double consumo, double riesgoExplosion) 
	
    {
	//llamamos al constructor de la clase superior, pas�ndole valores "espec�ficos", como
	//claseC,precio_gasC o penalizacionC
	super (identificador,claseC,velocidad,consumo,precio_gasC,riesgoExplosion,penalizacionC);
    }
    
    //-----------------------------------------------------------------
    //  Devuelve un string con la informaci�n del vehiculo tipoC
    //-----------------------------------------------------------------
    public String toString()
    {
	
	String resultado = super.toString();
	resultado += "\n";
	return resultado;
    }
    
    
    //-----------------------------------------------------------------
    //  M�todo necesario para cumplir la especificacion capazDeCompetir
    //----------------------------------------------------------------- 
    public void correUnKilometro () 
    {
	double explota;
	explota= generador.nextDouble(); //valor aleatorio para simular la prob. de explosi�n

	//Aumentamos el tiempo que ha tardado en recorrer este km
	this.tiempoReal= this.tiempoReal + (1/this.velocidad);
	//Aumentamos la penalizacion acumulada con su propia penalizacion
	this.penalizacionAcumulada= this.penalizacionAcumulada + this.penalizacion;
	//El tiempo compensado, es el tiempo acumulado, mas la penalizacion total 
	this.tiempoCompensado= this.tiempoReal + this.penalizacionAcumulada;
	//El gasto acumulado, sera el gasto anterior, m�s consumo por precio
	this.gastoAcumulado= this.gastoAcumulado + (this.consumo * this.precioGasolina);
	//y miramos si explota
	if (this.riesgoExplosion>explota)
	{
	    this.haExplotado=true;
	}
    }

}
