package com.example.sqlite.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlite.modelo.Alumno
import com.example.sqlite.R
import kotlinx.android.synthetic.main.template_alumno.view.*

class AdaptadorCustom(
    var items: ArrayList<Alumno>,
    val listener: ClickListener,
    var longClickListener: LongClickListener
) : RecyclerView.Adapter<AdaptadorCustom.ViewHolder>() {

    var multiSeleccion = false
    var itemsSeleccionados = ArrayList<Int>()
    lateinit var viewHolder: ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista =
            LayoutInflater.from(parent.context).inflate(R.layout.template_alumno, parent, false)
        viewHolder = ViewHolder(
            vista,
            listener,
            longClickListener
        )
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        /* if (itemsSeleccionados.contains(position)) {
            viewHolder.vista.setBackgroundColor(Color.LTGRAY)
        } else {
            viewHolder.vista.setBackgroundColor(Color.WHITE)
        }*/
        return holder.bind(items[position], listener)
    }

    override fun getItemCount() = items.size

    class ViewHolder(
        val vista: View,
        val listener: ClickListener,
        val longListener: LongClickListener
    ) :
        RecyclerView.ViewHolder(vista), View.OnClickListener, View.OnLongClickListener {
        private var lastPosition = -1

        init {
            vista.setOnClickListener(this)
            vista.setOnLongClickListener(this)
        }

        fun bind(item: Alumno, listener: ClickListener) = with(vista) {
            tvNombre.text = item.nombre
            tvId.text = item.id
        }

        override fun onClick(v: View?) {
            listener.onClick(v!!, adapterPosition)
        }

        override fun onLongClick(v: View?): Boolean {
            longListener.longClick(v!!, adapterPosition)
            return true
        }

    }

}
