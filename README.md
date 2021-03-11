# Privacy Modules API

Root module for the privacy tools.
This module defines all the low level APIs to monitor and manage privacy in android systems. It uses interface and abstract classes, and then should come with other modules, implementing these interfaces.
The implementations could use various technical solutions depending on the privileges levels they have in the android system.

# Modules architecture

See [here](./doc/architecture.md)



# Functionalities 

## v0.0.2

* Permissions
    * List apps
    * List app's permissions
    * List status of runtime permission
    * Set status of runtime permissions
    * List mode of appOp permissions
    * Set mode of appOp permissions

* Fake location
    * Fake the location of the device, at the specified point.

# Structure (UML class diagrams)

* v0.1 (Permissions)


```mermaid
classDiagram
    AbstractPermissionHelper o-- PemissionsCache
    AbstractPermissionHelper o-- AbstractPrivacyLeakageMeter
    AbstractPermissionHelper -- AppPermissionInfo

    AppPermissionInfo o-- AppInfo
    AppPermissionInfo o-- PermissionType

    class AbstractPermissionHelper{
        <<abstract>>
        #PermissionCache: cache
        #AbstractPrivacyLeakageMeter: privacyMeter
        +getGroupsPermissions() List~PermissionGroupInfo~
        +getPermissionsUsedInGroup(PermissionGroupInfo groupName) Pair~Integer__Integer~
        +getAppsWithPermission(PermissionGroupInfo groupName) List~AppPermissionInfo~
        +getTotalApps() int
        +getTotalPermissionsRequested() int
    }

    class PemissionsCache{
      +getGroupsPermissions()
      +getAppInfo(String packageName) AppInfo
    }

    class AbstractPrivacyLeakageMeter{
      <<abstract>>
      #permissionTimeSeries
      #tarckersTimeSeries
      +getUsedPermissions()
      +getUsedPermissions(int duration)
    }

    class AppPermissionInfo{
    <<data class>>
      +AppInfo info
      +PermissionType permissionType
      +boolean isEnabled
    }

    class AppInfo{
    <<data class>>
      +String appName
      +Drawable appIcon
    }

    class PermissionType{
    <<enumeration>>
      +NORMAL
      +DANGEROUS
      +DANGEROUS_ONE_TIME
    }

    class AbstractPermissionManager{
    <<abstract>>
    +toggleDangerousPermission()*
    +setAppOppMode()*
    }     
```
