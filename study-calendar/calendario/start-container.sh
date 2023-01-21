#! /usr/bin/env bash

#docker-compose -f authorization_server.yml down
#docker-compose -f authorization_server.yml up -d

docker-compose down
docker-compose up -d

echo 'Keycloak started !'