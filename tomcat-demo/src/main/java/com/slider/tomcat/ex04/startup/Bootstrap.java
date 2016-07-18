/* explains Tomcat's default container */
package com.slider.tomcat.ex04.startup;

import com.slider.tomcat.ex04.core.SimpleContainer;
import com.slider.tomcat.ex04.core.SimpleContainer2;

import org.apache.catalina.connector.http.HttpConnector;

public final class Bootstrap {
  public static void main(String[] args) {
    HttpConnector connector = new HttpConnector();
    SimpleContainer2 container = new SimpleContainer2();
    connector.setContainer(container);
    try {
      connector.initialize();
      connector.start();

      // make the application wait until we press any key.
      System.in.read();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}