#!/bin/bash


usage() {
    cat <<EOF
NAME
    ${0##*/} Cloudbuild Script

SYNOPSIS
    ${0##*/} [config-map|secret|decrypt]

Options:

    For example:
        ./cloudbuild maven-build staging
        ./cloudbuild docker-build-push staging

RETURN CODES
    Returns 0 on success, 1 if an error occurs.

SEE ALSO
    See the documentation on Confluence for more details, including
    instructions on creating environments.

EOF
}

function decrypt() { 
	gsutil -m rsync -d -r gs://gcp-meetup-cloudbuild/data/ .
	kms decrypt \
		--ciphertext-file=gcp-meetup-service-account.json.enc \
		--plaintext-file=gcp-meetup-service-account.json \
		--location=global \
		--keyring=example3-encryption-keyring \
		--key=example3-encryption-key
	kms decrypt \
		--ciphertext-file=gcp-meetup.jks.enc \
		--plaintext-file=gcp-meetup.jks \
		--location=global \
		--keyring=example3-encryption-keyring \
		--key=example3-encryption-key
	kms decrypt \
		--ciphertext-file=cloudbuild.properties.enc \
		--plaintext-file=cloudbuild.properties.json \
		--location=global \
		--keyring=example3-encryption-keyring \
		--key=example3-encryption-key
}

function createK8sSecret() {
	echo "$(kubectl create secret generic gcp-meetup-secret \
		--from-literal=gitusername=$1 \
		--from-literal=gitpassword=$2 \
		--from-literal=keystorealias=$3 \
		--from-literal=keystoresecret=$4 \
		--from-literal=keystorepassword=$5 \
		--from-literal=key=$6 \
		--from-file=service-account.json=gcp-meetup-service-account.json \
		--from-file=gcp-meetup.jks=gcp-meetup.jks \
		--dry-run=true \
		-o yaml)" > "k8s-deployment/gcp-meetup-secret.yaml"
}

function createK8sConfigMap() {
	echo "$(kubectl create configmap platform-config \
		--from-env-file=cloudbuild.properties \
		--dry-run=true \
		-o yaml)" > "k8s-deployment/gcp-meetup-config.yaml"
}

if [[ $1 == "secret" ]]; then
	createK8sSecret $1 $2 $3 $4 $5 $6
elif [[ $1 == "config-map" ]]; then
	createK8sConfigMap $1 $2 $3 $4 $5 $6
elif [[ $1 == "decrypt" ]]; then
	decrypt $1 $2 $3 $4 $5 $6
else
    usage
    exit 1
fi
