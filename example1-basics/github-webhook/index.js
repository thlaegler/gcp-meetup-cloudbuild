'use strict';

const request = require("request");
//const JSONPath = require('JSONPath');

exports.interceptPushEvent = (req, res) => {
  //var gitBranchRef = JSONPath({path: '$.ref', json: req.body);
  request.post('https://cloudbuild.googleapis.com/v1/projects/<your_project_id>/triggers/<trigger_id>:run', (err, response) => {
    if (!err && response.statusCode === 200) {
    	res.status(200).send('Github Webhook Push Event intercepted');
    } else {
    	res.status(200).send('Failed to intercept Webhook Push Event');
    }
  });
};