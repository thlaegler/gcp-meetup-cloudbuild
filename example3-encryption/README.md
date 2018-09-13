# Example3: Encryption


## Find the Cloud Build Service Account

Find the Cloud Build Service Account Email

```
$ export SERVICE_ACCOUNT_EMAIL "<CHANGE-ME>@cloudbuild.gserviceaccount.com"
```
or when running as Cloud Build Service Account simple run:

```
$ export SERVICE_ACCOUNT_EMAIL $(gcloud config list account --format "value(core.account)")
```

## Create KMS KeyRing and CryptoKey

```
$ gcloud kms keyrings create example3-encryption-keyring --location=global
$ gcloud kms keys create example3-encryption-key \
  --location=global \
  --keyring=example3-encryption-keyring \
  --purpose=encryption
$ gcloud kms keys add-iam-policy-binding \
    example3-encryption-key --location=global --keyring=example3-encryption-keyring \
    --member=serviceAccount:$SERVICE_ACCOUNT_EMAIL \
    --role=roles/cloudkms.cryptoKeyEncrypterDecrypte
```

## Encrypt Properties File

```
gcloud kms encrypt \
  --plaintext-file=cloudbuild.properties \
  --ciphertext-file=cloudbuild.properties.enc \
  --location=global \
  --keyring=example3-encryption-keyring \
  --key=example3-encryption-key
echo "cloudbuild.properties" >> .gitignore
```


## Decrypt Properties File

Now we can decrypt the Properties File while Cloud Build

```
kms decrypt \
	--ciphertext-file=cloudbuild.properties.enc \
	--plaintext-file=cloudbuild.properties \
	--location=global \
	--keyring=example3-encryption-keyring \
	--key=example3-encryption-key
```

## Run Cloud Build from local Machine

```
gcloud builds submit --config example3-encryption/example3-encryption-cloudbuild.yaml .
```