FROM oracle/serverjre:8 as final
ADD target/bcaf-solace-messaging-*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
