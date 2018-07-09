# s3 reverse proxy

This proxy contains a reverse proxy for s3. Currently it simply routes through any http request made.

## Prerequisites

The project requires a running s3 API. For this we use a CEPH docker container with S3 API.
The `docker-compose.yml` defines this, run it using:

    docker-compose up
