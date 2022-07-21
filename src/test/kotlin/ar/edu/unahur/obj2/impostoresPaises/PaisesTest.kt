package ar.edu.unahur.obj2.impostoresPaises

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.doubles.shouldBeBetween
import io.kotest.matchers.shouldBe


class PaisesTest: DescribeSpec({

    describe("Creacion de Paises y Prueba"){

        val Argentina = Pais("Argentina","ARG", 45808747, 2780400.0, "America", "ARS", 150.00, listOf("Mercosur"),listOf("Español"))
        val Bolivia = Pais( "Bolivia", "BOL",10985059, 1098581.0,"America", "BOB", 6.89, listOf("Mercosur" ), listOf("Español", "Quechua", "Aymara"))
        val EstadosUnidos = Pais("Estados Unidos","USA",332183000, 9147593.0,"America", "USD", 1.00, listOf("NAFTA" ), listOf("Ingles"))
        val Brasil = Pais("Brasil", "BR",216738890, 8515770.0, "America","BRL", 5.42, listOf("Mercosur"), listOf("Portugues"))
        val Mexico = Pais("Mexico", "MEX",130262220 ,1959248.0 , "America", "MXN", 20.53 , listOf("AP"), listOf("Español"))
        val Japon = Pais("Japon", "JPN", 126167000 ,377975.0 , "Asia", "JPY", 138.25, listOf("ASEAN"), listOf("Japones", "Hiragana", "Katakana"))

        Argentina.agregarLimitrofes(listOf(Bolivia, Brasil))
        Bolivia.agregarLimitrofes(listOf(Argentina, Brasil))
        Brasil.agregarLimitrofes(listOf(Argentina, Brasil))
        Mexico.agregarLimitrofes(listOf(EstadosUnidos))
        EstadosUnidos.agregarLimitrofes(listOf(Mexico))

        it ("Probamos si son Plurinacionales"){
            Argentina.esPlurinacional().shouldBeFalse()
            Bolivia.esPlurinacional().shouldBeTrue()
            EstadosUnidos.esPlurinacional().shouldBeFalse()
            Brasil.esPlurinacional().shouldBeFalse()
            Japon.esPlurinacional().shouldBeTrue()
        }

        it("Probamos si son Isla"){
            Japon.esIsla().shouldBeTrue()
            Argentina.esIsla().shouldBeFalse()
        }

        it ("Densidad Poblacional"){
            Argentina.densidadPoblacional().shouldBe(16)
            Bolivia.densidadPoblacional().shouldBe(10)
        }

        it ("Vecino mas poblado") {

            Argentina.vecinoMasPoblado().shouldBe(Brasil)
            Brasil.vecinoMasPoblado().shouldBe(Brasil)

            EstadosUnidos.agregarLimitrofes(listOf(Mexico))

            EstadosUnidos.vecinoMasPoblado().shouldBe(EstadosUnidos)
            Mexico.vecinoMasPoblado().shouldBe(EstadosUnidos)
        }

        it ("Probamos si son limitrofes") {

            Argentina.sonLimitrofes(EstadosUnidos).shouldBeFalse()
            Mexico.sonLimitrofes(EstadosUnidos).shouldBeTrue()
        }

        it ("Necesitan traduccion") {

            Argentina.necesitanTraduccion(EstadosUnidos).shouldBeTrue()
            Brasil.necesitanTraduccion(EstadosUnidos).shouldBeTrue()
            Mexico.necesitanTraduccion(Argentina).shouldBeFalse()
            Bolivia.necesitanTraduccion(Argentina).shouldBeFalse()
        }
        it ("Son Potenciales aliados") {

            Argentina.sonPotencialesAliados(Mexico).shouldBeFalse()
            Argentina.sonPotencialesAliados(Brasil).shouldBeFalse()
            Argentina.sonPotencialesAliados(Bolivia).shouldBeTrue()
        }

        it ("Conviene ir de compras"){

            Argentina.convieneIrDeCompras(Brasil).shouldBeFalse()
            Brasil.convieneIrDeCompras(Bolivia).shouldBeTrue()
            Mexico.convieneIrDeCompras(EstadosUnidos).shouldBeFalse()
            EstadosUnidos.convieneIrDeCompras(Mexico).shouldBeTrue()
        }

        it ("A cuanto equivalen un monto de dinero en otro pais"){

            Argentina.aCuantoEquivale(200.0, EstadosUnidos).shouldBeBetween(1.0, 2.0, 1.0)
            Argentina.aCuantoEquivale(100.0, Brasil).shouldBeBetween(3.0, 4.0, 1.0)
            Mexico.aCuantoEquivale(150.0, EstadosUnidos).shouldBeBetween(7.0, 8.0, 1.0)
        }
    }

})