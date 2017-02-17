
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * Am construit aceasta clasa Graph pentru a putea construii
 * graful necesar problemei. Graful acesta este de tip 
 * neorientat.
 * Am construit in aceasta clasa functi de tip set si get
 * pentru a fi mai usor lucrul cu graful.
 */
class Graph{
	
	private List<List<Integer>> listaAdiacenta;
	
	private boolean[] visited;
	int nrNoduri ;
	public int[] parinti ;
	
	public Graph(int n){
		int i;
		nrNoduri = n;
		listaAdiacenta = new ArrayList<List<Integer>>();
		parinti = new int[n];
		for(i=0; i<n; i++){
			listaAdiacenta.add(new ArrayList<Integer>());
		}
		visited = new boolean[n];
		for(i=0; i<n; i++){
			visited[i] = false;
		}
	}
	
	public void setVisit(int nod){
		this.visited[nod] = true;
	}
	
	public boolean isVisit(int nod){
		return this.visited[nod];
	}
	
	public void addMuchie(int sursa, int dest){
		listaAdiacenta.get(sursa-1).add(dest-1);
		listaAdiacenta.get(dest-1).add(sursa-1);
	}
	
	List<Integer> getVecini(int nod) {
		return listaAdiacenta.get(nod);
	}
	
}


public class Problema2 {

	
	/*
	 * Aceasta functie numita dfs, pe langa faptul ca aplica dfs
	 * pe graful g incepand de la nodul nod, returneaza si daca
	 * componenta conexa formata din nodurile vizitate din 
	 * punctul nod , exista cumva un ciclu.
	 * Variabila absentaCiclu care initializata cu 1 este valoarea
	 * returnata de aceasta functie. Daca gaseste vrun ciclu
	 * va returna 0.
	 */
	public static int dfsCiclu(Graph g, int nod){

		int v,i, nr =0;
		int absentaCiclu = 1;
		Stack<Integer> st = new Stack<>();
		
		st.push(nod);
		g.parinti[nod] = -1;
		
		while( !st.isEmpty() ){
			
			v = st.pop();
			g.setVisit(v);
			List<Integer> aux = g.getVecini(v);
			
			for(i=0; i<aux.size(); i++){
				if(g.isVisit( aux.get(i) ) == false){
					
					st.push(aux.get(i));
					g.parinti[aux.get(i)] = v;
					
				}else if(aux.get(i) != g.parinti[v])
					absentaCiclu = 0;
			}
			
		}
		
		return absentaCiclu;
		
	}
	
	public static void main(String[] args) throws IOException{
		
		/*
		 * Citirea o voi face cu BufferedReader scan , din fisierul portal.in, 
		 * iar afisarea o voi face cu BufferedWriter in fisierul
		 * portal.out.
		 */
		BufferedReader scan = new BufferedReader(new FileReader( "portal.in" ));
		File out = new File("portal.out");
		BufferedWriter output = new BufferedWriter(new FileWriter(out));
		
		// Aceasta variabila linie e pentru a putea citii o linie
		String linie = scan.readLine();
		String[] sir ;
		
		// Sirul de string-uri sir e pentru a putea taia spatile libere
		// si sa le introduc in acest array de stringuri sir.
		sir = linie.split(" ");
		
		int n = Integer.parseInt(sir[0]);
		
		//Cosntruiesc graful graph cu n noduri.
		Graph graph = new Graph(n);
		int m = Integer.parseInt(sir[1]);
		
		// Introduc muchiile citite in graf.
		for(int i=0; i<m; i++){
			linie = scan.readLine();
			sir = linie.split(" ");
			graph.addMuchie(Integer.parseInt(sir[0]),Integer.parseInt(sir[1]));
		}
		
		// Numarul de noduri izolate.
		int izolat = 0;
		
		for(int i=0; i<n; i++){
			if(graph.isVisit(i) == false)
				izolat += dfsCiclu(graph, i);	
		}
		// Scriu in fisier apoi inchid fisierul.
		output.write(String.valueOf(izolat));
		output.close();
		scan.close();
	}

}
