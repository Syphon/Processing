import codeanticode.syphon.*;
import java.util.Iterator;
import java.util.Map;

PGraphics[] canvas = {};
SyphonClient[] clients = {};

int nClients;

void setup() {
  size(400, 400, P2D);
  frameRate(1);

  updateClients();
}

void draw() {
  if (clients.length == 0) updateClients();
  
  background(0);
  
  if (0 < nClients) {
    int targetIndex =  frameCount % nClients;
    SyphonClient targetClient = clients[targetIndex];

    if (targetClient.newFrame()) {
      canvas[targetIndex] = targetClient.getGraphics(canvas[targetIndex]);
      image(canvas[targetIndex], 0, 0, width, height);  
    }
  }
}

void keyPressed() {
  if (key == ' ') {
    updateClients();
  }
}

void updateClients() {
  HashMap<String, String>[] allServers = SyphonClient.listServers();
  println(allServers);
  
  int nServers = allServers.length;

  canvas = new PGraphics[nServers];
  clients = new SyphonClient[nServers];

  for (int i = 0; i < allServers.length; i++) {
    String appName = allServers[i].get("AppName");
    String serverName = allServers[i].get("ServerName");
    clients[i] = new SyphonClient(this, appName, serverName);
  }

  nClients = nServers;    
}