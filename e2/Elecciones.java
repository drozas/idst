
//********************************************************************
//  Práctica 2 de IDST
//  David Rozas
//
//  Applet de java, en el que se dibuja un diagrama de tarta con los resultados
//  de las elecciones del 2004
//********************************************************************

import java.applet.Applet;
import java.awt.*;
import java.text.DecimalFormat;

public class Elecciones extends Applet
{
   public void paint (Graphics page)
   {
	final float PSOE = 10909687;
	final float PP = 9630512;
	final float CIU = 829046;
	final float ERC = 649999;
	final float EAJ_PNV = 417154;
	final float IU = 1269532;
	final float OTROS= 1880051;
	final float VOTOS_TOTALES= PSOE + PP + CIU + ERC + EAJ_PNV + IU + OTROS;
	final int X = 20;
	final int Y = 20;
	final int ANCHO =300;
	final int ALTO =200;
	int angDesde;
	int angHasta;
	float ppsoe,ppp,pciu,perc,ppnv,piu,potros;
	int x3d,y3d;
	int i;
	
	setBackground(Color.white);


	//pintado, para el efecto 3d
	x3d=X;
	y3d=Y;
	for (i=0; i<5; i++)
	    {   x3d=x3d +1;
		y3d=y3d +1;
		angDesde=0;
		angHasta= (int) ((PSOE*360)/VOTOS_TOTALES);
		page.setColor (Color.red.darker());
		page.fillArc (x3d,y3d,ANCHO,ALTO,angDesde,angHasta);
		
		angDesde=angHasta + angDesde;
		angHasta=(int)(((PP*360)/VOTOS_TOTALES));
		page.setColor(Color.blue.darker());
		page.fillArc(x3d,y3d,ANCHO,ALTO,angDesde,angHasta);
	
		angDesde=angHasta + angDesde;
		angHasta=(int)(((CIU*360)/VOTOS_TOTALES)) ;
		page.setColor(Color.yellow.darker());
		page.fillArc(x3d,y3d,ANCHO,ALTO,angDesde,angHasta);
		
		angDesde=angHasta + angDesde;
		angHasta=(int)(((ERC*360)/VOTOS_TOTALES));
		page.setColor(Color.orange.darker());
		page.fillArc(x3d,y3d,ANCHO,ALTO,angDesde,angHasta);
		
		angDesde=angHasta + angDesde;
		angHasta=(int)(((EAJ_PNV*360)/VOTOS_TOTALES));
		page.setColor(Color.pink.darker());
		page.fillArc(x3d,y3d,ANCHO,ALTO,angDesde,angHasta);
		
		angDesde=angHasta + angDesde;
		angHasta=(int)(((IU*360)/VOTOS_TOTALES)) ;
		page.setColor(Color.green.darker());
		page.fillArc(x3d,y3d,ANCHO,ALTO,angDesde,angHasta);
		
		angDesde=angHasta + angDesde;
		angHasta=(int)(((OTROS*360)/VOTOS_TOTALES));
		page.setColor(Color.black.darker());
		page.fillArc(x3d,y3d,ANCHO,ALTO,angDesde,angHasta);
	    }
	
	//Pintado del diagrama de sectores
	angDesde=0;
	angHasta= (int) ((PSOE*360)/VOTOS_TOTALES) ;
	page.setColor (Color.red);
	page.fillArc (X,Y,ANCHO,ALTO,angDesde,angHasta);
	
	angDesde=angHasta +angDesde;
	angHasta=(int)(((PP*360)/VOTOS_TOTALES));
	page.setColor(Color.blue);
	page.fillArc(X,Y,ANCHO,ALTO,angDesde,angHasta);
	
	angDesde=angHasta  + angDesde;
	angHasta=(int)(((CIU*360)/VOTOS_TOTALES)) ;
	page.setColor(Color.yellow);
	page.fillArc(X,Y,ANCHO,ALTO,angDesde,angHasta);
	
	angDesde=angHasta + angDesde;
	angHasta=(int)(((ERC*360)/VOTOS_TOTALES));
	page.setColor(Color.orange);
	page.fillArc(X,Y,ANCHO,ALTO,angDesde,angHasta);
	
	angDesde=angHasta + angDesde;
	angHasta=(int)(((EAJ_PNV*360)/VOTOS_TOTALES));
	page.setColor(Color.pink);
	page.fillArc(X,Y,ANCHO,ALTO,angDesde,angHasta);
	
	angDesde=angHasta +angDesde;
	angHasta=(int)(((IU*360)/VOTOS_TOTALES)) ;
	page.setColor(Color.green);
	page.fillArc(X,Y,ANCHO,ALTO,angDesde,angHasta);
	
	angDesde=angHasta + angDesde;
	angHasta=(int)(((OTROS*360)/VOTOS_TOTALES));
	page.setColor(Color.black);
	page.fillArc(X,Y,ANCHO,ALTO,angDesde,angHasta);
	
	//Etiquetas de las siglas políticas
	page.setColor(Color.white);
	page.drawString ("PSOE", 130, 70);
	page.drawString ("PP", 85, 170);
	page.drawString ("CIU", 200, 200);
	page.drawString ("ERC", 225, 200);
	page.drawString ("PNV", 244, 190);
	page.drawString("IU", 260,170);
	page.drawString("Otros",265,140);
	
	/*Cálculo de porcentajes para las etiquetas de porcentajes*/
	ppsoe= ((PSOE*100)/VOTOS_TOTALES);
	ppp= ((PP*100)/VOTOS_TOTALES);
	pciu= ((CIU*100)/VOTOS_TOTALES);
	perc= ((ERC*100)/VOTOS_TOTALES);
	ppnv= ((EAJ_PNV*100)/VOTOS_TOTALES);
	piu= ((IU*100)/VOTOS_TOTALES);
	potros= ((OTROS*100)/VOTOS_TOTALES);

	//Etiquetas de los porcentajes
	//Formato de la salida con 2 decimales
	DecimalFormat fmt = new DecimalFormat ("0.##");
	page.setColor(Color.black);
	page.drawString(fmt.format(ppsoe) +"%" ,130,15);
	page.drawString(fmt.format(ppp) + "%",85,230);
	page.drawString(fmt.format(pciu) + "%",230,230);
	page.drawString(fmt.format(perc) + "%",255,220);
	page.drawString(fmt.format(ppnv) + "%",278,205);
	page.drawString(fmt.format(piu) + "%",297,190);
	page.drawString(fmt.format(potros) + "%",325,155);

    
   }
}
