package com.lyhorng.practiceapp.ui.qr

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.lyhorng.practiceapp.R

class QrCodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code)

        val qrImageView: ImageView = findViewById(R.id.qrImageView)
        val qrText: TextView = findViewById(R.id.qrText)
        val itemTitle = intent.getStringExtra("item_title")

        // Sample QR data (customize based on your needs)
        val qrData = "KHQR Payment\nReceiver: 027 238 7010\nAmount: 0.00 USD\nName: Cash-Code User"
        qrText.text = "Scan to use $itemTitle"

        try {
            val bitmap = encodeAsBitmap(qrData, 300, 300)
            qrImageView.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }

    @Throws(WriterException::class)
    private fun encodeAsBitmap(str: String, width: Int, height: Int): Bitmap {
        val result = MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, width, height, null)
        val w = result.width
        val h = result.height
        val pixels = IntArray(w * h)
        for (y in 0 until h) {
            val offset = y * w
            for (x in 0 until w) {
                pixels[offset + x] = if (result[x, y]) 0xFF000000.toInt() else 0xFFFFFFFF.toInt()
            }
        }
        val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, w, 0, 0, w, h)
        return bitmap
    }
}