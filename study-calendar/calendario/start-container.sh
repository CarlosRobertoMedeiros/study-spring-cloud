#! /usr/bin/env bash

docker-compose -f authorization_server.yml down
docker-compose -f authorization_server.yml up -d

echo 'Keycloak started !'