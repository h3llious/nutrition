# Nutrition tracker demo with Compose

| Scanner | Animated Loading indicator  | Results | Streak Badge |
| -------- | -------  | ------- | ------ |
| <image width=300 src='https://github.com/user-attachments/assets/ece708af-aa27-4dc0-b1df-0eaa4dd244cd'/>  | <image width=300 src='https://github.com/user-attachments/assets/a59d7773-d4ce-443f-a299-75a0167cbf12'/>  | <image width=300 src='https://github.com/user-attachments/assets/f21a5223-b225-4cca-a2a4-70b3ed346746'/>  | <image width=300 src='https://github.com/user-attachments/assets/942abeca-84c9-433b-92ff-ca7764037312'/> |

https://github.com/user-attachments/assets/9cbb32d0-ecb9-48a9-b464-1cf306243b43

## Setup
Project created using:
- Android Studio Ladybug
- Kotlin 2.0.21

## Features
- Architecture: MVVM, Clean architecture, multi-modules
   - Layer separation:
     - Presentation: UI & State-holder 
     - Domain: skip PresentationModel and map DomainModel to UiModel, reduce time to handle middle model
- DI: Dagger Hilt
- UI Toolkit: Jetpack Compose
- Compose navigation library
- Camera2: customized camera UI with scanner animation
- Custom Compose components: animated dashed-line circular progress indicator, multi-level circular chart
