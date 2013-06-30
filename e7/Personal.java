//********************************************************************#
//  David Rozas
//
//  Personal.java       
//                      
//
//  Representa el personal de una organización concreta (modificado)
//********************************************************************#

public class Personal
{
   private Miembro[] listaDePersonal;

   //-----------------------------------------------------------------
   //  Construya la lista de personal con sus miembros
   //-----------------------------------------------------------------
   public Personal ()
   {
      listaDePersonal = new Miembro[8];

      listaDePersonal[0] = new Ejecutivo ("Samuel", "Principal, 12",
         "555-0469", "123-45-6789", 2423.07);

      listaDePersonal[1] = new Empleado ("Carla", "Nueva, 4",
         "555-1101", "987-65-4321", 1246.15);
      listaDePersonal[2] = new Empleado ("Woody", "Vieja, 7",
         "555-0000", "010-20-3040", 1169.23);

      listaDePersonal[3] = new PorHoras ("Carla", "Avda. Lejana, 6",
         "555-0690", "958-47-3625", 10.55);

      listaDePersonal[4] = new Voluntario ("Norberto", "Bulevar Sur, 9",
         "555-8374");
      listaDePersonal[5] = new Voluntario ("Clodovaldo", "Carretera Norta, 3",
         "555-7282");
      //Se añaden dos empleados a comisión a la listaDePersonal, uno ganando 6,25 euros por hora y con una comisión del 20%,
      //y el otro ganando 9,75 euros por hora y con una comisión del 15%.
      listaDePersonal[6] = new AComision("Homer Simpson", "Evergreen Terrace, 26", "666-7852", "2574541", 6.25, 0.2);
      listaDePersonal[7] = new AComision("Bender", "Farwsworth Village, 28", "696969", "1001010", 9.75, 0.15);


      ((Ejecutivo)listaDePersonal[0]).otorgarBonus (500.00);

      ((PorHoras)listaDePersonal[3]).añadirHoras (40);

      //El primero de los dos empleados añadidos habrá trabajado 35 horas con un total de ventas de 400 euros,
      // y el segundo 40 horas con unas ventas de 959 euros.
      ((AComision)listaDePersonal[6]).añadirHoras(35);
      ((AComision)listaDePersonal[6]).añadirVentas(400);

      ((AComision)listaDePersonal[7]).añadirHoras(40);
       ((AComision)listaDePersonal[7]).añadirVentas(959);

       
   }

   //-----------------------------------------------------------------
   //  Paga a todos los miembros del personal
   //-----------------------------------------------------------------
   public void díaDePaga ()
   {
      double cantidad;

      for (int i=0; i < listaDePersonal.length; i++)
      {
         System.out.println (listaDePersonal[i]);

         cantidad = listaDePersonal[i].pagar();  // polimorfismo

         if (cantidad == 0.0)
            System.out.println ("¡Gracias!");
         else
            System.out.println ("Pagos: " + cantidad);

         System.out.println ("-----------------------------------");
      }
   }


   //-----------------------------------------------------------------
   //  Ordena segun el método de inserción
   //-----------------------------------------------------------------

    public void ordenar()
    {
	
	Sorts.insertionSort(listaDePersonal);
    }

   //-----------------------------------------------------------------
   //  Devuelve en un string la info del personal
   //-----------------------------------------------------------------

    public String toString()
    {
	int i=0;
	String cadenaDevuelta="";
	
	for (i=0; i< listaDePersonal.length; i++)
	{
	    cadenaDevuelta+= listaDePersonal[i].toString();
	}
	
	return cadenaDevuelta;
    }

}
	
