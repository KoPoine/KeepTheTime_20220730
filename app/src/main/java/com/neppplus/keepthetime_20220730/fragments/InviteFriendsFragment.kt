package com.neppplus.keepthetime_20220730.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.neppplus.keepthetime_20220730.R
import com.neppplus.keepthetime_20220730.adapters.FriendRecyclerViewAdapter
import com.neppplus.keepthetime_20220730.databinding.FragmentInviteFriendsBinding
import com.neppplus.keepthetime_20220730.datas.BasicResponse
import com.neppplus.keepthetime_20220730.datas.FriendData
import com.neppplus.keepthetime_20220730.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InviteFriendsFragment : BaseFragment() {

    lateinit var mBinding : FragmentInviteFriendsBinding

    lateinit var mFriendAdapter : FriendRecyclerViewAdapter
    val mFriendList = ArrayList<FriendData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_invite_friends, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        getFriendListFromServer()

        mFriendAdapter = FriendRecyclerViewAdapter(mContext, mFriendList)
        mBinding.invitedRecyclerView.adapter = mFriendAdapter
        mBinding.invitedRecyclerView.layoutManager = LinearLayoutManager(mContext)
    }

    fun getFriendListFromServer() {
        val token = ContextUtil.getLoginToken(mContext)
        apiList.getRequestFriendsList(token, "requested").enqueue(object : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {
                    val br = response.body()!!

                    mFriendList.addAll(br.data.friends)
                    mFriendAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })
    }
}