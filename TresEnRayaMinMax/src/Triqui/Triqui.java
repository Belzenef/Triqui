package Triqui;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Triqui {
	// Estructura de memoria con las casillas del juego
	Tablero tabla = new Tablero();
	// Indicador de turno de jugador
	boolean turnoHumano;


	public static void main(String[] args) {
		Triqui juego = new Triqui();
		System.out.println("Init Game");
		System.out.println(juego.tabla.toString());

		juego.turno(juego);
	}

	
	public Triqui() {
		turnoHumano = true;
	}

	/**
	 * Gestor de turnos
	 * @param juego
	 * @return
	 */
	public boolean turno(Triqui juego) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (turnoHumano = true) {
					turnoHumano = false;
					//turno humano
					juego.turnoHuman(juego.tabla);
					System.out.println(juego.tabla.toString());
					// IA plays
					juego.turnoIA();
					System.out.println("IA :");
					System.out.println(juego.tabla.toString());
				}
				if (tabla.gano("X")) {
					System.out.println("//******GANADOR******//");
					return true;
				} else if (tabla.gano("O")) {
					System.out.println("//******PERDEDOR******//");
					return true;
				} else if (tabla.contarCasillasVacias() == 0) {
					System.out.println("//******TABLERO LLENO******//");
					return true;
				}

			}
		}
		return false; 

	}

	/**
	 *  Genera el turno del humano
	 * @param tabla
	 */
	@SuppressWarnings("resource")
	public void turnoHuman(Tablero tabla) {
		System.out.println("Human :");
		Scanner entrada = new Scanner(System.in);
		System.out.println("Escoja fila de 0 a 2 ");
		int linea = entrada.nextInt();
		System.out.println("Escoja columna de 0 a 2");
		int colomna = entrada.nextInt();
		if (linea > 2 | colomna > 2) {
			System.out.println("Fuera de rango intentelo de nuevo");
			turnoHuman(tabla);
		} else if (tabla.getCasilla(linea, colomna) == "X" | tabla.getCasilla(linea, colomna) == "O") {
			System.out.println("Casilla Marcada Intenteleo de nuevo");
			turnoHuman(tabla);
		} else
			System.out.println("Human :");
			tabla.set(linea, colomna, "X");
	}

	/**
	 * Generar sucesores de un estado
	 * @param estado
	 * @param turnoH
	 * @return
	 */
	public ArrayList<Tablero> sucesores(Tablero estado, boolean turnoH) {
		ArrayList<Tablero> result = new ArrayList<Tablero>();
		String c;
		if (turnoH) {
			c = "X";
		} else {
			c = "O";
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (estado.getCasilla(i, j) != "X" && estado.getCasilla(i, j) != "O") {
					Tablero tab = new Tablero(estado.getMatriz());
					tab.set(i, j, c);
					result.add(tab);
				}
			}
		}
		// to avoid IA always picking the first option
		Collections.shuffle(result);
		return result;
	}

	/**
	 * Simulacion del turno de la IA
	 */
	public void turnoIA() {
		turnoHumano = false;
		int max, score, profundidad = 9;
		Tablero mejor, test;
		ArrayList<Tablero> posibilidades = sucesores(tabla, turnoHumano);
		mejor = posibilidades.get(0);
		score = mejor.evaluar(turnoHumano);
		max = score;
		for (int i = 1; i < posibilidades.size(); i++) {
			test = posibilidades.get(i);
			score = min(test, profundidad);
			if (score > max) {
				max = score;
				mejor = test;
			}
		}
		tabla = mejor;
		turnoHumano = true;
	}

	/**
	 * Elegir el mejor sucesor (Min-Max) 
	 * @param tab
	 * @param profundidad
	 * @return
	 */
	public int min(Tablero tab, int profundidad) {
		// generar hijos del estado actual
		ArrayList<Tablero> posibilidades = sucesores(tab, true);

		// verificar si el estado actual es una hoja del arbol
		if (tab.gano("X") || tab.gano("O") || posibilidades.size() == 0) {
			return tab.evaluar(true);
		}

		int minScore = 100, score;
		Tablero mejor, test;
		mejor = posibilidades.get(0);
		score = mejor.evaluar(true);
		minScore = score;
		for (int i = 1; i < posibilidades.size(); i++) {
			test = posibilidades.get(i);
			score = max(test, profundidad - 1);
			if (score < minScore) {
				minScore = score;
				mejor = test;
			}
		}
		return minScore;
	}

	/**
	 * Funcion MAX
	 * @param tab
	 * @param profundidad
	 * @return
	 */
	public int max(Tablero tab, int profundidad) {
		// generar hijos del estado actual
		ArrayList<Tablero> posibilidades = sucesores(tab, false);

		// verificar si el estado actual es una hoja del arbol
		if (tab.gano("X") || tab.gano("O") || posibilidades.size() == 0) {
			return tab.evaluar(false);
		}

		int maxScore = -100, score;
		Tablero mejor, test;
		mejor = posibilidades.get(0);
		score = mejor.evaluar(false);
		maxScore = score;
		for (int i = 1; i < posibilidades.size(); i++) {
			test = posibilidades.get(i);
			score = min(test, profundidad - 1);
			if (score > maxScore) {
				maxScore = score;
				mejor = test;
			}
		}
		return maxScore;
	}
}
