#!/bin/bash


HELM_REPO_NAME=gcp-meetup-akl
HELM_REPO_URL=https://gcp-meetup-cloudbuild.storage.googleapis.com/

set -e

echo "Updating kubeconfig"
sed -i '/cmd-/d' /workspace/.kube/config

echo "Running: helm init --client-only"
helm init --client-only

# check if repo values provided then add that repo
if [[ -n $HELM_REPO_NAME && -n $HELM_REPO_URL ]]; then
  echo "Adding chart helm repo $HELM_REPO_URL "
  helm repo add $HELM_REPO_NAME $HELM_REPO_URL
fi

echo "Running: helm repo update"
helm repo update

#
exec "$@"