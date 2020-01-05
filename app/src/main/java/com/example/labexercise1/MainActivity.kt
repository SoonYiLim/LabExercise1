package com.example.labexercise1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.text.isDigitsOnly
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.buttonCalculate).setOnClickListener{
            calculate(it)
        }

        findViewById<Button>(R.id.buttonReset).setOnClickListener{
            clear(it)
        }

    }

    private fun calculate(view:View){
        val price = findViewById<EditText>(R.id.editTextCarPrice)
        val downp = findViewById<EditText>(R.id.editTextDownPayment)
        val period = findViewById<EditText>(R.id.editTextLoanPeriod)
        val interest = findViewById<EditText>(R.id.editTextInterestRate)

        val dLoan = findViewById<TextView>(R.id.textViewLoan)
        val dInterest = findViewById<TextView>(R.id.textViewInterest)
        val dRepayment = findViewById<TextView>(R.id.textViewMonthlyRepayment)

        when{
            price.text.isBlank() -> price.setText(R.string.error_input)
            downp.text.isBlank() -> downp.setText(R.string.error_input)
            period.text.isBlank() -> period.setText(R.string.error_input)
            interest.text.isBlank() -> interest.setText(R.string.error_input)
            else ->{
                val loan  = calLoan(price.text.toString().toDouble(), downp.text.toString().toDouble() )
                val interests = calInterest(loan,interest.text.toString().toDouble(), period.text.toString().toDouble() )
                val payment = calRepayment(loan,interests,period.text.toString().toDouble())

                dLoan.setText(dLoan.text.toString().plus(loan.toString()))
                dInterest.setText(dInterest.text.toString().plus(interests.toString()))
                dRepayment.setText(dRepayment.text.toString().plus(payment.toString()))


            }
        }


    }
    private fun clear(view:View){

        val price = findViewById<EditText>(R.id.editTextCarPrice)
        val downp = findViewById<EditText>(R.id.editTextDownPayment)
        val period = findViewById<EditText>(R.id.editTextLoanPeriod)
        val interest = findViewById<EditText>(R.id.editTextInterestRate)

        val dLoan = findViewById<TextView>(R.id.textViewLoan)
        val dInterest = findViewById<TextView>(R.id.textViewInterest)
        val dRepayment = findViewById<TextView>(R.id.textViewMonthlyRepayment)

        price.setText("")
        downp.setText("")
        period.setText("")
        interest.setText("")
        dLoan.setText(R.string.loan)
        dInterest.setText(R.string.interest)
        dRepayment.setText(R.string.monthly_repayment)
    }


        private fun calLoan( a: Double, b: Double) :Double{
            return a - b
        }
        private fun calInterest (a:Double , b: Double, c: Double) : Double{
            return a*(b / 100) * c
        }
        private fun calRepayment (a:Double , b: Double, c: Double) : Double{
            return (a + b) / c / 12
        }


}
