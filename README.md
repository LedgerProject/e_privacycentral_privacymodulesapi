# Privacy Modules API

Root module forthe privacy tools.  
This module defines all the low level APIs to monitor and manage privacy in android systems. It uses interface and abstract classes, and then should come with other modules, implementing these interfaces.  
The implementations could use various technical solutions depending on the privileges levels they have in the android system.


# Functionalities 

## v0.0.1

* Permissions
    * List apps
    * List app's permissions
    * List status of runtime permission
    * Set status of runtime permissions

* Fake location
    * Set permission
    * Fake the location of the device, at the specified point.
