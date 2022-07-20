package ar.edu.unahur.obj2.impostoresPaises

class Observatorio() {

    companion object{
        private var instance: Observatorio? = null

        fun getInstance(): Observatorio? {
            if(instance == null){
                instance = Observatorio()
            }
            return instance
        }
    }
    val paises: MutableList<Pais> = mutableListOf()

    fun registrarPais(pais: Pais) = paises.add(pais)

    //FUNCION PARA OBTENER EL PAIS POR "NOMBRE"
    fun paisPorNombre(nombre: String): Pais = paises.first { it.nombre == nombre }

    fun sonLimitrofes(nombrePais1: String, nombrePais2: String): Boolean {
        val pais1 = paisPorNombre(nombrePais1)
        val pais2 = paisPorNombre(nombrePais2)

        return pais1.sonLimitrofes(pais2)
    }

    fun necesitanTraduccion(nombrePais1: String, nombrePais2: String): Boolean {
        val pais1 = paisPorNombre(nombrePais1)
        val pais2 = paisPorNombre(nombrePais2)

        return pais1.necesitanTraduccion(pais2)
    }

    fun potencialesAliados(nombrePais1: String, nombrePais2: String): Boolean {
        val pais1 = paisPorNombre(nombrePais1)
        val pais2 = paisPorNombre(nombrePais2)

        return pais1.sonPotencialesAliados(pais2)
    }

    fun convieneIrDeComprasDesdeHacia(nombrePais1: String, nombrePais2: String): Boolean{
        val pais1 = paisPorNombre(nombrePais1)
        val pais2 = paisPorNombre(nombrePais2)

        return pais1.convieneIrDeCompras(pais2)
    }

    //entendemos que el primer parametro es el pais que "consulta" el monto en la moneda del pais del segundo parametro
    fun aCuantoEquivale(nombrePais1: String, monto:Double, nombrePais2: String ): Double {
        val pais1 = paisPorNombre(nombrePais1)
        val pais2 = paisPorNombre(nombrePais2)

        return pais1.aCuantoEquivale(monto, pais2)
    }

    fun isoMayorDensidadPoblacional(): List<String> {
        val ordenados = paises.sortedByDescending { it.densidadPoblacional() }
        val iso = ordenados.map { it.codigoISO }
        return iso.take(5)
    }

    fun continenteMasPlurinacional(): String? {
        val plurinacionales = paises.filter { it.esPlurinacional() }
        val agrupados = plurinacionales.groupingBy { it.continente }.eachCount()

       return agrupados.maxByOrNull { it.value }?.key
    }

    fun promedioDensidadInsulares(): Int {
        val insulares = paises.filter { it.esIsla()}

        return insulares.sumOf { it.densidadPoblacional() } / insulares.size
    }



}