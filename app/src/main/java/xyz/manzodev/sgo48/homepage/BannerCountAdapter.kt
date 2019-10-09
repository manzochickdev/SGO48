package xyz.manzodev.sgo48.homepage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import xyz.manzodev.sgo48.R
import xyz.manzodev.sgo48.databinding.BannerCountLayoutBinding

class BannerCountAdapter(var count : Int,var context:Context) : RecyclerView.Adapter<BannerCountAdapter.ViewHolder>() {
    var selected =0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.banner_count_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return count
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.isSelected = selected==position
        holder.binding.executePendingBindings()
    }

    fun onSelectedChange(selected : Int){
        if (this.selected==selected) return
        else{
            var temp = this.selected
            this.selected=selected
            notifyItemChanged(temp)
            notifyItemChanged(this.selected)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = DataBindingUtil.bind<BannerCountLayoutBinding>(itemView) as BannerCountLayoutBinding
    }
}