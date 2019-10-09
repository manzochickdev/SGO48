package xyz.manzodev.sgo48.homepage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import xyz.manzodev.sgo48.R
import xyz.manzodev.sgo48.databinding.NewsHomepageLayoutBinding
import xyz.manzodev.sgo48.model.News

class NewsHomepageAdapter(var news:ArrayList<News>, var context: Context, var onNewsClick : (news: News)->Unit):RecyclerView.Adapter<NewsHomepageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.news_homepage_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.news = news[position]
        holder.binding.newsContainer.setOnClickListener {
            onNewsClick(news[position])
        }
        holder.binding.executePendingBindings()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding  = DataBindingUtil.bind<NewsHomepageLayoutBinding>(itemView) as NewsHomepageLayoutBinding
    }
}