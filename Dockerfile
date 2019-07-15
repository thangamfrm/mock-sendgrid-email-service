FROM jetty
MAINTAINER Thangam <thangamfrm@gmail.com>

# Copy the war file
ADD target/mocksendgrid.war /var/lib/jetty/webapps/ROOT.war

EXPOSE 8080


