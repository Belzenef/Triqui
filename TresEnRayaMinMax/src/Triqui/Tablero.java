package Triqui;

public class Tablero {

	// Respresentación de estados
	int matrizTablero[][] = new int[3][3];
	
	// Tests
	public static void main(String[] args) {
		Tablero Tab = new Tablero();
		System.out.println("ok");
		System.out.println(Tab.toString());
		Tab.set(0,0,-1);
		Tab.set(0,2,-2);
		System.out.println(Tab.toString());
		int[][] M = Tab.evaluar(-1);
		Tablero test = new Tablero(M);
		System.out.println(test.toString());
		int[][] M2 = Tab.evaluar(-2);
		Tablero test2 = new Tablero(M2);
		System.out.println(test2.toString());
	}

	// Constructores
	public Tablero() {}
	
	public Tablero(int[][] M ) {
		// Inicializamos variables de la partida
		matrizTablero = M;
	}
	
	
	//Funcion heuristica 
	public int[][] evaluar(int e) {
		int matriz[][] = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (matrizTablero[i][j] != -1 && matrizTablero[i][j] != -2 ) {
					int count=0;
					// numero de e en linea i
					for (int p = 0; p < 3; p++) {
						if(matrizTablero[i][p]==e) {
							count++;
						}
					}
					// numero de e en columna j
					for (int p = 0; p < 3; p++) {
						if(matrizTablero[p][j]==e) {
							count++;
						}
					}
					// numero de e en diagonal
					if(i==j) {
						for (int p = 0; p < 3; p++) {
							if(matrizTablero[p][p]==e) {
								count++;
							}
						}
					}
					matriz[i][j]=count;
				}else {matriz[i][j]=matrizTablero[i][j];}
			}
		}
		return matriz;
	}
	
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
