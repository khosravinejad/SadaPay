# SadaPay Take-Home Exercise

## Running the app

To quickly run the app you don't need any specific task. The project is written in Android Studio
Electric Eel | 2022.1.1 Patch 2.

## Project structure and architecture

The application follows the Clean architecture pattern with MVVM (Model-View-ViewModel) as the
architectural style. The project is organized into the following modules:

- **data**: This module handles data-related tasks such as data models, data mappers, data sources,
  and repository implementations.

- **domain**: The domain module contains entities, repository interfaces, mappers, and use case
  implementations. It encapsulates complex business logic and provides data to the presentation
  layer.

- **presentation**: This module is responsible for implementing the ViewModel and related
  components. The UI module utilizes these components to retrieve the required data. For simplicity,
  presentation models are used in the UI module. However, for more complex scenarios, separate UI
  models can be implemented.

- **app**: This module handles dependency injection using Hilt and merges other modules.

- **ui**: The UI module provides UI composable functions to create screens.

## Tech Stack and Libraries

The application utilizes the following technologies and libraries:

- **Kotlin**: The primary programming language used in the project.

- **Coroutines**: A Kotlin library for handling asynchronous programming, simplifying code
  execution.

- **Flow**: Flow is used for asynchronous data streaming, allowing the passing of data that can be
  computed asynchronously.

- **Dagger-Hilt**: A dependency injection library for Android that simplifies dependency management.

- **JetPack**: Jetpack is a suite of libraries and tools provided by Google for Android app
  development. The following Jetpack components are used in the project:

    - **Compose**: Jetpack Compose is the recommended toolkit for building native UI in Android
      applications.

    - **Lifecycle**: The Lifecycle component is used to observe lifecycle events of activities or
      fragments and perform actions in response to these events.

    - **ViewModel**: ViewModel is used to store UI-related data that persists across configuration
      changes.

    - **Room**: Room is an SQLite object mapping library used for local data storage. It serves as
      the Single Source of Truth for cached data.

    - **Navigation**: The Navigation component is used for navigating between screens in the
      application.

    - **Coil**: Coil is an image loading library for Android that utilizes Kotlin Coroutines.

## Notes

- Due to time limitations and to adhere to the TDD (Test-Driven Development) approach, the
  implemented tests serve as samples. More tests should be added to cover critical code paths and
  ensure comprehensive test coverage.
- Due to unexpected circumstances, including my illness last week, there was a slight delay in the
  project's completion. To ensure timely submission, I have included the project's zip file without
  the dark mode feature. However, I assure you that I will promptly add the dark mode feature to the
  GitHub repository asap. If it's deemed unacceptable, please disregard the Github commits related
  to the dark mode feature.
- You can also find the git history here
  in [Github Repository](https://github.com/khosravinejad/SadaPay/commits/main). The
  repository is hidden. If you need to make it public or share it with you, please let me know.

I appreciate your time in reviewing the solution and hope you find this information useful. If you
have any further
questions about any part, please don't hesitate to reach out.