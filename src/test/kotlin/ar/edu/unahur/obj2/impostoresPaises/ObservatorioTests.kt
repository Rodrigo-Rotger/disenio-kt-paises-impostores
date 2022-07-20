package ar.edu.unahur.obj2.impostoresPaises

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.doubles.shouldBeBetween
import io.kotest.matchers.shouldBe

class ObservatorioTest: DescribeSpec({

    describe("Creacion de Observatorio , Paises y Pruba"){

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


        describe("Test Observatorio") {

            it("Probamos si son limitrofes") {

                observatorio?.sonLimitrofes("Argentina", "Brasil")?.shouldBeTrue()
                observatorio?.sonLimitrofes("EstadosUnidos", "Mexico")?.shouldBeTrue()
                observatorio?.sonLimitrofes("Mexico", "Brasil")?.shouldBeFalse()
                observatorio?.sonLimitrofes("Argentina", "EstadosUnidos")?.shouldBeFalse()
            }

            it("Probamos si necesitan traduccion") {
                observatorio?.necesitanTraduccion("Argentina", "Brasil")?.shouldBeTrue()
                observatorio?.necesitanTraduccion("Argentina", "Bolivia")?.shouldBeFalse()
                observatorio?.necesitanTraduccion("EstadosUnidos", "Mexico")?.shouldBeTrue()
                observatorio?.necesitanTraduccion("Mexico", "Bolivia")?.shouldBeFalse()
            }

            it("Probamos si son potenciales aliados") {
                observatorio?.potencialesAliados("Argentina", "Brasil")?.shouldBeFalse()
                observatorio?.potencialesAliados("Argentina", "Bolivia")?.shouldBeTrue()
                observatorio?.potencialesAliados("EstadosUnidos", "Mexico")?.shouldBeFalse()
                observatorio?.potencialesAliados("Argentina", "Mexico")?.shouldBeFalse()
            }

            it("Probamos si conviene ir de compras") {
                observatorio?.convieneIrDeComprasDesdeHacia("Argentina", "Brasil")?.shouldBeFalse()
                observatorio?.convieneIrDeComprasDesdeHacia("Brasil", "Bolivia")?.shouldBeTrue()
                observatorio?.convieneIrDeComprasDesdeHacia("Mexico", "EstadosUnidos")?.shouldBeFalse()
                observatorio?.convieneIrDeComprasDesdeHacia("EstadosUnidos", "Mexico")?.shouldBeTrue()

            }

            it("Probamos cuanto equivale un monto de un pais a otro") {
                observatorio?.aCuantoEquivale("Argentina", 200.00, "EstadosUnidos")?.shouldBeBetween(1.0, 2.0, 1.0)
                observatorio?.aCuantoEquivale("Argentina", 100.00, "Brasil")?.shouldBeBetween(3.0, 4.0, 1.0)
                observatorio?.aCuantoEquivale("Mexico", 150.00, "EstadosUnidos")?.shouldBeBetween(7.0, 8.0, 1.0)

            }

            it("Probamos el codigo ISO de los paises con mayor densidad poblacional") {
                var resultado = observatorio?.isoMayorDensidadPoblacional()
                //(listOf("JPN", "MEX", "USA", "BR", "ARG"))
                resultado?.first().shouldBe("JPN")
            }

            it("Probamos cual continente posee mas paises plurinacionales") {
                observatorio?.continenteMasPlurinacional().shouldBe("America")

            }
            it("Probamos el promedio densidad poblacional en paises insulares") {
                observatorio?.promedioDensidadInsulares().shouldBe(168)
            }

        }
    }

})