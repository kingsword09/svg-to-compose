# SVG to Compose

Converts SVG or Android Vector Drawable to Compose code.

- [Android Studio/IntelliJ plugin](https://plugins.jetbrains.com/plugin/18619-svg-to-compose) by [overpass](https://github.com/overpas)

## Features

- Convert SVG files to Compose Vector code
- Convert Android Vector Drawables to Compose code
- Generate icon packs with group support
- Kotlin Scripting support for automation
- Generate accessor functions for all icons

## Use cases

- **Dynamic SVG manipulation**: Generate and modify SVG source code programmatically
- **Icon pack creation**: Build icon libraries similar to Material Icons for Compose ([compose-icons](https://github.com/DevSrSouza/compose-icons) is built with SVG to Compose)
- **Asset migration**: Convert existing SVG/Vector drawable assets to Compose

## How it works

The project uses Android's [Svg2Vector](https://android.googlesource.com/platform/tools/base/+/master/sdk-common/src/main/java/com/android/ide/common/vectordrawable/Svg2Vector.java) to convert SVG to Android Drawables and uses a customized material icon code generator from the Jetpack Compose [source code](https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:compose/material/material/icons/generator/) to generate the source code.

## Usage

### Method 1: Kotlin Scripting

Create a script file `convert-icons.main.kts`:

```kotlin
@file:Repository("https://repo1.maven.org/maven2/")
@file:DependsOn("io.github.kingsword09:svg-to-compose:0.1.0")
@file:DependsOn("com.android.tools:sdk-common:31.13.0")
@file:DependsOn("com.squareup:kotlinpoet:2.0.0")
@file:DependsOn("org.ogce:xpp3:1.1.6")

import io.github.kingsword09.svg2compose.Svg2Compose
import io.github.kingsword09.svg2compose.VectorType
import java.io.File

val assetsDir = File("assets")
val srcDir = File("src/main/kotlin")

Svg2Compose.parse(
    applicationIconPackage = "com.myapp.icons",
    accessorName = "AppIcons",
    outputSourceDirectory = srcDir,
    vectorsDirectory = assetsDir,
    type = VectorType.SVG, // or VectorType.DRAWABLE for Android Vector Drawables
    allAssetsPropertyName = "AllIcons"
)
```

Run with: `kotlin convert-icons.main.kts`

### Method 2: Gradle Dependency

Add to your `build.gradle.kts`:

```kotlin
dependencies {
    implementation("io.github.kingsword09:svg-to-compose:0.1.0")
}
```

![](https://i.imgur.com/f7txCag.png)

Generating code by executing `kotlin convert-icons.main.kts`

![](https://i.imgur.com/5UTmT70.png)

**Using in code**: `AppIcons.MyIcon`

![](https://i.imgur.com/YAriDvV.png)

### SVG Files

For SVG files, use `VectorType.SVG`.

### Icon Pack Generation

For Icon Packs, subgroups are supported: `IconPack/group/icon.svg`

![](https://i.imgur.com/cunhmxl.png)

```kotlin
val assetsDir = File("linea-icons")
val srcDir = File("src/main/kotlin")

Svg2Compose.parse(
    applicationIconPackage = "com.myapp.icons",
    accessorName = "LineaIcons",
    outputSourceDirectory = srcDir,
    vectorsDirectory = assetsDir,
    type = VectorType.SVG,
    iconNameTransformer = { name, group -> name.removePrefix(group) },
    allAssetsPropertyName = "AllIcons"
)
```

**Using in code**: `LineaIcons.Arrows.ButtonUp`

The project also generates an accessor for all your assets. For the given example, it generates `LineaIcons.AllIcons` and also for child groups `LineaIcons.Arrows.AllIcons`

## API Reference

### Svg2Compose.parse() Parameters

- `applicationIconPackage`: Package name for generated icons (e.g., "com.myapp.icons")
- `accessorName`: Name of the main accessor object (e.g., "AppIcons")
- `outputSourceDirectory`: Output directory for generated Kotlin files
- `vectorsDirectory`: Directory containing SVG/Vector drawable files
- `type`: `VectorType.SVG` for SVG files, `VectorType.DRAWABLE` for Android Vector Drawables
- `iconNameTransformer`: Optional function to transform icon names
- `allAssetsPropertyName`: Name for the property containing all icons (e.g., "AllIcons")

## Requirements

- Kotlin 2.0.0+
- Java 17+

## Dependencies

The library uses these dependencies:
- `com.android.tools:sdk-common:31.13.0` - Android SVG to Vector conversion
- `com.squareup:kotlinpoet:2.0.0` - Kotlin code generation
- `org.ogce:xpp3:1.1.6` - XML parsing
