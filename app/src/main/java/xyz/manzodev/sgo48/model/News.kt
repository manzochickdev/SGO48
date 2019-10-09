package xyz.manzodev.sgo48.model

import org.jsoup.nodes.Document
import xyz.manzodev.sgo48.BASE_URL

open class News(var title:String,var img:String,var timeStamp:String,var href:String,var summary:String?=null) {
    var author:String?=null

    constructor(title:String,img:String,author:String?,timeStamp:String,href:String,summary:String?=null) : this(title, img, timeStamp, href, summary){
        this.author = author
    }

    companion object{
        fun getNews(doc:Document) : ArrayList<News>{
            var data = ArrayList<News>()
            val elements = doc.select("div.card:has(div.card-img)")
            for (e in elements){
                val title = e.select("div.card-body > a").text()
                val timeStamp = e.select("div.card-body > div > p").text()
                val img = e.select("div.card-img > img").attr("src")
                val href = BASE_URL + e.select("div.card-body > a").attr("href")

                data.add(News(title, img, timeStamp, href))
            }
            return data
        }

        fun getNewsExplicit(doc: Document) : ArrayList<News> {
            var data = ArrayList<News>()
            var elements = doc.select("div.row:has(div.card-img)")
            for (e in elements) {
                //img
                val img = e.select("div > div.card-img > img").attr("src")
                //href
                val href = BASE_URL+e.select("div a.new-title").attr("href")
                //tittle
                val title = e.select("div a.new-title").text()

                //timeStamp
                val nodeBeforeTimeStamp = e.select("div.news-info > img.news-icon")
                val author = nodeBeforeTimeStamp[0].nextSibling().toString()
                var timeStamp = nodeBeforeTimeStamp[1].nextSibling().toString()

                //summary
                val summary = e.select("div div.news-info").first().nextSibling().toString().trim()
                data.add(News(title, img, author,timeStamp, href, summary))
            }
            return data
        }
    }

}