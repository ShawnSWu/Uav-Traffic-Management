FROM tomcat:8.5.47-jdk8-openjdk
MAINTAINER swshawnwu@gmail.com

WORKDIR /utm-system

EXPOSE 8083

ADD target/utm-0.0.1-SNAPSHOT.jar /utm-system/utm-0.0.1-SNAPSHOT

ENV TZ=Asia/Taipei
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

CMD java -jar utm-0.0.1-SNAPSHOT