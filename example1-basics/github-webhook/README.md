To deploy Cloud Functions run:
`gcloud beta functions deploy GithubWebhook --trigger-http --memory=128MB --entry-point=GithubWebhook`

To deploy and run the Cloud Function locally:
`functions deploy GithubWebhook --trigger-http --memory=128MB --entry-point=GithubWebhook`
`functions call GithubWebhook --data "{ \"ref\": \"refs/tags/test\", \"repository\": { \"name\": \"repository-name\", \"owner\": { \"name\": \"account-name\" } }, \"pusher\": { \"name\": \"pusher-name\" } }"`