/**
 * The Processing-Syphon library allows to create Syphon clients
 * and servers in a Processing sketch to share frames with other
 * applications. It only works on MacOSX and requires the P3D
 * renderer.
 *
 * ##copyright##
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA  02111-1307  USA
 * 
 * @author    ##author##
 * @modified  ##date##
 * @version   ##version##
 */

package codeanticode.syphon;

/**
 * Processing library that encapsulates Syphon clients and servers.
 * Syphon is an open source Mac OS X technology that allows 
 * applications to share frames - full frame rate video or stills - 
 * with one another in realtime. 
 * 
 * {@link http://syphon.v002.info/}
 * 
 */ 
public class Syphon {  
  public final static String VERSION = "##library.prettyVersion##";
  
   protected static int count;

   /**
    * Static initialization.
    * 
    */   
   protected static void init() {
     if (count == 0) {       
       System.out.println("##library.name## ##library.prettyVersion## by ##author##");       
     }
     count++;
   }
     
   /**
    * Return the version of the library.
    * 
    * @return String
    */
   public static String version() {
     return VERSION;
   } 
}
