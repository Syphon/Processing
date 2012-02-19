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

// http://cocoadevcentral.com/d/learn_objectivec/
// http://www.otierney.net/objective-c.html
// http://syphon.v002.info/FrameworkDocumentation/
// http://forums.v002.info/topic.php?id=41&page=2&replies=38

import java.util.Dictionary;
import processing.core.*;
import processing.opengl.*;
import jsyphon.*;

/**
 * Syphon server class. It broadcasts the textures encapsulated in 
 * PImage objects when the OPENGL renderer is used.
 *
 */

public class SyphonClient {
  PApplet parent;
  PGraphicsOpenGL pgl;
  private JSyphonClient client;
  
  public final static String VERSION = "##version##";
  
  /**
   * Default constructor.
   * 
   * @param parent
   */
  public SyphonClient(PApplet parent) {
    this.parent = parent;
    pgl = (PGraphicsOpenGL)parent.g;
    client = new JSyphonClient();
    client.init();
    client.setApplicationName("Simple Server");
    
    
    //client.initWithName("Processing Syphon");
    
    // ??
    // event based to pass what to the sketch... an image?
    // or, initialize client object with image which will receive
    // the frames into its PTexture...
    // or get opengl texture handle from syphon
    // ??
    /*
    eventHandler = ;

    try {
      syphonEventMethod = parent.getClass().getMethod("syphonEvent",
          new Class[] { GSMovie.class });
    } catch (Exception e) {
      // no such method, or an error.. which is fine, just ignore
    }
    */
    
    (new FrameReceiverThread(this)).start();
    
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
  
  protected class FrameReceiverThread extends Thread {
    protected SyphonClient caller;

    public FrameReceiverThread(SyphonClient caller) {
      this.caller = caller;
    }

    public void run() {
      try {
        PApplet.println("FrameReceiver thread running...");
        while (true) {
          
          if (caller.client.hasNewFrame()) {
            PApplet.println("syphon client has frame");
          }
        
          Thread.sleep(100);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }      
      
      
      //PImage img = loadImage("Koala.jpg");
      //caller.hasLoaded(img);
    }
  }
  
}

