package xyz.manzodev.sgo48.homepage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import xyz.manzodev.sgo48.R
import xyz.manzodev.sgo48.databinding.BannerLayoutBinding
import xyz.manzodev.sgo48.model.Banner

const val MAX_VALUE = 200
class BannerAdapter(var banners:ArrayList<Banner>,var context:Context,var onBannerClick : (href:String)->Unit) : PagerAdapter() {


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as View
    }

    override fun getCount(): Int {
        return if (banners.size>0) banners.size*MAX_VALUE else 0
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view = LayoutInflater.from(context).inflate(R.layout.banner_layout,container,false)
        var binding = DataBindingUtil.bind<BannerLayoutBinding>(view) as BannerLayoutBinding
        var pos = position % banners.size
        binding.imgUrl = banners[pos].img
        binding.bannerView.setOnClickListener {
            banners[pos].href?.let {
                onBannerClick(it)
            }
        }

        container.addView(view,0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}