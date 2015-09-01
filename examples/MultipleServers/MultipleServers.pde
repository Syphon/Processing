// Use in combination with MultipleClients

import codeanticode.syphon.*;

int nServers = 4;
PGraphics[] canvas;
SyphonServer[] servers;
color[] colors;

void settings() {
  size(400, 400, P3D);
  PJOGL.profile = 1;
}

void setup() {
  canvas = new PGraphics[nServers];
  for (int i = 0; i < nServers; i++) {
    canvas[i] = createGraphics(200, 200, P3D);
  }
  colors = new color[4];
  colors[0] = color(255, 0, 0);
  colors[1] = color(0, 255, 0);
  colors[2] = color(0, 0, 255);
  colors[3] = color(255, 0, 255);

  // Create syhpon servers to send frames out.
  servers = new SyphonServer[nServers];
  for (int i = 0; i < nServers; i++) { 
    servers[i] = new SyphonServer(this, "Processing Syphon."+i);
  }
}

void draw() {
  for (int i = 0; i < nServers; i++) {
    canvas[i].beginDraw();
    canvas[i].background(colors[i]);
    canvas[i].lights();
    canvas[i].translate(canvas[i].width/2, canvas[i].height/2);
    canvas[i].rotateX(frameCount * 0.01);
    canvas[i].rotateY(frameCount * 0.01);  
    canvas[i].box(50);
    canvas[i].endDraw();
    image(canvas[i], 200 * (i % 2), 200 * (i / 2));
    servers[i].sendImage(canvas[i]);    
  }    
}
