import codeanticode.syphon.*;
import java.util.Iterator;
import java.util.Map;

PGraphics[] canvas;
SyphonClient[] clients;

int nClients;

void settings() {
  size(400, 400, P2D);
  PJOGL.profile=1;
}

void setup() {
  frameRate(1);


  HashMap<String, String>[] allServers = SyphonClient.listServers();

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

void draw() {

  background(0);

  int targetIndex =  frameCount % nClients;
  SyphonClient targetClient = clients[targetIndex];

  if (targetClient.newFrame()) {
    canvas[targetIndex] = targetClient.getGraphics(canvas[targetIndex]);
    image(canvas[targetIndex], 0, 0, width, height);  
  }
}