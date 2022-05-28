package shady.samir.photolab.views.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import shady.samir.photolab.R
import shady.samir.photolab.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAnimation()

        Handler().postDelayed({
                startActivity(Intent(this, HomeActivity::class.java))

            finish()
        }, 1000)

    }




    private fun setAnimation() {

        val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.feed_in)
        animation.duration = 1000
        binding.startImg.startAnimation(animation)
    }

}
