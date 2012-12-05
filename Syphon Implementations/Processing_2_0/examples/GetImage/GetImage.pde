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
    img = client.getImage(img); // load the pixels array with the updated image info (slow)
    //img = client.getImage(img, false); // does not load the pixels array (faster)
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