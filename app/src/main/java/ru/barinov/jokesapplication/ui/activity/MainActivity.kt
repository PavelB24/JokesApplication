package ru.barinov.jokesapplication.ui.activity

import android.os.Bundle
import androidx.appcompat.app.*
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import ru.barinov.jokesapplication.R
import ru.barinov.jokesapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setupWithNavController(findNavController(R.id.fragment_host))

    }


}