import processing.opengl2.*;
import codeanticode.syphon.*;

PGraphicsOpenGL2 canvas;
SyphonClient syphon;

void setup() {
  size(400,400, OPENGL2);
  canvas = (PGraphicsOpenGL2)createGraphics(400, 400, OPENGL2);  
  syphon = new SyphonClient(this);
}

void draw() {
  println(syphon.description());
  println(syphon.available());  
}

