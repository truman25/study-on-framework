# Dockerfile
# 基于的镜像
FROM ascdc/jdk8

VOLUME /opt/tmp

ADD springbootdemo-0.0.1-SNAPSHOT.jar springbootdemo.jar

# -Djava.security.egd=file:/dev/./urandom 可解决tomcat可能启动慢的问题
# 具体可查看：https://www.cnblogs.com/mightyvincent/p/7685310.html
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/springbootdemo.jar"]

# 对外端口
EXPOSE 8080