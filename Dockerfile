FROM tomcat:9.0

# Copy the war file
ADD target/mocksendgrid.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080


