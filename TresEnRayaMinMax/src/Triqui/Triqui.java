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
		juego.tabla.set(1,1,-2);
		ArrayList<Tablero> L = juego.sucesores();
		for (int i = 0; i < L.size() ; i++) {
			System.out.println(L.get(i).toString());
		}
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
		tabla.set(linea, colomna, -1);
		entrada.close();
	}
	
	// Generar sucesores del estado actual
	public ArrayList<Tablero> sucesores() {
		ArrayList<Tablero> result = new ArrayList<Tablero>();
		int c;
		if(turnoHumano) {
			c=-1;
		}else {c=-2;}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (tabla.getCasilla(i,j) != -1 && tabla.getCasilla(i,j) != -2 ) {
					Tablero tab = new Tablero(tabla.getMatriz());
					tab.set(i, j, c);
					result.add(tab);
				}
			}
		}
		return result;
	}
	
	// Elegir el mejor sucesor (Min-Max)
	public void elegir() {
		int c;
		if(turnoHumano) {
			c=-1;
		}else {c=-2;}
		ArrayList<Tablero> posibilidades = this.sucesores();
		int mejorIndex = 0;
		int mejorScore = posibilidades.get(0).evaluar(turnoHumano);
		
		for (int i = 1; i < posibilidades.size(); i++) {	
		}
	}
}
