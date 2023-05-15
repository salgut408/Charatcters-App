# Charatcters-App

- MVVM Clean Architecture
- Use cases (1)
- 100% Kotlin
- XML UI
- Dependacy Injections with Hilt
- Navigation Library For navigation in the app using SafeArgs
- The ui layer uses the ViewModel pattern
- Coroutines For handling async work
- Retrofit For networking tasks
- UDF & reactice programing with Kotlin Flow API
- Screen Ui State
- Data Layer, Domain Layer, Ui Layer

Each UI 'screen' has its own ViewModel, which exposes a single StateFlow containing the entire view state. 
Each ViewModel is responsible for subscribing to any data streams required for the view, as well as exposing functions which allow the UI to send events.

The ViewModel is implemented as MainViewModel, which exposes a StateFlow<UiState> for the UI to observe.
UiState contains the complete view state for the screen as a data class.
  
The Repository class is responsible for handling the data fetching of all information:


