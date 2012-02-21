import codeanticode.syphon.*;

PGraphics canvas;
SyphonClient client;

public void setup() {
  size(400, 400, P3D);  
  
  // Create syhpon client to receive frames 
  // from running server with given name: 
  client = new SyphonClient(this, "Simple Server");
}

public void draw() {
  background(0);
  if (client.available()) {
    client.getImage(null);    
  }  
}

void keyPressed() {
  if (key == ' ') {
    client.stop();  
  } else if (key == 'd') {
    println(client.description());
  }
}