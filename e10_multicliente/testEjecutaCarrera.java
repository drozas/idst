//Test para probar que funciona correctamente la nueva clase EjecutaCarrera

public class testEjecutaCarrera
{

   public static void main (String[] args) throws menorQueCeroException
   {
       EjecutaCarrera carreraPrueba= new EjecutaCarrera("Carrera de prueba", 5,0,3,1,35);
       String res;

       res= carreraPrueba.getResultado();

       System.out.println(res);
   }
}
