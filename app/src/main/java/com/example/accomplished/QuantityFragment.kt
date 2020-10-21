package com.example.accomplished

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.accomplished.database.AppDatabase
import com.example.accomplished.databinding.FragmentQuantityBinding


class QuantityFragment : Fragment() {
    lateinit var binding: FragmentQuantityBinding
    lateinit var db: AppDatabase
    var activityId: Int = 0
    var catId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentQuantityBinding.inflate(inflater,container,false)
        db = AppDatabase.getInstance(requireActivity().applicationContext)
        val args = IncrementFragmentArgs.fromBundle(requireArguments())
        activityId = args.actId
        catId = args.catId
        binding.quantity.setText(db.activityDao().getValue(activityId))
        binding.saveQuantity.setOnClickListener {
            val value = binding.quantity.text
            db.activityDao().setValue(activityId, value.toString())
            view?.findNavController()?.navigate(QuantityFragmentDirections.actionQuantityFragmentToActivityFragment(catId,value.toString()))
        }
        return binding.root
    }

}
