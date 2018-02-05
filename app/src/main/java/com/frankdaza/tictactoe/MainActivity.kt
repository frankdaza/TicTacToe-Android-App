package com.frankdaza.tictactoe

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : Activity() {

    var player1: ArrayList<Int> = ArrayList<Int>()
    var player2: ArrayList<Int> = ArrayList<Int>()
    var activePlayer: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * Get the selected button, for asign it
     * a cell number
     *
     * @author Frank Edward Daza G.
     * @version Jan 29, 2018
     */
    fun btnListener(view: View) {
        val btnSelected: Button = view as Button
        var cellId: Int = 0

        when (btnSelected.id) {
            R.id.btn1 -> cellId = 1
            R.id.btn2 -> cellId = 2
            R.id.btn3 -> cellId = 3
            R.id.btn4 -> cellId = 4
            R.id.btn5 -> cellId = 5
            R.id.btn6 -> cellId = 6
            R.id.btn7 -> cellId = 7
            R.id.btn8 -> cellId = 8
            R.id.btn9 -> cellId = 9
        }

        playGame(cellId, btnSelected)
    }

    /**
     * Select and disable the button for actual player, and
     * mark and X for player 1, and O for player two
     *
     * @author Frank Edward Daza G.
     * @version Jan 29, 2018
     * @param cellId Cell number for the button
     * @param btnSelected Button selected
     */
    fun playGame(cellId: Int, btnSelected: Button) {

        if (this.activePlayer == 1) {
            btnSelected.text = "X"
            btnSelected.setBackgroundResource(R.color.blue)
            this.player1.add(cellId)
            this.activePlayer = 2
            autoPlay()
        } else {
            btnSelected.text = "O"
            btnSelected.setBackgroundResource(R.color.darkGreen)
            this.player2.add(cellId)
            this.activePlayer = 1
        }

        btnSelected.isEnabled = false
        checkWinner()
    }

    /**
     * Check the winner of the game
     *
     * @author Frank Edward Daza G.
     * @version Jan 29, 2018
     */
    fun checkWinner() {
        var winner: Int = 0

        if ((this.player1.contains(1) && this.player1.contains(2) && this.player1.contains(3))
                || (this.player1.contains(4) && this.player1.contains(5) && this.player1.contains(6))
                || (this.player1.contains(7) && this.player1.contains(8) && this.player1.contains(9))
                || (this.player1.contains(1) && this.player1.contains(4) && this.player1.contains(7))
                || (this.player1.contains(2) && this.player1.contains(5) && this.player1.contains(8))
                || (this.player1.contains(3) && this.player1.contains(6) && this.player1.contains(9))
                || (this.player1.contains(1) && this.player1.contains(5) && this.player1.contains(9))
                || (this.player1.contains(3) && this.player1.contains(5) && this.player1.contains(7))) {
            winner = 1
        }

        if ((this.player2.contains(1) && this.player2.contains(2) && this.player2.contains(3))
                || (this.player2.contains(4) && this.player2.contains(5) && this.player2.contains(6))
                || (this.player2.contains(7) && this.player2.contains(8) && this.player2.contains(9))
                || (this.player2.contains(1) && this.player2.contains(4) && this.player2.contains(7))
                || (this.player2.contains(2) && this.player2.contains(5) && this.player2.contains(8))
                || (this.player2.contains(3) && this.player2.contains(6) && this.player2.contains(9))
                || (this.player2.contains(1) && this.player2.contains(5) && this.player2.contains(9))
                || (this.player2.contains(3) && this.player2.contains(5) && this.player2.contains(7))) {
            winner = 2
        }

        if (winner != 0) {
            if (winner == 1)
                Toast.makeText(this, "Player 1 wins the game", Toast.LENGTH_LONG).show()

            if (winner == 2)
                Toast.makeText(this, "Player 2 wins the game", Toast.LENGTH_LONG).show()
        }
    }

    /**
     * Play with the machine
     *
     * @author Frank Edward Daza G.
     * @version Jan 29, 2018
     */
    fun autoPlay() {
        var emptyCells: ArrayList<Int> = ArrayList<Int>()

        for (cellId in 1..9) {
            if (!(this.player1.contains(cellId) || this.player2.contains(cellId))) {
                emptyCells.add(cellId)
            }
        }

        val ramd = Random()
        val ramdIndex = ramd.nextInt(emptyCells.size)
        val cellId = emptyCells[ramdIndex]

        var btnSelected: Button

        when (cellId) {
            1 -> btnSelected = btn1
            2 -> btnSelected = btn2
            3 -> btnSelected = btn3
            4 -> btnSelected = btn4
            5 -> btnSelected = btn5
            6 -> btnSelected = btn6
            7 -> btnSelected = btn7
            8 -> btnSelected = btn8
            9 -> btnSelected = btn9
            else -> {
                btnSelected = btn1
            }
        }

        playGame(cellId, btnSelected)
    }
}
