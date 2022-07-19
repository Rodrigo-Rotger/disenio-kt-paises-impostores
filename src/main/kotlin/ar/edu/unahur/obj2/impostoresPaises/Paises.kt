package ar.edu.unahur.obj2.impostoresPaises

import kotlin.math.roundToInt
import kotlin.math.roundToLong

class Pais (val nombre: String, val codigoISO: String, val poblacion: Int, val superficie: Double, val continente: String,
            val moneda: String, val dolar: Double, val  bloqueReg: List<String>, val idiomas: List<String>  ){

    val limitrofes: MutableList<Pais> = mutableListOf()

    fun esPlurinacional(): Boolean = idiomas.size > 1

    fun esIsla(): Boolean = limitrofes.isEmpty()

    fun densidadPoblacional(): Int = (poblacion/superficie).roundToInt()
    //// NO ES MEJOR USAR maxByOrNull???
    fun vecinoMasPoblado(): Pais {
        val vecindad = mutableListOf(this)
        vecindad.addAll(limitrofes)
        val vecindadOrdenada = vecindad.sortedByDescending { it.poblacion }

        return vecindadOrdenada.first()
    }
    fun sonLimitrofes(pais:Pais): Boolean = limitrofes.contains(pais)

    fun necesitanTraduccion(pais:Pais): Boolean = idiomas.intersect(pais.idiomas).isEmpty()
    //AGREGUE FUNCION QUEDA MAS PROLIJO
    fun sonMismoBloqueReg(pais:Pais) : Boolean = bloqueReg.intersect(pais.bloqueReg).isNotEmpty()

    fun sonPotencialesAliados(pais: Pais): Boolean =  !necesitanTraduccion(pais) && sonMismoBloqueReg(pais)

    fun convieneIrDeCompras(pais: Pais): Boolean = pais.dolar > dolar

    fun aCuantoEquivale(monto: Double, pais: Pais ): Double = (monto/dolar) * pais.dolar

    fun agregarLimitrofes(listaPaises: List<Pais>) = limitrofes.addAll(listaPaises)
}
