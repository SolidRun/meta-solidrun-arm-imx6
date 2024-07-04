FROM ubuntu:22.04

# Set the default shell to bash
SHELL ["/bin/bash", "-c"]

# Create and set the working directory
WORKDIR /workspace

# Install necessary packages and dependencies for Yocto Project builds.
# This includes all the recommended packages listed in the Yocto Project Quick Start
# guide for a Ubuntu-based host, as of my last update.

ENV DEBIAN_FRONTEND noninteractive

# Build tools
RUN apt-get update && apt-get install -y apt-transport-https \
    gawk wget git diffstat \
    unzip texinfo gcc build-essential chrpath \
    socat cpio python3 python3-pip python3-pexpect \
    xz-utils debianutils iputils-ping python3-git \
    python3-jinja2 libegl1-mesa libsdl1.2-dev \
    python3-subunit mesa-common-dev patchelf \
    zstd liblz4-tool file locales libacl1 \
    tree sudo locales ca-certificates \
    bash icecc

RUN locale-gen en_US.UTF-8

RUN update-ca-certificates

# Set environment variables
ENV LANG=en_US.UTF-8 \
    LANGUAGE=en_US:en \
    LC_ALL=en_US.UTF-8

# Arguments to pass host user's UID and GID
ARG USER_ID=1000
ARG GROUP_ID=1000

# Create a user 'developer' with the same UID/GID as the host user
RUN groupadd -g ${GROUP_ID} developer
RUN useradd -m -u ${USER_ID} -g developer -d /home/developer developer
RUN mkdir -p /workspace
RUN chown -R developer:developer /workspace
RUN mkdir -p /home/developer/.ssh
RUN touch /home/developer/.ssh/known_hosts
RUN chmod 700 /home/developer/.ssh
RUN chmod 600 /home/developer/.ssh/known_hosts
RUN chown -R developer:developer /home/developer/.ssh


RUN git config --add --system user.email "developer@example.com"
RUN git config --add --system user.name "Build Container"
RUN git config --add --system http.version HTTP/1.1

# The entry point is set to bash. This means that when the container starts, it will drop the user into bash
ENTRYPOINT ["/bin/bash"]
