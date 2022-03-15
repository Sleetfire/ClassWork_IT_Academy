FROM tomcat:8.5.76-jre8-temurin
COPY target/MK_JD2-88-2-0.0.0.war usr/local/tomcat/webapps
CMD ["catalina.sh", "run"]
