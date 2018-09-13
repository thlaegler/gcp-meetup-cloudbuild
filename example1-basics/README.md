# Example 1: Basics

## When using Storage - give Access to Service Account

```
$ PROJECT="$(gcloud projects describe $(gcloud config get-value core/project -q) --format='get(projectNumber)')"
$ gcloud projects add-iam-policy-binding $PROJECT --member=serviceAccount:$PROJECT@cloudbuild.gserviceaccount.com --role=roles/storage.objectAdmin
```

## Using Helm

Depending on your setup Helm/Tiller will refuse to deploy your artifacts if RBAC is disabled. The following command fix that issue:

```
$ kubectl create serviceaccount --namespace kube-system tiller
$ kubectl create clusterrolebinding tiller-cluster-rule --clusterrole=cluster-admin --serviceaccount=kube-system:tiller
$ kubectl patch deploy --namespace kube-system tiller-deploy -p '{"spec":{"template":{"spec":{"serviceAccount":"tiller"}}}}'
```