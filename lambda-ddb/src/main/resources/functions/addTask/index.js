'use strict';
console.log('Loading function');

const AWS = require('aws-sdk');
const docClient = new AWS.DynamoDB.DocumentClient({region: 'us-west-2'});

exports.handler = (event, context, callback) => {


    var params = {
        Item: {
            description: 'new Task',
            priority: 0,
            completed: Date.now(),
            user: "ravi_kumar@msn.com"
        },
        
        TableName: 'tasks'
    };
    
    
    docClient.put(params, function(err, data){
        if(err){
            callback(err, null);
        }
        else{
            callback(null, data);
        }
    });
    
    
};