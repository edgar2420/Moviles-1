package com.ather.assignment.ui

import android.os.Bundle
import android.widget.GridLayout
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ather.assignment.util.OnSwipeTouchListener
import com.example.a2048_practico3.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val DEBUG_TAG = "DEBUG_TAG_MAIN"
    private val viewModel: MainVm by viewModels()
    lateinit var mGridLayout: GridLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUI()
        startGame()
    }

    /**
     * Initialize the UI when Screen loads first time
     */
    fun setUI() {
        mGridLayout = findViewById(R.id.gl_board) as GridLayout
        restart_btn.setOnClickListener {
            startGame()
        }
    }

    /**
     * Start or Restart the game, the game logic is being taken care in MainVM.
     */
    fun startGame() {
        setData()
        observeLiveData()
    }

    /**
     * Set the game data and game variables
     */
    private fun setData() {
        viewModel.clearData(mGridLayout)
        viewModel.makeMatrix(mGridLayout)
        viewModel.initGridOnGameStart(mGridLayout)
        handleSwipes()
    }

    /**
     * Observe all LiveData here, which are being emitted from MainVM.
     */
    private fun observeLiveData() {
        viewModel.scoreLiveData.observe(this, {
            it.let {
                score_tv.text = getString(R.string.score_label_to_show) + " " + it.toString()
            }
        })
    }

    /**
     * Use OnSwipeTouchListener Util to handle UI scipes here.
     */
    private fun handleSwipes() {

        cl_root.setOnTouchListener(object : OnSwipeTouchListener(this@MainActivity) {
            override fun onSwipeLeft() {
                if(!viewModel.isMatrixFull(viewModel.matrix)){
                    viewModel.traverseAndSlideTilesLeft(mGridLayout)
                }
                else {
                    Toast.makeText(applicationContext, getString(R.string.gave_over_message), LENGTH_LONG).show()
                }
                super.onSwipeLeft()
            }

            override fun onSwipeRight() {
                if(!viewModel.isMatrixFull(viewModel.matrix)) {
                    viewModel.traverseAndSlideTilesRight(mGridLayout)
                }
                else {
                    Toast.makeText(applicationContext, getString(R.string.gave_over_message), LENGTH_LONG).show()
                }
                super.onSwipeRight()
            }

            override fun onSwipeTop() {
                if(!viewModel.isMatrixFull(viewModel.matrix)) {
                    viewModel.traverseAndSlideTilesTop(mGridLayout)
                }
                else {
                    Toast.makeText(applicationContext, getString(R.string.gave_over_message), LENGTH_LONG).show()
                }
                super.onSwipeTop()
            }

            override fun onSwipeBottom() {
                if(!viewModel.isMatrixFull(viewModel.matrix)) {
                    viewModel.traverseAndSlideTilesBottom(mGridLayout)
                }
                else {
                    Toast.makeText(applicationContext, getString(R.string.gave_over_message), LENGTH_LONG).show()
                }
                super.onSwipeBottom()
            }
        })

    }
}