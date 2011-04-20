/**
 * you can put a one sentence description of your library here.
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

import java.util.Dictionary;

import processing.core.*;
import processing.opengl2.*;
import jsyphon.*;

/**
 * Syphon server class. It broadcasts the textures encapsulated in 
 * PImage objects when the OPENGL2 renderer is used.
 *
 */

public class SyphonClient {
  PApplet parent;
  PGraphicsOpenGL2 ogl2;
  private JSyphonClient client;
  
  public final static String VERSION = "##version##";
  
  /**
   * Default constructor.
   * 
   * @param parent
   */
  public SyphonClient(PApplet parent) {
    this.parent = parent;
    ogl2 = (PGraphicsOpenGL2)parent.g;
    client = new JSyphonClient();
    //client.initWithName("Processing Syphon");
    welcome();
  }
  
  /*
  public void sendImage(PImage img) {
    PTexture tex = ogl2.getTexture(img);
    server.publishFrameTexture(tex.glID,tex.glTarget, 0, 0, tex.glWidth, tex.glHeight, tex.glWidth, tex.glHeight, false);
  } 
  

  */

  public Dictionary<String, String> description() {
    return client.serverDescription();  
  }
  
  public boolean available() {
    return client.hasNewFrame();
  }
  
  private void welcome() {
    System.out.println("##name## ##version## by ##author##");
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

