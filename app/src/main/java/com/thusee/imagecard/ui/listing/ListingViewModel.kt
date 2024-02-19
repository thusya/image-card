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

    private val _categoryState = MutableStateFlow<UIState<List<Category>>>(UIState.Loading)
    val categoryState = _categoryState.asStateFlow()

    init {
        fetchCategory()
    }

    fun fetchCategory() {
        viewModelScope.launch {
            repository.getCategory().catch { e ->
                _categoryState.value = UIState.Error(e)
            }.collectLatest { result ->
                _categoryState.value = UIState.Success(result)
            }
        }
    }
}

sealed class UIState<out T> {
    data object Loading : UIState<Nothing>()
    data class Success<T>(val data: T) : UIState<T>()
    data class Error(val exception: Throwable) : UIState<Nothing>()
    data object Empty : UIState<Nothing>()
}