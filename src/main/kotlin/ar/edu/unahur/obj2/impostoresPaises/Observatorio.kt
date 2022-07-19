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

    //Obtener pais
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

    //entendemos que el primer parametro es el pais que "consulta" el monto en la moneda del segundo parametro
    fun aCuantoEquivale(nombrePais1: String, monto:Double, nombrePais2: String ): Double {
        val pais1 = this.paisPorNombre(nombrePais1)
        val pais2 = this.paisPorNombre(nombrePais2)

        return pais1.aCuantoEquivale(monto, pais2)
    }

    fun ISOMayorDensidadPoblacional(): List<String> {
        val ordenados = paises.sortedByDescending { it.densidadPoblacional() }
        val iso = ordenados.map { it.codigoISO }
        return iso.take(4)
    }

    fun continenteMasPlurinacional() {
        var plurinacionales = paises.filter { p -> p.esPlurinacional() }
        var america = cantidadPorContinente("america", plurinacionales)
        var europa = cantidadPorContinente("europa", plurinacionales)
        var asia = cantidadPorContinente("asia", plurinacionales)
        var africa = cantidadPorContinente("africa", plurinacionales)
        var oceania = cantidadPorContinente("oceania", plurinacionales)

        //val list = plurinacionales.groupingBy { it.continente }.eachCount().filter { it.value > 1 }

    }

    //AUXILIAR
    fun cantidadPorContinente(nombre: String, listaPaises: List<Pais>){
        //return listaPaises.filter { it.continenete == nombre }.size
    }

}