package com.example.accomplished

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.accomplished.database.Activity
import com.example.accomplished.database.AppDatabase
import com.example.accomplished.databinding.FragmentActivityEditBinding
import kotlinx.android.synthetic.main.fragment_activity_edit.*
import kotlinx.android.synthetic.main.fragment_activity_edit.view.*
import kotlinx.android.synthetic.main.fragment_add_activity.*


class ActivityEditFragment : Fragment() {
    private val args by navArgs<ActivityEditFragmentArgs>()
    var catId = 1
    private var type: Int = 0
    var atribute = ""
    var increment = 1
    var feeling = ""
    var typeA = 0
    var atrrA = ""
    var feelingA = ""
    var valueA = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentActivityEditBinding>(inflater, R.layout.fragment_activity_edit,container, false)
        val db = AppDatabase.getInstance(requireActivity().applicationContext)
        binding.UpdateActivityNameText?.setText(args.currentAct.actName)
        catId = args.currentAct.category

        typeA = args.currentAct.actType
        atrrA = args.currentAct.actAttribute.toString()
        feelingA = args.currentAct.feel.toString()
        valueA = args.currentAct.value.toString()
        if (typeA == 2){
            binding.updatetimeButton?.isChecked
        }
        if(typeA == 1){
            binding.updateincButton?.isChecked
            binding.updatedescriptionUnitTV?.visibility = View.INVISIBLE
            binding.updatemeasureUnitGroup?.visibility = View.INVISIBLE
            binding.updateincGroup?.visibility = View.VISIBLE
            binding.updatedecriptionIncTV?.visibility = View.VISIBLE
        }
        if(typeA == 3){
            binding.updatequantityButton?.isChecked
            binding.updatedescriptionUnitTV?.visibility = View.VISIBLE
            binding.updatemeasureUnitGroup?.visibility = View.VISIBLE
            binding.updateincGroup?.visibility = View.INVISIBLE
            binding.updatedecriptionIncTV?.visibility = View.INVISIBLE
        }
        binding?.updatetimeButton?.setOnClickListener(){
            type = 2
            binding.updatedescriptionUnitTV?.visibility = View.INVISIBLE
            binding.updatemeasureUnitGroup?.visibility = View.INVISIBLE
            binding.updateincGroup?.visibility = View.INVISIBLE
            binding.updatedecriptionIncTV?.visibility = View.INVISIBLE
            binding.updatetimeButton.setBackgroundResource(R.drawable.rounded2)
            binding.updateincButton.setBackgroundResource(R.drawable.rounded)
            binding.updatequantityButton.setBackgroundResource(R.drawable.rounded)
        }
        binding?.updateincButton?.setOnClickListener(){
            type = 1
            updatedescriptionUnitTV.visibility = View.INVISIBLE
            updatemeasureUnitGroup.visibility = View.INVISIBLE
            updateincGroup.visibility = View.VISIBLE
            updatedecriptionIncTV.visibility = View.VISIBLE
            binding.updatetimeButton.setBackgroundResource(R.drawable.rounded)
            binding.updateincButton.setBackgroundResource(R.drawable.rounded2)
            binding.updatequantityButton.setBackgroundResource(R.drawable.rounded)
        }

        binding.updatequantityButton?.setOnClickListener(){
            type = 3
            updatedescriptionUnitTV.visibility = View.VISIBLE
            updatemeasureUnitGroup.visibility = View.VISIBLE
            updateincGroup.visibility = View.INVISIBLE
            updatedecriptionIncTV.visibility = View.INVISIBLE
            binding.updatetimeButton.setBackgroundResource(R.drawable.rounded)
            binding.updateincButton.setBackgroundResource(R.drawable.rounded)
            binding.updatequantityButton.setBackgroundResource(R.drawable.rounded2)
        }

        if(feelingA == " sad "){binding.sad?.setChecked(true)}
        if(feelingA == " happy "){binding.happy?.setChecked(true)}
        if(feelingA == " tired "){binding.tired?.setChecked(true)}
        if(feelingA == " rested "){binding.rested?.setChecked(true)}

        binding?.updateEditAct?.setOnClickListener(){
            val aName = UpdateActivityNameText.text.toString()
            val attr = binding?.updatemeasureUnitGroup?.checkedRadioButtonId
            when(attr) {
                R.id.updatebpm -> atribute = " bpm"
                R.id.updatekcal -> atribute = " kcal"
                R.id.updatem -> atribute =" meter"
                R.id.updatekg -> atribute = " kilograms"
                R.id.updatelitar -> atribute =" litres"
                R.id.updatekm -> atribute = " kilometers"
            }
            val inc = binding?.updateincGroup?.checkedRadioButtonId
            when(inc){
                R.id.updatejedan -> increment = 1
                R.id.updatepet -> increment = 5
                R.id.updatedeset -> increment = 10
                R.id.updatepetnaest -> increment = 15
                R.id.updatedvadeset -> increment = 20
                R.id.updatedvadesetpet -> increment = 25
            }
            val feels = binding?.feelingsGroup?.checkedRadioButtonId
            when(feels){
                R.id.sad -> feeling = " sad "
                R.id.happy -> feeling = " happy "
                R.id.rested -> feeling = " rested "
                R.id.tired -> feeling = " tired "
            }
            Log.d("atr", atribute)
            Log.d("ink", increment.toString())
            Log.d("feel", feeling)
            if(type == 1){
                val updated = Activity(args.currentAct.uid, aName, increment.toString(), type, args.currentAct.category,"",feeling)
                db.activityDao().updateAct(updated)
            }else{
                val updated1 = Activity(args.currentAct.uid, aName, atribute, type, args.currentAct.category,"", feeling)
                db.activityDao().updateAct(updated1)
            }
            view?.findNavController()?.navigate(ActivityEditFragmentDirections.actionActivityEditFragmentToActivityFragment(args.currentAct.category, args.currentAct.value
            ))
        }
        return binding.root

    }
}
