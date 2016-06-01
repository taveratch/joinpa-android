# Design Document

## Patterns

#### Singleton

Classes that implement singleton

- **App**
- **HttpManager**
- **InternalData**
- **NotificationReceiver**

---
#### Command 

  All classes in **managers/commands**
  
  **UnfriendCommand, CancelEventCommand and RemoveEventCommand** are implemented **Command**, because ConfirmDialog.java will execute a command so we will pass these function in type of **Command** 
  
  These are commands for making interaction with the server.

---
#### Mediator

- **Notifier**
- **DateTimeHolder**

```
When user want to select date and time from picker dialog, the ui must be updated after date 
and time are selected. So I created DateTimeHolder for holding tvDate,tvTime and setting 
date and time logics (listener).
```

---
#### Adapter

classes in **ui/adapters**

```
These adapters are accept list of any type of object (for example, List<Friend>) 
and convert it to custom view
```

---
#### Observer

most activity and fragment are observer, which updates when a response notify it.

---
#### Facade

- **LoadService** - hides the complexity of making connection to server logic.

---
#### FlyWeight

- **InternalData** - reuse icons in many activity

## Principles

#### Open-Closed

**Friend** is an extension of **User**, **User** is closed for modification.

---
#### Don't repeat yourself

use `<merge>` and `<include>` in layout to reuse components.

**item_partial_event.xml** included in **item_event_explore.xml**

**friend_list_include.xml** included in **fragment_friends.xml**

---
#### Single Responsibility

- **joinpa/models**
- **EventManager**
- **PlaceManager**
- 

---
#### Information expert

- **EventManager**
- **PlaceManager**
- **HttpManager**
- **App**

---
#### Low coupling, High cohesion

- **joinpa/util**
- **DateUtil** for formatting date, reducing the need of activity to include logic for that.
- **ProgressDialogUtil** for showing load dialog and hiding without coupling in activity or fragments


---
## Team
- Chinthiti Wisetsombat 5710546194
- Taweerat Chaiman 5710546259
- Thongrapee Panyapatiphan 5710546267

