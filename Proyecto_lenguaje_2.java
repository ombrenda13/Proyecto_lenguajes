
package proyecto_lenguaje_2;
import java.util.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Proyecto_lenguaje_2 {

    public static void main(String[] args) {
       ArrayList producciones=new ArrayList();
       ArrayList sin_puntos_muertos=new ArrayList(producciones.size());
       ArrayList reglas_originales=new ArrayList();
       ArrayList no_terminales= new ArrayList();
        ArrayList terminales = new ArrayList();
        ArrayList no_terminales_original=new ArrayList();
       
       
      try {
             Lector("/home/brenda/Escritorio/apuntes SO/proyecto_lenguaje_2/reglas.txt",producciones,no_terminales,terminales);
        } catch (IOException ex) {
            Logger.getLogger(Proyecto_lenguaje_2.class.getName()).log(Level.SEVERE, null, ex);
        }
     System.out.println("REGLAS ORIGINALES:");
        System.out.println(producciones);
        
      try {
             Lector2("/home/brenda/Escritorio/apuntes SO/proyecto_lenguaje_2/reglas.txt",no_terminales,terminales);
        } catch (IOException ex) {
            Logger.getLogger(Proyecto_lenguaje_2.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("\n");
       System.out.println("Elementos no terminales:");
        System.out.println(no_terminales.toString());

        System.out.println("Elementos terminales:"); 
        System.out.println(terminales.toString());
        for(int a=0;a<producciones.size();a++){
            String cadena=producciones.get(a).toString();
            String[] Array1=cadena.split(":");
            String cadena2=Array1[0];
            reglas_originales.add(cadena2);}
        
        Eliminar_muertos(producciones,no_terminales,terminales,sin_puntos_muertos,reglas_originales);
      
        System.out.println("SIN PUNTOS MUERTOS:"+sin_puntos_muertos);
        
    }
    
    public static void Lector(String archivo,ArrayList producciones,ArrayList no_terminales,ArrayList terminales) throws FileNotFoundException, IOException{
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);         
        
        String linea; 
         while((linea=br.readLine())!=null){ 
            
            producciones.add(linea); //agrega las producciones a una lista conforme el archivo se lee linea por linea
            
        }
        
      
       fr.close();
    } 
    
    public static void Lector2(String archivo,ArrayList no_terminales,ArrayList terminales)throws FileNotFoundException, IOException{
    
    FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        
        int caracterLeido = fr.read();
        while(caracterLeido != -1) { 
           
       char caracter = (char) caracterLeido; 
   
           if(caracterLeido>=65 && caracterLeido<=90){
               terminales.add(caracter);
           }   
           if(caracterLeido>=97 && caracterLeido<=122){
               no_terminales.add(caracter);
               
           }
           if (caracterLeido==64){
               no_terminales.add(caracter);
           
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

        br.close();
    
    }
    
    public static void Eliminar_muertos(ArrayList producciones,ArrayList terminales,ArrayList no_terminales,ArrayList sin_puntos_muertos,ArrayList reglas_originales){
       int bandera=0;
       int aux_nt=0;
       String[] arreglo=new String[producciones.size()];
    String s_p_m=new String ();
    for(int a=0;a<producciones.size();a++){
            String cadena=producciones.get(a).toString();
            String[] Array1=cadena.split(":");
            String cadena3=Array1[1]; 
            String[] Array2=cadena3.split("/");
            String cadena4=new String();
                        for(int b=0;b<Array2.length;b++){
                            cadena4=Array2[b];
                            
                            aux_nt=0;
                        for(int c=0;c<cadena4.length();c++){
                            if(cadena4.charAt(c)>=65 && cadena4.charAt(c)<=90){
                                aux_nt=aux_nt+1;
                            }               
                        }  
                     bandera=0;
                           for(int c=0;c<reglas_originales.size();c++){
                              
                               for(int d=0;d<cadena4.length();d++){
                               if(cadena4.charAt(d)>=65 && cadena4.charAt(d)<=90){
                                   if(reglas_originales.get(c).toString().charAt(0)==Array2[b].charAt(d)){
                                   bandera=bandera+1;
                                   }  }}
                           }
                         
                    for(int c=0;c<=sin_puntos_muertos.size();c++){   
                         if(bandera!=aux_nt){
                         }  else{
                            s_p_m=s_p_m+Array2[b]+"/"; } 
                    }  
                        } 
                        arreglo[a]=(Array1[0]+":"+s_p_m);
              s_p_m="";       
    }    
     
        for(int a=0;a<arreglo.length;a++){
            sin_puntos_muertos.add(arreglo[a]);
        }       
    }


    













}
        
   
        
    
    

