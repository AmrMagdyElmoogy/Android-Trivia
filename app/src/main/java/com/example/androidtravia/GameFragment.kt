package com.example.androidtravia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.androidtravia.databinding.FragmentGameBinding


class GameFragment : Fragment() {


    var ourGame = listOf<Game>(
        Game(
            text = "What is Android Jetpack?",
            answers = listOf("all of these", "tools", "documentation", "libraries"),
            actualAnswer = "all of these"

        ),
        Game(
            text = "Base class for Layout?",
            answers = listOf("ViewGroup", "ViewSet", "ViewCollection", "ViewRoot"),
            actualAnswer = "ViewGroup"
        ),
        Game(
            text = "Layout for complex Screens?",
            answers = listOf("ConstraintLayout", "GridLayout", "LinearLayout", "FrameLayout"),
            actualAnswer = "ConstraintLayout"
        ),
        Game(
            text = "Pushing structured data into a Layout?",
            answers = listOf("Data Binding", "Data Pushing", "Set Text", "OnClick"),
            actualAnswer = "Data Binding"
        ),
        Game(
            text = "Inflate layout in fragments?",
            answers = listOf("onCreateView", "onViewCreated", "onCreateLayout", "onInflateLayout"),
            actualAnswer = "onCreateView"
        ),
        Game(
            text = "Build system for Android?",
            answers = listOf("Gradle", "Graddle", "Grodle", "Groyle"),
            actualAnswer = "Gradle"
        ),
        Game(
            text = "Android vector format?",
            answers = listOf(
                "VectorDrawable",
                "AndroidVectorDrawable",
                "DrawableVector",
                "AndroidVector"
            ),
            actualAnswer = "VectorDrawable"
        ),
        Game(
            text = "Android Navigation Component?",
            answers = listOf("NavController", "NavCentral", "NavMaster", "NavSwitcher"),
            actualAnswer = "NavController"
        ),
        Game(
            text = "Registers app with launcher?",
            answers = listOf("intent-filter", "app-registry", "launcher-registry", "app-launcher"),
            actualAnswer = "intent-filter"
        ),
        Game(
            text = "Mark a layout for Data Binding?",
            answers = listOf("<layout>", "<binding>", "<data-binding>", "<dbinding>"),
            actualAnswer = "<layout>"
        )
    )

    private lateinit var binding: FragmentGameBinding
    var checkedAnswerId: Int = -1
    val limit = 3
    var score = 0
    var indexOfQuestion = 0
    var question : Game = ourGame[0]
    private lateinit var radioGroup: RadioGroup
    private lateinit var answerOne: RadioButton
    private lateinit var answerTwo: RadioButton
    private lateinit var answerThree: RadioButton
    private lateinit var answerFour: RadioButton


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_game,container,false)
        binding.apply {
            radioGroup = this.questionRadioGroup
            answerOne = this.firstAnswerRadioButton
            answerTwo = this.secondAnswerRadioButton
            answerThree = this.thirdAnswerRadioButton
            answerFour = this.fourthAnswerRadioButton
        }
            var views = listOf(answerOne,answerTwo,answerThree,answerFour)
            views.forEach {
                it.setOnClickListener {
                    checkedAnswerId = it.id
                    println(checkedAnswerId)
                }
            }
            binding.question = this
            binding.submitButton.setOnClickListener {
                val answer = when(checkedAnswerId)
                {
                    answerOne.id -> answerOne.text
                    answerTwo.id -> answerTwo.text
                    answerThree.id -> answerThree.text
                    else -> answerFour.text
                }.toString()
                determineConditionalNavigation(answer)
                radioGroup.clearCheck()
                question = ourGame[indexOfQuestion]
                binding.invalidateAll()
            }
        return binding.root
    }

    private fun determineConditionalNavigation(answer: String) {
        if (answer!=question.actualAnswer) {
            view?.findNavController()?.navigate(GameFragmentDirections.actionGameFragment2ToGameOverFragment())
        } else if (indexOfQuestion == ourGame.size-1) {
            view?.findNavController()?.navigate(GameFragmentDirections.actionGameFragment2ToGameWonFragment(4))
        }
        else
        {
            indexOfQuestion++
        }
    }

}