package com.example.hedgehodtestapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.example.hedgehodtestapp.R

class BrowserFragment : Fragment() {

    private var webView : WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null)
            webView?.restoreState(savedInstanceState.getBundle("webViewState")!!)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_browser, container, false)
        webView = view.findViewById(R.id.webView)
        return view
    }

    fun goBackWebView(){
        webView?.goBack()
    }

    //need save state
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView?.loadUrl("https://www.icndb.com/api/")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val webViewBundle = Bundle()
        webView!!.saveState(webViewBundle)
        outState.putBundle("webViewState", webViewBundle)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            BrowserFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}