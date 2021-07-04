# Popular-NY-Times
Displaying Most Viewed Articles from NY Times

***This application demonstrates the use of MVVM architecture using the Android Jetpack Components***

## :rocket:	Components Used
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture)
- [Navigation Components](https://developer.android.com/guide/navigation)
- [Data Binding](https://developer.android.com/topic/libraries/data-binding)

Apart from the above Jetpack components, the following dependencies are used - 
- [Retrofit](https://square.github.io/retrofit/) networking library
- [Dagger 2](https://dagger.dev/) for dependency injection
- [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) for asynchronous operations
- [Coil](https://github.com/coil-kt/coil) for loading images
- [Mock Web Server](https://github.com/square/okhttp/tree/master/mockwebserver) by Square to mock http clients for testing
- [Timber](https://github.com/JakeWharton/timber) for logging
- [Jacoco](https://www.eclemma.org/jacoco/) for generating code coverage reports

## Prerequisites 
- Register on the [Ny Times](https://developer.nytimes.com/) and get the api key
- Add the API_KEY in the project level `build.gradle` file under `prod` `productFlavors`

## Generating reports 
Use the below command to generate Unit test code coverage report for this project 
```
./gradlew testProdDebugUnitTest
```
You will find the test coverage report in the following directory - 

> [projectDir]/app/build/reports/tests/testProdDebugUnitTest/index.html 

Open the `index.html` in your default/any browser to see the code coverage report.



## Future Scope
- [ ] Update to [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- [ ] Refactor UI elements using [JetPack Compose](https://developer.android.com/jetpack/compose)



