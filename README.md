# TrovoApiJava
 
## Adding to your own project

# Maven
Add the repository:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```  
  
Add the dependency:
```xml
<dependency>
    <groupId>com.github.casterlabs</groupId>
    <artifactId>trovoapijava</artifactId>
    <version>1.1.1</version> <!-- Update as needed -->
</dependency>
```

# Gradle
Add the repository:
```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```  
  
Add the dependency:
```gradle
dependencies {
  implementation 'com.github.casterlabs:trovoapijava:1.1.1' // Update as needed.
}
```
