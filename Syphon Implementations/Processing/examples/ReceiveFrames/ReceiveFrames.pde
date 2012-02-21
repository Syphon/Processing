import codeanticode.syphon.*;

PGraphics canvas;
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
    canvas = client.getGraphics(canvas);
    image(canvas, 0, 0, width, height);    
  }  
}

void keyPressed() {
  if (key == ' ') {
    client.stop();  
  } else if (key == 'd') {
    println(client.description());
  }
}