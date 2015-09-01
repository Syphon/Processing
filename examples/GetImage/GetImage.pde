import codeanticode.syphon.*;

PImage img;
SyphonClient client;

void settings() {
  size(480, 340, P3D);
  PJOGL.profile = 1;
}

void setup() {
  // Create syhpon client to receive frames 
  // from the first available running server: 
  client = new SyphonClient(this);
}

void draw() {
  background(0);
  if (client.newFrame()) {
    // The first time getImage() is called with 
    // a null argument, it will initialize the PImage
    // object with the correct size.
    img = client.getImage(img); // load the pixels array with the updated image info (slow)
    //img = client.getImage(img, false); // does not load the pixels array (faster)    
  }
  if (img != null) {
    image(img, 0, 0, width, height);  
  }
}

void keyPressed() {
  if (key == ' ') {
    client.stop();  
  } else if (key == 'd') {
    println(client.getServerName());
  }
}