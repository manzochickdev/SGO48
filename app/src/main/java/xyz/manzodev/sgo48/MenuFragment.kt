package xyz.manzodev.sgo48

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import xyz.manzodev.sgo48.databinding.FragmentMenuBinding
import xyz.manzodev.sgo48.homepage.HomepageFragment
import xyz.manzodev.sgo48.member.MemberFragment
import xyz.manzodev.sgo48.news.NewsFragment


class MenuFragment : Fragment(),IMenu  {

    private lateinit var binding : FragmentMenuBinding
    private var selectedCategory = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentMenuBinding>(inflater,R.layout.fragment_menu, container, false)
        binding.listener = this
        onCategorySelect(0)


        return binding.root
    }

    override fun onCategorySelect(i: Int) {
        if (selectedCategory==i) return
        selectedCategory = i
        binding.selectedCategory = selectedCategory

        when(i){
            0 ->{
                val homepageFragment = HomepageFragment()
                (activity as MainActivity).inflateFragment(homepageFragment)
            }
            1->{
                val newsFragment = NewsFragment()
                (activity as MainActivity).inflateFragment(newsFragment)
            }
            2->{
                val memberFragment = MemberFragment()
                (activity as MainActivity).inflateFragment(memberFragment)
            }
        }
    }

}
