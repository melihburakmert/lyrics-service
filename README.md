# Lyrics Service

A microservice used to fetch lyrics for a given artist and track.

## Tech Stack

- Kotlin + Spring Boot
- Google Cloud PubSub
- OVH Lyrics API
- Docker
- Gradle
- Modulith Architecture
- Event Driven Architecture

## Endpoints

- GET `/lyrics?artist=Darude&track=Sandstorm` - Fetch lyrics of Darude - Sandstorm

## Running in Docker locally:

- Build the image \
  `mvn jib:dockerBuild`
- Deploy the image to Docker \
  `docker-compose up -d lyrics-service`

### TODO:

- [ ] Publish to PubSub topic 'lyrics'
- [ ] Add tests
- [ ] Add CI/CD
- [ ] Deploy on Kubernetes