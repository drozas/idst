//********************************************************************#
//  Autor: David Rozas
//  tipoD.java      
//
//  Representa la clase de veh�culos sin motor
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
	//llamamos al constructor de veh�culo, con valores "espec�ficos" como claseD o penalizacionD
	super(identificador,claseD,velocidad,penalizacionD);
	
    }
    
    //-----------------------------------------------------------------
    //  Devuelve un string con la informaci�n del vehiculo tipoD
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
	//Aumentamos el tiempo que ha tardado en recorrer este km
	this.tiempoReal= this.tiempoReal + (1/this.velocidad);
	//Aumentamos la penalizacion (aunque en este caso es 0 siempre)
	this.penalizacionAcumulada= this.penalizacionAcumulada + this.penalizacion; 
	//El tiempo compensado es el acumulado m�s la penalizacion acumulada (que en este subtipo ser� 0)
	this.tiempoCompensado= this.tiempoReal + this.penalizacionAcumulada;
	//El gasto acumulado tampoco var�a en este subtipo
	this.gastoAcumulado= this.gastoAcumulado;
	//Los de tipo D nunca explotan, su booleano haExplotado ser� siempre false
    }

}
