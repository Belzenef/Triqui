package Triqui;

public class Tablero {

	// Respresentacion de estados
	String matrizTablero[][] = new String[3][3];
	int casillasVacias;
	
	// Tests
	public static void main(String[] args) {
		Tablero Tab = new Tablero();
		System.out.println("ok");
		System.out.println(Tab.toString());
		Tab.set(0,0,"X");
		Tab.set(1,2,"X");
		Tab.set(0,1,"O");
		Tab.set(0,2,"O");
		System.out.println(Tab.toString());
		int M = Tab.evaluar(true);
		System.out.println(M);
		int M2 = Tab.evaluar(false);
		System.out.println(M2);
		Tab.set(1,1,"X");
		Tab.set(2,2,"X");
		System.out.println(Tab.toString());
		int M3 = Tab.evaluar(true);
		System.out.println(M3);
		int M4 = Tab.evaluar(false);
		System.out.println(M4);
	}

	// Constructores
	public Tablero() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				matrizTablero[i][j]=" ";
			}
		}
		casillasVacias = 9;
	}
	
	public Tablero(String[][] M ) {
		// Inicializamos variables de la partida
		matrizTablero = M;
		casillasVacias = contarCasillasVacias();
	}
	
	// Verificar si alguien gano
   	public boolean gano(String c){
		for (int i = 0; i < 3; i++) {
			if(matrizTablero[i][0]==c && matrizTablero[i][1]==c && matrizTablero[i][2]==c) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
			if(matrizTablero[0][i]==c && matrizTablero[1][i]==c && matrizTablero[2][i]==c) {
                return true;
            }
        }
        if(matrizTablero[1][1]==c && matrizTablero[2][2]==c && matrizTablero[0][0]==c) {
        	return true;
        }
        if(matrizTablero[1][1]==c && matrizTablero[0][2]==c && matrizTablero[2][0]==c) {
      		return true;
        }
    	return false;
	}
   	
   	// Cuantas casillas vacias hay
   	public int contarCasillasVacias(){
   		int result=0;
   		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(matrizTablero[i][j]==" ") {
					result++;
				}
			}
		}
   		return result;
   	}
	
	// Funcion heuristica 
	public int evaluar(boolean turnoHumano) {
		// Verificar si alguien gano o si el tablero esta lleno
   		if(gano("X")) {
   			if(turnoHumano) {
   				return 100-9+casillasVacias;
   			}else {return -100+9-casillasVacias;}
   		}else if(gano("O")) {
   			if(turnoHumano) {
   				return -100+9-casillasVacias;
   			}else {return 100-9+casillasVacias;}
   		}else if(casillasVacias==0 ){
   			return 0;
   		}
   		
   		// Si la partida no esta terminada
		int result = 0;
		String good, bad;
		if(turnoHumano) {
			good="X";
			bad="O";
		}else {
			good="O";
			bad="X";
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
	public void set(int i, int j, String x) {
		matrizTablero[i][j] = x;
	}
	
	public String getCasilla(int i, int j) {
		return matrizTablero[i][j];
	}
	
	public String[][] getMatriz() {
		String matriz[][] = new String[3][3];
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
				result += matrizTablero[i][j] + "|";
			}
			result += "\n";
		}
		return result;
	}

}
