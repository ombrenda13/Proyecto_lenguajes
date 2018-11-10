
package proyecto_lenguaje;
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Proyecto_lenguaje {

    public static void main(String[] args) {
       
        ArrayList no_terminales= new ArrayList();
        ArrayList terminales = new ArrayList();
        ArrayList no_terminales_original=new ArrayList();
        
        try {
            muestraContenido("/home/brenda/Escritorio/apuntes SO/Proyecto_lenguaje/reglas.txt",no_terminales,terminales,no_terminales_original);
        } catch (IOException ex) {
            Logger.getLogger(Proyecto_lenguaje.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        System.out.println("Elementos no terminales:");
        while (!no_terminales.isEmpty()){   
        System.out.println(no_terminales.remove(0));
        }
        
        System.out.println("Elementos terminales:");
        while (!terminales.isEmpty()){   
        System.out.println(terminales.remove(0));
        }
       
    }
    
    
    public static void muestraContenido(String archivo,ArrayList no_terminales,ArrayList terminales,ArrayList no_terminales_original) throws FileNotFoundException, IOException {
        String cadena; 
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        
        int caracterLeido = fr.read();
       
        while(caracterLeido != -1) {
         
          char caracter = (char) caracterLeido; 
           if(caracterLeido>=65 && caracterLeido<=90){
               no_terminales.add(caracter);
           }   
           if(caracterLeido>=97 && caracterLeido<=122){
               terminales.add(caracter);
               
           }
          caracterLeido = fr.read(); 
        }
        
        
        Set<String> hs = new HashSet<>();//HashSet es un tipo de Array que no admite elementos repetidos
        hs.addAll(no_terminales);
        no_terminales.clear();
        no_terminales.addAll(hs);
        
        Set<String> hs1 = new HashSet<>();
        hs1.addAll(terminales);
        terminales.clear();
        terminales.addAll(hs1);
        
        while(br.readLine()!=null ){
                cadena=br.readLine();
                System.out.println(cadena);
                    br.readLine();
        }
    
        
        br.close();
    } 
}

