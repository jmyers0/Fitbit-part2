package com.example.fitbitpart1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.fitbitpart1.databinding.FragmentDashboardBinding
import kotlin.math.roundToInt

class DashboardFragment : Fragment() {
    private var _b: FragmentDashboardBinding? = null
    private val b get() = _b!!
    private val vm: FoodViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _b = FragmentDashboardBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vm.entries.observe(viewLifecycleOwner) { list ->
            val cals = list.map { it.calories }
            val total = list.size
            val avg = if (cals.isNotEmpty()) cals.average().roundToInt() else 0
            val min = cals.minOrNull() ?: 0
            val max = cals.maxOrNull() ?: 0

            b.totalText.text = "Entries: $total"
            b.avgText.text   = "Average: $avg Calories"
            b.minText.text   = "Min: $min Calories"
            b.maxText.text   = "Max: $max Calories"
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _b = null }
}
