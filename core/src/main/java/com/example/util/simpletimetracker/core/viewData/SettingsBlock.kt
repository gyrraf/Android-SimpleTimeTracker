package com.example.util.simpletimetracker.core.viewData

enum class SettingsBlock {
    MainTop,
    AllowMultitasking,
    DarkMode,
    Language,
    Categories,
    Archive,
    DataEdit,
    MainBottom,

    RatingTop,
    RateUs,
    Feedback,
    Version,
    RatingBottom,

    NotificationsTop,
    NotificationsCollapse,
    NotificationsShow,
    NotificationsShowControls,
    NotificationsInactivity,
    NotificationsInactivityRecurrent,
    NotificationsInactivityDoNotDisturbStart,
    NotificationsInactivityDoNotDisturbEnd,
    NotificationsActivity,
    NotificationsActivityRecurrent,
    NotificationsActivityDoNotDisturbStart,
    NotificationsActivityDoNotDisturbEnd,
    NotificationsSystemSettings,
    NotificationsBottom,

    DisplayTop,
    DisplayCollapse,
    DisplayUntrackedHint,
    DisplayUntrackedInRecords,
    DisplayUntrackedInStatistics,
    DisplayUntrackedIgnoreShort,
    DisplayUntrackedRangeCheckbox,
    DisplayUntrackedRangeStart,
    DisplayUntrackedRangeEnd,
    DisplayCalendarView,
    DisplayReverseOrder,
    DisplayDaysInCalendar,
    DisplayShowActivityFilters,
    DisplayGoalsOnSeparateTabs,
    DisplayKeepScreenOn,
    DisplayWidgetBackground,
    DisplayMilitaryFormat,
    DisplayMonthDayFormat,
    DisplayProportionalFormat,
    DisplayShowSeconds,
    DisplaySortActivities,
    DisplayCardSize,
    DisplayBottom,

    AdditionalTop,
    AdditionalCollapse,
    AdditionalIgnoreShort,
    AdditionalShowTagSelection,
    AdditionalTagSelectionExcludeActivities,
    AdditionalCloseAfterOneTag,
    AdditionalKeepStatisticsRange,
    AdditionalFirstDayOfWeek,
    AdditionalRepeatButton,
    AdditionalShiftStartOfDay,
    AdditionalShiftStartOfDayButton,
    AdditionalShiftStartOfDayHint,
    AdditionalAutomatedTracking,
    AdditionalSendEvents,
    AdditionalBottom,

    BackupTop,
    BackupCollapse,
    BackupSave,
    BackupAutomatic,
    BackupAutomaticHint,
    BackupRestore,
    BackupBottom,

    ExportTop,
    ExportCollapse,
    ExportSpreadsheet,
    ExportSpreadsheetAutomatic,
    ExportSpreadsheetAutomaticHint,
    ExportSpreadsheetImport,
    ExportSpreadsheetImportHint,
    ExportIcs,
    ExportBottom,

    TranslatorsTop,
    TranslatorsTitle,
    TranslatorsBottom,

    ContributorsTop,
    ContributorsTitle,
    ContributorsBottom,
}