package com.example.accomplished

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.accomplished.database.AppDatabase
import com.example.accomplished.database.Category
import kotlinx.android.synthetic.main.fragment_add_category.*


/**
 * A simple [Fragment] subclass.
 */
class AddCategoryFragment : Fragment() {
    private lateinit var submit: Button
    private var name = ""
    private var desc = ""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_add_category, container, false)
        submit = view.findViewById(R.id.submitButton)
        val db = AppDatabase.getInstance(requireActivity().applicationContext)

        submit.setOnClickListener{
            name = CategoryName.text.toString()
            desc = CategoryDesc.text.toString()
            db.categoryDao().insertCat(name,desc)
            view.findNavController().navigate(R.id.action_addCategoryFragment_to_choiceFragment)
        }
        return view
    }

}
