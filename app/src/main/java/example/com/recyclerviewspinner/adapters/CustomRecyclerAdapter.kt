package example.com.recyclerviewspinner.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import example.com.recyclerviewspinner.MainActivity
import example.com.recyclerviewexample.R
import example.com.recyclerviewspinner.model.Item


class CustomRecyclerAdapter(val itemList: ArrayList<Item>, private val actividad: MainActivity, private val click : (Item)-> Unit) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MiViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.custom_item_list,
            parent,
            false
        )
        val viewHolder = MiViewHolder(v)
        viewHolder.actividad = actividad
        return viewHolder
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MiViewHolder, position: Int, payloads: MutableList<Any>) {
        holder.bindItems(itemList[position])
        holder.itemView.setOnClickListener{click(itemList[position])}
    }


    class MiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bandera: ImageView
        val campo1: TextView
        val campo2: TextView
        lateinit var actividad: MainActivity

        init {
            bandera = itemView.findViewById(R.id.bandera) as ImageView
            campo1 = itemView.findViewById<TextView>(R.id.campo1)
            campo2 = itemView.findViewById(R.id.campo2) as TextView
        }

        fun bindItems(item: Item) {
            val bandera = itemView.findViewById(R.id.bandera) as ImageView
            val campo1 = itemView.findViewById<TextView>(R.id.campo1)
            val campo2 = itemView.findViewById(R.id.campo2) as TextView

            campo1.text = item.campo1
            campo2.text = item.campo2

            if (item.bandera.equals("uk")) {
                bandera.setImageResource(R.drawable.uk_flag)
                bandera.setOnClickListener{actividad.clickBandera("uk")}
            } else {
                bandera.setImageResource(R.drawable.es)
                bandera.setOnClickListener{actividad.clickBandera("es")}
            }
        }
    }

    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        holder.bindItems(itemList[position])
    }
}
