package xyz.manzodev.sgo48.model

import org.jsoup.nodes.Document
import xyz.manzodev.sgo48.BASE_URL

class Member(var name:String,var img:String,var href:String) {

    companion object{
        fun getMembers(doc:Document) : ArrayList<Member>{
            var data = ArrayList<Member>()
            var elements = doc.select("#carouselMembers > div.carousel-inner > div.carousel-item > div.row > div > div > div.img-responsive")

            for (e in elements){
                val name = e.select("div > a").text()
                val img = e.select("img").attr("data-src")
                val href = BASE_URL+e.select("div > a").attr("href")
                data.add(Member(name,img,href))
            }
            return data
        }

        fun getMemberExplicit(doc:Document): ArrayList<Member>{
            var data = ArrayList<Member>()
            val elements = doc.select("div.row:has(div.img-responsive) > div");
            for(e in elements){
                val img = e.select("div.img-responsive > img").attr("src")
                val name = e.select("div.img-responsive > div.alias_name").text()
                val href = e.select("div > a").attr("href")
                data.add(Member(name, img, href))
            }
            return data
        }
    }
}