FROM mariadb:10.5

ARG DEBIAN_FRONTEND=noninteractive

RUN apt-get update && apt-get install -y --no-install-recommends apt-utils

## 시간 동기화
RUN mv /etc/localtime /etc/localtime_org
RUN ln -snf /usr/share/zoneinfo/Asia/Seoul /etc/localtime
RUN echo "Asia/Seoul" > /etc/timezone

## 한글
RUN apt-get install -y locales
RUN sed -i -e 's/# ko_KR.UTF-8 UTF-8/ko_KR.UTF-8 UTF-8/' /etc/locale.gen && \
    echo 'LANG="ko_KR.UTF-8"'>/etc/default/locale && \
    dpkg-reconfigure --frontend=noninteractive locales && \
    update-locale LANG=ko_KR.UTF-8
ENV LANG ko_KR.UTF-8 

ADD custom.cnf /etc/mysql/conf.d/

RUN chmod 644 /etc/mysql/conf.d/custom.cnf

#docker build --rm -t mariadb10.5 .
#docker exec some-mysql sh -c 'exec mysqldump --all-databases -uroot -p"$MYSQL_ROOT_PASSWORD"' > /some/path/on/your/host/all-databases.sql