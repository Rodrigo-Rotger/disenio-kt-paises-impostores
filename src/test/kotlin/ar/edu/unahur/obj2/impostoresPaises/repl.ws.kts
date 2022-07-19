import ar.edu.unahur.obj2.impostoresPaises.Pais
import ar.edu.unahur.obj2.impostoresPaises.api.CurrencyConverterAPI
import ar.edu.unahur.obj2.impostoresPaises.api.RestCountriesAPI



val Argentina = Pais("Argentina","ARG", 45808747, 2780400.0, "Europa", "ARS", 150.00, listOf("Mercosur"),listOf("Español"))
val Bolivia = Pais( "Bolivia", "BOL",10985059, 1098581.0,"Europa", "BOB", 6.89, listOf("Mercosur" ), listOf("Español", "Quechua", "Aymara"))
val EstadosUnidos = Pais("Estados Unidos","USA",332183000, 9147593.0,"Asia", "USD", 1.00, listOf("NAFTA" ), listOf("Ingles"))
val Brasil = Pais("Brasil", "BR",216738890, 8515770.0, "America","BRL", 5.42, listOf("Mercosur"), listOf("Portugues"))

Argentina.agregarLimitrofes(listOf(Bolivia, Brasil))
Bolivia.agregarLimitrofes(listOf(Argentina, Brasil))
Brasil.agregarLimitrofes(listOf(Argentina, Brasil))

Argentina.vecinoMasPoblado()

EstadosUnidos.vecinoMasPoblado()

val list = mutableListOf(Argentina, Bolivia, EstadosUnidos, Brasil)
//val listaPlurinacionales = list.filter { p -> p.esPlurinacional()}
val ordenados = list.groupBy { p->p.continente }

val prueba = list.groupingBy { it.continente }.eachCount()

val prueba2 = prueba.maxByOrNull { it.value }

//ordenados
prueba

prueba2