package xyz.manzodev.sgo48.homepage


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import xyz.manzodev.sgo48.R
import xyz.manzodev.sgo48.Repository
import xyz.manzodev.sgo48.databinding.FragmentHomepageBinding
import xyz.manzodev.sgo48.logd
import xyz.manzodev.sgo48.model.Member
import xyz.manzodev.sgo48.model.News
import java.util.*

class HomepageFragment : Fragment() {

    private lateinit var binding : FragmentHomepageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_homepage, container, false)
        Repository.getInstance().getInitData {
            initData()
        }
        return binding.root
    }

    private fun initData(){
        initBanner()
        initMember()
        initNews()
    }

    private fun initBanner(){
        val banners = Repository.getInstance().banners
        if(banners.size>0){
            var countAdapter = BannerCountAdapter(banners.size,context!!)
            binding.rvBannerCount.adapter = countAdapter

            var adapter = BannerAdapter(banners,context!!,this::onBannerClick)
            binding.vpBanner.adapter = adapter
            binding.vpBanner.offscreenPageLimit =1
            binding.vpBanner.setCurrentItem(adapter.count / 2)

            binding.vpBanner.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
                override fun onPageScrollStateChanged(state: Int) {

                }

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                }

                override fun onPageSelected(position: Int) {
                    countAdapter.onSelectedChange(position % banners.size)
                }

            })

            var timerTask = object : TimerTask(){
                override fun run() {
                    binding.vpBanner.post {
                        binding.vpBanner.setCurrentItem(binding.vpBanner.currentItem+1)
                    }
                }
            }

            var timer = Timer()
            timer.schedule(timerTask,5000,5000)
        }


    }

    private fun initMember(){
        var members = Repository.getInstance().members
        if (members.size>0){
            val adapter = MemberHomepageAdapter(members,context!!,this::onMemberClick)
            binding.rvMember.adapter = adapter
        }
    }

    private fun initNews(){
        var news = Repository.getInstance().news
        if (news.size>0){
            val adapter = NewsHomepageAdapter(news,context!!,this::onNewsClick)
            binding.rvNews.adapter = adapter
        }
    }

    private fun onBannerClick(href:String){

    }

    private fun onMemberClick(member:Member){

    }

    private fun onNewsClick(news: News){

    }
}
