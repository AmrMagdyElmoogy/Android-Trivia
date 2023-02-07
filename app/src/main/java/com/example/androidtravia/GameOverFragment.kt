package com.example.androidtravia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.androidtravia.databinding.FragmentGameOverBinding
import com.example.androidtravia.databinding.FragmentGameWonBinding


class GameOverFragment : Fragment() {

    private lateinit var binding: FragmentGameOverBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_game_over, container, false)
        binding.buttonGameOver.setOnClickListener {
            view?.findNavController()?.popBackStack(R.id.Fragment_title,false)
        }
        return binding.root
    }


}