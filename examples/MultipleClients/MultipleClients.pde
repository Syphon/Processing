// Use in combination with MultipleServers

import codeanticode.syphon.*;

int nClients = 4;
PGraphics[] canvas;
SyphonClient[] clients;

void settings() {
  size(400, 400, P3D);
  PJOGL.profile = 1;
}

void setup() {
  canvas = new PGraphics[nClients];
  for (int i = 0; i < nClients; i++) {
    canvas[i] = createGraphics(200, 200, P2D);
  }

  // Create syhpon clients to receive the frames.
  clients = new SyphonClient[nClients];
  for (int i = 0; i < nClients; i++) { 
    clients[i] = new SyphonClient(this, "MultipleServers", "Processing Syphon."+i);
  }
}

void draw() {
  for (int i = 0; i < nClients; i++) {
    if (clients[i].newFrame()) {
      canvas[i] = clients[i].getGraphics(canvas[i]);
      image(canvas[i], 200 * (i % 2), 200 * (i / 2)); 
    }
  }
}
