//********************************************************************
//  Mus.java
//  David Rozas
//
//  Reparte aleatoriamente 4 cartas a cada 4 jugadores, y muestra quien
//  gana el "juego"
//********************************************************************

import java.lang.Math;

public class Mus
{
   public static void main (String[] args)
    {
       int juego1,juego2,juego3,juego4=0;
       int posj1,posj2,posj3,posj4,posmin=0;       

       //Creamos los cuatro jugadores
       Jugador jug1= new Jugador();
       Jugador jug2= new Jugador();
       Jugador jug3= new Jugador();
       Jugador jug4= new Jugador();
       
       //Mostramos las cartas de todos los jugadores
       System.out.println ("Cartas:");
       System.out.println ("\t Jugador 1 : " + jug1);
       System.out.println ("\t Jugador 2 : " + jug2);
       System.out.println ("\t Jugador 3 : " + jug3);
       System.out.println ("\t Jugador 4 : " + jug4);
       
       //Calculamos sus juegos
       juego1= jug1.GetJuego();
       juego2= jug2.GetJuego();
       juego3= jug3.GetJuego();
       juego4= jug4.GetJuego();
       
       //Mostramos sus juegos
       System.out.println("Juego:");
       if (juego1>=31){
       	  System.out.println("\t Jugador 1 : " + juego1);
       }else{
          System.out.println("\t Jugador 1 : No tiene");
       }
       
       if (juego2>=31){
       	  System.out.println("\t Jugador 2 : " + juego2);
       }else{
          System.out.println("\t Jugador 2 : No tiene");
       }
       
       if (juego3>=31){
       	  System.out.println("\t Jugador 3 : " + juego3);
       }else{
          System.out.println("\t Jugador 3 : No tiene");
       }
       
       if (juego4>=31){
       	  System.out.println("\t Jugador 4 : " + juego4);
       }else{
          System.out.println("\t Jugador 4 : No tiene");
       }

       //Calculamos las posiciones de sus juegos
       posj1= jug1.GetPosicionJuego(juego1);
       posj2= jug2.GetPosicionJuego(juego2);
       posj3= jug3.GetPosicionJuego(juego3);
       posj4= jug4.GetPosicionJuego(juego4);

       //Sacamos cual es el minimo de los juegos
       posmin= Math.min(Math.min(posj1,posj2),Math.min(posj3,posj4));

       //Y comparando obtenemos el ganador, y controlamos que tenga juego
       System.out.println("Ganador:");
       if (posj1==posmin && posj1!=8){
	   System.out.println("\t Gana el jugador 1");
       }
       if (posj2==posmin && posj2 !=8){
	   System.out.println("\t Gana el jugador 2");
       }
       if (posj3==posmin && posj3 !=8){
	   System.out.println("\t Gana el jugador 3");
       }
       if (posj4==posmin && posj4 !=8){
	   System.out.println("\t Gana el jugador 4");
       }
    }
 }