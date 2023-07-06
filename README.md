﻿# Back-end
## 프로젝트 소개
자기 일상의 운동 피드를 공유하는 플랫폼입니다.

## 👨‍💻 Contributors

| Contributor                                  |
|----------------------------------------------|
| [ssonghee](https://github.com/ssonghee)      |
| [sky-j](https://github.com/hopee0411)        |

## MariaDB Docker image download 및 mysql 동기화
(경로: project_path/docker/mariadb 로 이동)

````Bash
docker build --rm -t mariadb10.5 .
docker exec some-mysql sh -c 'exec mysqldump --all-databases -uroot -p"$MYSQL_ROOT_PASSWORD"' > /some/path/on/your/host/all-databases.sql
````

## Docker Compose 파일로 DB UP, DOWN
(경로: project_path 로 이동)
````Bash
docker-compose -f docker-compose.local.yml up
docker-compose -f docker-compose.local.yml down
````
