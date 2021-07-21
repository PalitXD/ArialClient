# Arial Client Development
## Setup
To setup the project, run `gradlew setupDecompWorkspace` and `gradlew genIntelliJRuns` to generate all project files.
## Common problems
Sometimes the task `genIntelliJRuns` fails.
To workaround this, create a dummy run configuration and run the task again. Usually this fixes it and you can delete the dummy configuration afterwards.