package ar.edu.unahur.obj2.impostoresPaises

import kotlin.math.roundToInt
import kotlin.math.roundToLong

class Pais (val nombre: String, val CodigoISO: String, val poblacion: Int, val superficie: Double, val continenete: String,
            val moneda: String, val dolar: Double, val  bloqueReg: List<String>, val idiomas: List<String>  ){

    val limitrofes: MutableList<Pais> = mutableListOf()

    fun esPlurinacional(): Boolean = idiomas.size > 1

    fun esIsla(): Boolean = limitrofes.isEmpty()

    fun densidadPoblacional(): Int = (poblacion/superficie).roundToInt()

    fun vecinoMasPoblado(): Pais {
        val vecindad = mutableListOf(this)
        vecindad.addAll(limitrofes)
        val vecindadOrdenada = vecindad.sortedByDescending { p -> p.poblacion }

        return vecindadOrdenada.first()
    }
    fun sonLimitrofes(pais:Pais): Boolean = limitrofes.contains(pais)

    fun necesitanTraduccion(pais:Pais): Boolean = idiomas.intersect(pais.idiomas).isEmpty()

    fun sonPotencialesAliados(pais: Pais): Boolean =  !necesitanTraduccion(pais) && bloqueReg.intersect(pais.bloqueReg).isNotEmpty()

    fun convieneIrDeCompras(pais: Pais): Boolean = pais.dolar > dolar

    fun aCuantoEquivale(monto: Double, pais: Pais ): Double = (monto/dolar) * pais.dolar

    fun agregarLimitrofes(listaPaises: List<Pais>) = limitrofes.addAll(listaPaises)

}
