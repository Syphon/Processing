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

/**
    Processing Syphon library 
 
    Copyright 2011 Andres Colubri.
    All rights reserved.

    Redistribution and use in source and binary forms, with or without
    modification, are permitted provided that the following conditions are met:

    * Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.

    * Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.

    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
    ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
    WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
    DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS BE LIABLE FOR ANY
    DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
    (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
    LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
    ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
    (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
    SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package codeanticode.syphon;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

import processing.core.*;
import processing.opengl.*;
import jsyphon.*;

/**
 * Syphon client class. It receives textures from a Syphon
 * server.
 *
 */
public class SyphonClient {
  protected PApplet parent;
  protected PGraphicsOpenGL pg;
  protected JSyphonClient client;
  protected IntBuffer getBuffer;
  public PGraphics tempDest;
  
  /**
   * Constructor that binds this client to the
   * specified named server.
   * 
   * @param parent
   * @param serverName
   */  
  public SyphonClient(PApplet parent, String appName) {
    this.parent = parent;
    pg = (PGraphicsOpenGL)parent.g;
    
    Syphon.init();
    
    client = new JSyphonClient();
    client.init();    
    
    if (appName != null && !appName.equals("")) {
      client.setApplicationName(appName);
    } else {
      throw new RuntimeException("No valid application name was provided");
    }
  }

  /**
   * Constructor that binds this client to the
   * specified named server.
   * 
   * @param parent
   * @param appName
   * @param serverName
   */  
  public SyphonClient(PApplet parent, String appName, String serverName) {
    this.parent = parent;
    pg = (PGraphicsOpenGL)parent.g;
    
    Syphon.init();
    
    client = new JSyphonClient();
    client.init();
    
    boolean setAppName = false; 
    if (appName != null && !appName.equals("")) {
      client.setApplicationName(appName);
      setAppName = true;
    }
    
    boolean setServerName = false;
    if (serverName != null && !serverName.equals("")) {
      client.setServerName(serverName);
      setServerName = true;
    }
    
    if (!setAppName && !setServerName) {
      throw new RuntimeException("No valid application or server names were provided");
    }
  }
  
  
  @SuppressWarnings("unchecked")
  /**
   * Returns an array of hash maps containing the names of the currently
   * available Syphon servers.
   * 
   * @return HashMap<String, String>
   */     
  static public HashMap<String, String>[] listServers() {
    ArrayList<Dictionary<String, String>> tempList = JSyphonServerList.getList();
    
    HashMap<String, String>[] outArray = new HashMap[tempList.size()]; 
    for (int i = 0; i < tempList.size(); i++) {
      Dictionary<String, String> desc = tempList.get(i);
      String appName = desc.get("SyphonServerDescriptionAppNameKey");
      String serverName = desc.get("SyphonServerDescriptionNameKey");
      HashMap<String, String> res = new HashMap<String, String>();
      res.put("AppName", appName);
      res.put("ServerName", serverName);      
      outArray[i] = res;
    }
                               
    return outArray;
  }
  
  
  /**
   * Returns a hash map containing two key-value pairs: one (key=="AppName")
   * containing the name of the application running the server bound to this 
   * client, the other (key=="ServerName") is the actual name of the server.
   * 
   * @return HashMap<String, String>
   */   
  public HashMap<String, String> getServerName() {
    Dictionary<String, String> desc = client.serverDescription();
    
    String appName = desc.get("SyphonServerDescriptionAppNameKey");
    String serverName = desc.get("SyphonServerDescriptionNameKey");
    
    HashMap<String, String> res = new HashMap<String, String>();
    res.put("AppName", appName);
    res.put("ServerName", serverName);
    
    return res;  
  }

  /**
   * Returns true if a new frame is available.
   * 
   * @return boolean 
   */   
  public boolean available() {
    return client.hasNewFrame();
  }

  /**
   * Copies the new frame to a PGraphics object.
   * It initializes dest if it is null or has the 
   * wrong size. It uses FBOs for fast copy.
   * 
   * @param dest
   */     
  public PGraphics getGraphics(PGraphics dest) {
    JSyphonImage img = client.newFrameImageForContext();
    
    int texId = img.textureName();
    int texWidth = img.textureWidth();
    int texHeight = img.textureHeight();
    
    if (dest == null || dest.width != texWidth || dest.height != texHeight) {            
      dest = parent.createGraphics(texWidth, texHeight, PConstants.P2D);
    }
    
    PGraphicsOpenGL destpg = (PGraphicsOpenGL)dest;
    destpg.beginDraw();
    destpg.background(0);
    destpg.drawTexture(PGL.TEXTURE_RECTANGLE, texId, texWidth, texHeight, 
                       0, 0, texWidth, texHeight);
    destpg.endDraw();
        
    return dest;      
  }

  
  public PImage getImage(PImage dest) {
    return getImage(dest, true);
  }
  
  
  /**
   * Copies the new frame to a PImage object.
   * It initializes dest if it is null or has the 
   * wrong size.
   * 
   * @param dest
   */    
  public PImage getImage(PImage dest, boolean loadPixels) {    
    JSyphonImage img = client.newFrameImageForContext();
    
    int texId = img.textureName();
    int texWidth = img.textureWidth();
    int texHeight = img.textureHeight();

    if (dest == null || dest.width != texWidth || dest.height != texHeight) {            
      dest = parent.createImage(texWidth, texHeight, PConstants.ARGB);
    }
        
    if (tempDest == null || tempDest.width != texWidth || tempDest.height != texHeight) {            
      tempDest = parent.createGraphics(texWidth, texHeight, PConstants.P2D);
    }
    
    PGraphicsOpenGL destpg = (PGraphicsOpenGL)tempDest;
    destpg.beginDraw();
    destpg.background(0);
    destpg.drawTexture(PGL.TEXTURE_RECTANGLE, texId, texWidth, texHeight, 
                       0, 0, texWidth, texHeight);
    destpg.endDraw();

    // Uses the PGraphics texture as the cache object for the image
    pg.setCache(dest, destpg.getTexture());
    
    if (loadPixels) {
      dest.loadPixels();
    }
    
    return dest;      
  }

  
  /**
   * Stops the client.
   * 
   */    
  public void stop() {
    client.stop();
  }
}

