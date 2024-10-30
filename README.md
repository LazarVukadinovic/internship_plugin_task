# IntelliJ Plugin: Create Package Action

This plugin for IntelliJ adds a custom action to the **Tools** menu, allowing users to create a new package within the currently selected project directory. 
When invoked, the action prompts the user for a package name and creates the specified package, ensuring proper syntax and structure.

## Features

- **Interactive Package Creation**: Prompts the user to enter a package name and creates the package structure in the selected project directory.
- **Syntax Validation**: Ensures valid package naming conventions (`^[a-zA-Z_][a-zA-Z0-9]*$`) to prevent invalid package structures.
- **IntelliJ Platform Integration**: Utilizes the IntelliJ Platform API for seamless integration into the IDE.

## Code Overview

### Main Components

- **CreatePackageAction.kt**: Contains the main logic for the action, including:
  - Displaying an input dialog to receive a package name from the user.
  - Validating the package name syntax.
  - Creating the package within the selected directory.
- **plugin.xml**: Registers the action with the IntelliJ Platform, placing it under the **Tools** menu.

### Key Methods

- **`actionPerformed`**: Invoked when the action is triggered, displays the input dialog, validates input, and creates the package.
- **`isValidPackageName(name: String): Boolean`**: Validates that the entered name matches standard package naming conventions.
- **`createPackage(directory: PsiDirectory, packageName: String)`**: Creates the package structure within the provided directory.

## Installation

1. Clone this repository:
   ```bash
   git clone https://github.com/your-username/create-package-action.git
   cd create-package-action
2. Open the project in IntelliJ.
3. Configure the **IntelliJ Platform SDK** if not already set up.
4. Build and run:
- Run the project from IntelliJ to start the plugin within a development IDE environment.

## Usage

1. In IntelliJ, go to the **Tools** menu and select **Create Package Action**.
2. Enter a valid package name in the prompt.
3. The package structure will be created in the selected directory.

## Example

1. Select a directory in your project structure.
2. Trigger **Tools > Create Package Action**.
3. The package structure will be created in the selected directory.
4. Enter a package name like `com.example.myplugin`.
5. The package `com.example.myplugin` is created within the selected directory.
