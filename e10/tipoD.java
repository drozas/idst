//********************************************************************#
//  Autor: David Rozas
//  tipoD.java      
//
//  Representa la clase de vehículos sin motor
//********************************************************************#

public class tipoD extends Vehiculo
{
    //Valores constantes de tipoD
    private static final char claseD='D';
    private static final double penalizacionD=0.0;

    //-----------------------------------------------------------------
    //  Construye un vehiculo de tipo D
    //-----------------------------------------------------------------
    public tipoD (int identificador, double velocidad)
    {
	//llamamos al constructor de vehículo, con valores "específicos" como claseD o penalizacionD
	super(identificador,claseD,velocidad,penalizacionD);
	
    }
    
    //-----------------------------------------------------------------
    //  Devuelve un string con la información del vehiculo tipoD
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
	//Aumentamos el tiempo que ha tardado en recorrer este km
	this.tiempoReal= this.tiempoReal + (1/this.velocidad);
	//Aumentamos la penalizacion (aunque en este caso es 0 siempre)
	this.penalizacionAcumulada= this.penalizacionAcumulada + this.penalizacion; 
	//El tiempo compensado es el acumulado más la penalizacion acumulada (que en este subtipo será 0)
	this.tiempoCompensado= this.tiempoReal + this.penalizacionAcumulada;
	//El gasto acumulado tampoco varía en este subtipo
	this.gastoAcumulado= this.gastoAcumulado;
	//Los de tipo D nunca explotan, su booleano haExplotado será siempre false
    }

}
