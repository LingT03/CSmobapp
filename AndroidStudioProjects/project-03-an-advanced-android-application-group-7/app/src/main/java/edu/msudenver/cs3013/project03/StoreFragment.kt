package edu.msudenver.cs3013.project03

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
private lateinit var answerListener: AnswerListener

/**
 * Fragment that displays a webview of a grocery store locator.
 */
class StoreFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var it = context
        val webView = it?.let { it1 -> WebView(it1.applicationContext) }
        if (webView != null) { webView.settings.javaScriptEnabled = true }
        if (webView != null) { webView.loadUrl("https://www.google.com") }
        return inflater.inflate(R.layout.fragment_store, container, false)//inflates fragment
    }

    override fun onAttach(context: Context) { //create context for webview
        super.onAttach(context)
        if (context is AnswerListener) {
            answerListener = context
        } else {
            throw RuntimeException("Must implement answerListener")
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mWebView = view.findViewById<View>(R.id.webview) as WebView
        mWebView.getSettings().setJavaScriptEnabled(true)
        mWebView.setWebViewClient(WebViewClient())
        mWebView.loadUrl("https://www.kingsoopers.com")
    }

    companion object { // creates a companion object
        @JvmStatic
        fun newInstance() =
            StoreFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}