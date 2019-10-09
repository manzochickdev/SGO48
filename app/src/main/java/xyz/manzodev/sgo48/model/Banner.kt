package xyz.manzodev.sgo48.model

import org.jsoup.nodes.Document
import xyz.manzodev.sgo48.BASE_URL

class Banner(var img:String , var href :String?=null) {

    companion object{
        fun getBanners(doc:Document) : ArrayList<Banner>{
            val data = ArrayList<Banner>()
            val elements = doc.select("#carouselBanner  > div.carousel-inner > div")
            for (e in elements){
                var href:String? = null
                if (e.select("a")!=null && e.select("a").attr("href").isNotBlank()){
                    href = e.select("a").attr("href")
                }
                val img = BASE_URL + e.select("img").attr("src")
                data.add(Banner(img,href))
            }
            return data
        }
    }
}