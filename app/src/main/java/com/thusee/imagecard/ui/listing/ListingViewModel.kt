package com.thusee.imagecard.ui.listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thusee.imagecard.domain.model.Category
import com.thusee.imagecard.domain.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListingViewModel @Inject constructor(
    private val repository: CategoryRepository
) : ViewModel() {

    private val _categoryState = MutableStateFlow<UIState>(UIState.Loading)
    val categoryState = _categoryState.asStateFlow()

    init {
        fetchCategory()
    }

    fun fetchCategory() {
        viewModelScope.launch {
            repository.getCategory().catch { e ->
                _categoryState.value = UIState.Error(e)
            }.collectLatest { result ->
                if (result.isEmpty()){
                    _categoryState.value = UIState.Empty
                }else {
                    _categoryState.value = UIState.Success(result)
                }
            }
        }
    }
}

sealed class UIState {
    data object Loading : UIState()
    data class Success(val data: List<Category>) : UIState()
    data class Error(val exception: Throwable) : UIState()
    data object Empty : UIState()
}