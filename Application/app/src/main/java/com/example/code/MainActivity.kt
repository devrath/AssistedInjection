package com.example.code

import android.content.Context
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.code.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // INJECTING-FACTORY: We can perform injection by variable or constructor
    @Inject lateinit var classToInjectFactory: ClassToInjectFactory

    // INJECTION DONE TO THIS CLASS
    private lateinit var classToInject: ClassToInject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnClickListeners()
    }

    /**
     * Set on click listeners for the buttons
     */
    private fun setOnClickListeners() {
        binding.apply {
            // INJECT - ACTION
            inject.setOnClickListener { view ->
                view.hideSoftInput()
                val textToInject = binding.txtToInjectId.text.toString()
                if(validateInput(textToInject)){
                    prepareTheFactory(ModelDataClass(textToInject))
                    displayMessage(view,getString(R.string.str_value_injected))
                }else{
                    displayMessage(view,getString(R.string.str_enter_value_to_inject))
                }
            }
            // DISPLAY - ACTION
            display.setOnClickListener { view ->
                view.hideSoftInput()
                val textToInject = classToInject.getInjectedValue()
                prepareTheFactory(ModelDataClass(textToInject))
                displayMessage(view,textToInject)
            }
        }
    }

    /**
     * Inject via a factory class
     */
    private fun prepareTheFactory(modelDataClass: ModelDataClass){
        classToInject = classToInjectFactory.create(modelDataClass)
    }

    /**
     * Validating the text if it is empty
     */
    private fun validateInput(textToInject: String): Boolean {
        return textToInject.isNotEmpty()
    }

    /**
     * Display a snack-bar message
     */
    private fun displayMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

    /**
     * Closing the keyboard
     */
    private fun View.hideSoftInput() {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }

}