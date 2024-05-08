package com.example.corrutinas

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.lang.Thread.sleep
import kotlin.random.Random
import kotlin.system.measureTimeMillis

data class Persona(val nombre: String, val edad: Int)

fun retornarPersona(): Flow<Persona> = flow {
    val lista = listOf(
        Persona("diego", 53),
        Persona("juan", 33),
        Persona("ana", 33)
    )
    for (elemento in lista) {
        delay(1000)
        emit(elemento)
    }
}

fun main(args: Array<String>) {
 //EJERCICIO 1
    log("Inicio del programa");
    GlobalScope.launch {
        log("Inicio de la corrutina")
        for(x in 1..10) {
            print("$x -")
            delay(1000) //función de suspensión
        }
    }
    log("Se bloquea el hilo principal del programa al llamar a readLine")
    readLine()

// // EJERCICIO 2
//    GlobalScope.launch {
//        log("Inicio de la corrutina 1")
//        for(x in 1..10) {
//            print("$x ")
//            delay(1000)  //sleep(1000)
//        }
//    }
//    GlobalScope.launch {
//        log("Inicio de la corrutina 2")
//        for(x in 11..20) {
//            print("$x ")
//            sleep(1000) //delay(1000)
//        }
//    }
//    readLine()

// //EJERCICIO 3
//    log("Ejecución en el hilo principal. Adivina el número entre 1 y 100 ")
//    val numero = Random.nextInt(1, 100)
//    var inicio = 1
//    var fin = 100
//    GlobalScope.launch {
//        var valor:Int
//        log("Inicio de la corrutina adivinadora")
//        do {
//            valor = Random.nextInt(inicio, fin)
//            println(valor)
//            if (valor == numero)
//                println("En número es el $valor")
//            else
//                if (valor < numero) {
//                    println("El numero es mayor")
//                    inicio = valor
//                } else {
//                    println("El numero es menor")
//                    fin = valor
//                }
//            delay(500)
//        } while (valor != numero)
//    }
//    readLine() //detenemos el hilo principal del programa

// //EJERCICIO 4
//    //Para probar esto hay que añadir " = runBlocking" en la función main
//    log("Running in the main thread")
//    launch {
//        delay(1000)
//        log("Paso un segundo")
//        }
//    log("After runBlocking")

//    //Con RunBlocking
//    //Prueba a ejecutar en el scope prinpical. Quitar el runBlocking de la función main
//    log("Running in the main thread")
//    runBlocking {
//        launch {
//            delay(1000)
//            log("Paso un segundo")
//        }
//    }
//    log("After runBlocking")


// //Refactorización de funciones.
//    log("Running in the main thread")
//    runBlocking {
//        launch {
//            log("En el hilo de la corrutina")
//            espera() //Esta debera ser una función suspendida por estar dentro de una corrutina
//        }
//    }
//    log("After runBlocking")

// //La corrutinas son livianas.
//    log("Running in the main thread")
//    runBlocking {
//        for (x in 1..100000)
//            launch {
//                delay(1000)
//                print(".")
//            }
//    }
//    log("After runBlocking")

//////Manejador que retorna launch
//    log("Running in the main thread")
//    runBlocking {
//        val corrutina1=launch {
//            log("En la corrutina 1")
//            delay(1000)
//            log("Pasó un segundo")
//        }
//        //corrutina1.join()
//        val corrutina2=launch {
//            log("En la corrutina 2")
//            delay(1000)
//            log("Pasó otro segundo")
//        }
//        //corrutina2.join()
//    }
//    log("After runBlocking")

////runBlocking y coroutineScope
//    runBlocking {
//        Tareas(1)
//        Tareas(2)
//        log("Fin de todas las tareas")
//    }
//

//// Funciones de suspensión (suspend fun)
//    runBlocking {
//        val d1=dato1()
//        log("Fin de la primera función de suspensión")
//        val d2=dato2()
//        log("Fin de la segundo función de suspensión")
//        println(d1+d2)
//    }

//// LLamadas concurrentes
//    runBlocking {
//        val tiempo1 = System.currentTimeMillis()
//        val corrutina1=async {
//            log("Iniciando la corrutina 1")
//            dato1()
//        }
//        val corrutina2=async {
//            log("Iniciando la corrutina 2")
//            dato2()
//        }
//        println(corrutina1.await()+corrutina2.await())
//        val tiempo2 = System.currentTimeMillis()
//        println("Tiempo total ${tiempo2-tiempo1} ms")
//    }

//// Lazily started async
//   runBlocking {
//       val time = measureTimeMillis {
//           val one = async(start = CoroutineStart.LAZY) {
//               log("Iniciando la corrutina 1")
//               doSomethingUsefulOne()
//           }
//           val two = async(start = CoroutineStart.LAZY) {
//               log("Iniciando la corrutina 2")
//               doSomethingUsefulTwo()
//           }
//           // some computation
//           one.start() // start the first one
//           two.start() // start the second one
//           println("The answer is ${one.await() + two.await()}.")
//       }
//       println("Completed in $time ms")
//    }

////  //CREAR SECUENCIAS
//    //Crear una secuencia con SequenceOf
//    val sequeceOfInt = sequenceOf(1, 2, 3, 4, 5);
//
//    //Crear una secuencia a partir de otra colección
//    val strNumbers = listOf("uno",  "dos", "tres", "cuatro", "cinco")
//    val sequence = strNumbers.asSequence()
//
//    //Filtramos,sin utilizar asSequence, los elementos de strNumbers que tienen más de 3 letras
//    val strNumbersSize= strNumbers
//        .filter { it.length > 3 } //esto crea una nueva lista
//        .map{it.length} //esto crea otra nueva lista
//
//    //Filtramos, utilizando asSequence, los elementos de strNumbers que tienen más de 3 letras
//    val strNumbersSize2= strNumbers
//        .asSequence() //esto crea una secuencia
//        .filter { it.length > 3 } //esto no crea una nueva lista, sino que opera sobre la secuencia
//        .map{it.length} //esto no crea una nueva lista, sino que opera sobre la secuencia
//        .toList() //esto convierte la secuencia final en una lista.
//    //Con esta forma hemos evitado crear dos listas intermedias

//////  //CREAR SECUENCIAS CON generateSequence
//    val oddNumbers = generateSequence(1) { it + 2 } //Empieza en 1 y va sumando 2
//    //esto me genera una secuencia infinita de números impares
//
//    //pero la usuaré para extraer de ella los que me interesen.
//    //extraerermos los que no sean modulo de 3 y que al convertirlo a string su longitud sea menor o igual que 3
//    //pero solo los numeros del 1 al 99 primeros
//    val oddNumbers2 = generateSequence(1) { it + 2 }
//        .filter { it % 3 != 0 }
//        .map { it.toString() }
//        .filter { it.length <= 3 }
//        .take(99)
//        .toList()
//
//    println(oddNumbers2)

//////  //CREAR SECUENCIAS CON sequence y yield
//    val seq = sequence {
//           yield(3)
//    }
//    println(seq.toList())
//
//    //Ahora vamos a hacer una secuencia que genere los números del 1 al 5
//    val seq2 = sequence {
//        for (i in 1..5) {
//            yield(i)
//        }
//    }
//    println(seq2.toList())
//
//    //Aqui generamos una secuencia que genere los números del 1 al 5 y luego los del 6 al 10
//    //utilizando yieldAll que lo que hace es añadir una lista de elementos a la secuencia
//    val seq3 = sequence {
//        for (i in 1..5) {
//            yield(i)
//        }
//        yieldAll(listOf(6, 7, 8, 9, 10))
//    }
//    println(seq3.toList())
//
//    //Aqui generamos una secuencia que genere los números del 1 al 5 y luego los del 6 al 10
//    //utilizando yieldAll que lo que hace es añadir una lista de elementos a la secuencia
//    val seq4 = sequence {
//        var last:Int=0
//        for (i in 1..5) {
//            yield(i)
//            last=i;
//        }
//        //Añadimos con yieldAll la secuencia de numeros impares a partir del último valor de la anterior
//        yieldAll(generateSequence(last+2) { it + 2 })
//    }
//    println(seq4.take(10).toList())


//// FLOWS EN KOTLIN
//    runBlocking {
//        retornarPersona().collect(){
//            log("${it.nombre} ${it.edad}")
//        }
//        log("Después del collect")
//    }

//// FLOWS EN KOTLIN llamada asíncrona
//    runBlocking {
//        val diferido =  async {
//            retornarPersona().collect(){ //it:Persona
//                log("${it.nombre} ${it.edad}")
//            }
//        }
//        log("Después del async")
//    }


////asFlow()
//runBlocking {
//    makeFlow().collect(){
//        log(it.toString())
//    }
//    //Otra forma de llamar de forma que para cada elemento se ejecuta una función
//    //es utilizar una referencia a la función en la llamada.
//    makeFlow().collect(::println)
//}

////Generando un Flow a partir de bucles
//    runBlocking {
//        makeFlow2().collect(){
//            log(it.toString())
//        }
//    }


////Generando un Flow
//    runBlocking {
//        makeFlow3().collect(){
//            log("Recibido: " + it.toString())
//        }
//    }

////Operaciones Intermedias
//    runBlocking {
//        makeFlow3()
//            .filter { it % 2 == 0  } //Nos quedamos solo con los pares
//            .map{
//                "Obtenido el par: $it"
//            }
//            .collect(){
//                log(it.toString())
//                }
//    }

////StateFlow
//    runBlocking {
//        val viewState = ViewState()
//        launch{
//            viewState.startUpdating()
//        }
//        delay(5000) //Comenzamos a recolectar pasados 5 segundos
//        viewState.state.collect(::println)
//    }
//

    println("Fin del main")
} //fin del Main

