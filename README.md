# Symptom Tracker

*Work in progress*
A Kotlin Multiplatform Mobile application for tracking symptoms and notes, built with modern Android and iOS development practices.

## Features

- **Symptom Tracking**: Record symptoms with severity ratings (1-10 scale) and optional notes
- **Note Management**: Create and manage detailed notes for health observations
- **Cross-Platform**: Native Android and iOS apps with shared business logic
- **Local Storage**: SQLDelight database for offline-first data persistence
- **Firebase Integration**: Cloud sync for Android users

## Technology Stack

- **Kotlin Multiplatform Mobile (KMM)**: Shared business logic across platforms
- **Compose Multiplatform**: Modern UI framework
- **SQLDelight**: Type-safe SQL database library
- **Firebase**: Cloud services for Android
- **Clean Architecture**: Domain-driven design with repository pattern

## Architecture

The app follows Clean Architecture principles with:
- **Domain Layer**: Core business logic and entities
- **Repository Pattern**: Data abstraction layer
- **Use Cases**: Application-specific business rules
- **MVVM**: Presentation layer with ViewModels
