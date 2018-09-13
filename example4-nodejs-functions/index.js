const firebaseFunctions = require('firebase-functions');
const request = require('request');

exports.doSomething = firebaseFunctions.database.ref('/somewhere/{requestId}').onWrite(change => {
  console.log('do something.');
  return true;
});