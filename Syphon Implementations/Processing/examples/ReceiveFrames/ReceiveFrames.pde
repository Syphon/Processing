import codeanticode.syphon.*;

PGraphics canvas;
SyphonClient syphon;

public void setup() {
  size(400,400, OPENGL);
  //canvas = (PGraphicsOpenGL)createGraphics(400, 400, OPENGL);  
  syphon = new SyphonClient(this);
}

public void draw() {
}