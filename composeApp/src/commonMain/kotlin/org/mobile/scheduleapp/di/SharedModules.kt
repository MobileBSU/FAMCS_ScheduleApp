package org.mobile.scheduleapp.di

import org.koin.dsl.module
import org.mobile.scheduleapp.auth.data.AuthRepositoryImpl
import org.mobile.scheduleapp.auth.data.AuthService
import org.mobile.scheduleapp.auth.domain.repository.AuthRepository
import org.mobile.scheduleapp.auth.domain.usecase.SignInUseCase
import org.mobile.scheduleapp.auth.domain.usecase.SignUpUseCase
import org.mobile.scheduleapp.common.util.provideDispatcher
import org.mobile.scheduleapp.searchGroup.data.SearchGroupRepositoryImpl
import org.mobile.scheduleapp.searchGroup.data.SearchGroupService
import org.mobile.scheduleapp.searchGroup.domain.repository.SearchGroupRepository
import org.mobile.scheduleapp.searchGroup.domain.usecase.GetAllGroupsUseCase
import org.mobile.scheduleapp.searchGroup.domain.usecase.GetGroupsByNameUseCase
import org.mobile.scheduleapp.searchTeacher.data.SearchTeacherRepositoryImpl
import org.mobile.scheduleapp.searchTeacher.data.SearchTeacherService
import org.mobile.scheduleapp.searchTeacher.domain.repository.SearchTeacherRepository
import org.mobile.scheduleapp.searchTeacher.domain.usecase.GetAllTeachersUseCase
import org.mobile.scheduleapp.searchTeacher.domain.usecase.GetTeachersByNameUseCase
import org.mobile.scheduleapp.subject.data.SubjectRepositoryImpl
import org.mobile.scheduleapp.subject.data.SubjectService
import org.mobile.scheduleapp.subject.domain.repository.SubjectRepository
import org.mobile.scheduleapp.subject.domain.usecase.GetSubjectsByGroupUseCase
import org.mobile.scheduleapp.subject.domain.usecase.GetSubjectsByTeacherUseCase

private val authModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    factory { AuthService() }
    factory { SignUpUseCase() }
    factory { SignInUseCase() }
}

private val searchModule = module {
    single<SearchGroupRepository> { SearchGroupRepositoryImpl(get(), get()) }
    factory { SearchGroupService() }
    factory { GetAllGroupsUseCase() }
    factory { GetGroupsByNameUseCase() }
}

private val searchTeacher = module {
    single<SearchTeacherRepository> { SearchTeacherRepositoryImpl(get(), get()) }
    factory { SearchTeacherService() }
    factory { GetAllTeachersUseCase() }
    factory { GetTeachersByNameUseCase() }
}

private val subject = module {
    single<SubjectRepository> {SubjectRepositoryImpl(get(), get())}
    factory { SubjectService() }
    factory { GetSubjectsByTeacherUseCase() }
    factory { GetSubjectsByGroupUseCase() }
}

private val utilityModule = module {
    factory { provideDispatcher() }
}

fun getSharedModules() = listOf(
    authModule,
    utilityModule,
    searchModule,
    searchTeacher,
    subject)