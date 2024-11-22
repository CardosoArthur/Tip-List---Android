package br.com.fiap.arthur_cardoso_rm93186.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.arthur_cardoso_rm93186.R
import br.com.fiap.arthur_cardoso_rm93186.model.TipModel

class TipsAdapter (private val onItemClicked: (TipModel) -> Unit): RecyclerView.Adapter<TipsAdapter.TipViewHolder>() {

    private var tips = mutableListOf<TipModel>()
    private var tipsFiltred = mutableListOf<TipModel>()

    init {
        tipsFiltred = tips
    }

    inner class TipViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val title = view.findViewById<TextView>(R.id.itemTitle)
        val description = view.findViewById<TextView>(R.id.itemDescription)
        val item = view.findViewById<LinearLayout>(R.id.item)

        fun bind(tip: TipModel) {

            title.text = tip.title

            description.text = tip.description

            item.setOnClickListener {Toast.makeText(itemView.context,"Título: ${tip.title}\nDescrição: ${tip.description}", Toast.LENGTH_LONG).show()}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tip, parent, false)
        return TipViewHolder(view)
    }

    override fun getItemCount(): Int = tipsFiltred.size

    override fun onBindViewHolder(holder: TipViewHolder, position: Int) {
        val tip = tipsFiltred[position]
        holder.bind(tip)
    }

    fun addTip(newTip: TipModel) {
        tips.add(newTip)
        notifyItemInserted(tips.size - 1)
    }

    fun updateTips(newItems: MutableList<TipModel>) {
        tips = newItems
        tipsFiltred = newItems
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        tipsFiltred = if (query.isEmpty()) {
            tips
        } else {
            tips.filter {
                it.title.contains(query, ignoreCase = true) || it.description.contains(query, ignoreCase = true)
            }.toMutableList()
        }
        notifyDataSetChanged()
    }


}