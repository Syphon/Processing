package codeanticode.syphon;

public class Syphon {  
   protected final static String VERSION = "##version##";
  
   protected static int count;
   
   public static void init() {
     if (count == 0) {
       System.out.println("##name## ##version## by ##author##");
     }
     count++;
   }
     
   /**
    * return the version of the library.
    * 
    * @return String
    */
   public static String version() {
     return VERSION;
   } 
}
