# Example 2: Pipeline

## Prepare Kubernetes Cluster and Service Account

```
$ gcloud container clusters create gcp-meetup-cluster \
      --zone australia-southeast1-a \

$ PROJECT="$(gcloud projects describe \
    $(gcloud config get-value core/project -q) --format='get(projectNumber)')"

$ gcloud projects add-iam-policy-binding $PROJECT \
    --member=serviceAccount:$PROJECT@cloudbuild.gserviceaccount.com \
    --role=roles/container.developer

$ gcloud container clusters get-credentials gcp-meetup-cluster --zone australia-southeast1-a --project gcp-meetup-akl
```

## Running without a Trigger

Without a Build Trigger or for debugging purpose you can also run:

```
/git/gcp-meetup-cloudbuild$ gcloud builds submit --config example2-pipeline/example2-pipeline-cloudbuild.yaml .
$ gcloud builds log efbf8cbe-109e-4adb-97b9-e3fb85b6103d --stream
```

## Using Helm

When using Helm in Cloud Build, it (Tiller Client) should've been initialized before:

```
helm init
```