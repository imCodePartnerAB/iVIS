ApplicationFormQuestionGroup
============================

Base URL: /api/v1/{format}/applicationformquestiongroups

where

{format} is 'json' or 'xml'.

Each request requires additional parameter access_token.

Create
------

url: /
method: POST
params: entity (ApplicationFormQuestionGroup)

Update
------
    
url: /{id}
method: PUT
params: entity (ApplicationFormQuestionGroup)

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
example: /api/v1/json/applicationformquestiongroups?access_token={yourToken}

Find One
--------

url: /{id}
method: GET
params:

Find by Name
------------

url: /
method: GET
params: name (String)