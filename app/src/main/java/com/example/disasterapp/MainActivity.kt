package com.example.disasterapp

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.GridLayoutManager
import com.example.disasterapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT))

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val colorPrimary = Color.parseColor("#6750A4")

        val typedValue = TypedValue()
        theme.resolveAttribute(android.R.attr.colorBackground, typedValue, true)
        val colorSurface = typedValue.data

        window.decorView.setBackgroundColor(colorPrimary)
        binding.root.setBackgroundColor(colorSurface)

        val basePadding = (16 * resources.displayMetrics.density).toInt()
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = bars.top
            }
            v.setPadding(
                bars.left + basePadding,
                basePadding,
                bars.right + basePadding,
                bars.bottom + basePadding
            )
            insets
        }

        val adapterDisaster = DisasterAdapter(generateDummy()) { disaster ->
            Toast.makeText(
                this@MainActivity,
                "You clicked on ${disaster.nameDisaster}",
                Toast.LENGTH_SHORT
            ).show()
        }

        with(binding) {

            rvDisaster.apply {
                adapter = adapterDisaster
                layoutManager = GridLayoutManager(this@MainActivity, 2)
            }
        }
    }

    fun generateDummy(): List<Disaster> {
        return listOf(
            Disaster("Tsunami", "Natural", R.drawable.img_tsunami),
            Disaster("Volcanic Eruption", "Natural", R.drawable.img_volcanic_eruption),
            Disaster("Earthquake", "Natural", R.drawable.img_earthquake),
            Disaster("Flood", "Natural", R.drawable.img_flood),
            Disaster("Fire", "Natural", R.drawable.img_fire),
            Disaster("Nuclear Accident", "Man-made", R.drawable.img_nuclear_accident),
            Disaster("Terrorist Attack", "Man-made", R.drawable.img_terrorist_attack),
            Disaster("War", "Man-made", R.drawable.img_war)
        )
    }
}