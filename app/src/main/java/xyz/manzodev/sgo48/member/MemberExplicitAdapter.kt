package xyz.manzodev.sgo48.member

import android.content.Context
import xyz.manzodev.sgo48.homepage.MemberHomepageAdapter
import xyz.manzodev.sgo48.model.Member

class MemberExplicitAdapter(members:ArrayList<Member>, context: Context, onMemberClick : (member: Member)->Unit) : MemberHomepageAdapter(members, context, onMemberClick) {
}