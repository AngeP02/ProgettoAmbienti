package com.example.progetto

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View


class StepsGraphView : View {
    private val steps = 5000 // Dati di esempio

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Imposta il colore per disegnare il grafico
        val paint = Paint()
        paint.color = Color.BLUE

        // Disegna un rettangolo per rappresentare il numero di passi
        val barHeight = height * (steps.toFloat() / 10000) // Altezza massima del grafico
        canvas.drawRect(0f, height - barHeight, width.toFloat(), height.toFloat(), paint)
    }
}
