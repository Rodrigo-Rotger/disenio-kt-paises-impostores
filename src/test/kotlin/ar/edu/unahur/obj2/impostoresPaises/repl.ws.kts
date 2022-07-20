import ar.edu.unahur.obj2.impostoresPaises.Observatorio
import ar.edu.unahur.obj2.impostoresPaises.Pais
import ar.edu.unahur.obj2.impostoresPaises.api.CurrencyConverterAPI
import ar.edu.unahur.obj2.impostoresPaises.api.RestCountriesAPI
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

val observatorio= Observatorio.getInstance()

val Argentina = Pais("Argentina","ARG", 45808747, 2780400.0, "America", "ARS", 150.00, listOf("Mercosur"),listOf("Español"))
val Bolivia = Pais( "Bolivia", "BOL",10985059, 1098581.0,"America", "BOB", 6.89, listOf("Mercosur" ), listOf("Español", "Quechua", "Aymara"))
val EstadosUnidos = Pais("Estados Unidos","USA",332183000, 9147593.0,"America", "USD", 1.00, listOf("NAFTA" ), listOf("Ingles"))
val Brasil = Pais("Brasil", "BR",216738890, 8515770.0, "America","BRL", 5.42, listOf("Mercosur"), listOf("Portugues"))
val Mexico = Pais("Mexico", "MEX",130262220 ,1959248.0 , "America", "MXN", 20.53 , listOf("AP"), listOf("Español"))
val Japon = Pais("Japon", "JPN", 126167000 ,377975.0 , "Asia", "JPY", 138.25, listOf("ASEAN"), listOf("Japones", "Hiragana", "Katakana"))
val Australia = Pais("Australia", "AUS", 25739256, 7741220.0, "Oceania", "AUD", 1.45, listOf("OMC"), listOf("Ingles"))


Argentina.agregarLimitrofes(listOf(Bolivia, Brasil))
Bolivia.agregarLimitrofes(listOf(Argentina, Brasil))
Brasil.agregarLimitrofes(listOf(Argentina, Bolivia))
Mexico.agregarLimitrofes(listOf(EstadosUnidos))
EstadosUnidos.agregarLimitrofes(listOf(Mexico))

observatorio?.registrarPais(Argentina)
observatorio?.registrarPais(Bolivia)
observatorio?.registrarPais(EstadosUnidos)
observatorio?.registrarPais(Brasil)
observatorio?.registrarPais(Mexico)
observatorio?.registrarPais(Japon)
observatorio?.registrarPais(Australia)


Argentina.densidadPoblacional()
Bolivia.densidadPoblacional()
Mexico.densidadPoblacional()
EstadosUnidos.densidadPoblacional()
Japon.densidadPoblacional()
Brasil.densidadPoblacional()
Australia.densidadPoblacional()

observatorio?.isoMayorDensidadPoblacional()

observatorio?.promedioDensidadInsulares()

observatorio?.continenteMasPlurinacional()

var paissacado = observatorio?.paisPorNombre("Argentina")
paissacado?.nombre

observatorio?.sonLimitrofes("Argentina", "Brasil")
observatorio?.sonLimitrofes("EstadosUnidos", "Mexico")
observatorio?.sonLimitrofes("Mexico", "Brasil")
observatorio?.sonLimitrofes("Argentina", "EstadosUnidos")







