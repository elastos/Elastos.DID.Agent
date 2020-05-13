Elastos.DID.Agent
==============

The Elastos.DID.Agent is mainly used to help you create your did, update your did or deactivate your did without the need to generate the transaction yourself.
you only need to create the did document then call the service api

# API

## Send DID Document to Did sidechain

### request url : 

`/api/v1/did/agent/payload/pretty`

### method :

`POST`

### request Data :
```js
{
    // data is the did document 
    // must 
    "data":{
        "header":{
            "specification":"elastos/did/1.0",
            "operation":"create"
        },
        "payload":"eyJpZCI6ImRpZDplbGFzdG9zOmlhY2VTWlNBNDFnaHQ0ZGdQZ2ZEajg1WjJvdHJKRFlMS2MiLCJwdWJsaWNLZXkiOlt7ImlkIjoiI3ByaW1hcnkiLCJwdWJsaWNLZXlCYXNlNTgiOiJmODd6NXhWUFBiMXBUS0h2WUdwRE0yZFdoUGlZRzR2bllIOTZ4NkhleDgyWCJ9XSwiYXV0aGVudGljYXRpb24iOlsiI3ByaW1hcnkiXSwiZXhwaXJlcyI6IjIwMjUtMDUtMTNUMDc6MDY6NTRaIiwicHJvb2YiOnsiY3JlYXRlZCI6IjIwMjAtMDUtMTNUMDc6MDY6NTRaIiwic2lnbmF0dXJlVmFsdWUiOiJfSl9BSU1kZFhFbTBfYkwxTHFtRF9UcUxXODdGWERja0dpNVpmSmZIN1RtQ2RpZm9SOTJuUnlpUVhqUUIxNlJKelh1UnNjVlhmMXZTTl9XR2FJUVEzUSJ9fQ",
        "proof":{
            "type":"ECDSAsecp256r1",
            "verificationMethod":"did:elastos:iaceSZSA41ght4dgPgfDj85Z2otrJDYLKc#primary",
            "signature":"qSNV0FJ5VdyUdBrcIrGyBNEvgQC8b1wmxVXKSE6urwe7ZZ-_jNvjAf3GS9JbqupKa0ATMfOpwvbRygyk2dlC3Q"
        }
    },
    
    // extra address to bind with the transaction
    // optional
    "address":"EaA6CXrtvG1CpethsoodvdTwpeCtWprZzH"
}
```


### response data :

```js
{
    "result": "3120dbb54a13eb8c5e1f71ed6dfa4666470bb7893c2690a5c55bdde95901ecf2",
    "status": 200
}
```

