package com.alc_challenge_android;

import android.content.Context;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ConnectionDetector {
  public static boolean isInternetAvailable() {

    String host = "www.google.com";
    int port = 80;
    Socket socket = new Socket();

    try {
      socket.connect(new InetSocketAddress(host, port), 2000);
      socket.close();
      return true;
    } catch (IOException e) {
      try {
        socket.close();
      } catch (IOException es) {}
      return false;
    }
  }
}
