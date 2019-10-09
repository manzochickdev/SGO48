package xyz.manzodev.sgo48.homepage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import xyz.manzodev.sgo48.R
import xyz.manzodev.sgo48.databinding.MemberHomepageLayoutBinding
import xyz.manzodev.sgo48.model.Member

open class MemberHomepageAdapter(var members:ArrayList<Member>, var context: Context, var onMemberClick : (member:Member)->Unit):RecyclerView.Adapter<MemberHomepageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.member_homepage_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return members.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.member = members[position]
        holder.binding.executePendingBindings()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding  = DataBindingUtil.bind<MemberHomepageLayoutBinding>(itemView) as MemberHomepageLayoutBinding
    }
}