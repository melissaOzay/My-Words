package com.example.mywords.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mywords.R
import com.example.mywords.db.WordyEntity
import com.example.mywords.view_model.SaveViewModel


class SaveFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this)[SaveViewModel::class.java]
    }
    private lateinit var name: EditText
    private lateinit var savebutton: Button
    //  private lateinit var openHome: OpenFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_save, container, false)
        savebutton = view.findViewById(R.id.button)
        saveButton()
        return view
    }

    private fun createWordy() {
        name = requireView().findViewById(R.id.textInputText)
        val nameText = name.text.toString()
        val user = WordyEntity(wordyId = 0, nameText)
        viewModel.createNewUser(user)

        viewModel.wordyListInfo.observe(requireActivity()) {
            Toast.makeText(requireActivity(), "Successfully created User", Toast.LENGTH_LONG).show()
        }
        viewModel.failure.observe(requireActivity()) {
            Toast.makeText(requireActivity(), "Failure", Toast.LENGTH_LONG).show()
        }

    }

    private fun saveButton() {
        savebutton.setOnClickListener {
            createWordy()
//            openHome = activity as OpenFragment
            //           openHome.openFragment()
            name.text.clear()
        }
    }
}

