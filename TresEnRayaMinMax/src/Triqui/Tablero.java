package Triqui;

public class Tablero {

	// Respresentacion de estados
	int matrizTablero[][] = new int[3][3];
	
	// Tests
	public static void main(String[] args) {
		Tablero Tab = new Tablero();
		System.out.println("ok");
		System.out.println(Tab.toString());
		Tab.set(0,2,-1);
		//Tab.set(0,2,-2);
		System.out.println(Tab.toString());
		int M = Tab.evaluar(true);
		System.out.println(M);
		int M2 = Tab.evaluar(false);
		System.out.println(M2);
	}

	// Constructores
	public Tablero() {}
	
	public Tablero(int[][] M ) {
		// Inicializamos variables de la partida
		matrizTablero = M;
	}
	
	
	// Funcion heuristica 
	public int evaluar(boolean turnoHumano) {
		int result = 0;
		int good;
		int bad;
		if(turnoHumano) {
			good=-1;
			bad=-2;
		}else {
			good=-2;
			bad=-1;
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (matrizTablero[i][j] == good ) {
					// numero de e en linea i
					int linea=0;
					for (int p = 0; p < 3; p++) {
						if(matrizTablero[i][p]==good) {
							linea++;
						}
						if(matrizTablero[i][p]==bad) {
							linea=0;
							p=4;
						}
					}
					result+=linea;
					
					// numero de e en columna j
					int col=0;
					for (int p = 0; p < 3; p++) {
						if(matrizTablero[p][j]==good) {
							col++;
						}
						if(matrizTablero[p][i]==bad) {
							col=0;
							p=4;
						}
					}
					result+=col;
					
					// numero de e en diagonal
					int diag=0;
					if(i==j) {
						for (int p = 0; p < 3; p++) {
							if(matrizTablero[p][p]==good) {
								diag++;
							}
							if(matrizTablero[p][p]==bad) {
								diag=0;
								p=4;
							}
						}
						result+=diag;
					}
				}
			}
		}
		return result;
	}
	
	// getters y setters
	public void set(int i, int j, int x) {
		matrizTablero[i][j] = x;
	}
	
	public int getCasilla(int i, int j) {
		return matrizTablero[i][j];
	}
	
	public int[][] getMatriz() {
		int matriz[][] = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				matriz[i][j]=this.matrizTablero[i][j];
			}
		}
		return matriz;
	}
	
	// vizualisacion
	public String toString() {
		String result = "";
		for (int i = 0; i < 3; i++) {
			result += "|";
			for (int j = 0; j < 3; j++) {
				if (matrizTablero[i][j] == -1) {
					result += "X|";
				} else if (matrizTablero[i][j] == -2) {
					result += "O|";
				} else {
					result += String.valueOf(matrizTablero[i][j])+"|";// String.valueOf(matrizTablero[i][j]);
				}
			}
			result += "\n";
		}
		return result;
	}

}
