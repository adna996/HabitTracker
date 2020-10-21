package com.example.accomplished

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.accomplished.database.AppDatabase
import com.example.accomplished.databinding.FragmentIncrementBinding


class IncrementFragment : Fragment() {
    lateinit var db: AppDatabase
    lateinit var binding: FragmentIncrementBinding
    var activityId: Int = 0
    var catId: Int = 0
    var inc: Int = 0
    var broj: Int = 0
    var num: Int = 0
    lateinit var args: IncrementFragmentArgs
    lateinit var textView: TextView
    //var numb: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentIncrementBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.getInstance(requireActivity().applicationContext)
        args = IncrementFragmentArgs.fromBundle(requireArguments())
        activityId = args.actId
        catId = args.catId
        textView = binding.numberInc
        if(db.activityDao().getAttr(activityId) == ""){
            num = 1
        }
        else{
            val br = db.activityDao().getAttr(activityId)
            num = br.filter { it.isDigit() }.toInt()
        }

        if(db.activityDao().getValue(activityId) == ""){
            broj = 0
            textView.text = "0"
        }
        else{
            broj = db.activityDao().getValue(activityId).toInt()
        }

        if(num > 0)
        {
            textView.text = db.activityDao().getValue(activityId)
            inc = num
        }
        else{
            textView.text = "0"
            inc = 1
        }

        binding.increase.setOnClickListener{
            broj += inc
            textView.text = broj.toString()
        }
        binding.decrease.setOnClickListener {
            broj -= inc
            textView.text = broj.toString()
        }
        binding.save.setOnClickListener {
            Log.d("broj" , broj.toString())
            db.activityDao().setAttribute(activityId, " Increment: $inc")
            val novo = broj.toString()
            db.activityDao().setValue(activityId, novo)
            view.findNavController().navigate(IncrementFragmentDirections.actionIncrementFragmentToActivityFragment(catId,novo))
        }
    }

}
