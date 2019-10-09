package xyz.manzodev.sgo48

import android.os.AsyncTask
import org.jsoup.Jsoup
import xyz.manzodev.sgo48.model.Banner
import xyz.manzodev.sgo48.model.Member
import xyz.manzodev.sgo48.model.News

class Repository {

    var banners : ArrayList<Banner> = ArrayList<Banner>()
    var members : ArrayList<Member> = ArrayList<Member>()
    var news : ArrayList<News> = ArrayList<News>()


    companion object{
        private var repository : Repository? = null
        fun getInstance() : Repository {
            if (repository==null) repository = Repository()
            return repository!!
        }
    }

    fun getInitData(callback : ()->Unit){
        if (banners.size>0 && members.size>0 &&news.size>0){
            callback()
            return
        }
        else{
            object : AsyncTask<Void,Void,Void>(){
                override fun doInBackground(vararg p0: Void?): Void? {
                    val doc = Jsoup.connect(BASE_URL).get()
                    banners = Banner.getBanners(doc)
                    members = Member.getMembers(doc)
                    news = News.getNews(doc)
                    return null
                }

                override fun onPostExecute(result: Void?) {
                    callback()
                }

            }.execute()
        }
    }

    fun getNewsExplicit(callback : ()->Unit){
        if (news.size>0 && news.find { it.summary!=null }!=null){
            callback()
            return
        }
        else{
            object : AsyncTask<Void,Void,Void>(){
                override fun doInBackground(vararg p0: Void?): Void? {
                    val doc = Jsoup.connect("https://www.sgo48.vn/news").get()
                    news = News.getNewsExplicit(doc)
                    return null
                }

                override fun onPostExecute(result: Void?) {
                    callback()
                }

            }.execute()
        }
    }

    fun getMemberExplicit(callback : ()->Unit){
        if (members.size>0) {
            callback()
            return
        }
        else{
            object : AsyncTask<Void,Void,Void>(){
                override fun doInBackground(vararg p0: Void?): Void? {
                    val doc = Jsoup.connect("https://www.sgo48.vn/members").get()
                    members = Member.getMemberExplicit(doc)
                    return null
                }

                override fun onPostExecute(result: Void?) {
                    callback()
                }

            }.execute()
        }
    }



}