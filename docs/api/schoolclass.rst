List of properties from SchoolClass
===================================

        #. id (NUMBER)
        #. name (STRING)
        #. school (OBJECT)
           type of `School <http://docs.ivis.se/en/latest/api/school.html>`_
        #. pupils (ARRAY)
           type of `Pupil <http://docs.ivis.se/en/latest/api/pupil.html>`_
        #. diaries (ARRAY)
           type of `Diary <http://docs.ivis.se/en/latest/api/diary.html>`_
        #. school_day_start (STRING)
        #. school_day_end (STRING)

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
       "id":10,
       "name":"",
       "school_day_start":"08:00:00",
       "school_day_end":"15:00:00",
       "school":{  },
       "pupils":[  ],
       "diaries":[  ],
       "diary_list":[  ]
    }