package com.example.accomplished

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.accomplished.database.Activity
import com.example.accomplished.database.AppDatabase
import com.example.accomplished.databinding.FragmentActivityBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ActivityFragment : Fragment(), OnActivityItemClickListener {
    lateinit var activitiesList: ArrayList<Activity>
    private var rec: RecyclerView? = null
    lateinit var btn: FloatingActionButton
    lateinit var db: AppDatabase
    var catId = 0
    var actId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val binding = DataBindingUtil.inflate<FragmentActivityBinding>(
            inflater, R.layout.fragment_activity, container, false)
        val args = ActivityFragmentArgs.fromBundle(requireArguments())
        catId = args.postion
        db = AppDatabase.getInstance(requireActivity().applicationContext)
        //db.activityDao().deleteAll()
        // db.activityDao().insertAll(listOf(Activity(1,"Running", "fast", 1,1), Activity(2,"Gym", "cardio", 1,1)))

        binding.fabActivity.setOnClickListener{
                view: View -> view.findNavController().navigate(ActivityFragmentDirections.actionActivityFragmentToAddActivityFragment(catId))
        }

        val activities = db.activityDao().loadAllByIds(catId)
        activitiesList = activities.map {
            Activity(
                it.uid,
                it.actName,
                it.actAttribute,
                it.actType,
                it.category,
                it.value,
                it.feel
            )
        } as ArrayList<Activity>

        rec = binding.activityRecyclerView
        rec?.layoutManager = LinearLayoutManager(activity)
        rec?.adapter = ActivityAdapter(activitiesList, this )

        return binding.root
    }
    override fun onItemClick(item: Activity, position: Int) {
        val type = item.actType
        actId = item.uid
        Log.d("id act", actId.toString())
        if (type == 2) { //Timer
            view?.findNavController()?.navigate(
                ActivityFragmentDirections.actionActivityFragmentToTimerFragment(
                    actId, catId
                )
            )
        } else if (type == 1) { //Increment
            view?.findNavController()?.navigate(
                ActivityFragmentDirections.actionActivityFragmentToIncrementFragment(
                    actId,
                    catId
                )
            )
        }
        if (type == 3) { //Quantity
            view?.findNavController()?.navigate(
                ActivityFragmentDirections.actionActivityFragmentToQuantityFragment(
                    actId,
                    catId
                )
            )
        }
    }
    override fun removeItem(item: Activity, position: Int) {
        db.activityDao().deleteActivity(item.uid)
        requireFragmentManager().beginTransaction().detach(this).attach(this).commit()
    }

    override fun editItem(item: Activity, position: Int) {
        val id = item.uid
        view?.findNavController()?.navigate(ActivityFragmentDirections.actionActivityFragmentToActivityEditFragment(id, item))
    }

}