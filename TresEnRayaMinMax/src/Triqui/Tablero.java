package Triqui;

public class Tablero {

	// Respresentacion de estados
	int matrizTablero[][] = new int[3][3];
	
	// Tests
	public static void main(String[] args) {
		Tablero Tab = new Tablero();
		System.out.println("ok");
		System.out.println(Tab.toString());
		Tab.set(0,0,-1);
		Tab.set(1,2,-1);
		Tab.set(0,1,-2);
		Tab.set(0,2,-2);
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
				    result+=2;
				    if(i==j || (i==0 && j==2) || (i==2 && j==0)) {
				        result++;
				    }
				} else if (matrizTablero[i][j] == bad ) {
				    
					// numero de posibilidades bloqueadas en la linea
					for (int p = 0; p < 3; p++) {
						if(matrizTablero[i][p]==good) {
							result--;
						}
					}
					
					// numero de posibilidades bloqueadas en la colomna
					for (int p = 0; p < 3; p++) {
						if(matrizTablero[p][j]==good) {
							result--;
						}
					}

					// numero de posibilidades bloqueadas en las diagonales
					if(i==j) {
						for (int p = 0; p < 3; p++) {
							if(matrizTablero[p][p]==good) {
								result--;
							}
						}
					}else if (i==0 && j==2){
					    if(matrizTablero[1][1]==good){result--;}
					    if(matrizTablero[2][0]==good){result--;}
					}else if (i==2 && j==0){
					    if(matrizTablero[1][1]==good){result--;}
					    if(matrizTablero[0][2]==good){result--;}
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
					result += " |";// String.valueOf(matrizTablero[i][j]);
				}
			}
			result += "\n";
		}
		return result;
	}

}
