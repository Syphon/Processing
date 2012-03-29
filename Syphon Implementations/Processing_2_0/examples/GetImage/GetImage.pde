// DOESN'T WORK YET! See ReceiveFrames to get images from a Syphon server.

import codeanticode.syphon.*;

PImage img;
SyphonClient client;

public void setup() {
  size(480, 340, P3D);
  
  // Create syhpon client to receive frames 
  // from running server with given name: 
  client = new SyphonClient(this, "Simple Server");
  
  background(0);
}

public void draw() {  
  if (client.available()) {
    // The first time getImage() is called with 
    // a null argument, it will initialize the PImage
    // object with the correct size.
    // Note that this method is slower than using 
    // getGraphics() with a PGraphics object, because
    // getImage() copies the OpenGL texture into the pixels
    // array of img, while getGraphics() does the copy
    // on the GPU using FBOs.
    img = client.getImage(img);
    image(img, 0, 0, width, height);
  }
}

void keyPressed() {
  if (key == ' ') {
    client.stop();  
  } else if (key == 'd') {
    println(client.description());
  }
}
