package Triqui;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Triqui {
	// Estructura de memoria con las casillas del juego
	Tablero tabla = new Tablero();
	// Indicador de turno de jugador
	boolean turnoHumano;

	// Implementación del algoritmo minimax
	final MiniMax minimax;

	public static void main(String[] args) {
		Triqui juego = new Triqui();
		System.out.println("Init Game");

		juego.turno(juego);
	}

	public Triqui() {
		turnoHumano = true;
		minimax = new MiniMax(3, 3);
	}

	/**
	 * Gestor de turnos
	 * 
	 * @param juego
	 * @return
	 */
	public void turno(Triqui juego) {
		// Movimiento decidido por el ordenador
		Movimiento mov;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (juego.tabla.gano(MiniMax.CASILLA_PERSONA)) {
					System.out.println("//******GANADOR******//");
					turnoHumano = false;
				} else if (juego.tabla.gano(MiniMax.CASILLA_ORDENADOR)) {
					System.out.println("//******PERDEDOR******//");
					turnoHumano = false;
				} else if (tabla.contarCasillasVacias() == MiniMax.CASILLA_VACIA) {
					System.out.println("//******EMPATE******//");
					turnoHumano = false;
				}
				if (turnoHumano) {
					turnoHumano = false;
					// turno humano
					juego.turnoHuman(juego.tabla);

					// Lanza el algoritmo minimax y recoge el movimiento elegido
					mov = minimax.minimax(tabla.getMatriz());
					if (mov.getPosX() != -1 && mov.getPosY() != -1) {
						// Dibujamos el movimiento del ordenador
						tabla.set(mov.getPosX(), mov.getPosY(), MiniMax.CASILLA_ORDENADOR);
						System.out.println("IA :");
						System.out.println(tabla.toString());
						turnoHumano = true;
					}

				}

			}
		}

	}

	/**
	 * Genera el turno del humano
	 * 
	 * @param tabla
	 */
	@SuppressWarnings("resource")
	public void turnoHuman(Tablero tabla) {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Escoja fila de 0 a 2 ");
		int linea = entrada.nextInt();
		System.out.println("Escoja columna de 0 a 2");
		int colomna = entrada.nextInt();
		if (linea > 2 | colomna > 2) {
			System.out.println("Fuera de rango intentelo de nuevo");
			turnoHuman(tabla);
		} else if (tabla.getCasilla(linea, colomna) == MiniMax.CASILLA_PERSONA
				| tabla.getCasilla(linea, colomna) == MiniMax.CASILLA_ORDENADOR) {
			System.out.println("Casilla Marcada Intenteleo de nuevo");
			turnoHuman(tabla);
		} else {
			System.out.println("Human :");
			tabla.set(linea, colomna, MiniMax.CASILLA_PERSONA);
			System.out.println(tabla.toString());
		}
	}

	/**
	 * Generar sucesores de un estado
	 * 
	 * @param estado
	 * @param turnoH
	 * @return
	 */
	public ArrayList<Tablero> sucesores(Tablero estado, boolean turnoH) {
		ArrayList<Tablero> result = new ArrayList<Tablero>();
		int c;
		if (turnoH) {
			c = MiniMax.CASILLA_PERSONA;
		} else {
			c = MiniMax.CASILLA_ORDENADOR;
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (estado.getCasilla(i, j) != MiniMax.CASILLA_PERSONA
						&& estado.getCasilla(i, j) != MiniMax.CASILLA_ORDENADOR) {
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

}
