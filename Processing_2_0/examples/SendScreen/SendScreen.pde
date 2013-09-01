import codeanticode.syphon.*;

SyphonServer server;

void setup() {
  size(400,400, P3D);
  
  // Create syhpon server to send frames out.
  server = new SyphonServer(this, "Processing Syphon");
}

void draw() {
  background(127);
  lights();
  translate(width/2, height/2);
  rotateX(frameCount * 0.01);
  rotateY(frameCount * 0.01);  
  box(150);
  server.sendScreen();
}
