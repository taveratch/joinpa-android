# Design Document

## Patterns

#### Singleton

- **App**
- **HttpManager**
- **InternalData**

#### Command 

  All classes in **managers/Commands**

#### Mediator

- **Notifier**
- **DateTimeHolder**

#### Adapter

classes in **ui/adapters**

#### Observer

#### Facade

- **LoadService**

#### FlyWeight

- **InternalData**

## Principles

#### Open-Closed

**Friend** is an extension of **User**, **User** is closed for modification.

#### Don't repeat yourself

use `<merge>` and `<include>` in layout to reuse components.

**item_partial_event** included in **item_event_explore**


#### Information expert

- **EventManager**
- **PlaceManager**
- **HttpManager**

#### Low coupling, High cohesion
- **DateUtil** for formatting date, reducing the need of activity to include logic for that.

- **ProgressDialogUtil** for showing load dialog and hiding without coupling in activity or fragments

## Team
- Chinthiti Wisetsombat
- Taweerat Chaiman
- Thongrapee Panyapatiphan

