package ar.edu.unahur.obj2.impostoresPaises

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.doubles.shouldBeBetween
import io.kotest.matchers.shouldBe

class ObservatorioTest: DescribeSpec({

    describe("Creacion de Observatorio , Paises y Pruba"){

        val Argentina = Pais("Argentina","ARG", 45808747, 2780400.0, "America", "ARS", 150.00, listOf("Mercosur"),listOf("Español"))
        val Bolivia = Pais( "Bolivia", "BOL",10985059, 1098581.0,"America", "BOB", 6.89, listOf("Mercosur" ), listOf("Español", "Quechua", "Aymara"))
        val EstadosUnidos = Pais("Estados Unidos","USA",332183000, 9147593.0,"America", "USD", 1.00, listOf("NAFTA" ), listOf("Ingles"))
        val Brasil = Pais("Brasil", "BR",216738890, 8515770.0, "America","BRL", 5.42, listOf("Mercosur"), listOf("Portugues"))
        val Mexico = Pais("Mexico", "MEX",130262220 ,1959248.0 , "America", "MXN", 20.53 , listOf("AP"), listOf("Español"))

        Argentina.agregarLimitrofes(listOf(Bolivia, Brasil))
        Bolivia.agregarLimitrofes(listOf(Argentina, Brasil))
        Brasil.agregarLimitrofes(listOf(Argentina, Brasil))
        Mexico.agregarLimitrofes(listOf(EstadosUnidos))

        Observatorio.agregarPais(Argentina)
        Observatorio.agregarPais(Bolivia)
        Observatorio.agregarPais(EstadosUnidos)
        Observatorio.agregarPais(Brasil)
        Observatorio.agregarPais(Mexico)


        describe("Test Observatorio") {

            it("Probamos si son limitrofes") {
                Observatorio.sonLimitrofes("Argentina", "Brasil").shouldBeTrue()
                Observatorio.sonLimitrofes("EstadosUnidos", "Mexico").shouldBeTrue()
                Observatorio.sonLimitrofes("Mexico", "Brasil").shouldBeFalse()
                Observatorio.sonLimitrofes("Argentina", "EstadosUnidos").shouldBeFalse()
            }

            it("Probamos si necesitan traduccion") {
                Observatorio.necesitanTraduccion("Argentina", "Brasil").shouldBeTrue()
                Observatorio.necesitanTraduccion("Argentina", "Bolivia").shouldBeFalse()
                Observatorio.necesitanTraduccion("EstadosUnidos", "Mexico").shouldBeTrue()
                Observatorio.necesitanTraduccion("Mexico", "Bolivia").shouldBeFalse()
            }

            it("Probamos si son potenciales aliados") {
                Observatorio.potencialesAliados("Argentina", "Brasil").shouldBeFalse()
                Observatorio.potencialesAliados("Argentina", "Bolivia").shouldBeTrue()
                Observatorio.potencialesAliados("EstadosUnidos", "Mexico").shouldBeFalse()
                Observatorio.potencialesAliados("Argentina", "Mexico").shouldBeFalse()
            }

            it("Probamos si conviene ir de compras") {
                Observatorio.convieneIrDeCompras("Argentina", "Brasil").shouldBeFalse()
                Observatorio.convieneIrDeCompras("Brasil", "Bolivia").shouldBeTrue()
                Observatorio.convieneIrDeCompras("Mexico", "EstadosUnidos").shouldBeFalse()
                Observatorio.convieneIrDeCompras("EstadosUnidos", "Mexico").shouldBeTrue()

            }

            it("Probamos cuanto equivale un monto de un pais a otro") {
                Observatorio.aCuantoEquivale("Argentina", 200.00, "EstadosUnidos").shouldBeBetween(1.0, 2.0, 1.0)
                Observatorio.aCuantoEquivale("Argentina", 100.00, "Brasil").shouldBeBetween(3.0, 4.0, 1.0)
                Observatorio.aCuantoEquivale("Mexico", 150.00, "EstadosUnidos").shouldBeBetween(7.0, 8.0, 1.0)

            }

            it("Probamos el codigo ISO de los paises con mayor densidad poblacional") {


            }

            it("Probamos cual continente posee mas paises plurinacionales") {


            }

            it("Probamos el promedio densidad poblacional en paises insulares") {


            }

        }
    }

})