package Triqui;

import java.util.ArrayList;
import java.util.Scanner;

public class Triqui {
	// Estructura de memoria con las casillas del juego
	Tablero tabla = new Tablero();
	// Indicador de turno de jugador
	boolean turnoHumano;

	// Iniciar el juego
	public static void main(String[] args) {
		Triqui juego = new Triqui();
		System.out.println("ok");
		System.out.println(juego.tabla.toString());
		
		// Humano starts
		juego.tabla.set(1,1,"X");
		System.out.println(juego.tabla.toString());
		juego.turnoHumano=false;
		
		// IA plays
		ArrayList<Tablero> L = juego.sucesores(juego.tabla);
		for (int i = 0; i < L.size() ; i++) {
			System.out.println(L.get(i).toString());
		}
		juego.turnoIA();
		System.out.println("ok");
		System.out.println(juego.tabla.toString());
		
		// Humano plays
		juego.tabla.set(0,2,"X");
		juego.turnoHumano=false;
		System.out.println(juego.tabla.toString());
		
		// IA plays
		juego.turnoIA();
		System.out.println("ok");
		System.out.println(juego.tabla.toString());
	}
	
	// Constructor
	public Triqui() {
		turnoHumano=true;
	}
	
	// Entrada del usuario
	public void rellenar() {
		Scanner entrada = new Scanner(System.in);
		System.out.println("linea?");
		int linea = entrada.nextInt();
		System.out.println("colomna?");
		int colomna = entrada.nextInt();
		tabla.set(linea, colomna,"X");
		entrada.close();
	}
	
	// Generar sucesores de un estado
	public ArrayList<Tablero> sucesores(Tablero estado) {
		ArrayList<Tablero> result = new ArrayList<Tablero>();
		String c;
		if(turnoHumano) {
			c="X";
		}else {c="O";}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (estado.getCasilla(i,j) != "X" && estado.getCasilla(i,j) != "O" ) {
					Tablero tab = new Tablero(estado.getMatriz());
					tab.set(i, j, c);
					result.add(tab);
				}
			}
		}
		return result;
	}
	
   	// Simulacion del turno de la IA
   	public void turnoIA() {
   		turnoHumano=false;
   		int max, score, profundidad=9;
   		Tablero mejor, test;
   		ArrayList<Tablero> posibilidades = sucesores(tabla);
   		mejor = posibilidades.get(0);
   		score = mejor.evaluar(turnoHumano);
   		max = score;
   		for (int i = 1; i < posibilidades.size(); i++) {	
			test = posibilidades.get(i);
			score = min(test,profundidad);
			if(score>max) {
				max = score;
				mejor = test;
			}
		}
		tabla=mejor;
		turnoHumano=true;
		System.out.println(max);
   	}
   	
	// Elegir el mejor sucesor (Min-Max)
   	public int min(Tablero tab, int profundidad) {
   		// generar hijos del estado actual
   		ArrayList<Tablero> posibilidades = sucesores(tab);
   		
   		// verificar si el estado actual es una hoja del arbol
   		if(tab.gano("X") || tab.gano("O") || posibilidades.size()==0 ){
   			return tab.evaluar(true);
      	}
   		
   		int minScore = 100, score;
   		Tablero mejor, test;
   		mejor = posibilidades.get(0);
   		score = mejor.evaluar(true);
   		minScore = score;
   		for (int i = 1; i < posibilidades.size(); i++) {
   			test = posibilidades.get(i);
   			score = max(test,profundidad-1);
			if(score<minScore) {
				minScore = score;
				mejor = test;
			}
   		}
   		return minScore;
   	}
   	
   	public int max(Tablero tab, int profundidad) {
   		// generar hijos del estado actual
   		ArrayList<Tablero> posibilidades = sucesores(tab);
   		
   		// verificar si el estado actual es una hoja del arbol
   		if(tab.gano("X") || tab.gano("O") || posibilidades.size()==0 ){
   			return tab.evaluar(false);
      	}
   		
   		int maxScore = -100, score;
   		Tablero mejor, test;
   		mejor = posibilidades.get(0);
   		score = mejor.evaluar(true);
   		maxScore = score;
   		for (int i = 1; i < posibilidades.size(); i++) {
   			test = posibilidades.get(i);
   			score = min(test,profundidad-1);
			if(score>maxScore) {
				maxScore = score;
				mejor = test;
			}
   		}
   		return maxScore;
   	}
}
