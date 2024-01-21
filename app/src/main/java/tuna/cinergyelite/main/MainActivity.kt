package tuna.cinergyelite.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import tuna.cinergyelite.R
import tuna.cinergyelite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        /*
         *we can use this if we want to navigate to login fragment if user is not logged in but commenting it as we are only using guest login
         */
        /*if (PreferenceHelper.getInt(Constants.USER_ID) == -1){
            navController.navigate(R.id.loginFragment)
        }else{
            navController.navigate(R.id.homeFragment)
        }*/

        navController.navigate(R.id.loginFragment)
    }
}