#!/bin/bash

# Stop application containers
docker stop spring-boot-jobmanager-app

# Prune dangling images
docker image prune -f

# Build images with no cache
docker-compose build --no-cache

# Bring up your Docker Compose setup
docker-compose up -d