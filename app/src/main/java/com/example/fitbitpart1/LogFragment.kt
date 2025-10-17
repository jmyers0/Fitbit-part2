package com.example.fitbitpart1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitbitpart1.databinding.FragmentLogBinding

class LogFragment : Fragment() {
    private var _b: FragmentLogBinding? = null
    private val b get() = _b!!
    private val vm: FoodViewModel by activityViewModels()
    private val adapter = FoodAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _b = FragmentLogBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        b.recycler.layoutManager = LinearLayoutManager(requireContext())
        b.recycler.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        b.recycler.adapter = adapter

        b.addButton.setOnClickListener {
            startActivity(Intent(requireContext(), AddEntryActivity::class.java))
        }

        vm.entries.observe(viewLifecycleOwner) { adapter.submit(it) }
    }

    override fun onDestroyView() { super.onDestroyView(); _b = null }
}
