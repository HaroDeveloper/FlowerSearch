package element.list.flowersmvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import element.list.flowersmvp.home.HomeFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, HomeFragment())
            .commitAllowingStateLoss()
    }
}
