package com.example.hedgehodtestapp.presentation.fragments.browser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.lifecycle.ViewModelProvider
import com.example.hedgehodtestapp.databinding.FragmentBrowserBinding

class BrowserFragment : Fragment() {

    private var _binding: FragmentBrowserBinding? = null
    private val mBinding get() = _binding!!

    private val mViewModel: BrowserFragmentViewModel by lazy {
        ViewModelProvider(this)[BrowserFragmentViewModel::class.java]
    }

    private var webView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null)
            webView?.restoreState(savedInstanceState.getBundle("webViewState")!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBrowserBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    fun goBackWebView() {
        webView?.goBack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webViewSettings()
    }

    private fun webViewSettings() {
        webView?.settings?.setSupportZoom(true)
        webView?.settings?.loadsImagesAutomatically
        webView?.settings?.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        webView?.loadUrl(WEB_VIEW_URL)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val webViewBundle = Bundle()
        webView!!.saveState(webViewBundle)
        outState.putBundle("webViewState", webViewBundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val WEB_VIEW_URL = "https://www.icndb.com/api/"
        fun newInstance() = BrowserFragment()
    }
}