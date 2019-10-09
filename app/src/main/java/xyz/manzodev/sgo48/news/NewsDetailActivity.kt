package xyz.manzodev.sgo48.news

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import xyz.manzodev.sgo48.R
import xyz.manzodev.sgo48.databinding.ActivityNewsDetailBinding
import xyz.manzodev.sgo48.model.News
import android.R.attr
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide


class NewsDetailActivity : AppCompatActivity() {

    lateinit var url:String
    lateinit var binding : ActivityNewsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_news_detail)

        url = intent.getStringExtra("href")
        getNewsDetail(url)

    }

    private fun getNewsDetail(url: String) {
        object : AsyncTask<Void, Void, Document>() {
            override fun doInBackground(vararg p0: Void?): Document {
                val doc = Jsoup.connect(url).get()
                return doc
            }

            override fun onPostExecute(doc: Document) {

                val elements = doc.select("main.main-container > div.container").first()
                val title = elements.select("h3 > b").text()

                var timeStamp = ""
                val nodeBeforeTimeStamp = elements.select("div.news-info > img.news-icon")
                for (el in nodeBeforeTimeStamp) {
                    timeStamp += " " + el.nextSibling().toString()
                }


                val summary = elements.select("div.news-summary").text()

                val news = News(title,"","",timeStamp,"",summary)
                binding.news = news

                val content = elements.select("div.news-content > *")
                for (e in content) {
                    if (e.select("img") != null && e.select("img").attr("src").isNotBlank()) {
                        val imgUrl = e.select("img").attr("src")
                        var imageView = ImageView(this@NewsDetailActivity)
                        imageView.layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            resources.getDimensionPixelSize(R.dimen.image)
                        )
                        Glide.with(this@NewsDetailActivity).load(imgUrl).into(imageView)
                        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                        binding.layoutContent.addView(imageView)
                    }

                    var textView = TextView(this@NewsDetailActivity)
                    textView.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    textView.text = e.text()
                    binding.layoutContent.addView(textView)
                }
            }
        }.execute()

    }

}
