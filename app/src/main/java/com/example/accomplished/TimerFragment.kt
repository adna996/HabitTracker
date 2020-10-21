package com.example.accomplished

import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.accomplished.database.AppDatabase
import kotlinx.android.synthetic.main.fragment_timer.*

/**
 * A simple [Fragment] subclass.
 */
class TimerFragment : Fragment() {

    lateinit var btn: Button
    lateinit var chronometer: Chronometer
    var actId: Int = 0
    var catId: Int = 0
    lateinit var db: AppDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        db =  AppDatabase.getInstance(requireActivity().applicationContext)
        val view = inflater.inflate(R.layout.fragment_timer,container,false)
        chronometer =view.findViewById(R.id.chronometerTimer)
        btn = view.findViewById(R.id.ControlBtn)
        val time: Long = 0
        var vrijeme: Long
        val args = TimerFragmentArgs.fromBundle(requireArguments())
        actId = args.actId
        catId = args.catId
        btn.setOnClickListener(object : View.OnClickListener {

            var StartQuit = false

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onClick(v: View) {
                if (!StartQuit) {
                    chronometerTimer.base = SystemClock.elapsedRealtime() + time
                    chronometer.start()
                    StartQuit = true
                } else {
                    vrijeme = ((SystemClock.elapsedRealtime() - chronometerTimer.base)/1000)
                    if(vrijeme<60){
                        val sec = vrijeme.toString()+" sec"
                        db.activityDao().setValue(actId, sec)
                        view.findNavController().navigate(TimerFragmentDirections.actionTimerFragmentToActivityFragment(catId,sec))

                    } else{
                        val min = (vrijeme/60).toString()+ " min "
                        val kon = min + (vrijeme-(vrijeme/60)).toString()+ " sec"
                        db.activityDao().setValue(actId, kon)
                        view.findNavController().navigate(TimerFragmentDirections.actionTimerFragmentToActivityFragment(catId,kon))

                    }
                    chronometerTimer.base = SystemClock.elapsedRealtime()
                    chronometer.stop()
                    StartQuit = false
                }

                btn.setText(if (!StartQuit) R.string.start else R.string.stop)
            }
        })

        return view
    }

}
