package ru.barinov.jokesapplication.ui.webFragment

import android.annotation.SuppressLint
import android.os.*
import android.view.*
import android.webkit.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.barinov.jokesapplication.databinding.*

private const val BASE_URL= "https://www.icndb.com/api/"

class WebFragment: Fragment() {


    private var _binding: WebFragmentLayoutBinding? = null
    private lateinit var webView: WebView


    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {

        if(savedInstanceState!= null){
            onViewStateRestored(savedInstanceState)
        }

        overrideOnBackPressed()
        super.onCreate(savedInstanceState)
    }

    private fun overrideOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(this,
            object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                if(webView.canGoBack()){
                    webView.goBack()
                } else {
                    findNavController().popBackStack()
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = WebFragmentLayoutBinding.inflate(inflater, container, false )
        initWebView(savedInstanceState)
        return binding.root
    }

    private fun initWebView( savedInstanceState: Bundle?) {

        webView = binding.webView
        if(savedInstanceState!=null){
            webView.restoreState(savedInstanceState)
        } else{
            webView.loadUrl(BASE_URL)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setWebViewSettings()
        super.onViewCreated(view, savedInstanceState)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebViewSettings() {

        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        webView.saveState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }


}