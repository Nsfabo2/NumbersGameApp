package com.example.numbersgameapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

/*
- ask the user to guess a number between 0 and 10
- generate a random number between 0 and 10
- take user input in the Edit Text field
- log user input and display whether or not the guess was correct
- only allow the user to enter numbers
*/

class MainActivity : AppCompatActivity() {

    lateinit var UserGuesses: ArrayList<Int>
    lateinit var RemeinngGuesses: TextView
    lateinit var UserInput: EditText
    lateinit var Submitbtn: Button
    lateinit var MyRV: RecyclerView
    lateinit var Conslo: ConstraintLayout
    var counter: Int = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //ask the user to guess a number between 0 and 10
        UserGuesses = ArrayList()
        RemeinngGuesses = findViewById(R.id.tvTrys)
        UserInput = findViewById(R.id.UserInput)
        Submitbtn = findViewById(R.id.GuessBtn)
        MyRV = findViewById(R.id.MyRV)
        Conslo = findViewById(R.id.Conslo)
        Submitbtn.setOnClickListener (){ ButtonClicked() }
    }//end oncreate

     fun ContinueDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Play again?")
        builder.setPositiveButton("Yes") { _: DialogInterface, i: Int -> this.recreate()}
        builder.setNegativeButton("No") { _: DialogInterface, i: Int -> finish()}
        builder.show()
    }//ContinueDialog

    //log user input and display whether or not the guess was correct
    fun InputChecked(rando: Int , toCheck: Int){
        if (toCheck == rando) {
            RemeinngGuesses.text = "Correct!!"
            UserGuesses.add(toCheck)
            MyRV.adapter = RecyclerViewAdapter(UserGuesses)
            MyRV.layoutManager = LinearLayoutManager(this)
            UserInput.setText("")
        } else {
            UserGuesses.add(toCheck)
            MyRV.adapter = RecyclerViewAdapter(UserGuesses)
            MyRV.layoutManager = LinearLayoutManager(this)
            UserInput.setText("")
            counter--
            RemeinngGuesses.text = "You Have $counter guesses left"
        }
    }//end InputChecked

    //generate a random number between 0 and 10
    fun ButtonClicked(){
        var rando = Random.nextInt(10)
        var isCheck = true

        if (counter > 0) {
            var toCheck = UserInput.text.toString()
            try {
                //only allow the user to enter numbers
                Integer.parseInt(toCheck)
            }catch (e:Exception){
                isCheck = false
            }

            if (isCheck == true) {
                InputChecked(rando , toCheck.toInt())
            } else {
                Snackbar.make(Conslo, "Enter a Number", Snackbar.LENGTH_LONG).show()
            }
        }else {
            Snackbar.make(Conslo, "Game Over :(", Snackbar.LENGTH_LONG).show()
            ContinueDialog()
        }
    }//end ButtonClicked

}//end class