FROM centos:7

# Install basic packages
ENV JAVA_TOOL_OPTIONS -Dfile.encoding=UTF8
ENV LANG kr_KO.UTF-8

RUN yum -y update
RUN yum install -y wget telnet nc tar vim unzip net-tools java-11-openjdk-devel


# Dstagram setup
RUN mkdir -p /boyun-stargram/operation
VOLUME ["/boyun-stargram/operation"]
EXPOSE 80 8080
WORKDIR /boyun-stargram
ADD ../run.sh /boyun-stargram/run.sh
RUN chmod 755 /boyun-stargram/run.sh
ADD nginx/nginx.conf /etc/nginx/nginx.conf

# ADD ssl/* /etc/nginx/
ADD war/*.war /boyun-stargram/

ENTRYPOINT ["/boyun-stargram/run.sh"]