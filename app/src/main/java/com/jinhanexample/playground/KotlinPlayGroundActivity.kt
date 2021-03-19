package com.jinhanexample.playground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jinhanexample.R
import java.io.File

class KotlinPlayGroundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_play_ground)

        val creditCard = CreditCard(1)
        val payment_1 = Payment(creditCard, 1)
        val payment_2 = Payment(creditCard, 1)
        val payment_3 = Payment(creditCard, 1)
        val payment_4 = Payment(creditCard, 1)
        val newPayment = payment_1.combine(payment_2).combine(payment_3)

        println("newPayment : ${newPayment.amount}")
    }


    class Payment(val creditCard: CreditCard, val amount: Int) {

        fun combine(payment: Payment): Payment =
            if (creditCard == payment.creditCard)
                Payment(creditCard, amount + payment.amount)
            else
                throw IllegalStateException("Cards don't match")

    }

    companion object {
        fun groupByCard(payments: List<Payment>): List<Payment> =
            payments.groupBy { it.creditCard }.values.map { it.reduce(Payment::combine) }

    }

    class CreditCard(val cardNum: Int)


}