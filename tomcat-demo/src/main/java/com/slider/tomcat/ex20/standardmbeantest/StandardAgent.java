package com.slider.tomcat.ex20.standardmbeantest;

import javax.management.Attribute;
import javax.management.ObjectName;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;

public class StandardAgent {

  private MBeanServer mBeanServer = null;

  public StandardAgent() {
    mBeanServer = MBeanServerFactory.createMBeanServer();
  }

  public MBeanServer getMBeanServer() {
    return mBeanServer;
  }

  public ObjectName createObjectName(String name) {
    ObjectName objectName = null;
    try { 
      objectName = new ObjectName(name); 
    }
    catch (Exception e) {
    }
    return objectName;
  }
  

  private void createStandardBean(ObjectName objectName, String managedResourceClassName) {
    try {
      mBeanServer.createMBean(managedResourceClassName, objectName);
    } 
    catch(Exception e) {
    }
  }

  public static void main(String[] args) {
    StandardAgent agent = new StandardAgent();
    MBeanServer mBeanServer = agent.getMBeanServer();
    String domain = mBeanServer.getDefaultDomain();
    String managedResourceClassName = "com.slider.tomcat.ex20.standardmbeantest.Car";
    ObjectName objectName = agent.createObjectName(domain + ":type=" + 
      managedResourceClassName);
    agent.createStandardBean(objectName, managedResourceClassName);


    // manage MBean
    try {
      Car car1 = new Car();
      Car car2 = new Car();
      Attribute colorAttribute = new Attribute("Color","blue");
      mBeanServer.setAttribute(objectName, colorAttribute);
      System.out.println(mBeanServer.getAttribute(objectName, "Color"));
      mBeanServer.invoke(objectName,"drive",null,null);
      Car car3 = new Car();
      car1.drive();car2.drive();car3.drive();
    } 
    catch (Exception e) {
      e.printStackTrace();
    }

  }
    
    
}
