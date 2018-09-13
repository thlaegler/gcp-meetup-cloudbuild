# Example 8: Reporting

Building and Testing a Maven Module and Create Reporting

## Running Build from local Machine

```
gcloud builds submit --config example6-reporting/example6-reporting-cloudbuild.yaml --substitutions _VERSION=0.0.1 .
```