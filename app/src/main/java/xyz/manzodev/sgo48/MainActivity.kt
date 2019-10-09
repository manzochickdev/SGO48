package xyz.manzodev.sgo48

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import xyz.manzodev.sgo48.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeFullScreen()
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        Glide.with(this).load(R.drawable.background).into(binding.ivBackground)
        //todo #1 tạo ui hiển thị trang chủ
        // #2 UI trang tin tức + member
        // #3 trang detail cho cả 2
    }

    private fun makeFullScreen() {
        window.statusBarColor = resources.getColor(R.color.colorPrimary)
    }

    fun inflateFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.main_container,fragment,fragment.javaClass.simpleName)
            .addToBackStack(fragment.javaClass.simpleName)
            .commit()
    }
}
