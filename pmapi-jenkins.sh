#!/usr/bin/env bash
app_name='pmapi'
docker stop ${app_name}
echo '——stop container——'
docker rm ${app_name}
echo '——rm container——'
docker run -p 8888:8888 --name ${app_name} \
--link mysql:db \
-v /etc/localtime:/etc/localtime \
-v /var/app/${app_name}/logs:/var/logs \
-d harbor.doughit.cn:9090/bbs/${app_name}:latest
echo '——start container——'