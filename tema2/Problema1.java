
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * Aceasta clasa este pentru a retine muchile din 
 * graf , am facut functii set si get pentru
 * a imi usura lucrul cu ele.
 */
class Edge implements Comparable<Edge>{
	
	private long v1;
	private long v2;
	private long w;
	
	public Edge(long nod1, long nod2, long dist){
		this.v1 = nod1;
		this.v2 = nod2;
		this.w = dist;
	}
	
	public long getV1(){
		return this.v1;
	}
	
	public long getV2(){
		return this.v2;
	}
	
	public long getW(){
		return this.w;
	}

	@Override
	public int compareTo(Edge o) {
		if(this.getW() > o.getW())
			return 1;
		else if(this.getW() < o.getW())
			return -1;
		else return 0;
		 
	}
}


public class Problema1 {

	
	/* Am construit aceasta functie statica numita Kruskal , numita chiar dupa
	 * numele algortimului. Aceasta functie imi returneaza un numar long
	 * fiind rezultatul algoritmul Kruskal asupra grafului. rez din argumentul
	 * functiei este numarul muchiei care trbuie ineaparat inclus.
	 */
	public static long Kruskal(ArrayList<Edge> muchii, long N, long rez){
		Edge rezerva;
		// Imi salvez in muchia rezerva muchia rez deoarece o voi seta
		// temporar cu 0 pentru a fi luata.
		if(rez != -1){
			rezerva = muchii.get( (int)rez );
			muchii.set( (int)rez, new Edge(rezerva.getV1(), rezerva.getV2(), 0) );
		}
		// Sortez muchile crescator.
		Collections.sort(muchii);
		HashMap<Long, Long> vector = new HashMap<>();
		
		for(long i=1; i<=N; i++){
			vector.put(i, i);
		}
		long maxim = 0;
		
		for(Edge e : muchii){
			long v1 = vector.get(e.getV1());
			long v2 = vector.get(e.getV2());
			
			if(v1 == v2){
				continue;
			}else {
				maxim += e.getW();
				
				for(long key : vector.keySet()){
					if(vector.get(key) == v2){
						vector.replace(key, v2, v1);
					}
				}					
			}
		}	
		return maxim;
	}
	
	
	public static void main(String[] args) throws IOException {
		
		/*
		 * Citirea o voi face cu Scanner scan , din fisierul kim.in, 
		 * iar afisarea o voi face cu BufferedWriter in fisierul
		 * kim.out.
		 */
		Scanner scan = new Scanner(new File("kim.in"));
		File out = new File("kim.out");
		BufferedWriter output = new BufferedWriter(new FileWriter(out));
		
		ArrayList<Edge> edges = new ArrayList<>();
		long N,M,Q;
		N = scan.nextLong();
		M = scan.nextLong();
		Q = scan.nextLong();
		
		// Citesc muchiile.
		for(int i=0; i<M; i++){
			edges.add(new Edge(scan.nextLong(), scan.nextLong(), scan.nextLong()));
		}
		
		ArrayList<Edge> save = new ArrayList<>();
		save.addAll(edges);
		
		
		/*
		 *  Ceea ce voi scrie in fisier este buf,deoarece
		 * trebuie sa scriu mai multe rezultate si cu ajutorul
		 * StringBuffer-ului e mai usor.
		 */
		StringBuffer buf = new StringBuffer();
		buf.append((Kruskal(edges, N, -1))).append("\n");
	
		for(long i=0; i<Q; i++){
			
			long rez = scan.nextLong();
			ArrayList<Edge> aux = new ArrayList<>();
			aux.addAll(save);
			long add = save.get((int)(rez-1)).getW();
			buf.append((Kruskal(aux, N, (rez-1)) + add)).append("\n");
		}
		scan.close();
		output.write(buf.toString());
		output.close();
		
	}

}
