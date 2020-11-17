package example.com.recyclerviewspinner

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import example.com.recyclerviewexample.R
import example.com.recyclerviewspinner.adapters.CustomRecyclerAdapter
import example.com.recyclerviewspinner.model.Item
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
     var banderaElegida = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // access the items of the list
        val banderas = resources.getStringArray(R.array.banderas)
        // access the spinner
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, banderas)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    banderaElegida = banderas[position]
                    Toast.makeText(this@MainActivity, getString(R.string.selected_item) + " " +"" + banderas[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

    fun leerLista(banderaElegida: String): ArrayList<Item> {
        var arrayRdo = ArrayList<Item>()

        var bufferedReaderRaw: BufferedReader = BufferedReader(
            InputStreamReader(
                resources!!.openRawResource(
                    R.raw.lista
                )
            )
        )
        if (banderaElegida.equals("TODOS")){
        bufferedReaderRaw.forEachLine { linea -> arrayRdo.add(Item(linea)) }
        }else if (banderaElegida.equals("UK")){
            bufferedReaderRaw.forEachLine { linea -> if (linea.contains("uk")){arrayRdo.add(Item(linea))} }
        }else if (banderaElegida.equals("ES")){
            bufferedReaderRaw.forEachLine { linea -> if (linea.contains("es")){arrayRdo.add(Item(linea))} }
        }
        bufferedReaderRaw.close()

        return arrayRdo
    }
    fun click(itemValue : Item){
        Toast.makeText(applicationContext, "Valor : ${itemValue.campo1 + "-" + itemValue.campo2 + "-" + itemValue.bandera}", Toast.LENGTH_LONG).show()
    }
    fun clickBandera(texto : String){
        Toast.makeText(applicationContext, "Bandera : $texto", Toast.LENGTH_LONG).show()
    }
    fun clickActLista(view: View){
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        var arrayList = leerLista(banderaElegida)
        var customAdapter = CustomRecyclerAdapter(arrayList, this){item -> click(item) }
        recyclerView.adapter = customAdapter
    }
}