#!/bin/bash
sleep 5
mongosh --host mongo1:27017 <<EOF
  var cfg = {
    "_id": "my-replica-set",
    "version": 1,
    "members": [
      {
        "_id": 0,
        "host": "mongo1:27017"
      },
      {
        "_id": 1,
        "host": "mongo2:27017"
      },
      {
        "_id": 2,
        "host": "mongo3:27017"
      }
    ]
  };
  rs.initiate(cfg);
EOF