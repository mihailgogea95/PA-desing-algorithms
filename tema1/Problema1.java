
import java.io.*;
import java.util.*;
import java.lang.*;

public class Problema1 {

	public static int palindromes(String v){
		
		// Count este numarul de interschimbari intre litere
		int count = 0;
		// Lungimea cuvantului : n
		int n = v.length();
		
		// Aceste 3 variabile a,b,c ma ajuta pentru a modifica cuvantul initial
		// dupa anumite interschimbari
		String a,b,c;
		
		// Parcurg pana la jumate cuvantul
		for(int i = 0; i < n/2; i++){
		
			if(v.charAt(i) != v.charAt(n-i-1)){
				for(int j = n-i-2; j >= i; j--){
					
					if(j == i){
						return -1;						
					}
					else 
						if(v.charAt(j) == v.charAt(i)){
							a = v.substring(0 , j);
							b = v.substring(j+1 , n-i);
							if(n-i == n)
								c = "";
							else c = v.substring(n-i , n);
							
							count += (n-i-1) - j;
							v = a + b + String.valueOf(v.charAt(j)) + c;
							break;
							
					}else 
						if(v.charAt(n-j-1) == v.charAt(n-i-1)){
							if(i == 0)
								a = "";
							else a = v.substring(0 , i);
					
							b = v.substring(i , n-j-1);
							c = v.substring(n-j , n);
							count += (n-j-1) - i;
							v = a + String.valueOf(v.charAt(n-j-1)) + b + c;
							break;
					}
				}
			}
		}
		
		return count;
		
	}
	
	public static void main(String[] args) throws IOException{
		
		// Deschid fisierul pentru a citi cu ajutorul lui bufferReader
		BufferedReader scan = new BufferedReader(new FileReader( "joc.in" ));
		
		// out este fisierul in care scriu
		File out = new File("joc.out");
		
		// Il voi scrie cu ajutorul lui bufferWriter
		BufferedWriter output = new BufferedWriter(new FileWriter(out));
		
		// Citesc numarul de cuvinte pe care vor urma sa le citesc
		int nr = Integer.parseInt(scan.readLine());
		
		StringBuffer buf = new StringBuffer();
		
		// Pentru fiecare cuvant apelez functia palindromes pentru a afisa numarul
		// de interschimbari
		for(int i = 0; i < nr; i++){
			buf.append(palindromes(scan.readLine()));
			if(i != nr-1)
				buf.append("\n");
		}
		
		output.write(buf.toString());
		output.close();
		
	}
	
}

