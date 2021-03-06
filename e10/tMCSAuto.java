//********************************************************************#
//  Autor: David Rozas
//  tMCSAuto.java      
//
//  Extiende la clase Mens Cliente->Servidor, con los atributos necesarios
//  para jugar a los Autos Locos.
// 
//********************************************************************#

public class tMCSAuto extends tMensajeCS
{
    protected String nombreCarrera;
    protected int nCochesA;
    protected int nCochesB;
    protected int nCochesC;
    protected int nCochesD;
    protected int km;

    //-----------------------------------------------------------------
    //  Construye un mensaje
    //-----------------------------------------------------------------
    public tMCSAuto (int tPrograma, String nombreCarrera, int nCochesA,
		     int nCochesB, int nCochesC, int nCochesD, int km)
    {
	super(tPrograma);
	this.nombreCarrera=nombreCarrera;
	this.nCochesA= nCochesA;
	this.nCochesB= nCochesB;
	this.nCochesC= nCochesC;
	this.nCochesD= nCochesD;
	this.km= km;
    }

    //-----------------------------------------------------------------
    //  Devuelve el valor del campo nombreCarrera
    //-----------------------------------------------------------------
    public String getNombreCarrera()
    {
	String res= this.nombreCarrera;

	return res;
    }

    //-----------------------------------------------------------------
    //  Devuelve el valor del campo nCochesA
    //-----------------------------------------------------------------
    public int getCochesA()
    {
	int res= this.nCochesA;
	
	return res;
    }

    //-----------------------------------------------------------------
    //  Devuelve el valor del campo nCochesB
    //-----------------------------------------------------------------
    public int getCochesB()
    {
	int res= this.nCochesB;
	
	return res;
    }

    //-----------------------------------------------------------------
    //  Devuelve el valor del campo nCochesC
    //-----------------------------------------------------------------
    public int getCochesC()
    {
	int res= this.nCochesC;
	
	return res;
    }

    //-----------------------------------------------------------------
    //  Devuelve el valor del campo nCochesD
    //-----------------------------------------------------------------
    public int getCochesD()
    {
	int res= this.nCochesD;
	
	return res;
    }


    //-----------------------------------------------------------------
    //  Devuelve el valor del atributo km
    //-----------------------------------------------------------------
    public int getKm()
    {
	int res= this.km;
	
	return res;
    }


    //-----------------------------------------------------------------
    //  Devuelve un String con la info del mensaje
    //-----------------------------------------------------------------
    public String toString()
    {
	String res= super.toString();
	res+="\t NombreCarrera" + this.nombreCarrera;
	res+="\t nCochesA" + this.nCochesA;
	res+="\t nCochesB" + this.nCochesB;
	res+="\t nCochesC" + this.nCochesC;
	res+="\t nCochesD" + this.nCochesD;
	res+="\t km " + this.km + "\n";	
	return res;
    }
    
}
