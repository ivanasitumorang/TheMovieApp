package com.azuka.themovieapp.utils

import androidx.paging.PagedList
import io.mockk.every
import io.mockk.mockk


/**
 * Created by ivanaazuka on 10/01/21.
 * Android Engineer
 */

object PagedListUtil {
    fun <T> mockPagedList(list: List<T>): PagedList<T> {
//        val pagedList = Mockito.mock(PagedList::class.java) as PagedList<T>
        val pagedList: PagedList<T> = mockk()
        every { pagedList[any()] }
            .answers {
                val index = invocation.args[0] as Int
                list[index]
            }
        every { pagedList.size }
            .returns(list.size)

//        `when`(pagedList[anyInt()]).then { invocation ->
//            val index = invocation.arguments.first() as Int
//            list[index]
//        }
//        `when`(pagedList.size).thenReturn(list.size)

        return pagedList
    }
}