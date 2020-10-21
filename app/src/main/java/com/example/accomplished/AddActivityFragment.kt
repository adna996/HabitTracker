package com.example.accomplished

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.accomplished.database.AppDatabase
import com.example.accomplished.databinding.FragmentAddActivityBinding
import kotlinx.android.synthetic.main.fragment_add_activity.*


class AddActivityFragment : Fragment() {
    var name = ""
    var atribute = ""
    var catId = 1
    private var bt1: Int = 0
    var increment = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_add_activity, container, false)
        val binding = DataBindingUtil.inflate<FragmentAddActivityBinding>(inflater,
            R.layout.fragment_add_activity,container,false)
        val db = AppDatabase.getInstance(requireActivity().applicationContext)
        val args = AddActivityFragmentArgs.fromBundle(requireArguments())
        catId = args.categoryId
        Log.d("id kategorije", catId.toString())
        binding.timeButton.setOnClickListener(){
            bt1 = 2
            binding.timeButton.setBackgroundResource(R.drawable.rounded2)
            binding.quantityButton.setBackgroundResource(R.drawable.rounded)
            binding.incButton.setBackgroundResource(R.drawable.rounded)
        }

        binding.quantityButton.setOnClickListener(){
            binding.timeButton.setBackgroundResource(R.drawable.rounded)
            binding.quantityButton.setBackgroundResource(R.drawable.rounded2)
            binding.incButton.setBackgroundResource(R.drawable.rounded)
            binding.yesButton.setOnClickListener(){
                descriptionUnitTV.visibility = VISIBLE
                measureUnitGroup.visibility = VISIBLE
                incGroup.visibility = INVISIBLE
                decriptionIncTV.visibility = INVISIBLE
                binding.noButton.setBackgroundResource(R.drawable.rounded)
                binding.yesButton.setBackgroundResource(R.drawable.rounded2)
            }
            binding.noButton.setOnClickListener(){
                binding.yesButton.setBackgroundResource(R.drawable.rounded)
                binding.yesButton.setBackgroundResource(R.drawable.rounded2)
            }

            bt1 = 3
        }
        binding.incButton.setOnClickListener(){
            binding.timeButton.setBackgroundResource(R.drawable.rounded)
            binding.quantityButton.setBackgroundResource(R.drawable.rounded)
            binding.incButton.setBackgroundResource(R.drawable.rounded2)
            binding.yesButton.setOnClickListener(){
                descriptionUnitTV.visibility = INVISIBLE
                measureUnitGroup.visibility = INVISIBLE
                incGroup.visibility = VISIBLE
                decriptionIncTV.visibility = VISIBLE
                binding.noButton.setBackgroundResource(R.drawable.rounded)
                binding.yesButton.setBackgroundResource(R.drawable.rounded2)
            }
            binding.noButton.setOnClickListener(){
                binding.yesButton.setBackgroundResource(R.drawable.rounded)
                binding.yesButton.setBackgroundResource(R.drawable.rounded2)
            }

            bt1 = 1
        }



        binding.submit1.setOnClickListener(){
            val attr = binding.measureUnitGroup.checkedRadioButtonId
            when(attr) {
                R.id.bpm -> atribute = " bpm"
                R.id.kcal -> atribute = " kcal"
                R.id.m -> atribute =" meter"
                R.id.kg -> atribute = " kilograms"
                R.id.litar -> atribute =" litres"
                R.id.km -> atribute = " kilometers"
            }
            val inc = binding.incGroup.checkedRadioButtonId
            when(inc){
                R.id.pet -> increment = 5
                R.id.deset -> increment = 10
                R.id.petnaest -> increment = 15
                R.id.jedan -> increment = 1
            }
            name = ActivityNameText.text.toString()
            Log.d("atr", atribute)
            Log.d("ink", increment.toString())
            if(bt1 == 1){
                db.activityDao().insertActivity(name,increment.toString(),bt1,catId, "")
            }
            else {
                db.activityDao().insertActivity(name, atribute, bt1, catId, "")
            }
            view?.findNavController()?.navigate(R.id.action_addActivityFragment_to_activityFragment)

        }

        return binding.root
    }

}
