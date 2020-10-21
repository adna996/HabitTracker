package com.example.accomplished

import android.icu.util.ULocale
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.accomplished.database.AppDatabase
import com.example.accomplished.database.Category
import com.example.accomplished.databinding.FragmentCategoryEditBinding
import kotlinx.android.synthetic.main.fragment_category_edit.*
import kotlinx.android.synthetic.main.fragment_category_edit.view.*
import kotlinx.android.synthetic.main.fragment_category_edit.view.updateCategoryDesc
import kotlinx.android.synthetic.main.fragment_category_edit.view.updateCategoryName

/**
 * A simple [Fragment] subclass.
 */
class CategoryEditFragment : Fragment() {
    private val args by navArgs<CategoryEditFragmentArgs>()
    private lateinit var mCategoryViewModel: CategoryViewModel
    lateinit var categoryList: ArrayList<Category>
    lateinit var db: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //mCategoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        val db = AppDatabase.getInstance(requireActivity().applicationContext)

        val view = inflater.inflate(R.layout.fragment_category_edit,
                container, false)
        view.updateCategoryName.setText(args.currentCat.catName)
        view.updateCategoryDesc.setText(args.currentCat.catDesc)
        view.editButton.setOnClickListener{
            val cName = updateCategoryName.text.toString()
            val cDesc = updateCategoryDesc.text.toString()
            val updateCat = Category(args.currentCat.uid, cName, cDesc)
            //mCategoryViewModel.updateCat(updateCat)
            db.categoryDao().updateCat(updateCat)
            //vrati se nazad
            view.findNavController()?.navigate(R.id.action_categoryEditFragment_to_choiceFragment)
        }
        return view
    }

    private fun updateItem(){
        //izmjeni

    }

}
