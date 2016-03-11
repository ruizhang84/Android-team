# Design Document

**Author**: \<Team 54\>

| Version | Description     |
| --------|:---------------:|
| V1      | Initial version |

## 1 Design Considerations

### 1.1 Assumptions

The purpose of developing the Android app *TCCart* is to provide a payment and reward managment system in a timely manner to help our contractor's coffee and tea selling bussiness grow. To develop TCCart, we made the following assumptions:

- The software is Android app that runs on minimum target at API Level 15 (IceCreamSandwich).

- The software is dependent on Android utilities for printing, QR code scanning, credit card scanning,  payment processing, and email sending, which are provided by our contractor and used as-is.

- We assume the hadware is sufficient for the basic function to perform (for example, sufficient storage to save customer info). 

- We assume the payment will be processed only for credit card, no cash allowed. 

- We assume the general credit/discount rule will be strictly applied to each transactions as described, no special rule will be employed.

### 1.2 Constraints

- The current design for TCCart requires the system runs on mobile phone. Thus, the neceesary functions need to
be robust and able to handle common situations(/failures) within a mobile environment.

- The priciple of Unified Software Process will be used in designing the solution.

### 1.3 System Environment

| Requirements | Description     |
| --------|:---------------:|
| Android     | 4.0.3 and up |
| Permissions     | Photos/Media/Files, Storage, Camera, other |

## 2 Architectural Design

*The architecture provides the high-level design view of a system and provides a basis for more detailed design work. These subsections describe the top-level components of the system you are building and their relationships.*

### 2.1 Component Diagram

*This section should provide and describe a diagram that shows the various components and how they are connected. This diagram shows the logical/functional components of the system, where each component represents a cluster of related functionality. In the case of simple systems, where there is a single component, this diagram may be unnecessary; in these cases, simply state so and concisely state why.*

### 2.2 Deployment Diagram

*This section should describe how the different components will be deployed on actual hardware devices. Similar to the previous subsection, this diagram may be unnecessary for simple systems; in these cases, simply state so and concisely state why.*

## 3 Low-Level Design

*Describe the low-level design for each of the system components identified in the previous section. For each component, you should provide details in the following UML diagrams to show its internal structure.*

### 3.1 Class Diagram

*In the case of an OO design, the internal structure of a software component would typically be expressed as a UML class diagram that represents the static class structure for the component and their relationships.*

### 3.2 Other Diagrams

*<u>Optionally</u>, you can decide to describe some dynamic aspects of your system using one or more behavioral diagrams, such as sequence and state diagrams.*

## 4 User Interface Design
*For GUI-based systems, this section should provide the specific format/layout of the user interface of the system (e.g., in the form of graphical mockups).*

