Applications
============

Base URL: /api/v1/{format}/applications

where

{format} is 'json' or 'xml'.

Each request requires additional parameter access_token.

Create
------

url: /
method: POST
params: entity (Application)

Update
------
    
url: /{id}
method: PUT
params: entity (Application)

Delete
------

url: /{id}
method: DELETE
params:

Find All
--------
    
url: /
method: GET
params:
example: /api/v1/json/applications?access_token={yourToken}

Find One
--------

url: /{id}
method: GET
params: