package com.bright.aroundafrica


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.bright.aroundafrica.fragment.AboutFragment
import com.bright.aroundafrica.fragment.LocalPlacesFragment
import com.bright.aroundafrica.fragment.QRScannerFragment
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var fragment: Fragment = LocalPlacesFragment.newInstance()

        topAppBar.setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.END)
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_search -> {
                    fragment = LocalPlacesFragment.newInstance()
                }
                R.id.action_qr_scan -> {
                    fragment = QRScannerFragment.newInstance()
                }
                R.id.action_about -> {
                    fragment = AboutFragment.newInstance()
                }
            }
            replaceFragment(fragment)
            menuItem.isChecked = true
            drawerLayout.closeDrawers()
            true
        }

        replaceFragment(fragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.mainFrame, fragment)
        ft.commit()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}