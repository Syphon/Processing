import codeanticode.syphon.*;

PGraphics canvas;
SyphonClient syphon;

public void setup() {
  size(400, 400, P3D);  
  
  // Create syhpon client to receive frames 
  // from running server with given name: 
  syphon = new SyphonClient(this, "Simple Server");
}

public void draw() {
  background(0);
}

void keyPressed() {
  if (key == ' ') {
    syphon.stop();  
  } else if (key == 'd') {
    println(syphon.description());
  }
}