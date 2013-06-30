//Test para probar que funciona correctamente la nueva clase EjecutaMus

public class testEjecutaMus
{
    public static void main (String[] args)
   {
       EjecutaMus partidaMus= new EjecutaMus();
       String res;

       res= partidaMus.getResultado();

       System.out.println(res);
   }
}
