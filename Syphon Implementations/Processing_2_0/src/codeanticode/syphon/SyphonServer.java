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
 * @author		##author##
 * @modified	##date##
 * @version		##version##
 */

package codeanticode.syphon;

import processing.core.*;
import processing.opengl.*;
import jsyphon.*;

/**
 * Syphon server class. It broadcasts the textures encapsulated in 
 * PImage objects.
 *
 */
public class SyphonServer {
	protected PApplet parent;
	protected PGraphicsOpenGL pg;
	protected JSyphonServer server;
	protected String serverName;
	
  /**
   * Constructor that sets server with specified name.
   * 
   * @param parent
   * @param serverName
   */	
  public SyphonServer(PApplet parent, String name) {
    this.parent = parent;
    pg = (PGraphicsOpenGL)parent.g;
    serverName = name;
    Syphon.init();
  }
	
  /**
   * Returns true if this server is bound to any
   * client.
   * 
   * @return boolean 
   */    
  public boolean hasClients() {
    return server.hasClients();
  }	
	
  /**
   * Sends the source image to the bound client.
   * 
   * @param source
   */ 	
  public void sendImage(PImage source) {
    if (parent.frameCount == 0) {
      PGraphics.showWarning("Only can send frames in draw()");
      return;
    }
    
    Texture tex = pg.getTexture(source);
    if (tex != null) {
      if (server == null) {
        // The server needs to be created after setup and all the 
        // JOGL initialization has taken place. Otherwise frame
        // sending doesn't work...
        server = new JSyphonServer();
        server.initWithName(serverName);        
      }
      
      server.publishFrameTexture(tex.glName, tex.glTarget, 
                                 0, 0, tex.glWidth, tex.glHeight, 
                                 tex.glWidth, tex.glHeight, false);
    } else {
      PGraphics.showWarning("Texture is null");
    }
  }	
  
  /**
   * Stops the server.
   * 
   */  
  public void stop() {
    server.stop();
  }  
}

