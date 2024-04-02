package com.space.core_ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.MainThread
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.viewbinding.ViewBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.space.core_ui.logEvent
import kotlinx.coroutines.channels.Channel


abstract class BaseFragment<VB : ViewBinding>(
    @LayoutRes val layoutID: Int
) : Fragment() {

    protected lateinit var binding: VB
    protected lateinit var firebaseAnalytics: FirebaseAnalytics


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAnalytics = FirebaseAnalytics.getInstance(requireContext())
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, parentFragmentManager.javaClass.name)
        }
        init()
        beforeObserverListener()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layoutID, container, false)
        initViewTitle()
        initView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        afterObserverListener()
    }

    protected open fun initViewTitle() {}


    /**
     * Fragment 시작시 초기 설정이 필요한 코드를 담는 공간
     * 코드가 binding 전 실행되는 공간
     */
    protected open fun init(): Unit {}

    /**
     * Fragment 시작후 view 관련 코드를 담는 공간
     * binding 설정후 실행되는 공간
     */
    protected open fun initView(): Unit {}

    /**
     * Fragment 시작후 View가 만들어지고나서 실행되는 공간
     * 여가지 이벤트를 설정하는 공간
     */
    protected open fun initListener() {}

    protected open fun afterObserverListener() {}
    protected open fun beforeObserverListener() {}
}

