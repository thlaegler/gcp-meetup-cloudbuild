#!/bin/bash


usage() {
    cat <<EOF
NAME
    ${0##*/} Cloudbuild Script

SYNOPSIS
    ${0##*/} ISSUE_ID

Options:

    For example:
        ./report-to-jira ABC-123

RETURN CODES
    Returns 0 on success, 1 if an error occurs.

SEE ALSO
    See the documentation on Confluence for more details, including
    instructions on creating environments.

EOF
}


function commentJiraIssue() {
	ISSUE_ID="$1"
	ASSIGNEE="mister-x"
	REPORTER="miss-y"
	USER_BASIC_AUTH="my-user-token"
	JIRA_BASE_URL="https://<my-account>.atlassian.net"
	
	echo "{ \"body\": \"Hi [~$ASSIGNEE], \n Hi [~$REPORTER], \n $comment \n This feature has been deployed \" }" > comment.json
	curl -i \
		-H "Accept: application/json" \
		-H "Authorization: Basic $USER_BASIC_AUTH" \
		-H "Content-Type:application/json" \
		-d "$(cat comment.json)" "$JIRA_BASE_URL/rest/api/2/issue/$ISSUE_ID/comment"
}

function messageToSlackChannel() { 
	CHANNEL="1$"
	TOKEN="<my-slack-token>"
	MESSAGE="This branch has been deployed: $BRANCH_NAME"

	curl -X POST -H 'Content-type: application/json' \
		"https://slack.com/api/chat.postMessage?token=$TOKEN&channel=$CHANNEL&text=$MESSAGE&pretty=1"
}

if [[ $1 == "jira" ]]; then
	commentJiraIssue $2
elif [[ $1 == "slack" ]]; then
	messageToSlackChannel $2
else
    usage
    exit 1
fi
