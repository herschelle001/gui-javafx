This is JavaFX version of the project "gui-swing".
Below is the configuration of incorporating JavaFX in IntelliJ IDE.
1) Download JavaFX library from https://gluonhq.com/products/javafx/
2) Extract them in a safe location such as documents which its unlikely to be deleted.
3) Open the project in IntelliJ, go to File -> Project Structure -> Global Libraries
4) Click the "+" and navigate to the location where JavaFX libraries were installed.
5) Inside JavaFX sdk folder open lib folder and select all .jar files and click OK.
6) You will see JavaFX libraries appearing in global libraries.
7) Right click and select all to modules and press Apply.

Note: If you are adding JavaFX in a new project, you will require a module-info.java file as you can see in my project.
To add it right click on src -> new -> module-info.java. 
Paste the following code in it
module Javafx {
    requires javafx.fxml;
    requires javafx.controls;

    open <YOUR PACKAGE NAME>;
}

This module-info.java needs to be in the src folder where your package is present.
