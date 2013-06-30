//Programilla para comprobar que funcionan las modificaciones en el cajero

public class testCajeroJuegos
{
    

   public static void main (String[] args)
   {
       int cantPrueba=50;
       int valorJuegoPrueba= 10;
       String res="";

       //En el servidor haremos...

       //Creamos nuestro cajero al principio
       Cajero mi_cajero= new Cajero();
       
       //Inicializacion con 20 billetes de cada.Usaremos de 50,20,10 y 5
       mi_cajero.anadirBillete(50,20);
       mi_cajero.anadirBillete(20,0);
       mi_cajero.anadirBillete(10,0);
       mi_cajero.anadirBillete(5,0);
       

       //Suponemos que nos han metido un billete de valor cantPrueba
       
       System.out.println("###Traza cajero antes de meter el billete:" + mi_cajero.toString());
       
       //Ingresamos el billete de 50 euros-> pos 0
       mi_cajero.ingresarUnBillete(0);
       System.out.println("###Traza cajero despues de meter el billete:" + mi_cajero.toString());

       //Le damos el cambio
       res=mi_cajero.sacarDinero(cantPrueba-valorJuegoPrueba);


       //TENDREMOS QUE CONTROLAR EN EL SERVER EL CASO DE NO HABER SIDO POSIBLE DAR CAMBIO
       //EN CUYO CASO, TENDREMOS QUE VOLVER A RESTAR EL BILLETE INGRESADO!!!

       if (res=="")
       {
	   System.out.println("No se pudo satisfacer la cantidad");
	   mi_cajero.restarUnBillete(0);
       }else{
	   System.out.println("###Traza-> cambio devuelto al cliente :" + res);
       }

       System.out.println("###Traza cajero despues de dar el cambio: " + mi_cajero.toString());
   }
}
