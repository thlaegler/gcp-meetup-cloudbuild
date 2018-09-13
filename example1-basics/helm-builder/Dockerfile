FROM gcr.io/cloud-builders/gcloud

ENV HELM_VERSION 2.11.0
ENV HELM_FILENAME helm-v${HELM_VERSION}-linux-amd64.tar.gz

RUN apt-get update && apt-get install --no-install-recommends -y \
  ca-certificates \
  wget \
  curl \
  rsync \
  vim \
  jq \
  && rm -rf /var/tmp/* \
  && rm -rf /var/lib/apt/lists/* \
  && rm -rf /var/cache/apt/archives/* \
  && wget https://storage.googleapis.com/kubernetes-helm/${HELM_FILENAME} -P /tmp \
  && tar -zxvf /tmp/${HELM_FILENAME} -C /tmp \
  && mv /tmp/linux-amd64/helm /usr/local/bin \
  && rm -rf /tmp/* \
  && git config --global core.editor vim

COPY /entrypoint.sh /

ENTRYPOINT ["/entrypoint.sh"]