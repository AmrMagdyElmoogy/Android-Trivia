package com.example.androidtravia

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.androidtravia.databinding.FragmentGameOverBinding
import com.example.androidtravia.databinding.FragmentGameWonBinding

class GameWonFragment : Fragment() {

    private lateinit var binding: FragmentGameWonBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_won, container, false)
        binding.buttonGameWin.setOnClickListener {
            view?.findNavController()?.popBackStack(R.id.Fragment_title, false)
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.share -> startActivity(getShareIntent())
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        return inflater.inflate(R.menu.winner_share,menu)
    }

    private fun getShareIntent() : Intent{
        val args = GameWonFragmentArgs.fromBundle(requireArguments())
        return Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT,getString(R.string.share_success_text , args.numCorrect, args.numQuestions) )
        }
    }
}