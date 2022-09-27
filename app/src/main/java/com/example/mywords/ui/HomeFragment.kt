package com.example.mywords.ui

import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mywords.R
import com.example.mywords.adapter.HomeAdapter
import com.example.mywords.db.WordyEntity
import com.example.mywords.utility.CommonUtility
import com.example.mywords.view_model.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    lateinit var recyclerView: RecyclerView

    private val recyclerViewAdapter by lazy {
        HomeAdapter(listener = object : HomeAdapter.HomeAdapterListener {
            override fun addFavorite(entity: WordyEntity) {
                viewModel.addFavoriteWord(entity)
            }

            override fun deleteFavorite(entity: WordyEntity) {
                viewModel.deleteWordy(entity)
            }

            override fun shareButton(text: String) {
                CommonUtility.shareText(requireActivity(),text)
            }

            override fun copyToClipboard(text: CharSequence) {
                CommonUtility.copyText(text,requireActivity())
                Toast.makeText(requireContext(),"Type copied",Toast.LENGTH_SHORT).show()
            }

        })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        requireActivity().getWindow()
            .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        recyclerView = view.recyclerViewHome
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewModel.getAllWordList()

        return view

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.wordList.observe(this) {
            recyclerViewAdapter.setListData(it)
        }
    }

}



