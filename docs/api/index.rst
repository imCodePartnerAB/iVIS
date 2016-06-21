API
===

iVIS provides API for data manipulation using JSON or XML format.

In general, the process looks like:

1. Authorization and obtaining token.
2. Sending requests and receiving responses.

Root for the all relative URLs is 'http://ivis.dev.imcode.com/';

Base relative URL for API: /api/v1/{format}

where

{format} is 'json' or 'xml'

Each request requires additional parameter access_token.

If method has url, it concatenates with base URL.

You can have access and operate with following entities

.. toctree::
   :titlesonly:

   authorization
   incidents
   issues
   activities
   pupils
   priorities
   categories
   statuses
   persons
   users
   schools
   schoolclasses
   applications
   applicationforms
   applicationformsteps
   applicationformquestiongroups
   applicationformquestions
   exceptionhandling