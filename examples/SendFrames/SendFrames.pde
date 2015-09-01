import codeanticode.syphon.*;

PGraphics canvas;
SyphonServer server;

void settings() {
  size(400,400, P3D);
  PJOGL.profile=1;
}

void setup() { 
  canvas = createGraphics(400, 400, P3D);
  
  // Create syhpon server to send frames out.
  server = new SyphonServer(this, "Processing Syphon");
}

void draw() {
  canvas.beginDraw();
  canvas.background(127);
  canvas.lights();
  canvas.translate(width/2, height/2);
  canvas.rotateX(frameCount * 0.01);
  canvas.rotateY(frameCount * 0.01);  
  canvas.box(150);
  canvas.endDraw();
  image(canvas, 0, 0);
  server.sendImage(canvas);
}
