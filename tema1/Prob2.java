
import java.io.*;
import java.util.*;

public class Prob2 {

	// Functie pentru determinarea minimului dintre 2 numere intregi
	public static int minim(int x , int y){
		  return (x < y) ? x : y;
    }
	
 // Aceasta functie returneaza numarul minim de ajustari asupra cuvantului rostit
	public static int distance(String fisier) throws IOException{
		
		//Pentru citire am format un obiect BufferedReader
		BufferedReader bf = new BufferedReader(new FileReader( fisier ));	
	
		// Citesc prima linie , stiind ca ea contine NrVar si N
		// si o despart cu ajutorul lui split(cu argumentu " ")
		
		String firstline = bf.readLine();
		String[] first = firstline.split(" ");
		
		int nrVar = Integer.parseInt(first[0]);
		int N = Integer.parseInt(first[1]);
		
		
		// Matricea in care retin pe fiecare linie varianta , iar coloanele sunt
		// pentru litere
		int[][] matrVar = new int[nrVar][N];

		for(int i = 0; i < nrVar; i++){
			
			// Citesc cuvantul rostit
			firstline = bf.readLine();
			first = firstline.split(" ");
			
			for(int j = 0 ; j < N ; j++)
				matrVar[i][j] = Integer.parseInt( first[j] );
		}
		
		// M este numarul de cuvinte al variantelor
		int M = Integer.parseInt( bf.readLine() );
		int[] cuvant = new int[M];
		int bol ;
		int distanta[][] = new int[M+1][N+1];
	 
		firstline = bf.readLine();
		first = firstline.split(" ");

	    for (int i = 0; i < M+1 ; i++)
	    {
	    	// Daca i este mai mic ca M atunci il voi citi in vectorul cuvant
	    	if(i < M)
	    		cuvant[i] = Integer.parseInt(first[i]);
	    	
	        for (int j = 0; j < N+1; j++)
	        {
	            if (i == 0)
	                distanta[i][j] = j;  
	       
	            else if (j == 0)
	                distanta[i][j] = i; 
	      
	            else {
	            	bol = distanta[i-1][j-1];
	            	// bol este pentru a ma folosi atunci cand gasesc ca o litera din var. de pe pozitia
	            	// j-1 este egala cu o litera din cuvantul rostit de pe i-1 , scad ca ea fiind minim
	            	for(int k = 0; k < nrVar ; k++){
	            		if(matrVar[k][j-1]  == cuvant[i-1]){
	            			bol--;
	            			break;
	            		}      			
	            	}	            	
	            
	            	distanta[i][j] = 1 + minim(distanta[i][j-1], minim(distanta[i-1][j], bol)); 
	            	
	            }
	        }
	    }
	    // Inchid bufferreader
	    bf.close();	    
	    return distanta[M][N];
	}
	
	
	// Aceasta functie e pentru a scrie in fisierul evaluare.out
	// rezultatul functiei distance, argumentul n este distanta.
	public static void scriere(String fisier, int n) throws IOException{
		
		// Scriu in fisier cu BufferReader
		File out = new File(fisier);
		BufferedWriter output = new BufferedWriter(new FileWriter(out));
		int dis = n;

		output.write(String.valueOf(dis));
		output.close();
		
	}
	
	
	public static void main(String[] args) throws IOException{
		
		// In aceasta functie principala main apeles cele 2 functii de mai sus
		// ambele avand ca argumente fisierele de unde trebuie citit si unde 
		// trebuie scris, cea de-a doua avand ca argument si numarul.
		
		int n = distance("evaluare.in");
		scriere("evaluare.out", n);
				
	}
	
}
