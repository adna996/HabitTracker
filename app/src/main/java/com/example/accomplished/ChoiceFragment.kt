package com.example.accomplished

import android.content.ClipData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.accomplished.R.layout
import com.example.accomplished.database.AppDatabase
import com.example.accomplished.database.Category
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_category_edit.*


class ChoiceFragment : Fragment() , OnCategoryItemClickListener{
    //lateinit var binding: FragmentChoiceBinding
    lateinit var categoryList: ArrayList<Category>
    private var rec: RecyclerView? = null
    private lateinit var btn: FloatingActionButton
    private lateinit var deleteBtn: Button
    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState != null){
            view?.let { FragmentManager.findFragment<ChoiceFragment>(it).retainInstance }
        }
        retainInstance = true
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_choice, container, false)
        db = AppDatabase.getInstance(requireActivity().applicationContext)
        val categories = db.categoryDao().getAll()
        val view: View = inflater.inflate(layout.fragment_choice, container, false)
        btn = view.findViewById(R.id.fabCategory)
        btn.setOnClickListener {
                view.findNavController().navigate(R.id.action_choiceFragment_to_AddCategoryFragment)
        }
        categoryList = categories.map {
            Category(
                it.uid,
                it.catName,
                it.catDesc
            )
        } as ArrayList<Category>

        rec = view.findViewById(R.id.categoryRecylerView)
        rec?.layoutManager = LinearLayoutManager(activity)
        rec?.adapter = CategoryAdapter(categoryList, this )

        return view

    }


    override fun onItemClick(item: Category, position: Int) {
        val id = item.uid
        view?.findNavController()?.navigate(ChoiceFragmentDirections.actionChoiceFragmentToActivityFragment(id,""))
    }

    override fun removeItem(item: Category, position: Int) {
        db.categoryDao().deleteCategory(item.uid)
        requireFragmentManager().beginTransaction().detach(this).attach(this).commit()

    }

    override fun editItem(item: Category, position: Int) {
        val id = item.uid
        view?.findNavController()?.navigate(ChoiceFragmentDirections.actionChoiceFragmentToCategoryEditFragment(id,item))
    }

    override fun onPause() {
        view?.let { FragmentManager.findFragment<ChoiceFragment>(it).retainInstance }
        super.onPause()
    }

    override fun onDestroy() {
        view?.let { FragmentManager.findFragment<ChoiceFragment>(it).retainInstance }
        super.onDestroy()
    }

    override fun onResume() {
        view?.let { FragmentManager.findFragment<ChoiceFragment>(it).retainInstance }
        super.onResume()
    }

}

