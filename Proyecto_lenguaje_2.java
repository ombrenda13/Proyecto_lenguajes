
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
        
        Eliminar_inaccesibles(sin_puntos_muertos,reglas_originales);
        
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

    public static void Eliminar_inaccesibles(ArrayList sin_puntos_muertos,ArrayList reglas_originales){
     int aux;
         int aux2=0; 
         String aux_char = null;
         
            ArrayList auxiliar=new ArrayList();
        for(int a=0; a<reglas_originales.size();a++){
            String cadena=sin_puntos_muertos.get(a).toString();
            String[] Array1=cadena.split(":");
            String cadena3=Array1[1]; 
            auxiliar.add(cadena3);  
        }  
        
         for(int a=0; a<reglas_originales.size();a++){
          aux=0;
          for(int b=0; b<auxiliar.size(); b++){
                   if(auxiliar.get(b).toString().contains(reglas_originales.get(a).toString())){
                     aux_char=reglas_originales.get(a).toString();
                    
                     aux=aux+1;  
                        
                     if(aux==1 && a==b){
                         if(a!=0){ //evita que se elimine el simbolo inicial 
                        System.out.println("INACCESIBLE:"+reglas_originales.get(a)); 
                        aux_char=reglas_originales.get(a).toString();
                         
                        for(int c=0; c<sin_puntos_muertos.size();c++){
                            if(aux_char.toString().charAt(0)==sin_puntos_muertos.get(c).toString().charAt(0)){
                                  sin_puntos_muertos.remove(sin_puntos_muertos.get(c));
                            }  }
                         }
                        
                     }  
                                        }    
                                               }
                    if(aux==0){
                        aux_char=reglas_originales.get(a).toString();
                     //   System.out.println("INACCESIBLE:"+reglas_originales.get(a));
                        for(int c=0; c<sin_puntos_muertos.size();c++){
                          
                            if(aux_char.toString().charAt(0)==sin_puntos_muertos.get(c).toString().charAt(0)){
                                        if(c!=0){
                                    sin_puntos_muertos.remove(sin_puntos_muertos.get(c));}
                            }
                        }
                              }  
                                                      }
         
      System.out.println("REGLAS_SIN_INACCESIBLES:"+sin_puntos_muertos);
         Eliminacion_cadenas_vacias(sin_puntos_muertos);
    }
    
    public static void Eliminacion_cadenas_vacias(ArrayList sin_puntos_muertos ){ 
        String Nueva_produccion=new String();
        ArrayList Cadena_vacia=new ArrayList();
        Set<String> Cadena_sin_repeticiones = new HashSet<>();
        int bandera=0;
        char caracter;
        String nuevas_cadenas_vacias=new String();
        for(int a=0;a<sin_puntos_muertos.size();a++){
           if(a==0){
               
               if(sin_puntos_muertos.get(a).toString().contains("@")){
                  // System.out.println("lo contiene:"+sin_puntos_muertos.get(a));
                   
                   
                   String cadena=sin_puntos_muertos.get(a).toString();
                        String[] Array1=cadena.split(":");
                        String cadena2=Array1[0]; 
                   for(int b=1;b<sin_puntos_muertos.size();b++){
                       if(sin_puntos_muertos.get(b).toString().contains(cadena2)){ // analiza si el simbolo inicial se encuentra en otra produccion
                          //System.out.println("estoy en la cadena:"+sin_puntos_muertos.get(b));
                       Nueva_produccion=(cadena2+"'"+":"+cadena2+"/"+"@");
                           bandera=1;
                       }
                   }
               }
               if(bandera==1){
             sin_puntos_muertos.add(0,Nueva_produccion);
               }
        }
           else{
                 if(sin_puntos_muertos.get(a).toString().contains("@")){
                  Cadena_vacia.add(sin_puntos_muertos.get(a).toString().charAt(0));
                    
                     
                 }
           } 
           
           for(int b=0;b<Cadena_vacia.size();b++){
               
                
           }
           
           
    } 
      
         System.out.println("cadena_vacia:"+Cadena_vacia);
        
        
        
        
//        for(int b=0; b<Cadena_vacia.size();b++){
//        for(int a=0; a<sin_puntos_muertos.size();a++){
//                    if(sin_puntos_muertos.get(a).toString().contains(Cadena_vacia.get(b).toString())){
//                        System.out.println("tambien produzco vacio:"+sin_puntos_muertos.get(a).toString().charAt(0));
//                        String cadena=new String();
//                        Cadena_vacia.add(sin_puntos_muertos.get(a).toString());
//                 //     System.out.println("cadenaaa:"+Cadena_vacia);
//                         
//                    }
//        }
//      
//        }
       // System.out.println("nueva_cadena_vacia:"+Cadena_vacia);
       Cadena_vacia.add(nuevas_cadenas_vacias);
       
        System.out.println("prueba hash---"+nuevas_cadenas_vacias);
     //   Cadena_vacia.add(sin_puntos_muertos.get(a).toString().charAt(0));
     Cadena_sin_repeticiones.add(nuevas_cadenas_vacias);
     for(int a=0; a<nuevas_cadenas_vacias.length();a++){
         
     
     }
        System.out.println("cadena_sin...."+Cadena_sin_repeticiones);
            Combinaciones(sin_puntos_muertos,nuevas_cadenas_vacias);
        
        
        
}

public static void Combinaciones(ArrayList sin_puntos_muertos,String Cadena_vacia){
        System.out.println("combinaciones:"+sin_puntos_muertos);
        System.out.println("combinaciones:"+Cadena_vacia);
       ArrayList auxiliar=new ArrayList();
        for(int a=0; a<sin_puntos_muertos.size();a++){
            String cadena=sin_puntos_muertos.get(a).toString();
            String[] Array1=cadena.split(":");
            String cadena3=Array1[1]; 
            auxiliar.add(cadena3);  
        }  
        
       for(int a=0;a<sin_puntos_muertos.size();a++){
           
           if(sin_puntos_muertos.get(a).toString().contains(Cadena_vacia)){
           
           }
           
       }
}







}

    

