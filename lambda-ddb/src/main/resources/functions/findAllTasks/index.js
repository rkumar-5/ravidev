'use strict';
console.log('Loading function');

const AWS = require('aws-sdk');
const docClient = new AWS.DynamoDB.DocumentClient({region: 'us-west-2'});

exports.handler = (event, context, callback) => {


    let scanningParameters = {
        TableName: 'tasks',
        Limit: 100
    };
    
    
    docClient.scan(scanningParameters, function(err, data){
        if(err){
            callback(err, null);
        }
        else{
            callback(null, data);
        }
    });
    
    
    
    
    
    /* Query
     * Scanning for all can be expensive and this uses the 
     * primary key to query for select records.
     * 
     * var params {
     *      TableName: 'tasks',
     *      Key: {
     *          'description': '1488907103608'
     *          'priority': 0
     *      }
     * }
     */
    
    
    /*
     * Results from the Scan 
     * 
     * {
  "Items": [
    {
      "completed": 1488907528163,
      "user": "ravi_kumar@msn.com",
      "priority": 0,
      "description": "new Task"
    },
    {
      "completed": "\"\"",
      "user": "ravi@msn.com",
      "priority": 0,
      "description": "\"awesome task\""
    }
  ],
  "Count": 2,
  "ScannedCount": 2
 }
     */
    
};