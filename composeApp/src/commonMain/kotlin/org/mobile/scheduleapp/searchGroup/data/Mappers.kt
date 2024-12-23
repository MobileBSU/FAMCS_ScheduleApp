package org.mobile.scheduleapp.searchGroup.data

import org.mobile.scheduleapp.searchGroup.domain.model.GroupSearchResultData

internal fun GroupSearchResponseData.toGroupSearchResultData(): GroupSearchResultData {
    return GroupSearchResultData(id, course, groupNumber, name, subGroupNumber)
}