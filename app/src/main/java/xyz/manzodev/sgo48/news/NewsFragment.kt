package xyz.manzodev.sgo48.news


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import xyz.manzodev.sgo48.R
import xyz.manzodev.sgo48.Repository
import xyz.manzodev.sgo48.databinding.FragmentNewsBinding
import xyz.manzodev.sgo48.logd
import xyz.manzodev.sgo48.model.News

class NewsFragment : Fragment() {

    lateinit var binding : FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_news, container, false)

        initData()

        return binding.root
    }

    private fun initData() {
        Repository.getInstance().getNewsExplicit {
            val news = Repository.getInstance().news
            val adapter = NewsExplicitAdapter(news,context!!,this::onNewsSelected)
            binding.rvNewsExplicit.adapter = adapter
        }
    }

    private fun onNewsSelected(news:News){
        val intent = Intent(context!!,NewsDetailActivity::class.java)
        intent.putExtra("href",news.href)
        startActivity(intent)
    }
}
