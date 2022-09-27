package com.example.mywords.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mywords.R
import com.example.mywords.adapter.FavoriteAdapter
import com.example.mywords.db.WordyEntity
import com.example.mywords.view_model.FavoriteViewModel
import kotlinx.android.synthetic.main.fragment_favorite.view.*


class FavoriteFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProvider(this)[FavoriteViewModel::class.java]
    }

    private val recyclerViewAdapter by lazy {
        FavoriteAdapter(listener = object : FavoriteAdapter.FavoriteListener{
            override fun favoriteDeleteListener(entity: WordyEntity) {
                viewModel.deleteFavoriInfo(entity)
            }
        })
    }
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getAllFavorite()
        viewModel.allFavorite.observe(this) {
            recyclerViewAdapter.setDataFavori(ArrayList(it))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        requireActivity().getWindow()
            .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        recyclerView = view.recyclerViewFrag
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        return view

}

}