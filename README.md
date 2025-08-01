# Android Boilerplate - MVVM Clean Architecture

A complete Android boilerplate project built with modern Android development practices and libraries.

## Features

- **MVVM Clean Architecture** with clear separation of concerns
- **Hilt** for dependency injection
- **Retrofit** for API calls with OkHttp logging
- **Coroutines & Flow** for asynchronous operations
- **Data Binding** for UI binding
- **Glide** for image loading with binding adapters
- **Fragment-based navigation** with FragmentUtils helper
- **Single Activity Architecture** hosting multiple fragments

## Architecture

The project follows Clean Architecture principles with three main layers:

### Presentation Layer (`ui/`)
- **Activities & Fragments**: UI components
- **ViewModels**: Handle UI logic and state management
- **Binding Adapters**: Custom data binding adapters

### Domain Layer (`domain/`)
- **Models**: Business entities
- **Repository Interfaces**: Define data contracts
- **Use Cases**: Business logic implementation
- **Result Wrapper**: State management (Success, Error, Loading, Idle)

### Data Layer (`data/`)
- **Models**: API request/response models
- **Repository Implementations**: Data source implementations
- **Remote**: API service interfaces and implementations

## Project Structure

```
app/src/main/java/com/example/androidboilerplate/
├── AndroidBoilerplateApplication.kt
├── MainActivity.kt
├── data/
│   ├── model/
│   │   ├── LoginRequest.kt
│   │   └── LoginResponse.kt
│   ├── remote/
│   │   └── AuthApiService.kt
│   └── repository/
│       └── AuthRepositoryImpl.kt
├── domain/
│   ├── model/
│   │   ├── Result.kt
│   │   └── User.kt
│   ├── repository/
│   │   └── AuthRepository.kt
│   └── usecase/
│       └── LoginUseCase.kt
├── di/
│   ├── NetworkModule.kt
│   └── RepositoryModule.kt
├── ui/
│   ├── base/
│   │   └── BaseFragment.kt
│   ├── login/
│   │   ├── LoginFragment.kt
│   │   └── LoginViewModel.kt
│   └── home/
│       └── HomeFragment.kt
└── utils/
    ├── FragmentUtils.kt
    └── BindingAdapters.kt
```

## Key Components

### Result Wrapper
```kotlin
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
    object Loading : Result<Nothing>()
    object Idle : Result<Nothing>()
}
```

### FragmentUtils
Helper class for fragment transactions with backstack management:
- `addFragment()`: Add fragment to container
- `replaceFragment()`: Replace current fragment
- `popBackStack()`: Navigate back
- `clearBackStack()`: Clear entire backstack

### BaseFragment
Abstract base class providing:
- View binding setup
- Common lifecycle management
- Template methods for setup and observation

### Binding Adapters
Custom data binding adapters for Glide image loading:
- `imageUrl`: Basic image loading
- `circleImageUrl`: Circular image loading
- `imageUrlWithPlaceholder`: Image loading with placeholder

## Dependencies

### Core Libraries
- **Hilt**: Dependency injection
- **Retrofit**: HTTP client for API calls
- **OkHttp**: HTTP client with logging
- **Coroutines**: Asynchronous programming
- **Flow**: Reactive streams
- **Data Binding**: UI binding
- **Glide**: Image loading

### Architecture Components
- **ViewModel**: UI state management
- **LiveData**: Observable data holder
- **Fragment**: UI components
- **ConstraintLayout**: Modern layout system

## Setup Instructions

1. **Clone the repository**
2. **Open in Android Studio**
3. **Sync Gradle files**
4. **Update API base URL** in `NetworkModule.kt`
5. **Run the application**

## Usage

### Adding New Features

1. **Create API Service** in `data/remote/`
2. **Define Repository Interface** in `domain/repository/`
3. **Implement Repository** in `data/repository/`
4. **Create Use Case** in `domain/usecase/`
5. **Add ViewModel** in `ui/[feature]/`
6. **Create Fragment** in `ui/[feature]/`
7. **Add Hilt modules** if needed

### Navigation

Use `FragmentUtils` for fragment navigation:
```kotlin
FragmentUtils.replaceFragment(
    activity = activity,
    fragment = HomeFragment(),
    containerId = R.id.fragmentContainer,
    tag = "HomeFragment",
    addToBackStack = false
)
```

### State Management

ViewModels expose Flow for state management:
```kotlin
private val _loginState = MutableStateFlow<Result<User>>(Result.Idle)
val loginState: StateFlow<Result<User>> = _loginState
```

Collect in fragments using lifecycleScope:
```kotlin
viewLifecycleOwner.lifecycleScope.launch {
    viewModel.loginState.collectLatest { result ->
        when (result) {
            is Result.Loading -> { /* Show loading */ }
            is Result.Success -> { /* Handle success */ }
            is Result.Error -> { /* Handle error */ }
            is Result.Idle -> { /* Handle idle state */ }
        }
    }
}
```

## Configuration

### API Configuration
Update the base URL in `NetworkModule.kt`:
```kotlin
.baseUrl("https://your-api-url.com/")
```

### Build Configuration
The project uses:
- **Java 11** for compilation
- **Android API 34** as target
- **Minimum SDK 28**
- **Data Binding** enabled

## Testing

The project includes basic test setup with:
- **JUnit** for unit tests
- **Espresso** for UI tests
- **Test runners** configured

## Contributing

1. Follow the existing architecture patterns
2. Use proper naming conventions
3. Add appropriate documentation
4. Include tests for new features

## License

This project is licensed under the MIT License. 