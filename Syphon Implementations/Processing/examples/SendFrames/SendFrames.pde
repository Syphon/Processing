import processing.opengl2.*;
import codeanticode.syphon.*;

PGraphicsOpenGL2 canvas;
SyphonServer syphon;

void setup() {
  size(400,400, OPENGL2);
  canvas = (PGraphicsOpenGL2)createGraphics(400, 400, OPENGL2);  
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