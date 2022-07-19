package ar.edu.unahur.obj2.impostoresPaises

class Observatorio private constructor() {

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

    //agregar pais
    fun agregarPais(pais: Pais) = paises.add(pais)

    //Obtener pais a traves del nombre
    fun paisPorNombre(nombre: String): Pais = paises.filter{ it.nombre == nombre }[0]

    fun sonLimitrofes(nombrePais1: String, nombrePais2: String): Boolean {
        val pais1 = this.paisPorNombre(nombrePais1)
        val pais2 = this.paisPorNombre(nombrePais2)

        return pais1.sonLimitrofes(pais2)
    }

    fun necesitanTraduccion(nombrePais1: String, nombrePais2: String): Boolean {
        val pais1 = this.paisPorNombre(nombrePais1)
        val pais2 = this.paisPorNombre(nombrePais2)

        return pais1.necesitanTraduccion(pais2)
    }

    fun potencialesAliados(nombrePais1: String, nombrePais2: String): Boolean {
        val pais1 = this.paisPorNombre(nombrePais1)
        val pais2 = this.paisPorNombre(nombrePais2)

        return pais1.sonPotencialesAliados(pais2)
    }

    //entendemos que el primer parametro es el pais que "consulta" si conviene ir de compras al segundo parametro
    fun convieneIrDeCompras(nombrePais1: String, nombrePais2: String): Boolean{
        val pais1 = this.paisPorNombre(nombrePais1)
        val pais2 = this.paisPorNombre(nombrePais2)

        return pais1.convieneIrDeCompras(pais2)
    }

    //entendemos que el primer parametro es el pais que "consulta" el monto en la moneda del pais del segundo parametro
    fun aCuantoEquivale(nombrePais1: String, monto:Double, nombrePais2: String ): Double {
        val pais1 = this.paisPorNombre(nombrePais1)
        val pais2 = this.paisPorNombre(nombrePais2)

        return pais1.aCuantoEquivale(monto, pais2)
    }

    fun isoMayorDensidadPoblacional(): List<String> {
        val ordenados = paises.sortedByDescending { it.densidadPoblacional() }
        val iso = ordenados.map { it.codigoISO }
        return iso.take(4)
    }

    fun continenteMasPlurinacional(): String? {
        val plurinacionales = paises.filter { it.esPlurinacional() }
        val agrupados = plurinacionales.groupingBy { it.continente }.eachCount()
        val contineneteMasPlu = agrupados.maxByOrNull { it.value }?.key

       return contineneteMasPlu
    }

    fun promedioDensidadInsulares(): Int {
        val insulares = paises.filter { it.esIsla()}
        val promedioDensidad = insulares.sumOf { it.densidadPoblacional() } / insulares.size

        return promedioDensidad
    }



}