
Calculator Build Instructions

Create calculator.service BndOSGi Project
   a) Select Project Layout: maven
   b) Select template: Component Development (OSGi DS)
   c) Add calculator.service source code
   d) In bnd.bnd change the bundle from Private Package to Export Package

Create calculator BndOSGi Project
   a) Select Project Layout: maven
   b) Select template: Component Development (OSGi DS)
   c) Add calculator source code
   d) Add Activator in bnd.bnd 
   e) Add project to Export Package (for testing)
   f) Setup maven
      1) Add a pom.xml file
      2) Update with build information at end of this document
      3) Run "mvn install"

   f) Add external 3rd party bundles 
          - calculator : org.apache.commons.lang.math (for calculator)
          - calculator.service.rpn.mul : org.apache.commons.math3 (for calculator.service.rpn.avg)
      Steps:
      1) Using m2 to download the 3rd-party bundles
      2) Drag-n-drop bundles into Repositories Local
      3) Add the bundles to the component in bnd.bnd Build Path
      4) Add package to Build Path in bnd.bnd
   g) In bnd.bnd Build Path change default OSGi environment (for Dependency Injection (Declarative Services)):
        - osgi.core-4.0.1.to osgi.core-5.0.0
        - osgi.cmpn-4.2.0 to osgi.cmpn-5.0.0

Create calculator.service.rpn.* services BndOSGi Project
  - calculator.service.rpn.add
  - calculator.service.rpn.avg
  - calculator.service.rpn.mul (requires org.apache.commons.math3)
  - calculator.service.rpn.sub
  - other services as needed

   For each project:
   a) select Project Layout: maven
   b) Select template: Component Development (OSGi DS)
   c) Add calculator.service source code
   d) Change the from Private Package to Export Package
   e) Add Activator in bnd.bnd 
   f) In bnd.bnd Build Path change default OSGi environment (for Dependency Injection (Declarative Services)):
          - osgi.core-4.0.1.to osgi.core-5.0.0
          - osgi.cmpn-4.2.0 to osgi.cmpn-5.0.0
   g) For each project add calculator.service to Build Path
   
Create a application build using BndOSGi Project type Run Descriptor
  a) Set parent folder to calculator
  b) Set name to RunCalculator
  c) Set Run Configuration Template to Apache Felix 4
  d) In RunCalculator set Auto-resolve on save to true
  e) Set OSGi Framework to org.apache.felix.framework version4.4.1
  f) Set Execution Env to JavaSE-1.8
  g) Add Run Requirement Bundles
      - org.apache.felix.scr
      - calculator.service
      - calculator
      - calculator.service.rpn.add
      - calculator.service.rpn.avg
      - calculator.service.rpn.mul
      - calculator.service.rpn.sub
  h) Save make sure all bundles resolve OK.

-------------------------------------------------------------------------------------------------------

Useful Links:

Main Felix Web Site
http://felix.apache.org/

Felix Source
https://github.com/apache/felix

Felix Issues
https://issues.apache.org/jira/browse/FELIX/?selectedTab=com.atlassian.jira.jira-projects-plugin:summary-panel

Felix Bundle Plugin for Maven
http://felix.apache.org/site/apache-felix-maven-bundle-plugin-bnd.html

-------------------------------------------------------------------------------------------------------

Troubleshooting

bnd.bnd
  -runtrace: true

-------------------------------------------------------------------------------------------------------

Appendix - Use of Maven in BndTools

BndtoolsMavenEclipseToolchain
http://wiki.osgi.org/wiki/BndtoolsMavenEclipseToolchain

Note: Possible discontinuation of current toolchain (see link below)
      Sunset bnd-maven-plugin experiment #629
      https://github.com/bndtools/bnd/issues/629

Calculator Maven pom.xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>org.foo.bar</groupId>
  <artifactId>OSGiBundleProject</artifactId>
  <version>1.0.0-SNAPSHOT</version>
  <packaging>bundle</packaging>

  <build>
    <plugins>
      <plugin>
        <groupId>biz.aQute.bnd</groupId>
        <artifactId>bnd-maven-plugin</artifactId>
        <version>1.0.2</version>
        <extensions>true</extensions>
      </plugin>
    </plugins>
  </build>
</project>
