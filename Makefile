NAME  	:= $(shell ./gradlew properties -q | grep "name:" | awk '{print $$2}')
TAG   	?= $(shell ./gradlew properties -q | grep "version:" | awk '{print $$2}')
USER_ID ?= $(shell stat -c "%u:%g" .)
REPO  	:= bsgfb
IMAGE 	:= ${REPO}/${NAME}:${TAG}

BRANCH_NAME ?= $(shell git rev-parse --abbrev-ref HEAD 2>/dev/null)
DOCKER_PROJECT_MODIFICATOR := $(shell echo ${BRANCH_NAME} | sed 's@origin/@@' | sed 's@/@_@')
DOCKER_COMPOSE_OPTS ?= --project-name ${NAME}-${DOCKER_PROJECT_MODIFICATOR}

DOCKER_COMPOSE ?= \
    IMAGE=${IMAGE} \
    USER_ID=${USER_ID} \
    TAG=${TAG} \
    docker-compose ${DOCKER_COMPOSE_OPTS} -f ./docker/docker-compose.build.yml

JAVA_SOURCE_FILES := $(shell find ./src -name '*.java')
GRADLE ?= ${DOCKER_COMPOSE} run --rm gradle

build:
	${GRADLE} build -x test

run:
	${DOCKER_COMPOSE} -f ./docker/docker-compose.yml up --force-recreate app

.PHONY: build run

clean-docker: DOCKER_COMPOSE_FILES := $(sort $(wildcard ./docker/docker-compose*.yml))
clean-docker: DOCKER_COMPOSE_FILES := $(patsubst %.yml,-f %.yml, ${DOCKER_COMPOSE_FILES})
clean-docker:
	${DOCKER_COMPOSE} ${DOCKER_COMPOSE_FILES} stop
	${DOCKER_COMPOSE} ${DOCKER_COMPOSE_FILES} rm --force -v
.PHONY: clean-docker