//----------------------------------------------------------

fun log(message: String) {
    println("[${Thread.currentThread().name}] : $message")
}

fun log(character: Char) {
    print("$character")
}
suspend fun espera() {
    delay(1000) //Esto también es una función de suspensión
    println("Pasó un segundo")
}

suspend fun Tareas(nro:Int) {
    coroutineScope {
        launch {
            log("Tarea $nro parte A. iniciando...")
            delay(1000)
            log("Tarea $nro parte A. finalizada")
        }
        launch {
            log("Tarea $nro parte B. iniciando...")
            delay(2000)
            log("Tarea $nro parte B. finalizada")
        }
        log("Esperando finalizar las dos partes de las tareas $nro")
    }
}

suspend fun dato1(): Int {
    delay(3000)
    return 3
}

suspend fun dato2(): Int {
    delay(3000)
    return 3
}

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}

fun makeFlow(): Flow<Int> {
    return listOf(1,2,3,4,5).asFlow()
}

fun makeFlow2(): Flow<Int> {
    return flow<Int>{
        for (i in 1..10){
            emit(i)
        }
    }
}

fun makeFlow3(): Flow<Int> {
    return flow<Int>{
        for (i in 1..20){
            log("Pedimos datos al servidor")
            val data = GetAsyncData()
            log("  Lo tenemos, vamos a emitir: $data")
            emit(data)
        }
    }
}

suspend fun GetAsyncData(): Int {
    return withContext(Dispatchers.IO){
        //simulamos la ejecución en el servidor
        delay(2000)
        Random.nextInt(1, 100)
    }
}

class ViewState(){
    private val _state = MutableStateFlow(1)
    val state
        get() = _state

    suspend fun startUpdating(){
        while(true){
            delay(2000)
            _state.value = _state.value +1
        }
    }
}