package xyz.manzodev.sgo48.member


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
import xyz.manzodev.sgo48.databinding.FragmentMemberBinding
import xyz.manzodev.sgo48.logd
import xyz.manzodev.sgo48.model.Member
import xyz.manzodev.sgo48.model.News
import xyz.manzodev.sgo48.news.NewsDetailActivity

class MemberFragment : Fragment() {
    lateinit var binding : FragmentMemberBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_member, container, false)
        initData()
        return binding.root
    }

    private fun initData() {
        Repository.getInstance().getMemberExplicit {
            val members = Repository.getInstance().members
            val adapter = MemberExplicitAdapter(members,context!!,this::onMemberSelected)
            binding.rvMemberExplicit.adapter = adapter
            binding.rvMemberExplicit.layoutManager = GridLayoutManager(context!!,3,
                GridLayoutManager.VERTICAL,false)
        }
    }

    fun onMemberSelected(member: Member){
        val intent = Intent(context!!, MemberDetailActivity::class.java)
        intent.putExtra("href",member.href)
        startActivity(intent)
    }
}
