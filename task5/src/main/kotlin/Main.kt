package org.example

abstract class Animal(var height : Double, var id : Int) {
    var warden : Warden? = null
    abstract fun makeSound(): String?
}

class Cat(height: Double, id: Int) : Animal(height, id) {
    override fun makeSound() = "Я кот"
}

class Dog(height : Double, id : Int) : Animal(height, id){
    override fun makeSound() = "Я собака"
}

class Hippo(height : Double, id : Int) : Animal(height, id) {
    override fun makeSound() = "Я бегемот"
}

class Horse(height : Double, id : Int) : Animal(height, id) {
    override fun makeSound() = "Я лошадь"
}

class Fish(height : Double, id : Int) : Animal(height, id) {
    override fun makeSound() = null
}

class Warden(var name : String, var id : Int) {
    var animals = mutableListOf<Animal>()
}

class Zoo {
    var animals = mutableSetOf<Animal>()
    var wardens = mutableSetOf<Warden>()

    // 1
    constructor()

    // 2
    constructor(animalList: List<Animal>) {
        animals.addAll(animalList)
    }

    // 3
    fun addAnimal(animal : Animal) = animals.add(animal)

    // 4
    fun findAnimalById(animalId : Int) = animals.find { it.id == animalId }

    fun deleteAnimalById(animalId : Int) = animals.removeIf { it.id == animalId }

    // 5
    fun assignWardenToAnimal(animalId : Int, warden: Warden) : Unit {
        val chosenAnimal = findAnimalById(animalId) ?: return
        if (!wardens.contains(warden)) wardens.add(warden)
        chosenAnimal.warden = warden
        warden.animals.add(chosenAnimal)
    }

    // 6
    fun getWardenAnimals(wardenId : Int) : List<Animal>? {
        val chosenWarden = wardens.find { it.id == wardenId } ?: return null
        return chosenWarden.animals
    }

    // 7
    fun getAnimalsByWardenName(wardenName : String) : List<Animal> =
        wardens.filter { it.name == wardenName }.flatMap { it.animals }

    // 8
    fun getAnimalsByHeight(minHeight : Double) : List<Animal> =
        animals.filter { it.height > minHeight }

    // 9
    fun getAnimalsBySound() : List<Animal> =
        animals.filter { it.makeSound() != null }

    // 10
    inline fun <reified T> getAnimalsByType() : List<T> = animals.filterIsInstance<T>()

}

fun main() {
    // тут какие-то небольшие тесты:
    println("Zoo1:")
    var zoo1 = Zoo()
    var cat = Cat(10.2, 1)
    var dog = Dog(9.2, 2)
    var fish = Fish(0.5, 3)
    zoo1.addAnimal(cat)
    zoo1.addAnimal(fish)
    println("Zoo1: getAnimalsByType<Cat>:")
    println(zoo1.getAnimalsByType<Cat>())

    var animalList = listOf(cat, dog, fish)

    var zoo2 = Zoo(animalList)
    println("Zoo2: getAnimalsBySound:")
    println(zoo2.getAnimalsBySound())

    zoo2.deleteAnimalById(2)
    println("Zoo2: getAnimalsBySound (after delete id2):")
    println(zoo2.getAnimalsBySound())


    var warden = Warden("Mr. Warden", 777)
    zoo2.assignWardenToAnimal(1, warden)
    zoo2.assignWardenToAnimal(3, warden)
    println("Zoo2: getWardenAnimals:")
    println(zoo2.getWardenAnimals(777))

    println("Zoo2: getAnimalsByWardenName:")
    println(zoo2.getAnimalsByWardenName("Mr. Warden"))

    println("Zoo2: getAnimalsByHeight:")
    println(zoo2.getAnimalsByHeight(10.0))


}