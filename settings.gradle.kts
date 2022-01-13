rootProject.name = "gym-kotlin"

include("gym")
project(":gym").projectDir = File("src/gym")

include("shared")
project(":shared").projectDir = File("src/shared")
