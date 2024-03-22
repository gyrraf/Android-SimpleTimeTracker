package com.example.util.simpletimetracker.feature_dialogs.recordTagSelectionTypes.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.util.simpletimetracker.core.extension.addOrRemove
import com.example.util.simpletimetracker.core.extension.set
import com.example.util.simpletimetracker.core.mapper.RecordTypeViewDataMapper
import com.example.util.simpletimetracker.core.repo.ResourceRepo
import com.example.util.simpletimetracker.domain.interactor.PrefsInteractor
import com.example.util.simpletimetracker.domain.interactor.RecordTypeInteractor
import com.example.util.simpletimetracker.domain.model.RecordType
import com.example.util.simpletimetracker.feature_base_adapter.ViewHolderType
import com.example.util.simpletimetracker.feature_base_adapter.divider.DividerViewData
import com.example.util.simpletimetracker.feature_base_adapter.empty.EmptyViewData
import com.example.util.simpletimetracker.feature_base_adapter.info.InfoViewData
import com.example.util.simpletimetracker.feature_base_adapter.loader.LoaderViewData
import com.example.util.simpletimetracker.feature_base_adapter.recordType.RecordTypeViewData
import com.example.util.simpletimetracker.feature_dialogs.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordTagSelectionTypesViewModel @Inject constructor(
    private val recordTypeInteractor: RecordTypeInteractor,
    private val prefsInteractor: PrefsInteractor,
    private val recordTypeViewDataMapper: RecordTypeViewDataMapper,
    private val resourceRepo: ResourceRepo,
) : ViewModel() {

    val types: LiveData<List<ViewHolderType>> by lazy {
        return@lazy MutableLiveData<List<ViewHolderType>>().let { initial ->
            viewModelScope.launch {
                initial.value = listOf(LoaderViewData())
                initial.value = loadRecordTypesViewData()
            }
            initial
        }
    }
    val close: LiveData<Unit> = MutableLiveData()
    val saveButtonEnabled: LiveData<Boolean> = MutableLiveData(true)

    private var initialized: Boolean = false
    private var recordTypesCache: List<RecordType> = emptyList()
    private var typeIdsSelected: MutableList<Long> = mutableListOf()

    fun onRecordTypeClick(item: RecordTypeViewData) {
        typeIdsSelected.addOrRemove(item.id)
        updateRecordTypesViewData()
    }

    fun onShowAllClick() {
        typeIdsSelected.addAll(recordTypesCache.map(RecordType::id))
        updateRecordTypesViewData()
    }

    fun onHideAllClick() {
        typeIdsSelected.clear()
        updateRecordTypesViewData()
    }

    fun onSaveClick() {
        saveButtonEnabled.set(false)
        viewModelScope.launch {
            // Remove non existent ids.
            recordTypesCache
                .filter { it.id in typeIdsSelected }
                .map { it.id }
                .let { prefsInteractor.setRecordTagSelectionExcludeActivities(it) }
            close.set(Unit)
        }
    }

    private fun updateRecordTypesViewData() = viewModelScope.launch {
        val data = loadRecordTypesViewData()
        types.set(data)
    }

    private suspend fun loadRecordTypesViewData(): List<ViewHolderType> {
        val numberOfCards = prefsInteractor.getNumberOfCards()
        val isDarkTheme = prefsInteractor.getDarkMode()

        if (!initialized) {
            recordTypesCache = recordTypeInteractor.getAll()
                .filter { !it.hidden }
            val recordTypeIds = recordTypesCache.map { it.id }
            typeIdsSelected = prefsInteractor.getRecordTagSelectionExcludeActivities()
                .filter { it in recordTypeIds }
                .toMutableList()
            initialized = true
        }

        fun map(type: RecordType): ViewHolderType {
            return recordTypeViewDataMapper.map(
                recordType = type,
                numberOfCards = numberOfCards,
                isDarkTheme = isDarkTheme,
                isChecked = null,
            )
        }

        val selected = recordTypesCache
            .filter { it.id in typeIdsSelected }
            .map(::map)
        val available = recordTypesCache
            .filter { it.id !in typeIdsSelected }
            .map(::map)

        val result = mutableListOf<ViewHolderType>()

        if (recordTypesCache.isEmpty()) {
            result += EmptyViewData(
                message = resourceRepo.getString(R.string.record_types_empty),
            )
            return result
        }
        if (selected.isNotEmpty()) {
            result += InfoViewData(resourceRepo.getString(R.string.something_selected))
            result += selected
        } else {
            result += InfoViewData(resourceRepo.getString(R.string.nothing_selected))
        }
        if (available.isNotEmpty()) {
            result += DividerViewData(0)
        }
        result += available

        return result
    }
}
