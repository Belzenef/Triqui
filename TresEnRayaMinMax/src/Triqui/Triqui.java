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
  
	// Verificar si alguien gano
   	public boolean gano(int c){
		for (int i = 0; i < 3; i++) {
			if( matrizTablero[i][0]==c && matrizTablero[i][1]==c && matrizTablero[i][2]==c) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
			if( matrizTablero[0][i]==c && matrizTablero[1][i]==c && matrizTablero[2][i]==c) {
                return true;
            }
        }
        if( matrizTablero[0][0]==c && matrizTablero[1][1]==c && matrizTablero[2][2]==c) {
        	return true;
        }
        if( matrizTablero[1][1]==c && matrizTablero[0][2]==c && matrizTablero[2][0]==c) {
      		return true;
        }
    	return false;
	}
	
	// Elegir el mejor sucesor (Min-Max)
    public int minmax(int[][] estado, int profundidad, boolean turnoIA){
      	int c;
		if(turnoIA) {
			c=-2;
		}else {c=-1;}
		if(gano(-1)){
        	System.out.println("X gano");
          	if(c==-1){return 1;}
          	else{return -1;}
      	}else if(gano(-2)){
			System.out.println("O gano");
          	if(c==-1){return -1;}
          	else{return 1;}
      	}
      	ArrayList<Tablero> posibilidades = this.sucesores();
      	if(posibilidades.size()==0){return 0;}
      	int mejorIndex = 0;
		int mejorScore = posibilidades.get(0).evaluar(turnoIA);
		for (int i = 0; i < posibilidades.size(); i++) {	
		}
      	
    }
        for coup in coupsPossibles:
            newPos = deepcopy (self)
            newPos. joue (joueur, coup // 3, coup % 3)
            cp = deepcopy (coupsPossibles)
            cp. remove (coup)
            valeur [coup] = newPos. minmax (autre, joueur, not moi, cp, False)
        if moi:
            v = valeur [coupsPossibles [0]]
            which = coupsPossibles [0]
            for coup in coupsPossibles:
                if valeur [coup] > v:
                    v = valeur [coup]
                    which = coup
            if racine:
                return (which)
            else:
                return (v)
        else:
            v = valeur [coupsPossibles [0]]
            which = coupsPossibles [0]
            for coup in coupsPossibles:
                if valeur [coup] < v:
                    v = valeur [coup]
                    which = coup
            if racine:
                return (which)
            else:
                return (v)
  
  	public int minimax(node, depth, maximizingPlayer){
      if self. agagne (joueur):
            self.__str__ ()
            if moi:
                return (+1)
            else:
                return (-1)
      if self. agagne (autre):
            self.__str__ ()
            if moi:
                return (-1)
            else:
                return (+1)
      
    }
    if depth = 0 or node is a terminal node then
        return the heuristic value of node
    if maximizingPlayer then
        value := −∞
        for each child of node do
            value := max(value, minimax(child, depth − 1, FALSE))
        return value
    else (* minimizing player *)
        value := +∞
        for each child of node do
            value := min(value, minimax(child, depth − 1, TRUE))
        return value
}
