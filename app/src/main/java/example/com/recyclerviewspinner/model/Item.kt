package example.com.recyclerviewspinner.model

class Item {
    var campo1: String = ""
    var campo2: String = ""
    var bandera: String = ""

    constructor(linea: String) {
        var campos = linea.split('#')
        this.campo1 = campos.get(0)
        this.campo2 = campos.get(1)
        this.bandera = campos.get(2)
    }
}