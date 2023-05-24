package com.zahi.whereisjudang.viewmodel

import androidx.lifecycle.LiveData
import com.zahi.whereisjudang.base.ViewState
import com.zahi.whereisjudang.base.viewmodel.BaseViewModel
import com.zahi.whereisjudang.livedata.SingleLiveData
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
): BaseViewModel() {

    private var _state: SingleLiveData<ViewState> = SingleLiveData()
    val state: LiveData<ViewState> = _state
}