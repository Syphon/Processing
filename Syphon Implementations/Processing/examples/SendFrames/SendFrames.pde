import codeanticode.syphon.*;

PGraphics canvas;
SyphonServer syphon;

void setup() {
  size(400,400, P3D);
  canvas = createGraphics(400, 400, P3D);
  
  // Create syhpon server to send frames out.
  // It is named "Processing Syphon" by default
  // but another name can be set by passing it
  // an additional argument to the constructor:
  //syphon = new SyphonServer(this, "Processing frame out");
  syphon = new SyphonServer(this);
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
  syphon.sendImage(canvas);
}
